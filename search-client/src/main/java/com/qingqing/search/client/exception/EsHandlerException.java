package com.qingqing.search.client.exception;

import org.omg.SendingContext.RunTime;

/**
 * Created by yaoqijun on 2016/11/14.
 * es handler 转换异常
 */
public class EsHandlerException extends RuntimeException{

    public EsHandlerException(String msg){
        super(msg);
    }
}
