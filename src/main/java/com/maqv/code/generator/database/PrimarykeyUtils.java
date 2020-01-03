package com.maqv.code.generator.database;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyin
 * @create 2019-12-15 11:59
 **/
public class PrimarykeyUtils {


    /**
     * 是否多主键
     * @param columnList
     * @return
     */
    public static boolean isMultiId(List<Column> columnList){
        List<Column> collect = columnList.stream().filter(i->i.primarykey()).collect(Collectors.toList());
        if(collect.size()>1){
            return true;
        }
        return  false;
    }

    /**
     * 是否没有主键
     * @param columnList
     * @return
     */
    private static  boolean isNoId(List<Column> columnList){
        List<Column> collect = columnList.stream().filter(i->i.primarykey()).collect(Collectors.toList());
        if(collect.size()==0){
            return true;
        }
        return  false;
    }

    public static String getDaoSuperType(List<Column> columnList){
        if(PrimarykeyUtils.isMultiId(columnList)){
            return "MultipleIdDao";
        }else if(PrimarykeyUtils.isNoId(columnList)){
            return "NoIdDao";
        }else{
            return "SingleIdDao";
        }
    }

    public static String getPrimarykeyType(List<Column> columnList,Table table){
        List<Column> collect = columnList.stream().filter(i->i.primarykey()).collect(Collectors.toList());
        if(collect.size()>1){
            return table.getEntityName()+"Key";
        }else if(collect.size()==1){
            Column column = collect.get(0);
            return column.getJavaString();
        }else {
            return "";
        }
    }
}
