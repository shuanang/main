package seedu.divelog.logic.parser;

import static seedu.divelog.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.divelog.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.divelog.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.divelog.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.divelog.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.divelog.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.divelog.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.divelog.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.divelog.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.divelog.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.divelog.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.divelog.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.divelog.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.divelog.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.divelog.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.divelog.testutil.TypicalIndexes.INDEX_FIRST_DIVE;
import static seedu.divelog.testutil.TypicalIndexes.INDEX_SECOND_DIVE;
import static seedu.divelog.testutil.TypicalIndexes.INDEX_THIRD_DIVE;

import org.junit.Test;

import seedu.divelog.commons.core.index.Index;
import seedu.divelog.logic.commands.EditCommand;
import seedu.divelog.logic.commands.EditCommand.EditDiveDescriptor;
import seedu.divelog.model.person.Address;
import seedu.divelog.model.person.Email;
import seedu.divelog.model.person.Name;
import seedu.divelog.model.person.Phone;
import seedu.divelog.model.tag.Tag;
import seedu.divelog.testutil.EditDiveDescriptorBuilder;

public class EditCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
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
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {

    }

    @Test
    public void parse_allFieldsSpecified_success() {

    }

    @Test
    public void parse_someFieldsSpecified_success() {

    }

    @Test
    public void parse_oneFieldSpecified_success() {

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {

    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {

    }

    @Test
    public void parse_resetTags_success() {

    }
}
