package com.maqv.code.generator.file.field.impl;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.value_type.impl.StringValueType;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.field.FieldElementFactory;

import java.util.ArrayList;
import java.util.List;

/** 新增的参数生成
 * @author zhangyin
 * @create 2019-12-19 17:07
 **/
public class ParamInsertColumFieldFactor implements FieldElementFactory {

    @Override
    public List<String> fieldAnnotation(Column column) {
        List<String> result=new ArrayList<>(1);
        result.add("@ApiModelProperty("+JavaCodeElement.doubleQuotes(column.getComment())+")");
        if(!column.nullable()){
            result.add(("@NotNull"));
        }
        if(column.getValueType() instanceof StringValueType){
            StringValueType valueType = (StringValueType) column.getValueType();


        }

        return result;
    }

    @Override
    public String fieldBody(Column column) {
        return PRIVATE+column.getJavaString()+" "+column.getFieldName()+SEMICOLON;
    }
}
