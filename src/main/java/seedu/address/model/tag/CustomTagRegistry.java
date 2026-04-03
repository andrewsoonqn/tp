package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Tracks which custom tags currently exist in the address book.
 */
public class CustomTagRegistry {

    private final Set<Tag> customTags = new HashSet<>();

    /**
     * Returns true if the given tag already exists.
     */
    public boolean contains(Tag tag) {
        requireNonNull(tag);
        return tag.isBuiltInTag() || customTags.contains(tag);
    }

    /**
     * Registers the given tag if it is custom.
     */
    public void register(Tag tag) {
        requireNonNull(tag);
        if (!tag.isBuiltInTag()) {
            customTags.add(tag);
        }
    }

    /**
     * Registers all given tags if they are custom.
     */
    public void registerAll(Collection<Tag> tags) {
        requireNonNull(tags);
        tags.forEach(this::register);
    }

    /**
     * Replaces the tracked custom tags with the given collection.
     */
    public void setCustomTags(Collection<Tag> tags) {
        requireNonNull(tags);
        customTags.clear();
        registerAll(tags);
    }

    /**
     * Returns the currently tracked custom tags.
     */
    public Set<Tag> getCustomTags() {
        return Collections.unmodifiableSet(customTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CustomTagRegistry)) {
            return false;
        }

        CustomTagRegistry otherRegistry = (CustomTagRegistry) other;
        return customTags.equals(otherRegistry.customTags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customTags);
    }
}
