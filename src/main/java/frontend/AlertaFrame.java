package frontend;

import javax.swing.*;
import java.awt.*;

public class AlertaFrame extends JFrame {
    public AlertaFrame() {
        setTitle("Alertas e Lembretes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        AlertaPanel alertaPanel = new AlertaPanel();
        add(alertaPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlertaFrame frame = new AlertaFrame();
            frame.setVisible(true);
        });
    }
}
