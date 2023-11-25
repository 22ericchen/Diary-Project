package cs3500.pa05.model.themes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Represents an abstract theme.
 */
public class AbstractTheme implements Theme {

  private final Color calendarColor;
  private final Color backgroundColor;
  private final Color textColor;
  private final Font font;

  /**
   * Creates a theme.
   *
   * @param calendarColor   color of the calendar
   * @param backgroundColor color of the background
   * @param textColor       color of the text
   * @param font            font type of the text
   */
  public AbstractTheme(Color calendarColor, Color backgroundColor, Color textColor, Font font) {
    this.calendarColor = calendarColor;
    this.backgroundColor = backgroundColor;
    this.textColor = textColor;
    this.font = font;
  }

  /**
   * Getter for the background color.
   *
   * @return String representation of the background color
   */
  public String getBackgroundColor() {
    return backgroundColor.toString();
  }

  /**
   * Getter for the calendar color.
   *
   * @return String representation of the calendar color
   */
  public String getCalendarColor() {
    return calendarColor.toString();
  }

  /**
   * Getter for the text color.
   *
   * @return String representation of the text color
   */
  public String getTextColor() {
    return textColor.toString();
  }

  /**
   * Getter for the font.
   *
   * @return name of the font
   */
  public String getFont() {
    return font.getName();
  }
}
