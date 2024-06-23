package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioPanel extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JFormattedTextField phoneField;
    private JComboBox<String> roleComboBox;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private int selectedIndex = -1;

    public UsuarioPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Adiciona uma borda com espaçamento

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Cadastro de Usuários", 0, 0, new Font("Arial", Font.BOLD, 16)));

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(nameLabel);
        nameField = new JTextField(15);
        inputPanel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(emailLabel);
        emailField = new JTextField(15);
        inputPanel.add(emailField);

        JLabel phoneLabel = new JLabel("Telefone:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(phoneLabel);
        try {
            MaskFormatter phoneMask = new MaskFormatter("(##) #####-####");
            phoneMask.setPlaceholderCharacter('_');
            phoneField = new JFormattedTextField(phoneMask);
        } catch (ParseException e) {
            e.printStackTrace();
            phoneField = new JFormattedTextField();
        }
        inputPanel.add(phoneField);

        JLabel roleLabel = new JLabel("Função:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(roleLabel);
        roleComboBox = new JComboBox<>(new String[]{"Agente de Saúde", "Cuidador"});
        roleComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(roleComboBox);

        add(inputPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        userList.setFont(new Font("Arial", Font.PLAIN, 14));
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && userList.getSelectedIndex() != -1) {
                selectedIndex = userList.getSelectedIndex();
                String[] userDetails = listModel.getElementAt(selectedIndex).split(" \\| ");
                nameField.setText(userDetails[0]);
                emailField.setText(userDetails[1]);
                phoneField.setText(userDetails[2]);
                roleComboBox.setSelectedItem(userDetails[3]);
                addButton.setEnabled(false);
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });

        JScrollPane listScrollPane = new JScrollPane(userList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Usuários Registrados", 0, 0, new Font("Arial", Font.BOLD, 16)));
        add(listScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        addButton = new JButton("Adicionar");
        styleButton(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String role = (String) roleComboBox.getSelectedItem();

                try {
                    validateEmail(email);
                    validatePhone(phone);

                    if (!name.isEmpty()) {
                        listModel.addElement(name + " | " + email + " | " + phone + " | " + role);
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(UsuarioPanel.this, "O campo Nome não pode estar vazio.");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(UsuarioPanel.this, ex.getMessage());
                }
            }
        });
        buttonPanel.add(addButton);

        editButton = new JButton("Editar");
        styleButton(editButton);
        editButton.setEnabled(false);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1) {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String phone = phoneField.getText();
                    String role = (String) roleComboBox.getSelectedItem();

                    try {
                        validateEmail(email);
                        validatePhone(phone);

                        if (!name.isEmpty()) {
                            listModel.setElementAt(name + " | " + email + " | " + phone + " | " + role, selectedIndex);
                            clearFields();
                            selectedIndex = -1;
                            addButton.setEnabled(true);
                            editButton.setEnabled(false);
                            deleteButton.setEnabled(false);
                        } else {
                            JOptionPane.showMessageDialog(UsuarioPanel.this, "O campo Nome não pode estar vazio.");
                        }
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(UsuarioPanel.this, ex.getMessage());
                    }
                }
            }
        });
        buttonPanel.add(editButton);

        deleteButton = new JButton("Excluir");
        styleButton(deleteButton);
        deleteButton.setEnabled(false);
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
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setValue(null);
        roleComboBox.setSelectedIndex(0);
        userList.clearSelection();
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

    private void validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Por favor, insira um email válido.");
        }
    }

    private void validatePhone(String phone) {
        String regex = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Por favor, insira um telefone válido no formato (XX) XXXXX-XXXX.");
        }
    }
}
