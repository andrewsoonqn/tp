package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Room} contains the given room fragment.
 */
public class RoomEqualsPredicate implements Predicate<Person> {

    private final String roomFragment;

    /**
     * Creates a predicate that matches persons whose room contains {@code roomFragment}.
     */
    public RoomEqualsPredicate(String roomFragment) {
        requireNonNull(roomFragment);
        this.roomFragment = roomFragment;
    }

    @Override
    public boolean test(Person person) {
        return person.getRoom().value.toLowerCase().contains(roomFragment.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RoomEqualsPredicate)) {
            return false;
        }

        RoomEqualsPredicate otherPredicate = (RoomEqualsPredicate) other;
        return roomFragment.equals(otherPredicate.roomFragment);
    }

    @Override
    public String toString() {
        return String.format("RoomEqualsPredicate[roomFragment=%s]", roomFragment);
    }
}

