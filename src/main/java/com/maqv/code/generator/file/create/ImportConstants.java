package com.maqv.code.generator.file.create;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-14 10:21
 **/
public class ImportConstants {

    public  static List<String> entityImports=new ArrayList();

    static {
        entityImports.add("tech.ibit.sqlbuilder.annotation.DbColumn");
        entityImports.add("tech.ibit.sqlbuilder.annotation.DbId");
        entityImports.add("tech.ibit.sqlbuilder.annotation.DbTable");
        entityImports.add("lombok.Data");
        entityImports.add("java.util.Date");
        entityImports.add("java.math.BigDecimal");
    }

    public  static List<String> tableImports=new ArrayList<>();
    static {
        tableImports.add("tech.ibit.sqlbuilder.Column");
        tableImports.add("tech.ibit.sqlbuilder.Table");
    }

    public static List<String> fieldImports=new ArrayList<>();
    static {
        fieldImports.add("com.fasterxml.jackson.annotation.JsonCreator");
        fieldImports.add("com.fasterxml.jackson.annotation.JsonValue");
        fieldImports.add("tech.ibit.mybatis.type.CommonEnum");
    }

    public static List<String> mappperImports=new ArrayList<>();
    static {
        mappperImports.add("tech.ibit.mybatis.template.mapper.Mapper");
    }

    public static List<String> daoImports=new ArrayList<>();
    static {
        daoImports.add("tech.ibit.mybatis.template.dao.SingleIdDao");
        daoImports.add("tech.ibit.mybatis.template.dao.MultipleIdDao");
        daoImports.add("tech.ibit.mybatis.template.dao.SingleIdDao");
        daoImports.add("tech.ibit.mybatis.template.dao.NoIdDao");
        daoImports.add("tech.ibit.mybatis.template.dao.impl.SingleIdDaoImpl");
        daoImports.add("tech.ibit.mybatis.template.dao.impl.MultipleIdDaoImpl");
        daoImports.add("tech.ibit.mybatis.template.dao.impl.NoIdDaoImpl");
    }

    public static List<String> daoImplImports=new ArrayList();
    static {
        daoImplImports.add("tech.ibit.mybatis.template.dao.SingleIdDao");
        daoImplImports.add("tech.ibit.mybatis.template.dao.MultipleIdDao");
        daoImplImports.add("tech.ibit.mybatis.template.dao.NoIdDao");
        daoImplImports.add("tech.ibit.mybatis.template.dao.impl.SingleIdDaoImpl");
        daoImplImports.add("tech.ibit.mybatis.template.dao.impl.MultipleIdDaoImpl");
        daoImplImports.add("tech.ibit.mybatis.template.dao.impl.NoIdDaoImpl");
        daoImplImports.add("org.springframework.stereotype.Repository");
        daoImplImports.add("org.springframework.beans.factory.annotation.Autowired");
        daoImplImports.add("tech.ibit.mybatis.template.mapper.Mapper");
    }

    public static List<String> serviceImplImports=new ArrayList<>();
    static {
        serviceImplImports.add("org.springframework.beans.factory.annotation.Autowired");
        serviceImplImports.add("org.springframework.stereotype.Service");
    }

    public static List<String> controllerImports=new ArrayList<>();
    static {
        controllerImports.add("org.springframework.beans.factory.annotation.Autowired");
        controllerImports.add("org.springframework.web.bind.annotation.RestController");
        controllerImports.add("org.springframework.web.bind.annotation.RequestMapping");
        controllerImports.add("org.springframework.validation.annotation.Validated");
        controllerImports.add("org.springframework.http.MediaType");
        controllerImports.add("cloud.heyasset.springboot.web.response.annotation.CustomResponse");
        controllerImports.add("io.swagger.annotations.Api");
    }

    public static List<String> paramImports=new ArrayList<>();
    static {
        paramImports.add("io.swagger.annotations.ApiModelProperty");
        paramImports.add("lombok.Data");
        paramImports.add("org.hibernate.validator.constraints.Length");
        paramImports.add("org.hibernate.validator.constraints.NotEmpty");
        paramImports.add("javax.validation.constraints.NotNull");
        paramImports.add("javax.validation.constraints.Size");
        paramImports.add("java.util.List");
    }

}
