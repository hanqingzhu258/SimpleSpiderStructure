/*
 * Copyright(c) 2018 Hanqing Zhu. All rights reserved
 * ProjectName:Spiders
 * FileName:LoggerTest.java
 * Date:18-8-11 上午9:39
 * Author:Hanqing Zhu
 */

package com.hfut.zxs.main.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProjectName: Spiders <br/>
 * Function:
 * Reason:
 * date: 2018/8/11 9:39 <br/>
 *
 * @author Zhu Hanqing
 * @since JDK 1.8
 */
public class LoggerTest {
    public static Logger logger= LoggerFactory.getLogger(LoggerTest.class);
    public static void main(String[] args) {

        logger.info("ssssssssssssssss");

    }

}
