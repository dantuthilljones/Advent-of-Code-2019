package painting;

import util.DynamicList;

import java.util.List;
import java.util.function.Supplier;

public class PaintableHull {

    private final static char BLACK_CHAR = '.';
    private final static char WHITE_CHAR = '#';

    public final static int BLACK_INT = 0;
    public final static int WHITE_INT = 1;

    private final List<List<Integer>> hull;
    private final List<List<Boolean>> painted;

    private int paintedNum;

    private int width;
    private int height;

    public PaintableHull() {
        Supplier<Integer> supplier = () -> 0;
        Supplier<List<Integer>> listSupplier = () -> new DynamicList<>(supplier);
        hull = new DynamicList<>(listSupplier);

        Supplier<Boolean> supplierBools = () -> false;
        Supplier<List<Boolean>> listSupplierBools = () -> new DynamicList<>(supplierBools);
        painted = new DynamicList<>(listSupplierBools);

        width = height = paintedNum = 0;
    }

    public int get(int x, int y) {
        updateSize(x, y);
        return hull.get(y).get(x);
    }

    public int set(int x, int y, int c) {
        updateSize(x, y);

        if (!painted.get(y).get(x)) {
            painted.get(y).set(x, true);
            paintedNum ++;
        }

        return hull.get(y).set(x, c);
    }

    public int getPaintedNum() {
        return paintedNum;
    }

    private void updateSize(int x, int y) {
        width = Math.max(width, x+1);
        height = Math.max(height, y+1);
    }

    public void draw() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                char c = getChar(get(x, y));
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static char getChar(int i) {
        if (i == BLACK_INT) {
            return BLACK_CHAR;
        } else if (i == WHITE_INT) {
            return WHITE_CHAR;
        } else {
            throw new IllegalArgumentException("Unknown value: " + i);
        }
    }
}
