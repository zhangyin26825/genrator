package com.maqv.code.generator.method.model;

import lombok.Data;

/**
 * @author zhangyin
 * @create 2020-01-19 16:35
 **/
@Data
public class JoinPair {

    private JoinType joinType;

    private String column;

    private String joinTable;

    private String joinTableColumn;
}
