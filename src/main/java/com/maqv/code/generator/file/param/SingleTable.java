package com.maqv.code.generator.file.param;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.database.Table;
import com.maqv.code.generator.file.source.ClassSource;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-19 16:17
 **/
@Data
@AllArgsConstructor
public class SingleTable implements ClassSource {

    private Table table;

    private List<Column> inputColumns;

    private List<Column> outputColumns;

    private String methodName;


}
