package com.maqv.code.generator.file.create.table;

import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.BaseTableJavaClassFile;
import com.maqv.code.generator.file.source.ClassType;

/**
 * @author zhangyin
 * @create 2019-12-16 17:24
 **/
public class ErrorCodeJavaFile extends BaseTableJavaClassFile {


    public ErrorCodeJavaFile(Table table) {
        super(table);
        addImport("cloud.heyasset.lhj.common.code.ErrorCode");
        addClassComment("errorCode for "+getTableName());
    }

    @Override
    protected ClassType classType() {
        return ClassType.ErrorCode;
    }


    @Override
    public String classIdentification() {
        return PUBLIC+INTERFACE+getClassName()+EXTENDS+"ErrorCode";
    }

    @Override
    protected String beforeField() {
        return " /**\n" +
                " * 编码前缀\n" +
                "  */\n" +
                "  int CODE_PREFIX = PRODUCT_CODE_PREFIX;";
    }

}
