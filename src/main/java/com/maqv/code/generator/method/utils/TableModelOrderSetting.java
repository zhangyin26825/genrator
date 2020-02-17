package com.maqv.code.generator.method.utils;

import com.maqv.code.generator.method.model.JoinPair;
import com.maqv.code.generator.method.model.TableModel;
import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangyin
 * @create 2020-01-19 16:53
 **/
public class TableModelOrderSetting {


    /**
     * 计算每个表模型中的每个表所在的顺序
     * @param tableModels
     */
    public static  void settingOrder(List<TableModel> tableModels){

        tableModels.forEach(tableModel -> tableModel.setOrder(null));

        List<TableModel> collect = tableModels.stream().filter(t -> t.isStart()).collect(Collectors.toList());
        if(collect.isEmpty()||collect.size()>1){
            throw new RuntimeException("表模型中开始表的数量错误，开始表的数量必须是1");
        }

        Map<String, TableModel> modelMap = tableModels.stream().collect(Collectors.toMap(TableModel::getTableName, Function.identity()));

        TableModel startModel = collect.get(0);
        int order=1;
        startModel.setOrder(order++);
        TableModel temp=null;

        Queue<TableModel> queue = new LinkedList<TableModel>();
        queue.add(startModel);
        while(!queue.isEmpty()){
            temp=queue.poll();
            if(CollectionUtils.isNotEmpty(temp.getJoinPairs())) {
                for (JoinPair joinPair : temp.getJoinPairs()) {
                    String joinTable = joinPair.getJoinTable();
                    TableModel tableModel = modelMap.get(joinTable);
                    if (tableModel == null) {
                        throw new RuntimeException("关联中 表的名称错误");
                    }
                    if(tableModel.getOrder()==null) {
                        tableModel.setOrder(order++);
                        queue.add(tableModel);
                    }
                }
            }
        }
    }
}
