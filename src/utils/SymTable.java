package utils;

import java.util.HashMap;
import java.util.Map;


public class SymTable<Key, Value> implements ISymTable<Key, Value> {

    private HashMap<Key, Value> table;

    public SymTable() {
        table = new HashMap<>();
    }

    public SymTable(Map<Key, Value> givenMap) {
        table = new HashMap<>(givenMap);
    }

    public void add(Key key, Value value) {
        if (!table.containsKey(key)) {
            table.put(key, value);
        } else
            setValue(key, value);
    }

    public Value getValue(Key key) throws SymbolTableException {
        Value value = table.get(key);
        if (value == null)
            throw new SymbolTableException("key" + key + "does not exist");
        return value;
    }

    public boolean has(Key key) {
        return table.containsKey(key);
    }

    public void setValue(Key key, Value value) {
        if (table.containsKey(key))
            table.put(key, value);
        else
            throw new SymbolTableException("No value with key" + key + " !!");
    }

    public int size() {
        return table.size();
    }

    public Map<Key, Value> getContent() {
        return table;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Symbol Table:\n");
        for (Key key : table.keySet())
            buffer.append(key).append("-->").append(this.getValue(key)).append("\n");
        return buffer.toString();
    }
}
