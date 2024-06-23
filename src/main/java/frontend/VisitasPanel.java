package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class VisitasPanel extends JPanel {
    private JTextField elderNameField;
    private JFormattedTextField dateField;
    private JFormattedTextField timeField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JList<String> scheduleList;
    private DefaultListModel<String> listModel;
    private int selectedIndex = -1;

    public VisitasPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Adiciona uma borda com espaçamento
        setBackground(Color.WHITE); // Define cor de fundo para branco

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Agendamento", 0, 0, new Font("Arial", Font.BOLD, 16)));
        inputPanel.setBackground(Color.WHITE); // Define cor de fundo para branco

        inputPanel.add(new JLabel("Nome do Idoso:"));
        elderNameField = new JTextField();
        styleTextField(elderNameField);
        inputPanel.add(elderNameField);

        inputPanel.add(new JLabel("Data (DD/MM/AAAA):"));
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            dateField = new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            e.printStackTrace();
            dateField = new JFormattedTextField();
        }
        styleTextField(dateField);
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Hora (HH:MM):"));
        try {
            MaskFormatter timeMask = new MaskFormatter("##:##");
            timeMask.setPlaceholderCharacter('_');
            timeField = new JFormattedTextField(timeMask);
        } catch (ParseException e) {
            e.printStackTrace();
            timeField = new JFormattedTextField();
        }
        styleTextField(timeField);
        inputPanel.add(timeField);

        add(inputPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        scheduleList = new JList<>(listModel);
        scheduleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scheduleList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane listScrollPane = new JScrollPane(scheduleList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Agendamentos", 0, 0, new Font("Arial", Font.BOLD, 16)));
        listScrollPane.setBackground(Color.WHITE); // Define cor de fundo para branco
        add(listScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE); // Define cor de fundo para branco

        addButton = new JButton("Adicionar");
        styleButton(addButton);
        buttonPanel.add(addButton);

        editButton = new JButton("Editar");
        styleButton(editButton);
        editButton.setEnabled(false);
        buttonPanel.add(editButton);

        deleteButton = new JButton("Excluir");
        styleButton(deleteButton);
        deleteButton.setEnabled(false);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Configurações dos botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String elderName = elderNameField.getText();
                String date = dateField.getText();
                String time = timeField.getText();

                try {
                    validateDateTime(date, time);
                    listModel.addElement(elderName + " | " + date + " | " + time);
                    clearFields();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(VisitasPanel.this,
                            "Por favor, insira uma data no formato DD/MM/AAAA e uma hora no formato HH:MM válidos.");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1) {
                    String elderName = elderNameField.getText();
                    String date = dateField.getText();
                    String time = timeField.getText();

                    try {
                        validateDateTime(date, time);
                        listModel.setElementAt(elderName + " | " + date + " | " + time, selectedIndex);
                        clearFields();
                        selectedIndex = -1;
                        addButton.setEnabled(true);
                        editButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(VisitasPanel.this,
                                "Por favor, insira uma data no formato DD/MM/AAAA e uma hora no formato HH:MM válidos.");
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                    clearFields();
                    selectedIndex = -1;
                    addButton.setEnabled(true);
                    editButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            }
        });

        // Listener para seleção na lista
        scheduleList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && scheduleList.getSelectedIndex() != -1) {
                selectedIndex = scheduleList.getSelectedIndex();
                String[] scheduleDetails = listModel.getElementAt(selectedIndex).split(" \\| ");
                elderNameField.setText(scheduleDetails[0]);
                dateField.setText(scheduleDetails[1]);
                timeField.setText(scheduleDetails[2]);
                addButton.setEnabled(false);
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
    }

    private void clearFields() {
        elderNameField.setText("");
        dateField.setValue(null);
        timeField.setValue(null);
        scheduleList.clearSelection();
    }

    private void validateDateTime(String date, String time) throws ParseException {
        if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new ParseException("Formato de data inválido.", 0);
        }

        if (!time.matches("\\d{2}:\\d{2}")) {
            throw new ParseException("Formato de hora inválido.", 0);
        }

        // Validar outras regras necessárias, como data no passado/futuro, etc.
        // Implemente conforme suas necessidades específicas.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Teste VisitSchedulePanel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);

            VisitasPanel panel = new VisitasPanel();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
