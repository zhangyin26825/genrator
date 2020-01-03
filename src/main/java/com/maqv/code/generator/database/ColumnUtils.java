package com.maqv.code.generator.database;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.maqv.code.generator.G;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.create.fieldenum.FieldEnumJavaFile;
import com.maqv.code.generator.file.source.ClassFindUtils;
import com.maqv.code.generator.file.source.ClassType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyin
 * @create 2019-12-19 17:21
 **/
public class ColumnUtils {


    public static List<String> getEnumFieldImport(List<Column> inputColumns){
        List<String> enumImports=new ArrayList<>();
        List<Column> enumColumns = inputColumns.stream().filter(i -> i.createEnum()).collect(Collectors.toList());
        for (Column column : enumColumns) {
            FieldEnumJavaFile fieldEnumJavaFile = new FieldEnumJavaFile(column);
            fieldEnumJavaFile.findOrCreateJavaFile();
            enumImports.add(fieldEnumJavaFile.getQualifiedName());
        }
        return  enumImports;
    }

    /**
     * 为某一列生成错误码
      * @param column
     * @param table
     */
    public static void generatorErrorCode(Column column,Table table){
        PsiClass aClass = ClassFindUtils.getClass(ClassType.ErrorCode, table);
        String columnErrorCodeName=column.getErrorCodeName();
        PsiClass[] innerClasses = aClass.getInnerClasses();
        if(innerClasses!=null){
            for (PsiClass innerClass : innerClasses) {
                if(StringUtils.equals(innerClass.getName(),columnErrorCodeName)){
                    //这里证明 这个错误码已经存在
                    return;
                }
            }
        }
        int size=innerClasses.length;

        String classContent="interface "+columnErrorCodeName+"{"
                +" int CODE = CODE_PREFIX +"+size+++";"
                +"String MSG = CODE+"+ JavaCodeElement.doubleQuotes("__")
                        +JavaCodeElement.doubleQuotes(column.getComment()+"错误")+";}";

        PsiJavaFile fileFromText = (PsiJavaFile) PsiFileFactory.getInstance(G.getProject())
                .createFileFromText(columnErrorCodeName+".java", JavaFileType.INSTANCE, classContent);
        PsiClass[] classes1 = fileFromText.getClasses();
        WriteCommandAction.runWriteCommandAction(G.getProject(), new Runnable() {
            @Override
            public void run() {
                aClass.add(classes1[0]);
            }
        });

    }
}
