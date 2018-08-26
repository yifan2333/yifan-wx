package com.yifan.wx.future;

import java.util.Random;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author: wuyifan
 * @date: 2018年05月25日 16:54
 */
public class Shop {

    private Random random = new Random();

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        Delay.delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
