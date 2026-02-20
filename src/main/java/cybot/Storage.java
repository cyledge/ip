package cybot;

import cybot.task.Deadline;
import cybot.task.Event;
import cybot.task.Task;
import cybot.task.TaskList;
import cybot.task.Todo;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file into ArrayList<Task>
     * @return list of tasks
     */
    public TaskList load() throws MyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // create directory if not exist
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // create file if not exist
        if (!file.exists()) {
            return new TaskList(tasks);
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new MyException("File not found: " + filePath);
        }

        return new TaskList(tasks);
    }

    private static void checkDataLength(String line, int len) throws MyException {
        String[] splittedLine = line.split("\\|");
        if (splittedLine.length < len) {
            throw new MyException("Corrupted data: " + line);
        }
    }

    /**
     * Parse a line from file into Task
     * @return Task of that list
     */
    private Task parseLineToTask(String line) throws  MyException {
        // format: "T | 1 | mytask | (D only) | (T only)"
        String[] splittedLine = line.split(" \\| ");
        checkDataLength(line, 3);



        String type = splittedLine[0];
        assert (splittedLine[1].equals("0") || splittedLine.equals("1")) : "Wrong isDone format";
        boolean isDone = splittedLine[1].equals("1");
        String taskName = splittedLine[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(taskName);
            break;
        case "D":
            checkDataLength(line, 4);
            LocalDateTime by = Task.parseFileDateTime((splittedLine[3]));
            task = new Deadline(taskName, by);
            break;
        case "E":
            checkDataLength(line, 5);
            LocalDateTime from = Task.parseFileDateTime((splittedLine[3]));
            LocalDateTime to = Task.parseFileDateTime((splittedLine[4]));
            task = new Event(taskName, from, to);
            break;
        default:
            throw new MyException("Unknown task type: " + line);
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }

    /**
     * Saves task back to file in hard disk
     */
    public void save(TaskList tasks) throws MyException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new MyException("Error saving tasks: " + e.getMessage());
        }
    }

}