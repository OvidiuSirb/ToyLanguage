package model;

public class acquire implements Stmt {
    private VarExpr var;
    public acquire(VarExpr var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer foundIndex = p.getSymTbl().getValue(var.getName());
        p.getSem().add(foundIndex);
        return null;
    }
}
