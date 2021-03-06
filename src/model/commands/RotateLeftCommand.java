package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to rotate the turtles left, or counterclockwise.
 * Dependencies: List
 * <p>
 * Example: When the user inputs "lt 90", create a RotateLeftCommand that when ran rotates the
 * active turtles counterclockwise by 90 degrees.
 *
 * @author Luis Pereda
 * @author Evan Kenyon
 */
public class RotateLeftCommand extends RotateCommand {

  /**
   * Purpose: Create a new RotateLeftCommand Assumptions: args is of size 1.
   *
   * @param args List of integers of size one representing the degrees of rotation.
   * @throws IllegalArgumentException thrown if args is not of size one since the rotate left
   * command only requires one argument.
   */
  public RotateLeftCommand(List<Integer> args)
      throws IllegalArgumentException {
    super(-1, args);
  }
}
