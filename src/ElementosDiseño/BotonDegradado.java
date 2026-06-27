package ElementosDiseño;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class BotonDegradado extends JButton {

    public BotonDegradado() {
        // Configuraciones base para que no dibuje el botón feo por defecto
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        
        // Estilo del texto
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 12));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        // Esto suaviza los bordes para que no se vean pixelados
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Crear el degradado (De Naranja a Rojo/Magenta)
        GradientPaint gp = new GradientPaint(0, 0, new Color(255, 138, 0), getWidth(), 0, new Color(255, 0, 68));
        g2.setPaint(gp);
        
        // Dibujar el fondo redondeado
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

        super.paintComponent(g);
        g2.dispose();
    }
}