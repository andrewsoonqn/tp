package seedu.address.model.tag;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Enumeration of built-in tag types.
 */
public enum DefaultTagEnum {
    VEGETARIAN("vegetarian"),
    VEGAN("vegan"),
    HALAL("halal"),
    ALLERGIES("allergies");

    private static final String MESSAGE_VALID_DEFAULT_TAGS = Arrays.stream(values())
            .map(DefaultTagEnum::getDisplayName)
            .collect(Collectors.joining(", "));

    private final String displayName;

    DefaultTagEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Converts a string to a built-in tag type.
     */
    public static DefaultTagEnum fromString(String input) {
        assert input != null : "Tag input should not be null";
        String normalized = input.trim();

        for (DefaultTagEnum type : DefaultTagEnum.values()) {
            if (type.displayName.equals(normalized)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                "Tags must be one of the following: " + MESSAGE_VALID_DEFAULT_TAGS);
    }

    /**
     * Returns true if the input matches one of the built-in tags.
     */
    public static boolean isDefaultTagName(String input) {
        try {
            fromString(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
