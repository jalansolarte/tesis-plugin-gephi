/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.ultimateGraphViewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 *
 * @author CamiloDelgado
 */
@ConvertAsProperties(dtd = "-//co.edu.usbcali.ultimateGraphViewer//Simple//Es", autostore = false)
@TopComponent.Description(preferredID = "UGVPanel",
        iconBase="img/logo.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "layoutmode", openAtStartup = true)
@ActionID(category = "Window", id = "co.edu.usbcali.ultimateGraphViewer.UGVPanel")
@ActionReference(path = "Menu/Window", position = 1)
@TopComponent.OpenActionRegistration(displayName = "#UGV", preferredID = "UGVPanel")
public class UGVPanel extends TopComponent {

    public UGVPanel() {
        initComponents();
        setName(NbBundle.getMessage(UGVPanel.class, "UGV"));
        setToolTipText(NbBundle.getMessage(UGVPanel.class, "UGV"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCrearGrafo = new javax.swing.JLabel();
        btnCrearGrafo = new javax.swing.JButton();
        lblMetodoVisualizacion = new javax.swing.JLabel();
        lblFiltros = new javax.swing.JLabel();
        btnAplicarVisualizacion = new javax.swing.JButton();
        comMetodosVisualizacion = new javax.swing.JComboBox<>();
        pnlFiltros = new javax.swing.JTabbedPane();
        pnlComunidades = new javax.swing.JPanel();
        lblMasNodos = new javax.swing.JLabel();
        txtMasNodos = new javax.swing.JTextField();
        lblMenosNodos = new javax.swing.JLabel();
        txtMenosNodos = new javax.swing.JTextField();
        pnlNodos = new javax.swing.JPanel();
        lblConTags = new javax.swing.JLabel();
        txtConTags = new javax.swing.JTextField();
        lblSinTags = new javax.swing.JLabel();
        txtSinTags = new javax.swing.JTextField();
        lblMasComunidades = new javax.swing.JLabel();
        lblMenosComunidades = new javax.swing.JLabel();
        txtMasComunidades = new javax.swing.JTextField();
        txtMenosComunidades = new javax.swing.JTextField();
        pnlAristas = new javax.swing.JPanel();
        lblMasPeso = new javax.swing.JLabel();
        lblMenosPeso = new javax.swing.JLabel();
        txtMasPeso = new javax.swing.JTextField();
        txtMenosPeso = new javax.swing.JTextField();
        btnLimpiarFiltros = new javax.swing.JButton();
        btnAplicarFiltros = new javax.swing.JButton();

        lblCrearGrafo.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblCrearGrafo, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblCrearGrafo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnCrearGrafo, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.btnCrearGrafo.text")); // NOI18N
        btnCrearGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearGrafoActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblMetodoVisualizacion, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblMetodoVisualizacion.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblFiltros, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblFiltros.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnAplicarVisualizacion, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.btnAplicarVisualizacion.text")); // NOI18N
        btnAplicarVisualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarVisualizacionActionPerformed(evt);
            }
        });

        comMetodosVisualizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sombreado", "Nodos", "Aristas" }));

        org.openide.awt.Mnemonics.setLocalizedText(lblMasNodos, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblMasNodos.text")); // NOI18N

        txtMasNodos.setText(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.txtMasNodos.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMenosNodos, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblMenosNodos.text")); // NOI18N

        txtMenosNodos.setText(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.txtMenosNodos.text")); // NOI18N

        javax.swing.GroupLayout pnlComunidadesLayout = new javax.swing.GroupLayout(pnlComunidades);
        pnlComunidades.setLayout(pnlComunidadesLayout);
        pnlComunidadesLayout.setHorizontalGroup(
            pnlComunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComunidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlComunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMasNodos)
                    .addComponent(lblMenosNodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(pnlComunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMasNodos, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(txtMenosNodos))
                .addContainerGap())
        );
        pnlComunidadesLayout.setVerticalGroup(
            pnlComunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComunidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlComunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMasNodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMasNodos))
                .addGap(18, 18, 18)
                .addGroup(pnlComunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMenosNodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMenosNodos))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pnlFiltros.addTab(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.pnlComunidades.TabConstraints.tabTitle"), pnlComunidades); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblConTags, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblConTags.text")); // NOI18N

        txtConTags.setText(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.txtConTags.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblSinTags, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblSinTags.text")); // NOI18N

        txtSinTags.setText(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.txtSinTags.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMasComunidades, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblMasComunidades.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMenosComunidades, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblMenosComunidades.text")); // NOI18N

        txtMasComunidades.setText(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.txtMasComunidades.text")); // NOI18N

        txtMenosComunidades.setText(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.txtMenosComunidades.text")); // NOI18N

        javax.swing.GroupLayout pnlNodosLayout = new javax.swing.GroupLayout(pnlNodos);
        pnlNodos.setLayout(pnlNodosLayout);
        pnlNodosLayout.setHorizontalGroup(
            pnlNodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNodosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConTags)
                    .addComponent(lblSinTags)
                    .addComponent(lblMasComunidades)
                    .addComponent(lblMenosComunidades))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(pnlNodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMenosComunidades, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(txtMasComunidades)
                    .addComponent(txtConTags)
                    .addComponent(txtSinTags))
                .addContainerGap())
        );
        pnlNodosLayout.setVerticalGroup(
            pnlNodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNodosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConTags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConTags))
                .addGap(18, 18, 18)
                .addGroup(pnlNodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSinTags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSinTags))
                .addGap(18, 18, 18)
                .addGroup(pnlNodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMasComunidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMasComunidades))
                .addGap(18, 18, 18)
                .addGroup(pnlNodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMenosComunidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMenosComunidades))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlFiltros.addTab(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.pnlNodos.TabConstraints.tabTitle"), pnlNodos); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMasPeso, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblMasPeso.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMenosPeso, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.lblMenosPeso.text")); // NOI18N

        txtMasPeso.setText(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.txtMasPeso.text")); // NOI18N

        txtMenosPeso.setText(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.txtMenosPeso.text")); // NOI18N

        javax.swing.GroupLayout pnlAristasLayout = new javax.swing.GroupLayout(pnlAristas);
        pnlAristas.setLayout(pnlAristasLayout);
        pnlAristasLayout.setHorizontalGroup(
            pnlAristasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAristasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAristasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAristasLayout.createSequentialGroup()
                        .addComponent(lblMenosPeso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(txtMenosPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAristasLayout.createSequentialGroup()
                        .addComponent(lblMasPeso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMasPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlAristasLayout.setVerticalGroup(
            pnlAristasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAristasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAristasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMasPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMasPeso))
                .addGap(18, 18, 18)
                .addGroup(pnlAristasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMenosPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMenosPeso))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pnlFiltros.addTab(org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.pnlAristas.TabConstraints.tabTitle"), pnlAristas); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnLimpiarFiltros, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.btnLimpiarFiltros.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnAplicarFiltros, org.openide.util.NbBundle.getMessage(UGVPanel.class, "UGVPanel.btnAplicarFiltros.text")); // NOI18N
        btnAplicarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarFiltrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnAplicarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMetodoVisualizacion))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblFiltros)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblCrearGrafo)
                                        .addComponent(comMetodosVisualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAplicarVisualizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCrearGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
                        .addGap(0, 13, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrearGrafo)
                    .addComponent(btnCrearGrafo))
                .addGap(18, 18, 18)
                .addComponent(lblMetodoVisualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comMetodosVisualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAplicarVisualizacion))
                .addGap(18, 18, 18)
                .addComponent(lblFiltros)
                .addGap(16, 16, 16)
                .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiarFiltros)
                    .addComponent(btnAplicarFiltros))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearGrafoActionPerformed
        // TODO add your handling code here:
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (!jf.isMultiSelectionEnabled()) {
         jf.setMultiSelectionEnabled(true);
        }
        jf.showOpenDialog(this); 
        File[] file = jf.getSelectedFiles();
    }//GEN-LAST:event_btnCrearGrafoActionPerformed

    private void btnAplicarVisualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarVisualizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAplicarVisualizacionActionPerformed

    private void btnAplicarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarFiltrosActionPerformed
        // TODO add your handling code here:
        List<String> filtros = validarFiltros();
    }//GEN-LAST:event_btnAplicarFiltrosActionPerformed

    public List<String> validarFiltros(){
        
        List<String> filtros = new ArrayList<>();
        
        //Comunidades
        String masNodos = txtMasNodos.getText();
        String menosNodos = txtMenosNodos.getText();
        
        if(!masNodos.isEmpty() && !masNodos.equals("")){
            filtros.add(masNodos);
        }
        if(!menosNodos.isEmpty() && !menosNodos.equals("")){
            filtros.add(menosNodos);
        }
        
        //Nodos
        String conTags = txtConTags.getText();
        String sinTags = txtSinTags.getText();
        String masComunidades = txtMasComunidades.getText();
        String menosComunidades = txtMenosComunidades.getText();
        
        if(!conTags.isEmpty() && !conTags.equals("")){
            filtros.add(conTags);
        }
        if(!sinTags.isEmpty() && !sinTags.equals("")){
            filtros.add(sinTags);
        }
        if(!masComunidades.isEmpty() && !masComunidades.equals("")){
            filtros.add(masComunidades);
        }
        if(!menosComunidades.isEmpty() && !menosComunidades.equals("")){
            filtros.add(menosComunidades);
        }
        
        //Aristas
        String pesoMayor = txtMasPeso.getText();
        String pesoMenor = txtMenosPeso.getText();
        
        if(!pesoMayor.isEmpty() && !pesoMayor.equals("")){
            filtros.add(pesoMayor);
        }
        if(!pesoMenor.isEmpty() && !pesoMenor.equals("")){
            filtros.add(pesoMenor);
        }
        
        if(filtros.size() == 0){
            
        }
        return filtros;
    }
    
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAplicarFiltros;
    private javax.swing.JButton btnAplicarVisualizacion;
    private javax.swing.JButton btnCrearGrafo;
    private javax.swing.JButton btnLimpiarFiltros;
    private javax.swing.JComboBox<String> comMetodosVisualizacion;
    private javax.swing.JLabel lblConTags;
    private javax.swing.JLabel lblCrearGrafo;
    private javax.swing.JLabel lblFiltros;
    private javax.swing.JLabel lblMasComunidades;
    private javax.swing.JLabel lblMasNodos;
    private javax.swing.JLabel lblMasPeso;
    private javax.swing.JLabel lblMenosComunidades;
    private javax.swing.JLabel lblMenosNodos;
    private javax.swing.JLabel lblMenosPeso;
    private javax.swing.JLabel lblMetodoVisualizacion;
    private javax.swing.JLabel lblSinTags;
    private javax.swing.JPanel pnlAristas;
    private javax.swing.JPanel pnlComunidades;
    private javax.swing.JTabbedPane pnlFiltros;
    private javax.swing.JPanel pnlNodos;
    private javax.swing.JTextField txtConTags;
    private javax.swing.JTextField txtMasComunidades;
    private javax.swing.JTextField txtMasNodos;
    private javax.swing.JTextField txtMasPeso;
    private javax.swing.JTextField txtMenosComunidades;
    private javax.swing.JTextField txtMenosNodos;
    private javax.swing.JTextField txtMenosPeso;
    private javax.swing.JTextField txtSinTags;
    // End of variables declaration//GEN-END:variables
}
