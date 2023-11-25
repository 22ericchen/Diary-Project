package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {
  private Event testEvent;

  @BeforeEach
  public void setUp() {
    testEvent = new Event("test", new Time("1:23AM"), new Duration("6H30M"),
        Day.THURSDAY, "testing");
    assertNotNull(testEvent);
  }

  @Test
  public void testName() {
    assertEquals("test", testEvent.name());
  }

  @Test
  public void testTime() {
    assertEquals(new Time("1:23AM").toString(), testEvent.startTime().toString());
  }

  @Test
  public void testDuration() {
    assertEquals(new Duration("6H30M").toString(), testEvent.duration().toString());
  }

  @Test
  public void testDay() {
    assertEquals(Day.THURSDAY, testEvent.day());
  }

  @Test
  public void testDescription() {
    assertEquals("testing", testEvent.description());
  }
}