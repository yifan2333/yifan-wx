package com.yifan.wx.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.yifan.wx.enums.Constant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的缓存工具类
 * @author wuyifan
 * @since 2018年03月07日
 */
public class CacheUtils {

    private Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    private static CacheUtils ourInstance = new CacheUtils();

    private final Map<String, Object> MAP = new HashMap<>();

    public static CacheUtils getInstance() {
        return ourInstance;
    }

    private CacheUtils() {
    }

    public void setAccessToken (String accessToken) {
        logger.info("更新accessToken为 {}", accessToken);
        MAP.put("accessToken", accessToken);
    }

    public String getAccessToken () {

        String accessToken = MAP.getOrDefault("accessToken", "").toString();

        if (StringUtils.isBlank(accessToken)) {
            accessToken = getAccessTokenHttp();
            setAccessToken(accessToken);
        }

        return accessToken;
    }

    /**
     * 获取access_token
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月6日
     */
    public String getAccessTokenHttp () {

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
}
