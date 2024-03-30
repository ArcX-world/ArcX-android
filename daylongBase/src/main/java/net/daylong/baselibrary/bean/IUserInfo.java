package net.daylong.baselibrary.bean;


public interface IUserInfo {
    /**
     * 用户昵称
     *
     * @return
     */
    String getUserName();

    /**
     * 用户头像
     * @return
     */
    String getUserImgUrl();

    /**
     * 用户Id
     * @return
     */
    Long getUserId();
}
