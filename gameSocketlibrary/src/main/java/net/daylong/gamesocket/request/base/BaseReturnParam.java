package net.daylong.gamesocket.request.base;

public class BaseReturnParam<T> {

    private int errorCode; //错误码
    private String errorDesc; //错误描述
    private String serverTime; //服务器时间
    private long serverDate; //服务器时间戳
    private T serverMsg; //返回内容

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public long getServerDate() {
        return serverDate;
    }

    public void setServerDate(long serverDate) {
        this.serverDate = serverDate;
    }

    public T getServerMsg() {
        return serverMsg;
    }

    public void setServerMsg(T serverMsg) {
        this.serverMsg = serverMsg;
    }
}
