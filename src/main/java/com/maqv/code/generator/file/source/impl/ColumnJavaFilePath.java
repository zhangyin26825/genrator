package com.maqv.code.generator.file.source.impl;

import com.maqv.code.generator.G;
import com.maqv.code.generator.PropertiesKey;
import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.JavaCodeElement;
import com.maqv.code.generator.file.config.PackageConfig;
import com.maqv.code.generator.file.source.ClassSource;
import com.maqv.code.generator.file.source.JavaBaseFilePath;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2019-12-16 14:28
 **/
public class ColumnJavaFilePath  extends JavaBaseFilePath {

    @Override
    public boolean support(Class t) {
        return t.equals(Column.class);
    }

    public ColumnJavaFilePath(PackageConfig packageConfig) {
        super(packageConfig);
    }

    @Override
    public String getPackageName(ClassSource classSource) {
        PropertiesKey key = packageConfig.getPropertiesKey();
        String packageName = G.getProperties(key);
        if(StringUtils.isNotEmpty(packageConfig.subPackage())){
            packageName=packageName+ JavaCodeElement.DOT+StringUtils.lowerCase(packageConfig.subPackage());
        }
        return packageName;
    }


    @Override
    public String getClassName(ClassSource classSource) {
        Column column=(Column)classSource;
        return StringUtils.capitalize(column.getFieldName());
    }
}
