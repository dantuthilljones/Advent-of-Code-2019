package Day10;

import asteroids.Asteroid;
import asteroids.AsteroidMap;
import com.google.common.math.DoubleMath;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionPart2 {

    @Test
    public void testSolutionPart2() throws IOException {
        AsteroidMap map = AsteroidMap.fromFile("input/Day10/map.txt");

        Asteroid station = calulateBestAsteroid(map);

        Asteroid asteroid = getNthDestroyedAsteroid(map, station,200);

        System.out.println("The solution to day 10 part 2 is:");
        System.out.println(asteroid.getX()*100 + asteroid.getY());

    }

    @Test
    public void testExample1() throws IOException {
        AsteroidMap map = AsteroidMap.fromFile("input/Day10/example4.txt");//example 1 in part 2 is the same as example 4 in part 1

        Asteroid station = calulateBestAsteroid(map);
        System.out.println("Station: " + station);

        Asteroid asteroid = getNthDestroyedAsteroid(map, station,200);

        Assert.assertEquals(new Asteroid(8, 2), asteroid);

    }

    private static Asteroid calulateBestAsteroid(AsteroidMap map) {
        return map.getAsteroids()
                .stream()
                .max(Comparator.comparingInt(map::calculateVisibleAsteroidsFrom))
                .get();
    }

    private static Asteroid getNthDestroyedAsteroid(AsteroidMap map, Asteroid station, int n) {
        List<Asteroid> visibleAsteroidsInShootingOrder = visibleAsteroidsInShootingOrder(station, map);

        int target = n;

        while(visibleAsteroidsInShootingOrder.size() < target) {
            target -= visibleAsteroidsInShootingOrder.size();
            map.remove(visibleAsteroidsInShootingOrder);
            visibleAsteroidsInShootingOrder = visibleAsteroidsInShootingOrder(station, map);
        }

        return visibleAsteroidsInShootingOrder.get(target-1);
    }

    private static List<Asteroid> visibleAsteroidsInShootingOrder(Asteroid station, AsteroidMap map) {
        return map.getAsteroids()
                .stream()
                .filter(asteroid -> map.isVisible(station, asteroid))
                .sorted(Comparator.comparingDouble(asteroid -> -angleBetween(station, asteroid)))
                .collect(Collectors.toList());
    }

    private static double angleBetween(Asteroid station, Asteroid asteroid) {
        double opposite = asteroid.getX() - station.getX();
        double adjacent = station.getY() - asteroid.getY();
        double angle = Math.atan2(opposite, adjacent);

        //convert from -Pi -> Pi range to 0 -> 2Pi
        if(angle < 0) {
            angle += Math.PI * 2;
        }

        return angle;
    }

    @Test
    public void testAngleBetween() {
        double angle = angleBetween(new Asteroid(10,10), new Asteroid(11, 9));
        Assert.assertTrue(DoubleMath.fuzzyEquals(Math.toRadians(45), angle, 0.1));

        angle = angleBetween(new Asteroid(10,10), new Asteroid(11, 10));
        Assert.assertTrue(DoubleMath.fuzzyEquals(Math.toRadians(90), angle, 0.1));

        angle = angleBetween(new Asteroid(10,10), new Asteroid(9, 9));
        Assert.assertTrue(DoubleMath.fuzzyEquals(Math.toRadians(315), angle, 0.1));
    }
}
