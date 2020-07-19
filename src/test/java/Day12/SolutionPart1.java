package Day12;

import moons.Coordinates;
import moons.Moon;
import moons.Space;
import moons.Velocities;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SolutionPart1 {

    @Test
    public void testSolutionPart1() throws IOException {

        /*
        <x=-10, y=-13, z=7>
        <x=1, y=2, z=1>
        <x=-15, y=-3, z=13>
        <x=3, y=7, z=-4>
         */

        Space space = new Space(List.of(
                new Moon(new Coordinates(-10, -13, 7), new Velocities()),
                new Moon(new Coordinates(1, 2, 1), new Velocities()),
                new Moon(new Coordinates(-15, -3, 13), new Velocities()),
                new Moon(new Coordinates(3, 7, -4), new Velocities())
        ));

        for(int i = 0; i < 1000; i++) {
            space.iterate();
        }

        System.out.println("The solution to day 12 part 1 is:");
        System.out.println(space.calculateTotalEnergy());

    }

    @Test
    public void testExample1() {

        /*
        <x=-1, y=0, z=2>
        <x=2, y=-10, z=-7>
        <x=4, y=-8, z=8>
        <x=3, y=5, z=-1>
         */
        Space space = new Space(List.of(
                new Moon(new Coordinates(-1, 0, 2), new Velocities()),
                new Moon(new Coordinates(2, -10, -7), new Velocities()),
                new Moon(new Coordinates(-4, -8, 8), new Velocities()),
                new Moon(new Coordinates(3, 5, -1), new Velocities())
        ));


        /*
        After 0 steps:
        pos=<x=-1, y=  0, z= 2>, vel=<x= 0, y= 0, z= 0>
        pos=<x= 2, y=-10, z=-7>, vel=<x= 0, y= 0, z= 0>
        pos=<x= 4, y= -8, z= 8>, vel=<x= 0, y= 0, z= 0>
        pos=<x= 3, y=  5, z=-1>, vel=<x= 0, y= 0, z= 0>
         */
        Moon moon0 = space.getMoon(0);
        Assert.assertEquals(new Velocities(0,0,0), moon0.getVelocity());
        Assert.assertEquals(new Coordinates(-1, 0,2), moon0.getCoords());


        space.iterate();
        /*
        After 1 step:
        pos=<x= 2, y=-1, z= 1>, vel=<x= 3, y=-1, z=-1>
        pos=<x= 3, y=-7, z=-4>, vel=<x= 1, y= 3, z= 3>
        pos=<x= 1, y=-7, z= 5>, vel=<x=-3, y= 1, z=-3>
        pos=<x= 2, y= 2, z= 0>, vel=<x=-1, y=-3, z= 1>
         */
        moon0 = space.getMoon(0);
        Assert.assertEquals(new Velocities(3, -1,-1), moon0.getVelocity());
        Assert.assertEquals(new Coordinates(2, -1,1), moon0.getCoords());
    }
}
