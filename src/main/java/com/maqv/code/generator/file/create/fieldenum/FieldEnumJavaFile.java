package com.maqv.code.generator.file.create.fieldenum;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.AbstractJavaClassFileFactory;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.source.ClassType;

/**
 * @author zhangyin
 * @create 2019-12-14 10:49
 **/
public class FieldEnumJavaFile extends AbstractJavaClassFileFactory {

    private Column column;

    public FieldEnumJavaFile(Column column) {
        super(column);
        this.column = column;
        addImports(ImportConstants.fieldImports);
        addClassComment(column.getComment());
    }

    @Override
    protected ClassType classType() {
        return ClassType.FiledEnum;
    }


    @Override
    public String classIdentification() {
        return PUBLIC+ENUM+getClassName()+IMPLEMENTS+" CommonEnum";
    }

    @Override
    protected String beforeField() {
        return " ;\n" +
                "\n" +
                "\n" +
                "    "+getClassName()+"(int value) {\n" +
                "        this.value = value;\n" +
                "    }\n" +
                "\n" +
                "    private int value;\n" +
                "\n" +
                "    @Override\n" +
                "    @JsonValue"+NEWLINE+
                "    public int getValue() {\n" +
                "        return value;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                " @JsonCreator"+NEWLINE+
                "    public static "+getClassName()+" get(Integer value) {\n" +
                "        if (null == value) {\n" +
                "            return null;\n" +
                "        }\n" +
                "        for ("+getClassName()+" status : values()) {\n" +
                "            if (value.equals(status.getValue())) {\n" +
                "                return status;\n" +
                "            }\n" +
                "        }\n" +
                "        return null;\n" +
                "    }";
    }
}
