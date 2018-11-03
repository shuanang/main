package systemtests;

import static org.junit.Assert.assertFalse;
import static seedu.divelog.commons.core.Messages.MESSAGE_DIVE_LISTED_OVERVIEW;

import org.junit.Test;

import seedu.divelog.model.Model;


public class FindCommandSystemTest extends DiveLogSystemTest {

    @Test
    public void find() {
        /* Case: find multiple dive sessions in divelog book, command with leading spaces and trailing spaces
         * -> 2 dive sessions found
         */


        /* Case: repeat previous find command where dive session list is displaying the dive sessions we are finding
         * -> 2 dive sessions found
         */


        /* Case: find dive session where dive list is not displaying the dive we are finding -> 1 dive found */


        /* Case: find multiple dive session in divelog book, 2 keywords -> 2 dives found */


        /* Case: find multiple dive session in divelog book, 2 keywords in reversed order -> 2 dives found */


        /* Case: find multiple dive sessions in divelog book, 2 keywords with 1 repeat -> 2 dives found */


        /* Case: find multiple dive sessions in divelog book, 2 matching keywords and 1 non-matching keyword
         * -> 2 dives found
         */


        /* Case: undo previous find command -> rejected */


        /* Case: redo previous find command -> rejected */


        /* Case: find same dive sessions in divelog book after deleting 1 of them -> 1 dive session found */


        /* Case: find dive session in divelog book, keyword is same as name but of different case -> 1 dive found */


        /* Case: find dive session in divelog book, keyword is substring of name -> 0 dive sessions found */


        /* Case: find dive session in divelog book, name is substring of keyword -> 0 dive sessions found */


        /* Case: find dive session not in divelog book -> 0 dive sessions found */


        /* Case: find phone number of dive session in divelog book -> 0 dive sessions found */


        /* Case: find divelog of dive session in divelog book -> 0 dive sessions found */


        /* Case: find email of dive session in divelog book -> 0 dive sessions found */


        /* Case: find tags of dive session in divelog book -> 0 dive sessions found */


        /* Case: find while a dive session is selected -> selected card deselected */


        /* Case: find dive session in empty divelog book -> 0 dive sessions found */


        /* Case: mixed case command word -> rejected */

    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code Messages#MESSAGE_DIVE_LISTED_OVERVIEW} with the number of people in the filtered list,
     * and the model related components equal to {@code expectedModel}.
     * These verifications are done by
     * {@code DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the status bar remains unchanged, and the command box has the default style class, and the
     * selected card updated accordingly, depending on {@code cardStatus}.
     * @see DiveLogSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel) {
        String expectedResultMessage = String.format(
                MESSAGE_DIVE_LISTED_OVERVIEW, expectedModel.getFilteredDiveList().size());

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
