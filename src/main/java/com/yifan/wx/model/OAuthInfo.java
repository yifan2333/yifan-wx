package com.yifan.wx.model;

import java.io.Serializable;

/**
 * 通过code获取的用户信息
 * @author wuyifan
 * @since 2018年03月05日
 */
public class OAuthInfo implements Serializable {
    private static final long serialVersionUID = 7284144753048939835L;

    private String access_token;

    private String expires_in;

    private String refresh_token;

    /**
     * 公众号会为每一个用户生成一个openId
     */
    private String openid;

    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
