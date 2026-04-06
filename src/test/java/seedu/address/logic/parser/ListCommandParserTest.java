package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HANNAH;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

public class ListCommandParserTest {

    private static final Comparator<Person> DUMMY_COMPARATOR = (p1, p2) -> 0;

    private ListCommandParser parser = new ListCommandParser();

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "", new ListCommand());
        assertParseSuccess(parser, "   ", new ListCommand());
        // equals() only compares field name, so a dummy comparator suffices
        assertParseSuccess(parser, " -sort n/", new ListCommand("name", DUMMY_COMPARATOR));
        assertParseSuccess(parser, " -sort r/", new ListCommand("room", DUMMY_COMPARATOR));
        assertParseSuccess(parser, " -SoRt r/", new ListCommand("room", DUMMY_COMPARATOR));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " random text", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " -sort", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " some preamble -sort n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " -sort invalid/",
                "Invalid sort field! Supported field prefixes: n/, r/");
    }

    @Test
    public void execute_listSortedByName_runsComparator() throws Exception {
        ListCommand command = parser.parse(" -sort n/");
        command.execute(model);

        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HANNAH),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_allSortPrefixes_runComparators() throws Exception {
        parser.parse("-sort n/").execute(model);
        parser.parse("-sort r/").execute(model);
    }
}
