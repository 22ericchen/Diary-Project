package cs3500.pa05.controller;

import cs3500.pa05.model.bujofile.BujoFileReader;
import cs3500.pa05.model.bujofile.BujoFileSaver;
import cs3500.pa05.view.GuiViewImpl;
import javafx.stage.Stage;

/**
 * A class to control the highest level of the bullet journal planner application.
 */
public class FullGuiController implements Controller {

  private Stage stage;

  /**
   * An instance of the FullGuiController class with a stage passed to it.
   *
   * @param primaryStage the stage desired to host the bullet journal aspects.
   */
  public FullGuiController(Stage primaryStage) {
    this.stage = primaryStage;
  }

  @Override
  public void run() {
    loadFileSelection();
  }

  /**
   * Loads the splash screen for the application.
   */
  private void loadSplashScreen() {
    try {
      SplashScreenController controller = new SplashScreenController();
      stage.setScene(new GuiViewImpl(controller, "splashScreen.fxml").load());
      stage.show();
      controller.run();

    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * Loads the file selector window.
   */
  public void loadFileSelection() {
    try {
      FileSelectorController fileSelectorController = new FileSelectorController(this);
      stage.setScene(new GuiViewImpl(fileSelectorController, "FileSelectionScreen.fxml").load());
      fileSelectorController.run();
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * Loads the diary window.
   *
   * @param fileSelectorController the fileSelectorController used to select the file.
   */
  public void loadDiary(FileSelectorController fileSelectorController) {
    stage.close();

    BujoFileReader fileReader = new BujoFileReader();
    fileReader.readFile(fileSelectorController.getFileSelected());
    BujoFileSaver saver = new BujoFileSaver(fileSelectorController.getFileSelected());
    stage = new Stage();
    DiaryController diaryController = new DiaryController(fileReader, saver, this);
    stage.setScene(new GuiViewImpl(diaryController, "diaryView.fxml").load());
    diaryController.run();
    stage.show();
  }

  /**
   * Loads the event editor screen.
   *
   * @param event The event controller .
   */
  public void loadNewEventScreen(EventController event) {
    stage = new Stage();
    GuiViewImpl view = new GuiViewImpl(event, "eventPrompt.fxml");
    stage.setScene(view.load());
    event.run();
    stage.show();
    //update week planner
  }

  /**
   * Loads a new task editor window.
   *
   * @param task the task controller.
   */
  public void loadNewTaskScreen(TaskController task) {
    stage = new Stage();
    GuiViewImpl view = new GuiViewImpl(task, "taskPrompt.fxml");
    stage.setScene(view.load());
    task.run();
    stage.show();
  }
}
