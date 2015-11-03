package test;

import java.util.ArrayList;

import robot.ARobot;
import robot.impl.JunkRobot;
import robot.impl.RobotCook;
import robot.impl.RobotSinger;
import robot.impl.SuperRobot;

public class RobotTester {

	public static void main(String[] args) {
		ArrayList<ARobot> robots = new ArrayList<ARobot>();

		RobotCook rCook = new RobotCook();

		robots.add(rCook);
		robots.add(new RobotSinger());
		robots.add(new SuperRobot());
		robots.add(new JunkRobot());
		robots.add(new RobotCook());
		robots.add(new RobotSinger());
		robots.add(new JunkRobot(rCook, new RobotSinger(), new SuperRobot()));

		for (ARobot robot : robots) {
			robot.greetings();
			robot.work();
			System.out.println();
		}

	}

}
