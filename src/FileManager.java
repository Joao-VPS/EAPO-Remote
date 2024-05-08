import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {
    private File file;

    public File autoDetectFile() {
        File result = new File("C:\\Program Files\\EqualizerAPO\\config\\config.txt");

        if (result.exists()) {
            file = result;
            Main.alert.consoleMessage("O arquivo config.txt foi encontrado automaticamente em " + file.getPath(), 'e');
            Main.alert.consoleMessage(String.valueOf(file), 'e');
        } else {
            openFile();
        }

        getObjects(file);
        return file;
    }

    public File openFile() {
        JFileChooser fileExplorer = new JFileChooser("C:\\Program Files\\EqualizerAPO\\config");
        fileExplorer.setDialogTitle("Procure o arquivo config.txt");
        fileExplorer.removeChoosableFileFilter(fileExplorer.getFileFilter());
        fileExplorer.addChoosableFileFilter(
                new FileNameExtensionFilter("Arquivos de Texto", "txt"));

        int returnedValue = fileExplorer.showOpenDialog(null);

        while (returnedValue != JFileChooser.APPROVE_OPTION && fileExplorer.getSelectedFile() == null || !fileExplorer.getSelectedFile().exists()) {
            returnedValue = fileExplorer.showOpenDialog(null);
            Main.alert.newAlert("Erro!", "Você precisa selecionar um arquivo válido ou criar um novo arquivo de texto", true, false);
        }

        file = fileExplorer.getSelectedFile();
        getObjects(file);
        return fileExplorer.getSelectedFile();
    }

    public void getObjects(File file) {
        Main.alert.consoleMessage("==============================", 'a');
        Main.alert.consoleMessage("Lendo e carregando novo arquivo", 'a');
        Main.alert.consoleMessage("==============================", 'a');

        if (!file.exists()) {
            Main.alert.newAlert("Erro!", "O aplicativo tentou ler um arquivo inexistente!", true, false);
            return;
        }

        FileReader reader;
        try {
            reader = new FileReader(file);
        } catch (Exception e) {
            Main.alert.newAlert("Opa!", "O arquivo não foi encontrado!", true, false);
            Main.alert.consoleMessage("O tratamento de segurança falhou e uma exceção foi lançada porque o arquivo não foi encontrado ao chamar FileManager.getObjects().", '4');
            return;
        }

        Scanner fileScanner = new Scanner(reader);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            if (line.startsWith("GraphicEQ:")) {
                Main.window.addElement("graphic_eq", new GraphicEQ(line));
            }
            if (line.startsWith("Filter:")) {
                Main.alert.consoleMessage("Parece que o objeto Filter ainda não foi criado...", 'e');
            }
        }

        Main.window.updateElement("text_pathFile", new JLabel("Caminho do arquivo: " + file.getPath()));
    }

    public void writer(String name, String values) {
        try {
            FileReader reader = new FileReader(file);
            Scanner fileScanner = new Scanner(reader);
            String newFile = "";
            boolean lineFound = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.startsWith(name)) {
                    newFile += name + ": " + values + "\n";
                    lineFound = true;
                } else {
                    newFile += line + "\n";
                }
            }

            if (!lineFound) {
                newFile += name + ": " + values + "\n";
            }

            FileWriter writer = new FileWriter(file);
            writer.write(newFile);
            writer.close();

            Main.alert.consoleMessage("Escrito novo arquivo de configuração:", 'a');
            Main.alert.consoleMessage(newFile);
        } catch (Exception e) {
            Main.alert.consoleMessage("Falha ao atualizar arquivo de configuração:", '4');
            Main.alert.consoleMessage(e.getMessage(), '4');
        }
    }

    /*public void changeEqValue(Integer frequency, Double value) {
        writer("GraphicEQ");
    }*/
}
