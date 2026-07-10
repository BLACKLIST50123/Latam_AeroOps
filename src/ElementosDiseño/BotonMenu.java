package ElementosDiseño;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BotonMenu extends JPanel {
    private boolean activo = false;
    private final JLabel lblTexto;

    public BotonMenu() {
        setOpaque(false); // Vital para que las esquinas redondeadas se vean bien
        setLayout(new BorderLayout());
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblTexto = new JLabel("Módulo");
        lblTexto.setForeground(new Color(203, 213, 225)); // Texto gris
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTexto.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10)); // Margen interno
        add(lblTexto, BorderLayout.CENTER);

        // Efectos del mouse (Hover)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!activo) lblTexto.setForeground(Color.WHITE); // Se ilumina un poco
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!activo) lblTexto.setForeground(new Color(203, 213, 225)); // Vuelve a gris
            }
        });
    }

    // Método para cambiar el texto desde NetBeans
    public void setTexto(String txt) {
        lblTexto.setText(txt);
    }

    // Método para encender/apagar el botón (Ponerlo rojo)
    public void setActivo(boolean estado) {
        this.activo = estado;
        if (activo) {
            lblTexto.setForeground(new Color(225, 29, 72)); // Letra roja
        } else {
            lblTexto.setForeground(new Color(203, 213, 225)); // Letra gris
        }
        repaint(); // Vuelve a dibujar el fondo
    }
    
    // Método para saber si el botón ya está seleccionado/activo
    public boolean isActivo() {
        return this.activo;
    }
    
    // Aquí dibujamos el fondo curvo y la línea roja a mano
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (activo) {
            // Fondo rojizo curvo
            g2.setColor(new Color(45, 10, 30));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            
            // Línea roja a la izquierda (Grosor de 4 píxeles)
            g2.setColor(new Color(225, 29, 72));
            g2.fillRoundRect(0, 0, 4, getHeight(), 4, 4);
        } else {
            // Fondo transparente/invisible si no está activo
            g2.setColor(new Color(15, 23, 42, 0));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        g2.dispose();
        super.paintComponent(g);
    }
}