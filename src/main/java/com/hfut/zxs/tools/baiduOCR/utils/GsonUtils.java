/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:GsonUtils.java
 * Date:18-8-5 下午9:59
 * Author:Hanqing Zhu
 */
package com.hfut.zxs.tools.baiduOCR.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Json工具类.
 */
public class GsonUtils {
    private static Gson gson = new GsonBuilder().create();

    public static String toJson(Object value) {
        return gson.toJson(value);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonParseException {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonParseException {
        return (T) gson.fromJson(json, typeOfT);
    }
}
