package seedu.divelog.logic.parser;

import static seedu.divelog.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.divelog.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.divelog.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.divelog.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.divelog.logic.parser.CommandParserTestUtil.assertParseSuccess;


import org.junit.Test;

import seedu.divelog.logic.commands.AddCommand;

import seedu.divelog.testutil.DiveSessionBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name

    }
}
