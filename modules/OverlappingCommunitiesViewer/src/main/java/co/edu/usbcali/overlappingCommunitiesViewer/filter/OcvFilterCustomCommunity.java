/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.filter;

import co.edu.usbcali.overlappingCommunitiesViewer.generator.model.FiltersValues;
import co.edu.usbcali.overlappingCommunitiesViewer.generator.model.OptionsFilter;
import co.edu.usbcali.overlappingCommunitiesViewer.utils.Constants;
import com.google.common.collect.Iterators;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.gephi.filters.spi.ComplexFilter;
import org.gephi.filters.spi.FilterProperty;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;

/**
 *
 * @author zinko
 */
public class OcvFilterCustomCommunity implements ComplexFilter {

    boolean useRegex;

    private List<FiltersValues> communitiesFilter;
    private OptionsFilter optionsFilterCommunity;

    //private int cantFilter;
    //private List<String> comFilter;

    @Override
    public String getName() {
        return "Overlapping Communities Viewer Filter";
    }

    @Override
    public FilterProperty[] getProperties() {
        try {
            return new FilterProperty[]{
                FilterProperty.createProperty(this, Boolean.class, "useRegex")
            };
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    public boolean isUseRegex() {
        return useRegex;
    }

    public void setUseRegex(boolean useRegex) {
        this.useRegex = useRegex;
    }

    @Override
    public Graph filter(Graph graph) {
        if (communitiesFilter.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay filtros por aplicar", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Node[] nodes = graph.getNodes().toArray();
            
            //comunidades
            if(communitiesFilter.get(0).getId() == 3 && communitiesFilter.get(1).getId() == 3){
            
            } else {
                for (Node node : nodes) {
                        boolean deleteCommunityMoreNode = false;
                        boolean deleteCommunityLessNode = false;
                        if(optionsFilterCommunity.getOption1() == 1){
                            deleteCommunityMoreNode = conMasDeNumNodos(node);
                        }
                        if(optionsFilterCommunity.getOption2() == 1){
                            deleteCommunityLessNode = conMenosDeNumNodos(node);
                        }
                        if(deleteCommunityMoreNode == true || deleteCommunityLessNode == true){
                            graph.removeNode(node);
                        }
                    }
            }
        }
        return graph;
    }

    //COMUNIDADES
    public boolean conMasDeNumNodos(Node node){
        boolean delete = true;
        //comunidades con mas de N nodos
        if (communitiesFilter.get(0).getId() == 0) {
            int numNodes = Integer.parseInt(node.getAttribute(Constants.countNodesColumn).toString());
            //int numNodesPerCommunities = graph.getEdges(node).toArray().length;
            int valueCompare = Integer.parseInt(communitiesFilter.get(0).getValue());
            if (valueCompare <= numNodes) {
                delete = false;
            }    
        }
        return delete;
    }
    
    public boolean conMenosDeNumNodos(Node node){
        boolean delete = true;
         //comunidades con mas de N nodos
        if (communitiesFilter.get(1).getId() == 1) {
            int numNodes = Integer.parseInt(node.getAttribute(Constants.countNodesColumn).toString());
            //int numNodesPerCommunities = graph.getEdges(node).toArray().length;
            int valueCompare = Integer.parseInt(communitiesFilter.get(1).getValue());
            if (valueCompare > numNodes) {
                delete = false;
            }    
        }
        return delete;
    }

    /**
     * @return the communitiesFilter
     */
    public List<FiltersValues> getCommunitiesFilter() {
        return communitiesFilter;
    }

    /**
     * @param communitiesFilter the communitiesFilter to set
     */
    public void setCommunitiesFilter(List<FiltersValues> communitiesFilter) {
        this.communitiesFilter = communitiesFilter;
    }

    /**
     * @return the optionsFilterCommunity
     */
    public OptionsFilter getOptionsFilterCommunity() {
        return optionsFilterCommunity;
    }

    /**
     * @param optionsFilterCommunity the optionsFilterCommunity to set
     */
    public void setOptionsFilterCommunity(OptionsFilter optionsFilterCommunity) {
        this.optionsFilterCommunity = optionsFilterCommunity;
    }
}
