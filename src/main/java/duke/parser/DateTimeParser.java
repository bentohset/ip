package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidDateTimeException;

public class DateTimeParser {
    private static final String FORMAT = "yyyy-MM-dd HHmm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public static LocalDateTime parseDate(String input) throws InvalidDateTimeException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(input.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }

        return dateTime;
    }

    public static String getFormat() {
        return FORMAT;
    }

    public static DateTimeFormatter getFormatter() {
        return FORMATTER;
    }

}
