package utils;


import java.util.HashMap;
import java.util.Map;

public class FileTable<Key, Value> implements IFileTable<Key, Value> {
    private HashMap<Key, Value> table;

    public FileTable() {
        table = new HashMap<>();
    }

    public void add(Key key, Value value) {
        if (!table.containsKey(key)) {
            table.put(key, value);
        } else
            setValue(key, value);
    }

    public Value getValue(Key key) throws FileTableException {
        Value value = table.get(key);
        if (value == null)
            throw new FileTableException("key does not exist");
        return value;
    }

    public boolean has(Key key) {
        return table.containsKey(key);
    }

    public void setValue(Key key, Value value) {
        if (table.containsKey(key))
            table.put(key, value);
        else
            throw new FileTableException("No value with key" + key + " !!");
    }

    public Map<Key, Value> getContent(){
        return table;
    }

    public int size() {
        return table.size();
    }

    public void remove(Key key) {
        table.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("File Table:\n");
        for (Key key : table.keySet())
            buffer.append(key).append("-->").append(this.getValue(key)).append("\n");
        return buffer.toString();
    }
}
