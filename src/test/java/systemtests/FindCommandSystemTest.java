package systemtests;

import static org.junit.Assert.assertFalse;
import static seedu.divelog.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.divelog.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import seedu.divelog.commons.core.index.Index;
import seedu.divelog.logic.commands.DeleteCommand;
import seedu.divelog.logic.commands.FindCommand;
import seedu.divelog.logic.commands.RedoCommand;
import seedu.divelog.logic.commands.UndoCommand;
import seedu.divelog.model.Model;


public class FindCommandSystemTest extends DiveLogSystemTest {

    @Test
    public void find() {
        /* Case: find multiple persons in divelog book, command with leading spaces and trailing spaces
         * -> 2 persons found
         */


        /* Case: repeat previous find command where person list is displaying the persons we are finding
         * -> 2 persons found
         */


        /* Case: find person where person list is not displaying the person we are finding -> 1 person found */


        /* Case: find multiple persons in divelog book, 2 keywords -> 2 persons found */


        /* Case: find multiple persons in divelog book, 2 keywords in reversed order -> 2 persons found */


        /* Case: find multiple persons in divelog book, 2 keywords with 1 repeat -> 2 persons found */


        /* Case: find multiple persons in divelog book, 2 matching keywords and 1 non-matching keyword
         * -> 2 persons found
         */


        /* Case: undo previous find command -> rejected */


        /* Case: redo previous find command -> rejected */


        /* Case: find same persons in divelog book after deleting 1 of them -> 1 person found */


        /* Case: find person in divelog book, keyword is same as name but of different case -> 1 person found */


        /* Case: find person in divelog book, keyword is substring of name -> 0 persons found */


        /* Case: find person in divelog book, name is substring of keyword -> 0 persons found */


        /* Case: find person not in divelog book -> 0 persons found */


        /* Case: find phone number of person in divelog book -> 0 persons found */


        /* Case: find divelog of person in divelog book -> 0 persons found */


        /* Case: find email of person in divelog book -> 0 persons found */


        /* Case: find tags of person in divelog book -> 0 persons found */


        /* Case: find while a person is selected -> selected card deselected */


        /* Case: find person in empty divelog book -> 0 persons found */


        /* Case: mixed case command word -> rejected */

    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code Messages#MESSAGE_PERSONS_LISTED_OVERVIEW} with the number of people in the filtered list,
     * and the model related components equal to {@code expectedModel}.
     * These verifications are done by
     * {@code DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the status bar remains unchanged, and the command box has the default style class, and the
     * selected card updated accordingly, depending on {@code cardStatus}.
     * @see DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel) {
        String expectedResultMessage = String.format(
                MESSAGE_PERSONS_LISTED_OVERVIEW, expectedModel.getFilteredDiveList().size());

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
     * These verifications are done by
     * {@code DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
     * error style.
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
