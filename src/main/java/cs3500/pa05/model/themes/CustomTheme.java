package cs3500.pa05.model.themes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Represents a custom theme.
 */
public class CustomTheme extends AbstractTheme {

  /**
   * Creates a theme.
   *
   * @param calendarColor   color of the calendar
   * @param backgroundColor color of the background
   * @param textColor       color of the text
   * @param font            font type of the text
   */
  public CustomTheme(Color calendarColor, Color backgroundColor, Color textColor, Font font) {
    super(calendarColor, backgroundColor, textColor, font);
  }

  /**
   * Represents a builder for a theme.
   */
  public static class ThemeBuilder {
    private Color calendarColor;
    private Color backgroundColor;
    private Color textColor;
    private Font font;

    /**
     * Sets the ThemeBuilder properties to the properties of a specified theme
     *
     * @param theme theme to set the ThemeBuilder to
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder setTheme(AbstractTheme theme) {
      this.calendarColor = Color.valueOf(theme.getCalendarColor());
      this.backgroundColor = Color.valueOf(theme.getBackgroundColor());
      this.textColor = Color.valueOf(theme.getTextColor());
      this.font = Font.font(theme.getFont());
      return this;
    }

    /**
     * Sets the calendar color to a specified color.
     *
     * @param calendarColor color to set the calendar color to
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder setCalendarColor(Color calendarColor) {
      this.calendarColor = calendarColor;
      return this;
    }

    /**
     * Sets the background color to a specified color.
     *
     * @param backgroundColor color to set the background color to
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder setBackgroundColor(Color backgroundColor) {
      this.backgroundColor = backgroundColor;
      return this;
    }

    /**
     * Sets the text color to a specified color.
     *
     * @param textColor color to set the text color to
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder setTextColor(Color textColor) {
      this.textColor = textColor;
      return this;
    }

    /**
     * Sets the font to a specified font.
     *
     * @param font font to set text to
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder setFont(Font font) {
      this.font = font;
      return this;
    }

    /**
     * Sets a brown theme.
     *
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder brown() {
      backgroundColor = Color.valueOf("#D6BB9E");
      calendarColor = Color.valueOf("#E6DACA");
      textColor = Color.BLACK;
      font = Font.font("Arial");
      return this;
    }

    /**
     * Sets a blue theme.
     *
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder blue() {
      backgroundColor = Color.valueOf("#ABDFF6");
      calendarColor = Color.valueOf("#D6E6F1");
      textColor = Color.BLACK;
      font = Font.font("Arial");
      return this;
    }

    /**
     * Sets a green theme.
     *
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder green() {
      backgroundColor = Color.valueOf("#78CCB2");
      calendarColor = Color.valueOf("#C1ECDF");
      textColor = Color.BLACK;
      font = Font.font("Arial");
      return this;
    }

    /**
     * Sets a red theme.
     *
     * @return the updated ThemeBuilder
     */
    public ThemeBuilder red() {
      backgroundColor = Color.MAROON;
      calendarColor = Color.valueOf("#990000");
      textColor = Color.valueOf("#ffff99");
      font = Font.font("Arial");
      return this;
    }

    /**
     * Generates a theme from the ThemeBuilder.
     *
     * @return theme built from the ThemeBuilder
     */
    public AbstractTheme build() {
      return new AbstractTheme(this.calendarColor, this.backgroundColor, this.textColor, this.font);
    }

  }
}
