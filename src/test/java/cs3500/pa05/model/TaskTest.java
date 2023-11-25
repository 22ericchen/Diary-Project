package cs3500.pa05.model;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
  private Task testTask1;
  private Task testTask2;

  @BeforeEach
  public void setUp() {
    testTask1 = new Task("test1", Day.WEDNESDAY, "testing", true);
    testTask2 = new Task("test2", Day.THURSDAY, "also testing");
  }

  @Test
  public void testName() {
    assertEquals("test1", testTask1.getName());
    assertEquals("test2", testTask2.getName());
  }

  @Test
  public void testDay() {
    assertEquals(Day.WEDNESDAY, testTask1.getDay());
    assertEquals(Day.THURSDAY, testTask2.getDay());
  }

  @Test
  public void testDescription() {
    assertEquals("testing", testTask1.getDescription());
    assertEquals("also testing", testTask2.getDescription());
  }

  @Test
  public void testCompleted() {
    assertFalse(testTask2.isComplete());
    assertTrue(testTask1.isComplete());

    testTask2.markComplete();
    assertTrue(testTask2.isComplete());

    testTask1.markIncomplete();
    assertFalse(testTask1.isComplete());
  }
}