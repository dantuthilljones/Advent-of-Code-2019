package Day11;

import computer.Computers;
import computer.IntcodeComputer;
import org.junit.Test;
import painting.PaintableHull;
import painting.PaintingRobot;
import painting.PaintingRobotInput;
import painting.PaintingRobotOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionPart2 {

    @Test
    public void testSolutionPart2() throws IOException {
        int startX = 0;
        int startY = 0;

        PaintingRobot robot = new PaintingRobot(startX, startY, PaintingRobot.UP);
        PaintableHull hull = new PaintableHull();
        hull.set(startX, startY, PaintableHull.WHITE_INT);

        PaintingRobotInput input = new PaintingRobotInput(hull, robot);
        PaintingRobotOutput output = new PaintingRobotOutput(hull, robot);

        IntcodeComputer computer = Computers.makeWithIO(input, output);

        String intcodes = Files.readString(Paths.get("input/Day11/input.txt"));
        List<Long> program = Arrays.stream(intcodes.split(",")).map(Long::parseLong).collect(Collectors.toList());
        computer.runProgram(program);

        System.out.println("The solution to day 11 part 2 is:");
        hull.draw();
    }
}
