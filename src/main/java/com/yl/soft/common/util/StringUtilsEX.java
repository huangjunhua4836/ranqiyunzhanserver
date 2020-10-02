package com.yl.soft.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilsEX extends StringUtils {

    /**
     * 字符串转Integer
     *
     * @param source
     * @return 值或-1
     */
    public static Integer ToInt(String source) {
        if (source == null) {
            return -1;
        }
        String proxy = source.trim();
        Integer target = -1;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Integer.valueOf(proxy);

            }
        } catch (Exception e) {
            target = -1;
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 字符串转Integer
     *
     * @param source
     * @return 值或-1
     */
    public static Integer toIntZero(String source) {
        if (source == null) {
            return 0;
        }
        String proxy = source.trim();
        Integer target = 0;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Integer.valueOf(proxy);

            }
        } catch (Exception e) {
            target = 0;
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 字符串转Integer
     *
     * @param source
     * @return 值或null
     */
    public static Integer ToIntNull(String source) {
        if (source == null) {
            return null;
        }
        String proxy = source.trim();
        Integer target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Integer.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成日期+时间格式
     *
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date ToDate(String source) {

        SimpleDateFormat dataformat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String proxy = source;
        Date date = null;
        if (proxy != null && isNotBlank(proxy)) {
            try {
                date = dataformat.parse(proxy);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 字符串转换成日期+时间格式
     *
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date ToDateNull(String source) throws ParseException {

        SimpleDateFormat dataformat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String proxy = source;
        Date date = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                date = dataformat.parse(proxy);
            }
        } catch (Exception ex) {

        }
        return date;
    }

    public static Date ToDate(String source, String fmt) {

        SimpleDateFormat dataformat = new SimpleDateFormat(
                fmt);
        String proxy = source;
        Date date = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                date = dataformat.parse(proxy);
            }
        } catch (Exception ex) {

        }
        return date;
    }

    /**
     * 字符串转换成日期格式
     *
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date ToShortDate(String source) throws ParseException {
        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
        String proxy = source;
        Date date = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                date = dataformat.parse(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换成Long
     *
     * @param source
     * @return 返回不能为空
     */
    public static Long ToLong(String source) {
        String proxy = source;
        Long target = -1l;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Long.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 日期转换成字符串格式
     *
     * @param date 日期
     * @param str  格式
     */
    public static String dateToString(Date date, String str) {

        SimpleDateFormat dataformat = new SimpleDateFormat(str);
        String proxy = "";
        try {
            if (date != null) {
                proxy = dataformat.format(date);
            }
        } catch (Exception ex) {

        }
        return proxy;
    }

    /**
     * 日期转换成字符串格式
     *
     * @param date 日期
     */
    public static String dateToString(Date date) {

        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String proxy = "";
        try {
            if (date != null) {
                proxy = dataformat.format(date);
            }
        } catch (Exception ex) {

        }
        return proxy;
    }

    /**
     * 日期转换成Long
     *
     * @param source
     * @return 返回可能为空
     */
    public static Long ToLongNull(String source) {
        String proxy = source;
        Long target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Long.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成double
     *
     * @param source
     * @return 返回值不能为空
     */
    public static Double ToDouble(String source) {
        String proxy = source;
        Double target = -1.0;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    public static Double toDoubleZore(String source) {
        String proxy = source;
        Double target = 0d;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成double
     *
     * @param source
     * @return 返回值可能为空
     */
    public static Double ToDoubleNull(String source) {
        String proxy = source;
        Double target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成ToDecimal（用于金额）
     *
     * @param source
     * @return 返回值不能为空
     */
    public static BigDecimal ToDecimal(String source) {
        BigDecimal df = new BigDecimal("0.00");
        String proxy = source;
        Double target = -1.00;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        df = new BigDecimal(target);
        df = df.setScale(2, BigDecimal.ROUND_HALF_UP);
        return df;
    }

    /**
     * 字符串转换成ToDecimal（用于金额）
     *
     * @param source
     * @return 返回值可能为空
     */
    public static BigDecimal ToDecimalNull(String source) {
        BigDecimal df = new BigDecimal("0.00");
        String proxy = source;
        Double target = 0.00;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        df = new BigDecimal(target);
        df = df.setScale(2, BigDecimal.ROUND_HALF_UP);
        return df;
    }

    /**
     * 字符串转换成ToBoolean
     *
     * @param source
     * @return 返回值不能为空
     */
    public static Boolean ToBoolean(String source) {
        String proxy = source;
        Boolean target = false;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Boolean.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 0 ，1 转换成功boolean
     *
     * @param source
     * @return
     */
    public static Boolean toBooleanForOS(String source) {
        String proxy = source;
        Boolean target = false;
        if (StringUtils.isNotBlank(proxy)) {
            if ("0".equals(proxy)) {
                target = false;
            } else if ("1".equals(proxy)) {
                target = true;
            }
        }
        return target;
    }

    /**
     * 字符串转换成ToBoolean
     *
     * @param source
     * @return 返回值能为空
     */
    public static Boolean ToBooleanNull(String source) {
        String proxy = source;
        Boolean target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Boolean.valueOf(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 字符串转换成float
     *
     * @param source
     * @return 返回值不能为空
     */
    public static Float ToFloat(String source) {
        if (source == null) {
            return -1.0f;
        }
        String proxy = source;
        Float target = -1.0f;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Float.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成float
     *
     * @param source
     * @return 返回值可能为空
     */
    public static Float ToFloatNull(String source) {
        String proxy = source;
        Float target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Float.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 判断字符串是否Null或空字符串
     *
     * @param source
     * @return 返回值为True 是否Null或空字符串 否则返回false
     */
    public static boolean IsNullOrWhiteSpace(String source) {
        if (source == null || ("").equals(source)) {
            return true;
        }
        return false;
    }




    public static boolean isDate(String dateString) {
        boolean isret = false;
        try {
            SimpleDateFormat dataformat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            dataformat.parse(dateString);
            isret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isret;
    }

    public static boolean isShortDate(String dateString) {
        boolean isret = false;
        try {
            SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
            dataformat.parse(dateString);
            isret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isret;
    }

    public static boolean isTime(String dateString) {
        boolean isret = false;
        try {
            SimpleDateFormat dataformat = new SimpleDateFormat("HH:mm:ss");
            dataformat.parse(dateString);
            isret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isret;
    }

    /**
     * 检验用户名是否合法
     *
     * @param username
     * @return
     */
    public static Boolean checkUserName(String username) {
        String regx = "^[a-zA-Z][a-zA-Z0-9_]{2,16}$";
        Pattern p = Pattern.compile(regx);
        return p.matcher(username).matches();
    }

    /**
     * 时间转换 string
     *
     * @param date
     * @param format
     * @return
     * @author kh.wang
     * @since 2016年6月7日
     */
    public static String parseDateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (date != null) {
            return dateFormat.format(date);
        } else {
            return null;
        }
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static Integer getLength(String s) {
        Integer valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 2;
            } else {
                // 其他字符长度为0.5
                valueLength += 1;
            }
        }
        // 进位取整
        return valueLength;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String s = "您的验证码是188";
        System.out.println(getLength(s));
    }

    /**
     * 检验参数是否合法
     *
     * @param type  参数类型（0：手机号，1：身份证）
     * @param param 参数
     */
    public static Boolean checkParam(Integer type, String param) {
        if (IsNullOrWhiteSpace(param)) {
            return false;
        }
        String mobile = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
        String card18 = "^[1-9]\\d{5}(19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        String card15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
        switch (type) {
            case 0:
                Pattern mPattern = Pattern.compile(mobile);
                return mPattern.matcher(param).matches();
            case 1:
                Pattern card18Pattern = Pattern.compile(card18);
                Pattern card15Pattern = Pattern.compile(card15);
                return card18Pattern.matcher(param).matches()
                        || card15Pattern.matcher(param).matches();
        }
        return false;
    }

    /**
     * 将id字符串转换成Integer集合
     *
     * @param param id字符串 格式："1,2,3,4"
     */
    public static List<Integer> ToList(String param) {
        List<Integer> list = new ArrayList<>();
        if (IsNullOrWhiteSpace(param)) {
            return list;
        }
        String[] ids = param.split(",");
        for (String id : ids) {
            if (!StringUtilsEX.IsNullOrWhiteSpace(id)) {
                list.add(StringUtilsEX.ToInt(id));
            }
        }
        return list;
    }

    /**
     * 隐藏用户信息 对字符串处理:将字符以星号代替
     *
     * @param content 传入的字符串
     * @param type    1：姓名，2：联系电话，3：身份证号，4：详细地址
     */
    public static String hideUserInfo(Integer type, String content) {
        if (StringUtilsEX.IsNullOrWhiteSpace(content)) {
            return content;
        }
        char[] strs = content.toCharArray();
        if (strs.length > 0) {
            for (int i = 0; i < strs.length; i++) {
                switch (type) {
                    case 1:
                        if (i != 0) {
                            strs[i] = '*';
                        }
                        break;
                    case 2:
                        if (i > 2 && i < 7) {
                            strs[i] = '*';
                        }
                        break;
                    case 3:
                        if (i > 5 && i < 14) {
                            strs[i] = '*';
                        }
                        break;
                    case 4:
                        strs[i] = '*';
                        break;
                }
            }
        }
        return String.valueOf(strs);
    }

    /**
     * 秒数转时分秒
     *
     * @param miao 秒数
     */
    public static String miaoToTime(Long miao) {
        String DateTimes = null;
        long days = miao / (60 * 60 * 24);
        long hours = (miao % (60 * 60 * 24)) / (60 * 60);
        long minutes = (miao % (60 * 60)) / 60;
        long seconds = miao % 60;
        if (days > 0) {
            DateTimes = days + "天" + hours + "小时" + minutes + "分钟" + seconds
                    + "秒";
        } else if (hours > 0) {
            DateTimes = hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (minutes > 0) {
            DateTimes = minutes + "分钟" + seconds + "秒";
        } else {
            DateTimes = seconds + "秒";
        }
        return DateTimes;
    }

    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean isMatch = false;
        // 制定验证条件，目前有些新卡会出现类似166开头的，需要适当调整
        //String regex1 = "^[1][3,4,5,7,8][0-9]{9}$";
        String regex2 = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$";

        p = Pattern.compile(regex2);
        m = p.matcher(str);
        isMatch = m.matches();
        return isMatch;
    }

}
