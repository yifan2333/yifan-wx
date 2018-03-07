package com.yifan.wx.schedule;

import com.yifan.wx.util.CacheUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时获取accessToken
 * @author wuyifan
 * @since 2018年03月07日
 */
@Configuration
@EnableScheduling
public class AccessTokenSchedule {

    /**
     * 定时获取accessToken
     * @author wuyifan
     * @since 2018年3月7日
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void getAccessToken() {
        String accessToken = CacheUtils.getInstance().getAccessTokenHttp();
        CacheUtils.getInstance().setAccessToken(accessToken);
    }
}
