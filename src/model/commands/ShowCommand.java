package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to show the turtle so that it can be seen. Assumption:
 * Args passed to the constructor should be empty Dependencies: List
 * <p>
 * Example: If the user inputs the command "st", then create an instance of this class and run the
 * command.
 *
 * @author Luis Pereda
 * @author Evan Kenyon
 */
public class ShowCommand extends ShowOrHideCommand {

  /**
   * Purpose: Create a new show command
   *
   * @param args Should be an empty list
   * @throws IllegalArgumentException thrown if args is not empty since the show command does not
   * require any arguments.
   */
  public ShowCommand(List<Integer> args) throws IllegalArgumentException {
    super(true, args);
  }
}
