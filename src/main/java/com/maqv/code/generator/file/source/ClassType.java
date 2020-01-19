package com.maqv.code.generator.file.source;

import com.maqv.code.generator.file.config.PackageConfig;
import com.maqv.code.generator.file.source.impl.ColumnJavaFilePath;
import com.maqv.code.generator.file.source.impl.TableJavaFilePath;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassType {

    FiledEnum(new ColumnJavaFilePath(PackageConfig.FiledEnum),false),
    MultiId(new TableJavaFilePath(PackageConfig.MultiId),true),

    Entity(new TableJavaFilePath(PackageConfig.Entity),true),
    Table(new TableJavaFilePath(PackageConfig.Table),true),
    Mapper(new TableJavaFilePath(PackageConfig.Mapper),false),
    MapperXml(new TableJavaFilePath(PackageConfig.MapperXml),false),
    Dao(new TableJavaFilePath(PackageConfig.Dao),false),
    DaoImpl(new TableJavaFilePath(PackageConfig.DaoImpl),false),
    Service(new TableJavaFilePath(PackageConfig.Service),false),
    ServiceImpl(new TableJavaFilePath(PackageConfig.ServiceImpl),false),
    Controller(new TableJavaFilePath(PackageConfig.Controller),false),
    ErrorCode(new TableJavaFilePath(PackageConfig.ErrorCode),false),
    ;

    private JavaBaseFilePath javaBaseFilePath;

    /**
     * 是否允许覆盖旧代码
     * 重新生成相关代码的时候 是否可以覆盖以前的代码
     * 数据库表字段变更，有些类可以重新生成，有些不能
     * Entity  Table   MapperXML 这些可以重新生成 覆盖旧代码
     * 其余的都不能覆盖以前的代码
     *
     */
    private boolean override;


}
