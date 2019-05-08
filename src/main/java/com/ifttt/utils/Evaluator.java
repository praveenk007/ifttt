package com.ifttt.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.ifttt.enums.OperationTypeEnum;
import com.ifttt.enums.OperatorEnum;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;

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
        if(operator.getType() == OperationTypeEnum.INPUT_LIST_HAS && actual instanceof List) {
            return ((List) actual).contains(((JsonNode) expected).asText());
        }
        if(operator.getType() == OperationTypeEnum.LIST_INTERSECTS && actual instanceof List) {
            List<String> actualList = new ArrayList<>(new ObjectMapper().convertValue(actual, ArrayList.class));
            List<String> expectedList = new ObjectMapper().convertValue(expected, ArrayList.class);
            actualList.retainAll(expectedList);
            return !actualList.isEmpty();
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
            return JaninoExpressUtils.brew(rule.get("id").asText(), rule.get("exp").asText(), rule.get("params").asText().split(","),  rule.get("paramTypes"), (Object[]) actual);
        }
        throw new Exception("Unsupported operator!");
    }
}
