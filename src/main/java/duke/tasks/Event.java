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

    @Override
    public String toString() {
        return "[E]" + super.toString() 
                + " (from: " + from.format(DateTimeParser.getFormatter()) 
                + " to: " + to.format(DateTimeParser.getFormatter()) + ")";
    }

    @Override
    public String encode() {
        return String.format("%s ### %s ### %s ### %s", "event", super.encode(), this.from, this.to);
    }

    public String getType() {
        return "event";
    }
}
