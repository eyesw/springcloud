package com.aaa.springboot_test_20200619.until;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码加密工具类
 */
public class EnctypeUtil {
    private static String alfrithmName="MD5";
    private static int hashIteratrions=1024;
    public static String encPassword(String oldPassword,Object salt){
        SimpleHash hash=new SimpleHash(alfrithmName,oldPassword,salt,hashIteratrions);
        String str = hash.toBase64();
        return str;
    }

    public static void main(String[] args) {
        String zhang= EnctypeUtil.encPassword("123456", "xuhaoran");
        System.out.println(zhang);
    }

}
