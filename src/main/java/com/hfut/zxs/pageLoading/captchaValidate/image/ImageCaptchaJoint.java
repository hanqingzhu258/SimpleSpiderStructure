/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:ImageCaptchaJoint.java
 * Date:18-8-6 上午9:51
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.pageLoading.captchaValidate.image;

import com.hfut.zxs.pageLoading.PageDown;
import com.hfut.zxs.parser.HtmlParser;
import com.hfut.zxs.topDesign.FatherSpider;
import org.apache.http.client.methods.HttpGet;

import java.util.Map;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/6 9:23 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class ImageCaptchaJoint extends FatherSpider{

    /**   
         *针对验证码与页面内容同时生成的，验证码获取
         * @param loginPageURL：登录页面（含验证码）的URL
         * @Date 2018/8/6 9:48 
         * @author zhuhanqing
         * @since JDK 1.8
    */
    public static Map<String,String> getDoubanCaptcha(String loginPageURL){

        Map<String,String> captcha;

        HttpGet httpGet=new HttpGet(loginPageURL);

        String response=PageDown.downPage(httpGet);

        captcha = HtmlParser.doubanCaptchaParse(response);

        return captcha;
    }


}
