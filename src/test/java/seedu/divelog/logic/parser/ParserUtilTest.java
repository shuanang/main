package seedu.divelog.logic.parser;

import static org.junit.Assert.assertEquals;

import static seedu.divelog.logic.parser.ParserUtil.MESSAGE_INVALID_DEPTH;
import static seedu.divelog.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.divelog.testutil.TypicalIndexes.INDEX_FIRST_DIVE;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.divelog.logic.parser.exceptions.ParseException;
import seedu.divelog.model.dive.DepthProfile;

public class ParserUtilTest {
    private static final String WHITESPACE = " \t\r\n";

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void parseIndex_invalidInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIndex("10 a");
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_INVALID_INDEX);
        ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_DIVE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_DIVE, ParserUtil.parseIndex("  1  "));
    }

    //@@author arjo129
    @Test
    public void parseDepth_validInput_success() throws Exception {
        //No white spaces
        assertEquals(new DepthProfile(1.0f), ParserUtil.parseDepth("1.0"));
        //Trailing whitespaces
        assertEquals(new DepthProfile(1.0f), ParserUtil.parseDepth(" 1.0  "));
    }

    //@@author arjo129
    @Test
    public void parseDepth_invalidInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_INVALID_DEPTH);
        ParserUtil.parseDepth("otototo MAMAMAMA");
    }

    //@@author arjo129
    @Test
    public void parseDepth_negativeInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_INVALID_DEPTH);
        ParserUtil.parseDepth("-1");
    }

    //@@author arjo129
    @Test
    public void parseDepth_zeroInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_INVALID_DEPTH);
        ParserUtil.parseDepth("0");
    }
    /*
    * Not yet completed
    * @author Cjunx

    @Test
    public void checkTimeformat_Test() throws ParseException {
        String args = "ds/25102018 ts/0800 (24Hr Format) de/2102018 " +
                "te/0900 ss/084 " +
                "d/15 pg/A pge/B l/Sentosa tz/5";
                ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        CliSyntax.PREFIX_DATE_START,
                        CliSyntax.PREFIX_TIME_START,
                        CliSyntax.PREFIX_DATE_END,
                        CliSyntax.PREFIX_TIME_END,
                        CliSyntax.PREFIX_SAFETY_STOP,
                        CliSyntax.PREFIX_DEPTH,
                        CliSyntax.PREFIX_PRESSURE_GROUP_START,
                        CliSyntax.PREFIX_PRESSURE_GROUP_END,
                        CliSyntax.PREFIX_LOCATION,
                        CliSyntax.PREFIX_TIMEZONE);

        if (!arePrefixesPresent(argMultimap,
                CliSyntax.PREFIX_DATE_START,
                CliSyntax.PREFIX_TIME_START,
                CliSyntax.PREFIX_DATE_END,
                CliSyntax.PREFIX_TIME_END,
                CliSyntax.PREFIX_SAFETY_STOP,
                CliSyntax.PREFIX_DEPTH,
                CliSyntax.PREFIX_PRESSURE_GROUP_START,
                CliSyntax.PREFIX_PRESSURE_GROUP_END,
                CliSyntax.PREFIX_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));
        }

        ParserUtil.checkDateformat(argMultimap);
        //assert
        ParserUtil.checkTimeZoneformat(argMultimap);
        //assert
        ParserUtil.checkTimeformat(argMultimap);
        //assert
    }*/




}
