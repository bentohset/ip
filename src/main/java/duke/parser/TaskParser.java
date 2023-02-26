package duke.parser;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidFormatException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.InvalidDateTimeException;

import java.time.LocalDateTime;

public class TaskParser {
    public static Task getTaskFromCommand(String[] inputArray) throws InvalidFormatException, InvalidCommandException, InvalidDateTimeException, InvalidTaskException {
        Task task;
        String command = inputArray[0];

        switch (command) {
        case "deadline":
            if (!inputArray[1].contains("/by")) {
                throw new InvalidFormatException("/by");
            }
            String[] deadlineDetails = inputArray[1].split(" /by ", 2);
            if (deadlineDetails.length < 2) {
                throw new InvalidTaskException(command);
            }
            LocalDateTime by = DateTimeParser.parseDate(deadlineDetails[1]);

            task = new Deadline(deadlineDetails[0], by);
            break;

        case "event":
            if (!inputArray[1].contains("/from") && !inputArray[1].contains("/to")) {
                throw new InvalidFormatException("/from", "/to");
            }
            //splits input according to eventDescription, /from, /to
            String[] eventDetails = inputArray[1].split(" /from | /to ", 3);
            
            if (eventDetails.length < 3) {
                throw new InvalidTaskException(command);
            }
            LocalDateTime from = DateTimeParser.parseDate(eventDetails[1]);
            LocalDateTime to = DateTimeParser.parseDate(eventDetails[2]);

            task = new Event(eventDetails[0], from, to);
            break;

        case "todo":
            if (inputArray.length == 1) {
                throw new InvalidTaskException(command);
            }
            String todoDetails = inputArray[1];
            task = new Todo(todoDetails);
            break;

        default:
            throw new InvalidCommandException();

        }
        return task;
    }
}
