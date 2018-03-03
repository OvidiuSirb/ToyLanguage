package model;

import utils.IHeap;
import utils.ISymTable;

public class NewHeap implements Stmt {
    private String varName;
    private Expr expression;

    public NewHeap(String givenVarName, Expr givenExpression) {
        varName = givenVarName;
        expression = givenExpression;
    }

    public PrgState execute(PrgState state) {
        ISymTable<String, Integer> symTbl = state.getSymTbl();
        IHeap<Integer> heap = state.getHeap();

        int address = heap.add(expression.evaluate(symTbl,state.getHeap()));
        symTbl.add(varName, address);

        return null;
    }

    @Override
    public String toString() {
        return "new(".concat(varName).concat(",").concat(expression.toString()).concat(")");
    }
}

