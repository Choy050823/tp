package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person immediately if the person is not already in the address book.
 * Otherwise, requests confirmation before allowing the duplicate person to be added.
 */
public class ConfirmAddCommand extends ConfirmCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROLE + "President "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_CONFIRM_DUPLICATE_PERSON =
            "This person already exists: %1$s\nAdd anyway? [y/n]";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";

    private final Person toAdd;

    /**
     * Creates a ConfirmAddCommand to add the specified {@code Person}
     */
    public ConfirmAddCommand(Person person) {
        requireNonNull(person);
        this.toAdd = person;
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
    protected String getConfirmationMessage(Model model) {
        return String.format(MESSAGE_CONFIRM_DUPLICATE_PERSON, Messages.format(toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ConfirmAddCommand)) {
            return false;
        }
        ConfirmAddCommand otherConfirmAddCommand = (ConfirmAddCommand) other;
        return toAdd.equals(otherConfirmAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
