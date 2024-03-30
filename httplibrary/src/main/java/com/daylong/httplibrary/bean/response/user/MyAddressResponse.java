package com.daylong.httplibrary.bean.response.user;

import android.text.TextUtils;

import java.io.Serializable;

public class MyAddressResponse implements Serializable {

    private int dealOperateType; //见数据操作方式数据字典
    private Integer addressId; //地址ID 编辑、删除必传

    private String name;//收货人姓名
    private String phone; //收货电话
    private String area;//收货区域
    private String province; //省份
    private String city; //城市
    private String county; //县区
    private Integer defaultFlag; //是否默认

    public boolean isDefault() {
        return defaultFlag != null && defaultFlag == 1;
    }

    public MyAddressResponse() {
    }

    public MyAddressResponse(int dealOperateType, Integer addressId) {
        this.dealOperateType = dealOperateType;
        this.addressId = addressId;
    }

    public int getDealOperateType() {
        return dealOperateType;
    }

    public void setDealOperateType(int dealOperateType) {
        this.dealOperateType = dealOperateType;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getAddress() {

        if (TextUtils.isEmpty(getProvince())) {
            return null;
        }

        return getProvince() + "-" + getCity() + "-" + getCounty();

    }

    public String getAddress2() {

        if (TextUtils.isEmpty(getProvince())) {
            return null;
        }

        return getProvince() + " " + getCity() + " " + getCounty();

    }


    public enum DataOperateType {
        ADD(1, "添加"), EDIT(2, "编辑"), DELETE(3, "删除");
        private int id;
        private String desc;

        DataOperateType(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }


    }

    public String getInfo() {

        String stringBuilder = "联系人:" + getName() +
                "\n" +
                "联系电话:" + getPhone() +
                "\n" +
                "地址:" + getAddress2() + getArea()
               ;


        return stringBuilder.trim();
    }
}
