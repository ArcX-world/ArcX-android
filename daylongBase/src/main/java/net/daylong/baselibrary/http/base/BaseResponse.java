package net.daylong.baselibrary.http.base;
public class BaseResponse<T> {

    private static final String CODE_SUCCESS = "1";//成功的code
    private int errorCode;
    private String errorDesc;
    private T serverMsg;
    private long serverDate;



    public long getTime() {
        return serverDate;
    }

    public void setTime(long time) {
        this.serverDate = time;
    }


    public int getCode() {
        return errorCode;
    }

    public void setCode(int code) {
        this.errorCode = code;
    }

    public String getMsg() {
        return errorDesc;
    }

    public void setMsg(String msg) {
        this.errorDesc = msg;
    }

    public T getData() {
        return serverMsg;
    }

    public void setData(T data) {
        this.serverMsg = data;
    }

    /**
     * 是否请求数据成功
     *
     * @return
     */
    public boolean isSuccess() {
        return CODE_SUCCESS.equals(String.valueOf(errorCode));
    }


}
