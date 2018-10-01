package seedu.divelog.storage;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.divelog.commons.exceptions.IllegalValueException;
import seedu.divelog.commons.util.XmlUtil;
import seedu.divelog.model.DiveLog;
import seedu.divelog.testutil.TypicalPersons;

public class XmlSerializableDiveLogTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "XmlSerializableDiveLogTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.xml");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.xml");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.xml");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        XmlSerializableDiveLog dataFromFile = XmlUtil.getDataFromFile(TYPICAL_PERSONS_FILE,
                XmlSerializableDiveLog.class);
        DiveLog diveLogFromFile = dataFromFile.toModelType();
        DiveLog typicalPersonsDiveLog = TypicalPersons.getTypicalAddressBook();
        assertEquals(diveLogFromFile, typicalPersonsDiveLog);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        XmlSerializableDiveLog dataFromFile = XmlUtil.getDataFromFile(INVALID_PERSON_FILE,
                XmlSerializableDiveLog.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        XmlSerializableDiveLog dataFromFile = XmlUtil.getDataFromFile(DUPLICATE_PERSON_FILE,
                XmlSerializableDiveLog.class);
        thrown.expect(IllegalValueException.class);
        thrown.expectMessage(XmlSerializableDiveLog.MESSAGE_DUPLICATE_PERSON);
        dataFromFile.toModelType();
    }

}
