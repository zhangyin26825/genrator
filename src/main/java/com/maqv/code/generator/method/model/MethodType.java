package com.maqv.code.generator.method.model;

public enum MethodType {
    insert(0),
    select(1),
    update(2),
    delete(3),
    ;

    private int value;

    MethodType(int value) {
        this.value = value;
    }
}
