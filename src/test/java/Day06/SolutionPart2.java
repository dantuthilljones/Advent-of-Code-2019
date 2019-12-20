package Day06;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SolutionPart2 {
    @Test
    public void testPart1() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/Day06/orbits.txt"));
        List<String[]> orbits = lines.stream().map(SolutionPart2::extractOrbit).collect(Collectors.toList());

        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        for (String[] orbit : orbits) {
            graph.addVertex(orbit[0]);
            graph.addVertex(orbit[1]);
            graph.addEdge(orbit[0], orbit[1]);
        }

        GraphPath<String, DefaultEdge> path = DijkstraShortestPath.findPathBetween(graph, "YOU", "SAN");
        int jumpsToSanta = path.getLength() - 2; //-2 to remove last and end jump


        System.out.println("The solution to day 6 part 1 is:");
        System.out.println(jumpsToSanta);
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
