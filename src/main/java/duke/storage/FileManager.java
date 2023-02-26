package duke.storage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

public class FileManager {
    private File file;
    private String filePath;
    private String dataDirectory;

    /**
     * Constructor for the File Manager object.
     * <p>
     * Creates a File object according to the relative path /data/duke.txt to store the data
     * <p>
     * Initializes a /data/ folder and duke.txt if it does not exist
     */
    public FileManager() {
        this.dataDirectory = "./data/";
        this.filePath = this.dataDirectory + "duke.txt";
        this.file = new File(this.filePath);
        File directory = new File(this.dataDirectory);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves the current taskList accumulated over the program's life and stores it in /data/duke.txt
     *
     * @param taskList the taskList containing all the user's tasks
     */
    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (Task task : taskList.getTasks()) {
                writer.write(task.encode() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves any tasks saved in /data/duke.txt if the directory exists.
     * <p>
     * Decodes the contents of duke.txt into a taskList object. 
     * Called upon initialisation of Duke.java
     *
     * @return taskList the taskList containing all the user's tasks saved in storage
     */
    public List<Task> retrieve() {
        Scanner scanner = null;
        List<Task> taskList = new ArrayList<>();
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                taskList.add(this.decodeTask(encoded));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
        
        return taskList;
    }

    /**
     * Decoder method to read each line of duke.txt storage and converts into a Task object
     *
     * @param task the string corresponding to the lines of duke.txt
     * @return decoded Task object
     */
    private Task decodeTask(String task) {
        String[] components = task.split(" ### ");
        String command = components[0];
        String mark = components[1];
        String description = components[2];
        Task decoded = null;

        switch (command) {
        case "todo":
            decoded = new Todo(description);
            break;
        case "event":
            LocalDateTime from = LocalDateTime.parse(components[3]);
            LocalDateTime to = LocalDateTime.parse(components[4]);

            decoded = new Event(description, from, to);
            break;
        case "deadline":
            LocalDateTime by = LocalDateTime.parse(components[3]);

            decoded = new Deadline(description, by);
            break;
        default:
            break;
        }
        if (mark.equals("true")) {
            decoded.markDone();
        }
        return decoded;
    }
}
