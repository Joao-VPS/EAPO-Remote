import javax.swing.*;

public class AlertWindow {
    private JFrame frame = new JFrame();
    private JLabel messageText = new JLabel();
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancelar");
    private boolean response = false;

    public AlertWindow(String title, String message, boolean hasOk, boolean hasCancel) {
        frame.setTitle(title);
        messageText.setText(message);

        frame.add(messageText);
        if (hasOk) {
            okButton.addActionListener(e -> closeWindow(true));
            frame.add(okButton);
        }
        if (hasCancel) {
            cancelButton.addActionListener(e -> closeWindow(false));
            frame.add(cancelButton);
        }

        frame.setVisible(true);
    }

    private void closeWindow(boolean option) {
        frame.setVisible(false);
        response = option;
    }
}
