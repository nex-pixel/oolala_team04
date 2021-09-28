package oolala;

public class TurtleModel {

  private int[] myPosition;
  // A number from 0 to 2pi representing the angle of travel of the turtle
  private double myTrajectory;
  // If true, pen is down and drawing; if false, pen is up and not drawing
  private boolean penSetting;
  private int[] myHome;

  /**
   * Create a TurtleModel with the initial where we want the turtle to be initialized on the screen.
   * If no initial position is passed then turtle will be placed at [0,0]
   *
   * @param initialPosition
   */
  public TurtleModel(int[] initialPosition) {
    myPosition = initialPosition;
    myTrajectory = 0;
    penSetting = true;
    myHome = new int[2];
  }

  public TurtleModel() {
    myPosition = new int[2];
    myTrajectory = 0;
    penSetting = true;
    myHome = new int[2];
  }

  /**
   * Getter for the position of the turtle at that moment
   *
   * @return
   */
  public int[] getPosition() {
    return myPosition;
  }

  /**
   * Getter for the angle at which the turtle is moving
   *
   * @return
   */
  public double getTrajectory() {
    return myTrajectory;
  }

  /**
   * Moves the turtle based on the distance provided by the user
   *
   * @param distance
   */
  public void move(int distance) {
    int dx = distance * (int) Math.cos(myTrajectory);
    int dy = distance * (int) Math.sin(myTrajectory);
    myPosition[0] = myPosition[0] + dx;
    myPosition[1] = myPosition[1] + dy;
  }

  /**
   * Rotate the turtle based on the angle provided by the user
   *
   * @param angle
   */
  public void rotate(int angle) {
    myTrajectory = angle * Math.PI / 180;
  }

  /**
   * Set whether the pen is drawing or not drawing
   *
   * @param penUpOrDown
   */
  public void setPen(boolean penUpOrDown) {
    penSetting = penUpOrDown;
  }

  /**
   * Set home location for turtle
   *
   * @param homeLocation
   */
  public void setHome(int[] homeLocation) {
    myHome = homeLocation;
  }

  /**
   * Turtle is moved to home
   */
  public void goHome() {
    myPosition = myHome;
  }
}
