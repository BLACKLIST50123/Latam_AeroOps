package Interfaces;
import java.awt.Color;
import javax.swing.JFrame;
import static javax.swing.UIManager.put;

/* ¿Para qué sirve esta pantalla?: Esta es la pantalla de inicio de sesión, la primera que ve cualquier persona al abrir la aplicación. Aquí el usuario escribe su nombre de usuario y su contraseña para entrar al sistema. Según el rol que tenga la cuenta (Oficial de Operaciones o Técnico de Mantenimiento), la aplicación abre automáticamente la pantalla que le corresponde. También tiene un botón para reiniciar los datos de prueba de la base de datos, pensado solamente para usarse durante el desarrollo, y un botón para salir de la aplicación */

public class Login_GUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login_GUI.class.getName());
        private boolean cargandoLogin = false;  // variable para evitar enter y click al mismo tiempo en login

    // ==========================================
    // MÉTODO CONSTRUCTOR DE LA PANTALLA
    // ==========================================
    // Descripción: Arma todos los elementos visuales de la pantalla de login (con initComponents, que es generado por el diseñador), la deja en pantalla completa, revisa que la conexión a la base de datos funcione al arrancar, y configura que la tecla Enter también sirva para iniciar sesión
    // Qué otros métodos la activan: Se ejecuta automáticamente al crear la pantalla, ya sea desde OperacionDeVuelos o desde el método main() de esta misma clase
    public Login_GUI() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Esto expande a FullScreen al iniciar

        // Verificación temprana de conexión a BD. A diferencia del código anterior,
        // ConexionBD ya no decide por sí sola cómo avisar del error (no depende de
        // Swing); es esta clase de presentación la responsable de mostrar el diálogo.
        try {
            BaseDeDatos.ConexionBD.getInstancia();
        } catch (BaseDeDatos.ConexionBDException e) {
            logger.log(java.util.logging.Level.SEVERE, "No se pudo conectar a la base de datos al iniciar", e);
            ElementosDiseño.NotificacionDialog.error(this,
                "No fue posible conectar con la base de datos. Verifique que el servidor PostgreSQL esté disponible.\n\n" + e.getMessage(),
                "Error de Conexión");
        }
    // =========================================================================
    // MAPEO DE LA TECLA ENTER CUANDO EL BOTÓN ES UN JLABEL
    // =========================================================================
        javax.swing.JRootPane root = this.getRootPane();
        root.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(javax.swing.KeyStroke.getKeyStroke("ENTER"), "presionarEnter");

        root.getActionMap().put("presionarEnter", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                iniciarSesionProceso(); // <-- Llamamos al método unificado
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bordeSuperior = new javax.swing.JPanel();
        fondoBtnSalir = new javax.swing.JPanel();
        txtBtnSalir = new javax.swing.JLabel();
        pnlFondoLogoBorde = new javax.swing.JPanel();
        logoBorde = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlFondoLogin = new javax.swing.JPanel();
        pnlContenedorLogin = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        campoUsuario = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        pnlFondoBtnIniciarSesion = new javax.swing.JPanel();
        btnIniciarSesion = new javax.swing.JLabel();
        campoContraseña = new javax.swing.JPasswordField();
        lblError = new javax.swing.JLabel();
        pnlLogoLatam = new javax.swing.JPanel();
        LogoLatam = new javax.swing.JLabel();
        pnlFondoSistemaOnline = new javax.swing.JPanel();
        lblSistemaOnline = new javax.swing.JLabel();
        lblPortalOperacionesTierra = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        fondoBtnReiniciarVuelos = new javax.swing.JPanel();
        btnReiniciarVuelos = new javax.swing.JLabel();

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
            .addComponent(txtBtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(logoBorde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LATAM AERO OPS");

        javax.swing.GroupLayout bordeSuperiorLayout = new javax.swing.GroupLayout(bordeSuperior);
        bordeSuperior.setLayout(bordeSuperiorLayout);
        bordeSuperiorLayout.setHorizontalGroup(
            bordeSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bordeSuperiorLayout.createSequentialGroup()
                .addComponent(pnlFondoLogoBorde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 996, Short.MAX_VALUE)
                .addComponent(fondoBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        bordeSuperiorLayout.setVerticalGroup(
            bordeSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bordeSuperiorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(bordeSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlFondoLogoBorde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fondoBtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(bordeSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(bordeSuperior, java.awt.BorderLayout.NORTH);

        pnlFondoLogin.setBackground(new java.awt.Color(15, 23, 42));
        pnlFondoLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        pnlContenedorLogin.setBackground(new java.awt.Color(30, 41, 59));
        pnlContenedorLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 65, 85), 1, true));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("USUARIO:");

        campoUsuario.setBackground(new java.awt.Color(15, 23, 42));
        campoUsuario.setForeground(new java.awt.Color(255, 255, 255));

        lblContraseña.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblContraseña.setForeground(new java.awt.Color(255, 255, 255));
        lblContraseña.setText("CONTRASEÑA: ");

        pnlFondoBtnIniciarSesion.setBackground(new java.awt.Color(225, 29, 72));

        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIniciarSesion.setText("INICIAR SESION");
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlFondoBtnIniciarSesionLayout = new javax.swing.GroupLayout(pnlFondoBtnIniciarSesion);
        pnlFondoBtnIniciarSesion.setLayout(pnlFondoBtnIniciarSesionLayout);
        pnlFondoBtnIniciarSesionLayout.setHorizontalGroup(
            pnlFondoBtnIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );
        pnlFondoBtnIniciarSesionLayout.setVerticalGroup(
            pnlFondoBtnIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        campoContraseña.setBackground(new java.awt.Color(15, 23, 42));
        campoContraseña.setForeground(new java.awt.Color(255, 255, 255));

        lblError.setBackground(new java.awt.Color(30, 41, 59));
        lblError.setForeground(new java.awt.Color(255, 255, 255));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlContenedorLoginLayout = new javax.swing.GroupLayout(pnlContenedorLogin);
        pnlContenedorLogin.setLayout(pnlContenedorLoginLayout);
        pnlContenedorLoginLayout.setHorizontalGroup(
            pnlContenedorLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLoginLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlContenedorLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoUsuario)
                    .addComponent(campoContraseña)
                    .addComponent(pnlFondoBtnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContenedorLoginLayout.setVerticalGroup(
            pnlContenedorLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLoginLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(pnlFondoBtnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnlLogoLatam.setBackground(new java.awt.Color(15, 23, 42));

        LogoLatam.setBackground(new java.awt.Color(30, 41, 59));
        LogoLatam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogoLatam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_imagenes/LogoLogin.png"))); // NOI18N

        javax.swing.GroupLayout pnlLogoLatamLayout = new javax.swing.GroupLayout(pnlLogoLatam);
        pnlLogoLatam.setLayout(pnlLogoLatamLayout);
        pnlLogoLatamLayout.setHorizontalGroup(
            pnlLogoLatamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogoLatam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLogoLatamLayout.setVerticalGroup(
            pnlLogoLatamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogoLatam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );

        pnlFondoSistemaOnline.setBackground(new java.awt.Color(5, 46, 22));
        pnlFondoSistemaOnline.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 46, 22), 1, true));

        lblSistemaOnline.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSistemaOnline.setForeground(new java.awt.Color(34, 197, 94));
        lblSistemaOnline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSistemaOnline.setText("SISTEMA ONLINE");
        lblSistemaOnline.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 46, 22), 1, true));

        javax.swing.GroupLayout pnlFondoSistemaOnlineLayout = new javax.swing.GroupLayout(pnlFondoSistemaOnline);
        pnlFondoSistemaOnline.setLayout(pnlFondoSistemaOnlineLayout);
        pnlFondoSistemaOnlineLayout.setHorizontalGroup(
            pnlFondoSistemaOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSistemaOnline, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        pnlFondoSistemaOnlineLayout.setVerticalGroup(
            pnlFondoSistemaOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSistemaOnline, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        lblPortalOperacionesTierra.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblPortalOperacionesTierra.setForeground(new java.awt.Color(255, 255, 255));
        lblPortalOperacionesTierra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPortalOperacionesTierra.setText("PORTAL OPERACIONES TIERRA");

        lblVersion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblVersion.setForeground(new java.awt.Color(148, 163, 184));
        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVersion.setText("Portal Operaciones Tierra - V1.0");

        fondoBtnReiniciarVuelos.setBackground(new java.awt.Color(30, 41, 59));

        btnReiniciarVuelos.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnReiniciarVuelos.setForeground(new java.awt.Color(255, 255, 255));
        btnReiniciarVuelos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnReiniciarVuelos.setText("REINICIAR VUELOS");
        btnReiniciarVuelos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReiniciarVuelos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReiniciarVuelosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReiniciarVuelosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReiniciarVuelosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReiniciarVuelosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnReiniciarVuelosMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout fondoBtnReiniciarVuelosLayout = new javax.swing.GroupLayout(fondoBtnReiniciarVuelos);
        fondoBtnReiniciarVuelos.setLayout(fondoBtnReiniciarVuelosLayout);
        fondoBtnReiniciarVuelosLayout.setHorizontalGroup(
            fondoBtnReiniciarVuelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReiniciarVuelos, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        );
        fondoBtnReiniciarVuelosLayout.setVerticalGroup(
            fondoBtnReiniciarVuelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReiniciarVuelos, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlFondoLoginLayout = new javax.swing.GroupLayout(pnlFondoLogin);
        pnlFondoLogin.setLayout(pnlFondoLoginLayout);
        pnlFondoLoginLayout.setHorizontalGroup(
            pnlFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLoginLayout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addGroup(pnlFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlLogoLatam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlContenedorLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPortalOperacionesTierra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(405, Short.MAX_VALUE))
            .addGroup(pnlFondoLoginLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFondoLoginLayout.createSequentialGroup()
                        .addComponent(fondoBtnReiniciarVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlFondoLoginLayout.createSequentialGroup()
                        .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlFondoSistemaOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        pnlFondoLoginLayout.setVerticalGroup(
            pnlFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFondoLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFondoLoginLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlFondoSistemaOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFondoLoginLayout.createSequentialGroup()
                        .addComponent(pnlLogoLatam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lblPortalOperacionesTierra)
                        .addGap(18, 18, 18)
                        .addComponent(pnlContenedorLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, Short.MAX_VALUE)
                        .addComponent(fondoBtnReiniciarVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        getContentPane().add(pnlFondoLogin, java.awt.BorderLayout.CENTER);

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

    private void btnIniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseClicked
            iniciarSesionProceso(); //METODO DE INICIO DE SESIO ABAJO
    }//GEN-LAST:event_btnIniciarSesionMouseClicked

    private void btnReiniciarVuelosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReiniciarVuelosMouseClicked
        try {
        // Instancia de laa classe DAO donde está el método
        ClasesDAO.VueloOperativoDAO dao = new ClasesDAO.VueloOperativoDAO(); 

        // El método puede lanzar la excepción aquí.
        boolean exito = dao.reiniciarDatosPrueba();

        if (exito) {
            ElementosDiseño.NotificacionDialog.exito(this, "La base de datos ha sido restablecida con éxito.", "Éxito");
            // recargue los paneles aquí si es necesario
        } else {
            ElementosDiseño.NotificacionDialog.error(this, "Hubo un error al limpiar los registros en PostgreSQL.", "Error");
        }
        
        } catch (BaseDeDatos.ConexionBDException ex) {
            // Captura la excepción de la base de datos y notifica al usuario de forma amigable.
            ElementosDiseño.NotificacionDialog.error(this, "Error de conexión: " + ex.getMessage(), "Error de Base de Datos");
        }
    }//GEN-LAST:event_btnReiniciarVuelosMouseClicked

    private void btnReiniciarVuelosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReiniciarVuelosMouseEntered
        fondoBtnReiniciarVuelos.setBackground(new java.awt.Color(51, 65, 85));
    }//GEN-LAST:event_btnReiniciarVuelosMouseEntered

    private void btnReiniciarVuelosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReiniciarVuelosMouseExited
            fondoBtnReiniciarVuelos.setBackground(new java.awt.Color(30, 41, 59));
    }//GEN-LAST:event_btnReiniciarVuelosMouseExited

    private void btnReiniciarVuelosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReiniciarVuelosMousePressed
            fondoBtnReiniciarVuelos.setBackground(new java.awt.Color(71, 85, 105));
    }//GEN-LAST:event_btnReiniciarVuelosMousePressed

    private void btnReiniciarVuelosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReiniciarVuelosMouseReleased
        // Al soltar el clic, regresa al estado Hover si el mouse sigue encima
        if (fondoBtnReiniciarVuelos.getBounds().contains(evt.getPoint())) {
            fondoBtnReiniciarVuelos.setBackground(new java.awt.Color(51, 65, 85));
        } else {
            fondoBtnReiniciarVuelos.setBackground(new java.awt.Color(30, 41, 59));
        }
    }//GEN-LAST:event_btnReiniciarVuelosMouseReleased

    private void btnIniciarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseEntered
        pnlFondoBtnIniciarSesion.setBackground(new java.awt.Color(190, 24, 74)); // Hover
    }//GEN-LAST:event_btnIniciarSesionMouseEntered

    private void btnIniciarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseExited
        pnlFondoBtnIniciarSesion.setBackground(new java.awt.Color(235,24,81)); // Base
    }//GEN-LAST:event_btnIniciarSesionMouseExited

    private void btnIniciarSesionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMousePressed
        pnlFondoBtnIniciarSesion.setBackground(new java.awt.Color(159, 18, 57)); // Pressed
    }//GEN-LAST:event_btnIniciarSesionMousePressed

    private void btnIniciarSesionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseReleased
        // Al soltar el clic, regresa al estado Hover si el mouse sigue encima
        if (pnlFondoBtnIniciarSesion.getBounds().contains(evt.getPoint())) {
            pnlFondoBtnIniciarSesion.setBackground(new java.awt.Color(190, 24, 74));
        } else {
            pnlFondoBtnIniciarSesion.setBackground(new java.awt.Color(235,24,81));
        }
    }//GEN-LAST:event_btnIniciarSesionMouseReleased

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
        java.awt.EventQueue.invokeLater(() -> new Login_GUI().setVisible(true));
    }
    
    
    // ==========================================
    // MÉTODO PARA PROCESAR EL INICIO DE SESIÓN
    // ==========================================
    // Descripción: Toma el usuario y la contraseña escritos en la pantalla, y le pide al Proxy de acceso que revise si son correctos. Según el resultado, muestra una advertencia si faltan campos, un error si las credenciales están mal, o abre la pantalla que le corresponde al usuario y cierra el login si todo salió bien. Mientras se está procesando un intento, bloquea que se dispare otro intento al mismo tiempo
    // Qué otros métodos la activan: Se activa al hacer clic en el botón de Iniciar Sesión, o al presionar la tecla Enter
    private void iniciarSesionProceso() {
        // Si ya se está procesando un intento, ignoramos por completo el segundo disparo
        if (cargandoLogin) {
            return; 
        }

        try {
            cargandoLogin = true; // Bloqueamos la entrada inmediatamente
            
            String user = campoUsuario.getText().trim();
            String pass = new String(campoContraseña.getPassword());
            // Instanciamos el ProxyAcceso
            Patrones.Proxy_Factory.ProxyAcceso proxy = new Patrones.Proxy_Factory.ProxyAcceso();

            // Resultado tipado: ya no se compara contra Strings mágicos ("VACIO"/"DENEGADO")
            Patrones.Proxy_Factory.ResultadoLogin resultado = proxy.intentarLogin(user, pass);

            switch (resultado.getMotivo()) {
                case CAMPOS_VACIOS:
                    ElementosDiseño.NotificacionDialog.advertencia(this, "Complete todos los campos.", "Aviso");
                    break;
                case CREDENCIALES_INVALIDAS:
                    ElementosDiseño.NotificacionDialog.error(this, "Credenciales incorrectas o acceso bloqueado.", "Error");
                    break;
                case OK:
                default:
                    // LOGIN EXITOSO - Mandamos el objeto completo a la fábrica
                    javax.swing.JFrame ventana = Patrones.Proxy_Factory.VentanaFactory.crearVentana(resultado.getUsuario());
                    if (ventana != null) {
                        ventana.setVisible(true);
                        this.dispose(); // Cerramos el login
                    }
                    break;
            }
        } finally {
            // Al terminar (con éxito o error), liberamos el bloqueo para futuros intentos
            cargandoLogin = false; 
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LogoLatam;
    private javax.swing.JPanel bordeSuperior;
    private javax.swing.JLabel btnIniciarSesion;
    private javax.swing.JLabel btnReiniciarVuelos;
    private javax.swing.JPasswordField campoContraseña;
    private javax.swing.JTextField campoUsuario;
    private javax.swing.JPanel fondoBtnReiniciarVuelos;
    private javax.swing.JPanel fondoBtnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblPortalOperacionesTierra;
    private javax.swing.JLabel lblSistemaOnline;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JLabel logoBorde;
    private javax.swing.JPanel pnlContenedorLogin;
    private javax.swing.JPanel pnlFondoBtnIniciarSesion;
    private javax.swing.JPanel pnlFondoLogin;
    private javax.swing.JPanel pnlFondoLogoBorde;
    private javax.swing.JPanel pnlFondoSistemaOnline;
    private javax.swing.JPanel pnlLogoLatam;
    private javax.swing.JLabel txtBtnSalir;
    // End of variables declaration//GEN-END:variables
}
