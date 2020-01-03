package com.maqv.code.generator.database.value_type.impl;

import com.maqv.code.generator.database.value_type.ValueType;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-10 11:09
 **/
public class EnumValueType implements ValueType {

    private String name;

    public EnumValueType(String name) {
        this.name = name;
    }

    @Override
    public String getJavaString() {
        return StringUtils.capitalize(name);
    }

    @Override
    public String getXmlJdbcType() {
        return "INTEGER";
    }
}
