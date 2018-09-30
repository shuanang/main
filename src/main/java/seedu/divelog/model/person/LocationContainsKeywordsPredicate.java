package seedu.divelog.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.divelog.commons.util.StringUtil;
import seedu.divelog.model.dive.DiveSession;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class LocationContainsKeywordsPredicate implements Predicate<DiveSession> {
    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(DiveSession dive) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(dive.getLocation().getLocationName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LocationContainsKeywordsPredicate) other).keywords)); // state check
    }

}
