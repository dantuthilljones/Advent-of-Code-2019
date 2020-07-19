package moons;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Velocities {

    private int x, y, z;

    public Velocities() {
        this(0, 0, 0);
    }

    public Velocities(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    public void incrementZ() {
        z++;
    }

    public void decrementX() {
        x--;
    }

    public void decrementY() {
        y--;
    }

    public void decrementZ() {
        z--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocities that = (Velocities) o;
        return x == that.x &&
                y == that.y &&
                z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y, z);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("x", x)
                .add("y", y)
                .add("z", z)
                .toString();
    }
}
