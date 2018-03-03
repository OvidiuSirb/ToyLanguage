package utils;
import java.util.*;

public interface ISymTable<Key, Value>{
    void add(Key key, Value value);

    Value getValue(Key key) throws SymbolTableException;

    boolean has(Key key);

    void setValue(Key key, Value value);

    int size();

    Map<Key, Value> getContent();
}


