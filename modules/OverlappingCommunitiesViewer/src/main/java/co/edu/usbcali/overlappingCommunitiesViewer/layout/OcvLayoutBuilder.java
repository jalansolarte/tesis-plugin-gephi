/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.layout;

import org.gephi.layout.spi.Layout;
import org.gephi.layout.spi.LayoutBuilder;
import org.gephi.layout.spi.LayoutUI;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author CamiloDelgado
 */
@ServiceProvider(service = LayoutBuilder.class)
public class OcvLayoutBuilder implements LayoutBuilder{

    @Override
    public String getName() {
        return "Overlapping Communities Layout";
    }

    @Override
    public LayoutUI getUI() {
        return new OcvLayoutUI();
    }

    @Override
    public Layout buildLayout() {
        return new OcvLayout(this);
    }
    
}
