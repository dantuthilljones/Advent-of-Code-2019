package painting;

import computer.io.ComputerOutput;

public class PaintingRobotOutput implements ComputerOutput {

    private final static long BLACK_LONG = 0;
    private final static long WHITE_LONG = 1;

    private final static long LEFT = 0;
    private final static long RIGHT = 1;

    private final static char BLACK_CHAR = '#';
    private final static char WHITE_CHAR = '.';

    private final PaintableHull hull;
    private final PaintingRobot robot;

    private Status status;

    public PaintingRobotOutput(PaintableHull hull, PaintingRobot robot) {
        this.hull = hull;
        this.robot = robot;

        status = Status.PAINTING;
    }


    @Override
    public void output(long value) {
        if (status == Status.PAINTING) {
            if (value == BLACK_LONG || value == WHITE_LONG) {
                hull.set(robot.getX(), robot.getY(), (int) value);
            } else {
                throw new IllegalArgumentException("Unknown value: " + value);
            }
        } else if (status == Status.TURNING) {
            if (value == LEFT) {
                robot.left();
            } else if (value == RIGHT) {
                robot.right();
            } else {
                throw new IllegalArgumentException("Unknown value: " + value);
            }
            robot.forward();
        }

        updateStatus();
    }

    public void updateStatus() {
        if (status == Status.PAINTING) {
            status = Status.TURNING;
        } else {
            status = Status.PAINTING;
        }
    }

    public enum Status {
        PAINTING,
        TURNING
    }

}
