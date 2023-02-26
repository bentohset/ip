package duke.tasks;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String encode() {
        return String.format("%s ### %s", "todo", super.encode());
    }

    public String getType() {
        return "todo";
    }
}
