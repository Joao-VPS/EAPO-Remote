import javax.swing.*;
import java.awt.*;

public class AlertWindow {
    private final JFrame frame = new JFrame();

    public void newAlert(String title, String message, boolean hasOk, boolean hasCancel) {
        frame.setTitle(title);
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel alertPanel = new JPanel();
        alertPanel.setLayout(new BoxLayout(alertPanel, BoxLayout.Y_AXIS));
        alertPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        alertPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel messageText = new JLabel();
        messageText.setText(message);

        alertPanel.add(messageText);
        if (hasOk) {
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> closeWindow(true));
            alertPanel.add(okButton);
        }
        if (hasCancel) {
            JButton cancelButton = new JButton("Cancelar");
            cancelButton.addActionListener(e -> closeWindow(false));
            alertPanel.add(cancelButton);
        }

        frame.add(alertPanel);
        frame.setVisible(true);
    }

    private void closeWindow(boolean option) {
        frame.setVisible(false);
    }

    public void consoleMessage(String message) {
        System.out.println(message);
    }

    public void consoleMessage(String message, Character color) {
        String escape = switch (color.toString().toLowerCase()) {
            case "0" -> "\033[30m";
            case "1" -> "\033[34m";
            case "2" -> "\033[32m";
            case "3" -> "\033[36m";
            case "4" -> "\033[31m";
            case "5" -> "\033[35m";
            case "6" -> "\033[33m";
            case "7" -> "\033[37m";
            case "8" -> "\033[90m";
            case "9" -> "\033[94m";
            case "a" -> "\033[92m";
            case "b" -> "\033[96m";
            case "c" -> "\033[91m";
            case "d" -> "\033[95m";
            case "e" -> "\033[93m";
            case "f" -> "\033[97m";
            default -> "\033[0m";
        };

        System.out.println(escape + message + "\033[0m");
    }
}
