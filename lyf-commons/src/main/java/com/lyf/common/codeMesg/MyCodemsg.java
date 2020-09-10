package com.lyf.common.codeMesg;

/**
 * @author lyf
 * @date 2020/6/11-17:42
 */
public enum MyCodemsg {

    VOLATILE_EXCEPTION(10400, "数据格式校验异常"),
    CODE_EXCEPTION(10002,"验证码频率太高，请稍后再试"),
    CODENULL_EXCEPTION(10004,"验证码不存在"),
    CODENOT_EXCEPTION(10005,"验证码不正确"),
    USERNAME_EXCEPTION(10006,"用户名已存在"),
    NIKENAME_EXCEPTION(10007,"昵称已存在"),
    MOBILE_EXCEPTION(10008,"手机号码已存在"),
    LOGIN_INFO_EXCEPTION(10009,"账号或密码错误"),
    SOCOAL_LOGIN_INFO_EXCEPTION(10010,"社交登陆异常"),
    ES_EXCEPTION(10011,"es异常"),
    JWT_EXCEPTION(10012,"无jwt异常"),
    JWTOUT_EXCEPTION(10013,"jwt错误或过期"),
    FOODNULL_EXCEPTION(10014,"food为空");

    private final int code;
    private final String msg;
    MyCodemsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}

