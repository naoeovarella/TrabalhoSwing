import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroDeContatos {
    private JPanel painelPrincipal;
    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JButton botaoAdicionar;
    private JButton botaoRemover;
    private JList<String> listaContatos;
    private DefaultListModel<String> listaModel;

    public CadastroDeContatos() {
        listaModel = new DefaultListModel<>();
        configurarInterface();

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarNovoContato();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirContato();
            }
        });
    }

    private void configurarInterface() {
        painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 8, 8));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Formulário de Cadastro"));

        painelFormulario.add(new JLabel("Nome:"));
        campoNome = new JTextField(20);
        painelFormulario.add(campoNome);

        painelFormulario.add(new JLabel("Telefone:"));
        campoTelefone = new JTextField(20);
        painelFormulario.add(campoTelefone);

        painelFormulario.add(new JLabel("E-mail:"));
        campoEmail = new JTextField(20);
        painelFormulario.add(campoEmail);

        botaoAdicionar = new JButton("Adicionar Contato");
        painelFormulario.add(botaoAdicionar);

        botaoRemover = new JButton("Remover Contato");
        painelFormulario.add(botaoRemover);

        painelPrincipal.add(painelFormulario, BorderLayout.NORTH);

        listaContatos = new JList<>(listaModel);
        JScrollPane scrollPaneContatos = new JScrollPane(listaContatos);
        scrollPaneContatos.setBorder(BorderFactory.createTitledBorder("Contatos Cadastrados"));
        painelPrincipal.add(scrollPaneContatos, BorderLayout.CENTER);
    }

    private void adicionarNovoContato() {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String email = campoEmail.getText().trim();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            exibirMensagem("Por favor, preencha todos os campos.");
            return;
        }

        String novoContato = String.format("Nome: %s | Telefone: %s | E-mail: %s", nome, telefone, email);
        listaModel.addElement(novoContato);

        campoNome.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
    }

    private void excluirContato() {
        int indiceSelecionado = listaContatos.getSelectedIndex();
        if (indiceSelecionado == -1) {
            exibirMensagem("Selecione um contato para remover.");
            return;
        }
        listaModel.remove(indiceSelecionado);
    }

    private void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(painelPrincipal, mensagem, "Atenção", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        JFrame janela = new JFrame("Cadastro de Contatos");
        CadastroDeContatos sistema = new CadastroDeContatos();
        janela.setContentPane(sistema.painelPrincipal);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setVisible(true);
    }
}
