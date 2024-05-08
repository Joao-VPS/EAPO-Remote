import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GraphicEQ extends JComponent {
    private final List<Pair<Integer, Double>> values = new ArrayList<>();

    public GraphicEQ(String lineData) {
        getValues(lineData);
        showEq();
    }

    private void getValues(String data) {
        //detect first ocurrence of a number
        int firstDigitIndex = -1;
        for (int i = 0; i < data.length(); i++) {
            if (firstDigitIndex < 0 && Character.isDigit(data.charAt(i))) {
                firstDigitIndex = i;
            }
        }

        if (firstDigitIndex == -1) {
            Main.alert.newAlert("Erro", "O valor passado não tem nenhum parâmetro válido", true, false);

            return;
        }

        String[] params = data.substring(firstDigitIndex).trim().split(";");
        values.clear();

        for (String element : params) {
            String[] keyValue = element.trim().split(" ");
            Main.alert.consoleMessage(Arrays.toString(keyValue), '9');

            if (keyValue.length >= 2) {
                Integer key = Integer.parseInt(keyValue[0]);
                double value = Double.parseDouble(keyValue[1]);

                if (value > 20.0) value = 20.0;
                if (value < -20.0) value = -20.0;

                values.add(new Pair<>(key, value));
            }
        }

        Main.alert.consoleMessage("Array completo: " + values, 'a');
    }

    public void showEq() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel txt_graphic = new JLabel("GraphicEQ");
        add(txt_graphic);

        for (Pair<Integer, Double> element : values) {
            JPanel sliderPanel = new JPanel();
            sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

            double value = element.getValue() * 10;

            JSlider slider = new JSlider(JSlider.VERTICAL, -200, 200, (int) value);
            slider.addChangeListener(e -> changeValue(element.getKey(), slider.getValue() / 10.0));

            slider.setUI(new BasicSliderUI(slider) {
                @Override
                public void paintTrack(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    Rectangle trackBounds = trackRect;

                    int centerX = (trackBounds.width / 2) - 2;
                    int centerY = (trackBounds.height / 2);
                    g2d.setColor(Color.GRAY);
                    g2d.fillRect(centerX, trackBounds.y, 4, trackBounds.height);

                    g2d.setColor(Color.GREEN);
                    if (slider.getValue() > 0) {
                        g2d.fillRect(centerX, centerY - slider.getValue(), 4, slider.getValue());
                    } else {
                        g2d.fillRect(centerX, centerY, 4, -slider.getValue());
                    }

                }
            });

            JLabel freqText = new JLabel(element.getKey().toString());
            if (element.getKey() >= 1000) {
                double newValue = (element.getKey() / 1000.0);
                freqText.setText(Double.toString(newValue).replace(".0", "") + "k");
            }

            JButton resetButton = new JButton("R");
            resetButton.addActionListener(e -> slider.setValue(0));

            slider.setAlignmentX(Component.CENTER_ALIGNMENT);
            freqText.setAlignmentX(Component.CENTER_ALIGNMENT);
            resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            sliderPanel.add(slider);
            sliderPanel.add(freqText);
            sliderPanel.add(resetButton);
            add(sliderPanel);
        }
    }

    public void changeValue(Integer frequency, Double value) {
        int index = -1;
        for (int i = 0; i < values.size(); i++) {
            Pair<Integer, Double> pair = values.get(i);
            if (Objects.equals(pair.getKey(), frequency)) {
                index = i;
            }
        }

        values.set(index, new Pair<>(frequency, value));
        Main.alert.consoleMessage("Frequência: " + frequency + " Valor: " + value, '6');

        Main.fileManager.writer(
                "GraphicEQ",
                values.toString()
                        .replaceAll("[\\[\\]]", "")
                        .replace(" = ", " ")
                        .replace(",", ";"));
    }
}
