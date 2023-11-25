package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeTest {

  private Time testTime;

  @BeforeEach
  public void setUp() {
    testTime = new Time("1:15AM");
    assertNotNull(testTime);
  }

  @Test
  public void testGetHours() {
    assertEquals(1, testTime.getHours());
  }

  @Test
  public void testGetMin() {
    assertEquals(15, testTime.getMinutes());
  }

  @Test
  public void testToString() {
    assertEquals("1:15AM", testTime.toString());
  }

  @Test
  public void testMorning() {
    assertTrue(testTime.isMorning());
  }
}