import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraDeMedias {
    private JPanel mainPanel;
    private JTextField inputNota;
    private JButton btnAddNota;
    private JButton btnCalcularMedia;
    private JTextArea notasArea;
    private JLabel resultadoMediaLabel;

    private final List<Double> notasList;

    public CalculadoraDeMedias() {
        notasList = new ArrayList<>();
        setupUI();

        btnAddNota.addActionListener((ActionEvent event) -> addNota());
        btnCalcularMedia.addActionListener((ActionEvent event) -> calculateMedia());
    }

    private void setupUI() {
        mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        inputNota = new JTextField(10);
        btnAddNota = new JButton("Adicionar");
        topPanel.add(new JLabel("Nota:"));
        topPanel.add(inputNota);
        topPanel.add(btnAddNota);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        notasArea = new JTextArea(10, 30);
        notasArea.setEditable(false);
        mainPanel.add(new JScrollPane(notasArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        btnCalcularMedia = new JButton("Calcular Média");
        resultadoMediaLabel = new JLabel("Média: - | Status: -", SwingConstants.CENTER);
        bottomPanel.add(btnCalcularMedia, BorderLayout.NORTH);
        bottomPanel.add(resultadoMediaLabel, BorderLayout.SOUTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addNota() {
        try {
            double nota = Double.parseDouble(inputNota.getText());
            if (nota < 0 || nota > 10) {
                showMessage("Insira uma nota válida entre 0 e 10.");
                return;
            }

            notasList.add(nota);
            notasArea.append(String.format("Nota adicionada: %.2f%n", nota));
            inputNota.setText("");
        } catch (NumberFormatException ex) {
            showMessage("Digite um número válido.");
        }
    }

    private void calculateMedia() {
        if (notasList.isEmpty()) {
            showMessage("Nenhuma nota foi registrada ainda.");
            return;
        }

        double media = notasList.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        String status = (media >= 7) ? "Aprovado" : "Reprovado";
        resultadoMediaLabel.setText(String.format("Média: %.2f | Status: %s", media, status));
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(mainPanel, message, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora de Média");
        CalculadoraDeMedias calculator = new CalculadoraDeMedias();
        frame.setContentPane(calculator.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
