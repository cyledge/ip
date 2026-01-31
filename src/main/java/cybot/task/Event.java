package cybot.task;

import cybot.MyException;


import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public static final String EVENT_FORMAT = "event <name> /from <" + Task.DATE_PATTERN + ">" + "/to <" + Task.DATE_PATTERN + ">";


    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Factory method to create Deadline from raw input
     * @param input "<name> /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm
     * @return Event obj
     * @throws  MyException for invalid input
     */
    public static Event createDeadline(String input) throws MyException {
        try {
            int fromIndex = input.indexOf(" /from ");
            int toIndex = input.indexOf(" /to ");

            String name = input.substring(0, fromIndex);

            String from = input.substring(fromIndex + 7, toIndex).trim();
            String to = input.substring(toIndex + 5).trim();

            LocalDateTime fromDateTime = Task.parseFileDateTime(from);
            LocalDateTime toDateTime = Task.parseFileDateTime(to);

            return new Event(name, fromDateTime, toDateTime);
        } catch (IllegalArgumentException e) {
            throw new MyException("Please use: " + EVENT_FORMAT);
        }

    }


    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + name + " | " + from.format(FILE_FORMATTER) + " | " + to.format(FILE_FORMATTER);
    }

    @Override
    public String toString() {
        String formattedTo = to.format(DISPLAY_FORMATTER);
        String formattedFrom = from.format(DISPLAY_FORMATTER);

        return String.format("[E]%s (from: %s to: %s)",  super.toString(), formattedFrom, formattedTo);

    }
}
