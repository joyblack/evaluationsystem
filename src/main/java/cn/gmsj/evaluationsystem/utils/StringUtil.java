package cn.gmsj.evaluationsystem.utils;

/**
 * @author Alan
 */
public class StringUtil {

    public static boolean isNotEmpty(String value) {
        if ((value != null) && (!value.equals(""))) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String value) {
        if ((value == null) || (value.equals(""))) {
            return true;
        }
        return false;
    }

    public static String toFirstLetterUpperCase(String str) {
        if (str == null || str.length() < 1) {
            return str;
        }
        if (str.length() >= 2) {
            if (Character.isUpperCase(str.charAt(1))) {
                return str;
            }
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        String firstLetter = str.substring(0, 1).toUpperCase();
        return firstLetter + str.substring(1, str.length());
    }

    public static boolean equals(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        } else {
            return false;
        }
    }


    public static String addZeroAppendRight(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append(str).append("0");
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    public static String addZeroAppendLeft(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.addZeroAppendRight("lxmk1", 8));
        System.out.println(StringUtil.addZeroAppendRight("lxmk113123", 8));
    }
}
