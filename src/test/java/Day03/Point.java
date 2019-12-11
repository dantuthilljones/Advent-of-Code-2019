package Day03;

import com.google.common.base.Objects;

public class Point {
    final int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Point) {
            Point p = (Point) other;
            return p.x == x && p.y == y;
        }
        return false;
    }

    Point right(int n) {
        return new Point(x + n, y);
    }

    Point down(int n) {
        return new Point(x, y - n);
    }

    Point left(int n) {
        return new Point(x - n, y);
    }

    Point up(int n) {
        return new Point(x, y + n);
    }

    int manhattanDistanceFromOrigin() {
        return Math.abs(x) + Math.abs(y);
    }
}
