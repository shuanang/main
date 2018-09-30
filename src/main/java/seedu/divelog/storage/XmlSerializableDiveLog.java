package seedu.divelog.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.divelog.commons.exceptions.IllegalValueException;
import seedu.divelog.model.DiveLog;
import seedu.divelog.model.ReadOnlyDiveLog;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.person.Person;

/**
 * An Immutable DiveLog that is serializable to XML format
 */
@XmlRootElement(name = "divelog")
public class XmlSerializableDiveLog {


    @XmlElement
    private List<XmlAdaptedDiveSession> dives;

    /**
     * Creates an empty XmlSerializableDiveLog.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableDiveLog() {
        dives = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableDiveLog(ReadOnlyDiveLog src) {
        this();
        dives.addAll(src.getDiveList().stream().map(XmlAdaptedDiveSession::new).collect(Collectors.toList()));
    }

    /**
     * Converts this addressbook into the model's {@code DiveLog} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedDiveSession}.
     */
    public DiveLog toModelType() throws IllegalValueException {
        DiveLog diveLog = new DiveLog();
        for (XmlAdaptedDiveSession p : dives) {
            DiveSession dive = p.toModelType();
            diveLog.addDive(dive);
        }
        return diveLog;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableDiveLog)) {
            return false;
        }
        return dives.equals(((XmlSerializableDiveLog) other).dives);
    }
}
