package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests and unit tests for {@code ConfirmAddCommand}.
 */
public class ConfirmAddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPerson_success() {
        Person personToAdd = new PersonBuilder().build();
        ConfirmAddCommand confirmAddCommand = new ConfirmAddCommand(personToAdd);

        String expectedMessage = String.format(ConfirmAddCommand.MESSAGE_SUCCESS,
                Messages.format(personToAdd));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addPerson(personToAdd);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage);

        assertCommandSuccess(confirmAddCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        ConfirmAddCommand confirmAddAliceCommand = new ConfirmAddCommand(alice);
        ConfirmAddCommand confirmAddBobCommand = new ConfirmAddCommand(bob);

        assertTrue(confirmAddAliceCommand.equals(confirmAddAliceCommand));

        ConfirmAddCommand confirmAddAliceCommandCopy = new ConfirmAddCommand(alice);
        assertTrue(confirmAddAliceCommand.equals(confirmAddAliceCommandCopy));

        assertFalse(confirmAddAliceCommand.equals(1));
        assertFalse(confirmAddAliceCommand.equals(null));
        assertFalse(confirmAddAliceCommand.equals(confirmAddBobCommand));
    }

    @Test
    public void toStringMethod() {
        ConfirmAddCommand confirmAddCommand = new ConfirmAddCommand(ALICE);
        String expected = ConfirmAddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, confirmAddCommand.toString());
    }

}
