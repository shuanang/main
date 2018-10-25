package seedu.divelog.logic.parser;

import java.util.stream.Stream;

import seedu.divelog.commons.core.Messages;
import seedu.divelog.logic.commands.AddCommand;
import seedu.divelog.logic.parser.exceptions.ParseException;
import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.Location;
import seedu.divelog.model.dive.OurDate;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.Time;
import seedu.divelog.model.dive.TimeZone;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
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
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }


        ParserUtil.checkTimeformat(argMultimap);
        ParserUtil.checkDateformat(argMultimap);
        ParserUtil.checkTimeZoneformat(argMultimap);

        OurDate dateStart = new OurDate(argMultimap.getValue(CliSyntax.PREFIX_DATE_START).get());
        Time startTime = new Time(argMultimap.getValue(CliSyntax.PREFIX_TIME_START).get());
        OurDate dateEnd = new OurDate(argMultimap.getValue(CliSyntax.PREFIX_DATE_END).get());
        Time endTime = new Time(argMultimap.getValue(CliSyntax.PREFIX_TIME_END).get());
        Time safetyStop = new Time(argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).get());
        PressureGroup pressureGroupAtBegining =
                new PressureGroup(argMultimap.getValue(CliSyntax.PREFIX_PRESSURE_GROUP_START).get());
        PressureGroup pressureGroupAtEnd =
                new PressureGroup(argMultimap.getValue(CliSyntax.PREFIX_PRESSURE_GROUP_END).get());
        Location location =
                new Location(argMultimap.getValue(CliSyntax.PREFIX_LOCATION).get());
        DepthProfile depthProfile = ParserUtil.parseDepth(argMultimap.getValue(CliSyntax.PREFIX_DEPTH).get());
        TimeZone timezone = new TimeZone(argMultimap.getValue(CliSyntax.PREFIX_TIMEZONE).get());
        DiveSession dive =
                new DiveSession(dateStart, startTime, safetyStop, dateEnd, endTime, pressureGroupAtBegining,
                        pressureGroupAtEnd, location, depthProfile, timezone);


        return new AddCommand(dive);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
