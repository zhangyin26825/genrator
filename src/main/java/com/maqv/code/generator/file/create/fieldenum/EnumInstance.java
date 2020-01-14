package com.maqv.code.generator.file.create.fieldenum;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhangyin
 * @create 2020-01-14 16:22
 **/
@Data
@ToString
public class EnumInstance {
    /**
     * 枚举的值
      */
    private Integer value;
    /**
     * 枚举的名称
      */
    private String name;
    /**
     * 注释
     */
    private String comment;

    public EnumInstance(Integer value, String name, String comment) {
        this.value = value;
        this.name = name;
        this.comment = comment;
    }
}
