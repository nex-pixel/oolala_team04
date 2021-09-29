package model.commands;

import model.TurtleModel;

public class MoveCommand extends Command{
  private int distance;

  MoveCommand(int distance) {
    this.distance = distance;
  }

  @Override
  public void runCommand(TurtleModel turtleModel) {
    turtleModel.move(distance);
  }
}
