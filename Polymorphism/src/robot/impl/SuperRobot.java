package robot.impl;

import robot.ARobot;
import robot.ICook;
import robot.ISing;

public class SuperRobot extends ARobot implements ICook, ISing {

	private static int id = 0;

	public SuperRobot() {
		super("SuperRobot", ++id);
	}

	public void work() {
		this.CookBreakfast();
		this.CookLunch();
		this.CookDinner();
		this.SingSong();
	}

	@Override
	public void SingSong() {
		System.out.println(this.robotType + " " + this.seriesNumber + " sing a super song to you!");
	}

	@Override
	public void CookBreakfast() {
		System.out.println(
				this.robotType + " " + this.seriesNumber + " cook a super breakfastfor you!");

	}

	@Override
	public void CookLunch() {
		System.out
				.println(this.robotType + " " + this.seriesNumber + " cook a super lunch for you!");

	}

	@Override
	public void CookDinner() {
		System.out.println(
				this.robotType + " " + this.seriesNumber + " cook a super dinner for you!");
	}

}
