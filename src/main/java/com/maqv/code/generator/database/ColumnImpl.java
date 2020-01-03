package com.maqv.code.generator.database;

import com.maqv.code.generator.database.value_type.ValueType;
import lombok.AllArgsConstructor;

/**
 * @author zhangyin
 * @create 2019-12-09 15:10
 **/
@AllArgsConstructor
public class ColumnImpl implements Column {

    private String name;

    private String comment;

    private boolean createEnum;

    private boolean nullable;

    private boolean primaryKey;

    private String tableName;

    private ValueType valueType;

    private boolean unique;

    private boolean autoIncrease;

    @Override
    public String getColumnName() {
        return name;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public boolean createEnum() {
        return createEnum;
    }

    @Override
    public boolean nullable() {
        return nullable;
    }

    @Override
    public boolean primarykey() {
        return primaryKey;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public ValueType getValueType() {
        return valueType;
    }

    @Override
    public boolean unique() {
        return unique;
    }

    @Override
    public boolean autoIncrease() {
        return autoIncrease;
    }

}
