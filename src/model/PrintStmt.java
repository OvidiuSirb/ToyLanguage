package model;

import utils.IOutput;

public class PrintStmt implements Stmt {
    private Expr v;
    public PrintStmt(Expr v){ this.v = v;}

    public PrgState execute(PrgState p){
        IOutput<Integer> t = p.getOutput();
        t.add(v.evaluate(p.getSymTbl(),p.getHeap()));
        return null;
    }
    public String toString(){
        return "Print(" +
                this.v.toString() +
                ")";
    }
}
