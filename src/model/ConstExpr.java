package model;

import utils.IHeap;
import utils.ISymTable;

public class ConstExpr implements Expr {
    private int value;

    public ConstExpr(int value){
        this.value = value;
    }

    public int getValue(){return this.value;}

    @Override
    public int evaluate(ISymTable<String,Integer> s, IHeap< Integer> heap){
        return value;
    }
    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public String getName(){return "";}
}
