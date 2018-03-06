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

    public static void getCode() {
        String url = String.format(Constant.CODE_URL, Constant.APP_ID, urlEncodeUTF8(Constant.REDIRECT_URI));

        HttpUtil.get(url);
    }

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

    public static String getUserInfo(String access_token, String openId) {

        String url = String.format(Constant.USER_INFO_URL, access_token, openId);

        return HttpUtil.get(url);
    }

    private static String urlEncodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
