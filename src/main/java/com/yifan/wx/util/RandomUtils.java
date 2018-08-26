package com.yifan.wx.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <p>Title: RandomUtils </p>
 * <p>Description: </p>
 * @author: wuyifan
 * @date: 2018年05月15日 16:40
 */
public class RandomUtils {
    private static Random random = new Random();

    /**
     * @Title
     * @Description 从list中取得count个元素
     * @param list list
     * @param count count 取得元素的数量
     * @param isRepetitive isRepetitive 是否允许重复
     * @return java.util.List
     * @author wuyifan
     * @date 2018年5月15日 16:55
     */
    public static <T> List<T> randomElements (List<T> list, int count, boolean isRepetitive) {
        int limit;
        final List<T> result = new ArrayList<>(count);
        if (list == null || (limit = list.size()) < count) {
            return result;
        }
        if (isRepetitive) {
            while (result.size() < count) {
                result.add(list.get(randomInt(limit)));
            }
        } else {
            List<T> copyList = new ArrayList<>(list);
            while (result.size() < count) {
                result.add(copyList.remove(randomInt(limit--)));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(JSONObject.toJSONString(randomElements(list, 10, false)));
        System.out.println(JSONObject.toJSONString(list));
        System.out.println(randomInt(1));
        System.out.println(randomInt(10, 15));
    }


    /**
     * @Title
     * @Description 生成 [0. limit)之间的随机数
     * @param limit limit
     * @return int
     * @author wuyifan
     * @date 2018年5月15日 17:02
     */
    public static int randomInt(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must be positive");
        }
        return random.nextInt(limit);
    }

    /**
     * @Title
     * @Description 生成 [min, max) 之间的随机数
     * @param min min
     * @param max max
     * @return int
     * @author wuyifan
     * @date 2018年5月15日 17:01
     */
    public static int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must less than max");
        }
        return randomInt(max - min) + min;
    }
}
