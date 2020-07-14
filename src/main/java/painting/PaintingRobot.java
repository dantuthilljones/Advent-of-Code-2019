package painting;

public class PaintingRobot {

    public final static int UP = 0;
    public final static int RIGHT = 1;
    public final static int DOWN = 2;
    public final static int LEFT = 3;

    private int x;
    private int y;
    private int direction;

    public PaintingRobot() {
        this(0,0, UP);
    }

    public PaintingRobot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void left() {
        direction = (direction + 3) % 4;
    }

    public void right() {
        direction = (direction + 1) % 4;
    }

    public void forward() {
        if (direction == UP) {
            y--;
        } else if (direction == RIGHT) {
            x++;
        } else if (direction == DOWN) {
            y++;
        } else if (direction == LEFT) {
            x--;
        }
    }

}
