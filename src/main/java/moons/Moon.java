package moons;

public class Moon {

    private final Coordinates coords;
    private final Velocities velocity;

    public Moon(Coordinates coords, Velocities velocity) {
        this.coords = coords;
        this.velocity = velocity;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public Velocities getVelocity() {
        return velocity;
    }
}
