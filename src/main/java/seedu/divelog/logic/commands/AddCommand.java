package seedu.divelog.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.divelog.logic.CommandHistory;
import seedu.divelog.logic.parser.CliSyntax;
import seedu.divelog.model.Model;
import seedu.divelog.model.dive.DiveSession;

/**
 * Adds a person to the divelog book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the divelog book. "
            + "Parameters: "
            + CliSyntax.PREFIX_TIME_START + "START_TIME "
            + CliSyntax.PREFIX_TIME_END + "END_TIME "
            + CliSyntax.PREFIX_SAFETY_STOP + "SAFETY_STOP_TIME "
            + CliSyntax.PREFIX_DEPTH + "DEPTH "
            + CliSyntax.PREFIX_PRESSURE_GROUP_START + "PRESSURE_GROUP_START "
            + CliSyntax.PREFIX_PRESSURE_GROUP_END + "PRESSURE_GROUP_END "
            + CliSyntax.PREFIX_LOCATION + "LOCATION \n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_TIME_START + "0700 "
            + CliSyntax.PREFIX_TIME_END + "0945 "
            + CliSyntax.PREFIX_SAFETY_STOP + "0930 "
            + CliSyntax.PREFIX_DEPTH + "15 "
            + CliSyntax.PREFIX_PRESSURE_GROUP_START + "A "
            + CliSyntax.PREFIX_PRESSURE_GROUP_END + "R "
            + CliSyntax.PREFIX_LOCATION + "Sentosa ";

    public static final String MESSAGE_SUCCESS = "New dive session added!";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the divelog book";

    private final DiveSession toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(DiveSession dive) {
        requireNonNull(dive);
        toAdd = dive;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.addDiveSession(toAdd);
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
