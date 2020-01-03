package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.PrimarykeyUtils;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.BaseTableJavaClassFile;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.source.ClassType;

import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-14 13:45
 **/
public class DaoJavaFile extends BaseTableJavaClassFile {

    private List<Column> columnList;

    public DaoJavaFile(Table table, List<Column> columnList) {
        super(table);
        this.columnList=columnList;
        addImports(ImportConstants.daoImports);
        addClassComment("Dao for "+getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.Dao;
    }



    @Override
    public String classIdentification() {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(PUBLIC+INTERFACE+getClassName()+EXTENDS);
        String daoSuperType = PrimarykeyUtils.getDaoSuperType(columnList);
        stringBuffer.append(daoSuperType);
        String primarykeyType = PrimarykeyUtils.getPrimarykeyType(columnList, table);
        stringBuffer.append("<"+getEntityName()+COMMA+primarykeyType+">");
        return stringBuffer.toString();

    }
}
