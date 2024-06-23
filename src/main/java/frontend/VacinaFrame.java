package frontend;

import javax.swing.*;
import java.awt.*;

public class VacinaFrame extends JFrame {
    public VacinaFrame() {
        setTitle("Cadastro de Vacinas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        VacinaPanel vacinaPanel = new VacinaPanel();
        add(vacinaPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VacinaFrame frame = new VacinaFrame();
            frame.setVisible(true);
        });
    }
}
