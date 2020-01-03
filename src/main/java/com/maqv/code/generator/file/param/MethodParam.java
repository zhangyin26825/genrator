package com.maqv.code.generator.file.param;


import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.source.ClassSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-19 16:16
 **/
@Data
@AllArgsConstructor
public class MethodParam  implements ClassSource {

    private SingleTable mainTable;

    private List<JoinTable> joinTables;

    private String methodName;

    private boolean controller=true;

    public List<Column>  allInputColumns(){
        List<Column> result=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(mainTable.getInputColumns())){
            result.addAll(mainTable.getInputColumns());
        }
        if(CollectionUtils.isNotEmpty(joinTables)){
            for (JoinTable joinTable : joinTables) {
                if(CollectionUtils.isNotEmpty(joinTable.getSingleTable().getInputColumns())){
                    result.addAll(joinTable.getSingleTable().getInputColumns());
                }
            }
        }
        return result;
    }

    public List<Column>  allOutputColumns(){
        List<Column> result=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(mainTable.getOutputColumns())){
            result.addAll(mainTable.getOutputColumns());
        }
        if(CollectionUtils.isNotEmpty(joinTables)){
            for (JoinTable joinTable : joinTables) {
                if(CollectionUtils.isNotEmpty(joinTable.getSingleTable().getOutputColumns())){
                    result.addAll(joinTable.getSingleTable().getOutputColumns());
                }
            }
        }
        return result;
    }

}
