package model;
import utils.*;

public interface Expr {
    int evaluate(ISymTable<String,Integer> s,IHeap<Integer> heap);
    public String getName();
}
