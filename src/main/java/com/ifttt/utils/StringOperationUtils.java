package com.ifttt.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.ifttt.enums.OperatorEnum;

/**
 * @author praveenkamath
 **/
public class StringOperationUtils {

    public static final Object eval(OperatorEnum operator, Object obj1, Object obj2) throws Exception {
        switch (operator.getExp()) {
            case "equalsIgnoreCase" : {
                return ((String) obj1).equalsIgnoreCase(((JsonNode) obj2).asText());
            }
            case "equals" : {
                return obj1.equals(((JsonNode)obj2).asText());
            }
            case "notEquals" : {
                return !obj1.equals(((JsonNode)obj2).asText());
            }
            default: {
                throw new Exception("Unsupported String operation '"+operator.getExp()+"'");
            }
        }
    }
}
