package main.java.com.ifttt.abstracts.impl;

import com.fasterxml.jackson.databind.JsonNode;
import main.java.com.ifttt.abstracts.RuleEngine;
import main.java.com.ifttt.annotations.Fact;
import main.java.com.ifttt.enums.BooleanJoinEnum;
import main.java.com.ifttt.enums.OperatorEnum;
import main.java.com.ifttt.utils.OperationUtils;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author praveenkamath
 **/
public class RuleEngineBooleanImpl extends RuleEngine {

    private JsonNode rule;

    private Object object;

    private Map<String, Object> fieldMap;

    @Override
    public RuleEngine runThis(JsonNode rule) throws Exception {
        if(this.compileThis()) {
            this.rule   =   rule;
            return this;
        } throw new Exception("Rule compilation error!");
    }

    private static boolean compileThis() {
        //TODO add compilation logic
        return true;
    }

    @Override
    public RuleEngineBooleanImpl against(Object object) throws Exception{
        this.object     =   object;
        if(object instanceof Map) {
            this.fieldMap = (Map<String, Object>) object;
        } else {
            constructAnnotationFieldMap();
        }
        return this;
    }

    private void constructAnnotationFieldMap() throws Exception {
        fieldMap    =   new HashMap<>();
        for(Field field: object.getClass().getFields()) {
            Annotation annotation   =  field.getAnnotation(Fact.class);
            if(annotation != null) {
                fieldMap.put((field.getAnnotation(Fact.class)).value(), field.get(object));
            }
        }
    }

    @Override
    public Boolean execute() throws Exception {
        return new Boolean(exec(rule));
    }

    private boolean exec(JsonNode rule) throws Exception {
        JsonNode anyRule =   rule.get("any");
        JsonNode allRule =   rule.get("all");
        boolean any = false;
        boolean all = true;
        if(anyRule != null) {
            for(JsonNode  object : anyRule) {
                any = any || recursivelyApply(object, BooleanJoinEnum.ANY);
            } return any;
        } else if(allRule != null) {
            for(JsonNode object : allRule) {
                all = all && recursivelyApply(object, BooleanJoinEnum.ALL);
            } return all;
        } return false;
    }
    private boolean recursivelyApply(JsonNode rule, BooleanJoinEnum join) throws Exception {
        if(rule.get("any") == null && rule.get("all") == null) {
            return join    ==  BooleanJoinEnum.ANY ? any(rule, object) : all(rule, object);
        } return exec (rule);
    }

    private boolean any(JsonNode rule, Object object) throws Exception {
        Object factVal  = fieldMap.get(rule.get("fact").asText());
        return eval(rule, factVal, rule.get("value"));
    }

    private boolean all(JsonNode rule, Object object) throws Exception {
        Object factVal = fieldMap.get(rule.get("fact").asText());
        return eval(rule, factVal, rule.get("value"));
    }

    private boolean eval(JsonNode rule, Object actual, Object expected) {
        String operator = rule.get("operator").asText();
        Expression exp = new ExpressionBuilder(actual + OperatorEnum.valueOf(operator).getExp() + expected)
                .operator(OperationUtils.getOperation(OperatorEnum.valueOf(operator)))
                .build();
        return exp.evaluate() == 1 ? true : false;
    }
}
