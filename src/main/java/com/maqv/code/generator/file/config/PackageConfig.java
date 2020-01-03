package com.maqv.code.generator.file.config;


import com.maqv.code.generator.PropertiesKey;

/**
 * 根据这个来找到对应的文件
 * @author
 */
public enum PackageConfig {
    /**
     * 属性生成的枚举类
     */
    FiledEnum(PropertiesKey.FieldEnumKey,false,"",""),
    /**
     * 多主键
     */
    MultiId(PropertiesKey.EntityKey,false,"","Key"),
    /**
     * 实体类
     */
    Entity(PropertiesKey.EntityKey,false,"",""),
    /**
     * Table类
     */
    Table(PropertiesKey.TableKey,false,"","Properties"),
    /**
     * Mapper
     */
    Mapper(PropertiesKey.MapperKey,false,"","Mapper"),
    /**
     * Mapper的xml文件
     */
    MapperXml(PropertiesKey.XmlKey,false,"",""),
    /**
     * Dao
     */
    Dao(PropertiesKey.DaoKey,false,"","Dao"),
    /**
     * DaoImpl
     */
    DaoImpl(PropertiesKey.DaoImplKey,false,"","DaoImpl"),
    /**
     * Service
     */
    Service(PropertiesKey.ServiceKey,true,"","Service"),
    /**
     * ServiceImpl
     */
    ServiceImpl(PropertiesKey.ServiceKey,true,"impl","ServiceImpl"),
    /**
     * Controller
     */
    Controller(PropertiesKey.ControllerKey,false,"","Controller"),
    /**
     * ErrorCode
     */
    ErrorCode(PropertiesKey.ServiceKey,true,"code","ErrorCode"),
    /**
     * SubParam
     */
    SubParam(PropertiesKey.ServiceKey,false,"param","SubParam"),
    /**
     * DTO
     */
    Dto(PropertiesKey.ServiceKey,false,"dto","DTO"),
    ;

    private PropertiesKey propertiesKey;


    private boolean addTableNameSubPackage;

    private String subPackage;

    private String classNameSuffix;


    PackageConfig(PropertiesKey propertiesKey, boolean addTableNameSubPackage,
                  String subPackage, String classNameSuffix) {
        this.propertiesKey = propertiesKey;
        this.addTableNameSubPackage = addTableNameSubPackage;
        this.subPackage = subPackage;
        this.classNameSuffix=classNameSuffix;
    }

    public PropertiesKey getPropertiesKey() {
        return propertiesKey;
    }


    public boolean needAddTableNameToSubPackage() {
        return addTableNameSubPackage;
    }

    public String subPackage() {
        return subPackage;
    }

    public String getClassNameSuffix() {
        return classNameSuffix;
    }
}
