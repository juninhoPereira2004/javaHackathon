package frontend;

import javax.swing.*;
import java.awt.*;

public class UsuarioFrame extends JFrame {
    public UsuarioFrame() {
        setTitle("Cadastro de Usuários (Agentes de Saúde e Cuidadores)");
        setSize(400, 500);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        UsuarioPanel usuarioPanel = new UsuarioPanel();
        add(usuarioPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuarioFrame frame = new UsuarioFrame();
            frame.setVisible(true);
        });
    }
}
