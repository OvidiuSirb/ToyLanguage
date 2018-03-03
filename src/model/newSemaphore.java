package model;

import utils.IHeap;
import utils.ISymTable;
import utils.SemaphoreTable;

public class newSemaphore implements Stmt{
    private Expr var;
    private Expr exp;

    public newSemaphore(Expr var,Expr exp){
        this.var = var;
        this.exp = exp;
    }


    @Override
    public PrgState execute(PrgState state) {
        ISymTable<String, Integer> symTbl = state.getSymTbl();
        SemaphoreTable<Integer> sem = state.getSem();

        int address = sem.add(exp.evaluate(symTbl,state.getHeap()));
        symTbl.add(var.getName(), address);
        return null;
    }
}
