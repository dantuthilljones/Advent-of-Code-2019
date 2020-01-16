package asteroids;

import com.google.common.math.IntMath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AsteroidMap {

    private final List<List<Boolean>> map;
    private List<Asteroid> asteroids;

    private AsteroidMap(List<List<Boolean>> map) {
        this.map = map;
        this.asteroids = calculateAsteroids();
    }

    public static AsteroidMap fromFile(String fileName) throws IOException {
        String intcodes = Files.readString(Paths.get(fileName));

        List<List<Boolean>> map = Arrays.stream(intcodes.split("\\r?\\n"))
                .map(line -> Arrays.stream(line.split(""))
                        .map(AsteroidMap::isAsteroid)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        return new AsteroidMap(map);
    }

    private boolean asteroidAt(int x, int y) {
        return map.get(y).get(x);
    }


    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    private List<Asteroid> calculateAsteroids() {
        List<Asteroid> asteroids = new ArrayList<>();
        for(int y = 0; y < map.size(); y++) {
            for(int x = 0; x < map.get(0).size(); x++) {
                if (asteroidAt(x, y)) {
                    asteroids.add(new Asteroid(x, y));
                }
            }
        }
        return asteroids;
    }

    private static boolean isAsteroid(String square) {
        return square.equals("#");
    }

    public int calculateVisibleAsteroidsFrom(Asteroid asteroid) {
        return (int) getAsteroids()
                .stream()
                .filter(other -> isVisible(asteroid, other))
                .count();
    }

    public boolean isVisible(Asteroid from, Asteroid to) {
        int diffX = to.getX()-from.getX();
        int diffY = to.getY()-from.getY();

        if (diffX == 0 && diffY == 0) {
            return false;
        } else if (diffX == 0) {
            int minY = Integer.min(to.getY(), from.getY());
            int maxY = Integer.max(to.getY(), from.getY());
            for(int checkY = minY +1; checkY < maxY; checkY++) {
                if(asteroidAt(from.getX(), checkY)) {
                    return false;
                }
            }
            return true;
        } else if (diffY == 0) {
            int minX = Integer.min(to.getX(), from.getX());
            int maxX = Integer.max(to.getX(), from.getX());
            for(int checkX = minX +1; checkX < maxX; checkX++) {
                if(asteroidAt(checkX, from.getY())) {
                    return false;
                }
            }
            return true;
        }

        int gcd = IntMath.gcd(Math.abs(diffX), Math.abs(diffY));
        if (gcd == 1) {
            return true;
        }

        int offsetX = diffX/gcd;
        int offsetY = diffY/gcd;

        int checkX = from.getX() + offsetX;
        int checkY = from.getY() + offsetY;

        while(checkX != to.getX()) {
            if(asteroidAt(checkX, checkY)) {
                return  false;
            }
            checkX += offsetX;
            checkY += offsetY;
        }

        return true;
    }

    public void remove(Collection<Asteroid> asteroids) {
        for (Asteroid asteroid : asteroids) {
            map.get(asteroid.getX()).set(asteroid.getY(), false);
        }
        this.asteroids = calculateAsteroids();
    }
}
