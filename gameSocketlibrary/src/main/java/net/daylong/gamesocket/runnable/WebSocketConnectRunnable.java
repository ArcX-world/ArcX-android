package net.daylong.gamesocket.runnable;


import net.daylong.gamesocket.mrg.WebSocketMrg;

public class WebSocketConnectRunnable implements Runnable {


    /**
     * 重新连接次数
     */
    private int reconnectNum;
    private static final int NEXT_RECONNECT_TIME = 1000 * 5; //5秒

    @Override
    public void run() {
        WebSocketMrg.getInstance().connect();
    }


    public void reconnect() {
        WebSocketMrg.HANDLER.postDelayed(this, reconnectNum == 0 ? 0 : NEXT_RECONNECT_TIME);
        reconnectNum++;
    }


    public void isReconnect() {

    }

    /**
     * 刷新重连次数
     */
    public void reReconnectNum() {
        if (reconnectNum != 0) {
            this.reconnectNum = 0;
        }
    }
}
