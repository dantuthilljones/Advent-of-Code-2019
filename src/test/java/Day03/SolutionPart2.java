package Day03;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Streams;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SolutionPart2 {

    private static Set<PointAndDistance> getPoints(List<String> directions) {
        Set<PointAndDistance> points = new HashSet<>();
        PointAndDistance currentPoint = new PointAndDistance(0, 0, 0);
        for (String direction : directions) {
            currentPoint = addPointsInDirection(points, currentPoint, direction);
        }
        return points;
    }

    private static PointAndDistance addPointsInDirection(Set<PointAndDistance> points, PointAndDistance startingPoint, String direction) {
        int magnitude = Integer.parseInt(direction.substring(1));
        if (direction.startsWith("R")) {
            for (int i = 1; i <= magnitude; i++) {
                points.add(startingPoint.right(i));
            }
            return startingPoint.right(magnitude);
        } else if (direction.startsWith("L")) {
            for (int i = 1; i <= magnitude; i++) {
                points.add(startingPoint.left(i));
            }
            return startingPoint.left(magnitude);
        } else if (direction.startsWith("U")) {
            for (int i = 1; i <= magnitude; i++) {
                points.add(startingPoint.up(i));
            }
            return startingPoint.up(magnitude);
        } else if (direction.startsWith("D")) {
            for (int i = 1; i <= magnitude; i++) {
                points.add(startingPoint.down(i));
            }
            return startingPoint.down(magnitude);
        }
        throw new RuntimeException("Invalid direction: " + direction);
    }

    @Test
    public void testPart2() throws IOException {
        List<String> wires = Files.readAllLines(Paths.get("input/Day03/wires.txt"));
        List<String> directionsWire1 = Arrays.asList(wires.get(0).split(","));
        List<String> directionsWire2 = Arrays.asList(wires.get(1).split(","));

        Set<PointAndDistance> wire1 = getPoints(directionsWire1);
        Set<PointAndDistance> wire2 = getPoints(directionsWire2);

        Map<Point, List<PointAndDistance>> allPoints = new HashMap<>();
        Streams.concat(wire1.stream(), wire2.stream())
                .forEach(pointWithDistance -> allPoints.compute(
                        pointWithDistance.getPoint(),
                        (point, list) -> {
                            if (list == null) {
                                return Lists.newArrayList(pointWithDistance);
                            } else {
                                list.add(pointWithDistance);
                                return list;
                            }
                        })
                );

        Set<Point> wire1Points = wire1.stream().map(PointAndDistance::getPoint).collect(Collectors.toSet());
        Set<Point> wire2Points = wire2.stream().map(PointAndDistance::getPoint).collect(Collectors.toSet());

        Set<Point> overlaps = Sets.intersection(
                wire1Points,
                wire2Points);

        Optional<Point> minPoint = overlaps.stream().min(
                Comparator.comparingInt(point -> {
                    List<PointAndDistance> pointsAtOverlap = allPoints.get(point);
                    return pointsAtOverlap.get(0).getDistance() + pointsAtOverlap.get(1).getDistance();
                })
        );

        List<PointAndDistance> pointsAtOverlap = allPoints.get(minPoint.get());
        int minDistance = pointsAtOverlap.get(0).getDistance() + pointsAtOverlap.get(1).getDistance();

        System.out.println("The solution to day 3 part 2 is:");
        System.out.println(minDistance);
    }
}
