package utils;
import java.util.*;

public interface IOutput<T> {
    void add(T x);

    Iterable<T> getContent();
}

