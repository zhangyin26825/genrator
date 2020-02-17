package com.maqv.code.generator.database.mysql;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.ColumnImpl;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.database.TableImpl;
import com.maqv.code.generator.database.value_type.ValueType;
import com.maqv.code.generator.database.value_type.impl.*;
import com.maqv.code.generator.file.JavaCodeElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangyin
 * @create 2019-12-15 14:13
 **/
@Data
@AllArgsConstructor
public class MysqlColumnInfo {

    private String columnName;

    private String tableName;

    private Integer ordinalPosition;

    private  String nullable;

    private String dataType;

    private Integer characterMaximumLength;

    private Integer characterOctetLength;

    private Integer numericPrecidion;

    private  Integer numericScale;

    private String columnType;

    private String columnKey;

    private  String extra;

    private String  columnComment;

    private String tableComment;


    public Column createColumn(){

        boolean createEnum= StringUtils.startsWith(columnComment,"enum");

        boolean isnullable=StringUtils.equals(nullable,"YES");

        boolean primaryKey=StringUtils.contains(columnKey,"PRI");
        ValueType valueType=createValueType(createEnum);

        boolean unique=StringUtils.contains(columnKey,"PRI")||StringUtils.contains(columnKey,"UNI");

        StringUtils.contains(columnKey,"PRI");

        boolean autoIncrease=StringUtils.contains(extra,"auto_increment");

        return new ColumnImpl(columnName,columnComment,createEnum,isnullable,primaryKey,tableName,valueType,unique,autoIncrease );

    }

    private ValueType  createValueType(boolean createEnum){
        if(createEnum){
            return new EnumValueType(StringUtils.capitalize(JavaCodeElement.getFieldName(columnName)));
        }else {
            switch (dataType){
                case "int":
                case "tinyint":
                    return new IntegerValueType();
                case "varchar":
                case "text":
                    return new StringValueType(characterMaximumLength);
                case "timestamp":
                case "date":
                case "time":
                case "datetime":
                    return new DateValueType(dataType);
                case "decimal":
                    return new BigdecimalValueType(numericPrecidion,numericScale);
                default:
                    throw new RuntimeException("数据库类型未定义"+dataType);
            }
        }
    }

    public static List<Table>  convert(List<MysqlColumnInfo> mysqlColumnInfos){
        List<Table> tables=new ArrayList<>();
        Map<String, List<MysqlColumnInfo>> collect = mysqlColumnInfos.stream().collect(Collectors.groupingBy(MysqlColumnInfo::getTableName));
        for (Map.Entry<String, List<MysqlColumnInfo>> entry : collect.entrySet()) {
            String tableName = entry.getKey();
            List<MysqlColumnInfo> value = entry.getValue();
            List<Column> columnInfos = value.stream().sorted( Comparator.comparingInt(MysqlColumnInfo::getOrdinalPosition))
                    .map(MysqlColumnInfo::createColumn)
                    .collect(Collectors.toList());
            String tableComment = value.get(0).getTableComment();
            Table table = new TableImpl(tableName, tableComment, columnInfos);
            tables.add(table);
        }
        return tables;
    }


}
