package cs3500.pa05.model.bujofile;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.jsons.EventJson;
import cs3500.pa05.model.jsons.JsonUtils;
import cs3500.pa05.model.jsons.TaskJson;
import cs3500.pa05.model.jsons.ThemeJson;
import cs3500.pa05.model.jsons.WeekJson;
import cs3500.pa05.model.themes.AbstractTheme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to save information stored in a bullet journal to a bujo file.
 */
public class BujoFileSaver {

  private final String pathName;

  /**
   * An instance of the BujoFileSaver class.
   *
   * @param pathName a String representing the pathname of the bujo file destination.
   */
  public BujoFileSaver(String pathName) {
    this.pathName = pathName;
  }

  /**
   * Saves the information provided to a new bujo file or rewrites an existing one.
   *
   * @param name           String representing the name of the week to be saved.
   * @param numCommitments the maximum number of commitments that the user wants for the week.
   * @param tasks          List of Tasks from the week
   * @param events         List of Events from the week.
   * @param theme          The theme saved by the user in that week.
   * @param quotes         A list of Strings representing everything to go into the quotes
   *                       and notes section.
   */
  public void save(String name, int numCommitments, List<Task> tasks, List<Event> events,
                   AbstractTheme theme, List<String> quotes) {
    File file = new File(pathName);
    try {
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(formatFileString(name, numCommitments, tasks, events, theme, quotes));
      fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * Formats all the information provided into a String to go into the file.
   *
   * @param name           String representing the name of the week to be saved.
   * @param numCommitments the maximum number of commitments that the user wants for the week.
   * @param tasks          List of Tasks from the week
   * @param events         List of Events from the week.
   * @param theme          The theme saved by the user in that week.
   * @param quotes         A list of Strings representing everything to go into the quotes
   *                       and notes section.
   * @return the formatted string
   */
  private String formatFileString(String name, int numCommitments, List<Task> tasks,
                                  List<Event> events, AbstractTheme theme, List<String> quotes) {
    List<TaskJson> taskJsons = new ArrayList<>();
    for (Task task : tasks) {
      taskJsons.add(new TaskJson(task.getName(), task.getDay().toString(), task.getDescription(),
          task.isComplete()));
    }
    List<EventJson> eventJsons = new ArrayList<>();
    for (Event event : events) {
      eventJsons.add(new EventJson(event.name(), event.day().toString(),
          event.startTime().toString(), event.duration().toString(),
          event.description()));
    }
    ThemeJson themeJson = new ThemeJson(theme.getBackgroundColor(),
        theme.getCalendarColor(), theme.getTextColor(),
        theme.getFont());
    return JsonUtils.serializeRecord(new WeekJson(name, numCommitments, taskJsons, eventJsons,
        themeJson, quotes)).toString();
  }
}
