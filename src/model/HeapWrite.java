package model;

import utils.IHeap;
import utils.ISymTable;

public class HeapWrite implements Stmt{
    private String varName;
    private Expr expression;

    public HeapWrite(String givenVarName, Expr givenExpression) {
        varName = givenVarName;
        expression = givenExpression;
    }

    @Override
    public PrgState execute(PrgState state) {
        ISymTable<String, Integer> symTbl = state.getSymTbl();
        IHeap<Integer> heap = state.getHeap();

        if (symTbl.has(varName))
            if (heap.has(symTbl.getValue(varName)))
                heap.setValue(symTbl.getValue(varName), expression.evaluate(symTbl, heap));
            else
                throw new RuntimeException("No address" + symTbl.getValue(varName) + "on heap");
        else
            throw new RuntimeException("No key" + varName + "in symbol table");

        return null;
    }

    @Override
    public String toString() {
        return "writeHeap(".concat(varName).concat(",").concat(expression.toString()).concat(")");
    }
}
