package utils;

import utils.FileTableException;

import java.util.Map;

public interface IFileTable<Key, Value> {
    void add(Key key, Value value);

    Value getValue(Key key) throws FileTableException;

    boolean has(Key key);

    void setValue(Key key, Value value);

    int size();

    void remove(Key key);

    Map<Key, Value> getContent();
}
