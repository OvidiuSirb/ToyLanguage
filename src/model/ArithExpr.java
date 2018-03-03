package model;
import utils.IHeap;
import utils.ISymTable;

public class ArithExpr implements Expr {
    private char operator;
    private VarExpr operand1;
    private Expr operand2;

    public ArithExpr(char op, VarExpr op1, Expr op2) {
        operator = op;
        operand1 = op1;
        operand2 = op2;
    }

    @Override
    public int evaluate(ISymTable<String,Integer> s, IHeap<Integer> heap) {
        int resultFirst = operand1.evaluate(s,heap), resultSecond = operand2.evaluate(s,heap);
        switch(operator) {
            case '+': return resultFirst + resultSecond;
            case '-': return resultFirst - resultSecond;
            case '*': return resultFirst * resultSecond;
            case '/':
                if(resultSecond == 0) {
                    throw new ArithmeticException("cannot divide by 0");
                } else {
                    return resultFirst / resultSecond;
                }
            default: throw new RuntimeException("runtime exception");
        }
    }

    public String getName(){
        return this.operand1.getName();}

    @Override
    public String toString() {
        return " " + operand1 + operator + operand2; //empty space automatically calls toString
    }
}
