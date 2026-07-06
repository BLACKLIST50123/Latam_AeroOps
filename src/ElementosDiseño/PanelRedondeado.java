package ElementosDiseño;

import javax.swing.*;
import java.awt.*;

public class PanelRedondeado extends JPanel {
    private int radioEsquina = 14; // Controla qué tan redondo es el borde (14px es el estándar web)

    public PanelRedondeado() {
        setOpaque(false); // CRÍTICO: Evita que Swing pinte el fondo rectangular original debajo
    }

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