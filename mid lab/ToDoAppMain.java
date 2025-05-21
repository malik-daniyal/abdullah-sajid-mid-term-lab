import javax.swing.*;
import java.awt.*;

public class ToDoAppMain {
    private TaskManagerService taskManager = new TaskManagerService();
    private ReminderService reminderManager = new ReminderService();
    private DefaultListModel<ToDoTask> listModel = new DefaultListModel<>();
    private JList<ToDoTask> taskList = new JList<>(listModel);

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
                ToDoTask task = new ToDoTask(title);
                taskManager.addTask(title);
                listModel.addElement(task);
                taskField.setText("");
            }
        });

        completeButton.addActionListener(e -> {
            ToDoTask selected = taskList.getSelectedValue();
            if (selected != null) {
                selected.markCompleted();
                taskList.repaint();
            }
        });

        remindButton.addActionListener(e -> {
            ToDoTask selected = taskList.getSelectedValue();
            if (selected != null) {
                reminderManager.setStrategy(new EmailReminder());
                reminderManager.scheduleReminder(selected.title);
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoAppMain().showGUI());
    }
}
