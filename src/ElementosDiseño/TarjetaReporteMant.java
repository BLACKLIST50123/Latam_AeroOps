package ElementosDiseño;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TarjetaReporteMant extends JPanel {
    private int idLogbook;
    private String matricula;
    private String modelo;
    private String prioridad;
    private String observaciones;
    private Interfaces.TecnicoMantenimiento_GUI guiPrincipal;
    private boolean mouseEncima = false;

    public TarjetaReporteMant(int idLogbook, String matricula, String modelo, String prioridad, String observaciones, Interfaces.TecnicoMantenimiento_GUI gui) {
        this.idLogbook = idLogbook;
        this.matricula = matricula;
        this.modelo = modelo;
        this.prioridad = prioridad;
        this.observaciones = observaciones;
        this.guiPrincipal = gui;

        // --- CONFIGURACIÓN DE CONTENEDOR WEB ---
        setOpaque(false); // Para que permita renderizar el fondo redondeado customizado
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14)); // Padding interno
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Impedimos que colapse el layout vertical en el BoxLayout padre
        Dimension tamañoFijo = new Dimension(245, 85);
        setPreferredSize(tamañoFijo);
        setMinimumSize(tamañoFijo);
        setMaximumSize(new Dimension(Short.MAX_VALUE, 85));

        // --- SUB-PANEL SUPERIOR: TÍTULO Y BADGE ---
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setOpaque(false);

        JLabel lblTitulo = new JLabel(matricula);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);

        // Píldora/Badge de prioridad estilo Tailwind
        JLabel lblBadge = new JLabel(" " + prioridad.toUpperCase() + " ");
        lblBadge.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblBadge.setOpaque(true);
        lblBadge.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Estilos ultra estilizados según prioridad
        switch (prioridad.toUpperCase()) {
            case "ALTA":
                lblBadge.setBackground(new Color(76, 5, 25)); // Fondo guinda oscuro (#4c0519)
                lblBadge.setForeground(new Color(251, 113, 133)); // Texto rosa neón (#f77185)
                break;
            case "MEDIA":
                lblBadge.setBackground(new Color(66, 32, 6)); // Fondo marrón oscuro (#422006)
                lblBadge.setForeground(new Color(251, 191, 36)); // Texto ámbar (#fbbf24)
                break;
            default: // BAJA o cualquiera otra
                lblBadge.setBackground(new Color(5, 46, 22)); // Fondo verde bosque (#052e16)
                lblBadge.setForeground(new Color(74, 222, 128)); // Texto verde claro (#4ade80)
                break;
        }
        // Bordes redondeados sutiles para el mini-badge
        lblBadge.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));

        pnlTop.add(lblTitulo, BorderLayout.WEST);
        pnlTop.add(lblBadge, BorderLayout.EAST);

        // --- SUB-PANEL INFERIOR: SUBTÍTULO Y TEXTO DE FALLA ---
        JPanel pnlBottom = new JPanel(new GridLayout(2, 1, 0, 2));
        pnlBottom.setOpaque(false);

        JLabel lblSub = new JLabel("Aeronave: " + modelo);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(148, 163, 184)); // Gris Slate-400

        // Recorte dinámico de texto para la vista previa de las observaciones
        String vistaPreviaFalla = observaciones;
        if (vistaPreviaFalla.length() > 28) {
            vistaPreviaFalla = vistaPreviaFalla.substring(0, 25) + "...";
        }
        JLabel lblFalla = new JLabel(vistaPreviaFalla);
        lblFalla.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblFalla.setForeground(new Color(203, 213, 225)); // Gris Slate-300

        pnlBottom.add(lblSub);
        pnlBottom.add(lblFalla);

        // Ensamblado en el contenedor principal
        add(pnlTop, BorderLayout.NORTH);
        add(pnlBottom, BorderLayout.SOUTH);

        // --- ESCUCHADOR DE EVENTOS (INTERACCIÓN DINÁMICA) ---
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guiPrincipal.mostrarDetalleReporte(idLogbook, matricula, modelo, prioridad, observaciones);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseEncima = true;
                repaint(); // Forzar el cambio de color inmediato
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseEncima = false;
                repaint(); // Volver al color base
            }
        });
    }

    // --- RENDERIZADO DEL FONDO REDONDEADO ESTILO WEB ---
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        // Activamos suavizado de pixeles para evitar bordes toscos de Java antiguo
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Color de fondo dinámico (Efecto Hover suave)
        if (mouseEncima) {
            g2.setColor(new Color(30, 41, 59)); // Slate-800 (#1e293b) al pasar el mouse
        } else {
            g2.setColor(new Color(15, 23, 42)); // Slate-900 (#0f172a) color por defecto
        }
        
        // Rellenar rectángulo redondeado de la tarjeta (radio de esquina de 12px)
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
        
        // Línea del borde ultrafina (Gris Slate-700, #334155)
        g2.setColor(new Color(51, 65, 85));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
        
        g2.dispose();
        super.paintComponent(g);
    }
}