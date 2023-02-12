package duke.storage;

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

    public FileManager() {
        this.dataDirectory = "./ip/data/";
        this.filePath = this.dataDirectory + "duke.txt";
        this.file = new File(this.filePath);
        File directory = new File(this.dataDirectory);
        try {
            System.out.println(dataDirectory);
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

    public List<Task> retrieve() {
        Scanner scanner = null;
        List<Task> taskList = new ArrayList<>();
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                System.out.println(encoded);
                taskList.add(this.decodeTask(encoded));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
        
        return taskList;
    }

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
            decoded = new Event(description, components[3], components[4]);
            break;
        case "deadline":
            decoded = new Deadline(description, components[3]);
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
