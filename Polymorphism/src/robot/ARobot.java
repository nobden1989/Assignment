package robot;

public abstract class ARobot {

	protected String robotType;
	protected String seriesNumber;

	private static int id = 0;

	public ARobot(final String roboType, final int subID) {
		this.robotType = roboType;
		this.seriesNumber = this.robotType.substring(0, 2).toUpperCase() + subID + "-" + ++id;
	}

	public String getRobotType() {
		return this.robotType;
	}

	public String getSeriesNumber() {
		return this.seriesNumber;
	}

	public void greetings() {
		System.out
				.println(this.getRobotType() + " " + this.getSeriesNumber() + " at your service.");
	}

	abstract public void work();
}
