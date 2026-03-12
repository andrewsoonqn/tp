package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class RoomEqualsPredicateTest {

    @Test
    public void equals() {
        Room roomA = new Room("#14-203-D");
        Room roomB = new Room("#3-118-A");

        RoomEqualsPredicate firstPredicate = new RoomEqualsPredicate(roomA);
        RoomEqualsPredicate secondPredicate = new RoomEqualsPredicate(roomB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoomEqualsPredicate firstPredicateCopy = new RoomEqualsPredicate(roomA);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different room -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_roomMatches_returnsTrue() {
        Room room = new Room("#14-203-D");
        RoomEqualsPredicate predicate = new RoomEqualsPredicate(room);

        assertTrue(predicate.test(new PersonBuilder().withRoom("#14-203-D").build()));
    }

    @Test
    public void test_roomDoesNotMatch_returnsFalse() {
        Room room = new Room("#14-203-D");
        RoomEqualsPredicate predicate = new RoomEqualsPredicate(room);

        assertFalse(predicate.test(new PersonBuilder().withRoom("#3-118-A").build()));
    }

    @Test
    public void toStringMethod() {
        Room room = new Room("#14-203-D");
        RoomEqualsPredicate predicate = new RoomEqualsPredicate(room);

        String expected = "RoomEqualsPredicate[room=#14-203-D]";
        assertEquals(expected, predicate.toString());
    }
}

