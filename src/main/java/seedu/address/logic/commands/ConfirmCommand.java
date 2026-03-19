package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command that requires user confirmation before the actual
 * operation is carried out.
 *
 * <p>A {@code ConfirmCommand} does not perform the requested action immediately.
 * Instead, it validates the request, prepares a confirmation prompt, and returns
 * a {@code CommandResult} indicating that the application is awaiting a
 * confirmation response from the user.
 *
 * <p>When the user confirms the action, the corresponding non-confirmation
 * command is executed separately by the {@code LogicManager}.
 */

public interface ConfirmCommand {

    /**
     * Returns the confirmation message to be shown to the user.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the confirmation message.
     * @throws CommandException if the confirmation request is invalid.
     */
    public String getConfirmationMessage(Model model) throws CommandException;

}
