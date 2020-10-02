package com.yl.soft.common.util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ProductNumUtil {

    static ConcurrentMap<String, ConcurrentMap<Integer, Integer>> concurrentMapMater = new ConcurrentHashMap<String,
            ConcurrentMap<Integer, Integer>>();


    private static int GetRand(int n) {
        int max = (int) Math.pow(10, n);
        int min = n == 1 ? 0 : (int) Math.pow(10, n - 1);
        return new Random().nextInt(max) % (max - min + 1) + min;

    }

    public static void main(String[] strngs) {
        System.out.println(getShopNum());
    }

    /**
     * 获取商品编号 yy+日期(+毫秒)+6位随机数
     *
     * @return
     */
    public static String getSkuNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYMMddSSS");
        Random rd = new Random();
        System.currentTimeMillis();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = "yy" + df.format(date) + sjs.toString();

        return randomNum;

    }

    /**
     * 生成shopNum
     *
     * @return
     */
    public static String getShopNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYMMddSSS");
        Random rd = new Random();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = df.format(date) + sjs.toString();
        return randomNum;
    }

    /**
     * 生成CouponNum
     *
     * @return
     */
    public static String getCouponNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYMMddSSS");
        Random rd = new Random();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = "cc" + df.format(date) + sjs.toString();
        return randomNum;
    }

    public static String getClassNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYMMddSSS");
        Random rd = new Random();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = "D" + df.format(date) + sjs.toString();
        return randomNum;
    }

    /**
     * 随机生成4位验证码
     *
     * @return
     */
    public static String randomValidata() {
        String[] beforeShuffle = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        System.out.print(result);
        return result;
    }

    public static String getRandNum() {
        return getRandomString(6);
    }

    public static String getRandomString(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String GetOrderNum() {
        String proNum = "";
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YY");
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        proNum = df.format(now) + cal.get(Calendar.DAY_OF_YEAR) + cal.get(Calendar.MILLISECOND);
        long rand = GetRand(15 - proNum.length());
        proNum += (rand == -1 ? "" : String.valueOf(rand));
        return proNum;
    }

    public static String getBatchNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMdd");
        Random rd = new Random();
        System.currentTimeMillis();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = df.format(date) + sjs.toString();

        return randomNum;

    }

    /**
     * 获取邀请码 手机号后四位+4位随机数
     */
    public static String getInviteCode(String phone) {
        if (phone == null || phone.length() < 11) {
            phone = "";
        } else {
            phone = phone.substring(7);
        }
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer(phone);
        for (int i = 0; i < 4; ++i) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


   

    /**
     * 创建一个在范围内的随机数
     *
     * @param minVal 最小值
     * @param maxVal 最大值
     * @return
     * @author ztd
     */
    public static Integer createRandomKey(Integer minVal, Integer maxVal) {
        Integer v = new Random().nextInt(maxVal);
        if (v <= minVal) {
            v = v + minVal;
        }
        return v;
    }

}
