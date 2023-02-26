package duke.tasks;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");    
    }

    public void markDone() {
        this.isDone = true;     
    }

    public void markUndone() {
        this.isDone = false;    
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;    
    }

    /**
     * Encodes the task into a string to be stored in duke.txt storage
     *
     * @return string in the format of "isDone ### description"
     */
    public String encode() {
        return String.format("%s ### %s", this.isDone, this.description);
    }

    public abstract String getType();
}
