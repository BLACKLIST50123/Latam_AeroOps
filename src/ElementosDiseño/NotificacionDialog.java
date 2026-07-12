package ElementosDiseño;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

/* ¿Para qué sirve?: Esta clase reemplaza las ventanas emergentes estándar de Java (JOptionPane) por ventanas propias con el mismo estilo oscuro del resto de la aplicación. Sirve para mostrar mensajes de éxito, error, advertencia, información, y también para pedir una confirmación al usuario antes de hacer algo importante
   Clases que la utilizan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
   Índice de Métodos: exito, error, advertencia, info, confirmar, colorFondoBadge, colorAcento, icono, construirYMostrar, crearBoton, paintComponent, mouseEntered, mouseExited, mousePressed, mouseReleased */
public class NotificacionDialog {

    public enum Tipo { EXITO, ERROR, ADVERTENCIA, INFO }

    // ==========================================
    // MÉTODO PARA MOSTRAR UN MENSAJE DE ÉXITO
    // ==========================================
    // Descripción: Muestra una ventana emergente de color verde indicando que una operación se completó correctamente. Existen dos versiones: una con título por defecto ('Operación exitosa') y otra que permite escribir un título propio
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static void exito(Component parent, String mensaje) { exito(parent, mensaje, "Operación exitosa"); }
    // ==========================================
    // MÉTODO PARA MOSTRAR UN MENSAJE DE ÉXITO
    // ==========================================
    // Descripción: Muestra una ventana emergente de color verde indicando que una operación se completó correctamente. Existen dos versiones: una con título por defecto ('Operación exitosa') y otra que permite escribir un título propio
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static void exito(Component parent, String mensaje, String titulo) {
        construirYMostrar(parent, mensaje, titulo, Tipo.EXITO, false);
    }

    // ==========================================
    // MÉTODO PARA MOSTRAR UN MENSAJE DE ERROR
    // ==========================================
    // Descripción: Muestra una ventana emergente de color rojo indicando que algo salió mal. Existen dos versiones: una con título por defecto ('Error') y otra que permite escribir un título propio
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static void error(Component parent, String mensaje) { error(parent, mensaje, "Error"); }
    // ==========================================
    // MÉTODO PARA MOSTRAR UN MENSAJE DE ERROR
    // ==========================================
    // Descripción: Muestra una ventana emergente de color rojo indicando que algo salió mal. Existen dos versiones: una con título por defecto ('Error') y otra que permite escribir un título propio
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static void error(Component parent, String mensaje, String titulo) {
        construirYMostrar(parent, mensaje, titulo, Tipo.ERROR, false);
    }

    // ==========================================
    // MÉTODO PARA MOSTRAR UN MENSAJE DE ADVERTENCIA
    // ==========================================
    // Descripción: Muestra una ventana emergente de color ámbar avisando de algo que el usuario debe tener en cuenta. Existen dos versiones: una con título por defecto ('Aviso') y otra que permite escribir un título propio
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static void advertencia(Component parent, String mensaje) { advertencia(parent, mensaje, "Aviso"); }
    // ==========================================
    // MÉTODO PARA MOSTRAR UN MENSAJE DE ADVERTENCIA
    // ==========================================
    // Descripción: Muestra una ventana emergente de color ámbar avisando de algo que el usuario debe tener en cuenta. Existen dos versiones: una con título por defecto ('Aviso') y otra que permite escribir un título propio
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static void advertencia(Component parent, String mensaje, String titulo) {
        construirYMostrar(parent, mensaje, titulo, Tipo.ADVERTENCIA, false);
    }

    // ==========================================
    // MÉTODO PARA MOSTRAR UN MENSAJE INFORMATIVO
    // ==========================================
    // Descripción: Muestra una ventana emergente de color cyan con información general para el usuario. Existen dos versiones: una con título por defecto ('Información') y otra que permite escribir un título propio
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static void info(Component parent, String mensaje) { info(parent, mensaje, "Información"); }
    // ==========================================
    // MÉTODO PARA MOSTRAR UN MENSAJE INFORMATIVO
    // ==========================================
    // Descripción: Muestra una ventana emergente de color cyan con información general para el usuario. Existen dos versiones: una con título por defecto ('Información') y otra que permite escribir un título propio
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static void info(Component parent, String mensaje, String titulo) {
        construirYMostrar(parent, mensaje, titulo, Tipo.INFO, false);
    }

    // ==========================================
    // MÉTODO PARA PEDIR UNA CONFIRMACIÓN
    // ==========================================
    // Descripción: Muestra una ventana emergente con dos botones (Cancelar y Confirmar) y espera a que el usuario elija. Entrega verdadero si el usuario presionó Confirmar, o falso si canceló
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    public static boolean confirmar(Component parent, String mensaje, String titulo) {
        return construirYMostrar(parent, mensaje, titulo, Tipo.ADVERTENCIA, true);
    }

    // ==========================================
    // MÉTODO PARA ELEGIR EL COLOR DE FONDO DEL ÍCONO
    // ==========================================
    // Descripción: Entrega el color de fondo que le corresponde al ícono de la ventana, según el tipo de aviso (éxito, error, advertencia o información)
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    private static Color colorFondoBadge(Tipo tipo) {
        switch (tipo) {
            case EXITO: return new Color(5, 46, 22);      // Verde bosque
            case ERROR: return new Color(76, 5, 25);       // Guinda oscuro
            case ADVERTENCIA: return new Color(66, 32, 6); // Marrón quemado
            default: return new Color(19, 43, 58);         // Azul-cyan oscuro
        }
    }

    // ==========================================
    // MÉTODO PARA ELEGIR EL COLOR DE ACENTO
    // ==========================================
    // Descripción: Entrega el color principal que le corresponde a la ventana (la barra superior y el ícono), según el tipo de aviso
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    private static Color colorAcento(Tipo tipo) {
        switch (tipo) {
            case EXITO: return new Color(34, 197, 94);      // Verde esmeralda 74, 222, 128
            case ERROR: return new Color(219, 29, 72);     // Rosa/rojo neón 251, 113, 133
            case ADVERTENCIA: return new Color(245, 158, 11);// Ámbar 251, 191, 36
            default: return new Color(34, 211, 238);         // Cyan
        }
    }

    // ==========================================
    // MÉTODO PARA ELEGIR EL SÍMBOLO DEL ÍCONO
    // ==========================================
    // Descripción: Entrega el símbolo de texto que se muestra como ícono (un check, una equis, un signo de alerta o una letra i), según el tipo de aviso
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    private static String icono(Tipo tipo) {
        switch (tipo) {
            case EXITO: return "\u2714"; // Símbolo de verificación (✔)
            case ERROR: return "\u2716"; // Aspa de multiplicación/error (✖)
            case ADVERTENCIA: return "\u26A0"; // Símbolo de alerta (⚠)
            default: return "i";
        }
    }

    // ==========================================
    // MÉTODO PARA ARMAR Y MOSTRAR LA VENTANA
    // ==========================================
    // Descripción: Arma toda la ventana emergente desde cero: la barra de color, el ícono, el título, el mensaje y los botones, y la deja visible en el centro de la pantalla hasta que el usuario la cierre. Si es una confirmación, entrega verdadero o falso según el botón que haya presionado el usuario
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    private static boolean construirYMostrar(Component parent, String mensaje, String titulo, Tipo tipo, boolean esConfirmacion) {
        Window owner = parent != null ? SwingUtilities.getWindowAncestor(parent) : null;
        JDialog dialog = new JDialog(owner, titulo, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85), 1));

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(new Color(15, 23, 42)); // Slate-900, igual que el resto de la app
        
        // Barra de acento superior (marca visualmente el tipo de aviso)
        JPanel barraAcento = new JPanel();
        barraAcento.setBackground(colorAcento(tipo));
        barraAcento.setPreferredSize(new Dimension(10, 4));
        raiz.add(barraAcento, BorderLayout.NORTH);

        // Cuerpo: icono + título + mensaje
        JPanel cuerpo = new JPanel();
        cuerpo.setOpaque(false);
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setBorder(BorderFactory.createEmptyBorder(20, 24, 6, 24));

        JPanel filaTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        filaTitulo.setOpaque(false);
        filaTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblIcono = new JLabel(icono(tipo));
        lblIcono.setVerticalAlignment(SwingConstants.CENTER); // <-- Añade esto para centrarlo verticalmente
        lblIcono.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblIcono.setForeground(colorAcento(tipo));
        lblIcono.setOpaque(true);
        lblIcono.setBackground(colorFondoBadge(tipo));
        lblIcono.setBorder(new LineBorder(colorAcento(tipo), 2, true)); // Borde del fondo del icono
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setPreferredSize(new Dimension(34, 34));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setForeground(Color.WHITE);

        filaTitulo.add(lblIcono);
        filaTitulo.add(lblTitulo);

        JTextArea txtMensaje = new JTextArea(mensaje);
        txtMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMensaje.setForeground(new Color(148, 163, 184)); //203, 213, 225
        txtMensaje.setBackground(new Color(15, 23, 42));
        txtMensaje.setEditable(false);
        txtMensaje.setFocusable(false);
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setBorder(BorderFactory.createEmptyBorder(14, 4, 0, 4));
        txtMensaje.setAlignmentX(Component.LEFT_ALIGNMENT);
        //txtMensaje.setColumns(30);
        // ESTA ES LA CLAVE: Forzar un ancho preferido, pero dejar el alto dinámico
        // 310 es un buen ancho para que la ventana quede proporcional.
        txtMensaje.setSize(new Dimension(310, Short.MAX_VALUE)); 
        txtMensaje.setPreferredSize(new Dimension(310, txtMensaje.getPreferredSize().height));
        txtMensaje.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8)); // Más padding
        txtMensaje.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cuerpo.add(filaTitulo);
        cuerpo.add(txtMensaje);
        raiz.add(cuerpo, BorderLayout.CENTER);

        // Botones
        final boolean[] resultado = {false};
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlBotones.setOpaque(false);
        pnlBotones.setBorder(BorderFactory.createEmptyBorder(10, 24, 18, 24));

        if (esConfirmacion) {
            JButton btnCancelar = crearBoton("Cancelar", new Color(30, 41, 59), new Color(203, 213, 225));
            btnCancelar.addActionListener(e -> { resultado[0] = false; dialog.dispose(); });

            JButton btnConfirmar = crearBoton("Confirmar", colorAcento(tipo), new Color(15, 23, 42));
            btnConfirmar.addActionListener(e -> { resultado[0] = true; dialog.dispose(); });

            pnlBotones.add(btnCancelar);
            pnlBotones.add(btnConfirmar);
            dialog.getRootPane().setDefaultButton(btnConfirmar);
        } else {
            JButton btnOk = crearBoton("Aceptar", colorAcento(tipo), new Color(15, 23, 42));
            btnOk.addActionListener(e -> dialog.dispose());
            pnlBotones.add(btnOk);
            dialog.getRootPane().setDefaultButton(btnOk);
        }
        raiz.add(pnlBotones, BorderLayout.SOUTH);

        dialog.setContentPane(raiz);
        dialog.pack();
        int ancho = Math.max(dialog.getWidth(), 380);
        dialog.setSize(ancho, dialog.getHeight());
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true); // Se bloquea aquí hasta que el usuario cierre el diálogo

        return resultado[0];
    }

    // ==========================================
    // MÉTODO PARA CREAR UN BOTÓN CON ESTILO PROPIO
    // ==========================================
    // Descripción: Arma un botón redondeado con el texto, el color de fondo y el color de texto indicados, y le agrega los efectos de mouse (se aclara al pasar por encima, se oscurece al presionarlo)
    // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
    private static JButton crearBoton(String texto, Color fondoNormal, Color colorTexto) {
        JButton btn = new JButton(texto) {
            // ==========================================
            // MÉTODO PAINTCOMPONENT
            // ==========================================
            // Descripción: Método propio de la clase NotificacionDialog
            // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Pintamos el fondo redondeado
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // 10px de redondeo

                super.paintComponent(g); // Pinta el texto
                g2.dispose();
            }
        };

        // Configuraciones base para quitar el estilo tosco de Windows/Java
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(colorTexto);
        btn.setBackground(fondoNormal);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); // Apagamos el fondo por defecto para usar nuestro paintComponent
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25)); // Padding interno (Arriba, Izq, Abajo, Der)

        // Calculamos colores dinámicos para el Hover y Presionado
        Color fondoHover = fondoNormal.brighter();
        Color fondoPresionado = fondoNormal.darker();

        // Eventos del Mouse para la interactividad
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            // ==========================================
            // MÉTODO PARA CUANDO EL MOUSE ENTRA AL BOTÓN
            // ==========================================
            // Descripción: Aclara el color de fondo del botón mientras el mouse está encima
            // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(fondoHover);
            }

            // ==========================================
            // MÉTODO PARA CUANDO EL MOUSE SALE DEL BOTÓN
            // ==========================================
            // Descripción: Devuelve el botón a su color de fondo normal cuando el mouse ya no está encima
            // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(fondoNormal);
            }

            // ==========================================
            // MÉTODO PARA CUANDO SE PRESIONA EL BOTÓN
            // ==========================================
            // Descripción: Oscurece el color de fondo del botón mientras se mantiene presionado
            // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn.setBackground(fondoPresionado);
            }

            // ==========================================
            // MÉTODO PARA CUANDO SE SUELTA EL BOTÓN
            // ==========================================
            // Descripción: Devuelve el botón al color de fondo aclarado (hover), ya que normalmente el mouse todavía sigue encima al soltar
            // Clases que lo usan: Login_GUI, OficialOperaciones_GUI, TecnicoMantenimiento_GUI, BaseDeDatos (a través de otras clases de arranque)
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn.setBackground(fondoHover); // Al soltar, vuelve al hover si el mouse sigue ahí
            }
        });

        return btn;
    }
}
