package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.commands.*;

// fix this
public class LSystemCommandRunner extends CommandModel {

  private int movementLength;
  private int rotationAngle;
  private boolean stampBranchImage;
  private List<Integer> location;

  public LSystemCommandRunner(int distance, int angle,
      int[] startingLoc) {
    movementLength = distance;
    rotationAngle = angle;
//    stampBranchImage = branchStamp;
    location = new ArrayList<>();
    location.add(startingLoc[0]);
    location.add(startingLoc[1]);
  }

  @Override
  public List<Command> getCommandsFromInput(String input) throws InputMismatchException {
    List<Command> commands = new ArrayList<>();
    for(String inputChar : input.split("(?!^)")) {
      commands.add(getCommand(inputChar));
    }
    location.set(1, location.get(1) + 100);
    commands.add(new SetHomeCommand(location));
    commands.add(new GoHomeCommand(new ArrayList<>()));
    return commands;
  }

  private Command getCommand(String inputChar) {
    List<Integer> rotateNum = new ArrayList<>();
    rotateNum.add(rotationAngle);
    return switch (inputChar) {
      case "f" -> new FCommand(movementLength);
      case "g" -> new GCommand(movementLength);
      case "a" -> new ACommand(movementLength);
      case "b" -> new BCommand(movementLength);
      case "+" -> new RotateRightCommand(rotateNum);
      case "-" -> new RotateLeftCommand(rotateNum);
      case "x" -> new StampCommand(new ArrayList<>());
      default -> throw new InputMismatchException();
    };
  }


  public void goToStartLocation() {
//    new SetHomeCommand(location).runCommand(turtleController);
//    new GoHomeCommand(new ArrayList<>()).runCommand(turtleController);
  }

  public void setStartLocation(int[] inputStartLocation) {
    location.clear();
    location.add(inputStartLocation[0]);
    location.add(inputStartLocation[1]);
  }

  public void setMovementLength(int distance) {
    this.movementLength = distance;
  }

  public void setRotationAngle(int rotationAngle) {
    this.rotationAngle = rotationAngle;
  }

  public void setStampBranchImage(boolean stampBranchImage) {
    this.stampBranchImage = stampBranchImage;
  }
}
