package seedu.divelog.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.divelog.commons.util.CompareUtil;
import seedu.divelog.logic.CommandHistory;
import seedu.divelog.logic.parser.CliSyntax;
import seedu.divelog.logic.pressuregroup.PressureGroupLogic;
import seedu.divelog.model.Model;
import seedu.divelog.model.dive.DiveSession;

/**
 * Adds a dive to the divelog book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_ERROR = "Dive is too deep and too long!!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a dive to the divelog book. "
            + "Parameters: "
            + CliSyntax.PREFIX_DATE_START + "DATE_START (DDMMYYYY Format) "
            + CliSyntax.PREFIX_TIME_START + "START_TIME (24Hr Format) "
            + CliSyntax.PREFIX_DATE_END + "DATE_END (DDMMYYYY Format) "
            + CliSyntax.PREFIX_TIME_END + "END_TIME (24Hr Format) "
            + CliSyntax.PREFIX_SAFETY_STOP + "SAFETY_STOP_TIME (24Hr Format) \n\t"
            + CliSyntax.PREFIX_DEPTH + "DEPTH "
            + CliSyntax.PREFIX_PRESSURE_GROUP_START + "PRESSURE_GROUP_START "
            + CliSyntax.PREFIX_LOCATION + "LOCATION"
            + CliSyntax.PREFIX_TIMEZONE + "TIMEZONE\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_DATE_START + "01082018 "
            + CliSyntax.PREFIX_TIME_START + "0700 "
            + CliSyntax.PREFIX_DATE_END + "01082018 "
            + CliSyntax.PREFIX_TIME_END + "0800 "
            + CliSyntax.PREFIX_SAFETY_STOP + "0745 "
            + CliSyntax.PREFIX_DEPTH + "16 "
            + CliSyntax.PREFIX_PRESSURE_GROUP_START + "A "
            + CliSyntax.PREFIX_LOCATION + "Sentosa "
            + CliSyntax.PREFIX_TIMEZONE + "+8";

    public static final String MESSAGE_SUCCESS = "New dive session added!";

    private DiveSession toAdd;

    /**
     * Creates an AddCommand to add the specified {@code DiveSession}
     */
    public AddCommand(DiveSession dive) {
        requireNonNull(dive);
        toAdd = dive;
    }

    //@@author shuanang

    /**
     *Checks if the dive to be added is a first dive or a repeat dive to calculate the correct pressure group,
     * then adds accordingly
     */
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        if (model.getPlanningMode()) {
            model.plannerCountPlus();
        }
        requireNonNull(model);
        List<DiveSession> lastShownList = model.getFilteredDiveList();
        //match the dive session
        int count = 0;
        for (DiveSession diveSessions : lastShownList) {
            if (toAdd.getDateStart().getOurDateString().equals(diveSessions.getDateStart().getOurDateString()
                    .intern())) {
                count = count + 1;
            }
        }
        if (count >= 1) {
            //NOT the first dive of the day
            model.addDiveSession(toAdd);
            model.commitDiveLog();
            return new CommandResult(MESSAGE_SUCCESS);
        } else if (count == 0) {
            //first dive of day
            try {
                long actualBottomTime = CompareUtil.checkTimeDifference(toAdd.getStart().getTimeString(),
                        toAdd.getEnd().getTimeString(), toAdd.getDateStart().getOurDateString(),
                        toAdd.getDateEnd().getOurDateString());
                toAdd = new DiveSession(toAdd.getDateStart(), toAdd.getStart(),
                        toAdd.getSafetyStop(), toAdd.getDateEnd(), toAdd.getEnd(),
                        toAdd.getPressureGroupAtBeginning(),
                        PressureGroupLogic.computePressureGroupFirstDive(toAdd.getDepthProfile(), actualBottomTime),
                        toAdd.getLocation(), toAdd.getDepthProfile(), toAdd.getTimeZone());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.addDiveSession(toAdd);
        model.commitDiveLog();
        return new CommandResult(MESSAGE_SUCCESS);
    }
    //@@author
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
