/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:RenRen.java
 * Date:18-8-8 上午8:59
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.main.loginDemo;

import com.hfut.zxs.pageLoading.PageDown;
import com.hfut.zxs.pageLoading.emulateLogin.EmulateLogin;
import org.apache.http.client.methods.HttpGet;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName: Spiders <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018/8/2 22:25 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class RenRen {

    public static void main(String args[]){

        //进行模拟登陆
        //登陆请求url
        String loginURL = "http://www.renren.com/PLogin.do";
        //建立登陆数据
        Map<String,String> loginInfor = new HashMap<>();

        //系统信息（必填）
        loginInfor.put("loginURL",loginURL);
        //提交方式，form或者json
        loginInfor.put("submitOption","form");

        //用户信息（选填）
        loginInfor.put("email","xxxxxxxxxxxxxx");
        loginInfor.put("password","xxxxxxxxxxx");
        loginInfor.put("domain","renren.com");
        /*loginInfor.put("origURL","http://www.renren.com/465530468/profile");*/
        loginInfor.put("icode","");
        loginInfor.put("key_id","1");
        loginInfor.put("captcha_type","web_login");
        loginInfor.put("rKey","8a339012c2e46e974617357b89ef889a");
        loginInfor.put("f","http%3A%2F%2Fwww.renren.com%2F967223436");

        try {
            //模拟登陆
            Map<String,Object> stateInfor = EmulateLogin.login(loginInfor);
            if ((Boolean) stateInfor.get("success")){
                //登录成功
                //获取所需页面
                String aimUrl="http://www.renren.com/465530468/profile";
                //建立请求
                HttpGet httpGet=new HttpGet(aimUrl);
                //下载页面
                String pageContent = PageDown.downPage(httpGet);
                System.out.println(pageContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
