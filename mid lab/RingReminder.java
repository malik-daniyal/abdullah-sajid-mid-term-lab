public class RingReminder implements ReminderStrategy {
    public void remind(String taskTitle) {
        System.out.println("[RING] Reminder ring for task: " + taskTitle);
    }
}
