package model;

import utils.ISymTable;

public class AssignStmt implements Stmt {
    private String var;
    private Expr exp;

    public AssignStmt(String v, Expr e) {
        var = v;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState p) {
        ISymTable<String,Integer> t = p.getSymTbl();            //todo last modifications
        int result = exp.evaluate(t,p.getHeap());
        t.add(var, result);
        return null;
    }

    @Override
    public String toString() {
        return " " + var + "=" + exp;
    }

}

