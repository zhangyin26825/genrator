package com.maqv.code.generator.file.field;


import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.JavaCodeElement;

/**
 * 把 column转换成field的字符串格式
 */
public interface ColumnFieldFactory extends JavaCodeElement {


    /**
     * 生成 field的字符串格式
     * @param column
     * @return
     */
    String  generatorFieldString(Column column);
}
