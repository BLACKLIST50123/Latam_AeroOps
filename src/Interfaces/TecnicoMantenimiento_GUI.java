package Interfaces;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class TecnicoMantenimiento_GUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TecnicoMantenimiento_GUI.class.getName());


    public TecnicoMantenimiento_GUI() {
        initComponents();
//## CAMBIO DE DISEÑO BOTONES MENU ##
        // Le ponemos el texto a cada botón
        btnReportesMantenimiento.setTexto("Reportes de Mantenimiento ");
        btnHistorialLogbook.setTexto("Historial LogBook");
        // Hacemos que el primero nazca encendido 
        btnReportesMantenimiento.setActivo(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Esto expande a FullScreen al iniciar

//## CAMBIO DE DISEÑO JCOMBOXS ##
        // Aplicamos el cambio de diseño para cada JCombox JComboBox
        aplicarTemaOscuro(cbxFiltroMatrícula);
        aplicarTemaOscuro(cbxFiltroPrioridad);
        aplicarTemaOscuro(cbxFiltroEstado);
//## CAMBIO DE DISEÑO JFORMATFIELD FECHA ##
        txtFiltroFecha.setBackground(new java.awt.Color(15, 23, 42));
        txtFiltroFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFiltroFecha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding
        ));
        
        
//## CAMBIO DE DISEÑO SCROLLS ##
        aplicarScrollModerno(ScrollReportesMantenimiento);
        aplicarScrollModerno(ScrollTablaHistorialLogBook);  
        aplicarScrollModerno(ScrollTxtObservacionesLogbook);
        aplicarScrollModerno(scrollTxtRegistroAcciones);
        aplicarScrollModerno(ScrollListaReportes);
        
//## CAMBIO DE DISEÑO TABLAS ##
        aplicarTemaTabla(TblHistorialLogBook);

//AJUSTE DINAMICO TABLAS
        ajustarAlturaDinamica(TblHistorialLogBook, ScrollTablaHistorialLogBook);

// BORRA ESTO CUANDO TENGAS LA BASE DE DATOS, SON DATOS DE PRUEBA PARA EL DISEÑO
        cargarDatosPruebaLogbook();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        bordeSuperior = new javax.swing.JPanel();
        fondoBtnSalir = new javax.swing.JPanel();
        txtBtnSalir = new javax.swing.JLabel();
        pnlFondoLogoBorde = new javax.swing.JPanel();
        logoBorde = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fondoBarraLateral = new javax.swing.JPanel();
        lblModulos = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblRolSistema = new javax.swing.JLabel();
        pnlFondoPerfil = new javax.swing.JPanel();
        fondoBtnCerrarSesion = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JLabel();
        pnlFondoSistemaOnline = new javax.swing.JPanel();
        lblSistemaOnline = new javax.swing.JLabel();
        btnReportesMantenimiento = new ElementosDiseño.BotonMenu();
        btnHistorialLogbook = new ElementosDiseño.BotonMenu();
        pnlContenedorPrincipal = new javax.swing.JPanel();
        pnlReportesMantenimiento = new javax.swing.JPanel();
        pnlReportesMantenimientoCuerpo = new javax.swing.JPanel();
        panelEncabezado3 = new javax.swing.JPanel();
        lblReportesMantenimiento = new javax.swing.JLabel();
        lblSistemaControlTierra = new javax.swing.JLabel();
        ScrollReportesMantenimiento = new javax.swing.JScrollPane();
        pnlContenidoReportesMant = new javax.swing.JPanel();
        pnlListaReportes = new javax.swing.JPanel();
        ScrollListaReportes = new javax.swing.JScrollPane();
        pnlContenedorTarjetasReportes = new javax.swing.JPanel();
        pnlTarjetasLleno = new javax.swing.JPanel();
        pnlTarjetasVacio = new javax.swing.JPanel();
        lblSinAeronaves = new javax.swing.JLabel();
        lblOperacionesMantenimiento = new javax.swing.JLabel();
        lblEstadoTecnicoControlLiberaciones = new javax.swing.JLabel();
        lblOrdenesActivas = new javax.swing.JLabel();
        pnlContenedorDetalleReporte = new javax.swing.JPanel();
        pnlDetalleVacio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlDetalleSeleccionado = new javax.swing.JPanel();
        pnlDatoAvion = new javax.swing.JPanel();
        lblMatriculaAeronave = new javax.swing.JLabel();
        lblModeloAeronave = new javax.swing.JLabel();
        pnlPrioridadMant = new javax.swing.JPanel();
        lblPrioridadMant = new javax.swing.JLabel();
        lblObservacioneslogbook = new javax.swing.JLabel();
        ScrollTxtObservacionesLogbook = new javax.swing.JScrollPane();
        txtAreaObservacionesLogbook = new javax.swing.JTextArea();
        lblRegistroAcciones = new javax.swing.JLabel();
        scrollTxtRegistroAcciones = new javax.swing.JScrollPane();
        txtAreaRegistroAcciones = new javax.swing.JTextArea();
        lblFirmaTecnica = new javax.swing.JLabel();
        txtFieldFirmaTecnica = new javax.swing.JTextField();
        fondoBtnAprobarPlan = new javax.swing.JPanel();
        btnAprobarPlan = new javax.swing.JLabel();
        pnlFondoContadorAOG = new javax.swing.JPanel();
        lblContadorAOG = new javax.swing.JLabel();
        pnlHistorialLogBook = new javax.swing.JPanel();
        pnlHistorialCuerpo = new javax.swing.JPanel();
        panelEncabezado5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlFiltrosHistorial = new javax.swing.JPanel();
        lblHistorialVuelo = new javax.swing.JLabel();
        lblFiltroFecha = new javax.swing.JLabel();
        lblFiltroMatricula = new javax.swing.JLabel();
        cbxFiltroMatrícula = new javax.swing.JComboBox<>();
        cbxFiltroPrioridad = new javax.swing.JComboBox<>();
        lblFiltroPrioridad = new javax.swing.JLabel();
        fondoBtnLimpiarFiltros = new javax.swing.JPanel();
        btnLimpiarFiltros = new javax.swing.JLabel();
        txtFiltroFecha = new javax.swing.JFormattedTextField();
        cbxFiltroEstado = new javax.swing.JComboBox<>();
        lblFiltroEstado = new javax.swing.JLabel();
        pnlTablaDinamicaHistorial = new javax.swing.JPanel();
        ScrollTablaHistorialLogBook = new javax.swing.JScrollPane();
        TblHistorialLogBook = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bordeSuperior.setBackground(new java.awt.Color(15, 23, 42));

        fondoBtnSalir.setBackground(new java.awt.Color(15, 23, 42));

        txtBtnSalir.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        txtBtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        txtBtnSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBtnSalir.setText("X");
        txtBtnSalir.setToolTipText("");
        txtBtnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtBtnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBtnSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtBtnSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtBtnSalirMouseExited(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnSalirLayout = new javax.swing.GroupLayout(fondoBtnSalir);
        fondoBtnSalir.setLayout(fondoBtnSalirLayout);
        fondoBtnSalirLayout.setHorizontalGroup(
            fondoBtnSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBtnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        fondoBtnSalirLayout.setVerticalGroup(
            fondoBtnSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnlFondoLogoBorde.setBackground(new java.awt.Color(15, 23, 42));

        logoBorde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoBorde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_imagenes/LogoBorde.png"))); // NOI18N

        javax.swing.GroupLayout pnlFondoLogoBordeLayout = new javax.swing.GroupLayout(pnlFondoLogoBorde);
        pnlFondoLogoBorde.setLayout(pnlFondoLogoBordeLayout);
        pnlFondoLogoBordeLayout.setHorizontalGroup(
            pnlFondoLogoBordeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoBorde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );
        pnlFondoLogoBordeLayout.setVerticalGroup(
            pnlFondoLogoBordeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoBorde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LATAM AERO OPS");

        javax.swing.GroupLayout bordeSuperiorLayout = new javax.swing.GroupLayout(bordeSuperior);
        bordeSuperior.setLayout(bordeSuperiorLayout);
        bordeSuperiorLayout.setHorizontalGroup(
            bordeSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bordeSuperiorLayout.createSequentialGroup()
                .addComponent(pnlFondoLogoBorde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 996, Short.MAX_VALUE)
                .addComponent(fondoBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        bordeSuperiorLayout.setVerticalGroup(
            bordeSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bordeSuperiorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fondoBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlFondoLogoBorde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bordeSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(bordeSuperior, java.awt.BorderLayout.PAGE_START);

        fondoBarraLateral.setBackground(new java.awt.Color(15, 23, 43));
        fondoBarraLateral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        lblModulos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblModulos.setForeground(new java.awt.Color(144, 163, 184));
        lblModulos.setText("MÓDULOS");

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_imagenes/LogoInterfaces.png"))); // NOI18N
        logo.setText("jLabel2");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUsuario.setText("Nombre");

        lblRolSistema.setText("Rol en Sistema ");

        javax.swing.GroupLayout pnlFondoPerfilLayout = new javax.swing.GroupLayout(pnlFondoPerfil);
        pnlFondoPerfil.setLayout(pnlFondoPerfilLayout);
        pnlFondoPerfilLayout.setHorizontalGroup(
            pnlFondoPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );
        pnlFondoPerfilLayout.setVerticalGroup(
            pnlFondoPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnCerrarSesionLayout = new javax.swing.GroupLayout(fondoBtnCerrarSesion);
        fondoBtnCerrarSesion.setLayout(fondoBtnCerrarSesionLayout);
        fondoBtnCerrarSesionLayout.setHorizontalGroup(
            fondoBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        fondoBtnCerrarSesionLayout.setVerticalGroup(
            fondoBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnlFondoSistemaOnline.setBackground(new java.awt.Color(5, 46, 22));
        pnlFondoSistemaOnline.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 46, 22), 1, true));

        lblSistemaOnline.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSistemaOnline.setForeground(new java.awt.Color(34, 197, 94));
        lblSistemaOnline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSistemaOnline.setText("SISTEMA ONLINE");
        lblSistemaOnline.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 46, 22), 1, true));

        javax.swing.GroupLayout pnlFondoSistemaOnlineLayout = new javax.swing.GroupLayout(pnlFondoSistemaOnline);
        pnlFondoSistemaOnline.setLayout(pnlFondoSistemaOnlineLayout);
        pnlFondoSistemaOnlineLayout.setHorizontalGroup(
            pnlFondoSistemaOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSistemaOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlFondoSistemaOnlineLayout.setVerticalGroup(
            pnlFondoSistemaOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSistemaOnline, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        btnReportesMantenimiento.setTexto("Reportes de Mantenimiento\n");
        btnReportesMantenimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesMantenimientoMouseClicked(evt);
            }
        });

        btnHistorialLogbook.setTexto("Historial LogBook");
        btnHistorialLogbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHistorialLogbookMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBarraLateralLayout = new javax.swing.GroupLayout(fondoBarraLateral);
        fondoBarraLateral.setLayout(fondoBarraLateralLayout);
        fondoBarraLateralLayout.setHorizontalGroup(
            fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoBarraLateralLayout.createSequentialGroup()
                .addGroup(fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fondoBarraLateralLayout.createSequentialGroup()
                        .addGroup(fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(pnlFondoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRolSistema)
                                    .addComponent(lblUsuario))
                                .addGap(34, 34, 34)
                                .addComponent(fondoBtnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(pnlFondoSistemaOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReportesMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fondoBarraLateralLayout.createSequentialGroup()
                                .addComponent(lblModulos)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnHistorialLogbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fondoBarraLateralLayout.setVerticalGroup(
            fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlFondoSistemaOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblModulos)
                .addGap(18, 18, 18)
                .addComponent(btnReportesMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHistorialLogbook, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 651, Short.MAX_VALUE)
                .addGroup(fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRolSistema))
                    .addComponent(pnlFondoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fondoBtnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        getContentPane().add(fondoBarraLateral, java.awt.BorderLayout.LINE_START);

        pnlContenedorPrincipal.setLayout(new java.awt.CardLayout());

        pnlReportesMantenimiento.setBackground(new java.awt.Color(153, 51, 0));

        pnlReportesMantenimientoCuerpo.setBackground(new java.awt.Color(15, 23, 42));

        panelEncabezado3.setBackground(new java.awt.Color(15, 23, 42));
        panelEncabezado3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        lblReportesMantenimiento.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblReportesMantenimiento.setForeground(new java.awt.Color(255, 255, 255));
        lblReportesMantenimiento.setText("Reportes de Mantenimiento");

        lblSistemaControlTierra.setForeground(new java.awt.Color(148, 163, 184));
        lblSistemaControlTierra.setText("Sistema de control en tierra ");

        javax.swing.GroupLayout panelEncabezado3Layout = new javax.swing.GroupLayout(panelEncabezado3);
        panelEncabezado3.setLayout(panelEncabezado3Layout);
        panelEncabezado3Layout.setHorizontalGroup(
            panelEncabezado3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelEncabezado3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSistemaControlTierra)
                    .addComponent(lblReportesMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEncabezado3Layout.setVerticalGroup(
            panelEncabezado3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblReportesMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSistemaControlTierra)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        ScrollReportesMantenimiento.setBorder(null);
        ScrollReportesMantenimiento.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlContenidoReportesMant.setBackground(new java.awt.Color(15, 23, 42));

        ScrollListaReportes.setBackground(new java.awt.Color(15, 23, 42));
        ScrollListaReportes.setBorder(null);

        pnlContenedorTarjetasReportes.setBackground(new java.awt.Color(15, 23, 42));
        pnlContenedorTarjetasReportes.setLayout(new java.awt.CardLayout());

        pnlTarjetasLleno.setBackground(new java.awt.Color(15, 23, 42));
        pnlTarjetasLleno.setLayout(new java.awt.GridLayout(1, 0));
        pnlContenedorTarjetasReportes.add(pnlTarjetasLleno, "card3");

        pnlTarjetasVacio.setBackground(new java.awt.Color(15, 23, 42));

        lblSinAeronaves.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSinAeronaves.setForeground(new java.awt.Color(148, 163, 184));
        lblSinAeronaves.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSinAeronaves.setText("Sin aeronaves en mantenimiento ");

        javax.swing.GroupLayout pnlTarjetasVacioLayout = new javax.swing.GroupLayout(pnlTarjetasVacio);
        pnlTarjetasVacio.setLayout(pnlTarjetasVacioLayout);
        pnlTarjetasVacioLayout.setHorizontalGroup(
            pnlTarjetasVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetasVacioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSinAeronaves, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTarjetasVacioLayout.setVerticalGroup(
            pnlTarjetasVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarjetasVacioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblSinAeronaves)
                .addContainerGap(449, Short.MAX_VALUE))
        );

        pnlContenedorTarjetasReportes.add(pnlTarjetasVacio, "card2");

        ScrollListaReportes.setViewportView(pnlContenedorTarjetasReportes);

        javax.swing.GroupLayout pnlListaReportesLayout = new javax.swing.GroupLayout(pnlListaReportes);
        pnlListaReportes.setLayout(pnlListaReportesLayout);
        pnlListaReportesLayout.setHorizontalGroup(
            pnlListaReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollListaReportes)
        );
        pnlListaReportesLayout.setVerticalGroup(
            pnlListaReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollListaReportes)
        );

        lblOperacionesMantenimiento.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblOperacionesMantenimiento.setForeground(new java.awt.Color(255, 255, 255));
        lblOperacionesMantenimiento.setText("Operaciones de Mantenimiento ");

        lblEstadoTecnicoControlLiberaciones.setForeground(new java.awt.Color(148, 163, 184));
        lblEstadoTecnicoControlLiberaciones.setText("Estado técnico de flota y control de liberaciones ");

        lblOrdenesActivas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblOrdenesActivas.setForeground(new java.awt.Color(255, 255, 255));
        lblOrdenesActivas.setText("Órdenes Activas ");

        pnlContenedorDetalleReporte.setBackground(new java.awt.Color(30, 41, 59));
        pnlContenedorDetalleReporte.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        pnlContenedorDetalleReporte.setLayout(new java.awt.CardLayout());

        pnlDetalleVacio.setBackground(new java.awt.Color(30, 41, 59));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Detalle no disponible hasta que se seleccione un reporte");

        javax.swing.GroupLayout pnlDetalleVacioLayout = new javax.swing.GroupLayout(pnlDetalleVacio);
        pnlDetalleVacio.setLayout(pnlDetalleVacioLayout);
        pnlDetalleVacioLayout.setHorizontalGroup(
            pnlDetalleVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleVacioLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pnlDetalleVacioLayout.setVerticalGroup(
            pnlDetalleVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleVacioLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(393, Short.MAX_VALUE))
        );

        pnlContenedorDetalleReporte.add(pnlDetalleVacio, "pnlDetalleVacio");

        pnlDetalleSeleccionado.setBackground(new java.awt.Color(30, 41, 59));

        pnlDatoAvion.setBackground(new java.awt.Color(51, 65, 85));
        pnlDatoAvion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblMatriculaAeronave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMatriculaAeronave.setForeground(new java.awt.Color(255, 255, 255));
        lblMatriculaAeronave.setText("OB-2055 ");

        lblModeloAeronave.setForeground(new java.awt.Color(255, 255, 255));
        lblModeloAeronave.setText("A320neo ");

        pnlPrioridadMant.setBackground(new java.awt.Color(51, 51, 51));

        lblPrioridadMant.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPrioridadMant.setForeground(new java.awt.Color(255, 255, 255));
        lblPrioridadMant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrioridadMant.setText("MEDIO");

        javax.swing.GroupLayout pnlPrioridadMantLayout = new javax.swing.GroupLayout(pnlPrioridadMant);
        pnlPrioridadMant.setLayout(pnlPrioridadMantLayout);
        pnlPrioridadMantLayout.setHorizontalGroup(
            pnlPrioridadMantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrioridadMantLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblPrioridadMant, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlPrioridadMantLayout.setVerticalGroup(
            pnlPrioridadMantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrioridadMant, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlDatoAvionLayout = new javax.swing.GroupLayout(pnlDatoAvion);
        pnlDatoAvion.setLayout(pnlDatoAvionLayout);
        pnlDatoAvionLayout.setHorizontalGroup(
            pnlDatoAvionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatoAvionLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlDatoAvionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMatriculaAeronave, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModeloAeronave, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPrioridadMant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        pnlDatoAvionLayout.setVerticalGroup(
            pnlDatoAvionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatoAvionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblMatriculaAeronave, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModeloAeronave)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatoAvionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPrioridadMant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblObservacioneslogbook.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblObservacioneslogbook.setForeground(new java.awt.Color(255, 255, 255));
        lblObservacioneslogbook.setText("OBSERVACIONES DEL LOGBOOK");

        txtAreaObservacionesLogbook.setEditable(false);
        txtAreaObservacionesLogbook.setBackground(new java.awt.Color(76, 5, 25));
        txtAreaObservacionesLogbook.setColumns(20);
        txtAreaObservacionesLogbook.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAreaObservacionesLogbook.setForeground(new java.awt.Color(251, 113, 133));
        txtAreaObservacionesLogbook.setLineWrap(true);
        txtAreaObservacionesLogbook.setRows(5);
        txtAreaObservacionesLogbook.setText("Fallo en el motor A9G");
        txtAreaObservacionesLogbook.setWrapStyleWord(true);
        txtAreaObservacionesLogbook.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ScrollTxtObservacionesLogbook.setViewportView(txtAreaObservacionesLogbook);

        lblRegistroAcciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRegistroAcciones.setForeground(new java.awt.Color(255, 255, 255));
        lblRegistroAcciones.setText("REGISTRO DE ACCION DE MANTENIMIENTO");

        txtAreaRegistroAcciones.setBackground(new java.awt.Color(15, 23, 42));
        txtAreaRegistroAcciones.setColumns(20);
        txtAreaRegistroAcciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAreaRegistroAcciones.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaRegistroAcciones.setLineWrap(true);
        txtAreaRegistroAcciones.setRows(5);
        txtAreaRegistroAcciones.setWrapStyleWord(true);
        scrollTxtRegistroAcciones.setViewportView(txtAreaRegistroAcciones);

        lblFirmaTecnica.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFirmaTecnica.setForeground(new java.awt.Color(255, 255, 255));
        lblFirmaTecnica.setText("FIRMA TÉCNICA");

        txtFieldFirmaTecnica.setBackground(new java.awt.Color(15, 23, 42));
        txtFieldFirmaTecnica.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldFirmaTecnica.setToolTipText("");

        fondoBtnAprobarPlan.setBackground(new java.awt.Color(22, 101, 52));

        btnAprobarPlan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAprobarPlan.setForeground(new java.awt.Color(255, 255, 255));
        btnAprobarPlan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAprobarPlan.setText("Firmar liberación a servicio (APTO)");
        btnAprobarPlan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout fondoBtnAprobarPlanLayout = new javax.swing.GroupLayout(fondoBtnAprobarPlan);
        fondoBtnAprobarPlan.setLayout(fondoBtnAprobarPlanLayout);
        fondoBtnAprobarPlanLayout.setHorizontalGroup(
            fondoBtnAprobarPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAprobarPlan, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        fondoBtnAprobarPlanLayout.setVerticalGroup(
            fondoBtnAprobarPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAprobarPlan, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlDetalleSeleccionadoLayout = new javax.swing.GroupLayout(pnlDetalleSeleccionado);
        pnlDetalleSeleccionado.setLayout(pnlDetalleSeleccionadoLayout);
        pnlDetalleSeleccionadoLayout.setHorizontalGroup(
            pnlDetalleSeleccionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleSeleccionadoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlDetalleSeleccionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDetalleSeleccionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblFirmaTecnica, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollTxtRegistroAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                        .addComponent(lblObservacioneslogbook, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRegistroAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ScrollTxtObservacionesLogbook)
                        .addComponent(pnlDatoAvion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFieldFirmaTecnica))
                    .addComponent(fondoBtnAprobarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlDetalleSeleccionadoLayout.setVerticalGroup(
            pnlDetalleSeleccionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleSeleccionadoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pnlDatoAvion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblObservacioneslogbook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollTxtObservacionesLogbook, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRegistroAcciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTxtRegistroAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFirmaTecnica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFieldFirmaTecnica, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(fondoBtnAprobarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlContenedorDetalleReporte.add(pnlDetalleSeleccionado, "pnlDetalleSeleccionado");

        pnlFondoContadorAOG.setBackground(new java.awt.Color(76, 5, 25));
        pnlFondoContadorAOG.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(76, 5, 25), 1, true));

        lblContadorAOG.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblContadorAOG.setForeground(new java.awt.Color(251, 111, 130));
        lblContadorAOG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorAOG.setText("0 AOG  |  Mant");

        javax.swing.GroupLayout pnlFondoContadorAOGLayout = new javax.swing.GroupLayout(pnlFondoContadorAOG);
        pnlFondoContadorAOG.setLayout(pnlFondoContadorAOGLayout);
        pnlFondoContadorAOGLayout.setHorizontalGroup(
            pnlFondoContadorAOGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblContadorAOG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        );
        pnlFondoContadorAOGLayout.setVerticalGroup(
            pnlFondoContadorAOGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblContadorAOG, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlContenidoReportesMantLayout = new javax.swing.GroupLayout(pnlContenidoReportesMant);
        pnlContenidoReportesMant.setLayout(pnlContenidoReportesMantLayout);
        pnlContenidoReportesMantLayout.setHorizontalGroup(
            pnlContenidoReportesMantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoReportesMantLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlContenidoReportesMantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOperacionesMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoTecnicoControlLiberaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContenidoReportesMantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContenidoReportesMantLayout.createSequentialGroup()
                            .addComponent(lblOrdenesActivas, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlFondoContadorAOG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContenidoReportesMantLayout.createSequentialGroup()
                            .addComponent(pnlListaReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pnlContenedorDetalleReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlContenidoReportesMantLayout.setVerticalGroup(
            pnlContenidoReportesMantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoReportesMantLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblOperacionesMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoTecnicoControlLiberaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoReportesMantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlFondoContadorAOG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOrdenesActivas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContenidoReportesMantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlContenedorDetalleReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlListaReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(347, Short.MAX_VALUE))
        );

        ScrollReportesMantenimiento.setViewportView(pnlContenidoReportesMant);

        javax.swing.GroupLayout pnlReportesMantenimientoCuerpoLayout = new javax.swing.GroupLayout(pnlReportesMantenimientoCuerpo);
        pnlReportesMantenimientoCuerpo.setLayout(pnlReportesMantenimientoCuerpoLayout);
        pnlReportesMantenimientoCuerpoLayout.setHorizontalGroup(
            pnlReportesMantenimientoCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScrollReportesMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        pnlReportesMantenimientoCuerpoLayout.setVerticalGroup(
            pnlReportesMantenimientoCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReportesMantenimientoCuerpoLayout.createSequentialGroup()
                .addComponent(panelEncabezado3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollReportesMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlReportesMantenimientoLayout = new javax.swing.GroupLayout(pnlReportesMantenimiento);
        pnlReportesMantenimiento.setLayout(pnlReportesMantenimientoLayout);
        pnlReportesMantenimientoLayout.setHorizontalGroup(
            pnlReportesMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlReportesMantenimientoCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlReportesMantenimientoLayout.setVerticalGroup(
            pnlReportesMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlReportesMantenimientoCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlContenedorPrincipal.add(pnlReportesMantenimiento, "pnlReportesMantenimiento");

        pnlHistorialLogBook.setBackground(new java.awt.Color(15, 23, 42));

        pnlHistorialCuerpo.setBackground(new java.awt.Color(15, 23, 42));

        panelEncabezado5.setBackground(new java.awt.Color(15, 23, 42));
        panelEncabezado5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Historial LogBook");

        jLabel9.setForeground(new java.awt.Color(148, 163, 184));
        jLabel9.setText("Sistema de control en tierra ");

        javax.swing.GroupLayout panelEncabezado5Layout = new javax.swing.GroupLayout(panelEncabezado5);
        panelEncabezado5.setLayout(panelEncabezado5Layout);
        panelEncabezado5Layout.setHorizontalGroup(
            panelEncabezado5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelEncabezado5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addContainerGap(935, Short.MAX_VALUE))
        );
        panelEncabezado5Layout.setVerticalGroup(
            panelEncabezado5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlFiltrosHistorial.setBackground(new java.awt.Color(30, 41, 59));
        pnlFiltrosHistorial.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblHistorialVuelo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHistorialVuelo.setForeground(new java.awt.Color(203, 213, 225));
        lblHistorialVuelo.setText("HISTORIAL DE REPARACIONES Y LOGBOOK");

        lblFiltroFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroFecha.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroFecha.setText("FECHA");

        lblFiltroMatricula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroMatricula.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroMatricula.setText("MATRÍCULA");

        cbxFiltroMatrícula.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroMatrícula.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroMatrícula.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroMatrícula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "OB-2101" }));
        cbxFiltroMatrícula.setToolTipText("");
        cbxFiltroMatrícula.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroMatrícula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroMatrícula.addActionListener(this::cbxFiltroMatrículaActionPerformed);

        cbxFiltroPrioridad.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroPrioridad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroPrioridad.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "COMPLETADO" }));
        cbxFiltroPrioridad.setToolTipText("");
        cbxFiltroPrioridad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroPrioridad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroPrioridad.addActionListener(this::cbxFiltroPrioridadActionPerformed);

        lblFiltroPrioridad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroPrioridad.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroPrioridad.setText("PRIORIDAD");

        fondoBtnLimpiarFiltros.setBackground(new java.awt.Color(225, 29, 72));

        btnLimpiarFiltros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiarFiltros.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarFiltros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLimpiarFiltros.setText("LIMPIAR");
        btnLimpiarFiltros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout fondoBtnLimpiarFiltrosLayout = new javax.swing.GroupLayout(fondoBtnLimpiarFiltros);
        fondoBtnLimpiarFiltros.setLayout(fondoBtnLimpiarFiltrosLayout);
        fondoBtnLimpiarFiltrosLayout.setHorizontalGroup(
            fondoBtnLimpiarFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        fondoBtnLimpiarFiltrosLayout.setVerticalGroup(
            fondoBtnLimpiarFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        txtFiltroFecha.setEditable(false);
        txtFiltroFecha.setBackground(new java.awt.Color(15, 23, 42));
        txtFiltroFecha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        txtFiltroFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFiltroFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txtFiltroFecha.setText("   00/00/00");

        cbxFiltroEstado.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroEstado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroEstado.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "COMPLETADO" }));
        cbxFiltroEstado.setToolTipText("");
        cbxFiltroEstado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroEstado.addActionListener(this::cbxFiltroEstadoActionPerformed);

        lblFiltroEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroEstado.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroEstado.setText("ESTADO");

        javax.swing.GroupLayout pnlFiltrosHistorialLayout = new javax.swing.GroupLayout(pnlFiltrosHistorial);
        pnlFiltrosHistorial.setLayout(pnlFiltrosHistorialLayout);
        pnlFiltrosHistorialLayout.setHorizontalGroup(
            pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                        .addComponent(lblHistorialVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltroFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFiltroFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxFiltroMatrícula, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFiltroMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxFiltroPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFiltroPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxFiltroEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFiltroEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fondoBtnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        pnlFiltrosHistorialLayout.setVerticalGroup(
            pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblHistorialVuelo)
                .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFiltrosHistorialLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFiltroFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fondoBtnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxFiltroMatrícula, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                                .addComponent(lblFiltroMatricula)
                                .addGap(52, 52, 52))
                            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                                .addComponent(lblFiltroPrioridad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxFiltroPrioridad))
                            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                                .addComponent(lblFiltroEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxFiltroEstado)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFiltrosHistorialLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFiltroFecha)
                        .addGap(71, 71, 71))))
        );

        pnlTablaDinamicaHistorial.setBackground(new java.awt.Color(15, 23, 42));
        pnlTablaDinamicaHistorial.setLayout(new java.awt.BorderLayout());

        TblHistorialLogBook.setBackground(new java.awt.Color(30, 41, 59));
        TblHistorialLogBook.setForeground(new java.awt.Color(30, 41, 59));
        TblHistorialLogBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "FECHA", "MATRÍCULA", "FALLA REPORTADA", "PRIORIDAD", "ACCIÓN DE MANT.", "TÉCNICO", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScrollTablaHistorialLogBook.setViewportView(TblHistorialLogBook);

        pnlTablaDinamicaHistorial.add(ScrollTablaHistorialLogBook, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout pnlHistorialCuerpoLayout = new javax.swing.GroupLayout(pnlHistorialCuerpo);
        pnlHistorialCuerpo.setLayout(pnlHistorialCuerpoLayout);
        pnlHistorialCuerpoLayout.setHorizontalGroup(
            pnlHistorialCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHistorialCuerpoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlHistorialCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTablaDinamicaHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFiltrosHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        pnlHistorialCuerpoLayout.setVerticalGroup(
            pnlHistorialCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistorialCuerpoLayout.createSequentialGroup()
                .addComponent(panelEncabezado5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlFiltrosHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTablaDinamicaHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHistorialLogBookLayout = new javax.swing.GroupLayout(pnlHistorialLogBook);
        pnlHistorialLogBook.setLayout(pnlHistorialLogBookLayout);
        pnlHistorialLogBookLayout.setHorizontalGroup(
            pnlHistorialLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHistorialCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlHistorialLogBookLayout.setVerticalGroup(
            pnlHistorialLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHistorialCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlContenedorPrincipal.add(pnlHistorialLogBook, "pnlHistorialLogBook");

        getContentPane().add(pnlContenedorPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBtnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnSalirMouseClicked
        System.exit(0); //Cierra el programa
    }//GEN-LAST:event_txtBtnSalirMouseClicked

    private void txtBtnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnSalirMouseEntered
        //En este tipo de eventos se cambia el color del fondo y texto del boton cuando pase o salga el cursor del boton.
        fondoBtnSalir.setBackground(new Color(250,0,0)); //Color fondo (JPanel)
        txtBtnSalir.setForeground(new Color(255,255,255)); //COlor letras (JLabel)
    }//GEN-LAST:event_txtBtnSalirMouseEntered

    private void txtBtnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnSalirMouseExited
        fondoBtnSalir.setBackground(new Color(15,23,42));
        txtBtnSalir.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txtBtnSalirMouseExited

    private void btnReportesMantenimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMantenimientoMouseClicked
    // 1. Apagamos TODOS los botones
        btnReportesMantenimiento.setActivo(false);
        btnHistorialLogbook.setActivo(false);
    // 2. Encendemos SOLO el que acabamos de cliquear
        btnReportesMantenimiento.setActivo(true);
    // 3. Cambiamos la pantalla del medio usando tu CardLayout
    // Esto permitira intercambiar paneles de forma rapida como si fueran barajas, poniendo la que se usa encima de la anterior
        java.awt.CardLayout carta = (java.awt.CardLayout) pnlContenedorPrincipal.getLayout();
        carta.show(pnlContenedorPrincipal, "pnlReportesMantenimiento"); // Aca va el nombre del panel
    }//GEN-LAST:event_btnReportesMantenimientoMouseClicked

    private void btnHistorialLogbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialLogbookMouseClicked
        // Apagas todos
        btnReportesMantenimiento.setActivo(false);
        btnHistorialLogbook.setActivo(false);
        // Enciendes Historial
        btnHistorialLogbook.setActivo(true);
        // Muestras el panel de Historial LogBook
        java.awt.CardLayout carta = (java.awt.CardLayout) pnlContenedorPrincipal.getLayout();
        carta.show(pnlContenedorPrincipal, "pnlHistorialLogBook");
    }//GEN-LAST:event_btnHistorialLogbookMouseClicked

    private void cbxFiltroPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroPrioridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroPrioridadActionPerformed

    private void cbxFiltroMatrículaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroMatrículaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroMatrículaActionPerformed

    private void cbxFiltroEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroEstadoActionPerformed

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        Login_GUI login = new Login_GUI();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TecnicoMantenimiento_GUI().setVisible(true));
    }
    
//##### ESTE METODO ES PARA PERSONALIZAR EL DISEÑO DE LOS JCOMBOX #####
    private void aplicarTemaOscuro(javax.swing.JComboBox combo) {
    // 1. ACHICAR FLECHA Y MATAR EL FONDO BLANCO DE RAÍZ
    combo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
        @Override
        protected javax.swing.JButton createArrowButton() {
            javax.swing.JButton btn = new javax.swing.JButton("▼");
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setForeground(new java.awt.Color(148, 163, 184)); // Flecha gris
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            
            // Forzamos el ancho a solo 25 píxeles para no cortar el texto
            btn.setPreferredSize(new java.awt.Dimension(10, 20));
            return btn;
        }

        // ESTA FUNCIÓN DESTRUYE EL BLANCO/GRIS DE WINDOWS
        @Override
        public void paintCurrentValueBackground(java.awt.Graphics g, java.awt.Rectangle bounds, boolean hasFocus) {
            g.setColor(new java.awt.Color(15, 23, 42)); // Siempre Azul Oscuro (#0f172a)
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    });

    // 2. ELIMINAR EL ROJO FIJO Y DARLE PADDING (Control de lista)
    combo.setRenderer(new javax.swing.DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            // LA LÍNEA QUE FALTABA: Obliga a Java a pintar el fondo
            setOpaque(true); 
            
            // Le damos el espacio (padding) para que el texto respire y no choque con el borde
            setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 8, 2, 8));

            if (index == -1) {
                // CAJA PRINCIPAL CERRADA (Nunca roja, nunca blanca)
                setBackground(new java.awt.Color(15, 23, 42)); 
                setForeground(new java.awt.Color(255, 255, 255)); 
            } else {
                // LISTA DESPLEGADA
                if (isSelected) {
                    setBackground(new java.awt.Color(225, 29, 72)); // Rojo Latam al pasar el cursor
                    setForeground(java.awt.Color.WHITE);
                } else {
                    setBackground(new java.awt.Color(15, 23, 42)); // Fondo de opciones
                    setForeground(java.awt.Color.WHITE);
                }
            }
            return this;
        }
    });
    
    // 3. COLORES EXTERIORES Y BORDE ULTRAFINO
    combo.setBackground(new java.awt.Color(15, 23, 42)); 
    combo.setForeground(new java.awt.Color(255, 255, 255)); 
    
    // Borde de exactamente 1 píxel gris (como el panel web)
    combo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85), 1));
    }
    
//##### METODO PARA CAMBIAR EL DISEÑO DEL SCROLL #####
    private void aplicarScrollModerno(javax.swing.JScrollPane scroll) {
        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Quita el borde exterior

        scroll.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {

            // 1. MATAMOS LAS FLECHAS DE ARRIBA Y ABAJO
            @Override
            protected JButton createDecreaseButton(int orientation) { return createZeroButton(); }
            @Override
            protected JButton createIncreaseButton(int orientation) { return createZeroButton(); }
            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                return jbutton;
            }

            // 2. COLORES DE LA BARRA
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 116, 139); // Color de la barrita móvil (#64748b)
                this.trackColor = new Color(15, 23, 42); // Color del fondo de la pista (#0f172a)
            }

            // 3. GROSOR Y FORMA 
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) return;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(thumbColor);

                // EL TRUCO DEL GROSOR: 
                // thumbBounds.x + 4 (lo empuja a la derecha)
                // thumbBounds.width - 8 (lo hace más delgado)
                g2.fillRoundRect(thumbBounds.x + 4, thumbBounds.y, thumbBounds.width - 8, thumbBounds.height, 10, 10);
                g2.dispose();
            }
        });
    }

// #### METODOS PARA CAMBIAR DISEÑO DE LA TABLA ####
    private void aplicarTemaTabla(javax.swing.JTable tabla) {
        // 1. Fondo, color de letra y líneas de la tabla
        tabla.setBackground(new java.awt.Color(30, 41, 59)); // Azul oscuro del fondo (#0f172a)
        tabla.setForeground(new java.awt.Color(203, 213, 225)); // Texto Gris
        tabla.setRowHeight(35); // Hacemos las filas más altas para que respire el texto (padding)
        tabla.setShowVerticalLines(false); // Quitamos las líneas verticales para un estilo web más limpio
        tabla.setShowHorizontalLines(true);  // Encendemos las horizontales
        tabla.setGridColor(new java.awt.Color(30, 41, 59)); // Color de la línea (un azul grisáceo sutil, #1e293b)
        
        // Color cuando haces clic en una fila (Hover/Selección)
        tabla.setSelectionBackground(new java.awt.Color(38, 49, 72)); 
        tabla.setSelectionForeground(java.awt.Color.WHITE);

        // 2. Personalizar la Cabecera (Los títulos de las columnas)
        javax.swing.table.JTableHeader cabecera = tabla.getTableHeader();
        cabecera.setReorderingAllowed(false); //Esto bloquea que se puedan mover las columnas
        
        cabecera.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new java.awt.Color(30, 41, 59)); // Fondo de cabecera (#1e293b)
                c.setForeground(new java.awt.Color(203, 213, 225)); // Texto gris claro
                ((javax.swing.JLabel) c).setHorizontalAlignment(javax.swing.SwingConstants.LEFT); 
                
                ((javax.swing.JLabel) c).setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 65, 85)), 
                    javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10) 
                ));
                return c;
            }
        });
        cabecera.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        // =========================================================================
        // 3. Renderizador para el CUERPO de la tabla (ESPECÍFICO PARA MANTENIMIENTO)
        // =========================================================================
        tabla.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                javax.swing.JLabel label = (javax.swing.JLabel) c;

                // --- A. RESETEO BASE (Para que las celdas normales no hereden colores) ---
                label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
                label.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Margen interno
                label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

                if (isSelected) {
                    label.setBackground(new java.awt.Color(38, 49, 72));
                    label.setForeground(java.awt.Color.WHITE);
                } else {
                    label.setBackground(new java.awt.Color(30, 41, 59));
                    // Aquí el "Técnico" y todos los demás datos tomarán el blanco automáticamente
                    label.setForeground(new java.awt.Color(248, 250, 252)); 
                }

                // --- B. COLUMNA "#": Letra en Negrita ---
                if (column == 0) {
                    label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
                }

                // --- C. EFECTO DE PÍLDORAS PARA "ESTADO" Y "PRIORIDAD" ---
                String nombreColumna = table.getColumnName(column).toUpperCase();
                
                if (nombreColumna.equals("ESTADO") && value != null && !isSelected) {
                    String estado = value.toString().toUpperCase();
                    label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                    
                    switch (estado) {
                        case "CERRADO": 
                            label.setBackground(new java.awt.Color(5, 46, 22)); // #052e16
                            label.setForeground(new java.awt.Color(74, 222, 128)); // #4ade80 (Verde)
                            break;
                        case "ABIERTO": 
                            label.setBackground(new java.awt.Color(66, 32, 6)); // #422006
                            label.setForeground(new java.awt.Color(251, 191, 36)); // #fbbf24 (Amarillito)
                            break;
                    }
                }
                
                // --- C.2 COLUMNA "PRIORIDAD" ---
                if (nombreColumna.equals("PRIORIDAD") && value != null && !isSelected) {
                    String prio = value.toString().toUpperCase();
                    label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                    
                    switch (prio) {
                        case "ALTO":
                            label.setBackground(new java.awt.Color(76, 5, 25)); // pr
                            label.setForeground(new java.awt.Color(251, 113, 133)); 
                            break;
                        case "MEDIO":
                            label.setBackground(new java.awt.Color(66, 32, 6)); // pa
                            label.setForeground(new java.awt.Color(251, 191, 36)); 
                            break;
                        case "BAJO":
                            label.setBackground(new java.awt.Color(5, 46, 22)); // pg
                            label.setForeground(new java.awt.Color(74, 222, 128)); 
                            break;
                    }
                }

                // (NOTA: Se eliminó todo el bloque de OOOI porque el técnico de mantenimiento no lo usa)

                return c;
            }
        });
        
        // =========================================================================
        // 4. Deseleccionar al hacer clic en CUALQUIER LADO
        // =========================================================================
        java.awt.Toolkit.getDefaultToolkit().addAWTEventListener(new java.awt.event.AWTEventListener() {
            @Override
            public void eventDispatched(java.awt.AWTEvent event) {
                if (event instanceof java.awt.event.MouseEvent) {
                    java.awt.event.MouseEvent me = (java.awt.event.MouseEvent) event;
                    if (me.getID() == java.awt.event.MouseEvent.MOUSE_PRESSED) {
                        java.awt.Component comp = me.getComponent();
                        if (comp != null) {
                            boolean esParteDeLaTabla = javax.swing.SwingUtilities.isDescendingFrom(comp, tabla.getParent().getParent());
                            if (!esParteDeLaTabla) {
                                tabla.clearSelection();
                            } else if (comp == tabla) {
                                if (tabla.rowAtPoint(me.getPoint()) == -1) {
                                    tabla.clearSelection();
                                }
                            }
                        }
                    }
                }
            }
        }, java.awt.AWTEvent.MOUSE_EVENT_MASK);
    }
    
// Este método va a contar cuántas filas tienes y decidirá si agranda la caja o si le activa el scroll
    private void ajustarAlturaDinamica(javax.swing.JTable tabla, javax.swing.JScrollPane scroll) {
        // ¡EL TRUCO SENIOR! Le decimos a Java: "Espera a que la interfaz termine de cargar"
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int altoFila = tabla.getRowHeight(); // Alto de cada fila (Ej: 35px)
                int cantidadFilas = tabla.getRowCount(); // Cuántos vuelos hay
                int altoCabecera = 35; // Fijo manual
                
                // Calculamos cuánto espacio real necesitan los datos
                int altoDeseado = (cantidadFilas == 0) ? (altoCabecera + 50) : ((altoFila * cantidadFilas) + altoCabecera);
                int altoMaximo = 450; 
                
                // Decidimos el alto final
                int altoFinal = (altoDeseado > altoMaximo) ? altoMaximo : (altoDeseado + 5);
                
                // Rescatamos el ancho original 
                int anchoActual = scroll.getPreferredSize().width;
                if (anchoActual == 0) anchoActual = scroll.getWidth(); 
                
                java.awt.Dimension nuevoTamano = new java.awt.Dimension(anchoActual, altoFinal);
                
                // 1. Aplicamos la altura a la tabla
                tabla.setPreferredScrollableViewportSize(nuevoTamano);
                
                // 2. Aplicamos la altura al Scroll
                scroll.setPreferredSize(nuevoTamano);
                
                // 3. ¡LA LLAVE MAESTRA! Aplicamos la altura a tu jPanel2 (El contenedor padre)
                java.awt.Container panelPadre = scroll.getParent();
                if (panelPadre instanceof javax.swing.JPanel) {
                    panelPadre.setPreferredSize(nuevoTamano);
                    // Le ponemos tamaño mínimo y máximo para que NetBeans no lo pueda aplastar
                    panelPadre.setMinimumSize(nuevoTamano);
                    panelPadre.setMaximumSize(nuevoTamano);
                }
                
                // 4. Forzamos a Java a redibujar la pantalla de inmediato
                scroll.revalidate(); 
                scroll.repaint();
                if(panelPadre != null) {
                    panelPadre.revalidate();
                    panelPadre.repaint();
                }
                
                // 5. Tapamos los huecos grises del Viewport
                tabla.setFillsViewportHeight(true);
                if (tabla.getParent() instanceof javax.swing.JViewport) {
                    javax.swing.JViewport viewport = (javax.swing.JViewport) tabla.getParent();
                    viewport.setBackground(new java.awt.Color(30, 41, 59)); 
                }
            }
        });
    }
    
//#### METODO PARA CARGAR DATOS EN HISTORIAL LOGBOOK ####
    private void cargarDatosPruebaLogbook() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) TblHistorialLogBook.getModel();
        modelo.setRowCount(0);

        // Columnas esperadas: #, FECHA, MATRÍCULA, FALLA REPORTADA, PRIORIDAD, ACCIÓN DE MANT., TÉCNICO, ESTADO
        modelo.addRow(new Object[]{
            "#1", "20/05/2026", "OB-2200", "Falla intermitente en sensor AOA", "BAJO", "Pendiente", "Pendiente", "ABIERTO"
        });
        
        modelo.addRow(new Object[]{
            "#2", "19/05/2026", "OB-2101", "Reemplazo de neumático principal", "MEDIO", "Se reemplazó rueda izq.", "A. Quispe", "CERRADO"
        });
        
        modelo.addRow(new Object[]{
            "#3", "18/05/2026", "OB-1995", "Alerta de sistema hidráulico", "ALTO", "Revisión completa sist. verde", "C. Mendoza", "CERRADO"
        });

        // Aplicamos el ajuste dinámico
        ajustarAlturaDinamica(TblHistorialLogBook, ScrollTablaHistorialLogBook);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollListaReportes;
    private javax.swing.JScrollPane ScrollReportesMantenimiento;
    private javax.swing.JScrollPane ScrollTablaHistorialLogBook;
    private javax.swing.JScrollPane ScrollTxtObservacionesLogbook;
    private javax.swing.JTable TblHistorialLogBook;
    private javax.swing.JPanel bordeSuperior;
    private javax.swing.JLabel btnAprobarPlan;
    private javax.swing.JLabel btnCerrarSesion;
    private ElementosDiseño.BotonMenu btnHistorialLogbook;
    private javax.swing.JLabel btnLimpiarFiltros;
    private ElementosDiseño.BotonMenu btnReportesMantenimiento;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JComboBox<String> cbxFiltroEstado;
    private javax.swing.JComboBox<String> cbxFiltroMatrícula;
    private javax.swing.JComboBox<String> cbxFiltroPrioridad;
    private javax.swing.JPanel fondoBarraLateral;
    private javax.swing.JPanel fondoBtnAprobarPlan;
    private javax.swing.JPanel fondoBtnCerrarSesion;
    private javax.swing.JPanel fondoBtnLimpiarFiltros;
    private javax.swing.JPanel fondoBtnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblContadorAOG;
    private javax.swing.JLabel lblEstadoTecnicoControlLiberaciones;
    private javax.swing.JLabel lblFiltroEstado;
    private javax.swing.JLabel lblFiltroFecha;
    private javax.swing.JLabel lblFiltroMatricula;
    private javax.swing.JLabel lblFiltroPrioridad;
    private javax.swing.JLabel lblFirmaTecnica;
    private javax.swing.JLabel lblHistorialVuelo;
    private javax.swing.JLabel lblMatriculaAeronave;
    private javax.swing.JLabel lblModeloAeronave;
    private javax.swing.JLabel lblModulos;
    private javax.swing.JLabel lblObservacioneslogbook;
    private javax.swing.JLabel lblOperacionesMantenimiento;
    private javax.swing.JLabel lblOrdenesActivas;
    private javax.swing.JLabel lblPrioridadMant;
    private javax.swing.JLabel lblRegistroAcciones;
    private javax.swing.JLabel lblReportesMantenimiento;
    private javax.swing.JLabel lblRolSistema;
    private javax.swing.JLabel lblSinAeronaves;
    private javax.swing.JLabel lblSistemaControlTierra;
    private javax.swing.JLabel lblSistemaOnline;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logoBorde;
    private javax.swing.JPanel panelEncabezado3;
    private javax.swing.JPanel panelEncabezado5;
    private javax.swing.JPanel pnlContenedorDetalleReporte;
    private javax.swing.JPanel pnlContenedorPrincipal;
    private javax.swing.JPanel pnlContenedorTarjetasReportes;
    private javax.swing.JPanel pnlContenidoReportesMant;
    private javax.swing.JPanel pnlDatoAvion;
    private javax.swing.JPanel pnlDetalleSeleccionado;
    private javax.swing.JPanel pnlDetalleVacio;
    private javax.swing.JPanel pnlFiltrosHistorial;
    private javax.swing.JPanel pnlFondoContadorAOG;
    private javax.swing.JPanel pnlFondoLogoBorde;
    private javax.swing.JPanel pnlFondoPerfil;
    private javax.swing.JPanel pnlFondoSistemaOnline;
    private javax.swing.JPanel pnlHistorialCuerpo;
    private javax.swing.JPanel pnlHistorialLogBook;
    private javax.swing.JPanel pnlListaReportes;
    private javax.swing.JPanel pnlPrioridadMant;
    private javax.swing.JPanel pnlReportesMantenimiento;
    private javax.swing.JPanel pnlReportesMantenimientoCuerpo;
    private javax.swing.JPanel pnlTablaDinamicaHistorial;
    private javax.swing.JPanel pnlTarjetasLleno;
    private javax.swing.JPanel pnlTarjetasVacio;
    private javax.swing.JScrollPane scrollTxtRegistroAcciones;
    private javax.swing.JTextArea txtAreaObservacionesLogbook;
    private javax.swing.JTextArea txtAreaRegistroAcciones;
    private javax.swing.JLabel txtBtnSalir;
    private javax.swing.JTextField txtFieldFirmaTecnica;
    private javax.swing.JFormattedTextField txtFiltroFecha;
    // End of variables declaration//GEN-END:variables
}
