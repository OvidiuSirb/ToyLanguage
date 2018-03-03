package utils;

import model.Stmt;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

public class ExeStack<T> implements IExeStack<T> {
    private Stack<T> stack = new Stack<>();
    public ExeStack() {
        stack = new Stack<>();
    }
    public void push(T el){
        stack.push(el);
    }
    public T pop(){ return stack.pop();}
    public boolean isEmpty(){return stack.empty();}

    @Override
    public Iterable<T> getContent(){return stack;}

    @Override
    public String toString(){
        StringBuilder buffer = new StringBuilder();
        buffer.append("Executable Stack:\n");

        ListIterator it = stack.listIterator(stack.size());
        while (it.hasPrevious()) {
            buffer.append(it.previous()).append("\n");
        }

        return buffer.toString();

        /*
        if(stack.isEmpty()){
            return "Empty stack";
        }
        else
        {
            Iterator iter = stack.iterator();
            StringBuilder str = new StringBuilder();
            while (iter.hasNext()){
                str.append(iter.next().toString());
                str.append(";");
            }
            return str.toString();
        }
        */
    }
}
