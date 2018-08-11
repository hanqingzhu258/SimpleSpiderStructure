/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:BaiduOCR.java
 * Date:18-8-5 下午9:59
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.tools.baiduOCR;
import com.hfut.zxs.tools.Validate;
import com.hfut.zxs.tools.baiduOCR.utils.Base64Util;
import com.hfut.zxs.tools.baiduOCR.utils.FileUtil;
import com.hfut.zxs.tools.baiduOCR.utils.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/5 19:57 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class BaiduOCR {

    public static String recognizeText(String picURL,String OCRHost){
        // 高精度识别url
        String otherHost = OCRHost;
        //通用识别url
        /*String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";*/
        // 本地图片路径
        String filePath = picURL;
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(otherHost, accessToken, params);
            System.out.println(result);

            JSONObject jsonObject=new JSONObject(result);
            JSONArray array=jsonObject.getJSONArray("words_result");
            //识别结果
            String words="";
            if (!Validate.validateNull(array)){
                words=array.getJSONObject(0).getString("words");
            }
            String textContent=words.replaceAll(" ","");

            System.out.println("test:::"+textContent);
            return textContent;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
