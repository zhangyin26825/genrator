package com.maqv.code.generator.file.create.xml;

import com.intellij.psi.PsiDirectory;
import com.maqv.code.generator.G;
import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.create.TextFileFactory;
import com.maqv.code.generator.file.source.ClassType;

import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-15 12:47
 **/
public class MapperXmlFile implements TextFileFactory {

    private Table table;

    private List<Column> columnList;

    private String mapperClassFullPath;

    private String entityClassFullPath;

    public MapperXmlFile(Table table, List<Column> columnList, String mapperClassFullPath, String entityClassFullPath) {
        this.table = table;
        this.columnList = columnList;
        this.mapperClassFullPath = mapperClassFullPath;
        this.entityClassFullPath = entityClassFullPath;
    }

    @Override
    public String getFileContent() {
        String poClass=table.getEntityName();
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuffer.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        stringBuffer.append("<mapper namespace=\""+ mapperClassFullPath+"\">\n");
        stringBuffer.append("<resultMap id=\"resultMap\" type=\""+entityClassFullPath+"\">\n");
        for (Column columnInfo : columnList) {
            if(columnInfo.primarykey()){
                stringBuffer.append("<id ");
            }else{
                stringBuffer.append("<result ");
            }
            stringBuffer.append("column=\"").append(columnInfo.getColumnName()).append("\" jdbcType=\"")
                    .append(columnInfo.getXmlJdbcType())
                    .append("\" property=\"")
                    .append(columnInfo.getFieldName())
                    .append("\" ");
            if(columnInfo.createEnum()){
                stringBuffer.append("typeHandler=\"tech.ibit.mybatis.type.CommonEnumTypeHandler\"");
            }
            stringBuffer.append("/>\n");
        }
        stringBuffer.append("</resultMap>\n");
        stringBuffer.append("</mapper>\n");
        return stringBuffer.toString();
    }

    @Override
    public String getFileName() {
        return table.getEntityName()+"Mapper.xml";
    }

    @Override
    public PsiDirectory getDirectory() {
        String packageName = ClassType.MapperXml.getJavaBaseFilePath().getPackageName(table);
        PsiDirectory packageDirectory = G.findPackageDirectory(packageName);
        PsiDirectory temp=packageDirectory;
        int i=1;
        while(!temp.getName().equals("main")||i>30){
            temp=temp.getParent();
            i++;
        }
        PsiDirectory sourcesRoot=null;
        if(temp.getName().equals("main")){
            sourcesRoot=temp.findSubdirectory("resources");
            PsiDirectory target=sourcesRoot;
            String[] split = packageName.split("\\.");
            for (String s : split) {

                PsiDirectory subdirectory = target.findSubdirectory(s);
                if (subdirectory == null) {
                    subdirectory= target.createSubdirectory(s);
                }
                target=subdirectory;
            }
            return target;
        }
        return packageDirectory;
    }
}
