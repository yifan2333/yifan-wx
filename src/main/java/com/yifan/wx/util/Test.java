package com.yifan.wx.util;

import cn.hutool.core.io.IoUtil;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yifan
 * @since 2018年05月09日
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("D:\\数据源\\ecej城市运营.txt"));
        List<String> orderList = IoUtil.readLines(inputStream, "UTF-8", new ArrayList<>());
        System.out.println(orderList.size());
        System.out.println();
        String sql = orderList.stream()
                .distinct()
                .map(String::trim)
                .map(NumberUtils::toInt)
                .map(str -> "UPDATE team_member SET org_role_id = 13 WHERE member_id = "
                        + str
                        + " and is_deleted = 0;\n")
                .reduce("", (a, b) -> a + b);
        OutputStream outputStream = new FileOutputStream(new File("D:\\sql\\ecej城市运营.sql"));
        IoUtil.write(outputStream, true, sql.getBytes());
    }
}
