package moons;

import java.util.ArrayList;
import java.util.List;

public class Space {

    private final List<Moon> moons;

    public Space(List<Moon> moons) {
        this.moons = moons;
    }

    public void iterate() {
        applyGravity(moons);
        applyVelocity(moons);
    }

    public int calculateTotalEnergy() {
        return moons.stream()
                .mapToInt(Space::calculateTotalEnergy)
                .sum();
    }

    private static int calculateTotalEnergy(Moon moon) {
        return calculateKineticEnergy(moon) * calculatePotentialEnergy(moon);
    }

    private static int calculatePotentialEnergy(Moon moon) {
        Coordinates coords = moon.getCoords();
        return Math.abs(coords.getX()) + Math.abs(coords.getY()) + Math.abs(coords.getZ());
    }

    private static int calculateKineticEnergy(Moon moon) {
        Velocities velocities = moon.getVelocity();
        return Math.abs(velocities.getX()) + Math.abs(velocities.getY()) + Math.abs(velocities.getZ());
    }

    private static void applyVelocity(List<Moon> moons) {
        for (Moon moon : moons) {
            Velocities velocity = moon.getVelocity();
            Coordinates coords = moon.getCoords();

            coords.addX(velocity.getX());
            coords.addY(velocity.getY());
            coords.addZ(velocity.getZ());
        }
    }

    private static void applyGravity(List<Moon> moons) {
        for (int i = 0; i < moons.size() - 1; i++) {
            for (int j = i+1; j < moons.size(); j++) {
                Moon moon1 = moons.get(i);
                Moon moon2 = moons.get(j);

                applyGravityOnPair(moon1, moon2);
            }
        }
    }

    private static void applyGravityOnPair(Moon first, Moon second) {
        Velocities firstVelocity = first.getVelocity();
        Velocities secondVelocity = second.getVelocity();

        Coordinates firstCoords = first.getCoords();
        Coordinates secondCoords = second.getCoords();

        if (firstCoords.getX() < secondCoords.getX()) {
            firstVelocity.incrementX();
            secondVelocity.decrementX();
        } else if (firstCoords.getX() > secondCoords.getX()) {
            firstVelocity.decrementX();
            secondVelocity.incrementX();
        }

        if (firstCoords.getY() < secondCoords.getY()) {
            firstVelocity.incrementY();
            secondVelocity.decrementY();
        } else if (firstCoords.getY() > secondCoords.getY()) {
            firstVelocity.decrementY();
            secondVelocity.incrementY();
        }

        if (firstCoords.getZ() < secondCoords.getZ()) {
            firstVelocity.incrementZ();
            secondVelocity.decrementZ();
        } else if (firstCoords.getZ() > secondCoords.getZ()) {
            firstVelocity.decrementZ();
            secondVelocity.incrementZ();
        }
    }

    public Moon getMoon(int moon) {
        return moons.get(moon);
    }

}
