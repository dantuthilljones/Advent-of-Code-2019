package util;

import java.util.ArrayDeque;
import java.util.Queue;

public class Utils {

    public static <T> Queue<T> queue(T... inputs) {
        Queue<T> result = new ArrayDeque<>();
        for(T t : inputs) {
            result.add(t);
        }
        return result;
    }

}
