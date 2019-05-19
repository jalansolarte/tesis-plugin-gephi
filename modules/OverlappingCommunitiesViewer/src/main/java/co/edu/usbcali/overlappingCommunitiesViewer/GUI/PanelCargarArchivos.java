/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.GUI;

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
/*@ConvertAsProperties(dtd = "-//co.edu.usbcali.ultimateGraphViewer//Simple//Es", autostore = false)
@TopComponent.Description(preferredID = "PanelCargarArchivos",
        iconBase="img/logo.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "layoutmode", openAtStartup = true)
@ActionID(category = "Window", id = "co.edu.usbcali.ultimateGraphViewer.PanelCargarArchivos")
@ActionReference(path = "Menu/Window", position = 1)
@TopComponent.OpenActionRegistration(displayName = "#UGV", preferredID = "PanelCargarArchivos")
*/
public class PanelCargarArchivos extends TopComponent {
    
    List<File> files = new ArrayList<>();
    
    public PanelCargarArchivos() {
        initComponents();
        setName(NbBundle.getMessage(PanelCargarArchivos.class, "UGV"));
        setToolTipText(NbBundle.getMessage(PanelCargarArchivos.class, "UGV"));
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
        btnTags = new javax.swing.JButton();
        btnRelaciones = new javax.swing.JButton();
        btnComunidades = new javax.swing.JButton();

        lblCrearGrafo.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblCrearGrafo, org.openide.util.NbBundle.getMessage(PanelCargarArchivos.class, "PanelCargarArchivos.lblCrearGrafo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnCrearGrafo, org.openide.util.NbBundle.getMessage(PanelCargarArchivos.class, "PanelCargarArchivos.btnCrearGrafo.text")); // NOI18N
        btnCrearGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearGrafoActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnTags, org.openide.util.NbBundle.getMessage(PanelCargarArchivos.class, "PanelCargarArchivos.btnTags.text")); // NOI18N
        btnTags.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTagsActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnRelaciones, org.openide.util.NbBundle.getMessage(PanelCargarArchivos.class, "PanelCargarArchivos.btnRelaciones.text")); // NOI18N
        btnRelaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelacionesActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnComunidades, org.openide.util.NbBundle.getMessage(PanelCargarArchivos.class, "PanelCargarArchivos.btnComunidades.text")); // NOI18N
        btnComunidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComunidadesActionPerformed(evt);
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
                        .addComponent(btnTags)
                        .addGap(57, 57, 57)
                        .addComponent(btnRelaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnComunidades))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCrearGrafo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCrearGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCrearGrafo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTags)
                    .addComponent(btnRelaciones)
                    .addComponent(btnComunidades))
                .addGap(18, 18, 18)
                .addComponent(btnCrearGrafo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearGrafoActionPerformed
        UGVPanel ugv = new UGVPanel();
        ugv.setVisible(true);
    }//GEN-LAST:event_btnCrearGrafoActionPerformed

    private void btnTagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTagsActionPerformed
        File tags = null;
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (!jf.isMultiSelectionEnabled()) {
         jf.setMultiSelectionEnabled(true);
        }
        jf.showOpenDialog(this); 
        tags = jf.getSelectedFile();
        files.add(tags);
    }//GEN-LAST:event_btnTagsActionPerformed

    private void btnRelacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelacionesActionPerformed
        File relaciones = null;
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (!jf.isMultiSelectionEnabled()) {
         jf.setMultiSelectionEnabled(true);
        }
        jf.showOpenDialog(this); 
        relaciones = jf.getSelectedFile();
        files.add(relaciones);
    }//GEN-LAST:event_btnRelacionesActionPerformed

    private void btnComunidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComunidadesActionPerformed
        File comunidades = null;
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (!jf.isMultiSelectionEnabled()) {
         jf.setMultiSelectionEnabled(true);
        }
        jf.showOpenDialog(this); 
        comunidades = jf.getSelectedFile();
        files.add(comunidades);
    }//GEN-LAST:event_btnComunidadesActionPerformed
    
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
    private javax.swing.JButton btnComunidades;
    private javax.swing.JButton btnCrearGrafo;
    private javax.swing.JButton btnRelaciones;
    private javax.swing.JButton btnTags;
    private javax.swing.JLabel lblCrearGrafo;
    // End of variables declaration//GEN-END:variables
}