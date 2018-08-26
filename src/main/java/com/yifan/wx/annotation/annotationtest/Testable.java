package com.yifan.wx.annotation.annotationtest;

/**
 * @author yifan
 * @since 2018年05月07日
 */
public class Testable {
    @Test
    void testExecute() {
        execute();
    }

    private void execute() {
        System.out.println("Executing...");
    }
}
