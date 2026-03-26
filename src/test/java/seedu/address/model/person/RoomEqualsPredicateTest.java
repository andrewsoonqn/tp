package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class RoomEqualsPredicateTest {

    @Test
    public void equals() {
        String roomA = "#14-2";
        String roomB = "#3-1";

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
        RoomEqualsPredicate predicate = new RoomEqualsPredicate("#14-2");

        assertTrue(predicate.test(new PersonBuilder().withRoom("#14-203-D").build()));
    }

    @Test
    public void test_roomDoesNotMatch_returnsFalse() {
        RoomEqualsPredicate predicate = new RoomEqualsPredicate("#14-2");

        assertFalse(predicate.test(new PersonBuilder().withRoom("#3-118-A").build()));
    }

    @Test
    public void toStringMethod() {
        RoomEqualsPredicate predicate = new RoomEqualsPredicate("#14-2");

        String expected = "RoomEqualsPredicate[roomFragment=#14-2]";
        assertEquals(expected, predicate.toString());
    }
}

