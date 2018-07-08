package main.java.com.ifttt.utils;

import main.java.com.ifttt.enums.OperationTypeEnum;
import main.java.com.ifttt.enums.OperatorEnum;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * @author praveenkamath
 **/
public class Evaluator {

    public static final Object eval(OperatorEnum operator, Object actual, Object expected) throws Exception {
        if(operator.getType() == OperationTypeEnum.STRING) {
            return StringOperationUtils.eval(operator, actual, expected);
        }
        if(operator.getType() == OperationTypeEnum.MATH) {
            Expression exp = new ExpressionBuilder(actual + operator.getExp() + expected)
                    .operator(MathOperationUtils.getOperation(operator))
                    .build();
            return exp.evaluate() == 1 ? true : false;
        } throw new Exception("Unsupported operator!");
    }
}
