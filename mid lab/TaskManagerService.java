import java.util.ArrayList;
import java.util.List;

public class TaskManagerService {
    private List<ToDoTask> tasks = new ArrayList<>();

    public void addTask(String title) {
        tasks.add(new ToDoTask(title));
    }

    public List<ToDoTask> getTasks() {
        return tasks;
    }
}
