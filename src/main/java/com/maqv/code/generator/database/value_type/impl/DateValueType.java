package com.maqv.code.generator.database.value_type.impl;

import com.maqv.code.generator.database.value_type.ValueType;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-09 15:06
 **/
public class DateValueType implements ValueType {

    private String dateType;

    public DateValueType(String dateType) {
        this.dateType = dateType;
    }

    @Override
    public String getJavaString() {
        return "java.util.Date";
    }

    @Override
    public String getXmlJdbcType() {
        if(StringUtils.equals(dateType,"datetime"))
        {
            return "TIMESTAMP";
        }
        return StringUtils.upperCase(dateType);
    }
}
