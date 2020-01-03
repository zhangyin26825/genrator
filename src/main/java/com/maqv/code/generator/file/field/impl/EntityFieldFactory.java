package com.maqv.code.generator.file.field.impl;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.field.FieldElementFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-13 17:17
 **/
public class EntityFieldFactory implements FieldElementFactory {

    @Override
    public String fieldComment(Column column) {
        return JavaCodeElement.comment(column.getComment());
    }

    @Override
    public List<String> fieldAnnotation(Column column) {
        List<String> result=new ArrayList<>(1);
        StringBuffer stringBuffer=new StringBuffer();
        if (column.primarykey()) {
            stringBuffer.append("@DbId");
            String name = "name=" + JavaCodeElement.doubleQuotes(column.getColumnName());
            String autoIncrease="autoIncrease="+column.autoIncrease();
            stringBuffer.append(JavaCodeElement.parentheses(name+COMMA+autoIncrease));
        }else {
            stringBuffer.append("@DbColumn");
            String name = "name=" + JavaCodeElement.doubleQuotes(column.getColumnName());
            String nullable="nullable="+column.nullable();

            String annotation=name;
            if(column.nullable()){
                annotation+=COMMA+nullable;
            }
            stringBuffer.append(JavaCodeElement.parentheses(annotation));
        }
        result.add(stringBuffer.toString());
        return result;
    }

    @Override
    public String fieldBody(Column column) {
        return PRIVATE+column.getJavaString()+" "+column.getFieldName()+SEMICOLON;
    }
}
