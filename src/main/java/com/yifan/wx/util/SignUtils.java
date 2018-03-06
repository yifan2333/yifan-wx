package com.yifan.wx.util;


import com.yifan.wx.enums.Constant;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求校验工具类
 * @author wuyifan
 * @since 2018年03月03日
 */
public class SignUtils {

    private static final char[] DIGIT = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 验证消息
     * @param signature signature
     * @param timestamp timestamp
     * @param nonce nonce
     * @return boolean
     * @author wuyifan
     * @since 2018年3月3日
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {

        if (StringUtils.isBlank(signature)
                || StringUtils.isBlank(timestamp)
                || StringUtils.isBlank(nonce)) {
            return false;
        }

        String[] arr = new String[] {Constant.TOKEN, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest md;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param byteArray byteArray
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月3日
     */
    private static String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (byte aByteArray : byteArray) {
            strDigest.append(byteToHexStr(aByteArray));
        }
        return strDigest.toString();
    }

    /**
     * 将字节转换为十六进制字符串
     * @param mByte mByte
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月3日
     */
    private static String byteToHexStr(byte mByte) {

        char[] tempArr = new char[2];
        tempArr[0] = DIGIT[(mByte >>> 4) & 0X0F];
        tempArr[1] = DIGIT[mByte & 0X0F];

        return new String(tempArr);
    }
}