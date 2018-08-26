package com.ifttt.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.ifttt.enums.ExpressParamTypeEnum;
import org.codehaus.janino.ExpressionEvaluator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author praveenkamath
 **/
public class JaninoExpressUtils {

    private static final Map<String, Object> compiledExpressions = new ConcurrentHashMap<>();

    public static Object brew(String key, String expression, String variables[], JsonNode paramTypes, Object substitutes[]) throws Exception {
        if(compiledExpressions == null) {
            return null;
        }
        ExpressionEvaluator evaluator = (ExpressionEvaluator) compiledExpressions.get(key);
        if(evaluator == null) {
            Class[] clazzes = getParamTypes(variables, paramTypes);
            evaluator = new ExpressionEvaluator();
            evaluator.setParameters(variables, clazzes);
            evaluator.cook(expression);
            compiledExpressions.put(key, evaluator);
        }
        return evaluator.evaluate(substitutes);
    }

    private static Class[] getParamTypes(String[] variables, JsonNode paramTypes) {
        Class clazzes[] = new Class[variables.length];
        for (int index = 0; index < variables.length; index++) {
            clazzes[index] = ExpressParamTypeEnum.valueOf(paramTypes.get(variables[index]).asText()).getClazz();
        }
        return clazzes;
    }
}
