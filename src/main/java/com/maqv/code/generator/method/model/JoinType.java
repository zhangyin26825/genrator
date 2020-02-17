package com.maqv.code.generator.method.model;

public enum JoinType {

    OneToOne(0),
    OneToN(1)

    ;

    private int value;

    JoinType(int value) {
        this.value = value;
    }
}
