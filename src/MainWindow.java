import javax.swing.*;
import java.util.LinkedHashMap;

public class MainWindow {
    private final JFrame window = new JFrame();
    private final JPanel app = new JPanel();

    private final LinkedHashMap<String, JComponent> elements = new LinkedHashMap<>();

    public MainWindow(String title) {
        window.setTitle(title);
        window.setSize(1000, 700);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        app.setLayout(new BoxLayout(app, BoxLayout.Y_AXIS));

        window.add(app);
        window.setVisible(true);
    }

    public void updateElement(String name, JComponent newComponent) {
        app.removeAll();

        if (elements.containsKey(name)) {
            elements.replace(name, newComponent);
        } else {
            elements.put(name, newComponent);
        }

        elements.forEach((key, value) -> app.add(value));

        app.updateUI();
    }

    public void addElement(String name, JComponent element) {
        if (elements.containsKey(name)) {
            updateElement(name, element);
        } else {
            elements.put(name, element);
            app.add(element);
            app.updateUI();
        }
    }
}
