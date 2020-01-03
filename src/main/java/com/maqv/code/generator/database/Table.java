package com.maqv.code.generator.database;

import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.source.ClassSource;
import com.maqv.code.generator.ui.listselect.ListValue;

import java.util.List;

public interface Table extends ClassSource, ListValue {

    /**
     * 获取表名
     * @return
     */
    String getTableName();

    /**
     * 表注释
     * @return
     */
    String getTableComment();

    /**
     *
     * @return
     */
    List<Column> getColumns();

    /**
     * 获取别名
     * @return
     */
    default String getAlias(){
        return JavaCodeElement.getAlias(getTableName());
    }

    @Override
    default String getStringValue(){
        return getTableName()+"("+getTableComment()+")";
    }

    /**
     * 获取表对应的实体名
     * @return
     */
    default String getEntityName(){
        return JavaCodeElement.convertTableNameToClassName(getTableName());
    }


}
