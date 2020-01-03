package com.maqv.code.generator.file;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * 构造Java源代码一些公用的元素
 */
public interface JavaCodeElement {

    String NEWLINE = "\n";

    String LEFT_BRACES = "{";

    String RIGHT_BRACES = "}";

    String PRIVATE = "private ";

    String PUBLIC = "public ";

    String SEMICOLON=";";

    String PACKAGE="package ";

    String IMPORT="import ";

    String CLASS="class ";

    String ENUM="enum ";

    String INTERFACE="interface ";

    String EXTENDS=" extends ";

    String IMPLEMENTS=" implements ";

    String DOT=".";

    String COMMA=",";

    String JAVA="java";

    String RETURN="return ";


    /**
     * 注释
     *
     * @param comments
     * @return
     */
    public static String comment(String... comments) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("/**" + NEWLINE);
        for (String comment : comments) {
            stringBuffer.append("*" + comment + NEWLINE);
        }
        stringBuffer.append("*/" + NEWLINE);
        return stringBuffer.toString();
    }
    public static String commentCollections(List<String> comments) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("/**" + NEWLINE);
        for (String comment : comments) {
            stringBuffer.append("*" + comment + NEWLINE);
        }
        stringBuffer.append("*/" + NEWLINE);
        return stringBuffer.toString();
    }


    //去掉连字符后的字母大写
    public static String removeUnderline(String old) {
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = old.toCharArray();
        boolean isHyphen = false;
        for (char aChar : chars) {
            if (aChar == '_') {
                isHyphen = true;
                continue;
            }
            if (isHyphen) {
                stringBuffer.append(Character.toUpperCase(aChar));
                isHyphen = false;
            } else {
                stringBuffer.append(aChar);
            }
        }
        return stringBuffer.toString();
    }

    public static String convertTableNameToClassName(String tableName){
        String result = tableName;
        if (tableName.startsWith("sz_")) {
            result = tableName.replaceFirst("sz_", "");
        }
        if (tableName.startsWith("xm_")) {
            result = tableName.replaceFirst("xm_", "");
        }
        String s = removeUnderline(result);
        return StringUtils.capitalize(s);
    }

    public static  String getFieldName(String columnName){
        return removeUnderline(columnName);
    }

    public static String getAlias(String tableName) {
        StringBuffer sb=new StringBuffer();
        String[] split = tableName.split("_");
        for (String s : split) {
            sb.append(s.charAt(0));
        }
        return sb.toString();
    }

    /**
     * 双引号
     * @param content
     * @return
     */
    public static String doubleQuotes(String content){
        return "\""+content+"\"";
    }

    /**
     * 小括号
     * @param content
     * @return
     */
    public static String parentheses(String content){
        return "("+content+")";
    }


}
