/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.generator;

import co.edu.usbcali.overlappingCommunitiesViewer.GUI.OgvGeneratorPanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.gephi.io.generator.spi.Generator;
import org.gephi.io.generator.spi.GeneratorUI;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author CamiloDelgado
 */
public class OcvGeneratorUI implements GeneratorUI{
    
    public OcvGenerator ocvGenerator;
    public OgvGeneratorPanel ogvGeneratorPanel;
    
    @Override
    public JPanel getPanel() {
        ogvGeneratorPanel = new OgvGeneratorPanel();
        return ogvGeneratorPanel;
    }

    @Override
    public void setup(Generator generator) {
        ocvGenerator = (OcvGenerator) generator;
    }

    @Override
    public void unsetup() {
        boolean error = false;
        
        if (ogvGeneratorPanel.getFileTags() == null || !ogvGeneratorPanel.getFileTags().exists()) {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar un archivo de tags", "Tags File Error", JOptionPane.ERROR_MESSAGE);
            error = true;
        }
        if (ogvGeneratorPanel.getFileRelations()== null || !ogvGeneratorPanel.getFileRelations().exists()) {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar un archivo de relaciones", "Relations File Error", JOptionPane.ERROR_MESSAGE);
            error = true;
        }
        if (ogvGeneratorPanel.getFileCommunities() == null || !ogvGeneratorPanel.getFileCommunities().exists()) {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar un archivo de comunidades", "Communities File Error", JOptionPane.ERROR_MESSAGE);
            error = true;
        }
        
        if(!error){
            ocvGenerator.setFileTags(ogvGeneratorPanel.getFileTags());
            ocvGenerator.setFileRelations(ogvGeneratorPanel.getFileRelations());
            ocvGenerator.setFileCommunities(ogvGeneratorPanel.getFileCommunities());
        }
            
    }
    
}
