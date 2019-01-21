package com.zzw.utils;

public class StringUtils {
    /**
     * 首字母转换方法(首字母大写)
     *
     * @param str
     * @return
     */
    public static String transferUpper(String str) {
        String prefix = str.substring(0, 1).toUpperCase();
        return prefix + str.substring(1);
    }

    /**
     * 首字母转换方法(首字母小写)
     *
     * @param str
     * @return
     */
    public static String transferLower(String str) {
        String prefix = str.substring(0, 1).toLowerCase();
        return prefix + str.substring(1);
    }

    public static String transferSqlType(String str) {
        if (null != str) {
            if (str.contains("int")) {
                return "int";
            } else if (str.contains("varchar")) {
                return "String";
            } else if (str.contains("date")) {
                return "Date";
            }else if (str.contains("char")){
                return "char";
            }
        }
        return null;
    }


    //todo注意系统环境
    public static String transferPackagePath2FilePath(String str) {
       return str.replace(".","\\");
    }

}
