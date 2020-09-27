package com.yl.soft.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringUtils extends org.springframework.util.StringUtils {

	/**
	 * 移除List<String>中的重复项
	 *
	 * @param listStr
	 * @return
	 */
	public static List<String> removeRepeatList(List<String> listStr) {
		List<String> listStrNew = new ArrayList<String>();
		if (!StringUtils.isEmpty(listStr)) {
			for (String str : listStr) {
				if (!listStrNew.contains(str)) {
					listStrNew.add(str);
				}
			}
		}
		return listStrNew;
	}

	/**
	 * 去掉字符串包含的所有空格
	 *
	 * @param value
	 * @return
	 */
	public static String string2AllTrim(String value) {
		if (StringUtils.isEmpty(value)) {
			return "";
		}
		return value.trim().replace(" ", "");
	}

	/**
	 * 首字母转换大写
	 *
	 * @param str
	 * @return
	 */
	public static String str2upperFirst(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		StringBuffer conert = new StringBuffer(str.substring(0, 1).toUpperCase());
		conert.append(str.substring(1));
		return conert.toString();
	}

    /**
     * 字符串首字母变为小写
     *
     * @param s
     * @return
     */
    public static String replaceFirstToLower(String s) {
        return s.replaceFirst("\\S{1}", s.substring(0, 1).toLowerCase());
    }


	/**
	 * 获得文件后缀
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileNameSuffix(String fileName) {
		if (StringUtils.isEmpty(fileName) || fileName.indexOf(".") < 1) {
			return "";
		}
		String suffix = fileName;
		suffix = suffix.substring(suffix.lastIndexOf("."));
		return suffix;
	}

	/**
	 * 获得文件前缀
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileNamePrefix(String fileName) {
		if (StringUtils.isEmpty(fileName) || fileName.indexOf(".") < 1) {
			return "";
		}
		String prefix = fileName;
		prefix = prefix.substring(0, prefix.lastIndexOf("."));
		return prefix;
	}

    /**
     * 判断对象是否为空
     * @param strData
     * @return
     */
    public static boolean isEmpty(Object strData) {
		if (strData == null || String.valueOf(strData).trim().equals("") || String.valueOf(strData).trim().equals("undefined") || String.valueOf(strData).trim().equals("null")) {
			return true;
		}
		return false;
	}

	public static boolean isMapNull(Map map) {
		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isListNull(List list) {
		if (list == null || list.isEmpty() || list.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 创建文件夹
	 *
	 * @param path
	 * @throws Exception
	 */
	public static void crateFileDy(String path) throws Exception {
		File f = new File(path);
		if (!f.exists())
			f.mkdirs();
	}

	/**
	 * 判断字符串是否数字的字符形式。是返回true，否则false。
	 *
	 * @param s
	 *            传入的参数
	 * @return true or false
	 *
	 */
	public static boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/** 判断是不是汉字（接口使用添加）如果是返回的就是true */
	public static boolean isChinese(String str) {

		char[] chars = str.toCharArray();
		boolean isGB2312 = false;
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				int[] ints = new int[2];
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;
				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
					isGB2312 = true;
					break;
				}
			}
		}
		return isGB2312;
	}

	/**
	 * 判断字符串是否是图片路径
	 */
	public static boolean validImage(String fileDir){
		boolean b=false;
		String suffix[]={".jpg",".jpeg",".png",".bmp",".gif"};
		for (String suf : suffix) {
			if(fileDir.endsWith(suf)){
				b=true;
			}
		}
		return b;
	}

	/**
	 * 检查字符串是否在ASCII范围之内
	 * @param registerCiphertext
	 * @return
	 */
	public static boolean checkASCII(String registerCiphertext){
		boolean flag = true;
		char[] c = registerCiphertext.toCharArray();
		for(int i= 0;i<c.length;i++){
			if(c[i]<0){
				flag=false;
				break;
			}else if(c[i]>127){
				flag=false;
				break;
			}
		}
		return flag;
	}

    /**
     * 对象数组转化为字符串数组
     * @param objectArr
     * @return
     */
	public static String[] objectArr2StringArr(Object[] objectArr){
        String stringArr[] = new String[objectArr.length];
        for(int i=0;i<objectArr.length;i++){
            stringArr[i]=objectArr[i].toString();
        }
        return stringArr;
	}
}