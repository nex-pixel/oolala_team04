package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.commands.Command;
import model.commands.SetHomeCommand;

/**
 * Purpose: This class is the high level model for the Logo IDE, using the TurtleController and
 * CommandModel to manage the backend of the program.
 * Assumptions: The program will only use a LogoModel in the display to manage all the work that
 * will be displayed in the view.
 * Dependencies: File, FileNotFoundException, InputMismatchException, LinkedList, List, Queue,
 * Command
 *
 * Example: In the display of the Logo IDE program, make an instance of LogoModel which can be used
 * to make the turtles move based on the users' input.
 *
 * @author Evan Kenyon
 */
public class LogoModel extends AppModel {

  /**
   * Purpose: Create a LogoModel
   */
  public LogoModel() {
    commandModel = new LogoCommandModel();
    commandsToRun = new LinkedList<>();
  }

  /**
   * Purpose: When the user inputs a file, use CommandModel's handleFile to return a list of commands.
   * Assumptions: File is a .txt and the commands inside are valid, if not errors will be thrown.
   * @param file A .txt file detailing commands for the turtles to execute.
   */
  public void handleFileInput(File file) {
    try {
      commandsToRun.addAll(((LogoCommandModel) commandModel).handleFileSelected(file));
    } catch (IllegalArgumentException | FileNotFoundException e) {
      // TODO: fix
      e.printStackTrace();
    }
  }


  /**
   * Purpose: Runs the next command for the turtle to execute.
   * Assumptions: Will do nothing if there are no commands in line
   */
  @Override
  public void runNextCommand() {
    if(!commandsToRun.isEmpty()) {
      commandsToRun.remove().runCommand(turtleController);
    }
  }


  /**
   * Purpose: Gets the history of commands that the user has entered to the program.
   * @return List of strings of the previous commands
   */
  @Override
  public List<String> getCommandHistory() {
    return ((LogoCommandModel) commandModel).getCommandHistory();
  }

  /**
   * Purpose: When the user inputs a string of text, have the command model parse the text and run the
   * appropriate command.
   * Assumptions: Commands are valid if not an error will be thrown.
   * @param input String representing a command for the turtle to execute
   */
  @Override
  public void handleTextInput(String input) throws InputMismatchException, NumberFormatException {
    commandsToRun.addAll(commandModel.getCommandsFromInput(input));
  }

  /**
   * Purpose: Check if turtle is active
   * Assumptions: If no active turtle exists with that ID, then return false.
   * @param turtleId Int representing a unique ID of a turtle.
   * @return Boolean which is true if the turtle is active, false if it is not
   */
  public boolean isTurtleActive(int turtleId) {
    try {
      getTurtleModel(turtleId);
    } catch (NullPointerException e) {
      return false;
    }
    return true;
  }

  /**
   * Purpose: Save the command history as a .txt file.
   * @throws IOException
   */
  @Override
  public void saveCommandsAsFile() throws IOException {
    ((LogoCommandModel) commandModel).saveCommandsAsFile();
  }

  /**
   * Purpose: Get the active turtles that are executing commands.
   *
   * @return List of TurtleModels that are active
   */
  public List<TurtleModel> getActiveTurtles() {
    return turtleController.getActiveTurtles();
  }

  /**
   * Purpose: Get the new turtles that have been added in the program.
   *
   * @return List of TurtleModels that are
   * @throws NullPointerException
   */
  @Override
  public List<TurtleModel> getNewTurtles() throws NullPointerException {
    return turtleController.getNewTurtles();
  }

  @Override
  public void setHomeLocation(int x, int y) {
    List<Integer> home = new ArrayList<>();
    home.add(x);
    home.add(y);
    commandsToRun.add(new SetHomeCommand(home));
  }

  /**
   * Purpose: Check if there are any new turtles in the program.
   *
   * @return Boolean which is true if there are new turtles; false if there are not.
   */
  @Override
  public boolean hasNewTurtles() {
    return turtleController.hasNewTurtle();
  }

  /**
   * Purpose: Get a TurtleModel with that unique ID
   * Assumptions: If no active turtle exists with that ID, NullPointerException will be thrown
   *
   * @param turtleId Unique ID of a turtle
   * @return TurtleModel with that ID
   * @throws NullPointerException
   */
  public TurtleModel getTurtleModel(int turtleId) throws NullPointerException {
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
   * Purpose: Getter so the test classes can access the controller
   *
   * @return TurtleController
   */
  TurtleController getTurtleController() {
    return turtleController;
  }

  /**
   * Getter to be able to access the LogoCommandModel
   * @return LogoCommandModel
   */
  LogoCommandModel getLogoCommandModel() {
    return ((LogoCommandModel) commandModel);
  }
}
