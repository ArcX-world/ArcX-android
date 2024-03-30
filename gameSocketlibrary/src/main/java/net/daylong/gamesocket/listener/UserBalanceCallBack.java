package net.daylong.gamesocket.listener;

public interface UserBalanceCallBack {
    void userBalance(long goldNum, long integralNum);


    /**
     * 用户能量
     *
     * @param cnAmt //当前进度数量
     * @param ttAmt //总进度数量
     * @param cgAmt //变更数量
     * @param lfTm //下次刷新时间
     */
    void userEnergy(int cnAmt, int ttAmt, int cgAmt, int lfTm);
}
