import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class Main {
    public static final MainWindow window = new MainWindow("EqualizerAPI");
    public static final AlertWindow alert = new AlertWindow();
    public static final FileManager fileManager = new FileManager();
    private static boolean status = false;
    private static final List<Map.Entry<Integer, Double>> eqValues = new ArrayList<>();

    public static void main(String[] args) {
        //file = askFileDirectory();

        drawWindow();
        fileManager.autoDetectFile();
    }

    public static void drawWindow() {
        JLabel txtStatus = new JLabel("Status do servidor: " + status);
        window.addElement("text_status", txtStatus);

        window.addElement("text_pathFile", new JLabel("Caminho do arquivo: nenhum arquivo selecionado"));

        JButton chooseFile = new JButton("Selecionar arquivo");
        chooseFile.addActionListener(e -> fileManager.openFile());
        window.addElement("button_openFile", chooseFile);
    }
}