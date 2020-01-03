package com.maqv.code.generator.database.value_type.impl;

import com.maqv.code.generator.database.value_type.ValueType;

/**
 * @author zhangyin
 * @create 2019-12-09 14:50
 **/
public class StringValueType implements ValueType {

    private Integer maxLength;

    @Override
    public String getJavaString() {
        return "String";
    }

    @Override
    public String getXmlJdbcType() {
        return "VARCHAR";
    }

    public StringValueType(Integer maxLength) {
        this.maxLength = maxLength;
    }
}
