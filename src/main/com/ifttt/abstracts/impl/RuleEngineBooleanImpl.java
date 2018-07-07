package main.com.ifttt.abstracts.impl;

import com.fasterxml.jackson.databind.JsonNode;
import main.com.ifttt.abstracts.RuleEngine;
import main.com.ifttt.annotations.Fact;
import main.com.ifttt.enums.BooleanJoinEnum;
import main.com.ifttt.enums.OperatorEnum;
import main.com.ifttt.utils.OperationUtils;
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

    private Map<String, Field> fieldMap;

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
    public RuleEngineBooleanImpl against(Object object) {
        this.object     =   object;
        constructAnnotationFieldMap();
        return this;
    }

    private void constructAnnotationFieldMap() {
        fieldMap    =   new HashMap<>();
        Arrays.stream(object.getClass().getFields()).forEach(field -> {
            Annotation annotation   =  field.getAnnotation(Fact.class);
            if(annotation != null) {
                fieldMap.put((field.getAnnotation(Fact.class)).value(), field);
            }
        } );
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
        Field field = fieldMap.get(rule.get("fact").asText());
        if(field == null) {
            throw new Exception("Fact "+rule.get("fact")+" missing in object! Add the annotation in object or remove it from rule.");
        } return eval(rule, field.get(object), rule.get("value"));
    }

    private boolean all(JsonNode rule, Object object) throws Exception {
        Field field = fieldMap.get(rule.get("fact").asText());
        if(field == null) {
            throw new Exception("Fact "+rule.get("fact")+" missing in object! Add the annotation in object or remove it from rule.");
        }
        return eval(rule, field.get(object), rule.get("value"));
    }

    private boolean eval(JsonNode rule, Object actual, Object expected) {
        String operator = rule.get("operator").asText();
        Expression exp = new ExpressionBuilder(actual + OperatorEnum.valueOf(operator).getExp() + expected)
                .operator(OperationUtils.getOperation(OperatorEnum.valueOf(operator)))
                .build();
        return exp.evaluate() == 1 ? true : false;
    }
}