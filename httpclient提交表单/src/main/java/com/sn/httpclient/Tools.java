package com.sn.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * date:2017/7/10
 * author:易宸锋(dell)
 * function:
 */

public class Tools {

    //字节流转成字符流
    public static String getTextFromStream(InputStream inputStream) {

        byte[] b = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len =  inputStream.read(b))!= -1){
                bos.write(b,0,len);
            }
            //把流中的数据转成字节数组的形式,然后用字节数组构造一个字符串
            byte[] bytes = bos.toByteArray();
            bos.close();
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
