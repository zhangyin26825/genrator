package com.maqv.code.generator.file.create;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.field.ColumnFieldFactory;
import lombok.experimental.Delegate;

import java.util.List;

/**
 *  依赖于某个表生成的文件
 * @author zhangyin
 * @create 2019-12-13 16:24
 **/
public abstract class BaseTableJavaClassFile extends AbstractJavaClassFileFactory {

    @Delegate
    protected Table table;

    public BaseTableJavaClassFile(Table table) {
        super(table);
        this.table = table;
    }

    public BaseTableJavaClassFile(List<Column> columnList, ColumnFieldFactory fieldFactory, Table table) {
        super(table,columnList, fieldFactory);
        this.table = table;
    }

}
