package seedu.divelog.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.divelog.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.divelog.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.divelog.logic.commands.CommandTestUtil.showDiveAtIndex;
import static seedu.divelog.testutil.TypicalIndexes.INDEX_FIRST_DIVE;
import static seedu.divelog.testutil.TypicalIndexes.INDEX_SECOND_DIVE;
import static seedu.divelog.testutil.TypicalDiveSessions.getTypicalAddressBook;

import org.junit.Test;

import seedu.divelog.commons.core.Messages;
import seedu.divelog.commons.core.index.Index;
import seedu.divelog.logic.CommandHistory;
import seedu.divelog.model.Model;
import seedu.divelog.model.ModelManager;
import seedu.divelog.model.UserPrefs;
import seedu.divelog.model.dive.DiveSession;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        DiveSession diveToDelete = model.getFilteredDiveList().get(INDEX_FIRST_DIVE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_DIVE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, diveToDelete);

        ModelManager expectedModel = new ModelManager(model.getDiveLog(), new UserPrefs());
        try {
            expectedModel.deleteDiveSession(diveToDelete);
        } catch (seedu.divelog.model.dive.exceptions.DiveNotFoundException e) {
            e.printStackTrace();
        }
        expectedModel.commitDiveLog();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDiveList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDiveAtIndex(model, INDEX_FIRST_DIVE);

        DiveSession personToDelete = model.getFilteredDiveList().get(INDEX_FIRST_DIVE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_DIVE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getDiveLog(), new UserPrefs());
        try {
            expectedModel.deleteDiveSession(personToDelete);
        } catch (seedu.divelog.model.dive.exceptions.DiveNotFoundException e) {
            e.printStackTrace();
        }
        expectedModel.commitDiveLog();
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDiveAtIndex(model, INDEX_FIRST_DIVE);

        Index outOfBoundIndex = INDEX_SECOND_DIVE;
        // ensures that outOfBoundIndex is still in bounds of divelog book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDiveLog().getDiveList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        DiveSession personToDelete = model.getFilteredDiveList().get(INDEX_FIRST_DIVE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_DIVE);
        Model expectedModel = new ModelManager(model.getDiveLog(), new UserPrefs());
        expectedModel.deleteDiveSession(personToDelete);
        expectedModel.commitDiveLog();

        // delete -> first person deleted
        deleteCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedModel.undoDiveLog();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first person deleted again
        expectedModel.redoDiveLog();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDiveList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        // execution failed -> divelog book state not added into model
        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // single divelog book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Person} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted person in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the person object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_samePersonDeleted() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_DIVE);
        Model expectedModel = new ModelManager(model.getDiveLog(), new UserPrefs());

        showDiveAtIndex(model, INDEX_SECOND_DIVE);
        DiveSession personToDelete = model.getFilteredDiveList().get(INDEX_FIRST_DIVE.getZeroBased());
        expectedModel.deleteDiveSession(personToDelete);
        expectedModel.commitDiveLog();

        // delete -> deletes second person in unfiltered person list / first person in filtered person list
        deleteCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedModel.undoDiveLog();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(personToDelete, model.getFilteredDiveList().get(INDEX_FIRST_DIVE.getZeroBased()));
        // redo -> deletes same second person in unfiltered person list
        expectedModel.redoDiveLog();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_DIVE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_DIVE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_DIVE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredDiveList(p -> false);

        assertTrue(model.getFilteredDiveList().isEmpty());
    }
}
