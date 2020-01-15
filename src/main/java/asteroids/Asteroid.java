package asteroids;

public class Asteroid {

    private final int x;
    private final int y;

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
