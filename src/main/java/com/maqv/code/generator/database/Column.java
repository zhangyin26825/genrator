package com.maqv.code.generator.database;

import com.maqv.code.generator.database.value_type.ValueType;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.source.ClassSource;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-09 14:41
 **/
public interface Column  extends ValueType,ClassSource {
    /**
     * 列的名字
     * @return
     */
    String getColumnName();

    default String getFieldName(){
        return JavaCodeElement.getFieldName(getColumnName());
    }

    /**
     * 注释
     * @return
     */
    String getComment();
    /**
     * 是否需要为这列创建枚举类
     * @return
     */
    boolean createEnum();
    /**
     * 是否可以为空
     * @return
     */
    boolean nullable();
    /**
     * 是否是主键
     * @return
     */
    boolean primarykey();
    /**
     * 获取表名
     * @return
     */
    String getTableName();
    /**
     * 是否是唯一的
     * @return
     */
    boolean unique();

    /**
     * 是否自增
     * @return
     */
    boolean autoIncrease();
    /**
     * 值类型
     * @return
     */
    ValueType getValueType();

    @Override
    default  String getJavaString(){
       return getValueType().getJavaString();
    }
    @Override
    default String getXmlJdbcType(){
        return getValueType().getXmlJdbcType();
    }
    
    
    default String getErrorCodeName(){
        return "PARAM_"+ StringUtils.upperCase(getFieldName())+"_ERROR";
    }


}
