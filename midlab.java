
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class Task {
    String title;
    boolean isCompleted;

    Task(String title) {
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

interface ReminderStrategy {
    void remind(String taskTitle);
}

class EmailReminder implements ReminderStrategy {
    public void remind(String taskTitle) {
        System.out.println("[EMAIL] Reminder for task: " + taskTitle);
    }
}

class RingReminder implements ReminderStrategy {
    public void remind(String taskTitle) {
        System.out.println("[RING] Reminder ring for task: " + taskTitle);
    }
}


class TaskManager {
    List<Task> tasks = new ArrayList<>();

    void addTask(String title) {
        tasks.add(new Task(title));
    }

    List<Task> getTasks() {
        return tasks;
    }
}

class ReminderManager {
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


public class ToDoAppGUI {
    private TaskManager taskManager = new TaskManager();
    private ReminderManager reminderManager = new ReminderManager();
    private DefaultListModel<Task> listModel = new DefaultListModel<>();
    private JList<Task> taskList = new JList<>(listModel);

    public void showGUI() {
        JFrame frame = new JFrame("To-Do Application");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JTextField taskField = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark Completed");
        JButton remindButton = new JButton("Remind (Email)");

        panel.add(taskField, BorderLayout.NORTH);
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(remindButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String title = taskField.getText().trim();
            if (!title.isEmpty()) {
                taskManager.addTask(title);
                listModel.addElement(new Task(title));
                taskField.setText("");
            }
        });

        completeButton.addActionListener(e -> {
            Task selected = taskList.getSelectedValue();
            if (selected != null) {
                selected.markCompleted();
                taskList.repaint();
            }
        });

        remindButton.addActionListener(e -> {
            Task selected = taskList.getSelectedValue();
            if (selected != null) {
                reminderManager.setStrategy(new EmailReminder());
                reminderManager.scheduleReminder(selected.title);
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoAppGUI().showGUI());
    }
}
