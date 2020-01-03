package com.maqv.code.generator.file.field.impl;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.field.FieldElementFactory;

import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-14 11:12
 **/
public class TableFieldFactory implements FieldElementFactory {

    @Override
    public List<String> fieldAnnotation(Column column) {
        return null;
    }

    @Override
    public String fieldBody(Column column) {
        return "Column "+column.getFieldName()+"= new Column(TABLE"+COMMA+ JavaCodeElement.doubleQuotes(column.getColumnName())+")"+SEMICOLON;
    }
}
