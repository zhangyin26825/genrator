package com.maqv.code.generator;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.search.GlobalSearchScope;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zhangyin
 * @create 2019-12-13 10:29
 **/
public class G {
    private static Logger logger = Logger.getLogger(G.class);

    private static boolean inited=false;

    private static Project project;

    private static JavaPsiFacade javaPsiFacade;

    private static Properties properties=new Properties();

    public static Project getProject() {
        return project;
    }



    public static  void init(Project project,Properties property){
            G.project = project;
            javaPsiFacade = JavaPsiFacade.getInstance(project);
            inited = true;
            properties=property;
    }

    public static String getProperties(PropertiesKey propertiesKey){
        return properties.getProperty(propertiesKey.getKey());
    }

    /**
     * 根据全路径找到对应的class文件
     * @param qualifiedName
     * @return
     */
    public static PsiClass findClass(String qualifiedName){
        PsiClass psiClass = javaPsiFacade.findClass(qualifiedName, GlobalSearchScope.allScope(project));
        return psiClass;
    }

    /**
     * 根据包名找到对应的包对象
     * @param packageName
     * @return
     */
    public static PsiPackage findPackage(String packageName){
        return javaPsiFacade.findPackage(packageName);
    }

    /**
     * 找到包所在的目录
     * @param packageName
     * @return
     */
    public static PsiDirectory findPackageDirectory(String packageName){
        PsiPackage aPackage = findPackage(packageName);
        if (aPackage == null) {
           return createPackage(packageName);
        }
        PsiDirectory directory = aPackage.getDirectories()[0];
        return directory;
    }

    private static PsiDirectory  createPackage(String packageName){
        String temp=packageName.substring(0,packageName.lastIndexOf('.'));
        while(findPackage(temp)==null) {
            if(temp.contains(".")) {
                temp = temp.substring(0, temp.lastIndexOf('.'));
            }else {
                break;
            }
        }
        PsiDirectory directory = findPackage(temp).getDirectories()[0];
        String substring = packageName.substring(temp.length()+1, packageName.length());
        String[] split = substring.split("\\.");
        WriteCommandAction.runWriteCommandAction(G.getProject(), () -> {
            PsiDirectory target=directory;
            for (String s : split) {
                PsiDirectory subdirectory = directory.findSubdirectory(s);
                if (subdirectory == null) {
                    target = directory.createSubdirectory(s);
                }else {
                    target=subdirectory;
                }
            }
        });
        PsiPackage aPackage = findPackage(packageName);
        return aPackage.getDirectories()[0];
    }

    public static void main(String[] args) {
       String packageName= "com.maqv.entity.abd";

        System.out.println(packageName.substring(0,packageName.lastIndexOf('.')));
    }
}
