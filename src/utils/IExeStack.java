package utils;

import java.util.Stack;
import model.Stmt;

public interface IExeStack<Stmt> {
    void push(Stmt s);
    Stmt pop();
    boolean isEmpty();
    Iterable<Stmt> getContent();
}

