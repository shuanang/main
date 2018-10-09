package seedu.divelog.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.divelog.logic.commands.CommandTestUtil.DESC_NIGHT_BALI;
import static seedu.divelog.logic.commands.CommandTestUtil.DESC_DAY_TIOMAN;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.divelog.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.Test;

import seedu.divelog.logic.commands.EditCommand.EditDiveDescriptor;
import seedu.divelog.testutil.EditDiveDescriptorBuilder;

public class EditDiveDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDiveDescriptor descriptorWithSameValues = new EditDiveDescriptor(DESC_NIGHT_BALI);
        assertTrue(DESC_NIGHT_BALI.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_NIGHT_BALI.equals(DESC_NIGHT_BALI));

        // null -> returns false
        assertFalse(DESC_NIGHT_BALI.equals(null));

        // different types -> returns false
        assertFalse(DESC_NIGHT_BALI.equals(5));

        // different values -> returns false
        assertFalse(DESC_NIGHT_BALI.equals(DESC_DAY_TIOMAN));

        // different name -> returns false
        EditDiveDescriptor editedAmy = new EditDiveDescriptorBuilder(DESC_NIGHT_BALI).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_NIGHT_BALI.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditDiveDescriptorBuilder(DESC_NIGHT_BALI).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_NIGHT_BALI.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditDiveDescriptorBuilder(DESC_NIGHT_BALI).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_NIGHT_BALI.equals(editedAmy));

        // different divelog -> returns false
        editedAmy = new EditDiveDescriptorBuilder(DESC_NIGHT_BALI).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_NIGHT_BALI.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditDiveDescriptorBuilder(DESC_NIGHT_BALI).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_NIGHT_BALI.equals(editedAmy));
    }
}
