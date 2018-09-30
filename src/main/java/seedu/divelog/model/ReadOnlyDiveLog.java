package seedu.divelog.model;

import javafx.collections.ObservableList;
import seedu.divelog.model.dive.DiveSession;

/**
 * Unmodifiable view of an divelog book
 */
public interface ReadOnlyDiveLog {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<DiveSession> getDiveList();

}
