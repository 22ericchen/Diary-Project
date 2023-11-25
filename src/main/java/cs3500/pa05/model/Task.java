package cs3500.pa05.model;

/**
 * A class to represent a task to go into a week planner.
 */
public class Task {

  private final String name;
  private final Day day;
  private final String description;
  private boolean isComplete;


  /**
   * An instance of the Task class where the completion is specified.
   *
   * @param name        name of the task
   * @param day         day of the task
   * @param description description of the task
   * @param isComplete  whether the task is complete or not.
   */
  public Task(String name, Day day, String description, boolean isComplete) {
    this.name = name;
    this.day = day;
    this.description = description;
    this.isComplete = isComplete;
  }

  /**
   * An instance of the Task class where the completion is not specified.
   *
   * @param name        name of the task
   * @param day         day of the task
   * @param description description of the task
   */
  public Task(String name, Day day, String description) {
    this.name = name;
    this.day = day;
    this.description = description;
    this.isComplete = false;
  }

  /**
   * Marks the task completed.
   */
  public void markComplete() {
    isComplete = true;
  }

  /**
   * Marks the task incomplete.
   */
  public void markIncomplete() {
    isComplete = false;
  }

  /**
   * Gets the name of the task.
   *
   * @return String name.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the description of the task.
   *
   * @return the string description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the day of the task.
   *
   * @return the day
   */
  public Day getDay() {
    return day;
  }

  /**
   * Returns whether the task is complete.
   *
   * @return boolean is complete or not.
   */
  public boolean isComplete() {
    return isComplete;
  }
}
