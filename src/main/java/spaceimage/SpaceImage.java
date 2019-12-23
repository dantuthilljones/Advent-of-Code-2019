package spaceimage;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpaceImage {

    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public static final int TRANSPARENT = 2;


    private final List<Layer> imageData;

    public SpaceImage(List<Integer> input, int width, int height) {
        int pixelsPerLayer = width*height;

        imageData = Lists.partition(input, pixelsPerLayer)
                .stream()
                .map(layer -> Layer.fromList(width, height, layer))
                .collect(Collectors.toList());
    }

    public Layer getLayer(int num) {
        return imageData.get(num);
    }

    public List<Layer> getLayers() {
        return imageData;
    }

    public int colourAt(int x, int y) {
        for(Layer layer : imageData) {
            int colour = layer.getPixel(x, y);
            if(colour == BLACK) {
                return BLACK;
            } else if(colour == WHITE) {
                return WHITE;
            } else if(colour == TRANSPARENT) {
                continue;
            }
            throw new RuntimeException("Unknown colour " + colour);
        }
        return 2;
    }

}
