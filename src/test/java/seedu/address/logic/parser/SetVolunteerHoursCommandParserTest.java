package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.HOURS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HOURS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOURS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.testdata.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetVolunteerHoursCommand;
import seedu.address.model.person.Hours;

public class SetVolunteerHoursCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetVolunteerHoursCommand.MESSAGE_USAGE);

    private final SetVolunteerHoursCommandParser parser = new SetVolunteerHoursCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", SetVolunteerHoursCommand.MESSAGE_NOT_EDITED);

        // no index and field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 hr/ 43", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_HOURS_DESC, Hours.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + HOURS_DESC_AMY;
        SetVolunteerHoursCommand expectedCommand = new SetVolunteerHoursCommand(targetIndex,
                VALID_HOURS_AMY);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
