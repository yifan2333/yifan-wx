package com.yifan.wx.util;

import java.util.Random;

/**
 * @author yifan
 * @since 2018年04月13日
 */
public class HongBaoUtils {

    /**
     * 根据金额和红包数生成红包
     * @param money 红包金额
     * @param hbNum 红包数量
     * @return
     */
    public static float[] generate(int money, int hbNum, int start, int end) {
        // 红包数组
        float[] hongbao = new float[hbNum];

        Random random = new Random();
        // 每次分配后的余额
        float blance = (float) money;
        // n为分配红包次数  随意定义 (n越大越平均，n越小差距越大)
        for (int n = 0; n < 4; n++) {
            // 每轮平均红包金额
            float avgMoney = blance / (float) hbNum;
            for (int i=0; i<hbNum; i++) {
                // 取一个start 到 1/2 start-end 的随机数
                int rate = random.nextInt((end + start) / 2 + 1) + start;
                float m = avgMoney * (float) rate / (float) hbNum;
                // 计算分配过后的钱
                float temp = hongbao[i] + m;
                // 超过end 就不给这个红包分配了
                if (temp > end) {
                    continue;
                }
                hongbao[i] = temp;
                blance = blance - m;
            }
        }
        // 余额给第一个红包 (多次循环后几乎可以忽略)
        hongbao[0] = hongbao[0] + blance;

        float b = (float) money;
        // 保证每个红包保留一位小数   中间过程不要做小数位保留
        for (int i=0; i<hongbao.length; i++) {
            hongbao[i] = Math.round(hongbao[i] * 100f) / 100f;
            b -= hongbao[i];
        }
        // 将剩余的金额按红包顺序分配， 超过end的将超过部分分配给下一个
        distributionBalance(b, hongbao, end);
        return hongbao;
    }

    private static void distributionBalance(float balance, float[] hongbao, int end) {
        for (int i = 0; i < hongbao.length; i++) {
            float temp = Math.round((hongbao[i] + balance) * 100f) / 100f;
            if (temp < (float) end) {
                hongbao[i] = temp;
                break;
            } else {
                balance = balance - ((float) end - hongbao[i]);
                hongbao[i] = (float) end;
            }
        }
    }
    public static void main(String[] args) {
        float[] result = generate(100, 10, 5, 15);
        float sum = 0;
        for (float a : result) {
            System.out.println(a);
            sum += a;
        }
        System.out.println("sum is " + sum);
    }

}
