/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.filter;

import javax.swing.Icon;
import javax.swing.JPanel;
import org.gephi.filters.spi.Category;
import org.gephi.filters.spi.Filter;
import org.gephi.filters.spi.FilterBuilder;
import org.gephi.project.api.Workspace;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author zinko
 */

@ServiceProvider(service = FilterBuilder.class)
public class OcvFilterBuilder implements FilterBuilder {

    private final static Category TOP_CATEGORY = new Category(
            "Overlapping Communities Viewer Nodes",
            null,
            null);
    
    
    @Override
    public Category getCategory() {
        return TOP_CATEGORY;
    }

    @Override
    public String getName() {
        return "Overlapping Communities Viewer Nodes";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Filter Nodes";
    }

    @Override
    public JPanel getPanel(Filter filter) {
        //Create the panel
        OcvFilterUI panel = new OcvFilterUI();
        panel.setup((OcvFilterCustom) filter);
        return panel;
    }

    @Override
    public void destroy(Filter filter) {
    
    }

    @Override
    public Filter getFilter(Workspace workspace) {
        return new OcvFilterCustom();
    }
}
