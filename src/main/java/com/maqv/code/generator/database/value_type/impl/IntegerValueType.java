package com.maqv.code.generator.database.value_type.impl;

import com.maqv.code.generator.database.value_type.ValueType;

/**
 * @author zhangyin
 * @create 2019-12-09 14:51
 **/
public class IntegerValueType implements ValueType {

    @Override
    public String getJavaString() {
        return "Integer";
    }

    @Override
    public String getXmlJdbcType() {
        return "INTEGER";
    }
}
