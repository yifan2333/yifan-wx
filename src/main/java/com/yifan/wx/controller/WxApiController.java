package com.yifan.wx.controller;

import com.yifan.wx.model.OAuthInfo;
import com.yifan.wx.util.CacheUtils;
import com.yifan.wx.util.SignUtils;
import com.yifan.wx.util.WxHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuyifan
 * @since 2018年03月03日
 */
@RestController
@RequestMapping("wxapi")
public class WxApiController {

    private Logger logger = LoggerFactory.getLogger(WxApiController.class);

    private volatile static int times = 0;

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(HttpServletRequest request) {
        return checkTokenAndGetEchostr(request);
    }

    private String checkTokenAndGetEchostr(HttpServletRequest request) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        logger.info("checking... signature:{}; timestamp:{}; nonce:{}; echostr:{}", signature, timestamp, nonce, echostr);

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtils.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        } else {
            return "error";
        }
    }

    /**
     * OAuth授权获取用户信息
     * @param request request
     * @author wuyifan
     * @since 2018年3月7日
     */
    @RequestMapping(value = "userInfoByOAuth")
    public void userInfoByCode (HttpServletRequest request) {

        logger.info("get userInfo... the times is {} ", ++times);

        String code = request.getParameter("code");

        logger.info("code is {}", code);

        if (StringUtils.isBlank(code)) {
            return;
        }

        OAuthInfo oAuthInfo = WxHttpUtils.getInstance().getOAuthInfo(code);

        if (oAuthInfo == null) {
            return;
        }

        if (WxHttpUtils.getInstance().checkAccessToken(oAuthInfo.getAccess_token(), oAuthInfo.getOpenid())) {
            logger.info("the result of get userInfo is {}", WxHttpUtils.getInstance().getUserInfo(CacheUtils.getInstance().getAccessToken(), oAuthInfo.getOpenid()));
        }
    }

    /**
     * 获取公众号下所有的用户列表
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月7日
     */
    @RequestMapping(value = "userList")
    public String userList() {

        String result = WxHttpUtils.getInstance().userList();

        logger.info(result);

        return result;
    }

}
