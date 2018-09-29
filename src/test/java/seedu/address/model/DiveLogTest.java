package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.divelog.model.DiveLog;
import seedu.divelog.model.ReadOnlyDiveLog;
import seedu.divelog.model.person.Person;
import seedu.divelog.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class DiveLogTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final DiveLog diveLog = new DiveLog();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), diveLog.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        diveLog.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        DiveLog newData = getTypicalAddressBook();
        diveLog.resetData(newData);
        assertEquals(newData, diveLog);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        DiveLogStub newData = new DiveLogStub(newPersons);

        thrown.expect(DuplicatePersonException.class);
        diveLog.resetData(newData);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        diveLog.hasPerson(null);
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(diveLog.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        diveLog.addPerson(ALICE);
        assertTrue(diveLog.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        diveLog.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(diveLog.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        diveLog.getPersonList().remove(0);
    }

    /**
     * A stub ReadOnlyDiveLog whose persons list can violate interface constraints.
     */
    private static class DiveLogStub implements ReadOnlyDiveLog {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        DiveLogStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
