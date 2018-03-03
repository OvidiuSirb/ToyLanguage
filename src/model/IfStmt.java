package model;

import utils.IExeStack;

public class IfStmt implements Stmt {
    private Expr e;
    private Stmt elseS;
    private Stmt ifS;

    public IfStmt(Expr e, Stmt exp, Stmt exp2) {
        this.e = e;
        this.ifS = exp;
        this.elseS = exp2;
    }

    @Override
    public PrgState execute(PrgState p) {
        IExeStack<Stmt> exe = p.getStack();
        if (e.evaluate(p.getSymTbl(),p.getHeap()) == 0) {
            exe.push(elseS);
        } else {
            exe.push(ifS);
        }
        return null;
    }

    @Override
    public String toString() {
        return "If "+ e.toString()+" then "+ ifS.toString()+" else "+ elseS.toString();
    }
}
