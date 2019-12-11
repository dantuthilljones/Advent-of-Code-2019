package Day03;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SolutionPart1 {

    private static Set<Point> getPoints(List<String> directions) {
        Set<Point> points = new HashSet<>();
        Point currentPoint = new Point(0, 0);
        for(String direction : directions) {
            currentPoint = addPointsInDirection(points, currentPoint, direction);
        }
        return points;
    }

    private static Point addPointsInDirection(Set<Point> points, Point startingPoint, String direction) {
        int magnitude = Integer.parseInt(direction.substring(1));
        if (direction.startsWith("R")) {
            for(int i = 1; i <= magnitude; i++) {
                points.add(startingPoint.right(i));
            }
            return startingPoint.right(magnitude);
        } else if (direction.startsWith("L")) {
            for(int i = 1; i <= magnitude; i++) {
                points.add(startingPoint.left(i));
            }
            return startingPoint.left(magnitude);
        } else if (direction.startsWith("U")) {
            for(int i = 1; i <= magnitude; i++) {
                points.add(startingPoint.up(i));
            }
            return startingPoint.up(magnitude);
        } else if (direction.startsWith("D")) {
            for(int i = 1; i <= magnitude; i++) {
                points.add(startingPoint.down(i));
            }
            return startingPoint.down(magnitude);
        }
        throw new RuntimeException("Invalid direction: " + direction);
    }

    @Test
    public void testPart1() throws IOException {
        List<String> wires = Files.readAllLines(Paths.get("input/Day03/wires.txt"));
        List<String> directionsWire1 = Arrays.asList(wires.get(0).split(","));
        List<String> directionsWire2 = Arrays.asList(wires.get(1).split(","));

        Set<Point> wire1 = getPoints(directionsWire1);
        Set<Point> wire2 = getPoints(directionsWire2);

        Set<Point> overlaps = Sets.intersection(wire1, wire2);

        Optional<Point> minDistance = overlaps.stream().min(Comparator.comparingInt(Point::manhattanDistanceFromOrigin));

        System.out.println("The solution to day 3 part 1 is:");
        System.out.println(minDistance.get().manhattanDistanceFromOrigin());
    }
}
