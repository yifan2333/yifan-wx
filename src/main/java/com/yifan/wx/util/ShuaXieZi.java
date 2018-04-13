package com.yifan.wx.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yifan
 * @since 2018年04月07日
 */
public class ShuaXieZi {



    public static void main(String[] args) {
        int count = 1;

        // 封装请求参数
        Map<String, Object> map = new HashMap<>();
        map.put("asynchStr", Arrays.asList(	442544,430020,440353,447072,431628));
        map.put("bargainTime", 0);
        map.put("browseHistory", Arrays.asList(434780,437335));
        map.put("flg", 1);
        map.put("page_button", 0);
        map.put("postID", 457181);
        map.put("product_mainID", "ABAM089");
        map.put("sizeStr", Arrays.asList(457195,457182,457183,457184,457185,
                457187,457199,457181,457196,457197,457198,457193,457194,457200,457201,
                457186,457188,457202,457204,457189,457190,457191,457203,457192));

        while (true){

            // 请求商品信息
            String jsonStr = HttpUtil.post("https://store.lining.com/ajax/goods_details.html", map);
            // 解析商品信息， 判断商品是否有43码黑色的，有则给夏哥发邮件，没有就等10秒后再次请求
            if (!StringUtils.isBlank(jsonStr)) {
                JSONObject object = JSONObject.parseObject(jsonStr);

                if (Integer.valueOf(200).equals(object.getInteger("code"))) {
                    JSONObject object1 = object.getJSONObject("data");

                    String listStr = object1.getString("goodsData");

                    List<GoodsModel> list = JSONArray.parseArray(listStr, GoodsModel.class);

                    list = list.stream().filter(GoodsModel::is43).collect(Collectors.toList());

                    System.out.println("请求了" + count++ + "次");

                    if (list != null && list.size() == 1 && list.get(0).isSaled()){
                        // 发送邮件
                        // email.send("1252178193@qq.com", null, null, "43的鞋子有了", "43的鞋子有了", null);
                        // 跳出循环，结束线程
                        break;
                    } else {
                        System.out.println("43码的鞋还是没有啊");
                    }
                }
            }

            try {
                // 线程停止十分钟
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @author yifan
     * @since 2018年04月07日
     */
    public static class GoodsModel {


        private String useFlg;
        private Integer enableSaleAmount;
        private Integer goodsOnSale;
        private String forSale;
        private String goodsID;
        private String postID;
        private String product_code;
        private Integer selectOnSale;
        private String spec;

        public String getUseFlg() {
            return useFlg;
        }

        public void setUseFlg(String useFlg) {
            this.useFlg = useFlg;
        }

        public Integer getEnableSaleAmount() {
            return enableSaleAmount;
        }

        public void setEnableSaleAmount(Integer enableSaleAmount) {
            this.enableSaleAmount = enableSaleAmount;
        }

        public Integer getGoodsOnSale() {
            return goodsOnSale;
        }

        public void setGoodsOnSale(Integer goodsOnSale) {
            this.goodsOnSale = goodsOnSale;
        }

        public String getForSale() {
            return forSale;
        }

        public void setForSale(String forSale) {
            this.forSale = forSale;
        }

        public String getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(String goodsID) {
            this.goodsID = goodsID;
        }

        public String getPostID() {
            return postID;
        }

        public void setPostID(String postID) {
            this.postID = postID;
        }

        public String getProduct_code() {
            return product_code;
        }

        public void setProduct_code(String product_code) {
            this.product_code = product_code;
        }

        public Integer getSelectOnSale() {
            return selectOnSale;
        }

        public void setSelectOnSale(Integer selectOnSale) {
            this.selectOnSale = selectOnSale;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public boolean is43() {
            return this.postID.equals("457199") || this.spec.equals("标准黑/金 43");
        }

        public boolean isSaled() {
            return this.useFlg.equals("1") && this.forSale.equals("1") && this.goodsOnSale.equals(1);
        }


    }


}
