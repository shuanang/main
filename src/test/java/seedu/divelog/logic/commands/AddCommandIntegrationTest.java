package seedu.divelog.logic.commands;

import static seedu.divelog.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.divelog.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.divelog.testutil.TypicalDiveSessions.getTypicalDiveLog;

import org.junit.Before;
import org.junit.Test;

import seedu.divelog.logic.CommandHistory;
import seedu.divelog.model.Model;
import seedu.divelog.model.ModelManager;
import seedu.divelog.model.UserPrefs;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.testutil.DiveSessionBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalDiveLog(), new UserPrefs());
    }

    @Test
    public void execute_newDive_success() {
        DiveSession validDive = new DiveSessionBuilder().build();

        Model expectedModel = new ModelManager(model.getDiveLog(), new UserPrefs());
        expectedModel.addDiveSession(validDive);
        expectedModel.commitDiveLog();

        assertCommandSuccess(new AddCommand(validDive), model, commandHistory,
                String.format(AddCommand.MESSAGE_SUCCESS, validDive), expectedModel);
    }

}
