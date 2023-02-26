package duke.tasks;

import java.time.LocalDateTime;

import duke.parser.DateTimeParser;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() 
                + " (by: " + by.format(DateTimeParser.getFormatter()) + ")";
    }

    @Override
    public String encode() {
        return String.format("%s ### %s ### %s", "deadline", super.encode(), this.by);
    }

    public String getType() {
        return "deadline";
    }
}
