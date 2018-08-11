/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:HtmlParser.java
 * Date:18-8-6 上午9:28
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.parser;

import com.hfut.zxs.entity.general.BaiduOCRHost;
import com.hfut.zxs.pageLoading.captchaValidate.image.ImageCaptchaRecognize;
import com.hfut.zxs.tools.Validate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName: Spiders <br/>
 * Function: 进行验证码id和url的解析，每个网站应对应一个特定的方法，方法中的Jsoup的选择器是不同的
 * Reason:
 * date: 2018/8/6 9:28 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class HtmlParser {

    /**   
         *针对豆瓣验证码的解析
         * @param pageContent:豆瓣登录页面的html字符串
         * @Date 2018/8/6 9:47 
         * @author zhuhanqing
         * @since JDK 1.8
    */
    public static Map<String,String> doubanCaptchaParse(String pageContent){

        //将String类型的页面转换成dom树
        Document document= Jsoup.parse(pageContent);

        //需要解析的数据
        //获取验证码下载地址
        String captchaURL=document.select("#captcha_image").attr("src");

        System.out.println("captchaURL:"+captchaURL);

        //对相应的验证码进行下载并识别
        //验证码本地保存位置
        String localURL = "E:\\javalearn\\picture\\captcha\\douban.jpg";
        String captchaValue="";
        try {
            if (!Validate.validateNull(captchaURL)){
                captchaValue= ImageCaptchaRecognize.captchaRecognize(captchaURL,localURL, BaiduOCRHost.GENERAL_BASIC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取验证码id
        String captchaId=document.select("[name=captcha-id]").val();

        //以map形式返回验证码信息
        Map<String,String> captcha=new HashMap<>();
        captcha.put("captchaId",captchaId);
        captcha.put("captchaValue",captchaValue);
        return captcha;
    }

}
