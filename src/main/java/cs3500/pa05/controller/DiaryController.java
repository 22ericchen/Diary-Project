package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.bujofile.BujoFileReader;
import cs3500.pa05.model.bujofile.BujoFileSaver;
import cs3500.pa05.model.themes.AbstractTheme;
import cs3500.pa05.model.themes.CustomTheme;
import cs3500.pa05.view.GuiViewImpl;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Controller for Diary inputs
 */
public class DiaryController implements Controller {
  @FXML
  private AnchorPane background;
  @FXML
  private AnchorPane calendar;
  @FXML
  private ComboBox<String> textFont;
  @FXML
  private ColorPicker backgroundColor;
  @FXML
  private ColorPicker calendarColor;
  @FXML
  private ColorPicker textColor;
  @FXML
  private Label constraintLabel;
  @FXML
  private Label entriesLabel;
  @FXML
  private Label taskQueueLabel;
  @FXML
  private Label themeLabel;
  @FXML
  private Label title;
  @FXML
  private Label quotesAndNotesLabel;
  @FXML
  private Scene scene;
  @FXML
  private VBox taskQueue;
  @FXML
  private VBox sun;
  @FXML
  private VBox mon;
  @FXML
  private VBox tue;
  @FXML
  private VBox wed;
  @FXML
  private VBox thu;
  @FXML
  private VBox fri;
  @FXML
  private VBox sat;
  @FXML
  private Button brownTheme;
  @FXML
  private Button blueTheme;
  @FXML
  private Button greenTheme;
  @FXML
  private Button redTheme;
  @FXML
  private Button saveWeek;
  private AbstractTheme theme;
  private CustomTheme.ThemeBuilder tempTheme;
  private final BujoFileSaver fileSaver;
  private final List<Task> taskList;
  private final List<Event> eventList;
  private String weekName;
  private final List<String> quotesAndNotes;
  private int maxNumberOfCommitments;
  private final FullGuiController controller;
  @FXML
  private Button newEventButton;
  @FXML
  private Button newTaskButton;
  @FXML
  private VBox quotesAndNotesBox;
  @FXML
  private Button newNote;
  @FXML
  private TextArea quoteNoteTextArea;
  @FXML
  private TextField maxArea;
  @FXML
  private Button constraintButton;
  @FXML
  private Label userWarning;
  @FXML
  private TextField weekNameField;
  @FXML
  private Button changeWeekName;


  /**
   * Initializes an instance of a DiaryController.
   *
   * @param bujoFileReader reader object that reads bujo files
   * @param bujoFileSaver  writer object that writes to a bujo file
   * @param controller     controller to load all GUIs
   */
  public DiaryController(BujoFileReader bujoFileReader, BujoFileSaver bujoFileSaver,
                         FullGuiController controller) {
    fileSaver = bujoFileSaver;
    this.controller = controller;
    weekName = bujoFileReader.getName();
    maxNumberOfCommitments = bujoFileReader.getMaxNumberCommitments();
    taskList = bujoFileReader.getTasks();
    eventList = bujoFileReader.getEvents();
    quotesAndNotes = bujoFileReader.getQuoteNotes();
    theme = bujoFileReader.getTheme();
  }

  @Override
  public void run() {
    initEvents();
    initTaskQueue();
    initTheme();
    initThemeButtons();
    initButtons();
    initQuotesAndNotes();
    initMaxCommitments();
    initName();
    initTasksInWeek();
  }

  /**
   * Adds the quotes and notes from the given bujo file.
   */
  private void initQuotesAndNotes() {
    if (!quotesAndNotes.isEmpty()) {
      for (String note : quotesAndNotes) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        Label label = new Label(note);
        Button button = new Button("Delete");
        button.setOnAction(e -> handleDeleteQuote(hbox, note));
        label.setPadding(new Insets(5, 25, 0, 10));
        hbox.getChildren().add(label);
        hbox.getChildren().add(button);
        quotesAndNotesBox.getChildren().add(hbox);
      }
    }
  }

  /**
   * Deletes a specified quote.
   *
   * @param hbox  container the quote is stored in
   * @param quote quote to delete
   */
  private void handleDeleteQuote(HBox hbox, String quote) {
    quotesAndNotes.remove(quote);
    quotesAndNotesBox.getChildren().remove(hbox);
  }

  /**
   * Sets up the button functions in the GUI.
   */
  private void initButtons() {
    newEventButton.setOnAction(e -> handleNewEvent());
    Label eventLabel = new Label("Create new event");
    eventLabel.setVisible(false);
    Tooltip tooltip = new Tooltip();
    tooltip.setGraphic(eventLabel);
    Tooltip.install(newEventButton, tooltip);

    newEventButton.setOnMouseEntered(event -> {
      eventLabel.setVisible(true);
      tooltip.show(newEventButton, event.getScreenX(), event.getScreenY() + 20);
    });

    newEventButton.setOnMouseExited(event -> {
      eventLabel.setVisible(false);
      tooltip.hide();
    });

    newTaskButton.setOnAction(e -> handleNewTask());
    Label taskLabel = new Label("Create new task");
    taskLabel.setVisible(false);
    Tooltip tooltip2 = new Tooltip();
    tooltip2.setGraphic(taskLabel);
    Tooltip.install(newTaskButton, tooltip2);

    newTaskButton.setOnMouseEntered(event -> {
      taskLabel.setVisible(true);
      tooltip2.show(newTaskButton, event.getScreenX(), event.getScreenY() + 20);
    });

    newTaskButton.setOnMouseExited(event -> {
      taskLabel.setVisible(false);
      tooltip2.hide();
    });
    saveWeek.setOnAction(e -> handleSaveWeek());

    newNote.setOnMouseClicked(e -> handleNewNote());
  }

  /**
   * Sets up the maximum number of commitments.
   */
  private void initMaxCommitments() {
    constraintLabel.setText("Maximum Commitments: " + maxNumberOfCommitments);
    constraintButton.setOnMouseClicked(e -> handleMaxCommitment());
  }

  /**
   * Handles the action of setting maximum commitments.
   */
  private void handleMaxCommitment() {
    try {
      if (!maxArea.getText().trim().isEmpty()
          && maxNumberOfCommitments != Integer.parseInt(maxArea.getText().trim())) {
        maxNumberOfCommitments = Integer.parseInt(maxArea.getText().trim());
        constraintLabel.setText("Maximum Commitments: " + maxNumberOfCommitments);
        maxArea.clear();
        updateDiaryController();
      }
    } catch (NumberFormatException ignore) {
    }
  }

  /**
   * Handles adding a new note.
   */
  private void handleNewNote() {
    if (!quoteNoteTextArea.getText().trim().isEmpty()) {
      quotesAndNotes.add(quoteNoteTextArea.getText());
      updateDiaryController();
      quoteNoteTextArea.clear();
    }
  }

  /**
   * Initializes the name of the week.
   */
  private void initName() {
    title.setText(weekName);
    changeWeekName.setOnMouseClicked(e -> handleNameChange());
  }

  /**
   * Handles changing the name of the week.
   */
  @FXML
  private void handleNameChange() {
    if (!weekNameField.getText().trim().isEmpty()) {
      weekName = weekNameField.getText().trim();
      title.setText(weekName);
      weekNameField.clear();
      updateDiaryController();
    }
  }

  /**
   * Initializes all the events in the diary.
   */
  private void initEvents() {
    for (Event event : eventList) {
      Button button = new Button(event.name() + " (Event)");
      button.setOnMouseClicked(e -> handleEventExpand(event.name()));

      Label eventLabel = new Label(event.name() + "\n" + event.description());
      eventLabel.setVisible(false);
      Tooltip tooltip = new Tooltip();
      tooltip.setGraphic(eventLabel);
      Tooltip.install(button, tooltip);

      button.setOnMouseEntered(eventL -> {
        eventLabel.setVisible(true);
        tooltip.show(button, eventL.getScreenX(), eventL.getScreenY() + 20);
      });

      button.setOnMouseExited(eventL -> {
        eventLabel.setVisible(false);
        tooltip.hide();
      });


      switch (event.day()) {
        case SUNDAY -> sun.getChildren().add(button);
        case MONDAY -> mon.getChildren().add(button);
        case TUESDAY -> tue.getChildren().add(button);
        case WEDNESDAY -> wed.getChildren().add(button);
        case THURSDAY -> thu.getChildren().add(button);
        case FRIDAY -> fri.getChildren().add(button);
        case SATURDAY -> sat.getChildren().add(button);
        default -> throw new IllegalStateException("Unexpected value: " + event.day());
      }
    }
  }

  /**
   * Initializes all the tasks to show in the calendar.
   */
  private void initTasksInWeek() {
    if (taskList.size() > maxNumberOfCommitments) {
      warnUser();
    } else {
      userWarning.setText("Number of commitments: " + taskList.size());
    }
    for (Task task : taskList) {
      Button button = new Button(task.getName() + " (Task)");
      button.setOnMouseClicked(e -> handleTaskExpand(task.getName()));

      Label eventLabel = new Label(task.getName() + "\n" + task.getDescription());
      eventLabel.setVisible(false);
      Tooltip tooltip = new Tooltip();
      tooltip.setGraphic(eventLabel);
      Tooltip.install(button, tooltip);

      button.setOnMouseEntered(eventL -> {
        eventLabel.setVisible(true);
        tooltip.show(button, eventL.getScreenX(), eventL.getScreenY() + 20);
      });

      button.setOnMouseExited(eventL -> {
        eventLabel.setVisible(false);
        tooltip.hide();
      });


      switch (task.getDay()) {
        case SUNDAY -> sun.getChildren().add(button);
        case MONDAY -> mon.getChildren().add(button);
        case TUESDAY -> tue.getChildren().add(button);
        case WEDNESDAY -> wed.getChildren().add(button);
        case THURSDAY -> thu.getChildren().add(button);
        case FRIDAY -> fri.getChildren().add(button);
        case SATURDAY -> sat.getChildren().add(button);
        default -> throw new IllegalStateException("Unexpected value: " + task.getDay());
      }
    }
  }

  /**
   * Gives the user a warning if task limit is exceeded.
   */
  private void warnUser() {
    userWarning.setText("WARNING!! You're exceeding your task limit :)");
  }

  /**
   * Handles expanding the view of an event.
   *
   * @param name name of the event
   */
  private void handleEventExpand(String name) {
    controller.loadNewEventScreen(new EventController(this, name));
  }

  /**
   * Handles expanding the view of a task.
   *
   * @param name name of the task
   */
  private void handleTaskExpand(String name) {
    controller.loadNewTaskScreen(new TaskController(this, name));
  }

  /**
   * Initializes all tasks to show in the task queue.
   */
  private void initTaskQueue() {
    for (Task task : taskList) {
      Label taskLabel = new Label(task.getName());
      taskLabel.setPadding(new Insets(0, 0, 0, 50));
      CheckBox checkBox = new CheckBox();
      HBox box = new HBox();
      if (task.isComplete()) {
        checkBox.setSelected(true);
      }
      checkBox.setOnMouseClicked(e -> handleCompleteTask(task, checkBox));
      box.getChildren().add(taskLabel);
      box.getChildren().add(checkBox);
      taskQueue.getChildren().add(box);
    }
  }

  /**
   * Initializes the theme.
   */
  private void initTheme() {
    tempTheme = new CustomTheme.ThemeBuilder().setTheme(theme);
    changeTextColor(scene.getRoot(), Color.valueOf(theme.getTextColor()));
    changeFont(scene.getRoot(), Font.font(theme.getFont()));
    background.setBackground(Background.fill(Color.valueOf(theme.getBackgroundColor())));
    calendar.setBackground(Background.fill(Color.valueOf(theme.getCalendarColor())));
  }

  /**
   * Initializes the buttons to customize the theme.
   */
  private void initThemeButtons() {
    backgroundColor.setOnAction(e -> {
      background.setBackground(Background.fill(backgroundColor.getValue()));
      tempTheme.setBackgroundColor(backgroundColor.getValue());
    });
    calendarColor.setOnAction(e -> {
      calendar.setBackground(Background.fill(calendarColor.getValue()));
      tempTheme.setCalendarColor(calendarColor.getValue());
    });
    textColor.setOnAction(e -> {
      changeTextColor(scene.getRoot(), textColor.getValue());
      tempTheme.setTextColor(textColor.getValue());
    });

    textFont.getItems().addAll(Font.getFontNames());
    textFont.setOnAction(e -> {
      changeFont(scene.getRoot(), Font.font(textFont.getValue()));
      tempTheme.setFont(Font.font(textFont.getValue()));
    });

    brownTheme.setOnAction(e -> {
      background.setBackground(Background.fill(Color.valueOf("#D6BB9E")));
      calendar.setBackground(Background.fill(Color.valueOf("#E6DACA")));
      changeTextColor(scene.getRoot(), Color.BLACK);
      changeFont(scene.getRoot(), Font.font("Arial"));
      tempTheme.brown();
    });
    blueTheme.setOnAction(e -> {
      background.setBackground(Background.fill(Color.valueOf("#ABDFF6")));
      calendar.setBackground(Background.fill(Color.valueOf("#D6E6F1")));
      changeTextColor(scene.getRoot(), Color.BLACK);
      changeFont(scene.getRoot(), Font.font("Arial"));
      tempTheme.blue();
    });
    greenTheme.setOnAction(e -> {
      background.setBackground(Background.fill(Color.valueOf("#78CCB2")));
      calendar.setBackground(Background.fill(Color.valueOf("#C1ECDF")));
      changeTextColor(scene.getRoot(), Color.BLACK);
      changeFont(scene.getRoot(), Font.font("Arial"));
      tempTheme.green();
    });
    redTheme.setOnAction(e -> {
      background.setBackground(Background.fill(Color.MAROON));
      calendar.setBackground(Background.fill(Color.valueOf("#990000")));
      changeTextColor(scene.getRoot(), Color.valueOf("#ffff99"));
      changeFont(scene.getRoot(), Font.font("Arial"));
      tempTheme.red();
    });
  }

  /**
   * Handles marking a task as complete.
   *
   * @param task     task
   * @param checkBox checkBox corresponding to the task
   */
  @FXML
  public void handleCompleteTask(Task task, CheckBox checkBox) {
    if (checkBox.isSelected()) {
      task.markComplete();
      checkBox.setSelected(true);
    } else {
      task.markIncomplete();
      checkBox.setSelected(false);
    }
  }

  /**
   * Handles action from user to create a new event
   */
  @FXML
  public void handleNewEvent() {
    controller.loadNewEventScreen(new EventController(this));
  }

  /**
   * Handles action from user to create a new task.
   */
  @FXML
  public void handleNewTask() {
    controller.loadNewTaskScreen(new TaskController(this));
  }

  /**
   * Handles action from user to save the week to bujo file.
   */
  @FXML
  public void handleSaveWeek() {
    theme = tempTheme.build();
    fileSaver.save(weekName, maxNumberOfCommitments, taskList, eventList, theme, quotesAndNotes);
  }

  /**
   * Changes the text color of all the labels.
   *
   * @param node  current node in recursive travel
   * @param color to change text color to
   */
  private void changeTextColor(Node node, Color color) {
    if (node instanceof Label) {
      ((Label) node).setTextFill(color);
    } else if (node instanceof javafx.scene.Parent parent) {
      for (javafx.scene.Node child : parent.getChildrenUnmodifiable()) {
        changeTextColor(child, color);
      }
    }
  }

  /**
   * Changes the font of all the labels.
   *
   * @param node current node in recursive travel
   * @param font to change text to
   */
  private void changeFont(Node node, Font font) {
    if (node instanceof Label) {
      if (node.equals(title)) {
        ((Label) node).setFont(Font.font(font.getName(), FontWeight.BOLD, 20));
      } else if (node.equals(constraintLabel)
          || node.equals(taskQueueLabel)
          || node.equals(themeLabel)
          || node.equals(entriesLabel)
          || node.equals(quotesAndNotesLabel)) {
        ((Label) node).setFont(Font.font(font.getName(), FontWeight.BOLD, 16));
      } else {
        ((Label) node).setFont(font);
      }
    } else if (node instanceof javafx.scene.Parent parent) {
      for (javafx.scene.Node child : parent.getChildrenUnmodifiable()) {
        changeFont(child, font);
      }
    }
  }

  /**
   * Updates the bullet journal week view.
   */
  public void updateDiaryController() {
    Stage stage = (Stage) sun.getScene().getWindow();
    stage.close();
    stage = new Stage();
    stage.setScene(new GuiViewImpl(this, "diaryView.fxml").load());
    this.run();
    stage.show();
  }

  /**
   * event list getter
   *
   * @return the List of events
   */
  public List<Event> getEventList() {
    return eventList;
  }

  /**
   * Task list getter
   *
   * @return the List of tasks
   */
  public List<Task> getTaskList() {
    return taskList;
  }
}
