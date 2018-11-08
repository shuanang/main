package seedu.divelog.logic.parser;

import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.divelog.commons.core.Messages;
import seedu.divelog.commons.core.index.Index;
import seedu.divelog.commons.util.StringUtil;
import seedu.divelog.logic.commands.AddCommand;
import seedu.divelog.logic.parser.exceptions.ParseException;
import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.exceptions.InvalidTimeException;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DEPTH = "Depth must be a positive number.";
    public static final String MESSAGE_INVALID_PRESSURE_GROUP = "Pressure group must be a single alphabet from A-Z";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }
    //@@author arjo129
    /**
     * Parses depth profile
     * @param depth - The depth in String Format
     * @return a Depth Profile object.
     * @throws ParseException if the depth is not a valid float.
     */
    public static DepthProfile parseDepth(String depth) throws ParseException {
        try {
            float value = Float.valueOf(depth);
            if (value <= 0) {
                throw new ParseException(MESSAGE_INVALID_DEPTH);
            }
            return new DepthProfile(value);
        } catch (NumberFormatException n) {
            throw new ParseException(MESSAGE_INVALID_DEPTH);
        }
    }
    //@@author Cjunx
    /**
     *  Returns true if string given is DATE FORMATTED
     * {@code ArgumentMultimap}.
     */
    public static void checkDateFormat(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(CliSyntax.PREFIX_DATE_START).get().length() != 8
                || argMultimap.getValue(CliSyntax.PREFIX_DATE_END).get().length() != 8) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        try {
            Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_DATE_START).get());
            Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_DATE_END).get());
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     *  throws exception if Time inputs are not in chronological order
     * {@code ArgumentMultimap}.
     */
    public static void checkTimeDateLimit(ArgumentMultimap argMultimap)
            throws java.text.ParseException, InvalidTimeException {
        String startDateString = argMultimap.getValue(CliSyntax.PREFIX_DATE_START).get();
        String endDateString = argMultimap.getValue(CliSyntax.PREFIX_DATE_END).get();
        String startTimeString = argMultimap.getValue(CliSyntax.PREFIX_TIME_START).get();
        String endTimeString = argMultimap.getValue(CliSyntax.PREFIX_TIME_END).get();
        String safetyTimeString = argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).get();

        SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyyHHmm");
        Date startTimeDateDate = inputFormat.parse(startDateString + startTimeString);
        Date endTimeDateDate = inputFormat.parse(endDateString + endTimeString);

        Date safetyEndDateTime = checkSafetyTime(startTimeString, endTimeString, safetyTimeString,
                startDateString, endDateString);

        if (safetyEndDateTime.getTime() - startTimeDateDate.getTime() < 0
                || safetyEndDateTime.getTime() - endTimeDateDate.getTime() > 0) {
            throw new InvalidTimeException(
                    String.format(Messages.MESSAGE_INVALID_DATE_LIMITS, AddCommand.MESSAGE_USAGE));
        }

        if (startTimeDateDate.getTime() - endTimeDateDate.getTime() > 0) {
            throw new InvalidTimeException(
                    String.format(Messages.MESSAGE_INVALID_DATE_LIMITS, AddCommand.MESSAGE_USAGE));
        }
    }
    /**
     * Throws an exception IF edit is now not legit.
     */
    public static void checkEditTimeDateLimit(DiveSession divesession)
            throws java.text.ParseException, InvalidTimeException {
        String startDateString = divesession.getDateStart().getOurDateString();
        String endDateString = divesession.getDateEnd().getOurDateString();
        String startTimeString = divesession.getStart().getTimeString();
        String endTimeString = divesession.getEnd().getTimeString();
        String safetyTimeString = divesession.getSafetyStop().getTimeString();

        SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyyHHmm");

        Date startTimeDateDate = inputFormat.parse(startDateString + startTimeString);
        Date endTimeDateDate = inputFormat.parse(endDateString + endTimeString);

        Date safetyEndDateTime = checkSafetyTime(startTimeString, endTimeString, safetyTimeString,
                startDateString, endDateString);

        if (safetyEndDateTime.getTime() - startTimeDateDate.getTime() < 0
                || safetyEndDateTime.getTime() - endTimeDateDate.getTime() > 0) {
            throw new InvalidTimeException(
                    String.format(Messages.MESSAGE_INVALID_DATE_LIMITS, AddCommand.MESSAGE_USAGE));
        }

        if (startTimeDateDate.getTime() - endTimeDateDate.getTime() > 0) {
            throw new InvalidTimeException(
                    String.format(Messages.MESSAGE_INVALID_DATE_LIMITS, AddCommand.MESSAGE_USAGE));
        }
    }
    /**
     * Determines date of safety time
     */
    private static Date checkSafetyTime(String startTimeString, String endTimeString, String safetyTimeString,
                                        String startDateString, String endDateString) throws java.text.ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyyHHmm");

        Date startTimeDate = timeFormat.parse(startTimeString);
        Date endTimeDate = timeFormat.parse(endTimeString);
        Date safetyTimeDate = timeFormat.parse(safetyTimeString);

        Date safetyEndDateTime = inputFormat.parse(startDateString + safetyTimeString);
        if (startTimeDate.getTime() - endTimeDate.getTime() != 0) {
            if (safetyTimeDate.getTime() - startTimeDate.getTime() > 0) {
                safetyEndDateTime = inputFormat.parse(startDateString + safetyTimeString);
            } else if (safetyTimeDate.getTime() - endTimeDate.getTime() < 0) {
                safetyEndDateTime = inputFormat.parse(endDateString + safetyTimeString);
            }
        }
        return safetyEndDateTime;
    }

    /**
     *  Returns true if string given is TIMEZONE FORMATTED
     * {@code ArgumentMultimap}.
     */
    public static void checkTimeZoneformat(String timeZoneString) throws ParseException {
        int toTest;
        try {
            toTest = Integer.parseInt(timeZoneString);
        } catch (NumberFormatException ex) {
            System.err.println("Illegal Timezone input");
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_TIMEZONE_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (toTest > 12 || toTest < -12 ) {
            System.err.println("Illegal Timezone input");
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_TIMEZONE_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * @param argMultimap
     * @throws ParseException
     */
    public static void checkTimeformat(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(CliSyntax.PREFIX_TIME_START).get().length() != 4
                || argMultimap.getValue(CliSyntax.PREFIX_TIME_END).get().length() != 4
                || argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).get().length() != 4) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_TIME_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        try {
            Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_TIME_END).get());
            Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).get());
            Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_TIME_START).get());
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_TIME_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }
    //@@author

    //@@author arjo129
    /**
     * Parses a pressure group
     * @param pressureGroup - The pressure group as a string
     * @return a {@code PressureGroup} object
     * @throws ParseException if the pressureGroup is not a valid pressure group. {@see PressureGroup#PressureGroup}
     */
    public static PressureGroup parsePressureGroup (String pressureGroup) throws ParseException {
        if (!PressureGroup.isValid(pressureGroup)) {
            throw new ParseException(MESSAGE_INVALID_PRESSURE_GROUP);
        }
        return new PressureGroup(pressureGroup);
    }
}
