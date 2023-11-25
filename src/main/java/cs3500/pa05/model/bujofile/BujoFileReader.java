package cs3500.pa05.model.bujofile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Duration;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Time;
import cs3500.pa05.model.jsons.EventJson;
import cs3500.pa05.model.jsons.TaskJson;
import cs3500.pa05.model.jsons.WeekJson;
import cs3500.pa05.model.themes.AbstractTheme;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A class intended to read from a bujo file and collect all of its saved
 * information in the form of Jsons.
 */
public class BujoFileReader {

  private final List<Task> tasks;
  private final List<Event> events;
  private final ObjectMapper mapper = new ObjectMapper();
  private String name;
  private final List<String> quoteNotes;
  private AbstractTheme theme;
  private int maxNumberCommitments;

  /**
   * An instance of the BujoFileReader class.
   */
  public BujoFileReader() {
    tasks = new ArrayList<>();
    events = new ArrayList<>();
    quoteNotes = new ArrayList<>();
  }

  /**
   * Reads the file. Uses Jsons to translate the bujo file.
   *
   * @param pathName a String that represents the path name of the file desired.
   */
  public void readFile(String pathName) {
    try {
      Scanner scanner = new Scanner(new File(pathName));
      StringBuilder weekString = new StringBuilder();
      while (scanner.hasNextLine()) {
        weekString.append(scanner.nextLine());
      }
      JsonParser parser = this.mapper.getFactory().createParser(String.valueOf(weekString));
      WeekJson weekJson = parser.readValueAs(WeekJson.class);


      for (EventJson eventJson : weekJson.events()) {
        Event event = new Event(eventJson.name(), new Time(eventJson.startTime()),
            new Duration(eventJson.duration()), stringToDay(eventJson.day()),
            eventJson.description());
        events.add(event);
      }

      for (TaskJson taskJson : weekJson.tasks()) {
        Task task = new Task(taskJson.name(), stringToDay(taskJson.day()), taskJson.description(),
            taskJson.isComplete());
        tasks.add(task);
      }
      quoteNotes.addAll(weekJson.quoteNotes());
      theme = new AbstractTheme(Color.valueOf(weekJson.theme().foregroundHexCode()),
          Color.valueOf(weekJson.theme().backgroundHexCode()),
          Color.valueOf(weekJson.theme().textColor()),
          Font.font(weekJson.theme().font()));
      maxNumberCommitments = weekJson.commits();
      name = weekJson.name();
      parser.close();
      scanner.close();
    } catch (IOException e) {
      System.err.println("Unexpected parsing error or scanner error: " + e.getMessage());
    }
  }

  /**
   * Converts the String version of a specified day to a Day object.
   *
   * @param string provided String day
   * @return appropriate day object.
   */
  private Day stringToDay(String string) {
    switch (string) {
      case "MONDAY" -> {
        return Day.MONDAY;
      }
      case "TUESDAY" -> {
        return Day.TUESDAY;
      }
      case "WEDNESDAY" -> {
        return Day.WEDNESDAY;
      }
      case "THURSDAY" -> {
        return Day.THURSDAY;
      }
      case "FRIDAY" -> {
        return Day.FRIDAY;
      }
      case "SATURDAY" -> {
        return Day.SATURDAY;
      }
      case "SUNDAY" -> {
        return Day.SUNDAY;
      }
      default -> {
        return null;
      }
    }
  }

  /**
   * Gets the events list.
   *
   * @return the events list.
   */
  public List<Event> getEvents() {
    return events;
  }

  /**
   * Gets tasks
   *
   * @return the tasks list
   */
  public List<Task> getTasks() {
    return tasks;
  }

  /**
   * Gets the theme
   *
   * @return the theme
   */
  public AbstractTheme getTheme() {
    return theme;
  }

  /**
   * Gets the quotes and notes
   *
   * @return list of Strings being the notes.
   */
  public List<String> getQuoteNotes() {
    return quoteNotes;
  }

  /**
   * Gets the saved maximum number of commitments.
   *
   * @return the number of commitments maximum
   */
  public int getMaxNumberCommitments() {
    return maxNumberCommitments;
  }

  /**
   * Gets the name of the week.
   *
   * @return the name.
   */
  public String getName() {
    return name;
  }
}
