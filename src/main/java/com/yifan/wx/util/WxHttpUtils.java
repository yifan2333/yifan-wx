package com.yifan.wx.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yifan.wx.enums.Constant;
import com.yifan.wx.model.OAuthInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuyifan
 * @since 2018年03月04日
 */
public class WxHttpUtils {

    private static WxHttpUtils ourInstance = new WxHttpUtils();

    private static Logger logger = LoggerFactory.getLogger(WxHttpUtils.class);

    public static WxHttpUtils getInstance() {
        return ourInstance;
    }

    private WxHttpUtils() {
    }

    /**
     * 获取到OAuthInfo
     * @param code code REDIRECT_URI 请求参数里的值
     * @return com.yifan.wx.model.OAuthInfo
     * @author wuyifan
     * @since 2018年3月6日
     */
    public OAuthInfo getOAuthInfo (String code) {
        String url = String.format(Constant.OPEN_ID_URL, Constant.APP_ID, Constant.APP_SECRET, code);

        String jsonStr = HttpUtil.get(url);

        logger.info("获取到OAuthInfo信息" + jsonStr);

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
    public String getUserInfo(String access_token, String openId) {

        String url = String.format(Constant.USER_INFO_URL, access_token, openId);

        return HttpUtil.get(url);
    }

    public String userList() {
        String url = String.format(Constant.USER_LIST_URL, CacheUtils.getInstance().getAccessToken());

        return HttpUtil.get(url);
    }

    /**
     * 校验accessToken是否合法
     * @param accessToken accessToken
     * @param openId openId
     * @return boolean
     * @author wuyifan
     * @since 2018年3月7日
     */
    public boolean checkAccessToken(String accessToken, String openId) {

        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId)) {
            return false;
        }

        String url = String.format(Constant.CHECK_ACCESS_TOKEN_URL, accessToken, openId);
        String result = HttpUtil.get(url);

        logger.info("check access_token result is {}", result);

        JSONObject object = JSON.parseObject(result);
        return object != null && object.containsKey("errmsg") && "ok".equals(object.get("errmsg"));
    }

    /**
     * 编码化url
     * @param url url
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月6日
     */
    public String urlEncodeUTF8(String url){
        return HttpUtil.encode(url, "UTF-8");
    }

}
