package duke.parser;

import java.util.Scanner;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidFormatException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.NoTasksException;
import duke.exceptions.InvalidDateTimeException;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.commands.Command;
import duke.commands.AddCommand;
import duke.commands.ListCommand;
import duke.commands.DeleteCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.FindCommand;

public class CommandParser {
    private static final String LIST = "list";
    private static final String EXIT = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private TaskList taskList;

    public CommandParser(TaskList tasks) {
        this.taskList = tasks;
    }
    
    /**
     * Validates an index if it is within boundaries of the task list and returns the index
     *
     * @param inputArr the input array being parsed
     * @return a valid index
     * @throws InvalidCommandException the input string does not match any valid command
     * @throws InvalidTaskException the input string does not match any valid task
     */
    private int getValidIndex(String[] inputArr, String command) throws InvalidTaskException, InvalidCommandException {
        if (inputArr.length == 1) {
            throw new InvalidTaskException(command);
        }

        //1-based index
        int index = Integer.parseInt(inputArr[1]);

        if (index <= 0 || index > this.taskList.size()) {
            throw new InvalidCommandException("No such task exists! Please try again");
        }

        return index;
    }

    /**
     * Parses and returns a `Command` object from a given input string
     *
     * @param inputArray the input array to be parsed, consisting of all the command arguments
     * @throws InvalidCommandException the input string does not match any valid command
     * @throws InvalidTaskException the input string does not match any valid task
     * @throws InvalidFormatException the input string is not in the correct format
     * @throws InvalidDateTimeException the date and time of the input string is in the wrong format
     * @throws NoTasksException no tasks resulting from the filter is found
     */
    public Command parseCommand(String[] inputArray) throws InvalidCommandException, InvalidTaskException, InvalidFormatException, InvalidDateTimeException, NoTasksException {
        String command = inputArray[0];
        
        switch (command) {
        case TODO:
        case DEADLINE:
        case EVENT:
            if (inputArray.length == 1) {
                throw new InvalidTaskException(command);
            }
            Task toAdd = TaskParser.getTaskFromCommand(inputArray);
            return new AddCommand(toAdd, taskList);

        case LIST:
            return new ListCommand(taskList);

        case MARK:
        case UNMARK:
            boolean isMark = command.equals(MARK);
            int taskNum = getValidIndex(inputArray, command);
            return isMark ? new MarkCommand(taskNum, taskList): new UnmarkCommand(taskNum, taskList);
            
        case DELETE:
            int deleteIndex = getValidIndex(inputArray, command);
            return new DeleteCommand(taskList, deleteIndex);

        case FIND:
            if (inputArray.length == 1) {
                throw new InvalidTaskException(command);
            }
            String keyword = inputArray[1];
            return new FindCommand(taskList, keyword);
            
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Gets lines of input strings from the Scanner and passes it to the handler in an array
     *
     * @throws InvalidCommandException the input string does not match any valid command
     * @throws InvalidTaskException the input string does not match any valid task
     * @throws InvalidFormatException the input string is not in the correct format
     * @throws InvalidDateTimeException the date and time of the input string is in the wrong format
     * @throws NoTasksException no tasks resulting from the filter is found
     */
    public void getInput() throws InvalidCommandException, InvalidTaskException, InvalidFormatException, InvalidDateTimeException , NoTasksException {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        do {
            String inputString = input.nextLine();
            String[] inputArray = inputString.split(" ", 2);
            if (inputArray[0].equals(EXIT)) {
                isRunning = false;
            } else {
                try {
                    Command cmd = parseCommand(inputArray);
                    cmd.handleCommand();
                } catch (InvalidTaskException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                } catch (NoTasksException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidDateTimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (isRunning);
        input.close();
    }
}
