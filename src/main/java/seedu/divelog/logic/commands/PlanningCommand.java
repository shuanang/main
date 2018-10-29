package seedu.divelog.logic.commands;

import seedu.divelog.logic.CommandHistory;

import seedu.divelog.logic.commands.exceptions.CommandException;
import seedu.divelog.model.Model;

//author @Cjunx
/**
 * Enters or exit the Planning Mode
 */
public class PlanningCommand extends Command {

    public static final String COMMAND_WORD_PLAN = "Newplan";
    public static final String COMMAND_WORD_NORMAL = "Normalmode";

    public static final String MESSAGE_ENTRY = "You're now in Planning Mode";
    public static final String MESSAGE_EXIT = "You're now in Normal Mode";


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        if (model.getPlanningMode()) {
            while (model.getPlannerCount() > 0) {
                model.undoDiveLog();
                model.plannerCountMinus();
            }
            model.setPlanningMode();
            return new CommandResult(MESSAGE_EXIT);
        }
        model.setPlanningMode();
        return new CommandResult(MESSAGE_ENTRY);
    }
}
