/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:ImageCaptchaIndependent.java
 * Date:18-8-6 上午9:51
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.pageLoading.captchaValidate.image;

import com.hfut.zxs.topDesign.FatherSpider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;

/**
 * ProjectName: Spiders <br/>
 * Function: 处理需要独立验证的验证码
 * Reason:
 * date: 2018/8/5 16:36 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class ImageCaptchaIndependent extends FatherSpider {

    /**
         * 验证识别验证码是否成功
         * @param validateURL：验证码校验地址
         * @Date 2018/8/5 22:35
         * @author zhuhanqing
         * @since JDK 1.8
    */
    public static boolean captchaValidate(String validateURL){

        HttpGet httpGet=new HttpGet(validateURL);
        ResponseHandler<String> responseHandler=new BasicResponseHandler();
        try {
            String result = httpClient.execute(httpGet,responseHandler);
            System.out.println(result);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
