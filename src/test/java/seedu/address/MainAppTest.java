package seedu.address;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class MainAppTest {

    private static final Path ADDRESS_BOOK_FILE = Paths.get("data", "addressbook.json");

    @Test
    public void getLoadedSampleDataMessage_returnsExpectedMessage() {
        assertEquals("No saved data found. Loaded sample residents.", MainApp.getLoadedSampleDataMessage());
    }

    @Test
    public void getLoadedSavedDataMessage_returnsExpectedMessage() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(new PersonBuilder().withRoom("#12-123-A").withEmail("sam@example.com")
                .withPhone("81234567").withTags("study-group").build());
        addressBook.addCustomTags(Set.of(new Tag("night-owl")));

        assertEquals("Loaded saved data from data/addressbook.json.\n"
                        + "Persons: 1\n"
                        + "Custom tags (2): night-owl, study-group.",
                MainApp.getLoadedSavedDataMessage(ADDRESS_BOOK_FILE, addressBook));
    }

    @Test
    public void getStartupMessage_withLoadingError_usesOriginalCauseMessage() {
        DataLoadingException exception = new DataLoadingException(
                new IllegalValueException("Persons list contains duplicate person(s)."));

        assertEquals("Persons list contains duplicate person(s). Starting with an empty address book from "
                        + "data/addressbook.json.",
                MainApp.getStartupMessage(ADDRESS_BOOK_FILE, exception));
    }

}
