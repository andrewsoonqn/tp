package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMENT_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommentCommand;
import seedu.address.model.person.Comment;

public class CommentCommandParserTest {
    private CommentCommandParser parser = new CommentCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // have comment
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_COMMENT + VALID_COMMENT_AMY;
        CommentCommand expectedCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment(VALID_COMMENT_AMY));
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_blankCommentTreatedAsEmpty_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        CommentCommand expectedCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment(""));

        String userInput = targetIndex.getOneBased() + " " + PREFIX_COMMENT;
        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + " " + PREFIX_COMMENT + "   ";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, CommentCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, CommentCommand.COMMAND_WORD + " " + VALID_COMMENT_AMY, expectedMessage);
    }

    @Test
    public void parse_missingCommentPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE);

        // missing comment prefix (equivalent to user typing "comment 1" without c/)
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + "", expectedMessage);
    }

    @Test
    public void parse_repeatedCommentPrefix_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_COMMENT + "first "
                + PREFIX_COMMENT + "second";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMMENT));
    }
}
