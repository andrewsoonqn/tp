package seedu.address.model.tag;

/**
 * Enumeration of built-in tag types.
 */
public enum DefaultTagType {
    VEGETARIAN("vegetarian"),
    VEGAN("vegan"),
    HALAL("halal"),
    ALLERGIES("allergies");

    private final String displayName;

    DefaultTagType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Converts a string to a built-in tag type.
     */
    public static DefaultTagType fromString(String input) {
        assert input != null : "Tag input should not be null";
        String normalized = input.trim();

        for (DefaultTagType type : DefaultTagType.values()) {
            if (type.displayName.equals(normalized)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                "Tags must be one of the following: vegetarian, vegan, halal, allergies");
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
