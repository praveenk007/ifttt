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

    LESSER_THAN_INCLUSIVE("<=", OperationTypeEnum.MATH);

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
