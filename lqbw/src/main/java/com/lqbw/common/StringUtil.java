package com.lqbw.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

public class StringUtil {

    /**
     * 是否为NULL或""或空格
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * MM-dd转MM月dd日
     *
     * @return
     */
    public static String toCN_MM_DD(String date) {
        date = date.replace("-", "月");
        date += "日";
        return date;
    }

    /**
     * 生成随机字符串
     *
     * @param length 字符长度
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * UTF8转码
     *
     * @param s
     * @return
     */
    public static String getUtf8Url(String s) {
        try {
            s = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * GB2312转码
     *
     * @param s
     * @return
     */
    public static String getGb2312Url(String s) {
        try {
            s = URLEncoder.encode(s, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
