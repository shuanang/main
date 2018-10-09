package seedu.divelog.logic.commands;

import static seedu.divelog.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.divelog.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.divelog.testutil.TypicalDiveSessions.getTypicalAddressBook;

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
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        DiveSession validPerson = new DiveSessionBuilder().build();

        Model expectedModel = new ModelManager(model.getDiveLog(), new UserPrefs());
        expectedModel.addDiveSession(validPerson);
        expectedModel.commitDiveLog();

        assertCommandSuccess(new AddCommand(validPerson), model, commandHistory,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        DiveSession personInList = model.getDiveLog().getDiveList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, commandHistory,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
