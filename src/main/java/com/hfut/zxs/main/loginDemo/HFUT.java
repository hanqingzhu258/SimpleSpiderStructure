/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:HFUT.java
 * Date:18-8-8 上午8:59
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.main.loginDemo;

import com.hfut.zxs.entity.general.BaiduOCRHost;
import com.hfut.zxs.pageLoading.PageDown;
import com.hfut.zxs.pageLoading.captchaValidate.image.ImageCaptchaIndependent;
import com.hfut.zxs.pageLoading.captchaValidate.image.ImageCaptchaRecognize;
import com.hfut.zxs.pageLoading.emulateLogin.EmulateLogin;
import org.apache.http.client.methods.HttpGet;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/5 22:08 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class HFUT {



    public static void main(String[] args) {

        //进行验证码校验
        //验证码id
        String captchaId="";
        //验证码识别值
        String captchaValue="";
        //验证码获取地址
        String captchaGetURL="http://my.hfut.edu.cn/captchaGenerate.portal";
        //验证码图片本地存储地址
        String imageURL="E:\\javalearn\\picture\\captcha\\hfut.jpg";
        try {
            captchaId=captchaValue= ImageCaptchaRecognize.captchaRecognize(captchaGetURL,imageURL, BaiduOCRHost.ACCURATE_BASIC);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("验证码识别失败");
        }

        System.out.println("captchaId:"+captchaId+";cpatchaValue:"+captchaValue);

        //验证码校验地址
        String captchaValidateURL="http://my.hfut.edu.cn/captchaValidate.portal?captcha="+captchaId+"&what=captcha&value="+captchaValue+"&_=";
        //验证码校验
        Boolean validateSuccess=ImageCaptchaIndependent.captchaValidate(captchaValidateURL);
        if (!validateSuccess){
            throw new RuntimeException("验证码校验失败");
        }

        //进行模拟登陆
        //登陆请求url
        String loginURL = "http://my.hfut.edu.cn/userPasswordValidate.portal";
        //建立登陆数据
        Map<String,String> loginInfor = new HashMap<>();

        //系统信息（必填）
        loginInfor.put("loginURL",loginURL);
        //提交方式，form或者json
        loginInfor.put("submitOption","form");

        //用户信息（选填）
        loginInfor.put("Login.Token1","xxxxxxxxxxxxxxx");
        loginInfor.put("Login.Token2","xxxxxxxxxxxxxxx");
        loginInfor.put("captchaField",captchaValue);
        loginInfor.put("goto","http://my.hfut.edu.cn/loginSuccess.portal");
        loginInfor.put("gotoOnFail","http://my.hfut.edu.cn/loginFailure.portal");

        try {
            //模拟登陆
            Map<String,Object> stateInfor = EmulateLogin.login(loginInfor);
            if ((Boolean) stateInfor.get("success")){
                //登录成功
                //获取所需页面
                String aimUrl="http://my.hfut.edu.cn/index.portal";
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
