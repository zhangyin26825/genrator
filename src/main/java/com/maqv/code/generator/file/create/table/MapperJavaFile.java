package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.BaseTableJavaClassFile;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.source.ClassType;

/**
 * @author zhangyin
 * @create 2019-12-14 13:26
 **/
public class MapperJavaFile extends BaseTableJavaClassFile {

    public MapperJavaFile(Table table) {
        super(table);
        addImports(ImportConstants.mappperImports);
        addClassComment("Mapper for "+getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.Mapper;
    }


    @Override
    public String classIdentification() {
        return PUBLIC+INTERFACE+getEntityName()+"Mapper "+EXTENDS+"Mapper<"+getEntityName()+">";
    }
}
