package seedu.divelog.model.dive;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.divelog.commons.core.LogsCenter;
import seedu.divelog.commons.util.CollectionUtil;
import seedu.divelog.model.dive.exceptions.DiveNotFoundException;

/**
 * @author arjo
 * Stores a list of dives
 */
public class DiveSessionList implements Iterable<DiveSession> {
    private final ObservableList<DiveSession> internalList = FXCollections.observableArrayList();
    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(DiveSession toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a Dive Session to the list.
     * The person must not already exist in the list.
     */
    public void add(DiveSession toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the dive session {@code target} in the list with {@code editedDiveSession}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedDiveSession} must not be the same as another existing person in the list.
     */
    public void setDiveSession(DiveSession target, DiveSession editedDiveSession) throws DiveNotFoundException {
        CollectionUtil.requireAllNonNull(target, editedDiveSession);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DiveNotFoundException();
        }
        internalList.set(index, editedDiveSession);
    }

    /**
     * Removes the equivalent dive from the list.
     * The person must exist in the list.
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
        Logger logger = LogsCenter.getLogger(DiveSessionList.class);
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
