package painting;

import computer.io.ComputerInput;

public class PaintingRobotInput implements ComputerInput {

    private final PaintableHull hull;
    private final PaintingRobot robot;

    public PaintingRobotInput(PaintableHull hull, PaintingRobot robot) {
        this.hull = hull;
        this.robot = robot;
    }

    @Override
    public long get() {
        return hull.get(robot.getX(), robot.getY());
    }
}
