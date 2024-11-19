import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AgendaDiaria {
    private JPanel painelPrincipal;
    private JTextField campoDescricao;
    private JSpinner seletorDataHora;
    private JButton botaoAdicionar;
    private JButton botaoRemover;
    private JTable tabelaCompromissos;
    private DefaultTableModel modeloTabela;

    public AgendaDiaria() {
        seletorDataHora.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorDataHora = new JSpinner.DateEditor(seletorDataHora, "dd/MM/yyyy HH:mm");
        seletorDataHora.setEditor(editorDataHora);

        String[] colunasTabela = {"Data/Hora", "Descrição"};
        modeloTabela = new DefaultTableModel(colunasTabela, 0);
        tabelaCompromissos.setModel(modeloTabela);

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = campoDescricao.getText();
                Date dataHora = (Date) seletorDataHora.getValue();

                if (descricao.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Por favor, insira uma descrição.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                modeloTabela.addRow(new Object[]{dataHora, descricao});
                campoDescricao.setText("");
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = tabelaCompromissos.getSelectedRow();

                if (indiceSelecionado < 0) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Selecione um item para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                modeloTabela.removeRow(indiceSelecionado);
            }
        });
    }

    public static void main(String[] args) {
        JFrame janela = new JFrame("Diário Pessoal");
        janela.setContentPane(new AgendaDiaria().painelPrincipal);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setVisible(true);
    }
}
