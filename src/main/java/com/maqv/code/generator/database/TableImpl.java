package com.maqv.code.generator.database;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author zhangyin
 * @create 2019-12-09 14:40
 **/
@Data
@AllArgsConstructor
public class TableImpl implements Table {

    private String tableName;

    private String comment;

    private List<Column> allColumns;

    @Override
    public String getTableComment() {
        return comment;
    }

    @Override
    public List<Column> getColumns() {
        return allColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof TableImpl)){ return false;}
        TableImpl table = (TableImpl) o;
        return Objects.equals(getTableName(), table.getTableName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableName());
    }
}
