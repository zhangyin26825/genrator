package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.BaseTableJavaClassFile;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.field.impl.EntityFieldFactory;
import com.maqv.code.generator.file.source.ClassType;


import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-14 10:13
 **/
public class EntityJavaFile extends BaseTableJavaClassFile {


    public EntityJavaFile(Table table,List<Column> columnList) {
        super(columnList, new EntityFieldFactory(), table);
        addImports(ImportConstants.entityImports);
        addAnnotation("@Data");
        addAnnotation("@DbTable(name ="+JavaCodeElement.doubleQuotes(table.getTableName())+COMMA+"alias = "
        +JavaCodeElement.doubleQuotes(getAlias())+")");
        addClassComment("entity for "+table.getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.Entity;
    }


    @Override
    public String classIdentification() {
        return PUBLIC+CLASS+getEntityName();
    }


}
