package net.daylong.gamesocket.request.base;


import net.daylong.gamesocket.cache.WebSocketCache;

public class BaseParam {


    private long tsmp;//当前时间
    private int rdAmt;//随机数
    private String mcCd ;//设备Id
    private String arcxPsd;//加密串
    private String vsCd;//版本号
    private String plm = "android";//版本号

    public BaseParam() {

    }

    public long getTsmp() {
        return tsmp;
    }

    public void setTsmp(long tsmp) {
        this.tsmp = tsmp;
    }

    public int getRdQt() {
        return rdAmt;
    }

    public void setRdQt(int rdQt) {
        this.rdAmt = rdQt;
    }

    public String getDeviceId() {
        return mcCd ;
    }

    public void setDeviceId(String deviceId) {
        this.mcCd  = deviceId;
    }

    public String getTidePlayPas() {
        return arcxPsd;
    }

    public void setTidePlayPas(String tidePlayPas) {
        this.arcxPsd = tidePlayPas;
    }

    public String getVsCd() {
        return vsCd;
    }

    public void setVsCd(String vsCd) {
        this.vsCd = vsCd;
    }

    public String getPlm() {
        return plm;
    }

    public void setPlm(String plm) {
        this.plm = plm;
    }


}
