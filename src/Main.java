import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    private static MainWindow mainWindow = new MainWindow("EqualizerAPI");
    private static File file;
    private static boolean status = false;
    private static final List<Map.Entry<Integer, Double>> eqValues = new ArrayList<>();

    public static void main(String[] args) {
        //file = askFileDirectory();

        drawWindow();
    }

    public static void drawWindow() {
        JLabel txtStatus = new JLabel("Status do servidor: " + status);
        mainWindow.addElement(txtStatus);

        JButton chooseFile = new JButton("Selecionar arquivo");
        chooseFile.addActionListener(e -> askFileDirectory());
        mainWindow.addElement(chooseFile);
    }

    public static void askFileDirectory() {
        try {
            JFileChooser configtxt = new JFileChooser("C:\\Program Files\\EqualizerAPO\\config");
            configtxt.setDialogTitle("Encontre o arquivo config.txt");
            FileNameExtensionFilter txtfile = new FileNameExtensionFilter("Arquivos de texto", "txt");
            configtxt.removeChoosableFileFilter(configtxt.getFileFilter());
            configtxt.setFileFilter(txtfile);

            int attempt = 1;
            int returnedVal = configtxt.showOpenDialog(configtxt);

            if (returnedVal == JFileChooser.CANCEL_OPTION) {
                return;
            }

            while (
                    returnedVal != JFileChooser.APPROVE_OPTION &&
                            !configtxt.getSelectedFile().exists()) {
                attempt++;
                new AlertWindow("Erro", "Este arquivo é inválido. Selecione ou crie um novo", true, false);
                returnedVal = configtxt.showOpenDialog(configtxt);

                if (attempt >= 3) {
                    new AlertWindow("Cancelado", "Operação cancelada!", true, false);
                    return;
                }
            }

            file = configtxt.getSelectedFile();
            Scanner configScanner = new Scanner(file);
            Boolean foundEq = false;

            while (configScanner.hasNextLine()) {
                String linha = configScanner.nextLine();

                if (linha.startsWith("GraphicEQ:")) {
                    foundEq = true;
                    eqValues.clear();

                    mainWindow.addElement(new GraphicEQ(linha));

                    String[] values = linha.substring(10).split(";");

                    for (String part : values) {
                        String[] keyValue = part.trim().split(" ");
                        if (keyValue.length == 2) {
                            eqValues.add(Map.entry(Integer.parseInt(keyValue[0]), Double.parseDouble(keyValue[1])));
                        }
                    }
                }
            }

            if (!foundEq) {
                FileWriter writer = new FileWriter(file);
                writer.write("GraphicEQ: 25 0; 40 0; 63 0; 100 0; 160 0; 250 0; 400 0; 630 0; 1000 0; 1600 0; 2500 0; 4000 0; 6300 0; 10000 0; 16000 0");
                writer.close();
                eqValues.clear();
                eqValues.add(Map.entry(25, 0.0));
                eqValues.add(Map.entry(40, 0.0));
                eqValues.add(Map.entry(63, 0.0));
                eqValues.add(Map.entry(100, 0.0));
                eqValues.add(Map.entry(160, 0.0));
                eqValues.add(Map.entry(250, 0.0));
                eqValues.add(Map.entry(400, 0.0));
                eqValues.add(Map.entry(630, 0.0));
                eqValues.add(Map.entry(1000, 0.0));
                eqValues.add(Map.entry(1600, 0.0));
                eqValues.add(Map.entry(2500, 0.0));
                eqValues.add(Map.entry(4000, 0.0));
                eqValues.add(Map.entry(6300, 0.0));
                eqValues.add(Map.entry(10000, 0.0));
                eqValues.add(Map.entry(16000, 0.0));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeValue(int frequency, int value) {
        try {
            eqValues.set(frequency, Map.entry(eqValues.get(frequency).getKey(), value / 10.0));

            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            String finalFile = "";

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("GraphicEQ:")) {
                    String newLine = eqValues.toString();
                    newLine = newLine.replace("=", " ").replace(",", ";");
                    newLine = newLine.substring(1, newLine.length() - 1);
                    finalFile += "GraphicEQ: " + newLine + "\n";
                } else {
                    finalFile += line + "\n";
                }
            }

            System.out.println(finalFile);
            FileWriter writer = new FileWriter(file);
            writer.write(finalFile);
            writer.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



		/*
		 * while (true) {
			try {
				System.out.println("Cole aqui o caminho do arquivo:");
				//String pathFile = consoleInput.nextLine() + "\\config.txt";
				//File file = new File(pathFile);

				if (!file.exists()) {
					file.createNewFile();
					FileWriter writer = new FileWriter(file);
					writer.write("GraphicEQ: 25 0; 40 0; 63 0; 100 0; 160 0; 250 0; 400 0; 630 0; 1000 0; 1600 0; 2500 0; 4000 0; 6300 0; 10000 0; 16000 0");
					writer.close();
					System.out.println("Criado novo arquivo de configuração config.txt");
				}

				boolean isOurs = false;
				Scanner info = new Scanner(file);

				if (!info.hasNextLine())
					isOurs = true;

				while (info.hasNextLine()) {
					if (info.nextLine().contains("GraphicEQ"))
						isOurs = true;
				}

				if (isOurs)
					return file;

				System.out.println("Já existe um arquivo de configuração, mas ele não é nosso. Escolha outra pasta!");

			} catch (Exception e) {
				System.out.println("Arquivo inválido. Verifique o caminho e tente novamente!");
			}
		}*/
    }
}