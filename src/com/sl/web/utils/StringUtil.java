package com.sl.web.utils;

import java.nio.charset.StandardCharsets;

public class StringUtil {
    public static Boolean isNullOrEmpty(String s){
        return s == null || s.isEmpty();
    }
    public static Boolean isNullOrEmpty(String ... args){
        for (String str : args){
            if (isNullOrEmpty(str))return true;
        }
        return false;
    }

    public static String toUTF_8(String s){
        return new String(s.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
    }
    public static String toISO_8859_1(String s){
        return new String(s.getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
    }
}
