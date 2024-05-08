// CLASSE USADA APENAS PARA TESTES DE RACIOCÍNIO E LÓGICA.
// DEVE SER APAGADA ASSIM QUE ENVIADA.

/*public class TempLogic {
    // Lógica para procura e tratamento de arquivo no sistema
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

                    window.addElement(new GraphicEQ(linha));

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

    // Lógica para alterar as linhas corretas com os valores corretos
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
    }
}*/
