package com.yifan.wx.controller;

import com.yifan.wx.enums.Constant;
import com.yifan.wx.util.WxHttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuyifan
 * @since 2018年03月07日
 */
@Controller
public class WxRedirectionController {
    @RequestMapping("OAuth")
    public String toOAuth(ModelMap map) {
        map.put("codeUrl", String.format(Constant.CODE_URL, Constant.APP_ID, WxHttpUtils.getInstance().urlEncodeUTF8(Constant.USER_INFO_URI)));
        return "test";
    }
}
