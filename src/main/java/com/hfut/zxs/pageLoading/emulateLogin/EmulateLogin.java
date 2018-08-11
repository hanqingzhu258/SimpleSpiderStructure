/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:EmulateLogin.java
 * Date:18-8-2 下午8:59
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.pageLoading.emulateLogin;

import com.alibaba.fastjson.JSONObject;
import com.hfut.zxs.tools.Validate;
import com.hfut.zxs.topDesign.FatherSpider;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: Spiders <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018/8/2 20:59 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class EmulateLogin extends FatherSpider{

    /**   
         * 
         * @Date 2018/8/2 21:13
         * @author zhuhanqing
         * @param loginInfor{"loginURL":"","submitOption":"","xxx":"",……},其中submitOption=="form" or "json"
         * 通过遍历map确定请求头中的各项参数及其值
         * @return true:模拟成功 or false:模拟失败
         * @since JDK 1.8
         * @condition  进行模拟登陆，获取某些需要登录才能获取到的信息
    */
    public static Map<String,Object> login(Map<String,String> loginInfor) throws Exception {

        //确定登录请求的url
        String loginURL = loginInfor.get("loginURL");
        //验证
        if (Validate.validateNull(loginURL)){
            throw new Exception("请求地址为空！！！");
        }
        //建立请求
        HttpPost httpPost = new HttpPost(loginURL);

        //map去除不必要参数即上述参数
        loginInfor.remove("loginURL");

        //遍历map,封装请求信息
        StringEntity entity=packageLoginInfor(loginInfor);
        //设置请求信息
        httpPost.setEntity(entity);

        //代理发送请求，并返回状态信息
        Map<String,Object> stateInfor = new HashMap<>();
        //responseBody
        HttpResponse response=null;
        try {
            response=httpClient.execute(httpPost);
            stateInfor.put("response",response);
            stateInfor.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            stateInfor.put("response",response);
            stateInfor.put("success",false);
        }finally {
            httpPost.abort();
        }
        return stateInfor;
    }

    /**
     * 封装登录数据
     * @param loginInfor{"submitOption":"'json' or 'form'","paramValue":"paramName",……}
     * @Date 2018/8/5 10:09
     * @author zhuhanqing
     * @since JDK 1.8
     */
    public static StringEntity packageLoginInfor(Map<String,String> loginInfor) throws Exception{
        StringEntity entity;
        //确认数据提交格式，json或者form
        String submitOption = loginInfor.get("submitOption");
        //从map中移除该数据
        loginInfor.remove("submitOption");
        //校验提交方式是否为空
        if (Validate.validateNull(submitOption)){
            throw new Exception(submitOption+"不能为空");
        }

        if (submitOption.equals("form")){
            //表单方式提交
            entity=formSubmit(loginInfor);
            /*System.out.println("form----------------------------------------------------");*/
        }else if (submitOption.equals("json")){
            //json方式提交
            entity=jsonSubmit(loginInfor);
        }else{
            throw new Exception("submitOption:"+submitOption+"参数异常");
        }

        return entity;
    }

    /**   
         * 封装json格式数据，传输表单内容
         * @param loginInfor{"paramName":"paramValue",……}
         * @Date 2018/8/5 9:52 
         * @author zhuhanqing
         * @since JDK 1.8
    */
    public static StringEntity jsonSubmit(Map<String,String> loginInfor) throws Exception{
        JSONObject jsonParam = new JSONObject();
        for (String inforName:loginInfor.keySet()){
            //验证
            if (Validate.validateNull(inforName)){
                throw new Exception(inforName+"不能为空！！！");
            }
            String inforValue = loginInfor.get(inforName);
            jsonParam.put(inforName,inforValue);
        }
        //解决中文乱码
        StringEntity entity=new StringEntity(jsonParam.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        return entity;
    }

    /**   
         * 表单方式提交数据
         * @param loginInfor{"paramValue":"paramName",……}
         * @Date 2018/8/5 9:56 
         * @author zhuhanqing
         * @since JDK 1.8
    */
    public static StringEntity formSubmit(Map<String,String> loginInfor) throws Exception{
        List<NameValuePair> nvps = new ArrayList<>();
        for (String inforName:loginInfor.keySet()){
            //验证
            if (Validate.validateNull(inforName)){
                throw new Exception(inforName+"不能为空！！！");
            }
            String inforValue = loginInfor.get(inforName);
            nvps.add(new BasicNameValuePair(inforName,inforValue));
        }
        StringEntity entity=new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
        return entity;
    }


}
