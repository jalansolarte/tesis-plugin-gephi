/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.layout;

import javax.swing.Icon;
import javax.swing.JPanel;
import org.gephi.layout.spi.Layout;
import org.gephi.layout.spi.LayoutUI;

/**
 *
 * @author CamiloDelgado
 */
public class OcvLayoutUI implements LayoutUI{

    @Override
    public String getDescription() {
        return "Overlapping Communities Layout";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public JPanel getSimplePanel(Layout layout) {
        return null;
    }

    @Override
    public int getQualityRank() {
        return 1;
    }

    @Override
    public int getSpeedRank() {
        return 1;
    }
    
}
