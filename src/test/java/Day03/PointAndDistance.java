package Day03;

import com.google.common.base.Objects;

public class PointAndDistance {

    private final int x, y, distance;

    PointAndDistance(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y, distance);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PointAndDistance) {
            PointAndDistance p = (PointAndDistance) other;
            return p.x == x && p.y == y && p.distance == distance;
        }
        return false;
    }

    PointAndDistance right(int n) {
        return new PointAndDistance(x + n, y, distance + n);
    }

    PointAndDistance down(int n) {
        return new PointAndDistance(x, y - n, distance + n);
    }

    PointAndDistance left(int n) {
        return new PointAndDistance(x - n, y, distance + n);
    }

    PointAndDistance up(int n) {
        return new PointAndDistance(x, y + n, distance + n);
    }

    public int getDistance() {
        return distance;
    }

    public boolean overlap(PointAndDistance other) {
        return other.x == x && other.y == y;
    }

    public Point getPoint() {
        return new Point(x, y);
    }
}
