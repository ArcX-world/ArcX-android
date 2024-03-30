package net.daylong.gamesocket.listener;

public interface WebSocketDefaultListener {


    /**
     * 连接中
     */
    void connect();

    /**
     * 重新连接
     */
    void reconnect();

    /**
     * 连接成功
     */
    void connectSuc();

    /**
     * 连接失败
     */
    void connectFail();

    /**
     * 返回错误
     *
     * @param cmd  操作指令
     * @param code 状态码
     * @param desc 返回内容
     */
    void responseError(int cmd, int code, String desc);


    /**
     * 街机返回
     *
     * @param coin
     */
    void arcadeCoinReturn(Long coin);


}
