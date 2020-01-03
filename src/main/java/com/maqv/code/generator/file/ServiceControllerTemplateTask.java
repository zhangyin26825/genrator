package com.maqv.code.generator.file;

import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.create.table.ControllerJavaFile;
import com.maqv.code.generator.file.create.table.ErrorCodeJavaFile;
import com.maqv.code.generator.file.create.table.ServiceImplJavaFile;
import com.maqv.code.generator.file.create.table.ServiceJavaFile;
import com.maqv.code.generator.file.source.ClassFindUtils;
import com.maqv.code.generator.file.source.ClassType;
import lombok.AllArgsConstructor;

/**
 * @author zhangyin
 * @create 2019-12-16 17:02
 **/
@AllArgsConstructor
public class ServiceControllerTemplateTask {

    private Table table;


    public void createTemplate(){

        CheckPackageExistUtils.check();



        String dao = ClassFindUtils.getQualifiedName(ClassType.Dao, table);
        ServiceJavaFile serviceJavaFile=new ServiceJavaFile(table);
        serviceJavaFile.findOrCreateJavaFile();

        ServiceImplJavaFile serviceImplJavaFile=new ServiceImplJavaFile(table);
        serviceImplJavaFile.addImport(dao);
        serviceImplJavaFile.addImport(serviceJavaFile.getQualifiedName());
        serviceImplJavaFile.findOrCreateJavaFile();

        ControllerJavaFile controllerJavaFile=new ControllerJavaFile(table);
        controllerJavaFile.addImport(serviceJavaFile.getQualifiedName());
        controllerJavaFile.findOrCreateJavaFile();

        ErrorCodeJavaFile errorCodeJavaFile = new ErrorCodeJavaFile(table);
        errorCodeJavaFile.findOrCreateJavaFile();

    }
}
