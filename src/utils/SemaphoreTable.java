package utils;

import java.util.HashMap;
import java.util.Map;

public class SemaphoreTable<Value> {
    private HashMap<Integer, Value> semT;
    private Integer free = 1;

    public SemaphoreTable() {
        semT = new HashMap<>();
    }

    public int add(Value value) {
        if (!semT.containsKey(free)) {
            semT.put( free, value);
            return free++;
        } else {
            setValue( free, value);
            return free;
        }
    }
    public void setValue(Integer key, Value value) {
        if (semT.containsKey(key))
            semT.put(key, value);
        else
            throw new HeapException("No value with key " + key + " !!");
    }

}
