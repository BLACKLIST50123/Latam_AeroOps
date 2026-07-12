package ElementosDiseño;

import javax.swing.*;
import java.awt.*;

/* ¿Para qué sirve?: Este es un panel reutilizable de la interfaz gráfica que dibuja su fondo con las esquinas redondeadas, en vez del rectángulo normal que trae Java por defecto. Se usa como base visual para varias pantallas del sistema
   Clases que la utilizan: Interfaces_GUI (pantallas de Login, Oficial de Operaciones y Técnico de Mantenimiento)
   Índice de Métodos: PanelRedondeado, paintComponent */
public class PanelRedondeado extends JPanel {
    private int radioEsquina = 14; // Controla qué tan redondo es el borde (14px es el estándar web)

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un panel nuevo dejándolo transparente, para que se pueda dibujar encima el fondo redondeado personalizado
    // Clases que lo usan: Interfaces_GUI (pantallas de Login, Oficial de Operaciones y Técnico de Mantenimiento)
    public PanelRedondeado() {
        setOpaque(false); // CRÍTICO: Evita que Swing pinte el fondo rectangular original debajo
    }

    // ==========================================
    // MÉTODO PARA DIBUJAR EL PANEL REDONDEADO
    // ==========================================
    // Descripción: Dibuja a mano el fondo del panel con las esquinas curvas y con el color actual, activando el suavizado para que las curvas no se vean pixeladas
    // Clases que lo usan: Interfaces_GUI (pantallas de Login, Oficial de Operaciones y Técnico de Mantenimiento)
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Activamos Anti-aliasing para que las curvas se vean súper suaves y no pixeladas
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Pintamos el fondo personalizado con el color actual del componente
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radioEsquina, radioEsquina);
        
        g2.dispose();
        super.paintComponent(g);
    }
}