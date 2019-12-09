package Day01;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TestDay01 {

    private static long fuelRequiredIncludingFuel(List<Long> modulesMasses) {
        return modulesMasses.stream().mapToLong(TestDay01::fuelIncludingFuelForMass).sum();
    }

    private static long fuelRequired(List<Long> modulesMasses) {
        return modulesMasses.stream().mapToLong(TestDay01::fuelForMass).sum();
    }

    private static long fuelForMass(long mass) {
        return Math.max((mass/3) -2, 0);
    }

    private static long fuelIncludingFuelForMass(long mass) {
        long totalFuel = fuelForMass(mass);
        long fuelForFuel = fuelForMass(totalFuel);
        while(fuelForFuel != 0) {
            totalFuel += fuelForFuel;
            fuelForFuel = fuelForMass(fuelForFuel);
        }
        return totalFuel;
    }

    @Test
    public void testPart1() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/Day01/masses.txt"));
        List<Long> masses = lines.stream().map(Long::parseLong).collect(Collectors.toList());

        System.out.println("The solution to day 1 part 1 is:");
        System.out.println(fuelRequired(masses));
    }

    @Test
    public void testPart2() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input/Day01/masses.txt"));
        List<Long> masses = lines.stream().map(Long::parseLong).collect(Collectors.toList());

        System.out.println("The solution to day 1 part 2 is:");
        System.out.println(fuelRequiredIncludingFuel(masses));
    }

    @Test
    public void testFuelCalculation() {
        Assert.assertEquals(2, fuelForMass(12));
        Assert.assertEquals(2, fuelForMass(14));
        Assert.assertEquals(654, fuelForMass(1969));
        Assert.assertEquals(33583, fuelForMass(100756));
    }

    @Test
    public void testFuelIncludingFuelCalculation() {
        Assert.assertEquals(2, fuelIncludingFuelForMass(12));
        Assert.assertEquals(2, fuelIncludingFuelForMass(14));
        Assert.assertEquals(966, fuelIncludingFuelForMass(1969));
        Assert.assertEquals(50346, fuelIncludingFuelForMass(100756));
    }
}
