/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:ImageCaptchaRecognize.java
 * Date:18-8-6 上午9:51
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.pageLoading.captchaValidate.image;

import com.hfut.zxs.tools.baiduOCR.BaiduOCR;
import com.hfut.zxs.topDesign.FatherSpider;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/6 9:39 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class ImageCaptchaRecognize extends FatherSpider{

    /**
     *利用OCR工具识别验证码
     * @param captchaGetURL:验证码获取地址
     * @param imageURL：图片保存至本地位置
     * @Date 2018/8/5 22:34
     * @author zhuhanqing
     * @since JDK 1.8
     */
    public static String captchaRecognize(String captchaGetURL, String imageURL,String OCRHost) throws Exception {

        /*HttpClient client=new DefaultHttpClient();*/

        //获取验证码图片流
        HttpGet httpGet = new HttpGet(captchaGetURL);

        System.out.println("captchaGetURL:"+captchaGetURL);

        HttpResponse httpResponse;
        String captcha_result = "";

        httpResponse = httpClient.execute(httpGet);
        //验证码获取成功
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = httpResponse.getEntity();
            byte[] data = EntityUtils.toByteArray(httpEntity);
            //转成图片并保存到本地
            Boolean saveImageSuccess = ImageDown.saveImage(data, imageURL);
            if (!saveImageSuccess) {
                throw new Exception("图片保存失败");
            }

            //利用OCR工具进行验证码识别
            captcha_result = BaiduOCR.recognizeText(imageURL,OCRHost);
        }

        /*httpGet.abort();*/

        return captcha_result;

    }

}
