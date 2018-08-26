package com.yifan.wx.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author: wuyifan
 * @date: 2018年05月25日 16:43
 */
public class CompletableFutureTest {
    private static List<ShopAsync> shops = Arrays.asList(
            new ShopAsync("BestPrice"),
            new ShopAsync("LetsSaveBig"),
            new ShopAsync("MyFavoriteShop"),
            new ShopAsync("BuyItAll"));
    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> {
                    try {
                        return String.format("%s price is %.2f", shop.getName(), shop.getPriceSupplyAsync(product).get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return "";
                    }
                }).collect(Collectors.toList());
    }

    public static void testCompletableFuture () {
        ShopAsync shopAsync = new ShopAsync("my favorite shop");
        long start = System.nanoTime();
        Future<Double> future = shopAsync.getPriceAsnyc("my favorite product");

        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        try {
            double price = future.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}
