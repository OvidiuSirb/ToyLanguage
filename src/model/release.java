package model;

public class release implements Stmt {
    private VarExpr var;
    public release(VarExpr var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer foundIndex = p.getSymTbl().getValue(var.getName());
        return null;
    }
}
