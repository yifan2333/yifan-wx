package com.yifan.wx.controller;

import com.yifan.wx.model.OAuthInfo;
import com.yifan.wx.util.MessageUtils;
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

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public String check(HttpServletRequest request) {

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

    @RequestMapping(value = "receiveMessage", method = RequestMethod.GET)
    public String receiveMessage(HttpServletRequest request) {
        return MessageUtils.handleWeChatMessage(request);
    }

    @RequestMapping(value = "code")
    public void getCode(HttpServletRequest request) {
        WxHttpUtils.getCode();
    }

    @RequestMapping(value = "userInfo")
    public void userInfo (HttpServletRequest request) {
        String code = request.getParameter("code");

        if (StringUtils.isBlank(code)) {
            return;
        }

        OAuthInfo oAuthInfo = WxHttpUtils.getOAuthInfo(code);

        if (oAuthInfo == null) {
            return;
        }

        logger.info(WxHttpUtils.getUserInfo(oAuthInfo.getAccess_token(), oAuthInfo.getOpenid()));
    }
}
