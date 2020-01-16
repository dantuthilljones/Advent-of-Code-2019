package Day10;

import asteroids.Asteroid;
import asteroids.AsteroidMap;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;

public class SolutionPart1 {

    @Test
    public void testSolutionPart1() throws IOException {
        AsteroidMap map = AsteroidMap.fromFile("input/Day10/map.txt");

        Asteroid best = map.getAsteroids()
                .stream()
                .max(Comparator.comparingInt(map::calculateVisibleAsteroidsFrom))
                .get();

        System.out.println("The solution to day 10 part 1 is:");
        System.out.println(map.calculateVisibleAsteroidsFrom(best));

    }

    @Test
    public void testExample1() throws IOException {
        AsteroidMap map = AsteroidMap.fromFile("input/Day10/example1.txt");

        Asteroid best = map.getAsteroids()
                .stream()
                .max(Comparator.comparingInt(map::calculateVisibleAsteroidsFrom))
                .get();

        Assert.assertEquals(5, best.getX());
        Assert.assertEquals(8, best.getY());
        Assert.assertEquals(33, map.calculateVisibleAsteroidsFrom(best));
    }

    @Test
    public void testIsVisibleFrom() throws IOException {
        AsteroidMap map = AsteroidMap.fromFile("input/Day10/example1.txt");

        //visible along x axis
        Assert.assertTrue(map.isVisible(new Asteroid(6, 0), new Asteroid(8, 0)));
        Assert.assertTrue(map.isVisible(new Asteroid(8, 0), new Asteroid(6, 0)));

        //visible along y axis
        Assert.assertTrue(map.isVisible(new Asteroid(0, 1), new Asteroid(0, 6)));
        Assert.assertTrue(map.isVisible(new Asteroid(0, 6), new Asteroid(0, 1)));

        //not visible along x axis
        Assert.assertFalse(map.isVisible(new Asteroid(0, 1), new Asteroid(5, 1)));
        Assert.assertFalse(map.isVisible(new Asteroid(5, 1), new Asteroid(0, 1)));

        //not visible along y axis
        Assert.assertFalse(map.isVisible(new Asteroid(0, 1), new Asteroid(0, 8)));
        Assert.assertFalse(map.isVisible(new Asteroid(0, 8), new Asteroid(0, 1)));

        //visible along angle
        Assert.assertTrue(map.isVisible(new Asteroid(6, 0), new Asteroid(0, 1)));
        Assert.assertTrue(map.isVisible(new Asteroid(8, 0), new Asteroid(6, 0)));

        //not visible along angle
        Assert.assertFalse(map.isVisible(new Asteroid(0, 1), new Asteroid(2, 5)));
        Assert.assertFalse(map.isVisible(new Asteroid(2, 5), new Asteroid(0, 1)));
    }
}
