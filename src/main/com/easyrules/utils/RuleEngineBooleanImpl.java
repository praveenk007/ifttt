package main.com.easyrules.utils;

import main.com.easyrules.annotations.Fact;
import main.com.easyrules.enums.BooleanJoinEnum;
import main.com.easyrules.models.Rule;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author praveenkamath
 **/
public class RuleEngineBooleanImpl<T> {

    private Map<String, Object> rule;

    private Object object;

    private Map<String, Field> fieldMap;

    public RuleEngineBooleanImpl runThis(Map<String, Object> rule) throws Exception {
        if(this.compileThis()) {
            this.rule   =   rule;
            return this;
        } throw new Exception("Rule compilation error!");
    }

    private static boolean compileThis() {
        //TODO add compilation logic
        return true;
    }

    public RuleEngineBooleanImpl<T> against(Object object) {
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
        System.out.println(fieldMap);
    }

    public boolean execute() throws Exception {
        return exec(rule);
    }

    private boolean exec(Map<String, Object> rule) throws Exception {
        Object anyRule =   rule.get("any");
        Object allRule =   rule.get("all");
        boolean any = false;
        boolean all = true;
        if(anyRule != null && anyRule instanceof List) {
            System.out.println("any rule identified");
            for(Object object : (List) anyRule) {
                System.out.println("rule :::::: "+object);
                any = any || recursivelyApply(object, BooleanJoinEnum.ANY);
            } return any;
        } else if(allRule != null) {
            System.out.println("all rule identified");
            for(Object object : (List) allRule) {
                all = all && recursivelyApply(object, BooleanJoinEnum.ALL);
            } return all;
        } return false;
    }
    private boolean recursivelyApply(Object rule, BooleanJoinEnum join) throws Exception {
            System.out.println("rule is inst :: "+rule.getClass());
        if(rule instanceof Rule) {
            return join    ==  BooleanJoinEnum.ANY ? any((Rule) rule, object) : all((Rule) rule, object);
        } else if(rule instanceof Map) {
            return exec ((Map<String, Object>) rule);
        } return false;
    }

    private boolean any(Rule rule, Object object) throws Exception {
        System.out.println("rule :: "+rule);
        Field field = fieldMap.get(rule.getFact());
        if(field == null) {
            throw new Exception("Fact "+rule.getFact()+" missing in object! Add the annotation in object or remove it from rule.");
        } return eval(rule, field.get(object), rule.getValue());
    }

    private boolean all(Rule rule, Object object) throws Exception {
        System.out.println("rule :: "+rule);
        Field field = fieldMap.get(rule.getFact());
        if(field == null) {
            throw new Exception("Fact "+rule.getFact()+" missing in object! Add the annotation in object or remove it from rule.");
        }
        return eval(rule, field.get(object), rule.getValue());
    }

    /*private boolean any(List<Rule> rules, Object object) {
        List<Boolean> results   =   rules.stream().map(rule -> eval(rule, fieldMap.get(rule.getFact()), rule.getValue()))
                    .collect(Collectors.toList());
        return results.contains(false) ? true : false;
    }

    private boolean all(List<Rule> rules, Object object) {
        List<Boolean> results   =   rules.stream().map(rule -> eval(rule, fieldMap.get(rule.getFact()), rule.getValue()))
                .collect(Collectors.toList());
        long trues   =   results.stream().filter(val -> val == false).count();
        return trues == results.size() ? true : false;
    }*/

    private boolean eval(Rule rule, Object actual, Object expected) {
        Expression exp = new ExpressionBuilder(actual + rule.getOperation().getExp() + expected)
                .operator(OperationUtils.getOperation(rule.getOperation()))
                .build();
        return exp.evaluate() == 1 ? true : false;
    }
}
