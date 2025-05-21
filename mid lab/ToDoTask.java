public class ToDoTask {
    String title;
    boolean isCompleted;

    public ToDoTask(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✔] " : "[ ] ") + title;
    }
}
