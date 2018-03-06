package com.yifan.wx.enums;

/**
 * @author wuyifan
 * @since 2018年03月03日
 */
public enum HttpStatus {
    OK(200, "成功"),
    FAIL(210, "失败"),
    STATUS_ERROR(211, "状态异常"),
    NO_TOKEN(310, "未登录"),
    ERROR(410, "异常"),
    INTERNAL_ERROR(500, "内部错误"),
    PAY_FAIL(2222, "支付跳转"),
    OP_INSTALL(215, "可选安装不在服务范围");

    private int code;
    private String desc;

    HttpStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
