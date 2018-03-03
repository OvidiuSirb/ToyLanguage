package model;

import utils.IHeap;
import utils.ISymTable;

public class BooleanExpr implements Expr{
    private String operator;
    private Expr operandA;
    private Expr operandB;

    public BooleanExpr(String givenOperator, Expr givenOperandA, Expr givenOperandB) {
        operator = givenOperator;
        operandA = givenOperandA;
        operandB = givenOperandB;
    }

    public int evaluate(ISymTable<String, Integer> table, IHeap<Integer> heap) {
        int resultFirst = operandA.evaluate(table, heap);
        int resultSecond = operandB.evaluate(table, heap);

        switch (operator) {
            case "==":
                return conv(resultFirst==resultSecond);
            case "!=":
                return resultFirst!=resultSecond ? 1:0;
            case ">=":
                return resultFirst>=resultSecond ? 1:0;
            case "<=":
                return resultFirst<=resultSecond ? 1:0;
            case ">":
                return resultFirst>resultSecond ? 1:0;
            case "<":
                return resultFirst<resultSecond ? 1:0;
            default:
                throw new RuntimeException("Invalid operator!");
        }
    }

    private int conv(boolean b) {
        return b ? 1: 0;
    }



    @Override
    public String toString() {
        return operandA.toString().concat(" ").concat(operator).concat(" ").concat(operandB.toString());


    }
    @Override
    public String getName(){return "";}
}
