package com.maqv.code.generator.method.model;

import lombok.Data;

import java.util.List;

/**
 * @author zhangyin
 * @create 2020-01-19 16:32
 **/
@Data
public class TableModel {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 是否有从这张表开始
     */
    private boolean start;
    /**
     * 输入的列
     */
    private List<String> inputs;
    /**
     * 输出的列
     */
    private List<String> outputs;
    /**
     * 管理的表
     */
    private List<JoinPair> joinPairs;
    /**
     * 处理的顺序
     */
    private Integer order;
}
