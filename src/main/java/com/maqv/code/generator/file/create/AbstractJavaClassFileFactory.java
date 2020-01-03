package com.maqv.code.generator.file.create;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.maqv.code.generator.G;
import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.ColumnUtils;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.config.PackageConfig;
import com.maqv.code.generator.file.field.ColumnFieldFactory;
import com.maqv.code.generator.file.source.ClassSource;
import com.maqv.code.generator.file.source.ClassType;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangyin
 * @create 2019-12-13 11:11
 **/
public abstract class AbstractJavaClassFileFactory implements TextFileFactory, JavaCodeElement {

    private static String javaSuffix=".java";

    private Set<String> importLists;

    private Set<String> annotations;

    private List<String> classComments;


    protected ClassSource classSource;

    protected List<Column> columnList;

    protected ColumnFieldFactory fieldFactory;


    public  void  addAnnotation(String annotation){
        annotations.add(annotation);
    }

    public  void  addImport(String importDesc){
        importLists.add(importDesc);
    }

    public  void addImports(List<String> imports){
        importLists.addAll(imports);
    }

    public void addClassComment(String classComment){
        classComments.add(classComment);
    }

    protected AbstractJavaClassFileFactory(ClassSource classSource){
        this(classSource,null,null);
    }

    public AbstractJavaClassFileFactory(ClassSource classSource,List<Column> columnList, ColumnFieldFactory fieldFactory) {
        this.classSource=classSource;
        this.columnList = columnList;
        this.fieldFactory = fieldFactory;
        importLists=new LinkedHashSet<>();
        annotations=new LinkedHashSet<>();
        classComments=new ArrayList<>();
    }

    /**
     * 返回类名
     * @return
     */
    public final  String  getClassName(){
       return classType().getJavaBaseFilePath().getClassName(classSource);
    }


    /**
     * 返回这个类的包配置
     * @return
     */
    abstract protected ClassType classType();

    /**
     * 返回包名
     * @return
     */
    public final String  getPackageName(){
       return classType().getJavaBaseFilePath().getPackageName(classSource);
    }

    @Override
    public String getFileName(){
        return  getClassName()+javaSuffix;
    }

    public String  getQualifiedName(){
        return  getPackageName()+DOT+getClassName();
    }

    /**
     * 包
     * @return
     */
    private final String  packageCode(){
        return PACKAGE+getPackageName()+SEMICOLON;
    }

    /**
     * 导入
     * @return
     */
    private final String importCode() {
        StringBuffer stringBuffer=new StringBuffer();
        for (String importDesc : importLists) {
            stringBuffer.append(IMPORT+importDesc+SEMICOLON+NEWLINE);
        }
        return stringBuffer.toString();
    }
//    /**
//     * 类注释
//     * @return
//     */
//    public abstract String  classCommentCode();

    private final String classComment(){
        String property = System.getProperties().getProperty("user.name");
        classComments.add("@author "+property);
       return JavaCodeElement.commentCollections(classComments);
    }


    /**
     * 类注释
     * @return
     */
    public final String  classAnnotation(){
        StringBuffer stringBuffer=new StringBuffer();
        for (String annotation : annotations) {
            stringBuffer.append(annotation+NEWLINE);
        }
        return stringBuffer.toString();
    }
    /**
     * 类标识信息
     * @return
     */
    public abstract String  classIdentification();

    /**
     * 类里面的信息
     * @return
     */
    public  final  String  classbody(){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(beforeField()+NEWLINE);
        if(CollectionUtils.isNotEmpty(columnList)&&fieldFactory!=null){
            List<String> enumFieldImport = ColumnUtils.getEnumFieldImport(columnList);
            addImports(enumFieldImport);
            for (Column column : columnList) {
                stringBuffer.append(fieldFactory.generatorFieldString(column));
                stringBuffer.append(NEWLINE);
            }
        }
        stringBuffer.append(afterField()+NEWLINE);
        return stringBuffer.toString();
    }

    protected String beforeField() {
        return "";
    }

    protected String afterField() {
        return "";
    }


    @Override
    public final String getFileContent() {
        StringBuffer sb=new StringBuffer();
        sb.append(packageCode()+NEWLINE);
        sb.append(NEWLINE);
        sb.append(importCode());
        sb.append(NEWLINE);
        sb.append(classComment());
        sb.append(NEWLINE);
        sb.append(classAnnotation());
        sb.append(classIdentification()+LEFT_BRACES+NEWLINE);
        sb.append(NEWLINE);
        sb.append(classbody());
        sb.append(NEWLINE);
        sb.append(RIGHT_BRACES);
        return sb.toString();
    }


    @Override
    public final PsiDirectory getDirectory() {
        return G.findPackageDirectory(getPackageName());
    }


    /**
     * 找到或者创建java文件
     * @return
     */
    public PsiJavaFile  findOrCreateJavaFile(){
        String qualifiedName = getQualifiedName();
        PsiClass aClass = G.findClass(qualifiedName);
        if (aClass == null) {
            PsiFile psiFile = create();
            return  (PsiJavaFile)psiFile;
        }else {
            ClassType classType = classType();
            if(classType.isOverride()){
                PsiFile psiFile = create();
                return  (PsiJavaFile)psiFile;
            }else {
                return  (PsiJavaFile)aClass.getContainingFile();
            }
        }
    }
}
