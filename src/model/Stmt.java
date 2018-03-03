package model;
import model.Stmt;
import utils.IExeStack;

public interface Stmt {
    PrgState execute(PrgState p);
}

