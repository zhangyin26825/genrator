package com.maqv.code.generator.file.create;

import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.PlainTextLanguage;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.maqv.code.generator.G;
import org.apache.log4j.Logger;

/**
 * @author zhangyin
 * @create 2019-12-13 10:27
 **/
public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    /**
     * 根据文件名  文件内容  目录 创建一个idea中的文件对象
     * 如果已经存在，那么先删除，然后新建一个文件
     * @param fileName
     * @param fileSourcesCode
     * @param directory
     * @return
     */
    public static PsiFile createFileFromText(String fileName, String fileSourcesCode, PsiDirectory directory){
        logger.info("在"+directory.getVirtualFile().getPath()+"目录下创建"+fileName+"文件");
        if(directory==null){
            throw new RuntimeException("创建文件指定的目录为空，新建的文件为"+fileName);
        }
        Language language=getLanguage(fileName);
        PsiFile psiFile = PsiFileFactory.getInstance(G.getProject()).createFileFromText(fileName, language, fileSourcesCode);
        //如果这个目录下已经存在同名的文件，那么删除
        PsiFile[] files = directory.getFiles();
        for (PsiFile file : files) {
            if(file.getName().equals(fileName)){
                WriteCommandAction.runWriteCommandAction(G.getProject(), () -> {
                    file.delete();
                });
            }
        }
        WriteCommandAction.runWriteCommandAction(G.getProject(), () -> {
            if(language== JavaLanguage.INSTANCE) {
                JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(G.getProject());
                styleManager.optimizeImports(psiFile);
            }
            CodeStyleManager.getInstance(G.getProject()).reformat(psiFile);
            directory.add(psiFile);
        });
        return psiFile;
    }

    private static Language getLanguage(String fileName){
        Language language;
        if(fileName.endsWith("java")){
            language= JavaLanguage.INSTANCE;
        }else if(fileName.endsWith("xml")){
            language= XMLLanguage.INSTANCE;
        }else {
            language= PlainTextLanguage.INSTANCE;
        }
        return  language;
    }
}
