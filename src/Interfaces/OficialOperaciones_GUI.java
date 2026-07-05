package Interfaces;
import Clases.ReporteLogbook;
import Clases.ValidadorMetar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class OficialOperaciones_GUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OficialOperaciones_GUI.class.getName());
    private double mtowActual;
    
    public OficialOperaciones_GUI() {
        initComponents();
        // LISTA GLOBAL PARA GUARDAR A LOS SELECCIONADOS
//## CAMBIO DE DISEÑO BOTONES MENU ##
        // Le ponemos el texto a cada botón
        btnAsigancionVuelos.setTexto("Asignación de vuelos");
        btnAutorizarDespacho.setTexto("Autorizar Despacho de Vuelos");
        btnHistorialVuelos.setTexto("Historial de vuelos");
        btnGestionFlota.setTexto("Gestión de Flota");
        // Hacemos que el primero nazca encendido 
        btnAsigancionVuelos.setActivo(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Esto expande a FullScreen al iniciar

//## CAMBIO DE DISEÑO JCOMBOXS ##
        // Aplicamos el cambio de diseño para cada JCombox JComboBox
        aplicarTemaOscuro(cbxSeleccionVuelo); // Cambia "cbxGrupo" por el nombre de tu variable
        aplicarTemaOscuro(cbxSeleccionCAP);
        aplicarTemaOscuro(cbxSeleccionFO);
        aplicarTemaOscuro(cbxVuelosOOOI);
        aplicarTemaOscuro(cbxVuelosLogbook);
        aplicarTemaOscuro(cbxVuelosDespacho);
        aplicarTemaOscuro(cbxFiltroVuelo);
        aplicarTemaOscuro(cbxFiltroAeronave);
        aplicarTemaOscuro(cbxFiltroEstadoVuelo);
        aplicarTemaOscuro(cbxFiltroMatricula);
        aplicarTemaOscuro(cbxFiltroModelo);
        aplicarTemaOscuro(cbxFiltroEstadoAeronave);

        
        
//## CAMBIO DE DISEÑO SCROLLS ##
        aplicarScrollModerno(ScrollAsignacionVuelos);
        aplicarScrollModerno(ScrollPendientesDespacho);
        aplicarScrollModerno(ScrollTxtReporteFallas);
        aplicarScrollModerno(ScrollTxtAreaClima);
        aplicarScrollModerno(ScrollTripulacion);
        aplicarScrollModerno(ScrollTablaHistorialVuelos);  
        aplicarScrollModerno(ScrollTablaFlota);      

        
//## CAMBIO DE DISEÑO TABLAS ##
        aplicarTemaTabla(TblHistorialVuelo);
        aplicarTemaTabla(TblFlota);

//AJUSTE DINAMICO TABLAS
        ajustarAlturaDinamica(TblHistorialVuelo, ScrollTablaHistorialVuelos);
        ajustarAlturaDinamica(TblFlota, ScrollTablaFlota);

// BORRAR ESTO LUEGO Y SU METODOD## INYECCION DE DATOS DE PRUEBA ##
        cargarDatosPruebaHistorial();
        cargarDatosPruebaFlota();
        
    // Llenamos las cajitas dinámicamente desde BD
        cargarComboBoxesDespacho();
    // Cargamos los vuelos pendientes de despacho para la vista
        cargarVuelosPendientesDespacho();
    // Cargamos los vuelos aprobados para la parte de Control OOOI
        cargarVuelosEnControlOOOI();
    
    
    // Fuerza el color gris claro (puedes ajustar los valores RGB)
        txtAreaClima.setForeground(new java.awt.Color(204,204,204));
    // Forzamos el color del perfil
        lblUsuario.setForeground(new java.awt.Color(204, 204, 204));
        lblRolSistema.setForeground(new java.awt.Color(204,204,204));
    // Creamos un Timer que se ejecuta cada 3000 milisegundos (3 segundos)
        javax.swing.Timer timerReactivo = new javax.swing.Timer(3000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 1. Refrescamos la lista lateral silenciosamente
                cargarVuelosPendientesDespacho();
                pnlListaItemsVuelo.revalidate();
                pnlListaItemsVuelo.repaint();

                // 2. VALIDACIÓN REACTIVA PARA EL PANEL CENTRAL
                ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();
                int cantidadPendientes = dao.obtenerVuelosPendientesDespacho().size();

                // Asumiendo que tu panel padre del CardLayout se llama pnlContenedorDespacho
                java.awt.CardLayout card = (java.awt.CardLayout) pnlDespachoCuerpo.getLayout();

                if (cantidadPendientes == 0) {
                    // Si ya no hay vuelos, forzamos la vista vacía
                    card.show(pnlDespachoCuerpo, "card2"); // debe ser el nombre (Card Name)
                } 
                // Nota: No ponemos un "else" para mostrar el lleno automáticamente, 
                // para evitar interrumpir al usuario si está navegando en otras pestañas.
            }
        });
        timerReactivo.start(); // Blindamos los campos desde el inicio    
        
    // ACTIVACIÓN DE MATEMÁTICA EN TIEMPO REAL PARA WEIGHT & BALANCE
        javax.swing.event.DocumentListener escuchadorPesos = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { calcularPesosEnVivo(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { calcularPesosEnVivo(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { calcularPesosEnVivo(); }
        };
        
        // Le pegamos el escuchador a todas las cajas de texto que reciben números
        txtFieldPasajeros.getDocument().addDocumentListener(escuchadorPesos);
        txtFieldEquipaje.getDocument().addDocumentListener(escuchadorPesos);
        txtFieldCarga.getDocument().addDocumentListener(escuchadorPesos);
        txtFieldCombRuta.getDocument().addDocumentListener(escuchadorPesos);
        txtFieldCombReserva.getDocument().addDocumentListener(escuchadorPesos);
        txtAreaClima.getDocument().addDocumentListener(escuchadorPesos); //Cuadro de texto METAR
    }

    public java.util.List<Clases.TripulanteCabina> tcpsSeleccionadosList = new java.util.ArrayList<>();

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoPrioridadFallas = new javax.swing.ButtonGroup();
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
        btnAsigancionVuelos = new ElementosDiseño.BotonMenu();
        btnAutorizarDespacho = new ElementosDiseño.BotonMenu();
        btnHistorialVuelos = new ElementosDiseño.BotonMenu();
        btnGestionFlota = new ElementosDiseño.BotonMenu();
        pnlContenedorPrincipal = new javax.swing.JPanel();
        pnlAsignacion = new javax.swing.JPanel();
        pnlAsignacionCuerpo = new javax.swing.JPanel();
        panelEncabezado3 = new javax.swing.JPanel();
        lblAsignacionVuelos = new javax.swing.JLabel();
        lblSistemaControlTierra = new javax.swing.JLabel();
        ScrollAsignacionVuelos = new javax.swing.JScrollPane();
        pnlContenidoAsignacion = new javax.swing.JPanel();
        fondoContadores2 = new javax.swing.JPanel();
        pnlColorVuelosActivos = new javax.swing.JPanel();
        pnlContadorVuelosActivos = new javax.swing.JPanel();
        lblContadorVuelosActivos = new javax.swing.JLabel();
        lblVuelosActivos = new javax.swing.JLabel();
        pnlColorPendientesDespacho = new javax.swing.JPanel();
        pnlContadorPendientesDespacho = new javax.swing.JPanel();
        lblContadorPendientesDespacho = new javax.swing.JLabel();
        lblPendientesDespacho = new javax.swing.JLabel();
        pnlColorAeronavesAptas = new javax.swing.JPanel();
        pnlContadorAeronavesAptas = new javax.swing.JPanel();
        lblContadorAeronavesAptas = new javax.swing.JLabel();
        lblAeronavesAptas = new javax.swing.JLabel();
        pnlColorPersonalLibre = new javax.swing.JPanel();
        pnlContadorPersonalLibre = new javax.swing.JPanel();
        lblPersonalLibre = new javax.swing.JLabel();
        lblContadorPersonalLibre = new javax.swing.JLabel();
        pnlCreacionAsignacionVuelo = new javax.swing.JPanel();
        lblCreacionAsignacionVuelo = new javax.swing.JLabel();
        lblVueloProgramado = new javax.swing.JLabel();
        cbxSeleccionVuelo = new javax.swing.JComboBox<>();
        lblCapitan = new javax.swing.JLabel();
        lblPrimerOficial = new javax.swing.JLabel();
        cbxSeleccionCAP = new javax.swing.JComboBox<>();
        cbxSeleccionFO = new javax.swing.JComboBox<>();
        lblTripulacionCabina = new javax.swing.JLabel();
        ScrollTripulacion = new javax.swing.JScrollPane();
        pnlContendorTCP = new javax.swing.JPanel();
        lblSeleccioneGrupo = new javax.swing.JLabel();
        fondoBtnCrearVuelo = new javax.swing.JPanel();
        btnCrearVuelo = new javax.swing.JLabel();
        fondoBtnLimpiar = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JLabel();
        pnlPendientesDespacho = new javax.swing.JPanel();
        lblDespachoTecnico3 = new javax.swing.JLabel();
        pnlBasePendientes = new javax.swing.JPanel();
        pnlPendientesVacio = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        pnlPendientesLleno = new javax.swing.JPanel();
        ScrollPendientesDespacho = new javax.swing.JScrollPane();
        pnlListaItemsVuelo = new javax.swing.JPanel();
        pnlControlOOOI = new javax.swing.JPanel();
        lblCreacionAsignacionVuelo1 = new javax.swing.JLabel();
        cbxVuelosOOOI = new javax.swing.JComboBox<>();
        btnOUT = new javax.swing.JPanel();
        lblOUT = new javax.swing.JLabel();
        lblPushBack = new javax.swing.JLabel();
        lblTiempoOUT = new javax.swing.JLabel();
        btnOFF = new javax.swing.JPanel();
        lblOFF = new javax.swing.JLabel();
        lblTakeOff = new javax.swing.JLabel();
        lblTiempoOFF = new javax.swing.JLabel();
        btnON = new javax.swing.JPanel();
        lblON = new javax.swing.JLabel();
        lblLanding = new javax.swing.JLabel();
        lblTiempoON = new javax.swing.JLabel();
        btnIN = new javax.swing.JPanel();
        lblIN = new javax.swing.JLabel();
        lblGateArrival = new javax.swing.JLabel();
        lblTiempoIN = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pnlLogBook = new javax.swing.JPanel();
        lblCreacionAsignacionVuelo2 = new javax.swing.JLabel();
        lblVueloProgramado1 = new javax.swing.JLabel();
        cbxVuelosLogbook = new javax.swing.JComboBox();
        lblVueloProgramado2 = new javax.swing.JLabel();
        lblVueloProgramado3 = new javax.swing.JLabel();
        rbtnPrioridadBaja = new javax.swing.JRadioButton();
        rbtnPrioridadMedia = new javax.swing.JRadioButton();
        rbtnPrioridadAlta = new javax.swing.JRadioButton();
        txtCombustibleRestante = new javax.swing.JTextField();
        lblVueloProgramado4 = new javax.swing.JLabel();
        ScrollTxtReporteFallas = new javax.swing.JScrollPane();
        txtReporteFallas = new javax.swing.JTextArea();
        fondoBtnCancelarVuelo3 = new javax.swing.JPanel();
        btnCerrarVuelo = new javax.swing.JLabel();
        rbtnSinFallas = new javax.swing.JRadioButton();
        pnlDespacho = new javax.swing.JPanel();
        pnlDespachoCuerpo = new javax.swing.JPanel();
        pnlVacio = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fondoContadores = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        pnlContadorPendientesDemora = new javax.swing.JPanel();
        lblContadorPendientesDemora = new javax.swing.JLabel();
        lblPendientesDemora = new javax.swing.JLabel();
        pnlColorAprobados = new javax.swing.JPanel();
        pnlContadorAprobados = new javax.swing.JPanel();
        lblContadorAprobados = new javax.swing.JLabel();
        lblAprobados = new javax.swing.JLabel();
        pnlColorCancelados = new javax.swing.JPanel();
        pnlContadorCancelados = new javax.swing.JPanel();
        lblContadorCancelados = new javax.swing.JLabel();
        lblCancelados = new javax.swing.JLabel();
        pnlColorModuloActivo = new javax.swing.JPanel();
        pnlContadorModuloActivo = new javax.swing.JPanel();
        lblModuloActivo = new javax.swing.JLabel();
        lblModuloactivo = new javax.swing.JLabel();
        pnlVacio1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDespachoTecnico1 = new javax.swing.JLabel();
        pnlLleno = new javax.swing.JPanel();
        pnlContenidoDespacho = new javax.swing.JPanel();
        lblDespachoTecnico = new javax.swing.JLabel();
        pnlFondoResumenRuta = new javax.swing.JPanel();
        lblResumenRuta = new javax.swing.JLabel();
        lblRequerimientosTecnicos = new javax.swing.JLabel();
        lblCombRuta = new javax.swing.JLabel();
        pnlFondoTxtArea = new javax.swing.JPanel();
        ScrollTxtAreaClima = new javax.swing.JScrollPane();
        txtAreaClima = new javax.swing.JTextArea();
        lblClimaDestino = new javax.swing.JLabel();
        lblCombReserva = new javax.swing.JLabel();
        txtFieldCombReserva = new javax.swing.JTextField();
        txtFieldEquipaje = new javax.swing.JTextField();
        lblSumatoriaPesos = new javax.swing.JLabel();
        lblPasajerosKG = new javax.swing.JLabel();
        txtFieldPasajeros = new javax.swing.JTextField();
        lblEquipajeKG = new javax.swing.JLabel();
        txtFieldCarga = new javax.swing.JTextField();
        lblCargaKG = new javax.swing.JLabel();
        lblCombustibleKGSuma = new javax.swing.JLabel();
        txtFieldCombustible = new javax.swing.JTextField();
        txtFieldCombRuta = new javax.swing.JTextField();
        pnlFondoCalculoMTOW = new javax.swing.JPanel();
        lblSumaTotal = new javax.swing.JLabel();
        lblSlash = new javax.swing.JLabel();
        lblPesoMaximo = new javax.swing.JLabel();
        barraMTOW = new javax.swing.JProgressBar();
        pnlFondoAprobado = new javax.swing.JPanel();
        lblAprobado = new javax.swing.JLabel();
        lblPorcentajeMTOW = new javax.swing.JLabel();
        fondoBtnAprobarPlan = new javax.swing.JPanel();
        btnAprobarPlan = new javax.swing.JLabel();
        fondoBtnDeclararDemora = new javax.swing.JPanel();
        btnDeclararDemora = new javax.swing.JLabel();
        fondoBtnCancelarVuelo = new javax.swing.JPanel();
        btnCancelarVuelo = new javax.swing.JLabel();
        cbxVuelosDespacho = new javax.swing.JComboBox<>();
        fondoContadores1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        pnlContadorPendientesDemora1 = new javax.swing.JPanel();
        lblContadorPendientesDemora1 = new javax.swing.JLabel();
        lblPendientesDemora1 = new javax.swing.JLabel();
        pnlColorAprobados1 = new javax.swing.JPanel();
        pnlContadorAprobados1 = new javax.swing.JPanel();
        lblContadorAprobados1 = new javax.swing.JLabel();
        lblAprobados1 = new javax.swing.JLabel();
        pnlColorCancelados1 = new javax.swing.JPanel();
        pnlContadorCancelados1 = new javax.swing.JPanel();
        lblContadorCancelados1 = new javax.swing.JLabel();
        lblCancelados1 = new javax.swing.JLabel();
        pnlColorModuloActivo1 = new javax.swing.JPanel();
        pnlContadorModuloActivo1 = new javax.swing.JPanel();
        lblModuloActivo1 = new javax.swing.JLabel();
        lblModuloactivo1 = new javax.swing.JLabel();
        panelEncabezado4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlHistorial = new javax.swing.JPanel();
        pnlHistorialCuerpo = new javax.swing.JPanel();
        panelEncabezado5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlFiltrosHistorial = new javax.swing.JPanel();
        lblHistorialVuelo = new javax.swing.JLabel();
        lblFiltroVuelo = new javax.swing.JLabel();
        cbxFiltroVuelo = new javax.swing.JComboBox<>();
        lblFiltroAeronave = new javax.swing.JLabel();
        cbxFiltroAeronave = new javax.swing.JComboBox<>();
        cbxFiltroEstadoVuelo = new javax.swing.JComboBox<>();
        lblFiltroEstadoVuelo = new javax.swing.JLabel();
        fondoBtnLimpiarFiltros = new javax.swing.JPanel();
        btnLimpiarFiltros = new javax.swing.JLabel();
        pnlTablaDinamicaHistorial = new javax.swing.JPanel();
        ScrollTablaHistorialVuelos = new javax.swing.JScrollPane();
        TblHistorialVuelo = new javax.swing.JTable();
        pnlFlota = new javax.swing.JPanel();
        pnlFlotaCuerpo = new javax.swing.JPanel();
        panelEncabezado6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlFiltrosFlota = new javax.swing.JPanel();
        lblRegistroFlota = new javax.swing.JLabel();
        lblFiltroMatricula = new javax.swing.JLabel();
        cbxFiltroMatricula = new javax.swing.JComboBox<>();
        lblFiltroModelo = new javax.swing.JLabel();
        cbxFiltroModelo = new javax.swing.JComboBox<>();
        cbxFiltroEstadoAeronave = new javax.swing.JComboBox<>();
        lblFiltroEstadoAeronave = new javax.swing.JLabel();
        fondoBtnLimpiarFiltrosFlota = new javax.swing.JPanel();
        btnLimpiarFiltrosFlota = new javax.swing.JLabel();
        pnlTablaDinamicaFlota = new javax.swing.JPanel();
        ScrollTablaFlota = new javax.swing.JScrollPane();
        TblFlota = new javax.swing.JTable();

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

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_imagenes/logo.png"))); // NOI18N
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

        btnAsigancionVuelos.setTexto("Asignación de vuelos");
        btnAsigancionVuelos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAsigancionVuelosMouseClicked(evt);
            }
        });

        btnAutorizarDespacho.setTexto("Autorizar Despacho de Vuelos");
        btnAutorizarDespacho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAutorizarDespachoMouseClicked(evt);
            }
        });

        btnHistorialVuelos.setTexto("Historial de vuelos");
        btnHistorialVuelos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHistorialVuelosMouseClicked(evt);
            }
        });

        btnGestionFlota.setTexto("Gestión de Flota");
        btnGestionFlota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGestionFlotaMouseClicked(evt);
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
                                .addGroup(fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                                        .addComponent(pnlFondoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblRolSistema)
                                            .addComponent(lblUsuario))
                                        .addGap(34, 34, 34)
                                        .addComponent(fondoBtnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(pnlFondoSistemaOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAsigancionVuelos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fondoBarraLateralLayout.createSequentialGroup()
                                .addComponent(lblModulos)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnAutorizarDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHistorialVuelos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGestionFlota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        fondoBarraLateralLayout.setVerticalGroup(
            fondoBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoBarraLateralLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlFondoSistemaOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblModulos)
                .addGap(18, 18, 18)
                .addComponent(btnAsigancionVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAutorizarDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHistorialVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGestionFlota, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 566, Short.MAX_VALUE)
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

        pnlAsignacion.setBackground(new java.awt.Color(153, 51, 0));

        pnlAsignacionCuerpo.setBackground(new java.awt.Color(15, 23, 42));

        panelEncabezado3.setBackground(new java.awt.Color(15, 23, 42));
        panelEncabezado3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        lblAsignacionVuelos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblAsignacionVuelos.setForeground(new java.awt.Color(255, 255, 255));
        lblAsignacionVuelos.setText("Asignación de vuelos ");

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
                    .addComponent(lblAsignacionVuelos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEncabezado3Layout.setVerticalGroup(
            panelEncabezado3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblAsignacionVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSistemaControlTierra)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        ScrollAsignacionVuelos.setBorder(null);
        ScrollAsignacionVuelos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlContenidoAsignacion.setBackground(new java.awt.Color(15, 23, 42));

        fondoContadores2.setBackground(new java.awt.Color(15, 23, 42));

        pnlColorVuelosActivos.setBackground(new java.awt.Color(219, 29, 72));
        pnlColorVuelosActivos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorVuelosActivos.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorVuelosActivos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorVuelosActivos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorVuelosActivos.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorVuelosActivos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorVuelosActivos.setText("0");

        lblVuelosActivos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblVuelosActivos.setForeground(new java.awt.Color(148, 163, 175));
        lblVuelosActivos.setText("Vuelos Activos");

        javax.swing.GroupLayout pnlContadorVuelosActivosLayout = new javax.swing.GroupLayout(pnlContadorVuelosActivos);
        pnlContadorVuelosActivos.setLayout(pnlContadorVuelosActivosLayout);
        pnlContadorVuelosActivosLayout.setHorizontalGroup(
            pnlContadorVuelosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorVuelosActivosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorVuelosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorVuelosActivosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblVuelosActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorVuelosActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorVuelosActivosLayout.setVerticalGroup(
            pnlContadorVuelosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorVuelosActivosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorVuelosActivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVuelosActivos)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorVuelosActivosLayout = new javax.swing.GroupLayout(pnlColorVuelosActivos);
        pnlColorVuelosActivos.setLayout(pnlColorVuelosActivosLayout);
        pnlColorVuelosActivosLayout.setHorizontalGroup(
            pnlColorVuelosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorVuelosActivosLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorVuelosActivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorVuelosActivosLayout.setVerticalGroup(
            pnlColorVuelosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorVuelosActivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorPendientesDespacho.setBackground(new java.awt.Color(245, 158, 11));
        pnlColorPendientesDespacho.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorPendientesDespacho.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorPendientesDespacho.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorPendientesDespacho.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorPendientesDespacho.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorPendientesDespacho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorPendientesDespacho.setText("0");

        lblPendientesDespacho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPendientesDespacho.setForeground(new java.awt.Color(148, 163, 175));
        lblPendientesDespacho.setText("Pendientes Despacho");

        javax.swing.GroupLayout pnlContadorPendientesDespachoLayout = new javax.swing.GroupLayout(pnlContadorPendientesDespacho);
        pnlContadorPendientesDespacho.setLayout(pnlContadorPendientesDespachoLayout);
        pnlContadorPendientesDespachoLayout.setHorizontalGroup(
            pnlContadorPendientesDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorPendientesDespachoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorPendientesDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorPendientesDespachoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblPendientesDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorPendientesDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorPendientesDespachoLayout.setVerticalGroup(
            pnlContadorPendientesDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorPendientesDespachoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorPendientesDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPendientesDespacho)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorPendientesDespachoLayout = new javax.swing.GroupLayout(pnlColorPendientesDespacho);
        pnlColorPendientesDespacho.setLayout(pnlColorPendientesDespachoLayout);
        pnlColorPendientesDespachoLayout.setHorizontalGroup(
            pnlColorPendientesDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorPendientesDespachoLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorPendientesDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorPendientesDespachoLayout.setVerticalGroup(
            pnlColorPendientesDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorPendientesDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorAeronavesAptas.setBackground(new java.awt.Color(34, 197, 94));
        pnlColorAeronavesAptas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorAeronavesAptas.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorAeronavesAptas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorAeronavesAptas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorAeronavesAptas.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorAeronavesAptas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorAeronavesAptas.setText("0");

        lblAeronavesAptas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAeronavesAptas.setForeground(new java.awt.Color(148, 163, 175));
        lblAeronavesAptas.setText("Aeronaves Aptas");

        javax.swing.GroupLayout pnlContadorAeronavesAptasLayout = new javax.swing.GroupLayout(pnlContadorAeronavesAptas);
        pnlContadorAeronavesAptas.setLayout(pnlContadorAeronavesAptasLayout);
        pnlContadorAeronavesAptasLayout.setHorizontalGroup(
            pnlContadorAeronavesAptasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorAeronavesAptasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorAeronavesAptasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorAeronavesAptasLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblAeronavesAptas, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorAeronavesAptas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorAeronavesAptasLayout.setVerticalGroup(
            pnlContadorAeronavesAptasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorAeronavesAptasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorAeronavesAptas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAeronavesAptas)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorAeronavesAptasLayout = new javax.swing.GroupLayout(pnlColorAeronavesAptas);
        pnlColorAeronavesAptas.setLayout(pnlColorAeronavesAptasLayout);
        pnlColorAeronavesAptasLayout.setHorizontalGroup(
            pnlColorAeronavesAptasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorAeronavesAptasLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorAeronavesAptas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorAeronavesAptasLayout.setVerticalGroup(
            pnlColorAeronavesAptasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorAeronavesAptas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorPersonalLibre.setBackground(new java.awt.Color(58, 128, 241));
        pnlColorPersonalLibre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorPersonalLibre.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorPersonalLibre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblPersonalLibre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPersonalLibre.setForeground(new java.awt.Color(148, 163, 175));
        lblPersonalLibre.setText("Personal Libre");

        lblContadorPersonalLibre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorPersonalLibre.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorPersonalLibre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorPersonalLibre.setText("0");

        javax.swing.GroupLayout pnlContadorPersonalLibreLayout = new javax.swing.GroupLayout(pnlContadorPersonalLibre);
        pnlContadorPersonalLibre.setLayout(pnlContadorPersonalLibreLayout);
        pnlContadorPersonalLibreLayout.setHorizontalGroup(
            pnlContadorPersonalLibreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorPersonalLibreLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlContadorPersonalLibreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContadorPersonalLibre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPersonalLibre, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorPersonalLibreLayout.setVerticalGroup(
            pnlContadorPersonalLibreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorPersonalLibreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorPersonalLibre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(lblPersonalLibre)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorPersonalLibreLayout = new javax.swing.GroupLayout(pnlColorPersonalLibre);
        pnlColorPersonalLibre.setLayout(pnlColorPersonalLibreLayout);
        pnlColorPersonalLibreLayout.setHorizontalGroup(
            pnlColorPersonalLibreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorPersonalLibreLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorPersonalLibre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorPersonalLibreLayout.setVerticalGroup(
            pnlColorPersonalLibreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorPersonalLibre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout fondoContadores2Layout = new javax.swing.GroupLayout(fondoContadores2);
        fondoContadores2.setLayout(fondoContadores2Layout);
        fondoContadores2Layout.setHorizontalGroup(
            fondoContadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoContadores2Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(pnlColorVuelosActivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorPendientesDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorAeronavesAptas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorPersonalLibre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fondoContadores2Layout.setVerticalGroup(
            fondoContadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoContadores2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoContadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlColorPersonalLibre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColorAeronavesAptas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColorPendientesDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColorVuelosActivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCreacionAsignacionVuelo.setBackground(new java.awt.Color(30, 41, 59));
        pnlCreacionAsignacionVuelo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblCreacionAsignacionVuelo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCreacionAsignacionVuelo.setForeground(new java.awt.Color(203, 213, 225));
        lblCreacionAsignacionVuelo.setText("CREACIÓN Y ASIGNACIÓN DE VUELO");

        lblVueloProgramado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblVueloProgramado.setForeground(new java.awt.Color(203, 213, 225));
        lblVueloProgramado.setText("VUELO PROGRAMADO");

        cbxSeleccionVuelo.setBackground(new java.awt.Color(15, 23, 42));
        cbxSeleccionVuelo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxSeleccionVuelo.setForeground(new java.awt.Color(255, 255, 255));
        cbxSeleccionVuelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "— Seleccionar Vuelo — ", "vUELO 1", "VUELO 2", "VUELO 3", " " }));
        cbxSeleccionVuelo.setToolTipText("");
        cbxSeleccionVuelo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxSeleccionVuelo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxSeleccionVuelo.addActionListener(this::cbxSeleccionVueloActionPerformed);

        lblCapitan.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCapitan.setForeground(new java.awt.Color(203, 213, 225));
        lblCapitan.setText("CAPITAN (CAP)");

        lblPrimerOficial.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPrimerOficial.setForeground(new java.awt.Color(203, 213, 225));
        lblPrimerOficial.setText("PRIMER OFICIAL (FO)");

        cbxSeleccionCAP.setBackground(new java.awt.Color(15, 23, 42));
        cbxSeleccionCAP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxSeleccionCAP.setForeground(new java.awt.Color(255, 255, 255));
        cbxSeleccionCAP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "—" }));
        cbxSeleccionCAP.setToolTipText("");
        cbxSeleccionCAP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxSeleccionCAP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxSeleccionCAP.addActionListener(this::cbxSeleccionCAPActionPerformed);

        cbxSeleccionFO.setBackground(new java.awt.Color(15, 23, 42));
        cbxSeleccionFO.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxSeleccionFO.setForeground(new java.awt.Color(255, 255, 255));
        cbxSeleccionFO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "—" }));
        cbxSeleccionFO.setToolTipText("");
        cbxSeleccionFO.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxSeleccionFO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxSeleccionFO.setName(""); // NOI18N
        cbxSeleccionFO.addActionListener(this::cbxSeleccionFOActionPerformed);

        lblTripulacionCabina.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTripulacionCabina.setForeground(new java.awt.Color(203, 213, 225));
        lblTripulacionCabina.setText("TCP — TRIPULACIÓN DE CABINA");

        ScrollTripulacion.setBorder(null);

        pnlContendorTCP.setBackground(new java.awt.Color(30, 41, 59));

        lblSeleccioneGrupo.setForeground(new java.awt.Color(148, 163, 184));
        lblSeleccioneGrupo.setText("Seleccione un vuelo primero");
        pnlContendorTCP.add(lblSeleccioneGrupo);

        ScrollTripulacion.setViewportView(pnlContendorTCP);

        fondoBtnCrearVuelo.setBackground(new java.awt.Color(225, 29, 72));

        btnCrearVuelo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCrearVuelo.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearVuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCrearVuelo.setText("Crear Vuelo");
        btnCrearVuelo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearVuelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearVueloMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnCrearVueloLayout = new javax.swing.GroupLayout(fondoBtnCrearVuelo);
        fondoBtnCrearVuelo.setLayout(fondoBtnCrearVueloLayout);
        fondoBtnCrearVueloLayout.setHorizontalGroup(
            fondoBtnCrearVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCrearVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
        fondoBtnCrearVueloLayout.setVerticalGroup(
            fondoBtnCrearVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCrearVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fondoBtnLimpiar.setBackground(new java.awt.Color(15, 23, 42));
        fondoBtnLimpiar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnLimpiarLayout = new javax.swing.GroupLayout(fondoBtnLimpiar);
        fondoBtnLimpiar.setLayout(fondoBtnLimpiarLayout);
        fondoBtnLimpiarLayout.setHorizontalGroup(
            fondoBtnLimpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        fondoBtnLimpiarLayout.setVerticalGroup(
            fondoBtnLimpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlCreacionAsignacionVueloLayout = new javax.swing.GroupLayout(pnlCreacionAsignacionVuelo);
        pnlCreacionAsignacionVuelo.setLayout(pnlCreacionAsignacionVueloLayout);
        pnlCreacionAsignacionVueloLayout.setHorizontalGroup(
            pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCreacionAsignacionVueloLayout.createSequentialGroup()
                .addGroup(pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCreacionAsignacionVueloLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fondoBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fondoBtnCrearVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCreacionAsignacionVueloLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrimerOficial, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSeleccionFO, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCreacionAsignacionVueloLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTripulacionCabina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxSeleccionVuelo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ScrollTripulacion)
                            .addGroup(pnlCreacionAsignacionVueloLayout.createSequentialGroup()
                                .addGroup(pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCapitan, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblVueloProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCreacionAsignacionVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxSeleccionCAP, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(19, 19, 19))
        );
        pnlCreacionAsignacionVueloLayout.setVerticalGroup(
            pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCreacionAsignacionVueloLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblCreacionAsignacionVuelo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVueloProgramado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxSeleccionVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCapitan)
                    .addComponent(lblPrimerOficial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSeleccionCAP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSeleccionFO, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTripulacionCabina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollTripulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCreacionAsignacionVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fondoBtnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fondoBtnCrearVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        pnlPendientesDespacho.setBackground(new java.awt.Color(30, 41, 59));
        pnlPendientesDespacho.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblDespachoTecnico3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDespachoTecnico3.setForeground(new java.awt.Color(203, 213, 225));
        lblDespachoTecnico3.setText("PENDIENTES DE DESPACHO TÉCNICO");

        pnlBasePendientes.setLayout(new java.awt.CardLayout());

        pnlPendientesVacio.setBackground(new java.awt.Color(30, 41, 59));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(148, 163, 175));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Sin vuelos pendientes ");

        javax.swing.GroupLayout pnlPendientesVacioLayout = new javax.swing.GroupLayout(pnlPendientesVacio);
        pnlPendientesVacio.setLayout(pnlPendientesVacioLayout);
        pnlPendientesVacioLayout.setHorizontalGroup(
            pnlPendientesVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendientesVacioLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        pnlPendientesVacioLayout.setVerticalGroup(
            pnlPendientesVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendientesVacioLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pnlBasePendientes.add(pnlPendientesVacio, "card2");

        pnlPendientesLleno.setBackground(new java.awt.Color(30, 41, 59));
        pnlPendientesLleno.setLayout(new java.awt.BorderLayout());

        ScrollPendientesDespacho.setBorder(null);

        pnlListaItemsVuelo.setBackground(new java.awt.Color(30, 41, 59));
        pnlListaItemsVuelo.setLayout(new javax.swing.BoxLayout(pnlListaItemsVuelo, javax.swing.BoxLayout.LINE_AXIS));
        ScrollPendientesDespacho.setViewportView(pnlListaItemsVuelo);

        pnlPendientesLleno.add(ScrollPendientesDespacho, java.awt.BorderLayout.CENTER);

        pnlBasePendientes.add(pnlPendientesLleno, "card3");

        javax.swing.GroupLayout pnlPendientesDespachoLayout = new javax.swing.GroupLayout(pnlPendientesDespacho);
        pnlPendientesDespacho.setLayout(pnlPendientesDespachoLayout);
        pnlPendientesDespachoLayout.setHorizontalGroup(
            pnlPendientesDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendientesDespachoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblDespachoTecnico3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPendientesDespachoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBasePendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPendientesDespachoLayout.setVerticalGroup(
            pnlPendientesDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendientesDespachoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblDespachoTecnico3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBasePendientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlControlOOOI.setBackground(new java.awt.Color(30, 41, 59));
        pnlControlOOOI.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblCreacionAsignacionVuelo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCreacionAsignacionVuelo1.setForeground(new java.awt.Color(203, 213, 225));
        lblCreacionAsignacionVuelo1.setText("CONTROL OOOI");

        cbxVuelosOOOI.setBackground(new java.awt.Color(15, 23, 42));
        cbxVuelosOOOI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxVuelosOOOI.setForeground(new java.awt.Color(255, 255, 255));
        cbxVuelosOOOI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LA800 - OB-2101" }));
        cbxVuelosOOOI.setToolTipText("");
        cbxVuelosOOOI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxVuelosOOOI.addActionListener(this::cbxVuelosOOOIActionPerformed);

        btnOUT.setBackground(new java.awt.Color(24, 34, 52));
        btnOUT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 2, true));
        btnOUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOUT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOUTMouseClicked(evt);
            }
        });

        lblOUT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblOUT.setForeground(new java.awt.Color(115, 123, 134));
        lblOUT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOUT.setText("OUT");

        lblPushBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPushBack.setForeground(new java.awt.Color(115, 123, 134));
        lblPushBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPushBack.setText("PushBack");

        lblTiempoOUT.setForeground(new java.awt.Color(115, 123, 134));
        lblTiempoOUT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempoOUT.setText("-- : --Z");

        javax.swing.GroupLayout btnOUTLayout = new javax.swing.GroupLayout(btnOUT);
        btnOUT.setLayout(btnOUTLayout);
        btnOUTLayout.setHorizontalGroup(
            btnOUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnOUTLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(btnOUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblOUT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPushBack, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(lblTiempoOUT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        btnOUTLayout.setVerticalGroup(
            btnOUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnOUTLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblOUT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPushBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTiempoOUT)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnOFF.setBackground(new java.awt.Color(24, 34, 52));
        btnOFF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 2, true));
        btnOFF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOFF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOFFMouseClicked(evt);
            }
        });

        lblOFF.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblOFF.setForeground(new java.awt.Color(115, 123, 134));
        lblOFF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOFF.setText("OFF");

        lblTakeOff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTakeOff.setForeground(new java.awt.Color(115, 123, 134));
        lblTakeOff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTakeOff.setText("TakeOff");

        lblTiempoOFF.setForeground(new java.awt.Color(115, 123, 134));
        lblTiempoOFF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempoOFF.setText("-- : --Z");

        javax.swing.GroupLayout btnOFFLayout = new javax.swing.GroupLayout(btnOFF);
        btnOFF.setLayout(btnOFFLayout);
        btnOFFLayout.setHorizontalGroup(
            btnOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnOFFLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(btnOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblOFF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTakeOff, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(lblTiempoOFF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        btnOFFLayout.setVerticalGroup(
            btnOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnOFFLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblOFF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTakeOff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTiempoOFF)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnON.setBackground(new java.awt.Color(24, 34, 52));
        btnON.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 2, true));
        btnON.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnON.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnONMouseClicked(evt);
            }
        });

        lblON.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblON.setForeground(new java.awt.Color(115, 123, 134));
        lblON.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblON.setText("ON");

        lblLanding.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLanding.setForeground(new java.awt.Color(115, 123, 134));
        lblLanding.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLanding.setText("Landing");

        lblTiempoON.setForeground(new java.awt.Color(115, 123, 134));
        lblTiempoON.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempoON.setText("-- : --Z");

        javax.swing.GroupLayout btnONLayout = new javax.swing.GroupLayout(btnON);
        btnON.setLayout(btnONLayout);
        btnONLayout.setHorizontalGroup(
            btnONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnONLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(btnONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLanding, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(lblTiempoON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        btnONLayout.setVerticalGroup(
            btnONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnONLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblON)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLanding)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTiempoON)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnIN.setBackground(new java.awt.Color(24, 34, 52));
        btnIN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 2, true));
        btnIN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnINMouseClicked(evt);
            }
        });

        lblIN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblIN.setForeground(new java.awt.Color(115, 123, 134));
        lblIN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIN.setText("IN");

        lblGateArrival.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGateArrival.setForeground(new java.awt.Color(115, 123, 134));
        lblGateArrival.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGateArrival.setText("Gate Arrival");

        lblTiempoIN.setForeground(new java.awt.Color(115, 123, 134));
        lblTiempoIN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempoIN.setText("-- : --Z");

        javax.swing.GroupLayout btnINLayout = new javax.swing.GroupLayout(btnIN);
        btnIN.setLayout(btnINLayout);
        btnINLayout.setHorizontalGroup(
            btnINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnINLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(btnINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGateArrival, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(lblTiempoIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        btnINLayout.setVerticalGroup(
            btnINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnINLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblIN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGateArrival)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTiempoIN)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(66, 32, 6));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(146, 64, 14), 1, true));

        jLabel12.setForeground(new java.awt.Color(252, 211, 77));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("   ⚠  Módulo de Control OOOI inhabilitado debido a la ausencia de operaciones de vuelo activas.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlControlOOOILayout = new javax.swing.GroupLayout(pnlControlOOOI);
        pnlControlOOOI.setLayout(pnlControlOOOILayout);
        pnlControlOOOILayout.setHorizontalGroup(
            pnlControlOOOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlOOOILayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlControlOOOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlControlOOOILayout.createSequentialGroup()
                        .addComponent(btnON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlControlOOOILayout.createSequentialGroup()
                        .addGroup(pnlControlOOOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCreacionAsignacionVuelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlControlOOOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnOFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxVuelosOOOI, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlControlOOOILayout.setVerticalGroup(
            pnlControlOOOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlOOOILayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlControlOOOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCreacionAsignacionVuelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxVuelosOOOI, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlControlOOOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlControlOOOILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlLogBook.setBackground(new java.awt.Color(30, 41, 59));
        pnlLogBook.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblCreacionAsignacionVuelo2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCreacionAsignacionVuelo2.setForeground(new java.awt.Color(203, 213, 225));
        lblCreacionAsignacionVuelo2.setText("CIERRE LOGBOOK");

        lblVueloProgramado1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblVueloProgramado1.setForeground(new java.awt.Color(203, 213, 225));
        lblVueloProgramado1.setText("VUELO EN ESTADO IN");

        cbxVuelosLogbook.setBackground(new java.awt.Color(15, 23, 42));
        cbxVuelosLogbook.setForeground(new java.awt.Color(255, 255, 255));
        cbxVuelosLogbook.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "— Seleccionar Vuelo —" }));
        cbxVuelosLogbook.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxVuelosLogbook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxVuelosLogbook.addActionListener(this::cbxVuelosLogbookActionPerformed);

        lblVueloProgramado2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblVueloProgramado2.setForeground(new java.awt.Color(203, 213, 225));
        lblVueloProgramado2.setText("COMBUSTIBLE RESTANTE (KG) ");

        lblVueloProgramado3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblVueloProgramado3.setForeground(new java.awt.Color(203, 213, 225));
        lblVueloProgramado3.setText("PRIORIDAD REPORTE");

        grupoPrioridadFallas.add(rbtnPrioridadBaja);
        rbtnPrioridadBaja.setForeground(new java.awt.Color(74, 209, 80));
        rbtnPrioridadBaja.setText("Baja");
        rbtnPrioridadBaja.addActionListener(this::rbtnPrioridadBajaActionPerformed);

        grupoPrioridadFallas.add(rbtnPrioridadMedia);
        rbtnPrioridadMedia.setForeground(new java.awt.Color(251, 214, 36));
        rbtnPrioridadMedia.setText("Media");
        rbtnPrioridadMedia.addActionListener(this::rbtnPrioridadMediaActionPerformed);

        grupoPrioridadFallas.add(rbtnPrioridadAlta);
        rbtnPrioridadAlta.setForeground(new java.awt.Color(251, 113, 133));
        rbtnPrioridadAlta.setText("Alta");
        rbtnPrioridadAlta.addActionListener(this::rbtnPrioridadAltaActionPerformed);

        txtCombustibleRestante.setBackground(new java.awt.Color(15, 23, 42));
        txtCombustibleRestante.setForeground(new java.awt.Color(255, 255, 255));
        txtCombustibleRestante.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCombustibleRestante.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        txtCombustibleRestante.addActionListener(this::txtCombustibleRestanteActionPerformed);

        lblVueloProgramado4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblVueloProgramado4.setForeground(new java.awt.Color(203, 213, 225));
        lblVueloProgramado4.setText("FALLAS REPORTADAS");

        txtReporteFallas.setBackground(new java.awt.Color(15, 23, 42));
        txtReporteFallas.setColumns(20);
        txtReporteFallas.setForeground(new java.awt.Color(255, 255, 255));
        txtReporteFallas.setLineWrap(true);
        txtReporteFallas.setRows(5);
        txtReporteFallas.setWrapStyleWord(true);
        txtReporteFallas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        txtReporteFallas.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ScrollTxtReporteFallas.setViewportView(txtReporteFallas);

        fondoBtnCancelarVuelo3.setBackground(new java.awt.Color(225, 29, 72));

        btnCerrarVuelo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCerrarVuelo.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarVuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCerrarVuelo.setText("Cerrar Vuelo");
        btnCerrarVuelo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarVuelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarVueloMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnCancelarVuelo3Layout = new javax.swing.GroupLayout(fondoBtnCancelarVuelo3);
        fondoBtnCancelarVuelo3.setLayout(fondoBtnCancelarVuelo3Layout);
        fondoBtnCancelarVuelo3Layout.setHorizontalGroup(
            fondoBtnCancelarVuelo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrarVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
        fondoBtnCancelarVuelo3Layout.setVerticalGroup(
            fondoBtnCancelarVuelo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrarVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        grupoPrioridadFallas.add(rbtnSinFallas);
        rbtnSinFallas.setForeground(new java.awt.Color(255, 255, 255));
        rbtnSinFallas.setSelected(true);
        rbtnSinFallas.setText("Sin Fallas");
        rbtnSinFallas.addActionListener(this::rbtnSinFallasActionPerformed);

        javax.swing.GroupLayout pnlLogBookLayout = new javax.swing.GroupLayout(pnlLogBook);
        pnlLogBook.setLayout(pnlLogBookLayout);
        pnlLogBookLayout.setHorizontalGroup(
            pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogBookLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLogBookLayout.createSequentialGroup()
                        .addComponent(fondoBtnCancelarVuelo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLogBookLayout.createSequentialGroup()
                        .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ScrollTxtReporteFallas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLogBookLayout.createSequentialGroup()
                                .addComponent(lblCreacionAsignacionVuelo2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlLogBookLayout.createSequentialGroup()
                                .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlLogBookLayout.createSequentialGroup()
                                        .addComponent(cbxVuelosLogbook, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(47, 47, 47))
                                    .addGroup(pnlLogBookLayout.createSequentialGroup()
                                        .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblVueloProgramado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblVueloProgramado3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                            .addGroup(pnlLogBookLayout.createSequentialGroup()
                                                .addComponent(rbtnPrioridadBaja)
                                                .addGap(18, 18, 18)
                                                .addComponent(rbtnPrioridadMedia)
                                                .addGap(18, 18, 18)
                                                .addComponent(rbtnPrioridadAlta))
                                            .addComponent(lblVueloProgramado4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblVueloProgramado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCombustibleRestante)
                                    .addComponent(rbtnSinFallas))))
                        .addGap(20, 20, 20))))
        );
        pnlLogBookLayout.setVerticalGroup(
            pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogBookLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblCreacionAsignacionVuelo2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVueloProgramado1)
                    .addComponent(lblVueloProgramado2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxVuelosLogbook, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCombustibleRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVueloProgramado3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLogBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnPrioridadBaja)
                    .addComponent(rbtnPrioridadMedia)
                    .addComponent(rbtnPrioridadAlta)
                    .addComponent(rbtnSinFallas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVueloProgramado4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollTxtReporteFallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fondoBtnCancelarVuelo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContenidoAsignacionLayout = new javax.swing.GroupLayout(pnlContenidoAsignacion);
        pnlContenidoAsignacion.setLayout(pnlContenidoAsignacionLayout);
        pnlContenidoAsignacionLayout.setHorizontalGroup(
            pnlContenidoAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoAsignacionLayout.createSequentialGroup()
                .addGroup(pnlContenidoAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlContenidoAsignacionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlContenidoAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlControlOOOI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlCreacionAsignacionVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlContenidoAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlLogBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlPendientesDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContenidoAsignacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fondoContadores2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        pnlContenidoAsignacionLayout.setVerticalGroup(
            pnlContenidoAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoAsignacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fondoContadores2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCreacionAsignacionVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPendientesDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContenidoAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlControlOOOI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLogBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        ScrollAsignacionVuelos.setViewportView(pnlContenidoAsignacion);

        javax.swing.GroupLayout pnlAsignacionCuerpoLayout = new javax.swing.GroupLayout(pnlAsignacionCuerpo);
        pnlAsignacionCuerpo.setLayout(pnlAsignacionCuerpoLayout);
        pnlAsignacionCuerpoLayout.setHorizontalGroup(
            pnlAsignacionCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScrollAsignacionVuelos, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        pnlAsignacionCuerpoLayout.setVerticalGroup(
            pnlAsignacionCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignacionCuerpoLayout.createSequentialGroup()
                .addComponent(panelEncabezado3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollAsignacionVuelos, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAsignacionLayout = new javax.swing.GroupLayout(pnlAsignacion);
        pnlAsignacion.setLayout(pnlAsignacionLayout);
        pnlAsignacionLayout.setHorizontalGroup(
            pnlAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAsignacionCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlAsignacionLayout.setVerticalGroup(
            pnlAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAsignacionCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlContenedorPrincipal.add(pnlAsignacion, "pnlAsignacion");

        pnlDespachoCuerpo.setBackground(new java.awt.Color(15, 23, 42));
        pnlDespachoCuerpo.setLayout(new java.awt.CardLayout());

        pnlVacio.setBackground(new java.awt.Color(15, 23, 42));

        panelEncabezado.setBackground(new java.awt.Color(15, 23, 42));
        panelEncabezado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Despacho De Vuelo");

        jLabel5.setForeground(new java.awt.Color(148, 163, 184));
        jLabel5.setText("Sistema de control en tierra ");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        fondoContadores.setBackground(new java.awt.Color(15, 23, 42));

        jPanel6.setBackground(new java.awt.Color(245, 158, 11));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorPendientesDemora.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorPendientesDemora.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorPendientesDemora.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorPendientesDemora.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorPendientesDemora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorPendientesDemora.setText("0");

        lblPendientesDemora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPendientesDemora.setForeground(new java.awt.Color(148, 163, 175));
        lblPendientesDemora.setText("Pendientes/Demora ");

        javax.swing.GroupLayout pnlContadorPendientesDemoraLayout = new javax.swing.GroupLayout(pnlContadorPendientesDemora);
        pnlContadorPendientesDemora.setLayout(pnlContadorPendientesDemoraLayout);
        pnlContadorPendientesDemoraLayout.setHorizontalGroup(
            pnlContadorPendientesDemoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorPendientesDemoraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorPendientesDemoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorPendientesDemoraLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblPendientesDemora, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorPendientesDemora, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorPendientesDemoraLayout.setVerticalGroup(
            pnlContadorPendientesDemoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorPendientesDemoraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorPendientesDemora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPendientesDemora)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorPendientesDemora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorPendientesDemora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorAprobados.setBackground(new java.awt.Color(34, 197, 94));
        pnlColorAprobados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorAprobados.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorAprobados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorAprobados.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorAprobados.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorAprobados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorAprobados.setText("0");

        lblAprobados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAprobados.setForeground(new java.awt.Color(148, 163, 175));
        lblAprobados.setText("Aprobados");

        javax.swing.GroupLayout pnlContadorAprobadosLayout = new javax.swing.GroupLayout(pnlContadorAprobados);
        pnlContadorAprobados.setLayout(pnlContadorAprobadosLayout);
        pnlContadorAprobadosLayout.setHorizontalGroup(
            pnlContadorAprobadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorAprobadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorAprobadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorAprobadosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblAprobados, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorAprobados, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorAprobadosLayout.setVerticalGroup(
            pnlContadorAprobadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorAprobadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorAprobados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAprobados)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorAprobadosLayout = new javax.swing.GroupLayout(pnlColorAprobados);
        pnlColorAprobados.setLayout(pnlColorAprobadosLayout);
        pnlColorAprobadosLayout.setHorizontalGroup(
            pnlColorAprobadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorAprobadosLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorAprobados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorAprobadosLayout.setVerticalGroup(
            pnlColorAprobadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorAprobados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorCancelados.setBackground(new java.awt.Color(219, 29, 72));
        pnlColorCancelados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorCancelados.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorCancelados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorCancelados.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorCancelados.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorCancelados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorCancelados.setText("0");

        lblCancelados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCancelados.setForeground(new java.awt.Color(148, 163, 175));
        lblCancelados.setText("Cancelados");

        javax.swing.GroupLayout pnlContadorCanceladosLayout = new javax.swing.GroupLayout(pnlContadorCancelados);
        pnlContadorCancelados.setLayout(pnlContadorCanceladosLayout);
        pnlContadorCanceladosLayout.setHorizontalGroup(
            pnlContadorCanceladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorCanceladosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorCanceladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorCanceladosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblCancelados, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorCancelados, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorCanceladosLayout.setVerticalGroup(
            pnlContadorCanceladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorCanceladosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorCancelados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCancelados)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorCanceladosLayout = new javax.swing.GroupLayout(pnlColorCancelados);
        pnlColorCancelados.setLayout(pnlColorCanceladosLayout);
        pnlColorCanceladosLayout.setHorizontalGroup(
            pnlColorCanceladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorCanceladosLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorCancelados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorCanceladosLayout.setVerticalGroup(
            pnlColorCanceladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorCancelados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorModuloActivo.setBackground(new java.awt.Color(58, 128, 241));
        pnlColorModuloActivo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorModuloActivo.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorModuloActivo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblModuloActivo.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        lblModuloActivo.setForeground(new java.awt.Color(255, 255, 255));
        lblModuloActivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModuloActivo.setText("DESPACHO VUELO");

        lblModuloactivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblModuloactivo.setForeground(new java.awt.Color(148, 163, 175));
        lblModuloactivo.setText("Modulo Activo");

        javax.swing.GroupLayout pnlContadorModuloActivoLayout = new javax.swing.GroupLayout(pnlContadorModuloActivo);
        pnlContadorModuloActivo.setLayout(pnlContadorModuloActivoLayout);
        pnlContadorModuloActivoLayout.setHorizontalGroup(
            pnlContadorModuloActivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorModuloActivoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblModuloactivo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(pnlContadorModuloActivoLayout.createSequentialGroup()
                .addComponent(lblModuloActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContadorModuloActivoLayout.setVerticalGroup(
            pnlContadorModuloActivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorModuloActivoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModuloActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModuloactivo)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorModuloActivoLayout = new javax.swing.GroupLayout(pnlColorModuloActivo);
        pnlColorModuloActivo.setLayout(pnlColorModuloActivoLayout);
        pnlColorModuloActivoLayout.setHorizontalGroup(
            pnlColorModuloActivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorModuloActivoLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorModuloActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorModuloActivoLayout.setVerticalGroup(
            pnlColorModuloActivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorModuloActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout fondoContadoresLayout = new javax.swing.GroupLayout(fondoContadores);
        fondoContadores.setLayout(fondoContadoresLayout);
        fondoContadoresLayout.setHorizontalGroup(
            fondoContadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoContadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorAprobados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorCancelados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorModuloActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fondoContadoresLayout.setVerticalGroup(
            fondoContadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoContadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoContadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlColorModuloActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColorCancelados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColorAprobados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlVacio1.setBackground(new java.awt.Color(30, 41, 59));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(148, 163, 184));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sin vuelos pendientes de despacho");

        lblDespachoTecnico1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDespachoTecnico1.setForeground(new java.awt.Color(203, 213, 225));
        lblDespachoTecnico1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDespachoTecnico1.setText("DESPACHO TÉCNICO (WEIGHT & BALANCE) ");

        javax.swing.GroupLayout pnlVacio1Layout = new javax.swing.GroupLayout(pnlVacio1);
        pnlVacio1.setLayout(pnlVacio1Layout);
        pnlVacio1Layout.setHorizontalGroup(
            pnlVacio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacio1Layout.createSequentialGroup()
                .addGroup(pnlVacio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVacio1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblDespachoTecnico1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVacio1Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        pnlVacio1Layout.setVerticalGroup(
            pnlVacio1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacio1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblDespachoTecnico1)
                .addGap(71, 71, 71)
                .addComponent(jLabel1)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlVacioLayout = new javax.swing.GroupLayout(pnlVacio);
        pnlVacio.setLayout(pnlVacioLayout);
        pnlVacioLayout.setHorizontalGroup(
            pnlVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVacioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(fondoContadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlVacio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlVacioLayout.setVerticalGroup(
            pnlVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacioLayout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fondoContadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlVacio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 589, Short.MAX_VALUE))
        );

        pnlDespachoCuerpo.add(pnlVacio, "card2");

        pnlLleno.setBackground(new java.awt.Color(15, 23, 42));

        pnlContenidoDespacho.setBackground(new java.awt.Color(30, 41, 59));
        pnlContenidoDespacho.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblDespachoTecnico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDespachoTecnico.setForeground(new java.awt.Color(203, 213, 225));
        lblDespachoTecnico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDespachoTecnico.setText("DESPACHO TÉCNICO (WEIGHT & BALANCE) ");

        pnlFondoResumenRuta.setBackground(new java.awt.Color(30, 41, 59));
        pnlFondoResumenRuta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblResumenRuta.setBackground(new java.awt.Color(30, 41, 59));
        lblResumenRuta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblResumenRuta.setText("<html><font color='#94a3b8'>Ruta:</font>  <b>LA440 — CUZ → JUL </b <font color='#94a3b8'>Aeronave:</font>  <b>OB-2200 (A320neo) </b <font color='#94a3b8'>CAP:</font> <b>Cdt. Ana Vargas </b <font color='#94a3b8'>TCP Jefe:</font>  <b>Diego Pinto </b </html>");

        javax.swing.GroupLayout pnlFondoResumenRutaLayout = new javax.swing.GroupLayout(pnlFondoResumenRuta);
        pnlFondoResumenRuta.setLayout(pnlFondoResumenRutaLayout);
        pnlFondoResumenRutaLayout.setHorizontalGroup(
            pnlFondoResumenRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoResumenRutaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResumenRuta)
                .addContainerGap())
        );
        pnlFondoResumenRutaLayout.setVerticalGroup(
            pnlFondoResumenRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoResumenRutaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResumenRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblRequerimientosTecnicos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRequerimientosTecnicos.setForeground(new java.awt.Color(203, 213, 225));
        lblRequerimientosTecnicos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRequerimientosTecnicos.setText("REQUERIMIENTOS TÉCNICOS Y CLIMA");

        lblCombRuta.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCombRuta.setForeground(new java.awt.Color(203, 213, 225));
        lblCombRuta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCombRuta.setText("COMB. RUTA (KG) ");

        pnlFondoTxtArea.setBackground(new java.awt.Color(15, 23, 42));

        txtAreaClima.setBackground(new java.awt.Color(15, 23, 42));
        txtAreaClima.setColumns(20);
        txtAreaClima.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtAreaClima.setLineWrap(true);
        txtAreaClima.setRows(5);
        txtAreaClima.setText("METAR SPZO 091200Z 28004KT 9999 FEW050 SCT100 15/02 Q1031");
        txtAreaClima.setWrapStyleWord(true);
        ScrollTxtAreaClima.setViewportView(txtAreaClima);

        javax.swing.GroupLayout pnlFondoTxtAreaLayout = new javax.swing.GroupLayout(pnlFondoTxtArea);
        pnlFondoTxtArea.setLayout(pnlFondoTxtAreaLayout);
        pnlFondoTxtAreaLayout.setHorizontalGroup(
            pnlFondoTxtAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollTxtAreaClima)
        );
        pnlFondoTxtAreaLayout.setVerticalGroup(
            pnlFondoTxtAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollTxtAreaClima, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );

        lblClimaDestino.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblClimaDestino.setForeground(new java.awt.Color(203, 213, 225));
        lblClimaDestino.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClimaDestino.setText("Clima Destino (METAR) ");

        lblCombReserva.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCombReserva.setForeground(new java.awt.Color(203, 213, 225));
        lblCombReserva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCombReserva.setText("COMB. RESERVA (KG) ");

        txtFieldCombReserva.setBackground(new java.awt.Color(15, 23, 42));
        txtFieldCombReserva.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldCombReserva.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        txtFieldEquipaje.setBackground(new java.awt.Color(15, 23, 42));
        txtFieldEquipaje.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldEquipaje.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblSumatoriaPesos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSumatoriaPesos.setForeground(new java.awt.Color(203, 213, 225));
        lblSumatoriaPesos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSumatoriaPesos.setText("SUMATORIA DE PESOS");

        lblPasajerosKG.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPasajerosKG.setForeground(new java.awt.Color(203, 213, 225));
        lblPasajerosKG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPasajerosKG.setText("PASAJEROS (KG)");

        txtFieldPasajeros.setBackground(new java.awt.Color(15, 23, 42));
        txtFieldPasajeros.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldPasajeros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblEquipajeKG.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblEquipajeKG.setForeground(new java.awt.Color(203, 213, 225));
        lblEquipajeKG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipajeKG.setText("EQUIPAJE (KG)");

        txtFieldCarga.setBackground(new java.awt.Color(15, 23, 42));
        txtFieldCarga.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldCarga.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblCargaKG.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCargaKG.setForeground(new java.awt.Color(203, 213, 225));
        lblCargaKG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCargaKG.setText("CARGA (KG)");

        lblCombustibleKGSuma.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCombustibleKGSuma.setForeground(new java.awt.Color(203, 213, 225));
        lblCombustibleKGSuma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCombustibleKGSuma.setText("COMBUSTIBLE (KG) (SUMA)");

        txtFieldCombustible.setEditable(false);
        txtFieldCombustible.setBackground(new java.awt.Color(15, 23, 42));
        txtFieldCombustible.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldCombustible.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        txtFieldCombRuta.setBackground(new java.awt.Color(15, 23, 42));
        txtFieldCombRuta.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldCombRuta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        pnlFondoCalculoMTOW.setBackground(new java.awt.Color(15, 23, 42));

        lblSumaTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSumaTotal.setForeground(new java.awt.Color(203, 213, 225));
        lblSumaTotal.setText("00.000");

        lblSlash.setForeground(new java.awt.Color(148, 163, 175));
        lblSlash.setText("/");

        lblPesoMaximo.setForeground(new java.awt.Color(148, 163, 175));
        lblPesoMaximo.setText("00.000 KG");

        barraMTOW.setBackground(new java.awt.Color(30, 41, 59));
        barraMTOW.setForeground(new java.awt.Color(34, 197, 94));
        barraMTOW.setString("");

        pnlFondoAprobado.setBackground(new java.awt.Color(5, 46, 22));
        pnlFondoAprobado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 46, 22), 1, true));

        lblAprobado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAprobado.setForeground(new java.awt.Color(34, 197, 94));
        lblAprobado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAprobado.setText("APROBADO");
        lblAprobado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 46, 22), 1, true));

        javax.swing.GroupLayout pnlFondoAprobadoLayout = new javax.swing.GroupLayout(pnlFondoAprobado);
        pnlFondoAprobado.setLayout(pnlFondoAprobadoLayout);
        pnlFondoAprobadoLayout.setHorizontalGroup(
            pnlFondoAprobadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAprobado, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        pnlFondoAprobadoLayout.setVerticalGroup(
            pnlFondoAprobadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAprobado, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        lblPorcentajeMTOW.setForeground(new java.awt.Color(148, 163, 175));
        lblPorcentajeMTOW.setText("00% del MTOW");

        javax.swing.GroupLayout pnlFondoCalculoMTOWLayout = new javax.swing.GroupLayout(pnlFondoCalculoMTOW);
        pnlFondoCalculoMTOW.setLayout(pnlFondoCalculoMTOWLayout);
        pnlFondoCalculoMTOWLayout.setHorizontalGroup(
            pnlFondoCalculoMTOWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoCalculoMTOWLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlFondoCalculoMTOWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraMTOW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFondoCalculoMTOWLayout.createSequentialGroup()
                        .addComponent(lblSumaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSlash, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPesoMaximo, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                        .addGap(116, 116, 116)
                        .addComponent(pnlFondoAprobado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFondoCalculoMTOWLayout.createSequentialGroup()
                        .addComponent(lblPorcentajeMTOW, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        pnlFondoCalculoMTOWLayout.setVerticalGroup(
            pnlFondoCalculoMTOWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoCalculoMTOWLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlFondoCalculoMTOWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFondoCalculoMTOWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSumaTotal)
                        .addComponent(lblSlash)
                        .addComponent(lblPesoMaximo))
                    .addComponent(pnlFondoAprobado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(barraMTOW, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPorcentajeMTOW)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        fondoBtnAprobarPlan.setBackground(new java.awt.Color(22, 101, 52));

        btnAprobarPlan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAprobarPlan.setForeground(new java.awt.Color(255, 255, 255));
        btnAprobarPlan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAprobarPlan.setText("Aprobar Plan De Vuelo");
        btnAprobarPlan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAprobarPlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAprobarPlanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnAprobarPlanLayout = new javax.swing.GroupLayout(fondoBtnAprobarPlan);
        fondoBtnAprobarPlan.setLayout(fondoBtnAprobarPlanLayout);
        fondoBtnAprobarPlanLayout.setHorizontalGroup(
            fondoBtnAprobarPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAprobarPlan, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );
        fondoBtnAprobarPlanLayout.setVerticalGroup(
            fondoBtnAprobarPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAprobarPlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fondoBtnDeclararDemora.setBackground(new java.awt.Color(177, 107, 3));
        fondoBtnDeclararDemora.setForeground(new java.awt.Color(255, 255, 255));

        btnDeclararDemora.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDeclararDemora.setForeground(new java.awt.Color(255, 255, 255));
        btnDeclararDemora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDeclararDemora.setText("Declarar Demora");
        btnDeclararDemora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeclararDemora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeclararDemoraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnDeclararDemoraLayout = new javax.swing.GroupLayout(fondoBtnDeclararDemora);
        fondoBtnDeclararDemora.setLayout(fondoBtnDeclararDemoraLayout);
        fondoBtnDeclararDemoraLayout.setHorizontalGroup(
            fondoBtnDeclararDemoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDeclararDemora, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
        fondoBtnDeclararDemoraLayout.setVerticalGroup(
            fondoBtnDeclararDemoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDeclararDemora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fondoBtnCancelarVuelo.setBackground(new java.awt.Color(102, 0, 0));

        btnCancelarVuelo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCancelarVuelo.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarVuelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelarVuelo.setText("Cancelar Vuelo");
        btnCancelarVuelo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarVuelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarVueloMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnCancelarVueloLayout = new javax.swing.GroupLayout(fondoBtnCancelarVuelo);
        fondoBtnCancelarVuelo.setLayout(fondoBtnCancelarVueloLayout);
        fondoBtnCancelarVueloLayout.setHorizontalGroup(
            fondoBtnCancelarVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCancelarVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
        fondoBtnCancelarVueloLayout.setVerticalGroup(
            fondoBtnCancelarVueloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCancelarVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        cbxVuelosDespacho.setBackground(new java.awt.Color(15, 23, 42));
        cbxVuelosDespacho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxVuelosDespacho.setForeground(new java.awt.Color(255, 255, 255));
        cbxVuelosDespacho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LA801 | OB-1995" }));
        cbxVuelosDespacho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxVuelosDespacho.addActionListener(this::cbxVuelosDespachoActionPerformed);

        javax.swing.GroupLayout pnlContenidoDespachoLayout = new javax.swing.GroupLayout(pnlContenidoDespacho);
        pnlContenidoDespacho.setLayout(pnlContenidoDespachoLayout);
        pnlContenidoDespachoLayout.setHorizontalGroup(
            pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoDespachoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                        .addComponent(lblDespachoTecnico, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                        .addGap(504, 504, 504)
                        .addComponent(cbxVuelosDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlFondoResumenRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                .addComponent(lblRequerimientosTecnicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(277, 277, 277))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoDespachoLayout.createSequentialGroup()
                                .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                        .addComponent(lblClimaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGap(321, 321, 321))
                                    .addComponent(pnlFondoTxtArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoDespachoLayout.createSequentialGroup()
                                                .addComponent(txtFieldCombRuta)
                                                .addGap(100, 100, 100))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoDespachoLayout.createSequentialGroup()
                                                .addComponent(lblCombRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(185, 185, 185)))
                                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                                .addComponent(lblCombReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(64, 64, 64))
                                            .addComponent(txtFieldCombReserva))))
                                .addGap(27, 27, 27)))
                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                .addComponent(lblCargaKG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(201, 201, 201)
                                .addComponent(lblCombustibleKGSuma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(55, 55, 55))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoDespachoLayout.createSequentialGroup()
                                .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                        .addComponent(txtFieldPasajeros)
                                        .addGap(50, 50, 50))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoDespachoLayout.createSequentialGroup()
                                        .addComponent(txtFieldCarga)
                                        .addGap(50, 50, 50))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoDespachoLayout.createSequentialGroup()
                                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                                .addComponent(lblPasajerosKG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(49, 49, 49))
                                            .addComponent(lblSumatoriaPesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(124, 124, 124)))
                                .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                        .addComponent(lblEquipajeKG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(134, 134, 134))
                                    .addComponent(txtFieldEquipaje)
                                    .addComponent(txtFieldCombustible)))
                            .addComponent(pnlFondoCalculoMTOW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                                .addComponent(fondoBtnCancelarVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fondoBtnDeclararDemora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fondoBtnAprobarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18))
        );
        pnlContenidoDespachoLayout.setVerticalGroup(
            pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDespachoTecnico)
                    .addComponent(cbxVuelosDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlFondoResumenRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                        .addComponent(lblRequerimientosTecnicos)
                        .addGap(12, 12, 12)
                        .addComponent(lblClimaDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlFondoTxtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCombRuta)
                            .addComponent(lblCombReserva)))
                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                        .addComponent(lblSumatoriaPesos)
                        .addGap(12, 12, 12)
                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPasajerosKG)
                            .addComponent(lblEquipajeKG))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldEquipaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldPasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCargaKG)
                            .addComponent(lblCombustibleKGSuma))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldCombustible, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoDespachoLayout.createSequentialGroup()
                        .addComponent(pnlFondoCalculoMTOW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fondoBtnCancelarVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fondoBtnDeclararDemora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fondoBtnAprobarPlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlContenidoDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFieldCombRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFieldCombReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        fondoContadores1.setBackground(new java.awt.Color(15, 23, 42));

        jPanel7.setBackground(new java.awt.Color(245, 158, 11));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorPendientesDemora1.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorPendientesDemora1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorPendientesDemora1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorPendientesDemora1.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorPendientesDemora1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorPendientesDemora1.setText("0");

        lblPendientesDemora1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPendientesDemora1.setForeground(new java.awt.Color(148, 163, 175));
        lblPendientesDemora1.setText("Pendientes/Demora ");

        javax.swing.GroupLayout pnlContadorPendientesDemora1Layout = new javax.swing.GroupLayout(pnlContadorPendientesDemora1);
        pnlContadorPendientesDemora1.setLayout(pnlContadorPendientesDemora1Layout);
        pnlContadorPendientesDemora1Layout.setHorizontalGroup(
            pnlContadorPendientesDemora1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorPendientesDemora1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorPendientesDemora1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorPendientesDemora1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblPendientesDemora1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorPendientesDemora1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorPendientesDemora1Layout.setVerticalGroup(
            pnlContadorPendientesDemora1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorPendientesDemora1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorPendientesDemora1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPendientesDemora1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorPendientesDemora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorPendientesDemora1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorAprobados1.setBackground(new java.awt.Color(34, 197, 94));
        pnlColorAprobados1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorAprobados1.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorAprobados1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorAprobados1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorAprobados1.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorAprobados1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorAprobados1.setText("0");

        lblAprobados1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAprobados1.setForeground(new java.awt.Color(148, 163, 175));
        lblAprobados1.setText("Aprobados");

        javax.swing.GroupLayout pnlContadorAprobados1Layout = new javax.swing.GroupLayout(pnlContadorAprobados1);
        pnlContadorAprobados1.setLayout(pnlContadorAprobados1Layout);
        pnlContadorAprobados1Layout.setHorizontalGroup(
            pnlContadorAprobados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorAprobados1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorAprobados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorAprobados1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblAprobados1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorAprobados1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorAprobados1Layout.setVerticalGroup(
            pnlContadorAprobados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorAprobados1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorAprobados1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAprobados1)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorAprobados1Layout = new javax.swing.GroupLayout(pnlColorAprobados1);
        pnlColorAprobados1.setLayout(pnlColorAprobados1Layout);
        pnlColorAprobados1Layout.setHorizontalGroup(
            pnlColorAprobados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorAprobados1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorAprobados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorAprobados1Layout.setVerticalGroup(
            pnlColorAprobados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorAprobados1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorCancelados1.setBackground(new java.awt.Color(219, 29, 72));
        pnlColorCancelados1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorCancelados1.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorCancelados1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblContadorCancelados1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContadorCancelados1.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorCancelados1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorCancelados1.setText("0");

        lblCancelados1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCancelados1.setForeground(new java.awt.Color(148, 163, 175));
        lblCancelados1.setText("Cancelados");

        javax.swing.GroupLayout pnlContadorCancelados1Layout = new javax.swing.GroupLayout(pnlContadorCancelados1);
        pnlContadorCancelados1.setLayout(pnlContadorCancelados1Layout);
        pnlContadorCancelados1Layout.setHorizontalGroup(
            pnlContadorCancelados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorCancelados1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContadorCancelados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContadorCancelados1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblCancelados1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContadorCancelados1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pnlContadorCancelados1Layout.setVerticalGroup(
            pnlContadorCancelados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorCancelados1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContadorCancelados1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCancelados1)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorCancelados1Layout = new javax.swing.GroupLayout(pnlColorCancelados1);
        pnlColorCancelados1.setLayout(pnlColorCancelados1Layout);
        pnlColorCancelados1Layout.setHorizontalGroup(
            pnlColorCancelados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorCancelados1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorCancelados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorCancelados1Layout.setVerticalGroup(
            pnlColorCancelados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorCancelados1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlColorModuloActivo1.setBackground(new java.awt.Color(58, 128, 241));
        pnlColorModuloActivo1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        pnlContadorModuloActivo1.setBackground(new java.awt.Color(30, 41, 59));
        pnlContadorModuloActivo1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));

        lblModuloActivo1.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        lblModuloActivo1.setForeground(new java.awt.Color(255, 255, 255));
        lblModuloActivo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModuloActivo1.setText("DESPACHO VUELO");

        lblModuloactivo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblModuloactivo1.setForeground(new java.awt.Color(148, 163, 175));
        lblModuloactivo1.setText("Modulo Activo");

        javax.swing.GroupLayout pnlContadorModuloActivo1Layout = new javax.swing.GroupLayout(pnlContadorModuloActivo1);
        pnlContadorModuloActivo1.setLayout(pnlContadorModuloActivo1Layout);
        pnlContadorModuloActivo1Layout.setHorizontalGroup(
            pnlContadorModuloActivo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorModuloActivo1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblModuloactivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(pnlContadorModuloActivo1Layout.createSequentialGroup()
                .addComponent(lblModuloActivo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContadorModuloActivo1Layout.setVerticalGroup(
            pnlContadorModuloActivo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContadorModuloActivo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModuloActivo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModuloactivo1)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlColorModuloActivo1Layout = new javax.swing.GroupLayout(pnlColorModuloActivo1);
        pnlColorModuloActivo1.setLayout(pnlColorModuloActivo1Layout);
        pnlColorModuloActivo1Layout.setHorizontalGroup(
            pnlColorModuloActivo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColorModuloActivo1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pnlContadorModuloActivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlColorModuloActivo1Layout.setVerticalGroup(
            pnlColorModuloActivo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContadorModuloActivo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout fondoContadores1Layout = new javax.swing.GroupLayout(fondoContadores1);
        fondoContadores1.setLayout(fondoContadores1Layout);
        fondoContadores1Layout.setHorizontalGroup(
            fondoContadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoContadores1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorAprobados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorCancelados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlColorModuloActivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fondoContadores1Layout.setVerticalGroup(
            fondoContadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoContadores1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoContadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlColorModuloActivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColorCancelados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColorAprobados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelEncabezado4.setBackground(new java.awt.Color(15, 23, 42));
        panelEncabezado4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Despacho De Vuelo");

        jLabel7.setForeground(new java.awt.Color(148, 163, 184));
        jLabel7.setText("Sistema de control en tierra ");

        javax.swing.GroupLayout panelEncabezado4Layout = new javax.swing.GroupLayout(panelEncabezado4);
        panelEncabezado4.setLayout(panelEncabezado4Layout);
        panelEncabezado4Layout.setHorizontalGroup(
            panelEncabezado4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelEncabezado4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addContainerGap(933, Short.MAX_VALUE))
        );
        panelEncabezado4Layout.setVerticalGroup(
            panelEncabezado4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLlenoLayout = new javax.swing.GroupLayout(pnlLleno);
        pnlLleno.setLayout(pnlLlenoLayout);
        pnlLlenoLayout.setHorizontalGroup(
            pnlLlenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fondoContadores1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLlenoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(pnlContenidoDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        pnlLlenoLayout.setVerticalGroup(
            pnlLlenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLlenoLayout.createSequentialGroup()
                .addComponent(panelEncabezado4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fondoContadores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlContenidoDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 340, Short.MAX_VALUE))
        );

        pnlDespachoCuerpo.add(pnlLleno, "card3");

        javax.swing.GroupLayout pnlDespachoLayout = new javax.swing.GroupLayout(pnlDespacho);
        pnlDespacho.setLayout(pnlDespachoLayout);
        pnlDespachoLayout.setHorizontalGroup(
            pnlDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDespachoCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDespachoLayout.setVerticalGroup(
            pnlDespachoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDespachoCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlContenedorPrincipal.add(pnlDespacho, "pnlDespacho");

        pnlHistorial.setBackground(new java.awt.Color(15, 23, 42));

        pnlHistorialCuerpo.setBackground(new java.awt.Color(15, 23, 42));

        panelEncabezado5.setBackground(new java.awt.Color(15, 23, 42));
        panelEncabezado5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Historial De Vuelos");

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
        lblHistorialVuelo.setText("HISTORIAL DE VUELOS Y ESTADO OOOI EN VIVO ");

        lblFiltroVuelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroVuelo.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroVuelo.setText("VUELO:");

        cbxFiltroVuelo.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroVuelo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroVuelo.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroVuelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "LA800" }));
        cbxFiltroVuelo.setToolTipText("");
        cbxFiltroVuelo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroVuelo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroVuelo.addActionListener(this::cbxFiltroVueloActionPerformed);

        lblFiltroAeronave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroAeronave.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroAeronave.setText("AERONAVE:");

        cbxFiltroAeronave.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroAeronave.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroAeronave.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroAeronave.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "OB-2101" }));
        cbxFiltroAeronave.setToolTipText("");
        cbxFiltroAeronave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroAeronave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroAeronave.addActionListener(this::cbxFiltroAeronaveActionPerformed);

        cbxFiltroEstadoVuelo.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroEstadoVuelo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroEstadoVuelo.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroEstadoVuelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "COMPLETADO" }));
        cbxFiltroEstadoVuelo.setToolTipText("");
        cbxFiltroEstadoVuelo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroEstadoVuelo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroEstadoVuelo.addActionListener(this::cbxFiltroEstadoVueloActionPerformed);

        lblFiltroEstadoVuelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroEstadoVuelo.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroEstadoVuelo.setText("ESTADO VUELO:");

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
            .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlFiltrosHistorialLayout = new javax.swing.GroupLayout(pnlFiltrosHistorial);
        pnlFiltrosHistorial.setLayout(pnlFiltrosHistorialLayout);
        pnlFiltrosHistorialLayout.setHorizontalGroup(
            pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                        .addComponent(lblHistorialVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltroVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxFiltroVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltroAeronave, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxFiltroAeronave, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                                .addComponent(lblFiltroEstadoVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(218, 218, 218))
                            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                                .addComponent(cbxFiltroEstadoVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fondoBtnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))))))
        );
        pnlFiltrosHistorialLayout.setVerticalGroup(
            pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblHistorialVuelo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                        .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                                .addComponent(lblFiltroAeronave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxFiltroAeronave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                                .addComponent(lblFiltroEstadoVuelo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlFiltrosHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fondoBtnLimpiarFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxFiltroEstadoVuelo, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlFiltrosHistorialLayout.createSequentialGroup()
                        .addComponent(lblFiltroVuelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxFiltroVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );

        pnlTablaDinamicaHistorial.setBackground(new java.awt.Color(15, 23, 42));
        pnlTablaDinamicaHistorial.setLayout(new java.awt.BorderLayout());

        TblHistorialVuelo.setBackground(new java.awt.Color(30, 41, 59));
        TblHistorialVuelo.setForeground(new java.awt.Color(30, 41, 59));
        TblHistorialVuelo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VUELO", "RUTA", "AERONAVE", "CAPITÁN", "JEFE CABINA", "ESTADO", "OUT", "OFF", "ON", "IN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScrollTablaHistorialVuelos.setViewportView(TblHistorialVuelo);

        pnlTablaDinamicaHistorial.add(ScrollTablaHistorialVuelos, java.awt.BorderLayout.NORTH);

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

        javax.swing.GroupLayout pnlHistorialLayout = new javax.swing.GroupLayout(pnlHistorial);
        pnlHistorial.setLayout(pnlHistorialLayout);
        pnlHistorialLayout.setHorizontalGroup(
            pnlHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHistorialCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlHistorialLayout.setVerticalGroup(
            pnlHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHistorialCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlContenedorPrincipal.add(pnlHistorial, "pnlHistorial");

        pnlFlota.setBackground(new java.awt.Color(51, 51, 0));

        pnlFlotaCuerpo.setBackground(new java.awt.Color(15, 23, 42));

        panelEncabezado6.setBackground(new java.awt.Color(15, 23, 42));
        panelEncabezado6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Gestion De Flota");

        jLabel13.setForeground(new java.awt.Color(148, 163, 184));
        jLabel13.setText("Sistema de control en tierra ");

        javax.swing.GroupLayout panelEncabezado6Layout = new javax.swing.GroupLayout(panelEncabezado6);
        panelEncabezado6.setLayout(panelEncabezado6Layout);
        panelEncabezado6Layout.setHorizontalGroup(
            panelEncabezado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelEncabezado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10))
                .addContainerGap(935, Short.MAX_VALUE))
        );
        panelEncabezado6Layout.setVerticalGroup(
            panelEncabezado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlFiltrosFlota.setBackground(new java.awt.Color(30, 41, 59));
        pnlFiltrosFlota.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblRegistroFlota.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblRegistroFlota.setForeground(new java.awt.Color(203, 213, 225));
        lblRegistroFlota.setText("REGISTRO DE FLOTA BASE");

        lblFiltroMatricula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroMatricula.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroMatricula.setText("MATRÍCULA:");

        cbxFiltroMatricula.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroMatricula.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroMatricula.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroMatricula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "OB-2200" }));
        cbxFiltroMatricula.setToolTipText("");
        cbxFiltroMatricula.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroMatricula.addActionListener(this::cbxFiltroMatriculaActionPerformed);

        lblFiltroModelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroModelo.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroModelo.setText("MODELO:");

        cbxFiltroModelo.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroModelo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroModelo.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "A320neo" }));
        cbxFiltroModelo.setToolTipText("");
        cbxFiltroModelo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroModelo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroModelo.addActionListener(this::cbxFiltroModeloActionPerformed);

        cbxFiltroEstadoAeronave.setBackground(new java.awt.Color(15, 23, 42));
        cbxFiltroEstadoAeronave.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxFiltroEstadoAeronave.setForeground(new java.awt.Color(255, 255, 255));
        cbxFiltroEstadoAeronave.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "APTO", "MANTENIMIENTO" }));
        cbxFiltroEstadoAeronave.setToolTipText("");
        cbxFiltroEstadoAeronave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));
        cbxFiltroEstadoAeronave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFiltroEstadoAeronave.addActionListener(this::cbxFiltroEstadoAeronaveActionPerformed);

        lblFiltroEstadoAeronave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroEstadoAeronave.setForeground(new java.awt.Color(203, 213, 225));
        lblFiltroEstadoAeronave.setText("ESTADO AERONAVE:");

        fondoBtnLimpiarFiltrosFlota.setBackground(new java.awt.Color(225, 29, 72));

        btnLimpiarFiltrosFlota.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiarFiltrosFlota.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarFiltrosFlota.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLimpiarFiltrosFlota.setText("LIMPIAR");
        btnLimpiarFiltrosFlota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout fondoBtnLimpiarFiltrosFlotaLayout = new javax.swing.GroupLayout(fondoBtnLimpiarFiltrosFlota);
        fondoBtnLimpiarFiltrosFlota.setLayout(fondoBtnLimpiarFiltrosFlotaLayout);
        fondoBtnLimpiarFiltrosFlotaLayout.setHorizontalGroup(
            fondoBtnLimpiarFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLimpiarFiltrosFlota, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        fondoBtnLimpiarFiltrosFlotaLayout.setVerticalGroup(
            fondoBtnLimpiarFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLimpiarFiltrosFlota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlFiltrosFlotaLayout = new javax.swing.GroupLayout(pnlFiltrosFlota);
        pnlFiltrosFlota.setLayout(pnlFiltrosFlotaLayout);
        pnlFiltrosFlotaLayout.setHorizontalGroup(
            pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                        .addComponent(lblRegistroFlota, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                        .addGroup(pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltroMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxFiltroMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltroModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxFiltroModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                                .addComponent(lblFiltroEstadoAeronave, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(218, 218, 218))
                            .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                                .addComponent(cbxFiltroEstadoAeronave, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fondoBtnLimpiarFiltrosFlota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))))))
        );
        pnlFiltrosFlotaLayout.setVerticalGroup(
            pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblRegistroFlota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                        .addGroup(pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                                .addComponent(lblFiltroModelo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxFiltroModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                                .addComponent(lblFiltroEstadoAeronave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlFiltrosFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fondoBtnLimpiarFiltrosFlota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxFiltroEstadoAeronave, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlFiltrosFlotaLayout.createSequentialGroup()
                        .addComponent(lblFiltroMatricula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxFiltroMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );

        pnlTablaDinamicaFlota.setBackground(new java.awt.Color(15, 23, 42));
        pnlTablaDinamicaFlota.setLayout(new java.awt.BorderLayout());

        TblFlota.setBackground(new java.awt.Color(30, 41, 59));
        TblFlota.setForeground(new java.awt.Color(30, 41, 59));
        TblFlota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MATRÍCULA", "MODELO", "ESTADO", "ASIENTOS", "MTOW (KG)", "AUTONOMÍA (KM)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScrollTablaFlota.setViewportView(TblFlota);

        pnlTablaDinamicaFlota.add(ScrollTablaFlota, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout pnlFlotaCuerpoLayout = new javax.swing.GroupLayout(pnlFlotaCuerpo);
        pnlFlotaCuerpo.setLayout(pnlFlotaCuerpoLayout);
        pnlFlotaCuerpoLayout.setHorizontalGroup(
            pnlFlotaCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFlotaCuerpoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlFlotaCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTablaDinamicaFlota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFiltrosFlota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        pnlFlotaCuerpoLayout.setVerticalGroup(
            pnlFlotaCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFlotaCuerpoLayout.createSequentialGroup()
                .addComponent(panelEncabezado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlFiltrosFlota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTablaDinamicaFlota, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFlotaLayout = new javax.swing.GroupLayout(pnlFlota);
        pnlFlota.setLayout(pnlFlotaLayout);
        pnlFlotaLayout.setHorizontalGroup(
            pnlFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFlotaCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFlotaLayout.setVerticalGroup(
            pnlFlotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFlotaCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlContenedorPrincipal.add(pnlFlota, "pnlFlota");

        getContentPane().add(pnlContenedorPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSeleccionCAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeleccionCAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSeleccionCAPActionPerformed

    private void cbxSeleccionFOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeleccionFOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSeleccionFOActionPerformed

    private void cbxSeleccionVueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeleccionVueloActionPerformed
        if (cbxSeleccionVuelo.getSelectedIndex() <= 0) {
            // 1. Regresamos las cajas de pilotos a su estado limpio
            javax.swing.JComboBox comboCAP = cbxSeleccionCAP;
            javax.swing.JComboBox comboFO = cbxSeleccionFO;

            comboCAP.removeAllItems();
            comboCAP.addItem("— Seleccionar CAP —");

            comboFO.removeAllItems();
            comboFO.addItem("— Seleccionar FO —");

            // 2. Limpiamos por completo el panel de los TCPs y la lista global
            pnlContendorTCP.removeAll();
            pnlContendorTCP.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 30));

            javax.swing.JLabel lblTemporal = new javax.swing.JLabel("Seleccione un vuelo primero");
            lblTemporal.setForeground(new java.awt.Color(148, 163, 184));
            lblTemporal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            pnlContendorTCP.add(lblTemporal);

            tcpsSeleccionadosList.clear(); // Vaciamos los registros en memoria

            // 3. Reseteamos el título informativo superior
            lblTripulacionCabina.setText("TCP — TRIPULACIÓN DE CABINA");

            // 4. Forzamos a NetBeans a redibujar el cambio visual inmediatamente
            pnlContendorTCP.revalidate();
            pnlContendorTCP.repaint();
            return; // Detiene el método aquí para que no intente buscar en la BD
        }

        // 1. Obtenemos el vuelo que el usuario acaba de seleccionar
        Clases.VueloProgramado vueloSel = (Clases.VueloProgramado) cbxSeleccionVuelo.getSelectedItem();
        String modelo = vueloSel.getModeloAeronave();
        int asientos = vueloSel.getCapacidadAsientos();

        // 2. Calculamos los TCP requeridos (1 por cada 50 asientos)
        int tcpMinimos = (int) Math.ceil(asientos / 50.0);
        lblTripulacionCabina.setText("TCP — TRIPULACIÓN DE CABINA (Mínimo " + tcpMinimos + " requeridos)");

        ClasesDAO.DespachoDAO dao = new ClasesDAO.DespachoDAO();

        javax.swing.JComboBox comboCAP = cbxSeleccionCAP;
        javax.swing.JComboBox comboFO = cbxSeleccionFO;

        // 3. Cargamos Pilotos habilitados pasando los OBJETOS REALES
        comboCAP.removeAllItems();
        comboCAP.addItem("— Seleccionar CAP —");
        for (Clases.TripulanteVuelo cap : dao.obtenerPilotosHabilitados("Capitan", modelo)) {
            comboCAP.addItem(cap); // Sin el .toString()
        }

        comboFO.removeAllItems();
        comboFO.addItem("— Seleccionar FO —");
        for (Clases.TripulanteVuelo fo : dao.obtenerPilotosHabilitados("Primer Oficial", modelo)) {
            comboFO.addItem(fo); // Sin el .toString()
        }

        // 4. LA MAGIA: Usamos GridLayout (0 filas significa infinitas hacia abajo, 3 columnas máximas, 10px de separación)
        pnlContendorTCP.removeAll();
        pnlContendorTCP.setLayout(new java.awt.GridLayout(0, 5, 8, 8));
        pnlContendorTCP.setBackground(new java.awt.Color(30, 41, 59)); // Mantenemos el fondo del panel
        tcpsSeleccionadosList.clear(); 

        // 5. Generamos el "BotonChipTCP" personalizado por cada TCP
        java.util.List<Clases.TripulanteCabina> tcpsHabilitados = dao.obtenerTCPsHabilitados(modelo);
        
        if (tcpsHabilitados.isEmpty()) {
            // SI NO HAY TCP: Cambiamos a FlowLayout centrado para que el mensaje no se deforme
            pnlContendorTCP.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 30));
            
            // Creamos el mensaje con terminología aeronáutica
            javax.swing.JLabel lblNoDisponibles = new javax.swing.JLabel("Sin Tripulantes de Cabina habilitados para esta flota");
            lblNoDisponibles.setForeground(new java.awt.Color(148,163,184)); // Un tono rojo/coral suave de alerta sutil
            lblNoDisponibles.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13)); // En negrita para que resalte la alerta
            
            pnlContendorTCP.add(lblNoDisponibles);
        } else {
            // SI HAY TCP: Mantenemos tu configuración original de cuadrícula
            pnlContendorTCP.setLayout(new java.awt.GridLayout(0, 5, 8, 8));
            
            for (Clases.TripulanteCabina tcp : tcpsHabilitados) {
                BotonChipTCP btnChip = new BotonChipTCP(tcp.getNombre()); // Instanciamos nuestra nueva clase

                // Evento: ¿Qué pasa cuando le doy clic al chip?
                btnChip.addItemListener(e -> {
                    if (btnChip.isSelected()) {
                        tcpsSeleccionadosList.add(tcp); 

                        if(tcpsSeleccionadosList.size() == 1) {
                            btnChip.setText(tcp.getNombre()); // El primero es el jefe
                        } else {
                            btnChip.setText(tcp.getNombre());
                        }
                    } else {
                        btnChip.setText(tcp.getNombre()); // Le quitamos el icono si lo deselecciona
                        tcpsSeleccionadosList.remove(tcp); 
                    }
                });

                pnlContendorTCP.add(btnChip);
            }
        }

        // Obligamos a Java a redibujar el panel
        pnlContendorTCP.revalidate();
        pnlContendorTCP.repaint();
    }//GEN-LAST:event_cbxSeleccionVueloActionPerformed

    private void cbxVuelosOOOIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxVuelosOOOIActionPerformed
        if (cbxVuelosOOOI.getSelectedIndex() <= 0 || cbxVuelosOOOI.getSelectedItem() instanceof String) {
        sincronizarBotonesOOOI(null);
        return;
        }

        Clases.VueloOperativo voSel = (Clases.VueloOperativo) cbxVuelosOOOI.getSelectedItem();
        sincronizarBotonesOOOI(voSel);
    }//GEN-LAST:event_cbxVuelosOOOIActionPerformed

    private void rbtnPrioridadBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPrioridadBajaActionPerformed
        if (txtReporteFallas.getText().equals("NIL - Operación sin novedades")) {
            txtReporteFallas.setText(""); // Limpia el texto por defecto
        }
        txtReporteFallas.setEnabled(true); // Habilita la escritura para que detallen la falla
        txtReporteFallas.requestFocus();
    }//GEN-LAST:event_rbtnPrioridadBajaActionPerformed

    private void rbtnPrioridadMediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPrioridadMediaActionPerformed
        if (txtReporteFallas.getText().equals("NIL - Operación sin novedades")) {
            txtReporteFallas.setText(""); // Limpia el texto por defecto
        }
        txtReporteFallas.setEnabled(true); // Habilita la escritura para que detallen la falla
        txtReporteFallas.requestFocus();
    }//GEN-LAST:event_rbtnPrioridadMediaActionPerformed

    private void rbtnPrioridadAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPrioridadAltaActionPerformed
        if (txtReporteFallas.getText().equals("NIL - Operación sin novedades")) {
            txtReporteFallas.setText(""); // Limpia el texto por defecto
        }
        txtReporteFallas.setEnabled(true); // Habilita la escritura para que detallen la falla
        txtReporteFallas.requestFocus();
    }//GEN-LAST:event_rbtnPrioridadAltaActionPerformed

    private void txtCombustibleRestanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCombustibleRestanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCombustibleRestanteActionPerformed

    private void cbxVuelosLogbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxVuelosLogbookActionPerformed
        if (cbxVuelosLogbook.getSelectedIndex() <= 0) {
            habilitarPanelLogbook(false); // Apaga todo si no hay vuelo
            return;
        } else {
            habilitarPanelLogbook(true);  // Enciende todo si hay un vuelo seleccionado
        }
    }//GEN-LAST:event_cbxVuelosLogbookActionPerformed

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

    private void btnAsigancionVuelosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAsigancionVuelosMouseClicked
    // 1. Apagamos TODOS los botones
        btnAsigancionVuelos.setActivo(false);
        btnAutorizarDespacho.setActivo(false);
        btnHistorialVuelos.setActivo(false);
        btnGestionFlota.setActivo(false);
    // 2. Encendemos SOLO el que acabamos de cliquear
        btnAsigancionVuelos.setActivo(true);
    // 3. Cambiamos la pantalla del medio usando tu CardLayout
    // Esto permitira intercambiar paneles de forma rapida como si fueran barajas, poniendo la que se usa encima de la anterior
        java.awt.CardLayout carta = (java.awt.CardLayout) pnlContenedorPrincipal.getLayout();
        carta.show(pnlContenedorPrincipal, "pnlAsignacion"); // Aca va el nombre del panel
    }//GEN-LAST:event_btnAsigancionVuelosMouseClicked

    private void btnAutorizarDespachoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAutorizarDespachoMouseClicked
        // Apagas todos
        btnAsigancionVuelos.setActivo(false);
        btnAutorizarDespacho.setActivo(false);
        btnHistorialVuelos.setActivo(false);
        btnGestionFlota.setActivo(false);
        // Enciendes Despacho
        btnAutorizarDespacho.setActivo(true);
        // Muestras el panel de Despacho
        java.awt.CardLayout carta = (java.awt.CardLayout) pnlContenedorPrincipal.getLayout();
        carta.show(pnlContenedorPrincipal, "pnlDespacho");
        
        //Sincroniza y voltea el panel si hay vuelos nuevos
        cargarVuelosEnDespacho();
    }//GEN-LAST:event_btnAutorizarDespachoMouseClicked

    private void btnHistorialVuelosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialVuelosMouseClicked
        // Apagas todos
        btnAsigancionVuelos.setActivo(false);
        btnAutorizarDespacho.setActivo(false);
        btnHistorialVuelos.setActivo(false);
        btnGestionFlota.setActivo(false);
        // Enciendes Historial
        btnHistorialVuelos.setActivo(true);
        // Muestras el panel de Historial
        java.awt.CardLayout carta = (java.awt.CardLayout) pnlContenedorPrincipal.getLayout();
        carta.show(pnlContenedorPrincipal, "pnlHistorial");
    }//GEN-LAST:event_btnHistorialVuelosMouseClicked

    private void btnGestionFlotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGestionFlotaMouseClicked
        // Apagas todos
        btnAsigancionVuelos.setActivo(false);
        btnAutorizarDespacho.setActivo(false);
        btnHistorialVuelos.setActivo(false);
        btnGestionFlota.setActivo(false);
        // Enciendes Flota
        btnGestionFlota.setActivo(true);
        // Muestras el panel de Flota
        java.awt.CardLayout carta = (java.awt.CardLayout) pnlContenedorPrincipal.getLayout();
        carta.show(pnlContenedorPrincipal, "pnlFlota");
    }//GEN-LAST:event_btnGestionFlotaMouseClicked

    private void cbxFiltroEstadoVueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroEstadoVueloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroEstadoVueloActionPerformed

    private void cbxFiltroAeronaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroAeronaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroAeronaveActionPerformed

    private void cbxFiltroVueloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroVueloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroVueloActionPerformed

    private void cbxFiltroMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroMatriculaActionPerformed

    private void cbxFiltroModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroModeloActionPerformed

    private void cbxFiltroEstadoAeronaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroEstadoAeronaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFiltroEstadoAeronaveActionPerformed

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        Login_GUI login = new Login_GUI();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        cbxSeleccionVuelo.setSelectedIndex(0);
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void btnCrearVueloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearVueloMouseClicked
        // 1. Validaciones iniciales de seguridad en interfaz
        if (cbxSeleccionVuelo.getSelectedIndex() <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione un vuelo programado.");
            return;
        }

        javax.swing.JComboBox comboCAP = cbxSeleccionCAP;
        javax.swing.JComboBox comboFO = cbxSeleccionFO;

        if (comboCAP.getSelectedIndex() <= 0 || comboCAP.getSelectedItem().equals("—")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe asignar un Capitán disponible.");
            return;
        }
        if (comboFO.getSelectedIndex() <= 0 || comboFO.getSelectedItem().equals("—")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe asignar un Primer Oficial disponible.");
            return;
        }

        // Recuperamos los objetos puros mapeados en las cajas
        Clases.VueloProgramado vueloSel = (Clases.VueloProgramado) cbxSeleccionVuelo.getSelectedItem();
        Clases.TripulanteVuelo capitanSel = (Clases.TripulanteVuelo) comboCAP.getSelectedItem();
        Clases.TripulanteVuelo foSel = (Clases.TripulanteVuelo) comboFO.getSelectedItem();

        // 2. Regla de negocio: El mismo piloto no puede duplicar roles en el mismo avión
        if (capitanSel.getIdEmpleado() == foSel.getIdEmpleado()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Alerta: El Capitán y el Copiloto no pueden ser la misma persona.");
            return;
        }

        // 3. Regla de negocio: Validar la capacidad de asientos (1 TCP cada 50 asientos)
        int tcpMinimos = (int) Math.ceil(vueloSel.getCapacidadAsientos() / 50.0);
        if (tcpsSeleccionadosList.size() < tcpMinimos) {
            javax.swing.JOptionPane.showMessageDialog(this, "Tripulación insuficiente. Se requieren mínimo " + tcpMinimos + " TCPs seleccionados.");
            return;
        }

        // 4. EJECUCIÓN TÉCNICA DEL PATRÓN BUILDER
        Patrones.VueloOperativoBuilder builder = new Patrones.VueloOperativoBuilder();
        Clases.VueloOperativo vueloConstruido = builder
                .setCodVuelo("LA" + vueloSel.getCodProgramacion()) // Generamos código transaccional
                .setFechaOperacion(new java.util.Date())          // Registramos momento exacto
                .setVueloBase(vueloSel)
                .setCapitan(capitanSel)
                .setPrimerOficial(foSel)
                .setTripulacionCabina(new java.util.ArrayList<>(tcpsSeleccionadosList)) // Clonamos la lista actual
                .build();

        // 5. Invocamos al DAO para persistir en PostgreSQL
        ClasesDAO.VueloOperativoDAO voDAO = new ClasesDAO.VueloOperativoDAO();
        if (voDAO.registrarVueloOperativo(vueloConstruido)) {
            javax.swing.JOptionPane.showMessageDialog(this, "¡Vuelo " + vueloConstruido.getCodVuelo() + " asignado con éxito a despacho!");

            // 6. Refrescamos el ComboBox inicial. Al borrarse de la lista de vírgenes, desaparecerá automáticamente.
            cargarComboBoxesDespacho();
            cargarVuelosPendientesDespacho(); //Actualizamos y agregamos el vuelo creado a la lista de pendientes despacho
            cargarVuelosEnDespacho(); // Asegura el intercambio inmediato de estados
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error crítico al guardar la asignación del vuelo operativo.");
        }
    }//GEN-LAST:event_btnCrearVueloMouseClicked

    private void cbxVuelosDespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxVuelosDespachoActionPerformed
        // 1. LIMPIAR TODO ANTES DE HACER NADA
        limpiarCampos();

        // 2. Si el usuario seleccionó la opción por defecto (índice 0)
        if (cbxVuelosDespacho.getSelectedIndex() <= 0) {
            lblResumenRuta.setText("<html><font color='#94a3b8'>Seleccione un vuelo operativo para cargar los requerimientos de despacho.</font></html>");
            return;
        }

        // 3. Cargamos el nuevo objeto vuelo
        Clases.VueloOperativo voSel = (Clases.VueloOperativo) cbxVuelosDespacho.getSelectedItem();
        // ENCENDEMOS LOS CAMPOS AL SELECCIONAR UN VUELO
        java.awt.Color colorActivo = new java.awt.Color(15, 23, 42); // Color de los campos

        txtAreaClima.setEditable(true);
        txtAreaClima.setFocusable(true);
        txtAreaClima.setBackground(colorActivo);

        txtFieldPasajeros.setEditable(true);
        txtFieldPasajeros.setFocusable(true);
        txtFieldPasajeros.setBackground(colorActivo);

        txtFieldEquipaje.setEditable(true);
        txtFieldEquipaje.setFocusable(true);
        txtFieldEquipaje.setBackground(colorActivo);

        txtFieldCarga.setEditable(true);
        txtFieldCarga.setFocusable(true);
        txtFieldCarga.setBackground(colorActivo);

        txtFieldCombRuta.setEditable(true);
        txtFieldCombRuta.setFocusable(true);
        txtFieldCombRuta.setBackground(colorActivo);

        txtFieldCombReserva.setEditable(true);
        txtFieldCombReserva.setFocusable(true);
        txtFieldCombReserva.setBackground(colorActivo);
        
        // 4. Actualizamos el MTOW dinámico (Modelo -> VueloProgramado -> Aeronave)
        this.mtowActual = voSel.getVueloBase().getPesoMaximoDespegue();
        lblPesoMaximo.setText(mtowActual + " KG"); // Actualizamos la etiqueta del máximo

        // 5. Actualizamos el resumen del vuelo
        String capName = (voSel.getCapitan() != null) ? voSel.getCapitan().getNombre() : "No asignado";
        String jefeName = (voSel.getTripulacionCabina() != null && !voSel.getTripulacionCabina().isEmpty()) ? voSel.getTripulacionCabina().get(0).getNombre() : "No asignado";

        lblResumenRuta.setText("<html><font color='#94a3b8'>Ruta:</font> <font color='#ffffff'><b>" + voSel.getVueloBase().getOrigenDestino() + 
                               "</b></font>  <font color='#94a3b8'>Aeronave:</font> <font color='#ffffff'><b>" + voSel.getVueloBase().getMatricula() + "</b></font> " +
                               " <font color='#94a3b8'>CAP:</font> <font color='#ffffff'><b>Cdt. " + capName + "</b></font>  <font color='#94a3b8'>TCP Jefe:</font> <font color='#ffffff'><b>" + jefeName + "</b></font></html>");


    }//GEN-LAST:event_cbxVuelosDespachoActionPerformed

    private void btnCancelarVueloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarVueloMouseClicked
        // 1. Validamos que haya un vuelo seleccionado
        if (cbxVuelosDespacho.getSelectedIndex() <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un vuelo operativo para cancelar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        Clases.VueloOperativo voSel = (Clases.VueloOperativo) cbxVuelosDespacho.getSelectedItem();

        // 2. Confirmación de seguridad con estilo de alerta crítica (del código largo)
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea CANCELAR el vuelo " + voSel.getCodVuelo() + " de forma definitiva?\nLa tripulación asignada será liberada.",
                "Alerta Crítica: Cancelar Vuelo",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.ERROR_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {

            // 3. INTEGRACIÓN PATRÓN STATE: El objeto cambia internamente a EstadoCancelado
            voSel.procesarCancelacion(); 

            ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();

            // 4. ACTUALIZACIÓN EN BASE DE DATOS: Pasamos el código de vuelo y el nuevo String del estado del objeto
            if (dao.actualizarEstadoVuelo(voSel.getCodVuelo(), voSel.getEstadoVuelo())) {

                // Mensaje de éxito profesional
                javax.swing.JOptionPane.showMessageDialog(this, "Vuelo Cancelado. Se ha liberado la aeronave y la tripulación asignada.");

                // 5. LIMPIEZA Y PARPADEO VISUAL DE LA INTERFAZ (Del código largo)
                limpiarCampos(); 
                cargarComboBoxesDespacho(); // O usa cargarVuelosEnDespacho() según se llame tu método actual de recarga de combo

                // REFRESCO VISUAL DE LAS TARJETAS (PANEL DERECHO)
                cargarVuelosPendientesDespacho(); 

                // Forzamos a Java a recalcular tamaños y redibujar los componentes de inmediato
                pnlListaItemsVuelo.revalidate(); 
                pnlListaItemsVuelo.repaint(); 

                ScrollPendientesDespacho.revalidate();
                ScrollPendientesDespacho.repaint();

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar cancelar el vuelo en el sistema.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCancelarVueloMouseClicked

    private void btnAprobarPlanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAprobarPlanMouseClicked
        if (cbxVuelosDespacho.getSelectedIndex() <= 0) return;

        // ==============================================================
        // 1. VALIDACIÓN DE CAMPOS VACÍOS (Asegura completitud de datos)
        // ==============================================================
        if (txtFieldPasajeros.getText().trim().isEmpty() ||
            txtFieldEquipaje.getText().trim().isEmpty() ||
            txtFieldCarga.getText().trim().isEmpty() ||
            txtFieldCombRuta.getText().trim().isEmpty() ||
            txtFieldCombReserva.getText().trim().isEmpty() ||
            txtAreaClima.getText().trim().isEmpty()) {
            
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Operación rechazada: Todos los campos de peso (Pasajeros, Equipaje, Carga, Combustible) y el reporte METAR deben estar completamente llenados antes de autorizar el despacho.", 
                "Información Incompleta", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return; // Rompe la ejecución aquí y evita el despacho
        }

        Clases.VueloOperativo voSel = (Clases.VueloOperativo) cbxVuelosDespacho.getSelectedItem();

        // ==============================================================
        // 2. REGLA DE ORO: Doble validación estricta (Peso y Clima)
        //    Ya NO se compara el texto del JLabel: se valida contra las
        //    clases de negocio reales (HojaDeCarga / ValidadorMetar).
        // ==============================================================
        Clases.ManifiestoCombustible manifiesto = construirManifiestoDesdeFormulario();
        Clases.HojaDeCarga hoja = construirHojaDeCargaDesdeFormulario(manifiesto);
        String codigoMetar = txtAreaClima.getText().trim().toUpperCase();

        if (!hoja.validarPesoMaximo(mtowActual)) {
            javax.swing.JOptionPane.showMessageDialog(this, "CRÍTICO: No se puede aprobar el despacho. El peso actual supera el MTOW de la aeronave.");
            return; // Rompe la ejecución aquí
        }

        if (!ValidadorMetar.esClimaApto(codigoMetar)) {
            javax.swing.JOptionPane.showMessageDialog(this, "CRÍTICO: El METAR reporta clima adverso. Debe declarar demora o cancelar el vuelo.");
            return; // Rompe la ejecución aquí
        }

        if (!metarCorrespondeADestino(codigoMetar, voSel)) {
            javax.swing.JOptionPane.showMessageDialog(this, "CRÍTICO: El código METAR ingresado no pertenece al aeropuerto de destino de este vuelo.", "Error de Jurisdicción", javax.swing.JOptionPane.ERROR_MESSAGE);
            return; // Rompe la ejecución aquí
        }

        // ==============================================================
        // 3. PROCESAMIENTO DEL PATRÓN STATE
        // ==============================================================
        voSel.procesarAprobacion();

        // ==============================================================
        // 4. ENSAMBLAJE FINAL CON EL PATRÓN BUILDER
        //    Reutilizamos el Builder para adjuntar HojaDeCarga, Manifiesto y
        //    Clima al vuelo YA existente, sin perder tripulación ni estado.
        // ==============================================================
        Clases.ReporteMeteorologico clima = new Clases.ReporteMeteorologico();
        clima.setCodigoMETAR(codigoMetar);

        voSel = new Patrones.VueloOperativoBuilder(voSel)
                .setHojaCarga(hoja)
                .setManifiesto(manifiesto)
                .setClima(clima)
                .build();

        // ==============================================================
        // 5. IMPACTO TRANSACCIONAL EN BASE DE DATOS POSTGRESQL
        // ==============================================================
        ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();
        if (dao.aprobarDespachoConDatosOperativos(voSel)) {
            javax.swing.JOptionPane.showMessageDialog(this, "¡Despacho Autorizado! El vuelo " + voSel.getCodVuelo() + " pasó a estado APROBADO.");

            // 6. Refrescamos el panel
            cargarVuelosEnDespacho();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar los datos de despacho en la base de datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        cargarVuelosEnControlOOOI();
    }//GEN-LAST:event_btnAprobarPlanMouseClicked

    private void btnDeclararDemoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeclararDemoraMouseClicked
        if (cbxVuelosDespacho.getSelectedIndex() <= 0) return;

        Clases.VueloOperativo voSel = (Clases.VueloOperativo) cbxVuelosDespacho.getSelectedItem();
        voSel.procesarDemora(); // El State cambia el comportamiento a EstadoEnDemora

        ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();
        if (dao.actualizarEstadoVuelo(voSel.getCodVuelo(), voSel.getEstadoVuelo())) {
            javax.swing.JOptionPane.showMessageDialog(this, "Alerta: El vuelo " + voSel.getCodVuelo() + " ha sido retenido en rampa (EN_DEMORA).");

            cargarVuelosEnDespacho();
        }
    }//GEN-LAST:event_btnDeclararDemoraMouseClicked

    private void btnOUTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOUTMouseClicked
        procesarClicControlOOOI("OUT", btnOUT);
    }//GEN-LAST:event_btnOUTMouseClicked

    private void btnOFFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOFFMouseClicked
        procesarClicControlOOOI("OFF", btnOFF);
    }//GEN-LAST:event_btnOFFMouseClicked

    private void btnONMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnONMouseClicked
        procesarClicControlOOOI("ON", btnON);
    }//GEN-LAST:event_btnONMouseClicked

    private void btnINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnINMouseClicked
        procesarClicControlOOOI("IN", btnIN);
    }//GEN-LAST:event_btnINMouseClicked

    private void btnCerrarVueloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarVueloMouseClicked
        // ==========================================
        // 1. VALIDACIÓN DE COMBUSTIBLE
        // ==========================================
        String combustibleStr = txtCombustibleRestante.getText().trim();
        if (combustibleStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Debe ingresar el combustible sobrante.", 
                "Error de Validación", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        double combustible = 0.0;
        try {
            combustible = Double.parseDouble(combustibleStr);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "El combustible sobrante debe ser un número válido.", 
                "Error de Formato", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ==========================================
        // 2. VALIDACIÓN DE FALLAS Y OBSERVACIONES
        // ==========================================
        // Si el usuario NO marcó "Sin Fallas", obligatoriamente debe escribir qué pasó
        if (!rbtnSinFallas.isSelected()) {
            if (txtReporteFallas.getText().trim().isEmpty() || txtReporteFallas.getText().equals("NIL - Operación sin novedades")) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Ha reportado una falla. Debe detallarla en las observaciones técnicas.", 
                    "Atención", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                txtReporteFallas.requestFocus(); // Pone el cursor en la caja de texto
                return;
            }
        }

        // ==========================================
        // 3. DETERMINAR LA PRIORIDAD SELECCIONADA
        // ==========================================
        Enumeradores.EstadoPrioridad prioridadBD = Enumeradores.EstadoPrioridad.SIN_FALLAS; // Ajusta el nombre según tu Enum
        if (rbtnPrioridadBaja.isSelected()) prioridadBD = Enumeradores.EstadoPrioridad.BAJA;
        if (rbtnPrioridadMedia.isSelected()) prioridadBD = Enumeradores.EstadoPrioridad.MEDIA;
        if (rbtnPrioridadAlta.isSelected()) prioridadBD = Enumeradores.EstadoPrioridad.ALTA;

        // ==========================================
        // 4. OBTENER EL VUELO DEL COMBOBOX
        // ==========================================
        // Nos aseguramos de que haya seleccionado un vuelo real y no el texto por defecto
        if (cbxVuelosLogbook.getSelectedIndex() <= 0 || !(cbxVuelosLogbook.getSelectedItem() instanceof Clases.VueloOperativo)) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un vuelo válido para cerrar.", 
                "Advertencia", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Casteamos el objeto que viene purito del combo
        Clases.VueloOperativo vueloSeleccionado = (Clases.VueloOperativo) cbxVuelosLogbook.getSelectedItem();

        // ==========================================
        // 5. LLENAR EL OBJETO REPORTELOGBOOK (POJO)
        // ==========================================
        Clases.ReporteLogbook objLogbook = new Clases.ReporteLogbook();

        // Ingresamos los datos al objeto Logbook
        objLogbook.setIdVueloOperativo(vueloSeleccionado.getIdVueloOperativo()); 
        objLogbook.setCombustibleSobrante(combustible);
        objLogbook.setObservacionesTecnicas(txtReporteFallas.getText().trim());
        objLogbook.setPrioridad(prioridadBD);

        // ==========================================
        // 6. EJECUTAR TRANSACCIÓN EN BASE DE DATOS
        // ==========================================
        ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();
        boolean transaccionExitosa = dao.cerrarVueloConLogbook(objLogbook);

        // ==========================================
        // 7. RESPUESTA VISUAL Y RESETEO DE INTERFAZ
        // ==========================================
        if (transaccionExitosa) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Vuelo " + vueloSeleccionado.getCodVuelo() + " cerrado y Logbook registrado exitosamente.", 
                "Cierre Operacional Completado", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // OJO: Aquí pon el método real que usas para llenar tu combo, para que se actualice la lista
            // cargarVuelosLogbook(); 
            
            // Limpiamos y bloqueamos el panel hasta que seleccione un nuevo vuelo
            habilitarPanelLogbook(false); 
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error crítico al intentar registrar el Logbook y cerrar el vuelo. Se ha deshecho la transacción (Rollback).", 
                "Error de Base de Datos", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCerrarVueloMouseClicked

    private void rbtnSinFallasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSinFallasActionPerformed
        txtReporteFallas.setText("NIL - Operación sin novedades");
        txtReporteFallas.setEnabled(false); // Bloquea la escritura
    }//GEN-LAST:event_rbtnSinFallasActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new OficialOperaciones_GUI().setVisible(true));
    }

// ===================================================================================================
// MÉTODO PARA LIMPIAR LOS CAMPOS Y DESHABILITARLOS SI NO HAY VUELO SELECCIONADO DE LA VISTA DESPACHO
// ===================================================================================================
    private void limpiarCampos() {
        // Limpiamos textos
        txtAreaClima.setText("");
        txtFieldPasajeros.setText("");
        txtFieldEquipaje.setText("");
        txtFieldCarga.setText("");
        txtFieldCombRuta.setText("");
        txtFieldCombReserva.setText("");
        txtFieldCombustible.setText("");
        
        // Bloqueamos los campos y pintamos de azul oscuro #0f172a
        java.awt.Color colorBloqueado = new java.awt.Color(15, 23, 42);
        
        txtAreaClima.setEditable(false);
        txtAreaClima.setFocusable(false);
        txtAreaClima.setBackground(colorBloqueado);
        
        txtFieldPasajeros.setEditable(false);
        txtFieldPasajeros.setFocusable(false);
        txtFieldPasajeros.setBackground(colorBloqueado);
        
        txtFieldEquipaje.setEditable(false);
        txtFieldEquipaje.setFocusable(false);
        txtFieldEquipaje.setBackground(colorBloqueado);
        
        txtFieldCarga.setEditable(false);
        txtFieldCarga.setFocusable(false);
        txtFieldCarga.setBackground(colorBloqueado);
        
        txtFieldCombRuta.setEditable(false);
        txtFieldCombRuta.setFocusable(false);
        txtFieldCombRuta.setBackground(colorBloqueado);
        
        txtFieldCombReserva.setEditable(false);
        txtFieldCombReserva.setFocusable(false);
        txtFieldCombReserva.setBackground(colorBloqueado);
        
        // Reseteamos semáforo
        lblAprobado.setText("PENDIENTE");
        lblAprobado.setForeground(java.awt.Color.GRAY);
        pnlFondoAprobado.setBackground(new java.awt.Color(30, 41, 59));
        barraMTOW.setValue(0);
        lblPorcentajeMTOW.setText("0% MTOW");
        lblSumaTotal.setText("0.00");
        
        btnAprobarPlan.setEnabled(false);
    }    

// ===============================================================================================
// MÉTODO PARA VALIDAR EL INGRESO DE SOLO NÚMEROS EN LOS CAMPOS DE CALCULO DE PESO VISTA DESPACHO
// ===============================================================================================
    private void aplicarValidacionNumerica() {
        // Creamos un "escucha" que solo deja pasar números y el punto decimal
        java.awt.event.KeyAdapter validador = new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                // Si lo que tecleó NO es un número
                if (!Character.isDigit(c)) {
                    evt.consume(); // Destruye la tecla (la ignora)
                }
            }
        };
        
        // Le inyectamos este escudo protector a los 5 campos
        txtFieldPasajeros.addKeyListener(validador);
        txtFieldEquipaje.addKeyListener(validador);
        txtFieldCarga.addKeyListener(validador);
        txtFieldCombRuta.addKeyListener(validador);
        txtFieldCombReserva.addKeyListener(validador);
    }
    

// ==================================================
// MÉTODO PARA PERSONALIZAR EL DISEÑO DE LOS JCOMBOX
// ==================================================
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

// ==========================================
// MÉTODO PARA CAMBIAR EL DISEÑO DEL SCROLL
// ==========================================    
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
// ==========================================
// MÉTODOS PARA CAMBIAR DISEÑO DE LAS TABLAS
// ==========================================  
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
        // 3. Renderizador para el CUERPO de la tabla
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
                    label.setForeground(new java.awt.Color(248, 250, 252)); // Letra blanca normal
                }

                // --- B. COLUMNA 0 ("VUELO"): Letra en Negrita ---
                if (column == 0) {
                    label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
                }

                // --- C. COLUMNA 5 ("ESTADO"): Efecto de Píldora de Color ---
                if (table.getColumnName(column).equalsIgnoreCase("ESTADO") && value != null && !isSelected) {
                    String estado = value.toString();
                    label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                    
                    // Mapeamos exactamente tus colores CSS (Fondo oscuro, letra neón)
                    switch (estado) {
                        case "APROBADO":
                            label.setBackground(new java.awt.Color(5, 46, 22)); // #052e16
                            label.setForeground(new java.awt.Color(74, 222, 128)); // #4ade80
                            break;
                        case "EN_VUELO":
                            label.setBackground(new java.awt.Color(12, 42, 74)); // #0c2a4a
                            label.setForeground(new java.awt.Color(96, 165, 250)); // #60a5fa
                            break;
                        case "COMPLETADO":
                            label.setBackground(new java.awt.Color(15, 23, 42)); // #0f172a
                            label.setForeground(new java.awt.Color(203, 213, 225)); // #cbd5e1
                            break;
                        case "CANCELADO":
                            label.setBackground(new java.awt.Color(76, 5, 25)); // #4c0519
                            label.setForeground(new java.awt.Color(251, 113, 133)); // #fb7185
                            break;
                        case "EN_DEMORA":
                            label.setBackground(new java.awt.Color(66, 32, 6)); // #422006
                            label.setForeground(new java.awt.Color(251, 191, 36)); // #fbbf24
                            break;
                        case "APTO":
                            label.setBackground(new java.awt.Color(5, 46, 22)); 
                            label.setForeground(new java.awt.Color(74, 222, 128)); 
                            break;
                        case "MANTENIMIENTO":
                            label.setBackground(new java.awt.Color(76, 5, 25)); 
                            label.setForeground(new java.awt.Color(251, 113, 133)); 
                            break;
                    }
                }

                // --- D. COLUMNAS 6 al 9 (Tiempos OOOI): Fuente de Consola y Verde ---
                if (column >= 6 && column <= 9 && value != null) {
                    String tiempo = value.toString();
                    // Fuente "Monospaced" (tipo consola)
                    label.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 13)); 
                    
                    if (!isSelected) { // Solo si NO está seleccionada la fila aplicamos colores
                        if (!tiempo.contains("--")) {
                            label.setForeground(new java.awt.Color(74, 222, 128)); // Verde neón
                        } else {
                            label.setForeground(new java.awt.Color(71, 85, 105)); // Gris apagado
                        }
                    }
                }

                return c;
            }
        });
        
        // =========================================================================
        // 4. Deseleccionar al hacer clic en CUALQUIER LADO
        // =========================================================================
        // Usamos Toolkit para poner un "Ojo" que vigila todos los clics de toda la ventana
        java.awt.Toolkit.getDefaultToolkit().addAWTEventListener(new java.awt.event.AWTEventListener() {
            @Override
            public void eventDispatched(java.awt.AWTEvent event) {
                if (event instanceof java.awt.event.MouseEvent) {
                    java.awt.event.MouseEvent me = (java.awt.event.MouseEvent) event;
                    
                    // Solo nos interesa cuando el usuario presiona el clic
                    if (me.getID() == java.awt.event.MouseEvent.MOUSE_PRESSED) {
                        java.awt.Component comp = me.getComponent();
                        
                        if (comp != null) {
                            // Verificamos si donde hizo clic pertenece a la caja de la tabla
                            boolean esParteDeLaTabla = javax.swing.SwingUtilities.isDescendingFrom(comp, tabla.getParent().getParent());
                            
                            if (!esParteDeLaTabla) {
                                // CASO A: Hizo clic en cualquier otro lado de la app (menú, fondo, títulos)
                                tabla.clearSelection();
                            } else if (comp == tabla) {
                                // CASO B: Hizo clic dentro de la tabla, pero comprobamos si fue en un hueco vacío
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
    
// ELIMINAR AL METER BASE DE DATOS METODO PARA CARGAR DATOS DE PRUEBA
    private void cargarDatosPruebaHistorial() {
        // 1. Obtenemos el modelo de la tabla (Cambia 'tuTablaHistorial' por el nombre de tu variable)
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) TblHistorialVuelo.getModel();

        // 2. Limpiamos la tabla por si acaso tenía filas vacías
        modelo.setRowCount(0);

        // 3. Agregamos las filas de prueba como si vinieran de una Base de Datos
        // Las columnas son: VUELO, RUTA, AERONAVE, CAPITÁN, JEFE CABINA, ESTADO, OUT, OFF, ON, IN
        modelo.addRow(new Object[]{
            "LA800", "LIM → CUZ", "OB-2200", "Cdt. Ana Vargas", "Pedro Salas", "EN_VUELO", "20:22Z", "20:30Z", "--:--", "--:--"
        });

        modelo.addRow(new Object[]{
            "LA532", "LIM → AQP", "OB-2101", "Cdt. Luis Poma", "Rosa Flores", "COMPLETADO", "18:00Z", "18:15Z", "19:10Z", "19:25Z"
        });

        modelo.addRow(new Object[]{
            "LA440", "CUZ → JUL", "OB-1995", "Cdt. Roberto Silva", "Diego Pinto", "APROBADO", "--:--", "--:--", "--:--", "--:--"
        });

        modelo.addRow(new Object[]{
            "LA101", "LIM → PIU", "OB-2055", "Cdt. Mario Cueva", "Carla Mendez", "CANCELADO", "--:--", "--:--", "--:--", "--:--"
        });

        // 4. ¡LA MAGIA! Llamamos al método que ajusta la altura para que la tabla crezca abrazando estas 4 filas
        // (Asegúrate de cambiar los nombres por las variables de tu tabla y tu scroll)
        ajustarAlturaDinamica(TblHistorialVuelo, ScrollTablaHistorialVuelos);
    }

// ELIMINAR AL METER BASE DE DATOS METODO PARA CARGAR DATOS DE PRUEBA EN FLOTA
    private void cargarDatosPruebaFlota() {
        // 1. Obtenemos el modelo de tu nueva tabla de Flota
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) TblFlota.getModel();

        // 2. Limpiamos la tabla
        modelo.setRowCount(0);

        // 3. Agregamos las filas exactas de tu diseño web
        // Columnas: MATRÍCULA, MODELO, ESTADO, ASIENTOS, MTOW (KG), AUTONOMÍA (KM)
        modelo.addRow(new Object[]{"OB-2200", "A320neo", "APTO", "180", "79.000", "6300"});
        modelo.addRow(new Object[]{"OB-2101", "B737-800", "APTO", "162", "79.016", "5765"});
        modelo.addRow(new Object[]{"OB-1995", "ATR 72-600", "APTO", "70", "23.000", "1528"});
        modelo.addRow(new Object[]{"OB-2055", "A320neo", "MANTENIMIENTO", "180", "79.000", "6300"});

        // 4. ¡LA MAGIA! Llamamos a tu mismo método dinámico para que ajuste esta nueva caja
        ajustarAlturaDinamica(TblFlota, ScrollTablaFlota);
    }

// ===========================================================
// MÉTODO PARA CARGAR DATOS DE COMBOX PAR ACREACION DE VUELOS
// ===========================================================   
    private void cargarComboBoxesDespacho() {
        ClasesDAO.DespachoDAO dao = new ClasesDAO.DespachoDAO();

        // Truco: Usamos variables genéricas para saltarnos la restricción de solo texto de NetBeans
        javax.swing.JComboBox comboVuelo = cbxSeleccionVuelo;
        javax.swing.JComboBox comboCAP = cbxSeleccionCAP;
        javax.swing.JComboBox comboFO = cbxSeleccionFO;

        // 1. Limpiamos y preparamos las cajitas con sus valores por defecto
        comboVuelo.removeAllItems(); 
        comboVuelo.addItem("— Seleccionar Vuelo —");

        comboCAP.removeAllItems();
        comboCAP.addItem("—");

        comboFO.removeAllItems();
        comboFO.addItem("—");

        // 2. Llenamos los Vuelos Programados pasando el OBJETO REAL
        java.util.List<Clases.VueloProgramado> vuelos = dao.obtenerVuelosDisponibles();
        for(Clases.VueloProgramado v : vuelos) {
            comboVuelo.addItem(v); // ¡MAGIA! Pasamos la variable 'v', SIN el .toString()
        }
    }
    
// ==========================================================
// MOVER A CARPETA ElementosDiseño CLASE PERSONALIZADA PARA LOS CHIPS DE LOS TCP (ESTILO WEB) 
// ==========================================================
    public class BotonChipTCP extends javax.swing.JToggleButton {
        public BotonChipTCP(String text) {
            super(text);
            setContentAreaFilled(false); 
            setFocusPainted(false);      
            setBorderPainted(false);     
            
            // 📍 AQUÍ CAMBIAS EL TAMANO DE LA LETRA (El número 12 es el tamaño)
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            
            // 📍 AQUÍ JUEGAS CON EL ANCHO Y ALTO DEL CUADRADITO (Ancho, Alto)
            // Si quieres que entren 4 o 5 por fila, reduce el ancho (ej: de 130 a 110 o 100)
            setPreferredSize(new java.awt.Dimension(100, 32));
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            if (isSelected()) {
                // ESTADO SELECCIONADO: Fondo Rojo sólido, Letra blanca
                g2.setColor(java.awt.Color.decode("#2d0a1e"));
                setForeground(java.awt.Color.decode("#ee716a"));
            } else {
                // ESTADO APAGADO (Tus colores web exactos)
                g2.setColor(java.awt.Color.decode("#0f172a")); // Fondo #2d0a1e
                setForeground(java.awt.Color.WHITE); // Letra #ee716a
            }

            // Dibujamos el fondo con esquinas redondeadas
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

            // Dibujamos el contorno
            if (isSelected()) {
                g2.setColor(java.awt.Color.decode("#e11d48")); 
            } else {
                g2.setColor(java.awt.Color.decode("#334155")); // Contorno #e11d48
            }
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);

            g2.dispose();
            super.paintComponent(g); 
        }
    }

// ==================================================================================
// MÉTODO PARA CARGAR LOS VUELOS PENDIENTES EN LA LISTA DE PENDIENTES A DESPACHO (W&B)
// ==================================================================================
    private void cargarVuelosPendientesDespacho() {
        ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();
        java.util.List<Clases.VueloOperativo> pendientes = dao.obtenerVuelosPendientesDespacho();

        // Limpiamos el panel contenedor
        pnlListaItemsVuelo.removeAll();

        // Cambiamos dinámicamente a BoxLayout Vertical para no estirar las tarjetas
        pnlListaItemsVuelo.setLayout(new javax.swing.BoxLayout(pnlListaItemsVuelo, javax.swing.BoxLayout.Y_AXIS));

        // Obtenemos el CardLayout de tu panel base
        java.awt.CardLayout carta = (java.awt.CardLayout) pnlBasePendientes.getLayout();

        if (pendientes.isEmpty()) {
            carta.show(pnlBasePendientes, "card2");
        } else {
            for (Clases.VueloOperativo vo : pendientes) {
                String cod = vo.getCodVuelo();
                String ruta = vo.getVueloBase().getOrigenDestino();
                String mat = vo.getVueloBase().getMatricula();

                ElementosDiseño.TarjetaVueloPendiente tarjeta = new ElementosDiseño.TarjetaVueloPendiente(cod, ruta, mat);

                // Alineamos la tarjeta a la izquierda para que el BoxLayout no la centre raro
                tarjeta.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

                pnlListaItemsVuelo.add(tarjeta);

                // Agregamos un "espacio rígido" invisible de 8 píxeles para separar las tarjeta
                pnlListaItemsVuelo.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 8)));
            }
            carta.show(pnlBasePendientes, "card3");
        }

        // Refrescamos la pantalla
        pnlListaItemsVuelo.revalidate();
        pnlListaItemsVuelo.repaint();
    }
    
// ==============================================================
// MÉTODO PARA MAPEAR LOS CAMPOS DE COMBUSTIBLE DEL FORMULARIO A UN OBJETO DE DOMINIO
// ==============================================================
    private Clases.ManifiestoCombustible construirManifiestoDesdeFormulario() {
        double combRuta = txtFieldCombRuta.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtFieldCombRuta.getText().trim());
        double combRes = txtFieldCombReserva.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtFieldCombReserva.getText().trim());

        Clases.ManifiestoCombustible manifiesto = new Clases.ManifiestoCombustible();
        manifiesto.setCombustibleRutaKg(combRuta);
        manifiesto.setCombustibleReservaKg(combRes);
        return manifiesto;
    }

// ==============================================================
// MÉTODO PARA MAPEAR LOS CAMPOS DE PESO DEL FORMULARIO A UN OBJETO DE DOMINIO
// ==============================================================
    private Clases.HojaDeCarga construirHojaDeCargaDesdeFormulario(Clases.ManifiestoCombustible manifiesto) {
        double pas = txtFieldPasajeros.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtFieldPasajeros.getText().trim());
        double eq = txtFieldEquipaje.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtFieldEquipaje.getText().trim());
        double car = txtFieldCarga.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtFieldCarga.getText().trim());

        Clases.HojaDeCarga hoja = new Clases.HojaDeCarga();
        hoja.setPesoPasajeros(pas);
        hoja.setPesoEquipaje(eq);
        hoja.setPesoCarga(car);
        hoja.setPesoCombustible(manifiesto.calcularCombustibleTotal());
        return hoja;
    }

// ==============================================================
// MÉTODO PARA VALIDAR QUE EL METAR INGRESADO CORRESPONDA AL AEROPUERTO DE DESTINO
// ==============================================================
    private boolean metarCorrespondeADestino(String codigoMetar, Clases.VueloOperativo voSel) {
        String ruta = voSel.getVueloBase().getOrigenDestino().toUpperCase();

        // Diccionario de aeropuertos LATAM AeroOps
        String icaoDestino = "";
        if (ruta.contains("CUSCO") || ruta.contains("CUZ")) icaoDestino = "SPZO";
        else if (ruta.contains("CHIMBOTE") || ruta.contains("CHM")) icaoDestino = "SPHZ";
        else if (ruta.contains("AREQUIPA") || ruta.contains("AQP")) icaoDestino = "SPQU";
        else if (ruta.contains("JULIACA") || ruta.contains("JUL")) icaoDestino = "SPJL";

        // Si el METAR no contiene el código de destino correcto, se marca como inválido
        if (!icaoDestino.isEmpty() && !codigoMetar.contains(icaoDestino) && !codigoMetar.trim().isEmpty()) {
            return false;
        }
        return true;
    }

// ==============================================================
// MÉTODO PARA CALCULAR LOS PESOS Y METAR CON FILTRO DE SEDE EN VIVO
// ==============================================================
    private void calcularPesosEnVivo() {
        try {
            // 1. Armamos los objetos de dominio reales a partir del formulario
            //    (mismo método que usará btnAprobarPlanMouseClicked para persistir)
            Clases.ManifiestoCombustible manifiesto = construirManifiestoDesdeFormulario();
            Clases.HojaDeCarga hoja = construirHojaDeCargaDesdeFormulario(manifiesto);

            // 2. El cálculo de combustible y peso total ya NO se hace a mano en la GUI:
            //    se delega a las clases de negocio (SRP)
            double combTotal = manifiesto.calcularCombustibleTotal();
            txtFieldCombustible.setText(String.format(java.util.Locale.US, "%.2f", combTotal));

            double sumaTotal = hoja.calcularPesoTotal();
            lblSumaTotal.setText(String.format(java.util.Locale.US, "%.2f", sumaTotal));
            
            // 3. Inyección y evaluación meteorológica en vivo con filtro de Aeropuerto (Sede)
            String codigoMetar = txtAreaClima.getText().toUpperCase();
            boolean climaApto = ValidadorMetar.esClimaApto(codigoMetar);
            boolean metarCorresponde = true;

            // Verificamos a dónde va el vuelo seleccionado para exigir su código OACI
            if (cbxVuelosDespacho.getSelectedIndex() > 0) {
                Clases.VueloOperativo voSel = (Clases.VueloOperativo) cbxVuelosDespacho.getSelectedItem();
                metarCorresponde = metarCorrespondeADestino(codigoMetar, voSel);
            }
            
            // 5. Lógica Visual de la Barra, Textos y Seguridad por Capas de Carga
            if (mtowActual > 0) {
                // Cálculo de porcentajes para el renderizado de la barra de progreso
                double porcentajeReal = (sumaTotal / mtowActual) * 100;
                int porcentajeRedondeado = (int) Math.round(porcentajeReal);

                // Forzamos el límite visual de la barra para mantener consistencia gráfica
                barraMTOW.setValue(Math.min(porcentajeRedondeado, 100));
                
                // Definición de colores institucionales en formato RGB
                java.awt.Color verde = new java.awt.Color(34, 197, 94);  // Verde #22c55e
                java.awt.Color ambar = new java.awt.Color(245, 158, 11); // Naranja/Ámbar #f59e0b
                java.awt.Color rojo = new java.awt.Color(225, 29, 72);   // Rojo Carmesí #e11d48
                java.awt.Color fondoBarraOscuro = new java.awt.Color(30, 41, 59); // Fondo Dark Mode para la barra

                // Aplicación de renderizador básico para anular estilos del sistema operativo
                barraMTOW.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
                barraMTOW.setBackground(fondoBarraOscuro);

                // == CONDICIÓN 1: PESO SEGURO BAJO (0% a 85%) ==
                if (porcentajeReal <= 85.0) {
                    if (climaApto && metarCorresponde) {
                        // Estado óptimo total: Peso en rango verde, clima apto y sede correcta
                        barraMTOW.setForeground(verde);
                        lblAprobado.setText("APROBADO");
                        lblAprobado.setForeground(verde);
                        pnlFondoAprobado.setBackground(new java.awt.Color(5, 46, 22));
                        btnAprobarPlan.setEnabled(true);
                    } else {
                        // Falla meteorológica o de sede: Cambia a rojo y bloquea el botón
                        barraMTOW.setForeground(rojo);
                        lblAprobado.setForeground(new java.awt.Color(255, 255, 255));
                        pnlFondoAprobado.setBackground(rojo);
                        btnAprobarPlan.setEnabled(false);
                        
                        if (!metarCorresponde) {
                            lblAprobado.setText("METAR INVÁLIDO");
                        } else {
                            lblAprobado.setText("ALERTA CLIMA");
                        }
                    }
                    lblPorcentajeMTOW.setText(porcentajeRedondeado + "% del MTOW");
                } 
                // == CONDICIÓN 2: PESO AL LÍMITE DE CAPACIDAD (85.1% a 100%) ==
                else if (porcentajeReal <= 100.0) {
                    if (climaApto && metarCorresponde) {
                        // Estado legal pesado: Peso en rango ámbar pero aceptable, clima y sede correctos
                        barraMTOW.setForeground(ambar);
                        lblAprobado.setText("APROBADO");
                        lblAprobado.setForeground(ambar);
                        pnlFondoAprobado.setBackground(new java.awt.Color(66, 32, 6));
                        btnAprobarPlan.setEnabled(true);
                    } else {
                        // Falla meteorológica o de sede estando pesado: Cambia a rojo y bloquea el botón
                        barraMTOW.setForeground(rojo);
                        lblAprobado.setForeground(new java.awt.Color(255, 255, 255));
                        pnlFondoAprobado.setBackground(rojo);
                        btnAprobarPlan.setEnabled(false);
                        
                        if (!metarCorresponde) {
                            lblAprobado.setText("METAR INVÁLIDO");
                        } else {
                            lblAprobado.setText("ALERTA CLIMA");
                        }
                    }
                    lblPorcentajeMTOW.setText(porcentajeRedondeado + "% del MTOW");
                } 
                // == CONDICIÓN 3: EN SOBREPESO CRÍTICO (> 100%) ==
                else {
                    // Estado ilegal por peso: Bloquea el despacho de forma mandatoria
                    double excedente = sumaTotal - mtowActual;
                    barraMTOW.setForeground(rojo);
                    lblAprobado.setForeground(new java.awt.Color(251, 113, 133));
                    pnlFondoAprobado.setBackground(new java.awt.Color(76, 5, 25));
                    btnAprobarPlan.setEnabled(false);
                    
                    if (!metarCorresponde) {
                        lblAprobado.setText("EXCEDIDO & METAR INVÁLIDO");
                    } else if (!climaApto) {
                        lblAprobado.setText("CRÍTICO: PESO Y CLIMA");
                    } else {
                        lblAprobado.setText("¡EXCEDIDO!");
                    }
                    
                    lblPorcentajeMTOW.setText("100% del MTOW · Excede en " + String.format(java.util.Locale.US, "%,.2f", excedente) + " kg");
                }
                
                // Redibujado inmediato de los elementos en pantalla
                barraMTOW.repaint();
            }
        } catch (NumberFormatException e) {
            // Manejo silencioso de excepciones numéricas durante el tipeo transitorio
        } 
    }

// ==============================================================
// MÉTODO PARA CARGAR LOS VUELOS EN LA VISTA DESPACHO
// ==============================================================    
    private void cargarVuelosEnDespacho() {
        ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();
        java.util.List<Clases.VueloOperativo> vuelosDespacho = dao.obtenerVuelosDetalladosParaDespacho();

        // Usamos la variable genérica para saltar la restricción de NetBeans
        javax.swing.JComboBox comboDespacho = cbxVuelosDespacho;
        comboDespacho.removeAllItems();

        // Rescatamos el CardLayout de tu panel central de Despacho
        java.awt.CardLayout cartaDespacho = (java.awt.CardLayout) pnlDespachoCuerpo.getLayout();

        if (vuelosDespacho.isEmpty()) {
            // Si no hay vuelos operativos pendientes, mostramos el panel vacío ("card2")
            cartaDespacho.show(pnlDespachoCuerpo, "card2");
        } else {
            // Si hay vuelos, cargamos el combo con los objetos puros y mostramos el panel lleno ("card3")
            comboDespacho.addItem("— Seleccionar Vuelo —");
            for (Clases.VueloOperativo vo : vuelosDespacho) {
                // El combo mostrará el código gracias a un truco interno o lo manejamos directo
                comboDespacho.addItem(vo); 
            }
            cartaDespacho.show(pnlDespachoCuerpo, "card3");
        }
    }
    
// ====================================================================
// MÉTODO PARA CARGAR LOS VUELOS AL COMBOX DE ELECCION DE CONTROL OOOI
// ====================================================================
private void cargarVuelosEnControlOOOI() {
    ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();
    java.util.List<Clases.VueloOperativo> vuelosOOOI = dao.obtenerVuelosParaControlOOOI();
    
    // 🛠️ EL TRUCO: Creamos una variable cruda para saltar la restricción de String de NetBeans
    javax.swing.JComboBox comboOOOI = cbxVuelosOOOI;
    
    comboOOOI.removeAllItems();
    
    if (vuelosOOOI.isEmpty()) {
        comboOOOI.addItem("— No hay vuelos para control —");
        sincronizarBotonesOOOI(null); // Apaga la interfaz si no hay nada
    } else {
        comboOOOI.addItem("— Seleccionar Vuelo —");
        for (Clases.VueloOperativo vo : vuelosOOOI) {
            comboOOOI.addItem(vo); // ¡Ahora el compilador te aceptará el objeto sin chistar!
        }
    }
}

// ==============================================================
// MÉTODO PARA SINCRONIZAR LOS BOTONES DE CONTROL OOOI 
// ==============================================================    
    private void sincronizarBotonesOOOI(Clases.VueloOperativo vo) {
        // 1. Por defecto, apagamos y ponemos en gris todos
        aplicarEstiloBotonOOOI(btnOUT, lblOUT, lblPushBack, lblTiempoOUT, "BLOQUEADO");
        aplicarEstiloBotonOOOI(btnOFF, lblOFF, lblTakeOff, lblTiempoOFF, "BLOQUEADO");
        aplicarEstiloBotonOOOI(btnON, lblON, lblLanding, lblTiempoON, "BLOQUEADO");
        aplicarEstiloBotonOOOI(btnIN, lblIN, lblGateArrival, lblTiempoIN, "BLOQUEADO");

        if (vo == null) {
            lblTiempoOUT.setText("--:--Z"); lblTiempoOFF.setText("--:--Z");
            lblTiempoON.setText("--:--Z"); lblTiempoIN.setText("--:--Z");
            return;
        }

        // 2. Cargamos los tiempos
        lblTiempoOUT.setText(vo.getHoraOut() != null ? vo.getHoraOut() : "--:--Z");
        lblTiempoOFF.setText(vo.getHoraOff() != null ? vo.getHoraOff() : "--:--Z");
        lblTiempoON.setText(vo.getHoraOn() != null ? vo.getHoraOn() : "--:--Z");
        lblTiempoIN.setText(vo.getHoraIn() != null ? vo.getHoraIn() : "--:--Z");

        String estado = vo.getEstadoOOOI() != null ? vo.getEstadoOOOI() : "PENDIENTE";

        // 3. Lógica de cascada visual
        if (estado.equals("PENDIENTE")) {
            aplicarEstiloBotonOOOI(btnOUT, lblOUT, lblPushBack, lblTiempoOUT, "HABILITADO");
        } 
        else if (estado.equals("OUT")) {
            aplicarEstiloBotonOOOI(btnOUT, lblOUT, lblPushBack, lblTiempoOUT, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnOFF, lblOFF, lblTakeOff, lblTiempoOFF, "HABILITADO");
        } 
        else if (estado.equals("OFF")) {
            aplicarEstiloBotonOOOI(btnOUT, lblOUT, lblPushBack, lblTiempoOUT, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnOFF, lblOFF, lblTakeOff, lblTiempoOFF, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnON, lblON, lblLanding, lblTiempoON, "HABILITADO");
        } 
        else if (estado.equals("ON")) {
            aplicarEstiloBotonOOOI(btnOUT, lblOUT, lblPushBack, lblTiempoOUT, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnOFF, lblOFF, lblTakeOff, lblTiempoOFF, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnON, lblON, lblLanding, lblTiempoON, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnIN, lblIN, lblGateArrival, lblTiempoIN, "HABILITADO");
        } 
        else if (estado.equals("IN")) {
            aplicarEstiloBotonOOOI(btnOUT, lblOUT, lblPushBack, lblTiempoOUT, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnOFF, lblOFF, lblTakeOff, lblTiempoOFF, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnON, lblON, lblLanding, lblTiempoON, "COMPLETADO");
            aplicarEstiloBotonOOOI(btnIN, lblIN, lblGateArrival, lblTiempoIN, "COMPLETADO");
        }
    }
    
// ==============================================
// MÉTODO PARA LOS COLORES DE LOS CONTROLES OOOI
// ==============================================      
    private void aplicarEstiloBotonOOOI(javax.swing.JPanel panel, javax.swing.JLabel lblMain, javax.swing.JLabel lblSub, javax.swing.JLabel lblTime, String estadoEstilo) {
        if (estadoEstilo.equals("COMPLETADO")) {
            // Verde Neón (Ya se pasó por esta fase)
            panel.setBackground(new java.awt.Color(5, 46, 22)); 
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 197, 94), 2, true)); 
            lblMain.setForeground(new java.awt.Color(74, 222, 128)); 
            lblSub.setForeground(new java.awt.Color(248, 250, 252));
            lblTime.setForeground(new java.awt.Color(248, 250, 252));
            panel.setEnabled(false); // Se bloquea para no volver a pulsarlo

        } else if (estadoEstilo.equals("HABILITADO")) {
            // Rojo Neón (Es el botón que toca pulsar ahora)
            panel.setBackground(new java.awt.Color(45, 10, 30)); // #2d0a1e
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 29, 72), 2, true)); // #e11d48
            lblMain.setForeground(new java.awt.Color(251, 113, 133)); // #fb7185
            lblSub.setForeground(new java.awt.Color(248, 250, 252));
            lblTime.setForeground(new java.awt.Color(248, 250, 252));
            panel.setEnabled(true); // Se habilita el clic

        } else { 
            // BLOQUEADO: Gris oscuro (Aún no es su turno)
            panel.setBackground(new java.awt.Color(24, 34, 52)); 
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85), 2, true));
            lblMain.setForeground(new java.awt.Color(115, 123, 134)); 
            lblSub.setForeground(new java.awt.Color(115, 123, 134));
            lblTime.setForeground(new java.awt.Color(115, 123, 134));
            panel.setEnabled(false); // Se bloquea
        }
    }
    
// ========================================
// MÉTODO PARA EL CLICK A LOS BOTONES OOOI
// ========================================      
    private void procesarClicControlOOOI(String faseAvanzar, javax.swing.JPanel panelPulsado) {
        // Candado de seguridad: Si el panel está "apagado", ignoramos el clic
        if (!panelPulsado.isEnabled() || cbxVuelosOOOI.getSelectedIndex() <= 0) return;

        Clases.VueloOperativo voSel = (Clases.VueloOperativo) cbxVuelosOOOI.getSelectedItem();

        // Generador de Hora Zulu
        java.text.SimpleDateFormat formatoZulu = new java.text.SimpleDateFormat("HH:mm'Z'");
        formatoZulu.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        String horaActual = formatoZulu.format(new java.util.Date());

        ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();

        // Registramos en BD
        if(dao.registrarFaseOOOI(voSel.getCodVuelo(), faseAvanzar, horaActual)){
            // Actualizamos memoria
            voSel.setEstadoOOOI(faseAvanzar);
            if(faseAvanzar.equals("OUT")) voSel.setHoraOut(horaActual);
            else if(faseAvanzar.equals("OFF")) {
                voSel.setHoraOff(horaActual);
                dao.actualizarEstadoVuelo(voSel.getCodVuelo(), "EN_VUELO"); // Actualiza a la vez el estado general
            }
            else if(faseAvanzar.equals("ON")) voSel.setHoraOn(horaActual);
            else if(faseAvanzar.equals("IN")) {
                voSel.setHoraIn(horaActual);
                dao.actualizarEstadoVuelo(voSel.getCodVuelo(), "EN_TIERRA");
            }

            // Volvemos a pintar la interfaz
            sincronizarBotonesOOOI(voSel);
        }
    }

// ==============================================
// MÉTODO PARA CARGAR COMBOX VUELOS PARA LOGBOOK
// ==============================================
    private void cargarComboBoxVuelosLogbook() {
        ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO();
        ArrayList<Clases.VueloOperativo> vuelosIn = dao.obtenerVuelosParaLogbook();

        cbxVuelosLogbook.removeAllItems(); // ⚠️ ¡CRÍTICO! Limpia el combo primero
        cbxVuelosLogbook.addItem("— Seleccione un Vuelo —"); // Opción por defecto (index 0)

        for (Clases.VueloOperativo vuelo : vuelosIn) {
            cbxVuelosLogbook.addItem(vuelo); // Agregamos el objeto puro
        }
    }
    
// ========================================
// MÉTODO PARA HABILITAR PANEL LOGBOOK
// ========================================      
    private void habilitarPanelLogbook(boolean estado) {
        // Campos de texto
        txtCombustibleRestante.setEnabled(estado);

        // Radio Buttons
        rbtnSinFallas.setEnabled(estado);
        rbtnPrioridadBaja.setEnabled(estado);
        rbtnPrioridadMedia.setEnabled(estado);
        rbtnPrioridadAlta.setEnabled(estado);

        // Área de texto y botón final
        txtReporteFallas.setEnabled(estado);
        btnCerrarVuelo.setEnabled(estado);

        // Lógica extra: Si se está habilitando el panel, reseteamos a los valores por defecto
        if (estado) {
            rbtnSinFallas.setSelected(true);
            txtReporteFallas.setText("NIL - Operación sin novedades");
            txtReporteFallas.setEnabled(false); // Se bloquea por defecto porque "Sin Fallas" está marcado
        } else {
            txtCombustibleRestante.setText("");
            txtReporteFallas.setText("");
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollAsignacionVuelos;
    private javax.swing.JScrollPane ScrollPendientesDespacho;
    private javax.swing.JScrollPane ScrollTablaFlota;
    private javax.swing.JScrollPane ScrollTablaHistorialVuelos;
    private javax.swing.JScrollPane ScrollTripulacion;
    private javax.swing.JScrollPane ScrollTxtAreaClima;
    private javax.swing.JScrollPane ScrollTxtReporteFallas;
    private javax.swing.JTable TblFlota;
    private javax.swing.JTable TblHistorialVuelo;
    private javax.swing.JProgressBar barraMTOW;
    private javax.swing.JPanel bordeSuperior;
    private javax.swing.JLabel btnAprobarPlan;
    private ElementosDiseño.BotonMenu btnAsigancionVuelos;
    private ElementosDiseño.BotonMenu btnAutorizarDespacho;
    private javax.swing.JLabel btnCancelarVuelo;
    private javax.swing.JLabel btnCerrarSesion;
    private javax.swing.JLabel btnCerrarVuelo;
    private javax.swing.JLabel btnCrearVuelo;
    private javax.swing.JLabel btnDeclararDemora;
    private ElementosDiseño.BotonMenu btnGestionFlota;
    private ElementosDiseño.BotonMenu btnHistorialVuelos;
    private javax.swing.JPanel btnIN;
    private javax.swing.JLabel btnLimpiar;
    private javax.swing.JLabel btnLimpiarFiltros;
    private javax.swing.JLabel btnLimpiarFiltrosFlota;
    private javax.swing.JPanel btnOFF;
    private javax.swing.JPanel btnON;
    private javax.swing.JPanel btnOUT;
    private javax.swing.JComboBox<String> cbxFiltroAeronave;
    private javax.swing.JComboBox<String> cbxFiltroEstadoAeronave;
    private javax.swing.JComboBox<String> cbxFiltroEstadoVuelo;
    private javax.swing.JComboBox<String> cbxFiltroMatricula;
    private javax.swing.JComboBox<String> cbxFiltroModelo;
    private javax.swing.JComboBox<String> cbxFiltroVuelo;
    private javax.swing.JComboBox<String> cbxSeleccionCAP;
    private javax.swing.JComboBox<String> cbxSeleccionFO;
    private javax.swing.JComboBox<String> cbxSeleccionVuelo;
    private javax.swing.JComboBox<String> cbxVuelosDespacho;
    private javax.swing.JComboBox cbxVuelosLogbook;
    private javax.swing.JComboBox<String> cbxVuelosOOOI;
    private javax.swing.JPanel fondoBarraLateral;
    private javax.swing.JPanel fondoBtnAprobarPlan;
    private javax.swing.JPanel fondoBtnCancelarVuelo;
    private javax.swing.JPanel fondoBtnCancelarVuelo3;
    private javax.swing.JPanel fondoBtnCerrarSesion;
    private javax.swing.JPanel fondoBtnCrearVuelo;
    private javax.swing.JPanel fondoBtnDeclararDemora;
    private javax.swing.JPanel fondoBtnLimpiar;
    private javax.swing.JPanel fondoBtnLimpiarFiltros;
    private javax.swing.JPanel fondoBtnLimpiarFiltrosFlota;
    private javax.swing.JPanel fondoBtnSalir;
    private javax.swing.JPanel fondoContadores;
    private javax.swing.JPanel fondoContadores1;
    private javax.swing.JPanel fondoContadores2;
    private javax.swing.ButtonGroup grupoPrioridadFallas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblAeronavesAptas;
    private javax.swing.JLabel lblAprobado;
    private javax.swing.JLabel lblAprobados;
    private javax.swing.JLabel lblAprobados1;
    private javax.swing.JLabel lblAsignacionVuelos;
    private javax.swing.JLabel lblCancelados;
    private javax.swing.JLabel lblCancelados1;
    private javax.swing.JLabel lblCapitan;
    private javax.swing.JLabel lblCargaKG;
    private javax.swing.JLabel lblClimaDestino;
    private javax.swing.JLabel lblCombReserva;
    private javax.swing.JLabel lblCombRuta;
    private javax.swing.JLabel lblCombustibleKGSuma;
    private javax.swing.JLabel lblContadorAeronavesAptas;
    private javax.swing.JLabel lblContadorAprobados;
    private javax.swing.JLabel lblContadorAprobados1;
    private javax.swing.JLabel lblContadorCancelados;
    private javax.swing.JLabel lblContadorCancelados1;
    private javax.swing.JLabel lblContadorPendientesDemora;
    private javax.swing.JLabel lblContadorPendientesDemora1;
    private javax.swing.JLabel lblContadorPendientesDespacho;
    private javax.swing.JLabel lblContadorPersonalLibre;
    private javax.swing.JLabel lblContadorVuelosActivos;
    private javax.swing.JLabel lblCreacionAsignacionVuelo;
    private javax.swing.JLabel lblCreacionAsignacionVuelo1;
    private javax.swing.JLabel lblCreacionAsignacionVuelo2;
    private javax.swing.JLabel lblDespachoTecnico;
    private javax.swing.JLabel lblDespachoTecnico1;
    private javax.swing.JLabel lblDespachoTecnico3;
    private javax.swing.JLabel lblEquipajeKG;
    private javax.swing.JLabel lblFiltroAeronave;
    private javax.swing.JLabel lblFiltroEstadoAeronave;
    private javax.swing.JLabel lblFiltroEstadoVuelo;
    private javax.swing.JLabel lblFiltroMatricula;
    private javax.swing.JLabel lblFiltroModelo;
    private javax.swing.JLabel lblFiltroVuelo;
    private javax.swing.JLabel lblGateArrival;
    private javax.swing.JLabel lblHistorialVuelo;
    private javax.swing.JLabel lblIN;
    private javax.swing.JLabel lblLanding;
    private javax.swing.JLabel lblModuloActivo;
    private javax.swing.JLabel lblModuloActivo1;
    private javax.swing.JLabel lblModuloactivo;
    private javax.swing.JLabel lblModuloactivo1;
    private javax.swing.JLabel lblModulos;
    private javax.swing.JLabel lblOFF;
    private javax.swing.JLabel lblON;
    private javax.swing.JLabel lblOUT;
    private javax.swing.JLabel lblPasajerosKG;
    private javax.swing.JLabel lblPendientesDemora;
    private javax.swing.JLabel lblPendientesDemora1;
    private javax.swing.JLabel lblPendientesDespacho;
    private javax.swing.JLabel lblPersonalLibre;
    private javax.swing.JLabel lblPesoMaximo;
    private javax.swing.JLabel lblPorcentajeMTOW;
    private javax.swing.JLabel lblPrimerOficial;
    private javax.swing.JLabel lblPushBack;
    private javax.swing.JLabel lblRegistroFlota;
    private javax.swing.JLabel lblRequerimientosTecnicos;
    private javax.swing.JLabel lblResumenRuta;
    private javax.swing.JLabel lblRolSistema;
    private javax.swing.JLabel lblSeleccioneGrupo;
    private javax.swing.JLabel lblSistemaControlTierra;
    private javax.swing.JLabel lblSistemaOnline;
    private javax.swing.JLabel lblSlash;
    private javax.swing.JLabel lblSumaTotal;
    private javax.swing.JLabel lblSumatoriaPesos;
    private javax.swing.JLabel lblTakeOff;
    private javax.swing.JLabel lblTiempoIN;
    private javax.swing.JLabel lblTiempoOFF;
    private javax.swing.JLabel lblTiempoON;
    private javax.swing.JLabel lblTiempoOUT;
    private javax.swing.JLabel lblTripulacionCabina;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVueloProgramado;
    private javax.swing.JLabel lblVueloProgramado1;
    private javax.swing.JLabel lblVueloProgramado2;
    private javax.swing.JLabel lblVueloProgramado3;
    private javax.swing.JLabel lblVueloProgramado4;
    private javax.swing.JLabel lblVuelosActivos;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logoBorde;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelEncabezado3;
    private javax.swing.JPanel panelEncabezado4;
    private javax.swing.JPanel panelEncabezado5;
    private javax.swing.JPanel panelEncabezado6;
    private javax.swing.JPanel pnlAsignacion;
    private javax.swing.JPanel pnlAsignacionCuerpo;
    private javax.swing.JPanel pnlBasePendientes;
    private javax.swing.JPanel pnlColorAeronavesAptas;
    private javax.swing.JPanel pnlColorAprobados;
    private javax.swing.JPanel pnlColorAprobados1;
    private javax.swing.JPanel pnlColorCancelados;
    private javax.swing.JPanel pnlColorCancelados1;
    private javax.swing.JPanel pnlColorModuloActivo;
    private javax.swing.JPanel pnlColorModuloActivo1;
    private javax.swing.JPanel pnlColorPendientesDespacho;
    private javax.swing.JPanel pnlColorPersonalLibre;
    private javax.swing.JPanel pnlColorVuelosActivos;
    private javax.swing.JPanel pnlContadorAeronavesAptas;
    private javax.swing.JPanel pnlContadorAprobados;
    private javax.swing.JPanel pnlContadorAprobados1;
    private javax.swing.JPanel pnlContadorCancelados;
    private javax.swing.JPanel pnlContadorCancelados1;
    private javax.swing.JPanel pnlContadorModuloActivo;
    private javax.swing.JPanel pnlContadorModuloActivo1;
    private javax.swing.JPanel pnlContadorPendientesDemora;
    private javax.swing.JPanel pnlContadorPendientesDemora1;
    private javax.swing.JPanel pnlContadorPendientesDespacho;
    private javax.swing.JPanel pnlContadorPersonalLibre;
    private javax.swing.JPanel pnlContadorVuelosActivos;
    private javax.swing.JPanel pnlContendorTCP;
    private javax.swing.JPanel pnlContenedorPrincipal;
    private javax.swing.JPanel pnlContenidoAsignacion;
    private javax.swing.JPanel pnlContenidoDespacho;
    private javax.swing.JPanel pnlControlOOOI;
    private javax.swing.JPanel pnlCreacionAsignacionVuelo;
    private javax.swing.JPanel pnlDespacho;
    private javax.swing.JPanel pnlDespachoCuerpo;
    private javax.swing.JPanel pnlFiltrosFlota;
    private javax.swing.JPanel pnlFiltrosHistorial;
    private javax.swing.JPanel pnlFlota;
    private javax.swing.JPanel pnlFlotaCuerpo;
    private javax.swing.JPanel pnlFondoAprobado;
    private javax.swing.JPanel pnlFondoCalculoMTOW;
    private javax.swing.JPanel pnlFondoLogoBorde;
    private javax.swing.JPanel pnlFondoPerfil;
    private javax.swing.JPanel pnlFondoResumenRuta;
    private javax.swing.JPanel pnlFondoSistemaOnline;
    private javax.swing.JPanel pnlFondoTxtArea;
    private javax.swing.JPanel pnlHistorial;
    private javax.swing.JPanel pnlHistorialCuerpo;
    private javax.swing.JPanel pnlListaItemsVuelo;
    private javax.swing.JPanel pnlLleno;
    private javax.swing.JPanel pnlLogBook;
    private javax.swing.JPanel pnlPendientesDespacho;
    private javax.swing.JPanel pnlPendientesLleno;
    private javax.swing.JPanel pnlPendientesVacio;
    private javax.swing.JPanel pnlTablaDinamicaFlota;
    private javax.swing.JPanel pnlTablaDinamicaHistorial;
    private javax.swing.JPanel pnlVacio;
    private javax.swing.JPanel pnlVacio1;
    private javax.swing.JRadioButton rbtnPrioridadAlta;
    private javax.swing.JRadioButton rbtnPrioridadBaja;
    private javax.swing.JRadioButton rbtnPrioridadMedia;
    private javax.swing.JRadioButton rbtnSinFallas;
    private javax.swing.JTextArea txtAreaClima;
    private javax.swing.JLabel txtBtnSalir;
    private javax.swing.JTextField txtCombustibleRestante;
    private javax.swing.JTextField txtFieldCarga;
    private javax.swing.JTextField txtFieldCombReserva;
    private javax.swing.JTextField txtFieldCombRuta;
    private javax.swing.JTextField txtFieldCombustible;
    private javax.swing.JTextField txtFieldEquipaje;
    private javax.swing.JTextField txtFieldPasajeros;
    private javax.swing.JTextArea txtReporteFallas;
    // End of variables declaration//GEN-END:variables
}
