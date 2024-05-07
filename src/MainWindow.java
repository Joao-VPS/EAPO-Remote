import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    JFrame window = new JFrame();
    JPanel app = new JPanel();

    public MainWindow(String title) {
        window.setTitle(title);
        window.setSize(1000, 700);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        app.setLayout(new BoxLayout(app, BoxLayout.Y_AXIS));

        window.add(app);
        window.setVisible(true);
    }

    public void updateElements(List<JComponent> elements) {
        app.removeAll();
        for (JComponent element : elements) {
            app.add(element);
        }
    }

    public void addElement(JComponent element) {
        app.add(element);
        app.updateUI();
    }
}
