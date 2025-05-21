public class EmailReminder implements ReminderStrategy {
    public void remind(String taskTitle) {
        System.out.println("[EMAIL] Reminder for task: " + taskTitle);
    }
}
