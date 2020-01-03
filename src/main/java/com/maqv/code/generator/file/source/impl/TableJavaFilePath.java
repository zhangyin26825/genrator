package com.maqv.code.generator.file.source.impl;

import com.maqv.code.generator.G;
import com.maqv.code.generator.PropertiesKey;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.config.PackageConfig;
import com.maqv.code.generator.file.source.ClassSource;
import com.maqv.code.generator.file.source.JavaBaseFilePath;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-16 14:08
 **/
public class TableJavaFilePath  extends JavaBaseFilePath {

    @Override
    public boolean support(Class t) {
        return t.equals(Table.class);
    }

    public TableJavaFilePath(PackageConfig packageConfig) {
        super(packageConfig);
    }

    @Override
    public String getPackageName(ClassSource classSource) {
        Table table =(Table)classSource;
        PropertiesKey key = packageConfig.getPropertiesKey();
        String packageName = G.getProperties(key);
        if(packageConfig.needAddTableNameToSubPackage()){
            packageName=packageName+ JavaCodeElement.DOT+ StringUtils.lowerCase(table.getEntityName());
        }
        if(StringUtils.isNotEmpty(packageConfig.subPackage())){
            packageName=packageName+JavaCodeElement.DOT+StringUtils.lowerCase(packageConfig.subPackage());
        }
        return packageName;
    }


    @Override
    public String getClassName(ClassSource t) {
        Table table =(Table)t;
        return table.getEntityName()+packageConfig.getClassNameSuffix();
    }
}
