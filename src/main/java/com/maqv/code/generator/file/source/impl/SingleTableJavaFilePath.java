package com.maqv.code.generator.file.source.impl;

import com.maqv.code.generator.G;
import com.maqv.code.generator.PropertiesKey;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.config.PackageConfig;
import com.maqv.code.generator.file.param.SingleTable;
import com.maqv.code.generator.file.source.ClassSource;
import com.maqv.code.generator.file.source.JavaBaseFilePath;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-19 16:30
 **/
public class SingleTableJavaFilePath extends JavaBaseFilePath {


    public SingleTableJavaFilePath(PackageConfig packageConfig) {
        super(packageConfig);
    }

    @Override
    public String getClassName(ClassSource t) {
        SingleTable singleTable =(SingleTable)t;
        return  StringUtils.capitalize(singleTable.getMethodName())+ singleTable.getTable().getEntityName()+packageConfig.getClassNameSuffix();
    }

    @Override
    public boolean support(Class t) {
        return t.equals(SingleTable.class);
    }

    @Override
    public String getPackageName(ClassSource t) {
        SingleTable singleTable =(SingleTable)t;
        PropertiesKey key = packageConfig.getPropertiesKey();
        String packageName = G.getProperties(key);
        if(packageConfig.needAddTableNameToSubPackage()){
            String entityName = singleTable.getTable().getEntityName();
            packageName=packageName+ JavaCodeElement.DOT+ StringUtils.lowerCase(entityName);
        }
        if(StringUtils.isNotEmpty(packageConfig.subPackage())){
            packageName=packageName+JavaCodeElement.DOT+StringUtils.lowerCase(packageConfig.subPackage());
        }
        return packageName;
    }
}
