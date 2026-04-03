package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ROOM + "ROOM "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ROOM + "#14-203-D "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "e1234567@u.nus.edu "
            + PREFIX_TAG + "halal "
            + PREFIX_TAG + "allergies";
    public static final String MESSAGE_USAGE_WITH_NEWTAG = COMMAND_WORD + ": Adds a person to the address book, "
            + "including creating new tag(s). "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ROOM + "ROOM "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_NEWTAG + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ROOM + "#14-203-D "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "e1234567@u.nus.edu "
            + PREFIX_TAG + "study-group "
            + PREFIX_NEWTAG;

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_UNKNOWN_TAGS = TagCommandUtil.MESSAGE_UNKNOWN_TAGS;

    private final Person toAdd;
    private final boolean shouldCreateNewTags;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        this(person, false);
    }

    /**
     * Creates an AddCommand to add the specified {@code Person}.
     */
    public AddCommand(Person person, boolean shouldCreateNewTags) {
        requireNonNull(person);
        toAdd = person;
        this.shouldCreateNewTags = shouldCreateNewTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TagCommandUtil.validateKnownTags(model, toAdd.getTags(), shouldCreateNewTags, MESSAGE_USAGE_WITH_NEWTAG);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (shouldCreateNewTags) {
            // Only register new custom tags when explicitly requested.
            model.addCustomTags(toAdd.getTags());
        }
        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd)
                && shouldCreateNewTags == otherAddCommand.shouldCreateNewTags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .add("shouldCreateNewTags", shouldCreateNewTags)
                .toString();
    }
}
