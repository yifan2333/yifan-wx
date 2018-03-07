package com.yifan.wx.enums;

/**
 * 微信开发常量类定义
 * @author wuyifan
 * @since 2018年03月03日
 */
public interface Constant {

    String APP_ID = "wx2f4137a0d32d4dba";
    String APP_SECRET = "945cd11d9451951b4495613e9b1338e3";

    String HOST = "http://yifan-5.vicp.cc";

//    String APP_ID = "9038b4b0cb304d18bf80c0ae3167a505";
//    String APP_SECRET = "9038b4b0cb304d18bf80c0ae3167a505";

    String TOKEN = "wuyifantest";

    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";

    String USER_INFO_URI = HOST + "/wxapi/userInfoByCode";

    String OPEN_ID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    String USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=";

    String CHECK_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s";
}
