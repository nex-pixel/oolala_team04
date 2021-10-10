package view;

import java.util.List;
import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.LogoModel;
import util.PropertiesLoader;

public class LogoDisplay {

  // Magic values borrowed from example_animation course gitlab repo
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final String DEFAULT_RESOURCES_PACKAGE = "./src/view/resources/";
  public static final String LOGO_RESOURCES_PACKAGE = DEFAULT_RESOURCES_PACKAGE + "logo/";
  public static final String DEFAULT_RESOURCE_FOLDER = "/view/resources/";
  public static final String STYLESHEET = "Default.css";

  private List<TurtleDisplay> turtleDisplays;
  private CommandDisplay commandDisplay;
  private ClearDisplay clearDisplay;
  
  private DisplayComponent instructionsDisplay;
  private TurtleInfoDisplay turtleInfoDisplay;
  private TurtleWindowDisplay turtleWindowDisplay;
  
  private GridPane root;
  private LogoModel logoModel;
  private Pane turtleWindow;
  private Properties props;
  // Could store this data in file
  // Or a String to int map
  private final int[] instructDispGridLayout = new int[]{0,0,7,10};
  private final int[] commandDispGridLayout = new int[]{0,11,7,10};
  private final int[] turtleWindowGridLayout = new int[]{9,1,20,10};
  private final int[] turtInfoDispGridLayout = new int[]{9,11,7,10};
  private final int PREF_WINDOW_SIZE = 400;

  public LogoDisplay() {
    root = new GridPane();
    setupLogoDisplay();
  }

  private void setupLogoDisplay() {
    props = PropertiesLoader.loadProperties(LOGO_RESOURCES_PACKAGE + "English.properties");
    commandDisplay = new CommandDisplay();
    instructionsDisplay = new InstructionsDisplay();
    turtleInfoDisplay = new TurtleInfoDisplay();
    logoModel = new LogoModel();
    turtleWindowDisplay = new TurtleWindowDisplay();
    clearDisplay = new ClearDisplay();
    rootSetup();
  }

  public Scene makeScene(int width, int height) {
    setupAnimation();
    Scene scene = new Scene(root, width, height);
    scene.getStylesheets().add(DEFAULT_RESOURCE_FOLDER + STYLESHEET);
    return scene;
  }

  private void rootSetup() {
//    root = new GridPane();
    root.add(instructionsDisplay.getDisplayComponentNode(), instructDispGridLayout[0],
        instructDispGridLayout[1],
        instructDispGridLayout[2], instructDispGridLayout[3]);
    root.add(commandDisplay.getDisplayComponentNode(), commandDispGridLayout[0],
        commandDispGridLayout[1],
        commandDispGridLayout[2], commandDispGridLayout[3]);
    root.add(turtleWindowDisplay.getDisplayComponentNode(), turtleWindowGridLayout[0], turtleWindowGridLayout[1],
        turtleWindowGridLayout[2],
        turtleWindowGridLayout[3]);
    root.add(turtleInfoDisplay.getDisplayComponentNode(), turtInfoDispGridLayout[0], turtInfoDispGridLayout[1],
            turtInfoDispGridLayout[2], turtInfoDispGridLayout[3]);

    root.add(clearDisplay.getDisplayComponentNode(), 9, 12, 1, 1);
    root.getStyleClass().add("grid-pane");
  }

  private void turtleWindowSetup() {
    Group turtleDisplaysGroup = new Group();
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      turtleDisplaysGroup.getChildren().add(turtleDisplay.getDisplayComponentNode());
    }
    turtleWindow = new Pane();
    turtleWindow.setPrefSize(PREF_WINDOW_SIZE, PREF_WINDOW_SIZE);
    turtleWindow.getChildren().addAll(turtleDisplaysGroup);
  }

  private void setupAnimation() {
    // Timeline setup borrowed from example_animation course gitlab repo
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  private void step(double elapsedTime) {
    handleReset();
    handleCommandInputted();
    handleFileInputted();
    handleRunNextCommand();
    handleFileSave();
    if (turtleDisplays != null) {
      for (TurtleDisplay t : turtleDisplays) {
        t.setPenThickness(turtleInfoDisplay.getPenThicknesss());
      }
    }
  }

  private void handleReset() {
    if(clearDisplay.getShouldReset()) {
      setupLogoDisplay();
    }
  }

  private void handleCommandInputted() {
    if (commandDisplay.getHasCommandUpdated()) {
      try {
        logoModel.handleTextInput(commandDisplay.getCommand());
      } catch (Exception e) {
        showError();
      }
    }
  }

  private void handleFileInputted() {
    if (commandDisplay.getIsFileUploaded()) {
      try {
        logoModel.handleFileInput(commandDisplay.getUploadedCommandFile());
      } catch (Exception e) {
        // TODO: change
        showError();
      }
    }
  }

  private void handleRunNextCommand() {
    commandDisplay.updateCommandHistory(logoModel.getCommandHistory());
    logoModel.runNextCommand();
    if (logoModel.hasNewTurtles()) {
      turtleWindowDisplay.addNewTurtles(logoModel.getNewTurtles());
    }
    turtleWindowDisplay.updateTurtleWindowAndDisplays(logoModel.getActiveTurtles());
  }

  private void handleFileSave() {
    if (commandDisplay.shouldSaveAsFile()) {
      try {
        logoModel.saveCommandsAsFile();
      } catch (Exception e) {
        // TODO: fix
        showError();
      }
    }
  }

  //Borrowed from lab_browser course gitlab repo
  private void showError() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(props.getProperty("errorMessage"));
    alert.show();
  }
}
