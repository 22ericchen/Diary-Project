package cs3500.pa05.model;

/**
 * A class to represent a time.
 */
public class Time {

  private final int hours;
  private final int minutes;
  private final String timeString;
  private final boolean morning;

  /**
   * An instance of the Time class.
   *
   * @param timeString String to be parsed into a time object.
   */
  public Time(String timeString) {
    hours = Integer.parseInt(timeString.substring(0, timeString.indexOf(":")));
    minutes = Integer.parseInt(
        timeString.substring(timeString.indexOf(":") + 1, timeString.length() - 2));
    this.timeString = timeString;
    this.morning = timeString.endsWith("AM");
  }

  /**
   * Gets the hours of the time.
   *
   * @return an int number of hours.
   */
  public int getHours() {
    return hours;
  }

  /**
   * Gets the minutes of the time.
   *
   * @return an int number of minutes.
   */
  public int getMinutes() {
    return minutes;
  }

  /**
   * Gets whether the time is in the morning.
   *
   * @return boolean whether the time is in the morning.
   */
  public boolean isMorning() {
    return morning;
  }

  /**
   * Gets the toString of the time
   *
   * @return String value of the time.
   */
  public String toString() {
    return this.timeString;
  }

}
