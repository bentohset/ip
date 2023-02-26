package duke.tasks;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Encodes the task into a string to be stored in duke.txt storage
     *
     * @return string in the format of "todo ### isDone ### description"
     */
    @Override
    public String encode() {
        return String.format("%s ### %s", "todo", super.encode());
    }
}
