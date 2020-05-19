package com.sl.web.response;

public interface ResponseCode {
    /**操作成功*/
    int SUCCESS = 2;
    /**操作失败*/
    int FAILURE = 128;
    /**数据库未连接*/
    int SQLNOCONNECTED = 4;
    /**未找到数据*/
    int NOTFOUND = 8;
    /**数据始终存在*/
    int ALWAYSEXISTS = 16;
    /**数据库错误*/
    int SQLERROR = 32;
    /**未知错误*/
    int UNKNOWNERROR = 64;
}
