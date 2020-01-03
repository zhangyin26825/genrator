package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.BaseTableJavaClassFile;
import com.maqv.code.generator.file.source.ClassType;

/**
 * @author zhangyin
 * @create 2019-12-16 16:40
 **/
public class ServiceJavaFile  extends BaseTableJavaClassFile {

    public ServiceJavaFile(Table table) {
        super(table);
        addClassComment("service for "+getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.Service;
    }


    @Override
    public String classIdentification() {
        return PUBLIC+INTERFACE+getClassName();
    }
}
