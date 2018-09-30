package seedu.divelog.model;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.divelog.commons.core.ComponentManager;
import seedu.divelog.commons.core.LogsCenter;
import seedu.divelog.commons.events.model.DiveLogChangedEvent;
import seedu.divelog.commons.util.CollectionUtil;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.exceptions.DiveNotFoundException;

/**
 * Represents the in-memory model of the divelog book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedDiveLog versionedDiveLog;
    private final FilteredList<DiveSession> filteredDives;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyDiveLog addressBook, UserPrefs userPrefs) {
        super();
        CollectionUtil.requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with divelog book: " + addressBook + " and user prefs " + userPrefs);

        versionedDiveLog = new VersionedDiveLog(addressBook);
        filteredDives = new FilteredList<>(versionedDiveLog.getDiveList());
    }

    public ModelManager() {
        this(new DiveLog(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyDiveLog newData) {
        versionedDiveLog.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyDiveLog getDiveLog() {
        return versionedDiveLog;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new DiveLogChangedEvent(versionedDiveLog));
    }


    @Override
    public void deleteDiveSession(DiveSession target) throws DiveNotFoundException {
        versionedDiveLog.removeDive(target);
        indicateAddressBookChanged();
    }

    @Override
    public void addDiveSession(DiveSession diveSession) {
        versionedDiveLog.addDive(diveSession);
        updateFilteredDiveList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }

    @Override
    public void updateDiveSession(DiveSession target, DiveSession editedDiveSession) throws DiveNotFoundException {
        CollectionUtil.requireAllNonNull(target, editedDiveSession);
        versionedDiveLog.updateDive(target, editedDiveSession);
        indicateAddressBookChanged();
    }

    //=========== Filtered DiveSession List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Dive} backed by the internal list of
     * {@code versionedDiveLog}
     */
    @Override
    public ObservableList<DiveSession> getFilteredDiveList() {
        return FXCollections.unmodifiableObservableList(filteredDives);
    }

    @Override
    public void updateFilteredDiveList(Predicate<DiveSession> predicate) {
        requireNonNull(predicate);
        filteredDives.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedDiveLog.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedDiveLog.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedDiveLog.undo();
        indicateAddressBookChanged();
    }

    @Override
    public void redoAddressBook() {
        versionedDiveLog.redo();
        indicateAddressBookChanged();
    }

    @Override
    public void commitAddressBook() {
        versionedDiveLog.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedDiveLog.equals(other.versionedDiveLog)
                && filteredDives.equals(other.filteredDives);
    }

}
