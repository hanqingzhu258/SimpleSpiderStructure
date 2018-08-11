/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:FatherSpider.java
 * Date:$today.year-$today.month-2 9:10:37
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.topDesign;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * ProjectName: Spiders <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018/8/2 21:10 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class FatherSpider {
    //共享的请求代理对象
    public static HttpClient httpClient=new DefaultHttpClient();
}
