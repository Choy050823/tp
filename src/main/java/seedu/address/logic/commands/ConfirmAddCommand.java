package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person immediately if the person is not already in the address book.
 * Otherwise, requests confirmation before allowing the duplicate person to be added.
 */
public class ConfirmAddCommand extends AddCommand implements ConfirmCommand {

    public static final String MESSAGE_CONFIRM_DUPLICATE_PERSON =
            "This person already exists: %1$s\nAdd anyway? [y/n]";

    /**
     * Creates a ConfirmAddCommand to add the specified {@code Person}
     */
    public ConfirmAddCommand(Person person) {
        super(person);
    }

    /**
     * Executes the command. If the person is not a duplicate, adds the person immediately.
     * Otherwise, returns a confirmation message and waits for user confirmation.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a command result containing either a success message or a confirmation prompt.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (!model.hasPerson(toAdd)) {
            model.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
        }

        return new CommandResult(
                String.format(MESSAGE_CONFIRM_DUPLICATE_PERSON, Messages.format(toAdd)),
                false, false, true
        );
    }

    @Override
    public String getConfirmationMessage(Model model) {
        return String.format(MESSAGE_CONFIRM_DUPLICATE_PERSON, Messages.format(toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConfirmAddCommand)) {
            return false;
        }

        ConfirmAddCommand otherConfirmAddCommand = (ConfirmAddCommand) other;
        return toAdd.equals(otherConfirmAddCommand.toAdd);
    }

}
