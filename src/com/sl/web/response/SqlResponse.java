package com.sl.web.response;

import com.sl.web.dao.BaseDao;

/**
 * 描述数据库操作的结果
 */
public class SqlResponse {
    /**
     * 返回操作影响数据条数
     */
    private int count;
    /**
     * 描述执行结果
     */
    private int code;
    /**
     * 描述执行结果
     */
    private String msg;
    public SqlResponse(){
        this(-1,0,"");
    }
    public SqlResponse(int count,int code,String msg){
        setCount(count);
        setCode(code);
        setMsg(msg);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg.trim();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean isSuccess(){
        return getState(ResponseCode.SUCCESS);
    }

    public Boolean isFailure(){
        return getState(ResponseCode.FAILURE);
    }

    public Boolean isNotFound(){
        return getState(ResponseCode.NOTFOUND);
    }

    public Boolean isAlwaysExists(){
        return getState(ResponseCode.ALWAYSEXISTS);
    }

    public Boolean isSqlError(){
        return getState(ResponseCode.SQLERROR);
    }

    public Boolean isSqlNoConnected(){
        return getState(ResponseCode.SQLNOCONNECTED);
    }

    public Boolean isUnKnownError(){
        return getState(ResponseCode.UNKNOWNERROR);
    }

    private Boolean getState(int state){
        return (code&state)==state;
    }

    /**
     * 快速生成SqlResponse
     * @param result sql语句执行完毕dao层返回的操作数据的行数
     * @param type 操作类型:当result为0时，显示什么类型的错误
     *             1-> 查询、更新操作，if result==0 NotFoundError
     *             2-> 插入操作,if result==0 AlwaysExistsError
     * @return
     */
    public static SqlResponse buildResponse(int result,int type){
        int code = 0;
        String msg = "";
        switch (result){
            case BaseDao.SQLERROR:
                code|=ResponseCode.SQLERROR;
                msg = "数据库出错";
                break;
            case BaseDao.SQLNOCONNECTED:
                code|=ResponseCode.SQLNOCONNECTED;
                msg = "数据库未连接";
                break;
            case 0:
                if (type == 1){
                    code|=ResponseCode.NOTFOUND;
                    msg = "未发现";
                }else {
                    code |= ResponseCode.ALWAYSEXISTS;
                    msg = "数据已存在";
                }
                break;
            default:break;
        }
        if (result>=0){
            code|=ResponseCode.SUCCESS;
            msg = "执行完毕 " +msg;
        }else {
            code|=ResponseCode.FAILURE;
            msg = "执行出错 " +msg;
        }
        return new SqlResponse(result,code,msg);
    }
}