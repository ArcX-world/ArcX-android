package net.daylong.gamesocket.listener;

import net.daylong.gamesocket.SocketLogUtil;
import net.daylong.gamesocket.runnable.HeartBeatRunnable;
import net.daylong.gamesocket.runnable.WebSocketConnectRunnable;
import net.daylong.gamesocket.strategy.response.SocketResponseStrategy;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MyWebSocketListener extends WebSocketListener {
    private static final String TAG = "WEB->Listener->";

    private boolean isConnect;
    /**
     * 心跳发送
     */
    private HeartBeatRunnable heartBeatRunnable;
    private WebSocketConnectRunnable webSocketConnectRunnable;


    public boolean isConnect() {
        return isConnect;
    }

    public MyWebSocketListener() {
        heartBeatRunnable = new HeartBeatRunnable();
        webSocketConnectRunnable = new WebSocketConnectRunnable();


    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        SocketLogUtil.e( "连接成功");
        SocketResponseStrategy.getInstance().getWebSocketDefaultStrategy().connectSuc();

        isConnect = response.code() == 101;
        if (isConnect) {
            //连接成功  开机发送心跳包
            heartBeatRunnable.refresh();
            //刷新重连次数
            webSocketConnectRunnable.reReconnectNum();
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, final String text) {
        super.onMessage(webSocket, text);
        //收到消息
        SocketResponseStrategy.getInstance().callback(text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        SocketLogUtil.e("客户端主动关闭");
        webSocket.close(1000, null);

    }
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        SocketResponseStrategy.getInstance().getWebSocketDefaultStrategy().connectFail();
        isConnect = false;
        //实现重连
        webSocketConnectRunnable.reconnect();

        SocketLogUtil.e("长连错误:"+t.getMessage());
    }
}
