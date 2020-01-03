package com.maqv.code.generator;

/**
 * @author zhangyin
 * @create 2019-12-13 11:04
 **/
public enum PropertiesKey {

    ControllerKey("controller.package"),

    ServiceKey("service.package"),

    ServiceImplKey("service.impl.package"),

    ErrorCodeKey("error.code.package"),

    EntityKey("entity.package"),

    MapperKey("mapper.package"),

    DaoKey("dao.package"),

    DaoImplKey("dao.impl.package"),

    XmlKey("xml.package"),

    JdbcUrlKey("spring.datasource.url"),

    UsernameKey("spring.datasource.username"),

    PasswordKey("spring.datasource.password"),

    FieldEnumKey("field.enum.package"),

    TableKey("table.package"),

    ;

    private String key;


    PropertiesKey(String key ) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
