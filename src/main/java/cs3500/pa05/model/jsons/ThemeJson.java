package cs3500.pa05.model.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json to represent a theme
 *
 * @param backgroundHexCode hexcode of background
 * @param foregroundHexCode hexcode of foreground
 * @param textColor         color of text
 * @param font              font of text
 */
public record ThemeJson(
    @JsonProperty("background") String backgroundHexCode,
    @JsonProperty("foreground") String foregroundHexCode,
    @JsonProperty("text-color") String textColor,
    @JsonProperty("font") String font
) {
}
