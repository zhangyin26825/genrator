package com.maqv.code.generator.file;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.ColumnUtils;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.create.fieldenum.FieldEnumJavaFile;
import com.maqv.code.generator.file.create.table.*;
import com.maqv.code.generator.file.create.xml.MapperXmlFile;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** 表基本信息生成的相关类
 * @author zhangyin
 * @create 2019-12-15 11:47
 **/
@AllArgsConstructor
public class TableGeneratorTask {

    private Table table;

    private List<Column> columns;


    public  void createTableRelationClasses(){

        CheckPackageExistUtils.check();
        /**
         * 属性需要生成枚举类
         */
        List<String> enumImports=new ArrayList<>();
        List<Column> enumColumns = columns.stream().filter(i -> i.createEnum()).collect(Collectors.toList());
        enumImports.addAll(ColumnUtils.getEnumFieldImport(enumColumns));


        /**
         *  多主键
         */
        List<String> primaryKeyImport=new ArrayList<>();
        List<Column> primaryKeys = columns.stream().filter(i -> i.primarykey()).collect(Collectors.toList());
        if(primaryKeys.size()>1){
            MultipleKeyJavaFile multipleKeyJavaFile = new MultipleKeyJavaFile(table, primaryKeys);
            multipleKeyJavaFile.findOrCreateJavaFile();
            primaryKeyImport.add(multipleKeyJavaFile.getQualifiedName());
        }
        /**
         * 实体类
         */
        EntityJavaFile entityJavaFile = new EntityJavaFile(table, columns);
        if(CollectionUtils.isNotEmpty(enumImports)){
            entityJavaFile.addImports(enumImports);
        }
        if(CollectionUtils.isNotEmpty(primaryKeyImport)){
            entityJavaFile.addImports(primaryKeyImport);
        }
        entityJavaFile.findOrCreateJavaFile();
        /**
         * Table类
         */
        TableJavaFile tableJavaFile = new TableJavaFile(table, columns);
        tableJavaFile.findOrCreateJavaFile();

        /**
         * Mapper
         */
        MapperJavaFile mapperJavaFile = new MapperJavaFile(table);
        mapperJavaFile.addImport(entityJavaFile.getQualifiedName());
        mapperJavaFile.findOrCreateJavaFile();

        /**
         * Dao
         */
        DaoJavaFile daoJavaFile = new DaoJavaFile(table, columns);
        daoJavaFile.addImport(entityJavaFile.getQualifiedName());
        if(CollectionUtils.isNotEmpty(primaryKeyImport)){
            daoJavaFile.addImports(primaryKeyImport);
        }
        daoJavaFile.findOrCreateJavaFile();

        /**
         * DaoImpl
         */
        DaoImplJavaFile daoImplJavaFile = new DaoImplJavaFile(table, columns);
        daoImplJavaFile.addImport(entityJavaFile.getQualifiedName());
        daoImplJavaFile.addImport(daoJavaFile.getQualifiedName());
        daoImplJavaFile.addImport(mapperJavaFile.getQualifiedName());
        if(CollectionUtils.isNotEmpty(primaryKeyImport)){
            daoImplJavaFile.addImports(primaryKeyImport);
        }
        daoImplJavaFile.findOrCreateJavaFile();

        /**
         * Mapper XML
         */
        MapperXmlFile mapperXmlFile = new MapperXmlFile(table, columns, mapperJavaFile.getQualifiedName(), entityJavaFile.getQualifiedName());
        mapperXmlFile.create();
    }




}
