package com.QA.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Administrator on 2017/7/17.
 */
public class MD5Util {
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString() ;
    }

    public static void main(String[] args) {
        String md5 = md5("abc123","crossoverjie") ;
        System.out.println(md5);
    }
}
