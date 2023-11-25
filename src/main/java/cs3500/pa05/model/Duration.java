package cs3500.pa05.model;

/**
 * Represents the duration of an event.
 */
public class Duration {
  private final int hours;
  private final int minutes;

  /**
   * Creates a new Duration object from its String representation.
   *
   * @param duration String representation of a duration
   */
  public Duration(String duration) {
    //Formatted like XXHXXM
    hours = Integer.parseInt(duration.substring(0, duration.indexOf('H')));
    minutes =
        Integer.parseInt(duration.substring(duration.indexOf('H') + 1, duration.indexOf('M')));
  }

  @Override
  public String toString() {
    return hours + "H" + minutes + "M";
  }

  /**
   * Getter for the hours in the duration.
   *
   * @return integer number of hours
   */
  public int getHours() {
    return hours;
  }

  /**
   * Getter for the minutes in the duration.
   *
   * @return integer number of minutes
   */
  public int getMinutes() {
    return minutes;
  }
}
