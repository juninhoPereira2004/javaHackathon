import frontend.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        initializeFrame();
        addComponents();
    }

    private void initializeFrame() {
        setTitle("Sistema de Gestão de Saúde");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Impede redimensionamento da janela
    }

    private void addComponents() {
        JPanel buttonPanel = createButtonPanel();
        buttonPanel.setBackground(Color.WHITE); // Define cor de fundo para branco
        add(buttonPanel, BorderLayout.CENTER);

        // Adicione outras funcionalidades e painéis aqui conforme necessário
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Borda com espaçamento

        // Criação e adição dos botões
        buttonPanel.add(createStyledButton("Agente de Saúde", UsuarioFrame.class));
        buttonPanel.add(createStyledButton("Histórico de Saúde", HistoricoFrame.class));
        buttonPanel.add(createStyledButton("Agenda", VisitasFrame.class));
        buttonPanel.add(createStyledButton("Alertas e Lembretes", AlertaFrame.class));
        buttonPanel.add(createStyledButton("Cadastro de Vacinas", VacinaFrame.class));

        return buttonPanel;
    }

    private JButton createStyledButton(String text, Class<? extends JFrame> frameClass) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setFocusPainted(false); // Remove a borda de foco
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espaçamento interno
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão horizontalmente no painel
        button.addActionListener(e -> openFrame(frameClass));
        return button;
    }

    private void openFrame(Class<? extends JFrame> frameClass) {
        try {
            JFrame frame = frameClass.getDeclaredConstructor().newInstance();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao abrir a janela: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
