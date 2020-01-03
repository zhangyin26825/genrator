package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.BaseTableJavaClassFile;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.source.ClassType;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-16 16:49
 **/
public class ControllerJavaFile extends BaseTableJavaClassFile {

    public ControllerJavaFile(Table table) {
        super(table);
        addImports(ImportConstants.controllerImports);
        addAnnotation("@RestController");
        addAnnotation("@RequestMapping(\"{version}/api/"+getEntityName().toLowerCase()+"\")");
        addAnnotation("@Validated");
        addAnnotation("@Api(tags ="+JavaCodeElement.doubleQuotes(getTableComment())+")");
        addClassComment("controller for"+getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.Controller;
    }


    @Override
    public String classIdentification() {
        return PUBLIC+CLASS+getClassName();
    }

    @Override
    protected String beforeField() {
        return  "@Autowired\n private " + getEntityName() + "Service " + StringUtils.uncapitalize(getEntityName() + "Service; ");
    }
}
