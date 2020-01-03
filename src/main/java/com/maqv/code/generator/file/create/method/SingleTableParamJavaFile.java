package com.maqv.code.generator.file.create.method;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.AbstractJavaClassFileFactory;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.field.impl.ParamInsertColumFieldFactor;
import com.maqv.code.generator.file.param.SingleTable;
import com.maqv.code.generator.file.source.ClassFindUtils;
import com.maqv.code.generator.file.source.ClassType;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-19 16:27
 **/
public class SingleTableParamJavaFile extends AbstractJavaClassFileFactory {

    private SingleTable singleTable;


    public SingleTableParamJavaFile(SingleTable singleTable) {
        super(singleTable,singleTable.getInputColumns(),new ParamInsertColumFieldFactor());
        this.singleTable=(SingleTable)classSource;
        addAnnotation("@Data");
        addImports(ImportConstants.paramImports);
        addImport(ClassFindUtils.getQualifiedName(ClassType.Entity,singleTable.getTable()));
        addClassComment("param");
    }

    @Override
    protected ClassType classType() {
        return ClassType.SubParam;
    }


    @Override
    public String classIdentification() {
        return PUBLIC+CLASS+getClassName();
    }


    @Override
    protected String afterField() {
        StringBuffer sb=new StringBuffer();
        Table table = singleTable.getTable();
        String entityName = table.getEntityName();
        String varName= StringUtils.uncapitalize(entityName);
        sb.append(PUBLIC+entityName+ "convert(){");
        sb.append(entityName+" "+varName+" = new "+entityName+"();");
        for (Column inputColumn : singleTable.getInputColumns()) {
            sb.append(varName+DOT+"set"+StringUtils.capitalize(inputColumn.getFieldName())+"("+inputColumn.getFieldName()+");");
        }
        sb.append(RETURN+varName+SEMICOLON+"}"+NEWLINE);
        return sb.toString();
    }
}
