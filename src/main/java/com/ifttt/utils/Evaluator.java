package com.ifttt.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.ifttt.enums.OperationTypeEnum;
import com.ifttt.enums.OperatorEnum;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Arrays;

/**
 * @author praveenkamath
 **/
public class Evaluator {

    public static Object eval(OperatorEnum operator, Object actual, JsonNode rule) throws Exception {
        Object expected = rule.get("value");
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
        }
        if(operator.getType() == OperationTypeEnum.EXPRESSION) {
            return JaninoExpressUtils.brew(rule.get("id").asText(), rule.get("exp").asText(), rule.get("params").asText().split(","), (Object[]) actual);
        }
        throw new Exception("Unsupported operator!");
    }
}
