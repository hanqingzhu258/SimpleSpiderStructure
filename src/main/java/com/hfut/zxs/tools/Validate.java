/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:Validate.java
 * Date:$today.year-$today.month-2 9:19:41
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.tools;

/**
 * ProjectName: Spiders <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018/8/2 21:19 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class Validate {

    /**
         *
         * @Date 2018/8/2 21:20
         * @author zhuhanqing
         * @param object
         * @return true:null or false:not null
         * @since JDK 1.8
         * @condition  验证对象是否为空值（null），对于字符串还要判断是否为" "
    */
    public static boolean validateNull(Object object){

        if (object == null || object.equals("")){
            return true;
        }

        return false;
    }

}
