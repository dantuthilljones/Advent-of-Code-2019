package Day06;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SolutionPart1 {

    @Test
    public void testPart1() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/Day06/orbits.txt"));
        List<String[]> orbits = lines.stream().map(SolutionPart1::extractOrbit).collect(Collectors.toList());

        Map<String, Planet> planets = new HashMap();
        for (String[] orbit : orbits) {
            Planet planet1 = planets.computeIfAbsent(orbit[0], name -> new Planet(name));
            Planet planet2 = planets.computeIfAbsent(orbit[1], name -> new Planet(name));
            planet1.orbitedBy.add(planet2);
        }

        Planet com = planets.get("COM");
        int nOrbits = countOrbits(com, 0, new AtomicInteger(0));

        System.out.println("The solution to day 6 part 1 is:");
        System.out.println(nOrbits);
    }

    private int countOrbits(Planet planet, int depth, AtomicInteger orbits) {
        for (Planet childPlanet: planet.orbitedBy) {
            countOrbits(childPlanet, depth +1, orbits);
        }
        orbits.set(orbits.get() + depth);
        return orbits.get();
    }

    private static String[] extractOrbit(String orbit) {
        return orbit.split("\\)");
    }
}
