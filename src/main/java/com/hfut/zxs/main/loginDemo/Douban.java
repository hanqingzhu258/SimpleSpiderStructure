/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:Douban.java
 * Date:18-8-8 上午8:59
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.main.loginDemo;

import com.hfut.zxs.pageLoading.PageDown;
import com.hfut.zxs.pageLoading.captchaValidate.image.ImageCaptchaJoint;
import com.hfut.zxs.pageLoading.emulateLogin.EmulateLogin;
import com.hfut.zxs.tools.Validate;
import com.hfut.zxs.topDesign.FatherSpider;
import org.apache.http.client.methods.HttpGet;
import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/6 9:52 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class Douban extends FatherSpider{

    public static void main(String[] args) throws Exception{

        //获取验证码
        //登录页面URL
        String loginPageURL = "https://www.douban.com/accounts/login";
        Map<String,String> captcha = ImageCaptchaJoint.getDoubanCaptcha(loginPageURL);


        //模拟登陆
        String login_src = "https://accounts.douban.com/login";
        //输入用户名及密码
        String form_email = "xxxxxxxxx";
        String form_password = "xxxxxxxxx";
        String login = "登录";
        String captcha_solution=captcha.get("captchaValue");
        String captcha_id=captcha.get("captchaId");


        //封装登录信息
        Map<String,String> loginInfor = new HashMap<>();

        //系统信息（必填）
        loginInfor.put("loginURL",login_src);
        //提交方式，form或者json
        loginInfor.put("submitOption","form");

        //用户信息
        loginInfor.put("form_email",form_email);
        loginInfor.put("form_password",form_password);
        loginInfor.put("login",login);

        //判断是否需要验证码
        if (Validate.validateNull(captcha_id)){
            loginInfor.put("source","None");
        }else{
            loginInfor.put("captcha-solution",captcha_solution);
            loginInfor.put("captcha-id",captcha_id);
        }

        //进行模拟登陆
        try{
            Map<String,Object> stateInfor = EmulateLogin.login(loginInfor);
            if ((Boolean) stateInfor.get("success")){

                System.out.println("登录成功");

                //登录成功
                //获取所需页面
                String aimUrl="https://www.douban.com/people/conanemily/contacts";
                //建立请求
                HttpGet httpGet=new HttpGet(aimUrl);
                //下载目标页面
                String aimPageContent = PageDown.downPage(httpGet);
                System.out.println(aimPageContent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
