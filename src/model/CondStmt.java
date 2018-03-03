package model;

public class CondStmt implements Stmt{
    private Expr exp1;
    private Expr exp2;
    private Expr exp3;
    public CondStmt(Expr exp1,Expr exp2,Expr exp3){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public PrgState execute(PrgState p){
        Integer e1;
        if (exp1 instanceof VarExpr)
            e1 = p.getSymTbl().getValue(exp1.getName());
        else
            e1 = exp1.evaluate(p.getSymTbl(),p.getHeap());
        p.getStack().push(new IfStmt(new ConstExpr(e1),new AssignStmt("v",exp2),new AssignStmt("v",exp3)));

        return null;
    }

    @Override
    public String toString() {
        return "v=".concat(exp1.toString()).concat("?").concat(exp2.toString()).concat(":").concat((exp3.toString()));
    }
}
