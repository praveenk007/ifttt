package main.com.easyrules.enums;

/**
 * @author praveenkamath
 **/
public enum OperatorEnum {

    EQUALS("=="),

    GREATER_THAN(">"),

    LESSER_THAN("<"),

    GREATER_THAN_INCLUSIVE(">="),

    LESSER_THAN_INCLUSIVE("<=");

    private String exp;

    OperatorEnum(String exp) {
        this.exp    =   exp;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
