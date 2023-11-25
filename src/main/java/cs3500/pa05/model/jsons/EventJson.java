package cs3500.pa05.model.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json to represent an Event
 *
 * @param name        name of the event.
 * @param day         day of the event as a string
 * @param startTime   starttime of the event as a string
 * @param duration    duration of the event as a string.
 * @param description description of the event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("day") String day,
    @JsonProperty("start-time") String startTime,
    @JsonProperty("duration") String duration,
    @JsonProperty("description") String description

) {
}
