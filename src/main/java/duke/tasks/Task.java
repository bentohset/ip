package duke.tasks;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //check task with X or tick
    public String getStatusIcon() {
        return (isDone ? "X" : " ");    
    }

    //mark task as done
    public void markDone() {
        this.isDone = true;     
    }

    //mark as undone
    public void markUndone() {
        this.isDone = false;    
    }

    //string of status and description
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;    
    }

    //for file saving purposes
    public String encode() {
        return String.format("%s ### %s", this.isDone, this.description);
    }

    public abstract String getType();
}
