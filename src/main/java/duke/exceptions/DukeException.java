package duke.exceptions;

/**
 * Parent class for all exceptions in Duke
 *
 */
public class DukeException extends Exception {
    DukeException(String message) {
        super("\tDuke Error: " + message);
    }
}
