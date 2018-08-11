/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:PageDown.java
 * Date:$today.year-$today.month-2 10:7:36
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.pageLoading;

import com.hfut.zxs.topDesign.FatherSpider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;

/**
 * ProjectName: Spiders <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018/8/2 22:07 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class PageDown extends FatherSpider{

    /**   
         * 
         * @Date 2018/8/2 22:15
         * @author zhuhanqing
         * @param request: httpGet=new HttpGet(redirectURL) or httpPost=new HttpPost(redirectURL)
         * @return String类型的页面
         * @since JDK 1.8
         * @condition  下载所需页面
    */
    public static String downPage(HttpUriRequest request){
        //Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody ;
        try{
            responseBody = httpClient.execute(request,responseHandler);
        }catch (Exception e){
            e.printStackTrace();
            responseBody=null;
        }finally {
            request.abort();
            /*httpClient.getConnectionManager().shutdown();*/
        }
        return responseBody;
    }
}
