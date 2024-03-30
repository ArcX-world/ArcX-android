package com.daylong.gamelibrary.strategy;

import com.daylong.gamelibrary.act.BaseGameActivity;
import com.daylong.gamelibrary.callback.OnChartCallBack;
import com.daylong.gamelibrary.callback.OnEnergyCallBack;
import com.daylong.gamelibrary.callback.OnGameDollCallBack;
import com.daylong.gamelibrary.callback.OnGameWebSocketCallBack;
import com.daylong.gamelibrary.meuns.GameCmdType;
import com.daylong.gamelibrary.meuns.GameOperateType;
import com.daylong.gamelibrary.response.GameInfoReturnResponse;

import net.daylong.baselibrary.utils.JsonUtil;
import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.gamesocket.mrg.WebSocketMrg;
import net.daylong.gamesocket.strategy.response.ISocketResponseStrategy;
import net.daylong.gamesocket.strategy.response.WebSocketUserStrategy;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WebSocketGameStrategy extends ISocketResponseStrategy {


    private BaseGameActivity activity;
    private Set<GameOperateType> dollOperateTypes;
    private static WebSocketGameStrategy mInstance;
    private OnChartCallBack onChartCallBack;
    private final List<OnGameWebSocketCallBack> onGameWebSocketCallBacks;


    public WebSocketGameStrategy(List<OnGameWebSocketCallBack> onGameWebSocketCallBacks) {
        this.onGameWebSocketCallBacks = onGameWebSocketCallBacks;
    }

    public void setActivity(BaseGameActivity activity) {
        this.activity = activity;
    }

    public BaseGameActivity getActivity() {
        return activity;
    }

    public static WebSocketGameStrategy getInstance() {
        if (null == mInstance) {
            synchronized (WebSocketMrg.class) {
                if (mInstance == null) {
                    mInstance = new WebSocketGameStrategy();
                }
            }
        }
        return mInstance;
    }


    private OnGameDollCallBack onGameDollCallBack;


    public void deleteAll() {
        removerOnGameDollCallBack();
        removerGameWebSocketCallBacksAll();

    }

    public void setOnGameDollCallBack(OnGameDollCallBack onGameDollCallBack) {
        this.onGameDollCallBack = onGameDollCallBack;
    }

    public void removerOnGameDollCallBack() {
        this.onGameDollCallBack = null;
        // 移除数据
        if (dollOperateTypes != null) {
            dollOperateTypes.clear();
        }

        //移除
        dollOperateTypes = null;

    }


    public WebSocketGameStrategy() {
        onGameWebSocketCallBacks = new ArrayList<OnGameWebSocketCallBack>();

    }

    // 注册回调事件
    public void register(OnGameWebSocketCallBack callBack) {
        onGameWebSocketCallBacks.add(callBack);
    }

    public void remover(OnGameWebSocketCallBack callBack) {
        onGameWebSocketCallBacks.removeIf(userBalanceCallBack -> userBalanceCallBack == callBack);
    }

    public void removerGameWebSocketCallBacksAll() {
        onGameWebSocketCallBacks.clear();
    }


    @Override
    public void addCmd() {

        //设置需要游戏接受的内容
        for (GameCmdType value : GameCmdType.values()) {
            addCmd(value.getReturnCdm());
        }


    }

    @Override
    public void issue(Integer cmd, JSONObject msg) {
        MyLogUtil.e("rag==回调收到>" + cmd + "<>" + msg);

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject serverMsg = msg.optJSONObject("serverMsg");

                    int productId = serverMsg.optInt("devId");
                    int operateType = serverMsg.optInt("hdlTp", -1);
                    //余额
                    int goldNum = serverMsg.optInt("gdAmt", -1);
                    //积分
                    int integralNum = serverMsg.optInt("axcAmt", -1);
                    GameOperateType operateState = GameOperateType.getOperateState(operateType);


                    if (goldNum != -1) {
                        WebSocketUserStrategy.getInstance().forCallBack(goldNum, integralNum);
                        MyLogUtil.e("rag==>余额:" + goldNum + "<积分:>" + integralNum);

                    }
                    String desc = GameCmdType.getGameReturnCmdType(cmd).getDesc();
                    MyLogUtil.e("rag==>" + desc + "<类型>" + (operateState != null ? operateState.getDesc() : "房间") + "信息:" + msg);

//
                    if (operateState == null) {
                        MyLogUtil.e("rag==房间操作>" + msg);
                        //默认


                        if (cmd.equals(GameCmdType.S2C_KICK_OUT_PRODUCT.getReturnCdm())) {

                            if (activity != null) {
                                activity.finish();
                                return;
                            }
                        }

                        for (OnGameWebSocketCallBack onGameWebSocketCallBack : onGameWebSocketCallBacks) {
                            //房间操作
//                房间信息
                            Integer returnCdm = GameCmdType.C2S_ROOM_MSG.getReturnCdm();
                            if (returnCdm.equals(cmd)) {
                                onGameWebSocketCallBack.gameInfo(JsonUtil.fromJsonToObject(msg.toString(), GameInfoReturnResponse.class).getServerMsg());
                            } else if (GameCmdType.C2S_CHARTER.getReturnCdm().equals(cmd) || GameCmdType.C2S_CHARTER_REFRESH.getReturnCdm().equals(cmd)) {
                                long charterBalance = serverMsg.optLong("charterBalance", 0);
                                int leftTime = serverMsg.optInt("leftTime", 0);
                                long endTime = serverMsg.optLong("endTime", 0);
                                onGameWebSocketCallBack.onChart(charterBalance, leftTime, endTime);
                            }  else if (cmd.equals(GameCmdType.C2S_ENERGY_MSG.getReturnCdm())) {
                                //能量返回

                                onGameWebSocketCallBack.onEnergy(serverMsg.optInt("cnAmt", 0)
                                        , serverMsg.optInt("ttAmt", 0)
                                        , serverMsg.optInt("cgAmt", 0)
                                        , serverMsg.optInt("lfTm", 0));
                            }
                        }


                    } else {
                        //  开始游戏
                        if (operateState == GameOperateType.START
                                || operateState == GameOperateType.PUSH_COIN
                                || operateState == GameOperateType.GET_COIN
                                || operateState == GameOperateType.CHARTER_SETTLEMENT
                        ) {
                            for (OnGameWebSocketCallBack userBalanceCallBack : onGameWebSocketCallBacks) {
                                Integer returnCdm = GameCmdType.C2S_ROOM_MSG.getReturnCdm();
                                if (operateState == GameOperateType.START) {
                                    userBalanceCallBack.startGameSuc();
                                } else if (operateState == GameOperateType.PUSH_COIN) {
                                    userBalanceCallBack.onRefreshTime();
                                } else if (operateState == GameOperateType.CHARTER_SETTLEMENT) {
                                    //包机结算
                                    JSONObject charterSettlementMsg = serverMsg.optJSONObject("charterSettlementMsg");
                                    long charterBalance = charterSettlementMsg.optLong("charterBalance");
                                    long returnNum = charterSettlementMsg.optLong("returnNum");
                                    long totalNum = charterSettlementMsg.optLong("totalNum");
                                    userBalanceCallBack.onChartEndReturn(charterBalance, returnNum, totalNum);

                                } else if (operateState == GameOperateType.GET_COIN) {
                                    long fallNum = serverMsg.optLong("flAmt", 0);
                                    if (fallNum > 0) {
                                        userBalanceCallBack.onCoinReturn(fallNum);
                                    }
                                }
                                MyLogUtil.e("rag==游戏操作>" + msg);

                            }
                        }

                    }
                }
            });
        }
    }
}
