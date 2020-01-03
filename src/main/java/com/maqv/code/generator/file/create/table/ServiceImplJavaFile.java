package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.create.BaseTableJavaClassFile;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.source.ClassType;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-16 16:43
 **/
public class ServiceImplJavaFile  extends BaseTableJavaClassFile {


    public ServiceImplJavaFile(Table table) {
        super(table);
        addImports(ImportConstants.serviceImplImports);
        addAnnotation("@Service");
        addClassComment("service for "+table.getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.ServiceImpl;
    }


    @Override
    public String classIdentification() {
        return PUBLIC+CLASS+getClassName()+IMPLEMENTS+getEntityName()+"Service";
    }

    @Override
    protected String beforeField() {
        return "@Autowired\n private "+getEntityName()+"Dao "+ StringUtils.uncapitalize(getEntityName() + "Dao; ");
    }
}
