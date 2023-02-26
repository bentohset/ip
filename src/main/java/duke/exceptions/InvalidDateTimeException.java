package duke.exceptions;

import duke.parser.DateTimeParser;

public class InvalidDateTimeException extends DukeException {
    private static final String MESSAGE = "Invalid date or time, Please use the correct format: \n"
            + "\t\t" + DateTimeParser.getFormat() + " in 24-hour format"; 

    public InvalidDateTimeException() {
        super(MESSAGE);
    }
}
