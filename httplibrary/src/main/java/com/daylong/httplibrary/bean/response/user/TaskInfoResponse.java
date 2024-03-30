package com.daylong.httplibrary.bean.response.user;

public class TaskInfoResponse {

    private Integer taskId; //任务ID
    private String taskImgUrl; //任务图片
    private String taskDesc; //任务描述
    private Integer completeNum; //已完成数量
    private Integer totalNum; //总数
    private Integer completeFlag; //是否已完成
    private Integer receiveFlag;//是否已领取

    public boolean isCompleteFlag() {
        return completeFlag == 1;
    }

    public boolean isReceiveFlag() {
        return receiveFlag == 1;
    }

    //已完成
    public boolean isComplete() {
        return isCompleteFlag() && isReceiveFlag();
    }

    public boolean isReceive() {
        return isCompleteFlag() && !isReceiveFlag();
    }

    public String getPdStr() {
        return completeNum + "/" + totalNum;
    }

    public int getDP() {
        return completeNum / totalNum * 100;

    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskImgUrl() {
        return taskImgUrl;
    }

    public void setTaskImgUrl(String taskImgUrl) {
        this.taskImgUrl = taskImgUrl;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(Integer completeFlag) {
        this.completeFlag = completeFlag;
    }

    public Integer getReceiveFlag() {
        return receiveFlag;
    }

    public void setReceiveFlag(Integer receiveFlag) {
        this.receiveFlag = receiveFlag;
    }

    public String getBtnText() {
        if (isComplete()) {
            return "已完成";
        } else {
            if (isCompleteFlag()) {
                return "领取";

            } else {
                return "去完成";
            }
        }
    }
}
