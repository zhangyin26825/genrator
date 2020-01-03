package com.maqv.code.generator.file;

import com.maqv.code.generator.database.Column;
import com.maqv.code.generator.file.param.MethodParam;

import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-19 16:15
 **/

public class MethodTask {


   private MethodParam methodParam;

    public MethodTask(MethodParam methodParam) {
        this.methodParam = methodParam;
    }

    public void creatMethodClass(){

   }

   private void generaterParamClass(){

       List<Column> columns = methodParam.allInputColumns();
       if(columns.size()>1) {



       }else {
           /**
            * 输入的字段的数目 小于1
            */
       }

   }


}
