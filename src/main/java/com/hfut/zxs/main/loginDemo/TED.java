/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:TED.java
 * Date:18-8-8 上午8:59
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.main.loginDemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hfut.zxs.pageLoading.PageDown;
import com.hfut.zxs.pageLoading.emulateLogin.EmulateLogin;
import com.hfut.zxs.tools.Validate;
import org.apache.http.client.methods.HttpGet;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/8 8:58 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class TED {

    public static void main(String[] args) {

        //进行模拟登陆
        //登陆请求url
        String loginURL = "https://auth.ted.com/session.json";
        //建立登陆数据
        Map<String,String> loginInfor = new HashMap<>();

        //系统信息（必填）
        loginInfor.put("loginURL",loginURL);
        //提交方式，form或者json
        loginInfor.put("submitOption","form");

        //用户信息（选填）
        String user_email="xxxxxxxxxxxxx";
        String user_password="xxxxxxxxxxx";
        String authenticity_token;
        //获取token值
        HttpGet httpGet=new HttpGet("https://auth.ted.com/session/new.json");
        String response = PageDown.downPage(httpGet);
        System.out.println("response:"+response);
        JSONObject jsonObject= JSON.parseObject(response);
        authenticity_token=jsonObject.getString("authenticity_token");

        loginInfor.put("user[email]",user_email);
        loginInfor.put("user[password]",user_password);
        loginInfor.put("user[remember_me]","on");
        loginInfor.put("authenticity_token",authenticity_token);

        try{
            //模拟登陆
            Map<String,Object> stateInfor = EmulateLogin.login(loginInfor);

            System.out.println("ssssss:\n"+stateInfor.get("response").toString());

            if ((Boolean) stateInfor.get("success")){

                String aimURL="https://www.ted.com/dashboard/history";
                HttpGet httpGet1=new HttpGet(aimURL);
                String reply=PageDown.downPage(httpGet1);
                //解析watch history的部分数据（json格式）
                if (!Validate.validateNull(reply)){

                    String[] media=reply.split("\"history.init\",");
                    String[] json=media[1].split("\\)</script></div>");
                    JSONObject object=JSON.parseObject(json[0]);
                    System.out.println(object.get("metadata"));
                }
                System.out.println("reply:"+reply);

            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
