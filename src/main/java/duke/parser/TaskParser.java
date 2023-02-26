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
    /**
     * Parses a task command as a string array and returns the respective task object
     *
     * @param inputArray the input array to be parsed, consisting of all the command arguments
     * @return the Task object according to the string array
     * @throws InvalidCommandException the input string does not match any valid command
     * @throws InvalidTaskException the input string does not match any valid task
     * @throws InvalidFormatException the input string is not in the correct format
     * @throws InvalidDateTimeException the date and time of the input string is in the wrong format
     */
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
