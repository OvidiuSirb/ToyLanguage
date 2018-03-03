package model;

import utils.ExeStack;
import utils.ISymTable;
import utils.SymTable;

public class ForkStmt implements Stmt{                                      //todo nu am facut modificari la stmt
    private Stmt statement;
    public ForkStmt(Stmt givenStatement){statement = givenStatement;}

    public PrgState execute(PrgState state) {
        ISymTable<String, Integer> newSymTable;
        newSymTable = new SymTable<>(state.getSymTbl().getContent());
        return new PrgState(
                new ExeStack<>(),
                newSymTable,
                state.getFileTable(),
                state.getHeap(),
                state.getOutput(),
                statement,
                state.getChild()
        );
    }

    @Override
    public String toString() {
        return "fork(".concat(statement.toString()).concat(")");
    }
}
