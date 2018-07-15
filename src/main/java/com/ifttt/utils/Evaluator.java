package com.ifttt.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.ifttt.enums.OperationTypeEnum;
import com.ifttt.enums.OperatorEnum;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * @author praveenkamath
 **/
public class Evaluator {

    public static Object eval(OperatorEnum operator, Object actual, Object expected) throws Exception {
        if(operator.getType() == OperationTypeEnum.STRING) {
            return StringOperationUtils.eval(operator, actual, expected);
        }
        if(operator.getType() == OperationTypeEnum.BOOLEAN) {
            return (boolean)actual == ((BooleanNode)expected).asBoolean();
        }
        if(operator.getType() == OperationTypeEnum.LIST_CONTAINS) {
            for(JsonNode jsonNode: (JsonNode) expected) {
                if(jsonNode.asText().equalsIgnoreCase(actual.toString())) {
                    return true;
                }
            } return false;
        }
        if(operator.getType() == OperationTypeEnum.LIST_NOT_CONTAINS) {
            for(JsonNode jsonNode: (JsonNode) expected) {
                if(jsonNode.asText().equalsIgnoreCase(actual.toString())) {
                    return false;
                }
            } return true;
        }
        if(operator.getType() == OperationTypeEnum.MATH) {
            Expression exp = new ExpressionBuilder(actual + operator.getExp() + expected)
                    .operator(MathOperationUtils.getOperation(operator))
                    .build();
            return exp.evaluate() == 1;
        } throw new Exception("Unsupported operator!");
    }
}
