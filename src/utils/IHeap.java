package utils;
import java.util.*;

public interface IHeap<Value> {
    int add(Value value);

    Value getValue(Integer key) throws HeapException;

    boolean has(Integer key);

    void setValue(Integer key, Value value) throws HeapException;

    Map<Integer, Value> getContent();

    void setContent(Map<Integer, Value> newHeap);
}
