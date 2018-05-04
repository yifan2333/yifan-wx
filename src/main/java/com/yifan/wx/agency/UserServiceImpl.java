package com.yifan.wx.agency;

/**
 * @author yifan
 * @since 2018年05月02日
 */
public class UserServiceImpl implements UserService {
    @Override
    @LoggerAnnotation
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    @Override
    @LoggerAnnotation
    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}
