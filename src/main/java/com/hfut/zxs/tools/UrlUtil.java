/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:UrlUtil.java
 * Date:18-8-8 下午5:19
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.tools;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/8 17:18 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class UrlUtil {

    /**
         * 将字符串转成特定形式的编码格式
         * @param str:待编码的字符串
         * @param ENCODE:指定的编码格式，即目标编码格式
         * @Date 2018/8/8 17:28
         * @author zhuhanqing
         * @since JDK 1.8
    */
    public static String getURLEncoderString(String str,String ENCODE) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将字符串转成特定形式的编码格式
     * @param str:待解码的字符串
     * @param ENCODE:指定的编码格式，即目标编码格式
     * @Date 2018/8/8 17:28
     * @author zhuhanqing
     * @since JDK 1.8
     */
    public static String getURLDecoderString(String str,String ENCODE) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    /*public static void main(String[] args) throws Exception{

        String test="我只爱学习";
        String gbk=getURLEncoderString(test, CodeType.UTF8);
        System.out.println(gbk);
        String gbkToC = java.net.URLDecoder.decode(gbk,CodeType.UTF8);
        System.out.println(gbkToC);
        *//*System.out.println(getURLEncoderString(test, CodeType.UTF8));*//*
        System.out.println(getURLDecoderString(test,CodeType.GBK));


    }*/

}
