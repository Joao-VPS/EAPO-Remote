import javax.swing.*;
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
            new AlertWindow("Erro", "O valor passado não tem nenhum parâmetro válido", true, false);
            return;
        }

        String[] params = data.substring(firstDigitIndex).trim().split(";");
        values.clear();

        for (String element : params) {
            String[] keyValue = element.trim().split(" ");
            System.out.println(Arrays.toString(keyValue));

            if (keyValue.length >= 2) {
                Integer key = Integer.parseInt(keyValue[0]);
                Double value = Double.parseDouble(keyValue[1]);

                if (value > 20.0) value = 20.0;
                if (value < -20.0) value = -20.0;

                values.add(new Pair<>(key, value));
            }
        }

        System.out.println("Array completo: " + values);
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

            JButton resetButton = new JButton("R");
            resetButton.addActionListener(e -> slider.setValue(0));

            sliderPanel.add(slider);
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
        System.out.println("Frequência: " + frequency + " Valor: " + value);

        Main.changeValue(index, (int) (value * 10));
    }
}
