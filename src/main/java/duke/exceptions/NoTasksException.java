package duke.exceptions;

/**
 * An exception representing an empty filtered list when filtered by keyword
 */
public class NoTasksException extends DukeException {
    public NoTasksException(String filter) {
        super(String.format("No tasks fit the filter '%s'", filter));
    }
}
