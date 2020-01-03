package com.maqv.code.generator.file.create.method;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.param.JoinTable;
import com.maqv.code.generator.file.param.MappingRelations;
import com.maqv.code.generator.file.param.MethodParam;
import com.maqv.code.generator.file.param.SingleTable;
import com.maqv.code.generator.file.source.ClassFindUtils;
import com.maqv.code.generator.file.source.ClassType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyin
 * @create 2019-12-20 10:44
 **/
public class MainParamJavaFile extends SingleTableParamJavaFile {

    private Map<Table,String> methods=new HashMap<>();

    private List<String> getMethods=new ArrayList<>();


    public MainParamJavaFile(MethodParam methodParam) {
        super(methodParam.getMainTable());
        if(CollectionUtils.isNotEmpty(methodParam.getJoinTables())){
            for (JoinTable joinTable : methodParam.getJoinTables()) {
                Table table = joinTable.getSingleTable().getTable();
                String qualifiedName = ClassFindUtils.getQualifiedName(ClassType.Entity, table);
                addImport(qualifiedName);
                if(joinTable.getMappingRelations().equals(MappingRelations.OneToOne)){
                    this.columnList.addAll(joinTable.getSingleTable().getInputColumns());
                    generatorOneToOneGetMethod(joinTable);
                }else {
                    generatorOneToNGetMethod(joinTable);
                }
            }
        }
    }

    /**
     * 1对1的 表的返回对象
     * @param joinTable
     */
    private void generatorOneToOneGetMethod(JoinTable joinTable){
        StringBuffer stringBuffer=new StringBuffer();
        SingleTable singleTable = joinTable.getSingleTable();
        Table table = singleTable.getTable();
        String entityName = table.getEntityName();
        String varName= StringUtils.uncapitalize(entityName);
        String methodName="get"+entityName;
        stringBuffer.append(PUBLIC+ entityName +" "+ methodName +"(){");
        stringBuffer.append(entityName+" "+varName+" = new "+entityName+"();");
        for (Column inputColumn : singleTable.getInputColumns()) {
            stringBuffer.append(varName+DOT+"set"+StringUtils.capitalize(inputColumn.getFieldName())+"("+inputColumn.getFieldName()+");");
        }
        stringBuffer.append(RETURN+varName+SEMICOLON+"}"+NEWLINE);
        methods.put(table,methodName);
        getMethods.add(stringBuffer.toString());
    }

    /**
     * 1对多属性跟方法 表返回方法
     * @param joinTable
     */
    private void generatorOneToNGetMethod(JoinTable joinTable){
        /**
         * 生成 一对多的属性
         */
        SingleTableParamJavaFile singleTableParamJavaFile = new SingleTableParamJavaFile(joinTable.getSingleTable());
        singleTableParamJavaFile.findOrCreateJavaFile();
        addImport(singleTableParamJavaFile.getQualifiedName());
        String paramClassName = singleTableParamJavaFile.getClassName();
        String fieldName=StringUtils.uncapitalize(paramClassName)+"s";
        String  fieldString=" private List<"+paramClassName+"> "+ fieldName+";"+NEWLINE;

        /**
         * 生成 一堆多的返回方法
         */
        StringBuffer sb=new StringBuffer(fieldString);
        Table table = joinTable.getSingleTable().getTable();
        String name = ClassFindUtils.getClassName(ClassType.Entity, table);
        String methodName="get"+name+"s";
        sb.append(PUBLIC+"List<"+name+"> "+methodName+"(){");
        sb.append(RETURN+fieldName+".stream().map("+paramClassName+"::convert).collect(Collectors.toList());"+NEWLINE);
        sb.append("}"+NEWLINE);
        methods.put(table,methodName);
        getMethods.add(sb.toString());
    }

    @Override
    protected String afterField() {
        StringBuffer stringBuffer = new StringBuffer(super.afterField());
        for (String getMethod : getMethods) {
            stringBuffer.append(getMethod);
        }
        return stringBuffer.toString();
    }
}
