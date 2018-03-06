package com.yifan.wx.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.yifan.wx.enums.Constant;
import com.yifan.wx.model.OAuthInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;

/**
 * @author wuyifan
 * @since 2018年03月04日
 */
public class WxHttpUtils {

    private static Logger logger = LoggerFactory.getLogger(WxHttpUtils.class);

    /**
     * 获取access_token
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月6日
     */
    public static String getAccessToken () {

        String url = String.format(Constant.ACCESS_TOKEN_URL, Constant.APP_ID, Constant.APP_SECRET);

        String jsonStr = HttpUtil.get(url);

        logger.info(jsonStr);

        JSONObject object = JSONObject.parseObject(jsonStr);

        if (object.containsKey("access_token")) {
            return object.getString("access_token");
        } else {
            return "";
        }
    }

    /**
     * get请求获取code，请求会重定向到定义的 REDIRECT_URI 上，
     * 这个REDIRECT_URI可以是一个页面，也可以是一个controller
     * code会在请求的参数里
     * @author wuyifan
     * @since 2018年3月6日
     */
    public static void getCode() {
        String url = String.format(Constant.CODE_URL, Constant.APP_ID, urlEncodeUTF8(Constant.REDIRECT_URI));

        HttpUtil.get(url);
    }

    /**
     * 获取到OAuthInfo
     * @param code code REDIRECT_URI 请求参数里的值
     * @return com.yifan.wx.model.OAuthInfo
     * @author wuyifan
     * @since 2018年3月6日
     */
    public static OAuthInfo getOAuthInfo (String code) {
        String url = String.format(Constant.OPEN_ID_URL, Constant.APP_ID, Constant.APP_SECRET, code);

        String jsonStr = HttpUtil.get(url);

        logger.info(jsonStr);

        if (StringUtils.isNotBlank(jsonStr)) {
            return JSONObject.parseObject(jsonStr, OAuthInfo.class);
        } else {
            return null;
        }
    }

    /**
     * 通过 OAuthInfo 的 access_token 和 openId 可以获取到用户信息
     * @param access_token access_token
     * @param openId openId
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月6日
     */
    public static String getUserInfo(String access_token, String openId) {

        String url = String.format(Constant.USER_INFO_URL, access_token, openId);

        return HttpUtil.get(url);
    }

    /**
     * 编码话url
     * @param url url
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月6日
     */
    private static String urlEncodeUTF8(String url){
        String result = url;
        try {
            result = URLEncoder.encode(url,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
