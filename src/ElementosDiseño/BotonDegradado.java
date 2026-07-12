package ElementosDiseño;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/* ¿Para qué sirve?: Este es un botón reutilizable de la interfaz gráfica que se dibuja con un degradado de color (de naranja a rojo) y las esquinas redondeadas, en vez del botón cuadrado normal de Java
   Clases que la utilizan: Interfaces_GUI (pantallas de Login, Oficial de Operaciones y Técnico de Mantenimiento)
   Índice de Métodos: BotonDegradado, paintComponent */
public class BotonDegradado extends JButton {

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un botón nuevo quitándole el estilo por defecto de Java (relleno, borde y foco), y le pone el texto en blanco, la fuente en negrita y el cursor de mano al pasar por encima
    // Clases que lo usan: Interfaces_GUI (pantallas de Login, Oficial de Operaciones y Técnico de Mantenimiento)
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

    // ==========================================
    // MÉTODO PARA DIBUJAR EL BOTÓN CON DEGRADADO
    // ==========================================
    // Descripción: Dibuja a mano el fondo del botón con un degradado de color que va de naranja a rojo, con las esquinas redondeadas y los bordes suavizados
    // Clases que lo usan: Interfaces_GUI (pantallas de Login, Oficial de Operaciones y Técnico de Mantenimiento)
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