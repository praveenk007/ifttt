package com.ifttt.utils;

import com.ifttt.enums.OperatorEnum;

/**
 * @author praveenkamath
 **/
public class StringOperationUtils {

    public static final Object eval(OperatorEnum operator, Object obj1, Object obj2) throws Exception {
        System.out.println(obj1);
        System.out.println(obj2);
        switch (operator.getExp()) {
            case "equalsIgnoreCase" : {
                return ((String) obj1).equalsIgnoreCase((String) obj2);
            }
            case "equals" : {
                return obj1.equals(obj2);
            }
            default: {
                throw new Exception("Unsupported String operation '"+operator.getExp()+"'");
            }
        }
    }
}
