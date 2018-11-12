package systemtests;

import static seedu.divelog.logic.commands.DeleteCommand.MESSAGE_DELETE_DIVE_SESSION_SUCCESS;
import static seedu.divelog.testutil.TestUtil.getDive;

import org.junit.Test;

import seedu.divelog.commons.core.Messages;
import seedu.divelog.commons.core.index.Index;
import seedu.divelog.logic.commands.DeleteCommand;
import seedu.divelog.logic.commands.RedoCommand;
import seedu.divelog.logic.commands.UndoCommand;
import seedu.divelog.model.Model;
import seedu.divelog.model.ModelManager;
import seedu.divelog.model.dive.DiveSession;

public class DeleteCommandSystemTest extends DiveLogSystemTest {

    private static final String MESSAGE_INVALID_DELETE_COMMAND_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

    @Test
    public void delete() {
        Model defaultModel = getModel();
        /* ----------------- Performing delete operation while an unfiltered list is being shown -------------------- */

        /* Case: delete the first dive session in the list -> deleted */


        /* Case: delete the first dive session in the list, command with leading spaces and trailing spaces ->deleted */
        //assertCommandSuccess("   " + DeleteCommand.COMMAND_WORD + "       ");



        /* Case: delete the last dive session in the list -> deleted */


        /* Case: undo deleting the last dive session in the list -> last dive session restored */
        String command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, defaultModel, expectedResultMessage);
        assertSelectedCardUnchanged();

        /* Case: redo deleting the last dive session in the list -> last dive session deleted again */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, new ModelManager(), expectedResultMessage);
        assertSelectedCardUnchanged();

        /* Case: delete the middle dive session in the list -> deleted */


        /* ------------------ Performing delete operation while a filtered list is being shown ---------------------- */

        /* Case: filtered dive session list, delete index within bounds of divelog book and dive list -> deleted */


        /* Case: filtered dive session list, delete index within bounds of divelog book but out of bounds of dive list
         * -> rejected
         */

        /* --------------------- Performing delete operation while a dive session card is selected ------------------ */

        /* Case: delete the selected dive session -> dive list panel selects the dive before the deleted dive */


        /* --------------------------------- Performing invalid delete operation ------------------------------------ */

        /* Case: invalid index (0) -> rejected */


        /* Case: invalid index (-1) -> rejected */


        /* Case: invalid index (size + 1) -> rejected */


        /* Case: invalid arguments (alphabets) -> rejected */


        /* Case: invalid arguments (extra argument) -> rejected */


        /* Case: mixed case command word -> rejected */

    }

    /**
     * Removes the {@code DiveSession} at the specified {@code index} in {@code model}'s divelog book.
     * @return the removed dive session
     */
    private DiveSession removeDive(Model model, Index index) {
        DiveSession targetDiveSession = getDive(model, index);
        try {
            model.deleteDiveSession(targetDiveSession);
        } catch (seedu.divelog.model.dive.exceptions.DiveNotFoundException e) {
            e.printStackTrace();
        }
        return targetDiveSession;
    }

    /**
     * Deletes the dive at {@code toDelete} by creating a default {@code DeleteCommand} using {@code toDelete} and
     * performs the same verification as {@code assertCommandSuccess(String, Model, String)}.
     * @see DeleteCommandSystemTest#assertCommandSuccess(String, Model, String)
     */
    private void assertCommandSuccess(Index toDelete) {
        Model expectedModel = getModel();
        DiveSession deletedDiveSession = removeDive(expectedModel, toDelete);
        String expectedResultMessage = String.format(MESSAGE_DELETE_DIVE_SESSION_SUCCESS, deletedDiveSession);

        assertCommandSuccess(
                DeleteCommand.COMMAND_WORD + " " + toDelete.getOneBased(), expectedModel, expectedResultMessage);
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays an empty string.<br>
     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url and selected card remains unchanged.<br>
     * 4. Asserts that the status bar's sync status changes.<br>
     * 5. Asserts that the command box has the default style class.<br>
     * Verifications 1 and 2 are performed by
     * {@code DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.
     * @see DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        assertCommandSuccess(command, expectedModel, expectedResultMessage, null);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String)} except that the browser url
     * and selected card are expected to update accordingly depending on the card at {@code expectedSelectedCardIndex}.
     * @see DeleteCommandSystemTest#assertCommandSuccess(String, Model, String)
     * @see DiveLogSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage,
            Index expectedSelectedCardIndex) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);

        if (expectedSelectedCardIndex != null) {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        } else {
            assertSelectedCardUnchanged();
        }

        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url, selected card and status bar remain unchanged.<br>
     * 4. Asserts that the command box has the error style.<br>
     * Verifications 1 and 2 are performed by
     * {@code DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
