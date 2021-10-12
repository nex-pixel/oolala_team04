package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to rotate the turtles right, or clockwise.
 * Dependencies: List
 *
 * Example: When the user inputs "rt 90", create a RotateRightCommand that when ran rotates the active
 * turtles clockwise by 90 degrees.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public class RotateRightCommand extends RotateCommand {

  /**
   * Purpose: Create a new RotateRightCommand
   * Assumptions: args is of size 1.
   * @param args List of integers of size one representing the degrees of rotation.
   * @throws IllegalArgumentException
   */
  public RotateRightCommand(List<Integer> args)
      throws IllegalArgumentException {
    super(1, args);
  }
}