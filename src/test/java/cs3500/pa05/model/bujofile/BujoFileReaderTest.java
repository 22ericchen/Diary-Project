package cs3500.pa05.model.bujofile;



import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.themes.AbstractTheme;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BujoFileReaderTest {

  private BujoFileReader reader;

  @BeforeEach
  public void setUp() {
    Path tempFile;
    try {
      tempFile = Files.createTempFile("test1", "bujo");
      Files.writeString(tempFile, "{\n"
          + "    \"name\": \"Hello World\",\n"
          + "    \"number-of-commits\": 3,\n"
          + "    \"tasks\": [\n"
          + "        {\n"
          + "            \"name\": \"name\",\n"
          + "            \"day\": \"MONDAY\",\n"
          + "            \"description\": \"Hello again\",\n"
          + "            \"isComplete\": false\n"
          + "        }\n"
          + "    ],\n"
          + "    \"events\": [\n"
          + "        {\n"
          + "            \"name\": \"name\",\n"
          + "            \"day\": \"SUNDAY\",\n"
          + "            \"start-time\": \"10:00PM\",\n"
          + "            \"duration\": \"12H30M\",\n"
          + "            \"description\": \"Hello x3\"\n"
          + "        },\n"
          + "        {\n"
          + "            \"name\": \"name\",\n"
          + "            \"day\": \"TUESDAY\",\n"
          + "            \"start-time\": \"10:00PM\",\n"
          + "            \"duration\": \"12H30M\",\n"
          + "            \"description\": \"Hello x3\"\n"
          + "        },\n"
          + "        {\n"
          + "            \"name\": \"name\",\n"
          + "            \"day\": \"WEDNESDAY\",\n"
          + "            \"start-time\": \"10:00PM\",\n"
          + "            \"duration\": \"12H30M\",\n"
          + "            \"description\": \"Hello x3\"\n"
          + "        },\n"
          + "        {\n"
          + "            \"name\": \"name\",\n"
          + "            \"day\": \"THURSDAY\",\n"
          + "            \"start-time\": \"10:00PM\",\n"
          + "            \"duration\": \"12H30M\",\n"
          + "            \"description\": \"Hello x3\"\n"
          + "        },\n"
          + "        {\n"
          + "            \"name\": \"name\",\n"
          + "            \"day\": \"FRIDAY\",\n"
          + "            \"start-time\": \"10:00PM\",\n"
          + "            \"duration\": \"12H30M\",\n"
          + "            \"description\": \"Hello x3\"\n"
          + "        },\n"
          + "        {\n"
          + "            \"name\": \"name\",\n"
          + "            \"day\": \"SATURDAY\",\n"
          + "            \"start-time\": \"10:00PM\",\n"
          + "            \"duration\": \"12H30M\",\n"
          + "            \"description\": \"Hello x3\"\n"
          + "        }\n"
          + "    ],\n"
          + "    \"theme\": {\n"
          + "        \"background\": \"#D6BB9E\",\n"
          + "        \"foreground\": \"#E6DACA\",\n"
          + "        \"text-color\": \"#000000\"\n"
          + "    },\n"
          + "    \"quotes-and-notes\": [\n"
          + "        \"String\",\n"
          + "        \"note\"\n"
          + "    ]\n"
          + "}", StandardOpenOption.WRITE);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    reader = new BujoFileReader();
    try {
      reader.readFile(tempFile.toAbsolutePath().toString());
    } catch (Exception e) {
      System.err.println("Not working" + e.getMessage());
      throw new RuntimeException(tempFile.toAbsolutePath().toString());
    }
  }

  @Test
  public void getEventsTest() {
    List<Event> events = reader.getEvents();
    Event event = events.get(0);
    assertEquals("name", event.name());
  }

  @Test
  public void getTasksTest() {
    List<Task> tasks = reader.getTasks();
    assertEquals(1, tasks.size());
  }

  @Test
  public void getThemeTest() {
    AbstractTheme theme = reader.getTheme();
    assertEquals("0xd6bb9eff", theme.getBackgroundColor());
    assertEquals("0xe6dacaff", theme.getCalendarColor());
    assertEquals("System Regular", theme.getFont());
    assertEquals("0x000000ff", theme.getTextColor());
  }

  @Test
  public void getQuoteNotesTest() {
    List<String> quotes = reader.getQuoteNotes();
    String expected = "String";
    assertEquals(expected, quotes.get(0));
  }

  @Test
  public void getMaxCommitmentsTest() {
    int expected = 3;
    assertEquals(expected, reader.getMaxNumberCommitments());
  }

  @Test
  public void getNameTest() {
    String expected = "Hello World";
    assertEquals(expected, reader.getName());
  }
}