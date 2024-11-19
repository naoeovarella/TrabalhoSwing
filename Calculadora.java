

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Calculadora {
        private JButton a0Button;
        private JButton a1Button;
        private JButton a2Button;
        private JButton a3Button;
        private JButton a4Button;
        private JButton a5Button;
        private JButton a6Button;
        private JButton a7Button;
        private JButton a8Button;
        private JButton a9Button;
        private JButton btnSomar;
        private JButton btnSubtrair;
        private JButton btnMultiplicar;
        private JButton btnDivisao;
        private JButton btnLimpar;
        private JButton btnIgual;
        private JTextField txtResultado;

        private double num1 = 0;
        private double num2 = 0;
        private String operador = "";

        public Calculadora() {
            JFrame frame = new JFrame("Calculadora");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 400);
            frame.setLayout(new BorderLayout());

            txtResultado = new JTextField();
            frame.add(txtResultado, BorderLayout.NORTH);
            txtResultado.setEditable(false);
            txtResultado.setHorizontalAlignment(SwingConstants.RIGHT);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 4));

            a1Button = new JButton("1");
            a2Button = new JButton("2");
            a3Button = new JButton("3");
            a4Button = new JButton("4");
            a5Button = new JButton("5");
            a6Button = new JButton("6");
            a7Button = new JButton("7");
            a8Button = new JButton("8");
            a9Button = new JButton("9");
            a0Button = new JButton("0");

            btnSomar = new JButton("+");
            btnSubtrair = new JButton("-");
            btnMultiplicar = new JButton("*");
            btnDivisao = new JButton("/");
            btnLimpar = new JButton("C");
            btnIgual = new JButton("=");

            panel.add(a7Button);
            panel.add(a8Button);
            panel.add(a9Button);
            panel.add(btnDivisao);

            panel.add(a4Button);
            panel.add(a5Button);
            panel.add(a6Button);
            panel.add(btnMultiplicar);

            panel.add(a1Button);
            panel.add(a2Button);
            panel.add(a3Button);
            panel.add(btnSubtrair);

            panel.add(a0Button);
            panel.add(btnLimpar);
            panel.add(btnIgual);
            panel.add(btnSomar);

            frame.add(panel, BorderLayout.CENTER);

            frame.setVisible(true);

            a0Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "0"));
            a1Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "1"));
            a2Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "2"));
            a3Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "3"));
            a4Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "4"));
            a5Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "5"));
            a6Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "6"));
            a7Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "7"));
            a8Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "8"));
            a9Button.addActionListener(e -> txtResultado.setText(txtResultado.getText() + "9"));

            btnSomar.addActionListener(e -> {
                num1 = Double.parseDouble(txtResultado.getText());
                operador = "+";
                txtResultado.setText("");
            });

            btnSubtrair.addActionListener(e -> {
                num1 = Double.parseDouble(txtResultado.getText());
                operador = "-";
                txtResultado.setText("");
            });

            btnMultiplicar.addActionListener(e -> {
                num1 = Double.parseDouble(txtResultado.getText());
                operador = "*";
                txtResultado.setText("");
            });

            btnDivisao.addActionListener(e -> {
                num1 = Double.parseDouble(txtResultado.getText());
                operador = "/";
                txtResultado.setText("");
            });

            btnLimpar.addActionListener(e -> txtResultado.setText(""));

            btnIgual.addActionListener(e -> {
                num2 = Double.parseDouble(txtResultado.getText());
                double resultado = 0;

                switch (operador) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            resultado = num1 / num2;
                        } else {
                            JOptionPane.showMessageDialog(frame, "Não é possível dividir por zero.");
                            return;
                        }
                        break;
                }

                txtResultado.setText(String.valueOf(resultado));
                num1 = resultado;
            });
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(Calculadora::new);
        }
    }
