package com.maqv.code.generator.file.field;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.JavaCodeElement;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-13 16:45
 **/
public interface  FieldElementFactory extends ColumnFieldFactory{

    default String fieldComment(Column column){
        return JavaCodeElement.comment(column.getComment());
    }

    List<String> fieldAnnotation(Column column);

    String fieldBody(Column column);


    @Override
    default String generatorFieldString(Column column){
        StringBuffer stringBuffer=new StringBuffer();
        String comment = fieldComment(column);
        if(StringUtils.isNotEmpty(comment)){
            stringBuffer.append(comment);
        }
        List<String> annotations = fieldAnnotation(column);
        if(CollectionUtils.isNotEmpty(annotations)){
            for (String annotation : annotations) {
                stringBuffer.append(annotation);
                stringBuffer.append(NEWLINE);
            }
        }
        stringBuffer.append(fieldBody(column));
        return  stringBuffer.toString();
    }
}
