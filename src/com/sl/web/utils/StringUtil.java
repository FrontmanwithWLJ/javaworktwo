package com.sl.web.utils;

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
}
