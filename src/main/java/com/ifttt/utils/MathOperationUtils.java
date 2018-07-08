package com.ifttt.utils;

import com.ifttt.enums.OperatorEnum;
import net.objecthunter.exp4j.operator.Operator;

/**
 *
 *
 * @author praveenkamath
 **/
public class MathOperationUtils {

    public static Operator getOperation(OperatorEnum operation) {
        switch (operation.getExp()) {
            case ">=": {
                return getGTE();
            }
            case ">": {
                return getGT();
            }
            case "<=": {
                return getLTE();
            }
            case "<": {
                return getLT();
            }
            case "==": {
                return getEQ();
            }
            default: {
                return null;
            }
        }
    }

    private static Operator getEQ() {
        return new Operator("==", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

            @Override
            public double apply(double[] values) {
                if (values[0] == values[1]) {
                    return 1d;
                } else {
                    return 0d;
                }
            }
        };
    }

    private static Operator getLT() {
        return new Operator("<", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

            @Override
            public double apply(double[] values) {
                if (values[0] < values[1]) {
                    return 1d;
                } else {
                    return 0d;
                }
            }
        };
    }

    private static Operator getLTE() {
        return new Operator("<=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

            @Override
            public double apply(double[] values) {
                if (values[0] <= values[1]) {
                    return 1d;
                } else {
                    return 0d;
                }
            }
        };
    }

    private static Operator getGT() {
        return new Operator(">", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

            @Override
            public double apply(double[] values) {
                if (values[0] > values[1]) {
                    return 1d;
                } else {
                    return 0d;
                }
            }
        };
    }

    private static Operator getGTE() {
        return new Operator(">=", 2, true, Operator.PRECEDENCE_ADDITION - 1) {

            @Override
            public double apply(double[] values) {
                if (values[0] >= values[1]) {
                    return 1d;
                } else {
                    return 0d;
                }
            }
        };
    }
}
