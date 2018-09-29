package seedu.divelog.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.divelog.commons.exceptions.IllegalValueException;
import seedu.divelog.model.DiveLog;
import seedu.divelog.model.ReadOnlyDiveLog;
import seedu.divelog.model.person.Person;

/**
 * An Immutable DiveLog that is serializable to XML format
 */
@XmlRootElement(name = "addressbook")
public class XmlSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    @XmlElement
    private List<XmlAdaptedPerson> persons;

    /**
     * Creates an empty XmlSerializableAddressBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableAddressBook() {
        persons = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableAddressBook(ReadOnlyDiveLog src) {
        this();
        persons.addAll(src.getPersonList().stream().map(XmlAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this addressbook into the model's {@code DiveLog} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedPerson}.
     */
    public DiveLog toModelType() throws IllegalValueException {
        DiveLog diveLog = new DiveLog();
        for (XmlAdaptedPerson p : persons) {
            Person person = p.toModelType();
            if (diveLog.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            diveLog.addPerson(person);
        }
        return diveLog;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableAddressBook)) {
            return false;
        }
        return persons.equals(((XmlSerializableAddressBook) other).persons);
    }
}
