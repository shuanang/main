package seedu.divelog.logic.parser;

import seedu.divelog.commons.core.index.Index;
import seedu.divelog.commons.util.StringUtil;
import seedu.divelog.logic.parser.exceptions.ParseException;
import seedu.divelog.model.dive.DepthProfile;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DEPTH = "Depth must be a number.";
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

    /**
     * @author arjo
     * Parses depth profile
     * @param depth
     * @return a Depth Profile object.
     */
    public static DepthProfile parseDepth(String depth) throws ParseException {
        try {
            return new DepthProfile(Float.valueOf(depth));
        } catch (NumberFormatException n) {
            throw new ParseException(MESSAGE_INVALID_DEPTH);
        }
    }
}
