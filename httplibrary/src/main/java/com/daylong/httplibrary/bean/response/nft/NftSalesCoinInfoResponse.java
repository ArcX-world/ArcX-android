package com.daylong.httplibrary.bean.response.nft;

import net.daylong.baselibrary.utils.StringUtils;

public class NftSalesCoinInfoResponse {

    private String nftCd; //NFT编号
    private Long usdtExc; //USDT兑换值
    private double usdtBl; //玩家USDT余额
    private long maxUsdt; //可购买的上限USDT
    private long maxVl;//上限储币量

    public String getUsdtToCoin() {
        return "1USDT=" + StringUtils.numFormatDot(usdtExc) + " coins";
    }


    public int getUserMax() {
        if (usdtBl > maxUsdt) {
            return (int) usdtBl;
        } else {

            if (usdtBl < maxUsdt) {
                return (int) usdtBl;
            }
            return (int) maxUsdt;
        }
    }

    public int getUserBalanceInt() {
        return (int) usdtBl;
    }


    public String getNftCd() {
        return nftCd;
    }

    public void setNftCd(String nftCd) {
        this.nftCd = nftCd;
    }

    public Long getUsdtExc() {
        return usdtExc;
    }

    public void setUsdtExc(Long usdtExc) {
        this.usdtExc = usdtExc;
    }

    public long getEditCoin(int num) {

        return usdtExc * num;
    }

    public Double getUsdtBl() {
        return usdtBl;
    }

    public void setUsdtBl(Double usdtBl) {
        this.usdtBl = usdtBl;
    }

    public long getMaxUsdt() {
        return maxUsdt;
    }

    public void setMaxUsdt(long maxUsdt) {
        this.maxUsdt = maxUsdt;
    }

    public long getMaxVl() {
        return maxVl;
    }

    public void setMaxVl(long maxVl) {
        this.maxVl = maxVl;
    }
}
