package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to set the pen up so that it does not draw. Assumption:
 * Args passed to the constructor should be empty Dependencies: List
 * <p>
 * Example: If a user inputs the command "pd", then make a SetPenDownCommand and run the command to
 * make it so all the active turtles are drawing.
 *
 * @author Luis Pereda
 * @author Evan Kenyon
 */
public class SetPenUpCommand extends SetPenCommand {

  /**
   * Purpose: Create a new SetPenUpCommand
   *
   * @param args List of integers of size 0.
   * @throws IllegalArgumentException thrown if args is not empty since the set pen up command
   * does not require any arguments.
   */
  public SetPenUpCommand(List<Integer> args) throws IllegalArgumentException {
    super(false, args);
  }

}
