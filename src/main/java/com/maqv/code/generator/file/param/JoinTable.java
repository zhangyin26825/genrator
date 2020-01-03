package com.maqv.code.generator.file.param;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangyin
 * @create 2019-12-19 16:21
 **/
@Data
@AllArgsConstructor
public class JoinTable {

    private MappingRelations mappingRelations;

    private JoinCondition joinCondition;

    private SingleTable singleTable;

}
