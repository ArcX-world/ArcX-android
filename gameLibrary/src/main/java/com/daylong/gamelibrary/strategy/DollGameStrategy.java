package com.daylong.gamelibrary.strategy;

import com.daylong.gamelibrary.act.BaseGameActivity;
import com.daylong.gamelibrary.callback.OnGameDollCallBack;
import com.daylong.gamelibrary.callback.OnGameWebSocketCallBack;
import com.daylong.gamelibrary.meuns.GameCmdType;
import com.daylong.gamelibrary.meuns.GameOperateType;
import com.daylong.httplibrary.bean.AwardBean;

import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.gamesocket.mrg.WebSocketMrg;
import net.daylong.gamesocket.strategy.response.ISocketResponseStrategy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 抓娃娃
 */
public class DollGameStrategy extends ISocketResponseStrategy {


    private BaseGameActivity activity;
    private Set<GameOperateType> dollOperateTypes;
    private static DollGameStrategy mInstance;


    public DollGameStrategy(List<OnGameDollCallBack> onGameWebSocketCallBacks) {
        this.onGameWebSocketCallBacks = onGameWebSocketCallBacks;
    }

    public void setActivity(BaseGameActivity activity) {
        this.activity = activity;
    }

    public BaseGameActivity getActivity() {
        return activity;
    }

    public static DollGameStrategy getInstance() {
        if (null == mInstance) {
            synchronized (WebSocketMrg.class) {
                if (mInstance == null) {
                    mInstance = new DollGameStrategy();
                }
            }
        }
        return mInstance;
    }

    private final List<OnGameDollCallBack> onGameWebSocketCallBacks;


    public void deleteAll() {
        removerOnGameDollCallBack();
        removerGameWebSocketCallBacksAll();
        mInstance = null;
    }


    public void removerOnGameDollCallBack() {
        // 移除数据
        if (dollOperateTypes != null) {
            dollOperateTypes.clear();
        }

        //移除
        dollOperateTypes = null;

    }


    public DollGameStrategy() {
        onGameWebSocketCallBacks = new ArrayList<OnGameDollCallBack>();

    }

    // 注册回调事件
    public void register(OnGameDollCallBack callBack) {
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
        addCmd(GameCmdType.GAME_CMD_DOLL.getReturnCdm());
        addCmd(GameCmdType.GAME_CMD_BALL.getReturnCdm());


    }

    @Override
    public void issue(Integer cmd, JSONObject msg) {
        MyLogUtil.e("rag==娃娃机回调收到>" + cmd + "<>" + msg);

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject serverMsg = msg.optJSONObject("serverMsg");

                    int productId = serverMsg.optInt("devId");
                    int operateType = serverMsg.optInt("hdlTp", -1);
                    //余额
                    //积分
                    GameOperateType operateState = GameOperateType.getOperateState(operateType);


                    if (operateState != null) {
                        for (OnGameDollCallBack onGameWebSocketCallBack : onGameWebSocketCallBacks) {
                            //下抓
                            if (operateState == GameOperateType.CATCH) {

                                onGameWebSocketCallBack.catchDown();


                                //结果
                            } else if (operateState == GameOperateType.CATCH_RESULT || operateState == GameOperateType.ROCK) {


                                int dolRes = serverMsg.optInt("dolRes");


                                ArrayList<AwardBean> list = null;
                                if (dolRes == 2) {
                                    list = new ArrayList<>();
                                    JSONArray awdTbln = serverMsg.optJSONArray("awdTbln");
                                    for (int i = 0; i < awdTbln.length(); i++) {
                                        JSONObject jsonObject = awdTbln.optJSONObject(i);

                                        int cmdTp = jsonObject.optInt("cmdTp");
                                        long awdAmt = jsonObject.optLong("awdAmt");
                                        String awdPct = jsonObject.optString("awdPct");
                                        list.add(new AwardBean(cmdTp, awdPct, awdAmt));

                                    }
                                }

                                if (dolRes > 0) {
                                    onGameWebSocketCallBack.catchReturn(dolRes == 2, list);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
