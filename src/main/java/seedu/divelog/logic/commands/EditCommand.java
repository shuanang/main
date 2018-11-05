package seedu.divelog.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.divelog.commons.core.Messages;
import seedu.divelog.commons.core.index.Index;
import seedu.divelog.commons.util.CollectionUtil;
import seedu.divelog.commons.util.CompareUtil;
import seedu.divelog.logic.CommandHistory;
import seedu.divelog.logic.commands.exceptions.CommandException;
import seedu.divelog.logic.parser.CliSyntax;
import seedu.divelog.logic.pressuregroup.PressureGroupLogic;
import seedu.divelog.model.Model;
import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.Location;
import seedu.divelog.model.dive.OurDate;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.Time;
import seedu.divelog.model.dive.TimeZone;


/**
 * Edits the details of an existing dive session in the divelog book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the dive identified "
            + "by the index number used in the displayed dive list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_DATE_START + "DATE_START] "
            + "[" + CliSyntax.PREFIX_TIME_START + "TIME_START] "
            + "[" + CliSyntax.PREFIX_DATE_END + "DATE_END] "
            + "[" + CliSyntax.PREFIX_TIME_END + "TIME_END] "
            + "[" + CliSyntax.PREFIX_SAFETY_STOP + "SAFETY_STOP_TIME] "
            + "[" + CliSyntax.PREFIX_DEPTH + "DEPTH] "
            + "[" + CliSyntax.PREFIX_PRESSURE_GROUP_START + "PG_AT_START] "
            + "[" + CliSyntax.PREFIX_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PRESSURE_GROUP_START + "B "
            + CliSyntax.PREFIX_LOCATION + "Tioman "
            + CliSyntax.PREFIX_TIMEZONE + "+8 ";

    public static final String MESSAGE_EDIT_DIVE_SUCCESS = "Edited Dive: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_DATE_START + "DATE_START] "
            + "[" + CliSyntax.PREFIX_TIME_START + "TIME_START] "
            + "[" + CliSyntax.PREFIX_DATE_END + "DATE_END] "
            + "[" + CliSyntax.PREFIX_TIME_END + "TIME_END] "
            + "[" + CliSyntax.PREFIX_SAFETY_STOP + "SAFETY_STOP_TIME] "
            + "[" + CliSyntax.PREFIX_DEPTH + "DEPTH] "
            + "[" + CliSyntax.PREFIX_PRESSURE_GROUP_START + "PG_AT_START] "
            + "[" + CliSyntax.PREFIX_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PRESSURE_GROUP_START + "B "
            + CliSyntax.PREFIX_LOCATION + "Tioman "
            + CliSyntax.PREFIX_TIMEZONE + "+8 ";

    private final Index index;
    private final EditDiveDescriptor editDiveDescriptor;

    /**
     * @param index of the dive session in the filtered dive list to edit
     * @param editDiveDescriptor details to edit the dive session with
     */
    public EditCommand(Index index, EditDiveDescriptor editDiveDescriptor) {
        requireNonNull(index);
        requireNonNull(editDiveDescriptor);

        this.index = index;
        this.editDiveDescriptor = new EditDiveDescriptor(editDiveDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<DiveSession> lastShownList = model.getFilteredDiveList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DIVE_DISPLAYED_INDEX);
        }

        DiveSession diveToEdit = lastShownList.get(index.getZeroBased());
        DiveSession editedDive = null;
        editedDive = createEditedDive(diveToEdit, editDiveDescriptor);

        try {
            model.updateDiveSession(diveToEdit, editedDive);
        } catch (seedu.divelog.model.dive.exceptions.DiveNotFoundException e) {
            e.printStackTrace();
        }
        model.updateFilteredDiveList(Model.PREDICATE_SHOW_ALL_DIVES);
        model.commitDiveLog();
        if (model.getPlanningMode()) {
            model.plannerCountPlus();
        }
        return new CommandResult(String.format(MESSAGE_EDIT_DIVE_SUCCESS, editedDive));
    }

    /**
     * Creates and returns a {@code DiveSession} with the details of {@code diveSessionToEdit}
     * edited with {@code editDiveDescriptor}.
     */
    private static DiveSession createEditedDive(DiveSession diveToEdit, EditDiveDescriptor editDiveSessionDescriptor) {
        assert diveToEdit != null;
        OurDate dateStart = editDiveSessionDescriptor.getDateStart().orElse(diveToEdit.getDateStart());
        Time start = editDiveSessionDescriptor.getStart().orElse(diveToEdit.getStart());
        OurDate dateEnd = editDiveSessionDescriptor.getDateEnd().orElse(diveToEdit.getDateEnd());
        Time end = editDiveSessionDescriptor.getEnd().orElse(diveToEdit.getEnd());
        Time safetyStop = editDiveSessionDescriptor.getSafetyStop().orElse(diveToEdit.getSafetyStop());
        Location location = editDiveSessionDescriptor.getLocation().orElse(diveToEdit.getLocation());
        DepthProfile depth = editDiveSessionDescriptor.getDepthProfile().orElse(diveToEdit.getDepthProfile());
        TimeZone timezone = editDiveSessionDescriptor.getTimeZone().orElse(diveToEdit.getTimeZone());
        PressureGroup pressureGroupAtBeginning = editDiveSessionDescriptor.getPressureGroupAtBeginning()
                .orElse(diveToEdit.getPressureGroupAtBeginning());
        //if diveToEdit end pressure group matches calculation for first dive -> editDive run through first dive calc
        //else (diveToEdit is a repeat dive) -> editDive run through repeat dive calc
        try {
            long actualBottomTimeOfDiveToEdit = CompareUtil.checkTimeDifference(diveToEdit.getStart().getTimeString(),
                    diveToEdit.getEnd().getTimeString(), diveToEdit.getDateStart().getOurDateString(),
                    diveToEdit.getDateEnd().getOurDateString());
            PressureGroup diveToEditEndPg = PressureGroupLogic.computePressureGroupFirstDive(diveToEdit
                    .getDepthProfile(), actualBottomTimeOfDiveToEdit);
            if (diveToEdit.getPressureGroupAtEnd().getPressureGroup().equals(diveToEditEndPg.getPressureGroup())) {
                //diveToEdit is a first dive, run through first dive calculation
                //what is user edits the first dive pg? Then it wouldnt be a first dive anymore...
                //should throw error if cannot calc end pg (due to too long dive etc) -> Cancel edit
                //bug: add 40m cannot go beyond ..minutes, but can edit to further
                System.out.println("editing dive that is first dive");
                long actualBottomTimeOfEditedDive = CompareUtil.checkTimeDifference(start.getTimeString(),
                        end.getTimeString(), dateStart.getOurDateString(), dateEnd.getOurDateString());
                editDiveSessionDescriptor.pressureGroupAtEnd = PressureGroupLogic.computePressureGroupFirstDive(depth,
                        actualBottomTimeOfEditedDive);
            } else {
                //diveToEdit is a repeat dive, run through repeat dive calculation
                long actualBottomTimeOfEditedDive = CompareUtil.checkTimeDifference(start.getTimeString(),
                        end.getTimeString(), dateStart.getOurDateString(), dateEnd.getOurDateString());
                editDiveSessionDescriptor.pressureGroupAtEnd = PressureGroupLogic.computePressureGroup(depth,
                        actualBottomTimeOfEditedDive, pressureGroupAtBeginning);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PressureGroup pressureGroupAtEnd = editDiveSessionDescriptor.getPressureGroupAtEnd()
                .orElse(diveToEdit.getPressureGroupAtEnd());
        System.out.println("Pg at end: " + editDiveSessionDescriptor.getPressureGroupAtEnd());

        return new DiveSession(dateStart, start, safetyStop, dateEnd, end, pressureGroupAtBeginning, pressureGroupAtEnd,
                location, depth, timezone);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editDiveDescriptor.equals(e.editDiveDescriptor);
    }

    /**
     * Stores the details to edit the dive session with. Each non-empty field value will replace the
     * corresponding field value of the dive session.
     */
    public static class EditDiveDescriptor {
        private OurDate dateStart;
        private Time start;
        private Time safetyStop;
        private OurDate dateEnd;
        private Time end;
        private PressureGroup pressureGroupAtBeginning;
        private PressureGroup pressureGroupAtEnd;
        private Location location;
        private DepthProfile depthProfile;
        private TimeZone timezone;


        public EditDiveDescriptor() {}

        public EditDiveDescriptor(EditDiveDescriptor descriptor) {
            setDateStart(descriptor.dateStart);
            setStart(descriptor.start);
            setSafetyStop(descriptor.safetyStop);
            setDateEnd(descriptor.dateEnd);
            setEnd(descriptor.end);
            setPressureGroupAtBeginning(descriptor.pressureGroupAtBeginning);
            setPressureGroupAtEnd(descriptor.pressureGroupAtEnd);
            setLocation(descriptor.location);
            setDepthProfile(descriptor.depthProfile);
            setTimeZone(descriptor.timezone);
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(dateStart, start, safetyStop, dateEnd, end,
                    pressureGroupAtBeginning, pressureGroupAtEnd, location, depthProfile, timezone);
        }



        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDiveDescriptor)) {
                return false;
            }

            // state check
            EditDiveDescriptor e = (EditDiveDescriptor) other;

            return getDateStart().equals(e.getDateStart())
                    && getStart().equals(e.getStart())
                    && getDateEnd().equals(e.getDateEnd())
                    && getEnd().equals(e.getEnd())
                    && getPressureGroupAtBeginning().equals(e.getPressureGroupAtBeginning())
                    && getPressureGroupAtEnd().equals(e.getPressureGroupAtEnd())
                    && getLocation().equals(e.getLocation())
                    && getDepthProfile().equals(e.getDepthProfile())
                    && getTimeZone().equals(e.getTimeZone());
        }

        public void setDateStart(OurDate dateStart) {
            this.dateStart = dateStart;
        }

        public void setDateEnd(OurDate dateEnd) {
            this.dateEnd = dateEnd;
        }

        public void setStart(Time start) {
            this.start = start;
        }

        public void setSafetyStop(Time safetyStop) {
            this.safetyStop = safetyStop;
        }

        public void setEnd(Time end) {
            this.end = end;
        }

        public void setPressureGroupAtBeginning(PressureGroup pressureGroupAtBeginning) {
            this.pressureGroupAtBeginning = pressureGroupAtBeginning;
        }

        public void setPressureGroupAtEnd(PressureGroup pressureGroupAtEnd) {
            this.pressureGroupAtEnd = pressureGroupAtEnd;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public void setDepthProfile(DepthProfile depthProfile) {
            this.depthProfile = depthProfile;
        }

        public void setTimeZone(TimeZone timezone) {
            this.timezone = timezone;
        }

        public Optional<OurDate> getDateStart() {
            return Optional.ofNullable(dateStart);
        }

        public Optional<OurDate> getDateEnd() {
            return Optional.ofNullable(dateEnd);
        }

        public Optional<Time> getStart() {
            return Optional.ofNullable(start);
        }

        public Optional<Time> getSafetyStop() {
            return Optional.ofNullable(safetyStop);
        }

        public Optional<Time> getEnd() {
            return Optional.ofNullable(end);
        }

        public Optional<PressureGroup> getPressureGroupAtBeginning() {
            return Optional.ofNullable(pressureGroupAtBeginning);
        }

        public Optional<PressureGroup> getPressureGroupAtEnd() {
            return Optional.ofNullable(pressureGroupAtEnd);
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public Optional<DepthProfile> getDepthProfile() {
            return Optional.ofNullable(depthProfile);
        }

        public Optional<TimeZone> getTimeZone() {
            return Optional.ofNullable(timezone);
        }
    }
}
