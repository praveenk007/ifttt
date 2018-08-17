package com.ifttt.utils;

import org.codehaus.janino.ExpressionEvaluator;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author praveenkamath
 **/
public class JaninoExpressUtils {

    private static final Map<String, Object> compiledExpressions = new ConcurrentHashMap<>();

    public static Object brew(String key, String expression, String variables[], Object substitutes[]) throws Exception {
        if(compiledExpressions == null) {
            return null;
        }
        ExpressionEvaluator evaluator = (ExpressionEvaluator) compiledExpressions.get(key);
        if(evaluator == null) {
            System.out.println("Adding janino evaluator for key :: " + key);
            evaluator = new ExpressionEvaluator();
            evaluator.setParameters(variables, new Class[] { int.class, int.class });
            evaluator.cook(expression);
            compiledExpressions.put(key, evaluator);
        }
        return evaluator.evaluate(substitutes);
    }
}
