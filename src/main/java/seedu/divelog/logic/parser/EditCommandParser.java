package seedu.divelog.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.divelog.commons.core.Messages;
import seedu.divelog.commons.core.index.Index;
import seedu.divelog.logic.commands.EditCommand;
import seedu.divelog.logic.parser.exceptions.ParseException;
import seedu.divelog.model.dive.Location;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.Time;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        CliSyntax.PREFIX_TIME_START,
                        CliSyntax.PREFIX_TIME_END,
                        CliSyntax.PREFIX_SAFETY_STOP,
                        CliSyntax.PREFIX_DEPTH,
                        CliSyntax.PREFIX_PRESSURE_GROUP_START,
                        CliSyntax.PREFIX_PRESSURE_GROUP_END,
                        CliSyntax.PREFIX_LOCATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditDiveDescriptor editPersonDescriptor = new EditCommand.EditDiveDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_TIME_START).isPresent()) {
            editPersonDescriptor.setStart(new Time(argMultimap.getValue(CliSyntax.PREFIX_TIME_START).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).isPresent()) {
            editPersonDescriptor.setSafetyStop(new Time(argMultimap.getValue(CliSyntax.PREFIX_SAFETY_STOP).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_TIME_END).isPresent()) {
            editPersonDescriptor.setEnd(new Time(argMultimap.getValue(CliSyntax.PREFIX_TIME_END).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DEPTH).isPresent()) {
            editPersonDescriptor.setDepthProfile(
                    ParserUtil.parseDepth(argMultimap.getValue(CliSyntax.PREFIX_DEPTH).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PRESSURE_GROUP_START).isPresent()) {
            editPersonDescriptor.setPressureGroupAtBeginning(
                    new PressureGroup(argMultimap.getValue(CliSyntax.PREFIX_PRESSURE_GROUP_START).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PRESSURE_GROUP_END).isPresent()) {
            editPersonDescriptor.setPressureGroupAtEnd(
                    new PressureGroup(argMultimap.getValue(CliSyntax.PREFIX_PRESSURE_GROUP_END).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_LOCATION).isPresent()) {
            editPersonDescriptor.setLocation(
                    new Location(argMultimap.getValue(CliSyntax.PREFIX_LOCATION).get()));
        }
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }


}
