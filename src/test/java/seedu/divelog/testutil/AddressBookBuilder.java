package seedu.divelog.testutil;

import seedu.divelog.model.DiveLog;
import seedu.divelog.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code DiveLog ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private DiveLog diveLog;

    public AddressBookBuilder() {
        diveLog = new DiveLog();
    }

    public AddressBookBuilder(DiveLog diveLog) {
        this.diveLog = diveLog;
    }

    /**
     * Adds a new {@code Person} to the {@code DiveLog} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        diveLog.addPerson(person);
        return this;
    }

    public DiveLog build() {
        return diveLog;
    }
}
