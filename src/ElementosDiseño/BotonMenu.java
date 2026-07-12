package ElementosDiseño;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* ¿Para qué sirve?: Este es un botón reutilizable que se usa en el menú lateral de las pantallas principales. Cambia de color cuando el mouse pasa por encima y se marca en rojo cuando el módulo correspondiente está activo
   Clases que la utilizan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI
   Índice de Métodos: BotonMenu, mouseEntered, mouseExited, setTexto, setActivo, isActivo, paintComponent */
public class BotonMenu extends JPanel {
    private boolean activo = false;
    private final JLabel lblTexto;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara el botón de menú con su texto inicial y sus efectos de mouse: cuando el cursor entra se ilumina, y cuando sale vuelve a su color gris normal
    // Clases que lo usan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI
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
            // ==========================================
            // MÉTODO PARA CUANDO EL MOUSE ENTRA AL BOTÓN
            // ==========================================
            // Descripción: Si el botón no está activo, ilumina el texto en blanco mientras el mouse está encima
            // Clases que lo usan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!activo) lblTexto.setForeground(Color.WHITE); // Se ilumina un poco
            }
            // ==========================================
            // MÉTODO PARA CUANDO EL MOUSE SALE DEL BOTÓN
            // ==========================================
            // Descripción: Si el botón no está activo, devuelve el texto a su color gris normal cuando el mouse ya no está encima
            // Clases que lo usan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI
            @Override
            public void mouseExited(MouseEvent e) {
                if (!activo) lblTexto.setForeground(new Color(203, 213, 225)); // Vuelve a gris
            }
        });
    }

    // Método para cambiar el texto desde NetBeans
    // ==========================================
    // MÉTODO PARA CAMBIAR EL TEXTO DEL BOTÓN
    // ==========================================
    // Descripción: Cambia el texto que se muestra en el botón de menú
    // Clases que lo usan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setTexto(String txt) {
        lblTexto.setText(txt);
    }

    // Método para encender/apagar el botón (Ponerlo rojo)
    // ==========================================
    // MÉTODO PARA MARCAR EL BOTÓN COMO ACTIVO O INACTIVO
    // ==========================================
    // Descripción: Enciende o apaga el estado activo del botón. Si queda activo, el texto se pone rojo; si no, vuelve al gris normal. Al final vuelve a dibujar el botón para que se note el cambio
    // Clases que lo usan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI
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
    // ==========================================
    // MÉTODO PARA CONSULTAR SI EL BOTÓN ESTÁ ACTIVO
    // ==========================================
    // Descripción: Entrega verdadero si el botón está actualmente marcado como activo, o falso si no lo está
    // Clases que lo usan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public boolean isActivo() {
        return this.activo;
    }
    
    // Aquí dibujamos el fondo curvo y la línea roja a mano
    // ==========================================
    // MÉTODO PARA DIBUJAR EL BOTÓN DE MENÚ
    // ==========================================
    // Descripción: Dibuja a mano el fondo del botón: si está activo, pinta un fondo rojizo con una línea roja a la izquierda; si no, deja el fondo transparente
    // Clases que lo usan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI
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