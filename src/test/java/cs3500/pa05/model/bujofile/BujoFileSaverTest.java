package cs3500.pa05.model.bujofile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Duration;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Time;
import cs3500.pa05.model.themes.AbstractTheme;
import cs3500.pa05.model.themes.CustomTheme;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BujoFileSaverTest {
  private File file;
  private BujoFileSaver saver;
  private List<Task> taskList;
  private List<Event> events;
  private AbstractTheme theme;
  private List<String> quotes;

  @BeforeEach
  public void initializeFileSaver() {
    Path path;
    try {
      path = Files.createTempFile("test1", "bujo");
      Files.writeString(path, "{\"name\":\"JAck\",\"number-of-commits\""
          + ":5,\"tasks\":[{\"name\":\"Test\",\"day\":\"WEDNESDAY\",\"description\":\""
          + "Please work\",\"isComplete\":true}],\"events\":[{\"name\":\"EventTest\",\"day\""
          + ":\"THURSDAY\",\"start-time\":\"10:00PM\",\"duration\":\"4H30M\",\"description\""
              + ":\"WORK\"}]"
          + ",\"theme\":{\"background\":\"0xd6bb9eff\",\"foreground\":\"0xe6dacaff\""
          + ",\"text-color\":\"0x000000ff\",\"font\":\"Arial\"},\"quotes-and-notes\""
              + ":[\"Hello world\"]}",
          StandardOpenOption.WRITE);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    file = new File(String.valueOf(path));
    saver = new BujoFileSaver(file.toString());
    taskList = new ArrayList<>();
    Task task = new Task("Test", Day.WEDNESDAY, "Please work", true);
    taskList.add(task);
    Event event = new Event("EventTest", new Time("10:00PM"), new Duration("4H30M"),
        Day.THURSDAY, "WORK");
    events = new ArrayList<>();
    events.add(event);
    try {
      theme = new CustomTheme.ThemeBuilder().brown().build();
    } catch (Exception e) {

    }
    quotes = new ArrayList<>();
    quotes.add("Hello world");
  }

  @Test
  public void saveTest() {
    saver.save("JAck", 5, taskList, events, theme, quotes);
    try {
      Scanner scanner = new Scanner(file);
      String actualFile = scanner.nextLine();
      String expectedFileString = "{\"name\":\"JAck\",\"number-of-commits\":5,\"tasks\":[{"
          +
          "\"name\":"
          + "\"Test\",\"day\":\"WEDNESDAY\",\"description\":\"Please work\",\"isComplete\":"
          + "true}],\""
          + "events\":[{\"name\":\"EventTest\",\"day\":\"THURSDAY\",\"start-time\":\"10:00PM\",\""
          + "duration\":\"4H30M\",\"description\":\"WORK\"}],\"theme\":{\"background\":\"0xd6bb9"
          + "eff\",\"foreground\":\"0xe6dacaff\",\"text-color\":\"0x000000ff\",\"font\":\"Arial\""
          + "},\"quotes-and-notes\":[\"Hello world\"]}";
      assertEquals(expectedFileString, actualFile);
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }

  @Test
  public void saveThrowsTest() {
    saver = new BujoFileSaver("/INVALID PATH/");
    assertThrows(RuntimeException.class, () -> saver.save("JAck", 5, taskList,
        events, theme, quotes));
  }


}