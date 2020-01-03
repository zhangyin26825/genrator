package com.maqv.code.generator.file.source;

import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.config.PackageConfig;

/**
 * @author zhangyin
 * @create 2019-12-16 10:22
 **/
public abstract class JavaBaseFilePath extends BaseFilePath {


    public JavaBaseFilePath(PackageConfig packageConfig) {
        super(packageConfig);
    }


    public abstract String getClassName(ClassSource t);

    @Override
    public final  String getFileName(ClassSource t) {
        return getClassName(t)+JavaCodeElement.DOT+JavaCodeElement.JAVA;
    }

    /**
     * 获取 类路径完整的名称
     * @param t
     * @return
     */
    public String getQualifiedName(ClassSource t) {
        return getPackageName(t) + JavaCodeElement.DOT +getClassName(t);
    }
}
