package model;

import utils.IHeap;
import utils.ISymTable;

public class HeapRead implements Expr{
    private String varName;

    public HeapRead(String givenVarName) {
        varName = givenVarName;
    }

    @Override
    public int evaluate(ISymTable<String,Integer> table, IHeap<Integer> heap) {
        if (table.has(varName))
            if (heap.has(table.getValue(varName)))
                return heap.getValue(table.getValue(varName));
            else
                throw new RuntimeException("No address" + table.getValue(varName) + "on heap");
        else
            throw new RuntimeException("No key" + varName + "in symbol table");
    }

    @Override
    public String toString() {
        return "readHeap(".concat(varName).concat(")");
    }

    @Override
    public String getName(){return "";}
}
