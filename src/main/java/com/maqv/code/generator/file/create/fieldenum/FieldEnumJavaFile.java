package com.maqv.code.generator.file.create.fieldenum;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.AbstractJavaClassFileFactory;
import com.maqv.code.generator.file.create.ImportConstants;
import com.maqv.code.generator.file.source.ClassType;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String comment = column.getComment();
        String s = convertSourceCode(comment);
        return s+" ;\n" +
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


    private static Pattern pattern=Pattern.compile("([0-9]+):[a-zA-Z_]+:[^ ]+");
    private static List<EnumInstance> parse(String comment){
        List<EnumInstance> result=new ArrayList<>();
        Matcher matcher = pattern.matcher(comment);

        while (matcher.find()){
            String group = matcher.group(0);
            String[] s = group.split(":");
            EnumInstance enumInstance = new EnumInstance(Integer.parseInt(s[0]), s[1].toUpperCase(), s[2]);
            result.add(enumInstance);
        }
        return result;
    }

    private static String convertSourceCode(String comment){

        List<EnumInstance> parse = parse(comment);
        if(parse.isEmpty()){
            return " ";
        }
        try {
            StringBuffer stringBuffer=new StringBuffer();

            for (EnumInstance enumInstance : parse) {

                stringBuffer.append(JavaCodeElement.comment(enumInstance.getComment()));
                stringBuffer.append(enumInstance.getName());
                stringBuffer.append("("+enumInstance.getValue()+"),"+NEWLINE);
                stringBuffer.append(NEWLINE);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return " ";
        }
    }

    public static void main(String[] args) {
        String testString="enum    1:activation:激活 2:disable:禁用  ";

        List<EnumInstance> parse = parse(testString);
        System.out.println(parse);
    }
}
