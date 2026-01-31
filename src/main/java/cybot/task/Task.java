package cybot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String name;
    protected boolean isDone;
    static final String DISPLAY_PATTERN = "dd MMM yyyy, hh:mm a";
    static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
    protected static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern(DISPLAY_PATTERN);
    protected static final DateTimeFormatter FILE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public abstract String toFileFormat();

    public static String fileDateFormatStr() {
        return DATE_PATTERN;
    }

    /**
     * Parse String from file or user input into DateTime in file format
     *
     */
    public static LocalDateTime parseFileDateTime(String dateTimeStr) throws IllegalArgumentException {
        try {
            return LocalDateTime.parse(dateTimeStr, FILE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid datetime format. Format: YYYY-MM-DD HH:mm\n" +
                            "Example: 2026-02-18 15:26"
            );
        }
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String box;
        if (isDone) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return box + name;
    }
}
