public class ReminderService {
    private ReminderStrategy strategy;

    public void setStrategy(ReminderStrategy strategy) {
        this.strategy = strategy;
    }

    public void scheduleReminder(String taskTitle) {
        if (strategy != null) {
            strategy.remind(taskTitle);
        }
    }
}
