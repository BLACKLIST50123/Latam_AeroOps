package ElementosDiseño;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* ¿Para qué sirve?: Esta es una tarjeta reutilizable que muestra en resumen los datos de un vuelo pendiente de despacho (código, ruta y matrícula), junto con una pequeña etiqueta que indica que ese vuelo requiere el proceso de Peso y Balance (W&B)
   Clases que la utilizan: OficialOperaciones_GUI
   Índice de Métodos: TarjetaVueloPendiente, paintComponent */
public class TarjetaVueloPendiente extends JPanel {
    private String codVuelo;
    private String ruta;
    private String matricula;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara una tarjeta nueva con el código del vuelo, la ruta y la matrícula recibidos, y arma los textos que se muestran dentro de la tarjeta
    // Clases que lo usan: OficialOperaciones_GUI
    public TarjetaVueloPendiente(String codVuelo, String ruta, String matricula) {
        this.codVuelo = codVuelo;
        this.ruta = ruta;
        this.matricula = matricula;

        setOpaque(false); // Para que las esquinas redondeadas se vean bien
        setPreferredSize(new Dimension(300, 55)); // Alto fijo para la tarjeta
        setMaximumSize(new Dimension(600, 55)); // Evita que el GridLayout la estire deformándola
        setLayout(null); // Posicionamiento absoluto para clavar los textos

        // Título del Vuelo (Ej: LA800)
        JLabel lblVuelo = new JLabel(codVuelo);
        lblVuelo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblVuelo.setForeground(Color.WHITE);
        lblVuelo.setBounds(15, 0, 60, 55);
        add(lblVuelo);

        // Ruta y Matrícula (Ej: LIM → AQP • OB-2101)
        JLabel lblDetalle = new JLabel(ruta + "  •  " + matricula);
        lblDetalle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDetalle.setForeground(new Color(203, 213, 225)); // Gris claro
        lblDetalle.setBounds(80, 0, 200, 55);
        add(lblDetalle);
    }

    // ==========================================
    // MÉTODO PARA DIBUJAR LA TARJETA
    // ==========================================
    // Descripción: Dibuja a mano el fondo de la tarjeta con esquinas redondeadas, su borde sutil, y la pequeña píldora de color que dice 'Req. W&B'
    // Clases que lo usan: OficialOperaciones_GUI
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo de la tarjeta (Azul marino muy oscuro)
        g2.setColor(new Color(15, 23, 42)); 
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

        // Borde sutil
        g2.setColor(new Color(51, 65, 85)); 
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

        // ============================================
        // DIBUJO DE LA PÍLDORA "Req. W&B"
        // ============================================
        String textoPill = "Req. W&B";
        g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        int pillWidth = 75;
        int pillHeight = 26;
        int pillX = getWidth() - pillWidth - 15;
        int pillY = (getHeight() - pillHeight) / 2;

        // Fondo Pill (Marrón oscuro)
        g2.setColor(new Color(66, 32, 6)); 
        g2.fillRoundRect(pillX, pillY, pillWidth, pillHeight, 15, 15);

        // Texto Pill (Ámbar)
        g2.setColor(new Color(251, 191, 36)); 
        int textX = pillX + 9;
        int textY = pillY + 17;
        g2.drawString(textoPill, textX, textY);

        g2.dispose();
        super.paintComponent(g); // Pinta los JLabel encima del fondo
    }
}