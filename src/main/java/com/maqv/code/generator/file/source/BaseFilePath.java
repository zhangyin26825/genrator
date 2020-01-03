package com.maqv.code.generator.file.source;

import com.maqv.code.generator.file.config.PackageConfig;

/**
 *  文件的基本信息
 *
 *  文件所在的包名
 *  文件的名称
 *
 * @author zhangyin
 * @create 2019-12-16 10:14
 **/
public abstract class BaseFilePath {

    public abstract boolean support(Class t);

    protected PackageConfig packageConfig;

    public BaseFilePath(PackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }
    /**
     * 完整的包名
     * 在idea里都是通过包名去寻找对应的路径
     * 这个在只生成java相关的文件是没有问题的
     * 但是要生成一些不是java相关的类，就有可能有问题，这里暂时忽略这个问题
     * @return
     */
    public abstract String getPackageName(ClassSource t);

    /**
     * 文件名称
     * @return
     */
    public abstract String getFileName(ClassSource t);




}
