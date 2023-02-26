package duke.tasks;

import java.time.LocalDateTime;

import duke.parser.DateTimeParser;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getType() {
        return "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() 
                + " (from: " + from.format(DateTimeParser.getFormatter()) 
                + " to: " + to.format(DateTimeParser.getFormatter()) + ")";
    }

    /**
     * Encodes the task into a string to be stored in duke.txt storage
     *
     * @return string in the format of "event ### isDone ### description ### from ### to"
     */
    @Override
    public String encode() {
        return String.format("%s ### %s ### %s ### %s", "event", super.encode(), this.from, this.to);
    }
}
