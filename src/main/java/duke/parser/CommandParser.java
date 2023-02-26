package duke.parser;

import java.util.Scanner;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidFormatException;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.commands.Command;
import duke.commands.AddCommand;
import duke.commands.ListCommand;
import duke.commands.DeleteCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;

public class CommandParser {
    private static final String LIST = "list";
    private static final String EXIT = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private TaskList taskList;

    public CommandParser(TaskList tasks) {
        this.taskList = tasks;
    }

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

    public Command parseCommand(String[] inputArray) throws InvalidCommandException, InvalidTaskException, InvalidFormatException {
        String command = inputArray[0];
        
        switch (command) {
        case TODO:
        case DEADLINE:
        case EVENT:
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
            
        default:
            throw new InvalidCommandException();
        }
    }

    public void getInput() throws InvalidCommandException, InvalidTaskException, InvalidFormatException {
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
                }
            }
        } while (isRunning);
        input.close();
    }
}
