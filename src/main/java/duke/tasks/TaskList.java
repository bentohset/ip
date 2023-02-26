package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import duke.exceptions.NoTasksException;
import duke.ui.Ui;

public class TaskList {
    private List<Task> tasks;

    /**
     * Overloaded constructor for the creation of a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for the creation of a TaskList object.
     * @param tasks List of tasks to be included in TaskList after creation.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list
     *
     * @return the number of tasks in the task list
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Gets the list of tasks.
     * @return List of tasks inline separated by commas
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets a task from the task list by index
     *
     * @param index the index of the task
     * @return the task with index `index`
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a given task to the TaskList object.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);

        String addedMessage = "\tGot it. I've added this task:\n" + "\t  " + task;
        String sizeMessage = printSize();

        String[] messagePacket = {addedMessage, sizeMessage};
        Ui.printMessage(messagePacket);
    }

    /**
     * Deletes the task identified by its order in the TaskList.
     * @param taskNum The number corresponding to the task to be deleted.
     */
    public void deleteTask(int deleteIndex) {
        Task taskToDelete = this.tasks.get(deleteIndex-1);
        this.tasks.remove(taskToDelete);

        String removeMessage = "\tNoted. I've removed this task:\n" + "\t  " + taskToDelete;
        String sizeMessage = printSize();

        String[] messagePacket = {removeMessage, sizeMessage};
        Ui.printMessage(messagePacket);
    }

    /**
     * Gets the number of tasks in the list as a message.
     * @return sizeMessage The message indicating how many tasks are in the list.
     */
    public String printSize() {
        String sizeMessage = "\tNow you have " + this.tasks.size() + " tasks in the list.";

        return sizeMessage;
    }

    /**
     * Marks the task, identified by its order, as completed.
     * @param taskNum The number corresponding to the task to be marked as completed.
     */
    public void markTask(int taskNum) {
        Task currentTask = this.tasks.get(taskNum - 1);
        currentTask.markDone();

        String markMessage = "\tNice! I've marked this task as done:\n" + "\t  " + currentTask;

        String[] messagePacket = {markMessage};
        Ui.printMessage(messagePacket);
    }

    /**
     * Unmarks the task, identified by its order, as incomplete.
     * @param taskNum The number corresponding to the task to be unmarked.
     */
    public void unmarkTask(int taskNum) {
        Task currentTask = this.tasks.get(taskNum - 1);
        currentTask.markUndone();

        String unmarkMessage =
                "\tOK, I've marked this task as not done yet:\n" + "\t  " + currentTask;

        String[] messagePacket = {unmarkMessage};
        Ui.printMessage(messagePacket);
    }

    /**
     * Displays information about all the tasks tracked by the TaskList.
     */
    public void printTasks() {
        String[] messagePacket = new String[this.tasks.size() + 1];
        messagePacket[0] = "\tHere are the tasks in your list:";
        int messageCount = 1;

        for (int i = 0; i < this.tasks.size(); i++) {
            String line = "\t" + (i + 1) + ". " + this.tasks.get(i);
            messagePacket[messageCount++] = line;
        }
        Ui.printMessage(messagePacket);
    }

    /**
     * Displays all tasks filtered by a keyword
     * @param filter the keyword to filter the tasks by
     */
    public void printFilteredTasks(String filter) throws NoTasksException {
        List<Task> taskList = getTasks();
        ArrayList<String> messagePacket = new ArrayList<String>();

        messagePacket.add(String.format("\tHere are the tasks matching %s:", filter));

        //convert list of tasks to list of strings
        List<String> stringList = taskList.stream()
                .map(task -> "\t  " + task.toString())
                .collect(Collectors.toList());

        //filter tasks by keyword
        List<String> filteredTasks = stringList.stream()
                .filter(task -> task.toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());

        if (filteredTasks.size() == 0) {
            throw new NoTasksException(filter);
        }

        messagePacket.addAll(filteredTasks);

        Ui.printMessage(messagePacket.toArray(String[] ::new));
    }

}
