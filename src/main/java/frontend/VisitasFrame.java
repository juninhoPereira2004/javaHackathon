package frontend;

import javax.swing.*;
import java.awt.*;

public class VisitasFrame extends JFrame {
    public VisitasFrame() {
        setTitle("Agendamento de Visitas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        VisitasPanel visitasPanel = new VisitasPanel();
        add(visitasPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisitasFrame frame = new VisitasFrame();
            frame.setVisible(true);
        });
    }
}
