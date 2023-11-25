package cs3500.pa05.model.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A Json to represent a week saved.
 *
 * @param name       name of the week
 * @param commits    max number of commitments
 * @param tasks      list of task jsons
 * @param events     list of event jsons
 * @param theme      theme json
 * @param quoteNotes list of strings representing the quotes and notes of the week.
 */
public record WeekJson(
    @JsonProperty("name") String name,
    @JsonProperty("number-of-commits") int commits,
    @JsonProperty("tasks") List<TaskJson> tasks,
    @JsonProperty("events") List<EventJson> events,
    @JsonProperty("theme") ThemeJson theme,
    @JsonProperty("quotes-and-notes") List<String> quoteNotes
) {
}
