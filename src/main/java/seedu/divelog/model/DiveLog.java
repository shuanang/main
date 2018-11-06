package seedu.divelog.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.divelog.commons.enums.SortingMethod;
import seedu.divelog.logic.pressuregroup.exceptions.LimitExceededException;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.DiveSessionList;
import seedu.divelog.model.dive.exceptions.DiveNotFoundException;
import seedu.divelog.model.dive.exceptions.InvalidTimeException;


/**
 * Wraps all data at the divelog-book level
 * Duplicates are not allowed (by .isSameDiveSession comparison)
 */
public class DiveLog implements ReadOnlyDiveLog {

    private final DiveSessionList diveSessions;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        diveSessions = new DiveSessionList();
    }

    public DiveLog() {}

    /**
     * Creates an DiveLog using the DiveSessions in the {@code toBeCopied}
     */
    public DiveLog(ReadOnlyDiveLog toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the dive list with {@code diveSessions}.
     * {@code diveSessions} must not contain duplicate diveSessions.
     * @param diveSessions
     */
    public void setDives(List<DiveSession> diveSessions) {
        this.diveSessions.setDives(diveSessions);
    }

    /**
     * Resets the existing data of this {@code DiveLog} with {@code newData}.
     */
    public void resetData(ReadOnlyDiveLog newData) {
        requireNonNull(newData);

        setDives(newData.getDiveList());
    }

    //// DiveSession-level operations

    /**
     * Returns true if a dive session with the same identity as {@code DiveSession} exists in the divelog book.
     * @param diveSession
     */
    public boolean hasDive(DiveSession diveSession) {
        requireNonNull(diveSession);
        return diveSessions.contains(diveSession);
    }

    /**
     * Adds a dive session to the dive log.
     * The dive session must not already exist in the dive log.
     */
    public void addDive(DiveSession p) {
        diveSessions.add(p);
    }

    /**
     * Replaces the given dive {@code target} in the list with {@code editedDive}.
     * {@code target} must exist in the divelog book.
     * The dive of {@code editedDive} must not be the same as another existing dive session in the divelog book.
     * @param target
     * @param editedDive
     */
    public void updateDive(DiveSession target, DiveSession editedDive) throws DiveNotFoundException {
        requireNonNull(editedDive);
        diveSessions.setDiveSession(target, editedDive);
    }

    /**
     * Removes {@code key} from this {@code DiveLog}.
     * {@code key} must exist in the divelog book.
     */
    public void removeDive(DiveSession key) throws DiveNotFoundException {
        diveSessions.remove(key);
    }

    /**
     * Sorts dive session according to their category
     * @param sortByCategory
     */
    public void sortDiveSession(SortingMethod sortByCategory) {
        diveSessions.sortDiveSession(sortByCategory);
    }

    /**
     * Returns the most recent dive session
     * @return a DiveSession object
     */
    public DiveSession getMostRecentDive() {
        return diveSessions.getMostRecentDive();
    }

    /**
     * This method recalculates dive pressure groups. It assumes the oldest dive has the correct starting pressure.
     *
     */
    public void recalculatePressureGroups() throws LimitExceededException, InvalidTimeException {
        diveSessions.recalculatePressureGroup();
    }
    //// util methods

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(diveSessions.asUnmodifiableObservableList().size() + " diveSessions:\n");
        sb.append(diveSessions.toString());
        return sb.toString();
    }

    @Override
    public ObservableList<DiveSession> getDiveList() {
        return diveSessions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DiveLog // instanceof handles nulls
                && diveSessions.equals(((DiveLog) other).diveSessions));
    }

    @Override
    public int hashCode() {
        return diveSessions.hashCode();
    }
}
