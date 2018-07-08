package com.ifttt.models;


import com.ifttt.enums.OperatorEnum;

/**
 * @author praveenkamath
 **/
public class Rule {

    private String          fact;

    private OperatorEnum operation;

    private Object          value;

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public OperatorEnum getOperation() {
        return operation;
    }

    public void setOperation(OperatorEnum operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "fact='" + fact + '\'' +
                ", operation=" + operation +
                ", value=" + value +
                '}';
    }
}
