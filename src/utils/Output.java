package utils;

import java.util.ArrayList;
import java.util.Iterator;

public class Output<T> implements IOutput<T> {
    private ArrayList<T> list = new ArrayList<T>();

    @Override
    public Iterable<T> getContent() {return list;}
    @Override
    public void add(T el) {
        list.add(el);
    }
    @Override
    public String toString(){
        if(list.isEmpty()){
            return "Empty output";
        }
        else
        {
            Iterator iter = list.iterator();
            StringBuilder str = new StringBuilder();
            while (iter.hasNext()){
                str.append(iter.next().toString());
                if(iter.hasNext())
                    str.append(",");
            }
            return str.toString();
        }
    }
}
