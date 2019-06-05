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
 * @author Zinko
 */

@ServiceProvider(service = FilterBuilder.class)
public class OcvFilterBuilderCommunity implements FilterBuilder{

    private final static Category TOP_CATEGORY = new Category(
            "Overlapping Communities Viewer",
            null,
            null);
    
    @Override
    public Category getCategory() {
        return TOP_CATEGORY;
    }

    @Override
    public String getName() {
        return "Overlapping Communities Viewer";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public String getDescription() {
        return "filter communities";
    }

    @Override
    public Filter getFilter(Workspace wrkspc) {
        return new OcvFilterCustomCommunity();
    }

    @Override
    public JPanel getPanel(Filter filter) {
        OcvFilterCommunityUI panel = new OcvFilterCommunityUI();
        panel.setup((OcvFilterCustomCommunity) filter);
        return panel;
    }

    @Override
    public void destroy(Filter filter) {
  
    }
    
}
