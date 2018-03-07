package com.yifan.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.yifan.wx.enums.MessageType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理微信用户发送的消息请求类
 * @author wuyifan
 * @since 2018年03月04日
 */
public class MessageUtils {

    private static Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    public static String handleWeChatMessage(HttpServletRequest request) {

        String result = "success";

        Map<String, String> map = xmlToMap(request);
        if (CollectionUtils.isEmpty(map)) {
            return result;
        }

        String messageType = map.get("MsgType");
        // 发送方帐号（一个OpenID）
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");

        MessageType messageEnumType = MessageType.valueOf(MessageType.class, messageType.toUpperCase());

        switch (messageEnumType){
            case TEXT: result = handelTextMessage(map, fromUserName, toUserName); break;
            case LINK: result = handelLinkMessage(map, fromUserName, toUserName); break;
            case VIDEO: result = handelVideoMessage(map, fromUserName, toUserName); break;
            case EVENT: result = handelEventMessage(map, fromUserName, toUserName); break;
            case IMAGE: result = handelImageMessage(map, fromUserName, toUserName); break;
            case VOICE: result = handelVoiceMessage(map, fromUserName, toUserName); break;
            case LOCATION: result = handelLocationMessage(map, fromUserName, toUserName); break;
            case SHORT_VIDEO: result = handelShortVideoMessage(map, fromUserName, toUserName); break;
            default: return result;
        }

        logger.info("返回的消息：{}", result);

        return result;
    }

    /**
     * 生成消息创建时间 （整型）
     * @return 消息创建时间
     */
    private static String getMessageCreateTime() {
        Date dt = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
        String nowTime = df.format(dt);
        long dd = (long) 0;
        try {
            dd = df.parse(nowTime).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(dd);
    }

    /**
     *
     * @param map map
     * @param fromUserName fromUserName
     * @param toUserName toUserName
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月7日
     */
    private static String handelShortVideoMessage(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("收到的小视频信息：{}", JSONObject.toJSONString(map));
        return "";
    }

    private static String handelLocationMessage(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("收到的定位信息：{}", JSONObject.toJSONString(map));
        return "";
    }

    private static String handelVoiceMessage(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("收到的视频信息：{}", JSONObject.toJSONString(map));
        return "";
    }

    /**
     * <xml>
     *     <ToUserName><![CDATA[toUser]]></ToUserName>
     *     <FromUserName>< ![CDATA[fromUser] ]></FromUserName>
     *     <CreateTime>1348831860</CreateTime>
     *     <MsgType>< ![CDATA[image] ]></MsgType>
     *     <MediaId>< ![CDATA[media_id] ]></MediaId>
     * </xml>
     * @param map map
     * @param fromUserName fromUserName
     * @param toUserName toUserName
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月7日
     */
    private static String handelImageMessage(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("收到的图片信息：{}", JSONObject.toJSONString(map));
        String imageTemplate = "<xml>" +
                                    "<ToUserName><![CDATA[%s]]></ToUserName> " +
                                    "<FromUserName>< ![CDATA[%s] ]></FromUserName> " +
                                    "<CreateTime>%s</CreateTime> " +
                                    "<MsgType><![CDATA[image]]></MsgType> " +
                                    "<MediaId><![CDATA[%s]]></MediaId> " +
                                " </xml>";

        return String.format(imageTemplate, fromUserName,
                toUserName, getMessageCreateTime(), map.getOrDefault("MediaId", ""));
    }

    private static String handelEventMessage(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("收到的事件消息：{}", JSONObject.toJSONString(map));
        return "";
    }

    private static String handelVideoMessage(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("收到的视频信息：{}", JSONObject.toJSONString(map));
        return "";
    }

    private static String handelLinkMessage(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("收到的连接信息：{}", JSONObject.toJSONString(map));
        return "";
    }

    /**
     * <xml>
     *     <ToUserName>< ![CDATA[toUser] ]></ToUserName>
     *     <FromUserName>< ![CDATA[fromUser] ] </FromUserName>
     *     <CreateTime>1348831860</CreateTime>
     *     <MsgType>< ![CDATA[text] ]></MsgType>
     *     <Content>< ![CDATA[this is a test] ]></Content>
     * </xml>
     * @param map map
     * @param fromUserName fromUserName
     * @param toUserName toUserName
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月7日
     */
    private static String handelTextMessage(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("收到的文本信息：{}", JSONObject.toJSONString(map));
        String textTemplate = "<xml>" +
                                    "<ToUserName><![CDATA[%s]]></ToUserName>" +
                                    "<FromUserName><![CDATA[%s]]></FromUserName>" +
                                    "<CreateTime>%s</CreateTime>" +
                                    "<MsgType><![CDATA[text]]></MsgType>" +
                                    "<Content><![CDATA[%s]]></Content>" +
                                "</xml>";
        return String.format(textTemplate, fromUserName,
                toUserName, getMessageCreateTime(), map.getOrDefault("Content", "不能解析改内容"));
    }


    /**
     * 微信返回的消息是一个xml格式，将xml格式转换为map
     * @param request request
     * @return java.util.Map
     * @author wuyifan
     * @since 2018年3月6日
     */
    private static Map<String, String> xmlToMap(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream ins = null;

        try {
            ins = request.getInputStream();
            Document doc = reader.read(ins);
            Element root = doc.getRootElement();
            @SuppressWarnings("unchecked")
            List<Element> list = root.elements();
            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
        } catch (IOException | DocumentException e1) {
            e1.printStackTrace();
        } finally {
            try {
                assert ins != null;
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return map;

    }

}
