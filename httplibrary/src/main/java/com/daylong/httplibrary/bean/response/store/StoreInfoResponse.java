package com.daylong.httplibrary.bean.response.store;

import android.text.TextUtils;
import android.view.View;

import com.daylong.httplibrary.bean.request.UsdTPayRequest;

import java.io.Serializable;
import java.util.List;

public class StoreInfoResponse {


    private SpPlyIfoDTO spPlyIfo; //超级玩家
    private String selCnMch; //售币机
    private List<CnTblnDTO> cnTbln; //游戏币列表
    private PpyIfoDTO ppyIfo; //道具信息


    public boolean isShowSpPay() {
        return spPlyIfo != null&&!TextUtils.isEmpty(spPlyIfo.getPct());
    }


    public SpPlyIfoDTO getSpPly() {
        return spPlyIfo;
    }

    public void setSpPly(SpPlyIfoDTO spPly) {
        this.spPlyIfo = spPly;
    }

    public String getSelCnMch() {
        return selCnMch;
    }

    public void setSelCnMch(String selCnMch) {
        this.selCnMch = selCnMch;
    }

    public List<CnTblnDTO> getCnTbln() {
        return cnTbln;
    }

    public void setCnTbln(List<CnTblnDTO> cnTbln) {
        this.cnTbln = cnTbln;
    }

    public PpyIfoDTO getPpyIfo() {
        return ppyIfo;
    }

    public void setPpyIfo(PpyIfoDTO ppyIfo) {
        this.ppyIfo = ppyIfo;
    }

    public static class SpPlyIfoDTO implements Serializable {

        private String pct;
        private long usdtAmt;

        public String getPct() {
            return pct;
        }

        public void setPct(String pct) {
            this.pct = pct;
        }

        public long getUsdtAmt() {
            return usdtAmt;
        }

        public void setUsdtAmt(long usdtAmt) {
            this.usdtAmt = usdtAmt;
        }

        public UsdTPayRequest create() {
            return new UsdTPayRequest(1, null,usdtAmt, 0);

        }
    }

    public static class PpyIfoDTO implements Serializable {
        private Integer rfTm; //下次刷新时间
        private Integer rfAxcAmt; //刷新AXC价格
        private List<PpyTblnDTO> ppyTbln; //道具列表

        public Integer getRfTm() {
            return rfTm;
        }

        public void setRfTm(Integer rfTm) {
            this.rfTm = rfTm;
        }

        public Integer getRfAxcAmt() {
            return rfAxcAmt;
        }

        public void setRfAxcAmt(Integer rfAxcAmt) {
            this.rfAxcAmt = rfAxcAmt;
        }

        public List<PpyTblnDTO> getPpyTbln() {
            return ppyTbln;
        }

        public void setPpyTbln(List<PpyTblnDTO> ppyTbln) {
            this.ppyTbln = ppyTbln;
        }

        public static class PpyTblnDTO implements Serializable {
            private Integer cmdId; //商品ID
            private Long ppyAmt; //道具数量
            private String nm; //道具名称
            private String pct; //图片
            private String dsc; //描述
            private Integer soFlg; //描述
            private Long axcAmt; //AXC价格

            public boolean isSoFlg() {
                return soFlg == 1;
            }

            public void setPpyAmt(Long ppyAmt) {
                this.ppyAmt = ppyAmt;
            }

            public String getDsc() {
                return dsc;
            }

            public void setDsc(String dsc) {
                this.dsc = dsc;
            }

            public Integer getSoFlg() {
                return soFlg;
            }

            public void setSoFlg(Integer soFlg) {
                this.soFlg = soFlg;
            }

            public boolean isShowNum() {
                return ppyAmt > 1;
            }

            public Integer getCmdId() {
                return cmdId;
            }

            public void setCmdId(Integer cmdId) {
                this.cmdId = cmdId;
            }

            public Long getPpyAmt() {
                return ppyAmt;
            }

            public void setPpyAmt(long ppyAmt) {
                this.ppyAmt = ppyAmt;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public String getPct() {
                return pct;
            }

            public void setPct(String pct) {
                this.pct = pct;
            }

            public Long getAxcAmt() {
                return axcAmt;
            }

            public void setAxcAmt(Long axcAmt) {
                this.axcAmt = axcAmt;
            }


            public UsdTPayRequest create() {
                return new UsdTPayRequest(3, cmdId, getAxcAmt(), getPpyAmt());
            }
        }
    }

    public static class CnTblnDTO implements Serializable {
        private Integer cmdId; //商品ID
        private Long cnAmt; //游戏币数量
        private Integer usdtAmt; //USDT价格
        private String pct; //图片


        public String getUsdtAmtStr() {
            return "$" + getUsdtAmt();
        }

        public Integer getCmdId() {
            return cmdId;
        }

        public void setCmdId(Integer cmdId) {
            this.cmdId = cmdId;
        }

        public Long getCnAmt() {
            return cnAmt;
        }

        public void setCnAmt(Long cnAmt) {
            this.cnAmt = cnAmt;
        }

        public long getUsdtAmt() {
            return usdtAmt;
        }

        public void setUsdtAmt(Integer usdtAmt) {
            this.usdtAmt = usdtAmt;
        }

        public String getPct() {
            return pct;
        }

        public void setPct(String pct) {
            this.pct = pct;
        }

        public UsdTPayRequest create() {
            return new UsdTPayRequest(2, cmdId, getUsdtAmt(), getCnAmt());
        }
    }
}
