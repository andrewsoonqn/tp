package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Room} matches the given room.
 */
public class RoomEqualsPredicate implements Predicate<Person> {

    private final Room room;

    public RoomEqualsPredicate(Room room) {
        this.room = room;
    }

    @Override
    public boolean test(Person person) {
        return person.getRoom().equals(room);
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
        return room.equals(otherPredicate.room);
    }

    @Override
    public String toString() {
        return String.format("RoomEqualsPredicate[room=%s]", room);
    }
}

