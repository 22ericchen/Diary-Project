package cs3500.pa05;

import cs3500.pa05.controller.FullGuiController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Program entry point.
 */
public class Driver extends Application {

  /**
   * Program entry point.
   *
   * @param args commandLine arguments.
   */
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FullGuiController fullGuiController = new FullGuiController(primaryStage);
    fullGuiController.run();
  }
}
