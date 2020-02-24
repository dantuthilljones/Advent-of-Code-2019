package util;

import java.util.ArrayList;
import java.util.function.Supplier;

public class DynamicList<T> extends ArrayList<T> {

    private final Supplier<T> defaultSupplier;

    public DynamicList(Supplier<T> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
    }

    @Override
    public T get(int position) {
        fillUntil(position);
        return super.get(position);
    }

    @Override
    public T set(int position, T value) {
        fillUntil(position);
        return super.set(position, value);
    }

    public void fillUntil(int position) {
        while (position >= size()) {
            add(defaultSupplier.get());
        }
    }
}
