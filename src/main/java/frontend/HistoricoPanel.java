package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HistoricoPanel extends JPanel {
    private JTextArea healthRecordsArea;

    public HistoricoPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Adiciona uma borda com espaçamento

        healthRecordsArea = new JTextArea(10, 30);
        healthRecordsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(healthRecordsArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Histórico de Saúde"));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addHealthRecordExtended(String elderName, String vaccine, String date, String agent) {
        healthRecordsArea.append("Nome do Idoso: " + elderName + "\nVacina Aplicada: " + vaccine + "\nData de Aplicação: " + date + "\nAgente de Saúde: " + agent + "\n\n");
    }

    // Método para compatibilidade com o código existente
    public void addHealthRecord(String elderName, String vaccine, String date) {
        // Chamada para o método extendido com um valor padrão para agente
        addHealthRecordExtended(elderName, vaccine, date, "Agente de Saúde não especificado");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Teste HistoricoPanel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            HistoricoPanel panel = new HistoricoPanel();
            frame.add(panel);

            // Exemplo de uso:
            panel.addHealthRecordExtended("José", "Vacina ABC", "22/06/2024", "Maria Silva");

            frame.setVisible(true);
        });
    }
}
