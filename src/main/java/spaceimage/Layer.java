package spaceimage;

import com.google.common.collect.Lists;

import java.util.List;

public class Layer {

    private final List<List<Integer>> rows;

    public Layer(List<List<Integer>> rows) {
        this.rows = rows;
    }

    public List<List<Integer>> getRows() {
        return rows;
    }

    public List<Integer> getRow(int row) {
        return rows.get(row);
    }

    public static Layer fromList(int width, int height, List<Integer> input) {
        return new Layer(Lists.partition(input, width));
    }

    public int getPixel(int x, int y) {
        return rows.get(y).get(x);
    }
}
