package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AlertaPanel extends JPanel {
    private JTextField alertTitleField;
    private JTextField alertDescriptionField;
    private JButton saveButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTextArea alertDisplayArea;

    public AlertaPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Adiciona uma borda com espaçamento

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Adiciona espaçamentos entre os componentes
        inputPanel.setBorder(BorderFactory.createTitledBorder("Alertas e Lembretes"));
        inputPanel.add(new JLabel("Título do Alerta:"));
        alertTitleField = new JTextField();
        inputPanel.add(alertTitleField);

        inputPanel.add(new JLabel("Descrição do Alerta:"));
        alertDescriptionField = new JTextField();
        inputPanel.add(alertDescriptionField);

        saveButton = new JButton("Salvar");
        inputPanel.add(saveButton);

        editButton = new JButton("Editar");
        inputPanel.add(editButton);

        JButton deleteButton1 = deleteButton;
    }
}