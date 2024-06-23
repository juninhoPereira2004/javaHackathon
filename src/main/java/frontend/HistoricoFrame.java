package frontend;

import javax.swing.*;
import java.awt.*;

public class HistoricoFrame extends JFrame {
    public HistoricoFrame() {
        setTitle("Registro e Visualização do Histórico de Saúde do Idoso");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        HistoricoPanel historicoPanel = new HistoricoPanel();
        add(historicoPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HistoricoFrame frame = new HistoricoFrame();
            frame.setVisible(true);
        });
    }
}
