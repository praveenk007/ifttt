package com.ifttt.enums;

/**
 * @author praveenkamath
 **/
public enum ExpressParamTypeEnum {

    INTEGER(int.class),
    STRING(String.class),
    FLOAT(float.class),
    LONG(long.class),
    DOUBLE(double.class);

    private Class clazz;

    ExpressParamTypeEnum(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
