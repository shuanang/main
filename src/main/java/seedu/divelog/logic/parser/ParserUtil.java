package seedu.divelog.logic.parser;

import seedu.divelog.commons.core.index.Index;
import seedu.divelog.commons.util.StringUtil;
import seedu.divelog.logic.parser.exceptions.ParseException;
import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.PressureGroup;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DEPTH = "Depth must be a number.";
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
            return new DepthProfile(Float.valueOf(depth));
        } catch (NumberFormatException n) {
            throw new ParseException(MESSAGE_INVALID_DEPTH);
        }
    }
    //@@author arjo129
    /**
     * Parses a pressure group
     * @param pressureGroup - The pressure group as a string
     * @return a {@code PressureGroup} object
     * @throws ParseException if the pressureGroup is not a valid pressure group. {@see PressureGroup#PressureGroup}
     */
    public static PressureGroup parsePressureGroup(String pressureGroup) throws ParseException {
        if (!PressureGroup.isValid(pressureGroup)) {
            throw new ParseException(MESSAGE_INVALID_PRESSURE_GROUP);
        }
        return new PressureGroup(pressureGroup);
    }
}
