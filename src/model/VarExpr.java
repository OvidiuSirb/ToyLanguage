package model;

import utils.IHeap;
import utils.ISymTable;

public class VarExpr implements Expr {
    private String name;

    public VarExpr(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(ISymTable<String,Integer> table, IHeap<Integer> heap) {
        if (table.has(name))
            return  table.getValue(name);
        else
            throw new RuntimeException("No \"".concat(name).concat("\" variable exists!"));
    }

    public String getName(){return this.name;}

    @Override
    public String toString() {
        return name;
    }
}