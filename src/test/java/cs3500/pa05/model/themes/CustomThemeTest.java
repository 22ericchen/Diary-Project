package cs3500.pa05.model.themes;



import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.junit.jupiter.api.Test;

class CustomThemeTest {

  @Test
  public void setThemeTest() {
    AbstractTheme original = new CustomTheme(Color.RED, Color.PAPAYAWHIP, Color.CADETBLUE,
        Font.font("Tahoma"));
    AbstractTheme theme = new CustomTheme.ThemeBuilder().setTheme(original).build();
    assertEquals(Color.PAPAYAWHIP.toString(), theme.getBackgroundColor());
    assertEquals(Color.RED.toString(), theme.getCalendarColor());
    assertEquals(Color.CADETBLUE.toString(), theme.getTextColor());
    assertEquals("Tahoma", theme.getFont());
  }

  @Test
  public void setBackgroundColorTest() {
    AbstractTheme theme = new CustomTheme.ThemeBuilder()
        .setBackgroundColor(Color.BURLYWOOD).build();
    assertEquals(Color.BURLYWOOD.toString(), theme.getBackgroundColor());
  }

  @Test
  public void setCalendarColorTest() {
    AbstractTheme theme = new CustomTheme.ThemeBuilder().setCalendarColor(Color.CORNSILK).build();
    assertEquals(Color.CORNSILK.toString(), theme.getCalendarColor());
  }

  @Test
  public void setTextColorTest() {
    AbstractTheme theme = new CustomTheme.ThemeBuilder().setTextColor(Color.GREY).build();
    assertEquals(Color.GREY.toString(), theme.getTextColor());
  }

  @Test
  public void setFontTest() {
    AbstractTheme theme = new CustomTheme.ThemeBuilder().setFont(Font.font("System Regular"))
        .build();
    assertEquals("System Regular", theme.getFont());
  }

  @Test
  public void brownThemeTest() {
    AbstractTheme theme = new CustomTheme.ThemeBuilder().brown().build();
    assertEquals(Color.valueOf("#D6BB9E").toString(), theme.getBackgroundColor());
    assertEquals(Color.valueOf("#E6DACA").toString(), theme.getCalendarColor());
    assertEquals(Color.BLACK.toString(), theme.getTextColor());
    assertEquals("Arial", theme.getFont());
  }

  @Test
  public void blueThemeTest() {
    AbstractTheme theme = new CustomTheme.ThemeBuilder().blue().build();
    assertEquals(Color.valueOf("#ABDFF6").toString(), theme.getBackgroundColor());
    assertEquals(Color.valueOf("#D6E6F1").toString(), theme.getCalendarColor());
    assertEquals(Color.BLACK.toString(), theme.getTextColor());
    assertEquals("Arial", theme.getFont());
  }

  @Test
  public void greenThemeTest() {
    AbstractTheme theme = new CustomTheme.ThemeBuilder().green().build();
    assertEquals(Color.valueOf("#78CCB2").toString(), theme.getBackgroundColor());
    assertEquals(Color.valueOf("#C1ECDF").toString(), theme.getCalendarColor());
    assertEquals(Color.BLACK.toString(), theme.getTextColor());
    assertEquals("Arial", theme.getFont());
  }

  @Test
  public void redThemeTest() {
    AbstractTheme theme = new CustomTheme.ThemeBuilder().red().build();
    assertEquals(Color.MAROON.toString(), theme.getBackgroundColor());
    assertEquals(Color.valueOf("#990000").toString(), theme.getCalendarColor());
    assertEquals(Color.valueOf("#ffff99").toString(), theme.getTextColor());
    assertEquals("Arial", theme.getFont());
  }

}