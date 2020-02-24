package painting;

import util.DynamicList;

import java.util.List;
import java.util.function.Supplier;

public class PaintableHull {

    private final List<List<Character>> hull;

    private int width;
    private int height;

    public PaintableHull() {
        Supplier<Character> charSupplier = () -> '.';
        Supplier<List<Character>> listSupplier = () -> new DynamicList<>(charSupplier);
        hull = new DynamicList<>(listSupplier);

        width = height = 0;
    }

    public char get(int x, int y) {
        updateSize(x, y);
        return hull.get(y).get(x);
    }

    public char set(int x, int y, char c) {
        updateSize(x, y);
        return hull.get(y).set(x, c);
    }

    private void updateSize(int x, int y) {
        width = Math.max(width, x+1);
        height = Math.max(height, y+1);
    }

    public void draw() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                System.out.print(get(x, y));
            }
            System.out.println();
        }
    }
}
