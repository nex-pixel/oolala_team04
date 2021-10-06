package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import model.commands.Command;

public class LogoModel {

  private final TurtleController turtleController;
  private final CommandModel commandModel;

  public LogoModel() {
    turtleController = new TurtleController();
    commandModel = new CommandModel();
  }

  /**
   * When the user inputs a file, use CommandModel's handleFile to return a list of commands
   *
   * @param file
   * @return
   */
  public List<Command> handleFileInput(File file) {
    List<Command> commands = new ArrayList<>();
    try {
      commands = commandModel.handleFileSelected(file);
    } catch (IllegalArgumentException | FileNotFoundException e) {
      e.printStackTrace();
    }
    return commands;
  }

  /**
   * When the user inputs a string of texts, have the command model parse the text and run the
   * appropriate command.
   *
   * @param input
   * @return
   */
  public void handleTextInput(String input) throws InputMismatchException, NumberFormatException {
    Command command = commandModel.parseInput(input);
    command.runCommand(turtleController);
  }

  public boolean isTurtleActive(int turtleId) {
    try {
      getTurtleModel(turtleId);
    } catch (NullPointerException e) {
      return false;
    }
    return true;
  }

  /**
   * Returns the position of a turtle found by its ID This will be used in the step function to
   * determine if the turtle has moved and a new line needs to be made.
   *
   * @param turtleId
   * @return
   */
  public int[] getTurtlePosition(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getPosition();
  }

  public double getTurtleTrajectory(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getTrajectory();
  }

  public boolean getTurtlePenDown(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getPen();
  }

  public boolean getShouldTurtleStamp(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getShouldStamp();
  }

  public boolean getTurtleShouldShow(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getShouldShow();
  }

  public TurtleModel getNewTurtle() throws NullPointerException {
    return turtleController.getNewTurtle();
  }

  public boolean hasNewTurtle() {
    return turtleController.hasNewTurtle();
  }

  private TurtleModel getTurtleModel(int turtleId) throws NullPointerException {
    TurtleModel turtleModel = null;
    for (TurtleModel turtle : turtleController.getActiveTurtles()) {
      if (turtle.getID() == turtleId) {
        turtleModel = turtle;
      }
    }

    if (turtleModel == null) {
      throw new NullPointerException();
    }
    return turtleModel;
  }

  /**
   * Getter so the test classes can access the controller
   *
   * @return
   */
  TurtleController getTurtleController() {
    return turtleController;
  }

  CommandModel getCommandModel() {
    return commandModel;
  }
}
