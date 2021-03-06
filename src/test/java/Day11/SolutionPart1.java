package Day11;

import computer.Computers;
import computer.IntcodeComputer;
import painting.PaintableHull;
import painting.PaintingRobot;
import painting.PaintingRobotInput;
import painting.PaintingRobotOutput;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionPart1 {

    @Test
    public void testSolutionPart1() throws IOException {

        PaintingRobot robot = new PaintingRobot(100, 100, PaintingRobot.UP);
        PaintableHull hull = new PaintableHull();
        PaintingRobotInput input = new PaintingRobotInput(hull, robot);
        PaintingRobotOutput output = new PaintingRobotOutput(hull, robot);

        IntcodeComputer computer = Computers.makeWithIO(input, output);

        String intcodes = Files.readString(Paths.get("input/Day11/input.txt"));
        List<Long> program = Arrays.stream(intcodes.split(",")).map(Long::parseLong).collect(Collectors.toList());
        computer.runProgram(program);

        System.out.println("The solution to day 11 part 1 is:");
        System.out.println(hull.getPaintedNum());

    }
}
