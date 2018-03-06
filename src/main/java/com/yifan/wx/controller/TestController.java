package com.yifan.wx.controller;

import com.yifan.wx.enums.HttpStatus;
import com.yifan.wx.util.ResultUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuyifan
 * @since 2018年03月03日
 */
@RestController
public class TestController {

    @RequestMapping("hello")
    public ModelMap test () {
        return ResultUtils.makeModel(HttpStatus.OK);
    }


}
