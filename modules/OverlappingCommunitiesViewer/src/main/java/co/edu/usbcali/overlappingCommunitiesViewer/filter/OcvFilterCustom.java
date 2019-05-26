/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.filter;

import co.edu.usbcali.overlappingCommunitiesViewer.generator.model.FiltersValues;
import com.google.common.collect.Iterators;
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
public class OcvFilterCustom implements ComplexFilter{

    boolean useRegex;
    
    private List<FiltersValues> communitiesFilter;
    private List<FiltersValues> nodesFilter;
    private List<FiltersValues> aristasFilter;
    
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
        
        if(communitiesFilter.isEmpty() && nodesFilter.isEmpty() && aristasFilter.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay filtros por aplicar", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            Node[] nodes = graph.getNodes().toArray();
            boolean eliminar = true;
            
            for (Node node : nodes) {
                //comunidades con mas de N nodos
                if(!communitiesFilter.isEmpty()){
                    if(communitiesFilter.get(0).getId() == 0){
                        if(node.getAttribute("isCommunity") != null && 
                                node.getAttribute("isCommunity").equals(true)){
                           int numNodesPerCommunities = Iterators.size(graph.getEdges(node).iterator());
                           int valueCompare = Integer.parseInt(communitiesFilter.get(0).getValue());
                           if(valueCompare <= numNodesPerCommunities){
                               eliminar = false;
                           }
                        }
                    }
                }
                //comunidades con menos de N nodos
                if(!communitiesFilter.isEmpty()){
                    if(communitiesFilter.get(1).getId() == 1){
                        if(node.getAttribute("isCommunity") != null && 
                                node.getAttribute("isCommunity").equals(true)){
                           int numNodesPerCommunities = Iterators.size(graph.getEdges(node).iterator());
                           int valueCompare = Integer.parseInt(communitiesFilter.get(0).getValue());
                           if(valueCompare > numNodesPerCommunities){
                               eliminar = false;
                           }
                        }
                    }
                }
            
                //nodos contengan tags
                if(!nodesFilter.isEmpty()){
                    if (nodesFilter.get(0).getId() == 0 ) {
                        //Cambiar por Contains -LowerCase
                        if (node.getAttribute("Tags") != null /*&& 
                                node.getAttribute("Tags").equals(nodesFilter.get(0).getValue())*/) {
                                String tagsCompare = node.getAttribute("Tags").toString();
                                tagsCompare= tagsCompare.replace("<", "");
                                tagsCompare= tagsCompare.replace(">", "");
                                if(tagsCompare.contains(nodesFilter.get(0).getValue())){
                                    eliminar = false;
                                }
                        }
                    }
                }   
                //nodos que no contengan tags
                if(!nodesFilter.isEmpty()){
                    if (nodesFilter.get(1).getId() == 1 ) {
                        //Cambiar por Contains -LowerCase
                        if (node.getAttribute("Tags") != null /*&& 
                                node.getAttribute("Tags").equals(nodesFilter.get(0).getValue())*/) {
                                String tagsCompare = node.getAttribute("Tags").toString();
                                tagsCompare= tagsCompare.replace("<", "");
                                tagsCompare= tagsCompare.replace(">", "");
                                if(!tagsCompare.contains(nodesFilter.get(1).getValue())){
                                    eliminar = false;
                                }
                        }
                    }
                }
                //nodos en más de n comunidades
                /*if(!nodesFilter.isEmpty()){
                    if (nodesFilter.get(2).getId() == 2 ) {
                        //Cambiar por Contains -LowerCase
                        if (node.getAttribute("Tags") != null && 
                                !node.getAttribute("Tags").equals(nodesFilter.get(2).getValue())) {
                                eliminar = false;
                        }
                    }
                }*/
                //nodos en menos de n comunidades
                /*if(!nodesFilter.isEmpty()){
                    if (nodesFilter.get(3).getId() == 3 ) {
                        //Cambiar por Contains -LowerCase
                        if (node.getAttribute("Tags") != null && 
                                !node.getAttribute("Tags").equals(nodesFilter.get(3).getValue())) {
                                eliminar = false;
                        }
                    }
                }*/
                
               
                if(eliminar == true){
                    graph.removeNode(node);
                }
                eliminar = true;
            }
            
            Edge[] edges = graph.getEdges().toArray();
            Boolean eliminarArista = true;
            for (Edge edge : edges) {
                
                 //aristas con peso mayor a N
                if(!aristasFilter.isEmpty()){
                    if(aristasFilter.get(0).getId() == 0){
                        double weightFilter = Double.parseDouble(aristasFilter.get(0).getValue());
                        int compare = Double.compare(edge.getWeight(),weightFilter);
                        if(compare > 0){
                            eliminarArista = false;
                        }
                    }
                }
                //aristas con peso menor a N
                if(!aristasFilter.isEmpty()){
                    if(aristasFilter.get(1).getId() == 1){
                        double weightFilter = Double.parseDouble(aristasFilter.get(1).getValue());
                        int compare = Double.compare(edge.getWeight(),weightFilter);
                        if(compare < 0){
                            eliminarArista = false;
                        }
                    }
                }
                if(eliminarArista == true){
                    graph.removeEdge(edge);
                }
                eliminarArista = true;
            }
        }
        return graph;
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
     * @return the nodesFilter
     */
    public List<FiltersValues> getNodesFilter() {
        return nodesFilter;
    }

    /**
     * @param nodesFilter the nodesFilter to set
     */
    public void setNodesFilter(List<FiltersValues> nodesFilter) {
        this.nodesFilter = nodesFilter;
    }

    /**
     * @return the aristasFilter
     */
    public List<FiltersValues> getAristasFilter() {
        return aristasFilter;
    }

    /**
     * @param aristasFilter the aristasFilter to set
     */
    public void setAristasFilter(List<FiltersValues> aristasFilter) {
        this.aristasFilter = aristasFilter;
    }
}
