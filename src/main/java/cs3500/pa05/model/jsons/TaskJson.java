package cs3500.pa05.model.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json to represent a Task
 *
 * @param name        name of the task
 * @param day         String version of the day of the task
 * @param description description of the task
 * @param isComplete  whether the task is complete or not.
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("day") String day,
    @JsonProperty("description") String description,
    @JsonProperty("isComplete") boolean isComplete
) {
}
