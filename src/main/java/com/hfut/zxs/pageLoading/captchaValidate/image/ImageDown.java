/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:ImageDown.java
 * Date:18-8-6 上午9:51
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.pageLoading.captchaValidate.image;

import java.io.File;
import java.io.FileOutputStream;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/5 21:40 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class ImageDown {

    /**   
         *根据httpresponse解析出的字节数组，保存为fileURL所示图片
         * @param data：图片的字节数组
         * @param fileURL:图片保存位置
         * @Date 2018/8/5 21:48 
         * @author zhuhanqing
         * @since JDK 1.8
    */
    public static boolean saveImage(byte[] data,String fileURL){

        //删除原先图片
        File file=new File(fileURL);
        if (file.exists()){
            file.delete();
        }

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileURL);
            fos.write(data);
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
