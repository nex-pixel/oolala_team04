package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandModelTest {
  private CommandModel commandModel;

  @BeforeEach
  void setUp() {
    commandModel = new CommandModel();
  }

  @Test
  public void parseInputForwardIncorrect() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> commandModel.parseInput("fd bad"));
  }

  @Test
  public void parseInputBackwardIncorrect() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> commandModel.parseInput("bk bad"));
  }

  @Test
  public void parseInputNonExistentCommand() {
    Assertions.assertThrows(InputMismatchException.class, () -> commandModel.parseInput("bad"));
  }

  @Test
  public void parseInputPenUpNumArg() {
    Assertions.assertThrows(InputMismatchException.class, () -> commandModel.parseInput("pu 50"));
  }

  @Test
  public void handleFileSelectedNotTxt() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> commandModel.handleFileSelected(new File("./data/FOLDER_PURPOSE.md")));
  }

  @Test
  public void saveCommandsAsFileCorrect() throws FileNotFoundException, UnsupportedEncodingException {
    commandModel.saveCommandsAsFile();
    Scanner scanner = new Scanner(new File("./data/programs/program1.txt"));
    scanner.useDelimiter("\n");
    Assertions.assertEquals("#Saved program number 1", scanner.next());
    Assertions.assertEquals("fd 50", scanner.next());
    Assertions.assertEquals( "rt 50", scanner.next());
  }

  @Test
  public void handleFileSelectedBadCommands() {
    Assertions.assertThrows(InputMismatchException.class, () -> commandModel.handleFileSelected(new File("./data/examples/logo/bad.txt")));
  }

}