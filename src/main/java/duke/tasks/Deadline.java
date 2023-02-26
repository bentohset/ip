package duke.tasks;

import java.time.LocalDateTime;

import duke.parser.DateTimeParser;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String getType() {
        return "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() 
                + " (by: " + by.format(DateTimeParser.getFormatter()) + ")";
    }

    /**
     * Encodes the task into a string to be stored in duke.txt storage
     *
     * @return string in the format of "deadline ### isDone ### description ### by"
     */
    @Override
    public String encode() {
        return String.format("%s ### %s ### %s", "deadline", super.encode(), this.by);
    }
}
