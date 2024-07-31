/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Controller.ArduinoController;
import Controller.PersonaControlador;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class VistaVitalSings extends javax.swing.JInternalFrame {
    private SerialPort comPort;
    private InputStream inputStream;
    private OutputStream outputStream;
    private XYSeries series;
    ArduinoController arduinoController;
    IntWrapper xWrapper = new IntWrapper(0); 
    
    private String spo2;
    private String avgBpm;
    
    private volatile double variable1;
    private volatile double variable2;
    private PersonaControlador personaControlador;
    
    public VistaVitalSings(PersonaControlador persona) {
        initComponents();
//        jTextArea1.setText("");
        this.personaControlador = persona;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSpO2 = new javax.swing.JTextField();
        txtBPM = new javax.swing.JTextField();
        txtValorNormalBPM = new javax.swing.JTextField();
        txtValorNormalSpO2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();

        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel8.setText("TOMA DE SIGNOS VITALES");

        btnIniciar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnIniciar.setText("INICIAR");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("SpO2");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("BPM");

        txtSpO2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSpO2.setToolTipText("XXXXXXXXXX");
        txtSpO2.setName("cnb"); // NOI18N

        txtBPM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBPM.setToolTipText("XXXXXXXXXX");
        txtBPM.setName("cnb"); // NOI18N

        txtValorNormalBPM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValorNormalBPM.setToolTipText("XXXXXXXXXX");
        txtValorNormalBPM.setName("cnb"); // NOI18N

        txtValorNormalSpO2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValorNormalSpO2.setToolTipText("XXXXXXXXXX");
        txtValorNormalSpO2.setName("cnb"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Alertas");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Rango normal 95% - 100%");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Rango normal 60 - 100 bpm");

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtBPM, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtSpO2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorNormalBPM)
                            .addComponent(txtValorNormalSpO2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(326, 326, 326)
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(btnIniciar)
                        .addGap(52, 52, 52)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtBPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorNormalBPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSpO2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorNormalSpO2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(btnSalir)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIniciar)
                            .addComponent(btnGuardar))
                        .addGap(28, 28, 28))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        //stopSimulation();
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
       datos();
       
       SwingUtilities.invokeLater(() -> {
            txtBPM.setText(String.valueOf(variable1));
            txtSpO2.setText(String.valueOf(variable2));
        });
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        ResultSet rs = personaControlador.buscarPaciente(VistaPrincipal.txtCedula.getText());
        try {
            boolean guardado = personaControlador.guardarRegistro(rs.getInt("codigoPaciente"), LocalDateTime.now(), Double.parseDouble(txtBPM.getText()), Integer.parseInt(txtSpO2.getText()));
            if(guardado){
                JOptionPane.showMessageDialog(rootPane, "Registro guardado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaVitalSings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    
    private void datos() {
    arduinoController = new ArduinoController("COM7");
    arduinoController.connect();
    try {
        Thread.sleep(2000);
        comPort = arduinoController.sendCommand('S');
        Thread.sleep(1000);

        // Crear la serie de datos para el gráfico
        series = new XYSeries("Datos del Arduino");
        // Configurar el gráfico
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Datos en Tiempo Real",
                "Tiempo",
                "Valor",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        // Mostrar el gráfico en un JFrame
        JFrame frame = new JFrame("Arduino Serial Plotter");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new ChartPanel(chart), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        IntWrapper xWrapper = new IntWrapper(0); // Usar la clase envolvente

        Thread thread = new Thread(() -> {
            while (true) {
                String response = arduinoController.readResponse();
                if (response != null && !response.isEmpty()) {
                    System.out.println("Received line: " + response); // Depuración: Verifica los datos recibidos
                    // Dividir la línea en partes
                    String[] parts = response.split(",");
                    if (parts.length > 0) {
                        try {
                            // Extraer el primer valor (beatsPerMinute)
                            double beatsPerMinute = Double.parseDouble(parts[0]);
                            
                            
                            
                            // Actualizar la serie de datos en el gráfico
                            actualizarGrafico(beatsPerMinute, parts[1], parts[2]);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid data format: " + e.getMessage());
                        }
                    }
                }

                try {
                    Thread.sleep(10); // Ajusta el intervalo según sea necesario
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted: " + e.getMessage());
                }
            }
        });

        thread.start();

    } catch (InterruptedException ex) {
        Logger.getLogger(VistaVitalSings.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    private void actualizarGrafico(double beatsPerMinute, String part, String part1) {
        txtBPM.setText("");
            txtSpO2.setText("");
        SwingUtilities.invokeLater(() -> {
            series.add(xWrapper.getValue(), beatsPerMinute);
            xWrapper.increment();
            txtBPM.setText(part);
            txtSpO2.setText(part1);
            
            // Realizar las comprobaciones
            if (Double.parseDouble(txtBPM.getText()) < 60 || Double.parseDouble(txtBPM.getText()) > 100) {
                txtValorNormalBPM.setText("Recomendación chequeo médico");
                } else{
                txtValorNormalBPM.setText("");
            }

            if (Double.parseDouble(txtSpO2.getText()) < 90 || Double.parseDouble(txtSpO2.getText()) > 100) {
                txtValorNormalSpO2.setText("Recomendación chequeo médico");
                }else{
                txtValorNormalSpO2.setText("");
            }
            
        });
    }
    
    




    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtBPM;
    private javax.swing.JTextField txtSpO2;
    private javax.swing.JTextField txtValorNormalBPM;
    private javax.swing.JTextField txtValorNormalSpO2;
    // End of variables declaration//GEN-END:variables
}