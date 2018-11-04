package seedu.divelog.model.dive;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.divelog.commons.core.LogsCenter;
import seedu.divelog.commons.enums.SortingMethod;
import seedu.divelog.commons.util.CollectionUtil;
import seedu.divelog.commons.util.CompareUtil;
import seedu.divelog.model.dive.exceptions.DiveNotFoundException;

/**
 * Stores a list of dives
 */
public class DiveSessionList implements Iterable<DiveSession> {
    private final ObservableList<DiveSession> internalList = FXCollections.observableArrayList();
    /**
     * Returns true if the list contains an equivalent dive session as the given argument.
     */
    public boolean contains(DiveSession toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    //@@author Cjunx
    /**
     * Sorts the InternalList based on Time
     * Can be scaled to sort based on other things
     */
    public void sortDiveSession(SortingMethod sortByCategory) {
        Comparator<DiveSession> dateTimeComparator = (one, two) -> {
            Date dateTime1 = one.getDateTime();
            Date datetime2 = two.getDateTime();
            return dateTime1.compareTo(datetime2);
        };

        switch(sortByCategory) {
        default:
            FXCollections.sort(internalList, dateTimeComparator);
            break;
        }
    }

    /**
     * Gets the most recent dive.
     * @return a handle to the DiveSession
     */
    public DiveSession getMostRecentDive() {
        DiveSession mostRecent = null;
        for (DiveSession diveSession: internalList) {

            if (mostRecent == null) {
                mostRecent = diveSession;
                continue;
            }

            try {
                if (mostRecent.compareTo(diveSession) < 0
                        && CompareUtil.getCurrentDateTime().compareTo(diveSession.getDiveLocalDate()) > 0) {
                    mostRecent = diveSession;
                }
            } catch (Exception e) {
                //This will technically never occur due to input checking
                Logger log = LogsCenter.getLogger(DiveSessionList.class);
                log.severe("Something went wrong decoding the divelist time: " + e.toString());
            }
        }
        return mostRecent;
    }

    /**
     * Adds a Dive Session to the list.
     * The dive session must not already exist in the list.
     * If planning mode, adds to planningInternalList;
     */
    public void add(DiveSession toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
        sortDiveSession(SortingMethod.TIME);
    }

    /**
     * Replaces the dive session {@code target} in the list with {@code editedDiveSession}.
     * {@code target} must exist in the list.
     * The dive of {@code editedDiveSession} must not be the same as another existing dive session in the list.
     */
    public void setDiveSession(DiveSession target, DiveSession editedDiveSession) throws DiveNotFoundException {
        CollectionUtil.requireAllNonNull(target, editedDiveSession);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DiveNotFoundException();
        }
        sortDiveSession(SortingMethod.TIME);
        internalList.set(index, editedDiveSession);
    }

    /**
     * Removes the equivalent dive from the list.
     * The dive session must exist in the list.
     */
    public void remove(DiveSession toRemove) throws DiveNotFoundException {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DiveNotFoundException();
        }
    }


    /**
     * Sets all the dives in a dive session list.
     * @param replacement
     */
    public void setDives(DiveSessionList replacement) {
        requireNonNull(replacement);
        sortDiveSession(SortingMethod.TIME);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code diveSessions}.
     *
     */
    public void setDives(List<DiveSession> diveSessions) {
        CollectionUtil.requireAllNonNull(diveSessions);
        internalList.setAll(diveSessions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<DiveSession> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DiveSessionList)) {
            return false;
        }
        DiveSessionList otherDiveList = (DiveSessionList) other;
        if (otherDiveList.internalList.size() != internalList.size()) {
            return false;
        }
        for (int i = 0; i < internalList.size(); i++) {
            if (!internalList.get(i).equals(otherDiveList.internalList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public Iterator<DiveSession> iterator() {
        return null;
    }
}
