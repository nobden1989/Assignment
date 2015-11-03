package robot.impl;

import java.util.ArrayList;

import robot.ARobot;

public class JunkRobot extends ARobot {

	private static int id = 0;

	private ArrayList<ARobot> partsFromOthers = new ArrayList<ARobot>();

	public JunkRobot() {
		super("JunkRobot", ++id);
	}

	public JunkRobot(ARobot... args) {
		super("JunkRobot", ++id);
		for (ARobot robotParts : args) {
			partsFromOthers.add(robotParts);
		}
	}

	public void work() {
		if (!partsFromOthers.isEmpty()) {
			for (ARobot robotParts : partsFromOthers) {
				System.out.print(this.robotType + " " + this.seriesNumber + " use parts form ");
				robotParts.work();
			}
		} else {
			System.out.println(this.seriesNumber + " has no parts from others, it cannot work!");
		}
	}

}
