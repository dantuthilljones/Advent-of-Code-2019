package Day08;

import org.junit.Test;
import spaceimage.Layer;
import spaceimage.SpaceImage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionPart1 {

    @Test
    public void testPart1() throws IOException {
        String imageString = Files.readString(Paths.get("input/Day08/image.txt"));
        List<Integer> input = Arrays.stream(imageString.split("")).map(Integer::parseInt).collect(Collectors.toList());

        int width = 25;
        int height = 6;

        SpaceImage image = new SpaceImage(input, width, height);

        Layer layerWithLeast0s = image.getLayers()
                .stream()
                .min(Comparator.comparingInt(layer -> numberOf(0, layer)))
                .get();

        int numberOfOnes = numberOf(1, layerWithLeast0s);
        int numberOfTwos = numberOf(2, layerWithLeast0s);

        int answer = numberOfOnes * numberOfTwos;

        System.out.println("The solution to day 8 part 1 is:");
        System.out.println(answer);
    }

    private static List<Integer> getLayer(int layer, List<Integer> input, int width, int height, int digitsPerPixel) {
        return input.subList(layer * height * digitsPerPixel, layer * height * digitsPerPixel + width * digitsPerPixel);
    }

    private static int numberOf(int number, Layer layer) {
        return (int) layer.getRows()
                .stream()
                .flatMap(List::stream)
                .filter(n -> n == number)
                .count();
    }
}
