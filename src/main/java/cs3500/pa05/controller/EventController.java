package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Duration;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Time;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A class to create, edit, and delete events.
 */
public class EventController implements Controller {
  @FXML
  private TextField eventName;
  @FXML
  private ComboBox<Day> eventDay;
  @FXML
  private ComboBox<Integer> startHr;
  @FXML
  private ComboBox<Integer> startMin;
  @FXML
  private ComboBox<String> startAmPm;
  @FXML
  private ComboBox<Integer> durationMin;
  @FXML
  private ComboBox<Integer> durationHr;
  @FXML
  private TextArea eventDescription;
  @FXML
  private Button eventSave;
  @FXML
  private Button eventDelete;

  private final DiaryController diary;
  private String name = "";
  private boolean isNew = true;

  /**
   * An instance of the EventController class without a name passed to it.
   *
   * @param controller DiaryController passed to the instance.
   */
  public EventController(DiaryController controller) {
    this.diary = controller;
  }

  /**
   * An instance of the EventController class with a name passed to it.
   *
   * @param controller DiaryController passed to the instance.
   * @param name name of the event
   */
  public EventController(DiaryController controller, String name) {
    isNew = false;
    this.diary = controller;
    this.name = name;
  }

  @Override
  public void run() {
    initButtons();
    initText();
  }

  /**
   * initializes all the buttons and drop down selections
   */
  private void initButtons() {
    startHr.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    startMin.getItems().addAll(0, 15, 30, 45);
    startAmPm.getItems().addAll("AM", "PM");
    durationHr.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    durationMin.getItems().addAll(0, 15, 30, 45);
    eventDay.getItems()
        .addAll(Day.SUNDAY, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY,
            Day.SATURDAY);

    eventSave.setOnAction(e -> {
      delete();
      String time = startHr.getValue() + ":" + startMin.getValue() + startAmPm.getValue();
      String duration = durationHr.getValue() + "H" + durationMin.getValue() + "M";
      Event event = new Event(eventName.getText(), new Time(time), new Duration(duration),
          eventDay.getValue(), eventDescription.getText());
      diary.getEventList().add(event);
      Stage stage = (Stage) eventSave.getScene().getWindow();
      stage.close();
      diary.updateDiaryController();
    });

    eventDelete.setOnAction(e -> {
      delete();
      Stage stage = (Stage) eventDelete.getScene().getWindow();
      stage.close();
      diary.updateDiaryController();
    });

    if (isNew) {
      eventSave.setDisable(true);
    }
    eventName.textProperty().addListener((observable, oldValue, newValue) -> {
      // Enable the button if the name field is not empty
      eventSave.setDisable(
          newValue.trim().isEmpty()
              || eventDescription.getText().trim().isEmpty()
              || eventDay.getSelectionModel().isEmpty()
              || startHr.getSelectionModel().isEmpty()
              || startMin.getSelectionModel().isEmpty()
              || startAmPm.getSelectionModel().isEmpty()
              || durationHr.getSelectionModel().isEmpty()
              || durationMin.getSelectionModel().isEmpty()
      );
    });

    eventDescription.textProperty().addListener((observable, oldValue, newValue) -> {
      // Enable the button if the name field is not empty
      eventSave.setDisable(
          newValue.trim().isEmpty()
              || eventName.getText().trim().isEmpty()
              || eventDay.getSelectionModel().isEmpty()
              || startHr.getSelectionModel().isEmpty()
              || startMin.getSelectionModel().isEmpty()
              || startAmPm.getSelectionModel().isEmpty()
              || durationHr.getSelectionModel().isEmpty()
              || durationMin.getSelectionModel().isEmpty()
      );
    });

    eventDay.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          // Enable the button if the gender combo box is selected and the name field is not empty
          eventSave.setDisable(newValue == null
              || eventName.getText().trim().isEmpty()
              || eventDescription.getText().trim().isEmpty()
              || startHr.getSelectionModel().isEmpty()
              || startMin.getSelectionModel().isEmpty()
              || startAmPm.getSelectionModel().isEmpty()
              || durationHr.getSelectionModel().isEmpty()
              || durationMin.getSelectionModel().isEmpty());
        });
  }

  /**
   * Deletes an event from the list of events.
   */
  private void delete() {
    for (int i = 0; i < diary.getEventList().size(); i++) {
      Event e = diary.getEventList().get(i);
      if (Objects.equals(e.name(), name)) {
        diary.getEventList().remove(e);
      }
    }
  }

  /**
   * Initializes the text in the event editor window.
   */
  private void initText() {
    for (int i = 0; i < diary.getEventList().size(); i++) {
      Event e = diary.getEventList().get(i);
      if (Objects.equals(e.name(), name)) {
        eventName.setText(e.name());
        eventDay.setValue(e.day());
        startHr.setValue(e.startTime().getHours());
        startMin.setValue(e.startTime().getMinutes());
        if (e.startTime().isMorning()) {
          startAmPm.setValue("AM");
        } else {
          startAmPm.setValue("PM");
        }
        durationHr.setValue(e.duration().getHours());
        durationMin.setValue(e.duration().getMinutes());
        eventDescription.setText(e.description());
      }
    }
  }
}
