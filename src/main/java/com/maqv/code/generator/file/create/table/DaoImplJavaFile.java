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
 * @create 2019-12-15 11:38
 **/
public class DaoImplJavaFile extends BaseTableJavaClassFile {

    private List<Column> columnList;

    public DaoImplJavaFile(Table table, List<Column> columnList) {
        super(table);
        this.columnList=columnList;
        addAnnotation("@Repository");
        addImports(ImportConstants.daoImplImports);
        addClassComment("DaoImpl for "+getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.DaoImpl;
    }


    @Override
    public String classIdentification() {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(PUBLIC+CLASS+getClassName()+EXTENDS);
        String daoSuperType = PrimarykeyUtils.getDaoSuperType(columnList);
        stringBuffer.append(daoSuperType+"Impl");
        stringBuffer.append("<"+getEntityName()+COMMA);
        String primarykeyType = PrimarykeyUtils.getPrimarykeyType(columnList, table);
        stringBuffer.append(primarykeyType+">");
        stringBuffer.append(IMPLEMENTS+getEntityName()+"Dao");
        return stringBuffer.toString();
    }

    @Override
    protected String beforeField() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@Autowired\n" +
                "    private "+getEntityName()+"Mapper mapper;\n" +
                "\n" +
                "    @Override\n" +
                "    public Mapper<"+getEntityName()+"> getMapper() {\n" +
                "    return mapper;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Class<"+getEntityName()+"> getPoClazz() {\n" +
                "        return "+getEntityName()+".class;\n" +
                "    }");
        return stringBuffer.toString();
    }
}
