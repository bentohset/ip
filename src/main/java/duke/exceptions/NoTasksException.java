package duke.exceptions;

public class NoTasksException extends DukeException {
    public NoTasksException(String filter) {
        super(String.format("No tasks fit the filter '%s'", filter));
    }
}
