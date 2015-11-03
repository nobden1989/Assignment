package robot.impl;

import robot.ARobot;
import robot.ICook;

public class RobotCook extends ARobot implements ICook {

	private static int id = 0;

	public RobotCook() {
		super("RobotCook", ++id);
	}

	public void work() {
		this.CookBreakfast();
		this.CookLunch();
		this.CookDinner();
	}

	@Override
	public void CookBreakfast() {
		System.out.println(this.robotType + " " + this.seriesNumber + " cook a breakfastfor you!");
	}

	@Override
	public void CookLunch() {
		System.out.println(this.robotType + " " + this.seriesNumber + " cook a lunch for you!");

	}

	@Override
	public void CookDinner() {
		System.out.println(this.robotType + " " + this.seriesNumber + " cook a dinner for you!");
	}

}
