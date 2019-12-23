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

public class SolutionPart2 {

    @Test
    public void testPart1() throws IOException {
        String imageString = Files.readString(Paths.get("input/Day08/image.txt"));
        List<Integer> input = Arrays.stream(imageString.split("")).map(Integer::parseInt).collect(Collectors.toList());

        int width = 25;
        int height = 6;

        SpaceImage image = new SpaceImage(input, width, height);

        System.out.println("The solution to day 8 part 1 is:");
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                int colour = image.colourAt(col, row);
                if(colour == SpaceImage.BLACK) {
                    System.out.print("\u25A0");
                } else if(colour == SpaceImage.WHITE) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
