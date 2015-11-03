package robot.impl;

import robot.ARobot;
import robot.ISing;

public class RobotSinger extends ARobot implements ISing {

	private static int id = 0;

	public RobotSinger() {
		super("RobotSinger", ++id);
	}

	@Override
	public void SingSong() {
		System.out.println(this.robotType + " " + this.seriesNumber + " sing a song to you!");
	}

	public void work() {
		this.SingSong();
	}

}
