package cs3500.pa05.model;

/**
 * Represents a single event.
 */
public record Event(String name, Time startTime, Duration duration, Day day, String description) {
}

