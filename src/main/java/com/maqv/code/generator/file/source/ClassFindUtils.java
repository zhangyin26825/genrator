package com.maqv.code.generator.file.source;

import com.intellij.psi.PsiClass;
import com.maqv.code.generator.G;

/**
 *  找到框架自动生成的类的方法
 *  这里不包括在系统中存在的别的class
 *  如果需要找别的类，需要用到的是 G.findClass() 方法
 * @author zhangyin
 * @create 2019-12-16 15:16
 **/
public class ClassFindUtils {


    public static PsiClass getClass(ClassType classType,ClassSource classSource){
        JavaBaseFilePath javaBaseFilePath = classType.getJavaBaseFilePath();
        if (javaBaseFilePath.support(classSource.getClass())) {
            return  G.findClass(javaBaseFilePath.getQualifiedName(classSource));
        }
        throw new RuntimeException("错误的ClassType");
    }

    public static String getQualifiedName(ClassType classType,ClassSource classSource){
        JavaBaseFilePath javaBaseFilePath = classType.getJavaBaseFilePath();
        return javaBaseFilePath.getQualifiedName(classSource);
    }

    public static String  getClassName(ClassType classType,ClassSource classSource){
        JavaBaseFilePath javaBaseFilePath = classType.getJavaBaseFilePath();
        return javaBaseFilePath.getClassName(classSource);
    }

}
