package utils;

import java.util.HashMap;
import java.util.Map;

public class Heap<Value> implements IHeap<Value> {
    private HashMap<Integer, Value> heap;
    private Integer free = 1;

    public Heap() {
        heap = new HashMap<>();
    }

    public int add(Value value) {
        if (!heap.containsKey(free)) {
            heap.put( free, value);
            return free++;
        } else {
            setValue( free, value);
            return free;
        }
    }

    public boolean has(Integer key) {
        return heap.containsKey(key);
    }

    public void setValue(Integer key, Value value) {
        if (heap.containsKey(key))
            heap.put(key, value);
        else
            throw new HeapException("No value with key " + key + " !!");
    }

    public Value getValue(Integer key) throws HeapException {
        Value value = heap.get(key);
        if (value == null)
            throw new HeapException("Key " + key + " does not exist!!");
        return value;
    }

    public Map<Integer, Value> getContent() {
        return heap;
    }

    public void setContent(Map<Integer, Value> newHeap) {
        heap = (HashMap<Integer, Value>) newHeap;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Heap:\n");
        for (Integer key : heap.keySet())
            buffer.append(key).append("-->").append(this.getValue(key)).append("\n");
        return buffer.toString();
    }
}
