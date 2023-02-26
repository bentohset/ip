package duke.exceptions;

import duke.parser.DateTimeParser;

/**
 * An exception representing an input string which cannot
 * be parsed into a valid DateTime object
 */
public class InvalidDateTimeException extends DukeException {
    private static final String MESSAGE = "Invalid date or time, Please use the correct format: \n"
            + "\t\t" + DateTimeParser.getFormat() + " in 24-hour format"; 

    public InvalidDateTimeException() {
        super(MESSAGE);
    }
}
