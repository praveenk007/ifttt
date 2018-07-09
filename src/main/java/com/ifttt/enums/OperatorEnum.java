package com.ifttt.enums;

/**
 * @author praveenkamath
 **/
public enum OperatorEnum {

    EQUALS("==", OperationTypeEnum.MATH),

    GREATER_THAN(">", OperationTypeEnum.MATH),

    LESSER_THAN("<", OperationTypeEnum.MATH),

    GREATER_THAN_INCLUSIVE(">=", OperationTypeEnum.MATH),

    STRING_EQUALS_IGNORE("equalsIgnoreCase", OperationTypeEnum.STRING),

    STRING_EQUALS("equals", OperationTypeEnum.STRING),

    STRING_NOT_EQUALS("notEquals", OperationTypeEnum.STRING),

    BOOLEAN_EQUALS("booleanEquals", OperationTypeEnum.BOOLEAN),

    LESSER_THAN_INCLUSIVE("<=", OperationTypeEnum.MATH),

    LIST_CONTAINS("listContains", OperationTypeEnum.LIST_CONTAINS),

    LIST_NOT_CONTAINS("listNotContains", OperationTypeEnum.LIST_NOT_CONTAINS);

    private String exp;

    private OperationTypeEnum type;

    OperatorEnum(String exp, OperationTypeEnum type) {
        this.exp    =   exp;
        this.type   =   type;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public OperationTypeEnum getType() {
        return type;
    }

    public void setType(OperationTypeEnum type) {
        this.type = type;
    }
}
