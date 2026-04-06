package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static final String MESSAGE_INVALID_SORT_FIELD =
            "Invalid sort field! Supported field prefixes: n/, r/";

    private static final Pattern SORT_ARGUMENT_PATTERN =
            Pattern.compile("\\s*-sort\\s+(?<field>\\S+)\\s*", Pattern.CASE_INSENSITIVE);

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        }

        Matcher matcher = SORT_ARGUMENT_PATTERN.matcher(trimmedArgs);
        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String fieldPrefix = matcher.group("field").toLowerCase();
        assert fieldPrefix.endsWith("/") : "Sort field should be a prefix token ending with '/'";
        assert !fieldPrefix.isBlank() : "Sort field prefix should not be blank";

        switch (fieldPrefix) {
        case "n/":
            return new ListCommand("name", (p1, p2) -> p1.getName().fullName
                    .compareToIgnoreCase(p2.getName().fullName));
        case "r/":
            return new ListCommand("room", (p1, p2) -> p1.getRoom().compareTo(p2.getRoom()));
        default:
            throw new ParseException(MESSAGE_INVALID_SORT_FIELD);
        }
    }
}
