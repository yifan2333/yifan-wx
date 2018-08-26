package com.yifan.wx.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author: wuyifan
 * @date: 2018年05月25日 16:59
 */
public class ShopAsync {
    private String name;

    public ShopAsync(String name) {
        this.name = name;
    }

    private Random random = new Random();

    public Future<Double> getPriceAsnyc(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceSupplyAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        Delay.delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
