package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.source.ClassType;

import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-15 12:14
 **/
public class MultipleKeyJavaFile extends EntityJavaFile {

    public MultipleKeyJavaFile(Table table,List<Column> columnList) {
        super(table, columnList);
    }


    @Override
    protected ClassType classType() {
        return ClassType.MultiId;
    }

    @Override
    public String classIdentification() {
        return PUBLIC+CLASS+getClassName()+IMPLEMENTS+"DBId";
    }
}
