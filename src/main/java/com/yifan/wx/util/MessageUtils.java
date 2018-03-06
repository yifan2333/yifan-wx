package com.yifan.wx.util;

import com.yifan.wx.enums.MessageType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
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
 * @author wuyifan
 * @since 2018年03月04日
 */
public class MessageUtils {

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
            case TEXT: handelTextMessage(map); break;
            case LINK: handelLinkMessage(map); break;
            case VIDEO: handelVideoMessage(map); break;
            case EVENT: handelEventMessage(map); break;
            case IMAGE: handelImageMessage(map); break;
            case VOICE: handelVoiceMessage(map); break;
            case LOCATION: handelLocationMessage(map); break;
            case SHORT_VIDEO: handelShortVideoMessage(map); break;
            default: return result;
        }

        result = String
                .format(
                        "<xml>" +
                                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                                "<CreateTime>%s</CreateTime>" +
                                "<MsgType><![CDATA[text]]></MsgType>" +
                                "<Content><![CDATA[%s]]></Content>" +
                                "</xml>",
                        fromUserName, toUserName, getMessageCreateTime(),
                        "已收到消息");

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

    private static void handelShortVideoMessage(Map<String, String> map) {
    }

    private static void handelLocationMessage(Map<String, String> map) {
    }

    private static void handelVoiceMessage(Map<String, String> map) {
    }

    private static void handelImageMessage(Map<String, String> map) {
    }

    private static void handelEventMessage(Map<String, String> map) {
    }

    private static void handelVideoMessage(Map<String, String> map) {
    }

    private static void handelLinkMessage(Map<String, String> map) {
    }


    private static void handelTextMessage(Map<String, String> map) {
    }


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
