package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Task;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A class to control the creation, editing, and deleting of tasks from the week view.
 */
public class TaskController implements Controller {

  @FXML
  private TextField taskName;
  @FXML
  private ComboBox<Day> taskDay;
  @FXML
  private TextArea taskDescription;
  @FXML
  private Button taskSave;
  @FXML
  private Button taskDelete;

  private final DiaryController diary;
  private boolean isNew = true;
  private String name = "";

  /**
   * An instance of the TaskController class.
   *
   * @param controller the Diary controller passed to this controller.
   */
  public TaskController(DiaryController controller) {
    this.diary = controller;
  }

  /**
   * An instance of the TaskController class.
   *
   * @param controller The controller passed to this controller
   * @param name the name of the task
   */
  public TaskController(DiaryController controller, String name) {
    isNew = false;
    this.diary = controller;
    this.name = name;
  }

  @Override
  public void run() {
    initButtons();
    initText();
  }

  private void initButtons() {
    taskDay.getItems()
        .addAll(Day.SUNDAY, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY,
            Day.SATURDAY);
    taskSave.setOnAction(e -> {
      delete();
      Task task = new Task(taskName.getText(), taskDay.getValue(), taskDescription.getText());
      diary.getTaskList().add(task);
      Stage stage = (Stage) taskSave.getScene().getWindow();
      stage.close();
      diary.updateDiaryController();
    });

    taskDelete.setOnAction(e -> {
      delete();
      Stage stage = (Stage) taskDelete.getScene().getWindow();
      stage.close();
      diary.updateDiaryController();
    });

    if (isNew) {
      taskSave.setDisable(true);
    }

    // Event handlers for the text field, combo box, and text area
    taskName.textProperty().addListener((observable, oldValue, newValue) -> {
      // Enable the button if the text field, combo box, and text area are not empty
      taskSave.setDisable(
          newValue.trim().isEmpty()
              || taskDay.getSelectionModel().isEmpty()
              || taskDescription.getText().trim().isEmpty()
      );
    });

    taskDay.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          // Enable the button if the text field, combo box, and text area are not empty
          taskSave.setDisable(
              newValue == null
                  || taskName.getText().trim().isEmpty()
                  || taskDescription.getText().trim().isEmpty()
          );
        });

    taskDescription.textProperty().addListener((observable, oldValue, newValue) -> {
      // Enable the button if the text field, combo box, and text area are not empty
      taskSave.setDisable(
          newValue.trim().isEmpty()
              || taskName.getText().trim().isEmpty()
              || taskDay.getSelectionModel().isEmpty()
      );
    });
  }

  private void delete() {
    for (int i = 0; i < diary.getTaskList().size(); i++) {
      Task e = diary.getTaskList().get(i);
      if (Objects.equals(e.getName(), name)) {
        diary.getTaskList().remove(e);
      }
    }
  }


  private void initText() {
    for (int i = 0; i < diary.getTaskList().size(); i++) {
      Task e = diary.getTaskList().get(i);
      if (Objects.equals(e.getName(), name)) {
        taskName.setText(e.getName());
        taskDay.setValue(e.getDay());
        taskDescription.setText(e.getDescription());
      }
    }
  }
}
