package duke.exceptions;
public class DukeException extends Exception {
    DukeException(String message) {
        super("\tDuke Error: " + message);
    }
}
