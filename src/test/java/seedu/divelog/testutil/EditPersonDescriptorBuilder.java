package seedu.divelog.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.divelog.logic.commands.EditCommand;
import seedu.divelog.logic.commands.EditCommand.EditDiveDescriptor;
import seedu.divelog.model.person.Address;
import seedu.divelog.model.person.Email;
import seedu.divelog.model.person.Name;
import seedu.divelog.model.person.Person;
import seedu.divelog.model.person.Phone;
import seedu.divelog.model.tag.Tag;

/**
 * A utility class to help with building EditDiveDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditCommand.EditDiveDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditCommand.EditDiveDescriptor();
    }

    public EditPersonDescriptorBuilder(EditCommand.EditDiveDescriptor descriptor) {
        this.descriptor = new EditCommand.EditDiveDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDiveDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditDiveDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditDiveDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditDiveDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditDiveDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditDiveDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditDiveDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditDiveDescriptor build() {
        return descriptor;
    }
}
