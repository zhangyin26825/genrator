package com.maqv.code.generator.database.value_type.impl;

import com.maqv.code.generator.database.value_type.ValueType;

/**
 * @author zhangyin
 * @create 2019-12-09 14:52
 **/
public class BigdecimalValueType implements ValueType {

    /**
     * 整数位数
     */
    private Integer integer;

    /**
     * 小数位数
     */
    private Integer fraction;

    @Override
    public String getJavaString() {
        return "BigDecimal";
    }

    public BigdecimalValueType(Integer integer, Integer fraction) {
        this.integer = integer;
        this.fraction = fraction;
    }

    @Override
    public String getXmlJdbcType() {
        return "DECIMAL";
    }
}
