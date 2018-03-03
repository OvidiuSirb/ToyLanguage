package model;

import utils.IExeStack;

public class CompStmt implements Stmt {
    private Stmt first;
    private Stmt second;

    public CompStmt(Stmt f, Stmt s) {
        first = f;
        second = s;
    }

    @Override
    public PrgState execute(PrgState p) {
        IExeStack<Stmt> exe = p.getStack();
        exe.push(second);
        exe.push(first);
        return null;
    }

    @Override
    public String toString() {
        return "{" + first + ";" + second + "}";
    }
}
