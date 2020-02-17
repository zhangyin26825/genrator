package com.maqv.code.generator.method;

import com.maqv.code.generator.method.model.MethodType;
import com.maqv.code.generator.method.model.TableModel;
import lombok.Data;

import java.util.List;

/**
 *  生成整个方法需要参数
 * @author zhangyin
 * @create 2020-01-19 16:29
 **/
@Data
public class MethodGeneratorParameter {

    /**
     * 方法的名称
     */
    private String methodName;
    /**
     * 方法的类型
     */
    private MethodType methodType;
    /**
     * 整个模型
     */
    private List<TableModel> tableModels;
}
