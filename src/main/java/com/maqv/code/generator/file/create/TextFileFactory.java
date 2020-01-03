package com.maqv.code.generator.file.create;

import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;

/** 文本文件的创建
 * @author zhangyin
 * @create 2019-12-13 10:22
 **/
public interface TextFileFactory extends FileFactory {

    /**
     * 获取文件的内容
     * @return
     */
    String   getFileContent();

    /**
     * 获取文件的名字
     * @return
     */
    String   getFileName();

    /**
     * 文件放置的目录
     * @return
     */
    PsiDirectory getDirectory();


    @Override
    default PsiFile create(){
        return FileUtils.createFileFromText(getFileName(),getFileContent(),getDirectory());
    }
}
