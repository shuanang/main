package seedu.divelog.logic.parser;

import java.util.stream.Stream;

import seedu.divelog.commons.core.Messages;
import seedu.divelog.logic.commands.AddCommand;
import seedu.divelog.logic.parser.exceptions.ParseException;
import seedu.divelog.model.dive.Date;
import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.Location;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.Time;

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
                        CliSyntax.PREFIX_LOCATION);

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

        checkTimeformat(argMultimap);
        checkDateformat(argMultimap);

        Date dateStart = new Date(argMultimap.getValue(CliSyntax.PREFIX_DATE_START).get());
        Time startTime = new Time(argMultimap.getValue(CliSyntax.PREFIX_TIME_START).get());
        Date dateEnd = new Date(argMultimap.getValue(CliSyntax.PREFIX_DATE_END).get());
        Time endTime = new Time(argMultimap.getValue(CliSyntax.PREFIX_TIME_END).get());
        Time safetyStop = new Time(argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).get());
        PressureGroup pressureGroupAtBegining =
                new PressureGroup(argMultimap.getValue(CliSyntax.PREFIX_PRESSURE_GROUP_START).get());
        PressureGroup pressureGroupAtEnd =
                new PressureGroup(argMultimap.getValue(CliSyntax.PREFIX_PRESSURE_GROUP_END).get());
        Location location =
                new Location(argMultimap.getValue(CliSyntax.PREFIX_LOCATION).get());
        DepthProfile depthProfile = ParserUtil.parseDepth(argMultimap.getValue(CliSyntax.PREFIX_DEPTH).get());
        DiveSession dive =
                new DiveSession(dateStart, startTime, safetyStop, dateEnd, endTime, pressureGroupAtBegining,
                        pressureGroupAtEnd, location, depthProfile);

        return new AddCommand(dive);
    }

    public void checkTimeformat(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(CliSyntax.PREFIX_TIME_START).get().length() != 4
            || argMultimap.getValue(CliSyntax.PREFIX_TIME_END).get().length() != 4
            || argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).get().length() != 4) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_TIME_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        try {
            int g=Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_TIME_END).get());
            int f=Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).get());
            int i=Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_TIME_START).get());
        }
        catch (NumberFormatException nfe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_TIME_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    public void checkDateformat(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(CliSyntax.PREFIX_DATE_START).get().length() != 8
            || argMultimap.getValue(CliSyntax.PREFIX_DATE_END).get().length() != 8) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        try {
            int g=Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_DATE_START).get());
            int f=Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_DATE_END).get());
        }
        catch (NumberFormatException nfe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
