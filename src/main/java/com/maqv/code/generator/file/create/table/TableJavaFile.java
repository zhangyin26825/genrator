package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.config.PackageConfig;
import com.maqv.code.generator.file.create.BaseTableJavaClassFile;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.field.impl.TableFieldFactory;
import com.maqv.code.generator.file.source.ClassType;

import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-14 10:30
 **/
public class TableJavaFile  extends BaseTableJavaClassFile {

    public TableJavaFile(Table table,List<Column> columnList) {
        super(columnList, new TableFieldFactory(), table);
        addImports(ImportConstants.tableImports);
        addClassComment("DBTable for "+table.getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.Table;
    }

    @Override
    public String classIdentification() {
        return PUBLIC+INTERFACE+getClassName();
    }

    @Override
    protected String beforeField() {
        return "Table TABLE = new Table("+JavaCodeElement.doubleQuotes(getTableName())+COMMA+JavaCodeElement.doubleQuotes(getAlias())+")"+SEMICOLON;
    }
}
