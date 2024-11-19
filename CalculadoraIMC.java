import javax.swing.*;
import java.awt.*;

public class CalculadoraIMC {
    private JPanel panelPrincipal;
    private JTextField campoPeso;
    private JTextField campoAltura;
    private JButton botaoCalcular;
    private JLabel labelResultado;

    public CalculadoraIMC() {
        configurarInterface();

        botaoCalcular.addActionListener(evento -> calcularIMC());
    }

    private void configurarInterface() {
        panelPrincipal = new JPanel(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridLayout(3, 2, 10, 10));
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Dados do Usuário"));

        painelEntrada.add(new JLabel("Peso (kg):"));
        campoPeso = new JTextField(10);
        painelEntrada.add(campoPeso);

        painelEntrada.add(new JLabel("Altura (m):"));
        campoAltura = new JTextField(10);
        painelEntrada.add(campoAltura);

        botaoCalcular = new JButton("Calcular");
        painelEntrada.add(botaoCalcular);

        panelPrincipal.add(painelEntrada, BorderLayout.NORTH);

        labelResultado = new JLabel("Preencha os campos acima e clique em Calcular", SwingConstants.CENTER);
        labelResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));
        panelPrincipal.add(labelResultado, BorderLayout.CENTER);
    }

    private void calcularIMC() {
        String textoPeso = campoPeso.getText().trim();
        String textoAltura = campoAltura.getText().trim();

        try {
            double peso = Double.parseDouble(textoPeso);
            double altura = Double.parseDouble(textoAltura);

            if (peso <= 0 || altura <= 0) {
                throw new IllegalArgumentException("Peso e altura precisam ser maiores que zero.");
            }

            double imc = peso / Math.pow(altura, 2);
            String classificacao = classificarIMC(imc);

            labelResultado.setText(String.format("IMC: %.1f - %s", imc, classificacao));
        } catch (NumberFormatException e) {
            exibirMensagem("Por favor, insira números válidos para peso e altura.");
        } catch (IllegalArgumentException e) {
            exibirMensagem(e.getMessage());
        }
    }

    private String classificarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc <= 24.9) {
            return "Peso normal";
        } else if (imc <= 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }

    private void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(panelPrincipal, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora de IMC");
        CalculadoraIMC calculadora = new CalculadoraIMC();
        frame.setContentPane(calculadora.panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
