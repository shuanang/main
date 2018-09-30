package seedu.divelog.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.divelog.testutil.TypicalPersons.ALICE;
import static seedu.divelog.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.divelog.model.person.Person;
import seedu.divelog.model.person.exceptions.DuplicatePersonException;
import seedu.divelog.testutil.PersonBuilder;

public class DiveLogTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final DiveLog diveLog = new DiveLog();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), diveLog.getDiveSessionList());
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
        diveLog.hasDive(null);
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(diveLog.hasDive(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        diveLog.addDive(ALICE);
        assertTrue(diveLog.hasDive(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        diveLog.addDive(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(diveLog.hasDive(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        diveLog.getDiveSessionList().remove(0);
    }

    /**
     * A stub ReadOnlyDiveLog whose persons list can violate interface constraints.
     */
    private static class DiveLogStub implements ReadOnlyDiveLog {
        private final ObservableList<DiveSession> persons = FXCollections.observableArrayList();

        DiveLogStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<DiveSession> getPersonList() {
            return persons;
        }
    }

}
