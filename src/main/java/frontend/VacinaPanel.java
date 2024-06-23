package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VacinaPanel extends JPanel {
    private JTextField nomeVacinaField;
    private JComboBox<String> vacinaComboBox;
    private JButton cadastrarButton;
    private DefaultComboBoxModel<String> comboBoxModel;
    private List<String> vacinas;

    public VacinaPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Adiciona uma borda com espaçamento

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Cadastro de Vacinas", 0, 0, new Font("Arial", Font.BOLD, 16)));

        JLabel nameLabel = new JLabel("Nome da Vacina:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(nameLabel);
        nomeVacinaField = new JTextField();
        inputPanel.add(nomeVacinaField);

        JLabel vacinaLabel = new JLabel("Vacinas Cadastradas:");
        vacinaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(vacinaLabel);
        vacinaComboBox = new JComboBox<>();
        vacinaComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(vacinaComboBox);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 14));
        cadastrarButton.setBackground(new Color(70, 130, 180));
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        buttonPanel.add(cadastrarButton);

        add(buttonPanel, BorderLayout.CENTER);

        vacinas = new ArrayList<>();
        comboBoxModel = new DefaultComboBoxModel<>();
        vacinaComboBox.setModel(comboBoxModel);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeVacina = nomeVacinaField.getText();
                if (!nomeVacina.isEmpty()) {
                    vacinas.add(nomeVacina);
                    comboBoxModel.addElement(nomeVacina);
                    nomeVacinaField.setText("");
                } else {
                    JOptionPane.showMessageDialog(VacinaPanel.this, "O campo Nome da Vacina não pode estar vazio.");
                }
            }
        });
    }

    public String getVacinaSelecionada() {
        return (String) vacinaComboBox.getSelectedItem();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Teste VacinaPanel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);

            VacinaPanel panel = new VacinaPanel();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
