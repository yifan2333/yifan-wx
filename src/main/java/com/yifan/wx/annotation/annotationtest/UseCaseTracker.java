package com.yifan.wx.annotation.annotationtest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yifan
 * @since 2018年05月07日
 */
public class UseCaseTracker {

    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method method : cl.getDeclaredMethods()) {
            UseCase useCase = method.getAnnotation(UseCase.class);
            if (useCase != null) {
                System.out.println("Found Use Cases: " + useCase.id() + " " + useCase.description());
                useCases.remove(useCase.id());
            }
        }
        for (int i : useCases) {
            System.out.println("Warning : Missing use Case-" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }

}
