package com.maqv.code.generator.file.create;

import com.intellij.psi.PsiFile;

/**
 * @author zhangyin
 * @create 2019-12-13 10:21
 **/
public interface FileFactory {

    /**
     * 创建idea中的文件
      * @return
     */
     PsiFile create();
}
