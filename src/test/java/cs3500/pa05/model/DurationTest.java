package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DurationTest {
  private Duration testDuration;

  @BeforeEach
  public void setUp() {
    testDuration = new Duration("10H3M");
    assertNotNull(testDuration);
  }

  @Test
  public void testHours() {
    assertEquals(10, testDuration.getHours());
  }

  @Test
  public void testMin() {
    assertEquals(3, testDuration.getMinutes());
  }

  @Test
  public void testToString() {
    assertEquals("10H3M", testDuration.toString());
  }
}