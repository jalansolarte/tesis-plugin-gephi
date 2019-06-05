/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.filter;

import co.edu.usbcali.overlappingCommunitiesViewer.generator.model.FiltersValues;
import co.edu.usbcali.overlappingCommunitiesViewer.generator.model.OptionsFilter;
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
public class OcvFilterCustom implements ComplexFilter {

    boolean useRegex;

    private List<FiltersValues> nodesFilter;
    private List<FiltersValues> aristasFilter;
    private OptionsFilter optionsFilter;
    private OptionsFilter optionsFilterEdge;

    //private int cantFilter;
    //private List<String> comFilter;

    @Override
    public String getName() {
        return "Overlapping Communities Viewer Filter Node";
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
        if (nodesFilter.isEmpty() && aristasFilter.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay filtros por aplicar", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Node[] nodes = graph.getNodes().toArray();

            //nodos
            if (!nodesFilter.isEmpty()) {
                for (Node node : nodes) {
                    boolean deleteTags = false;
                    boolean deleteSinTags = false;
                    boolean deleteMasComunidades = false;
                    boolean deleteMenosComunidades = false;
                    
                    
                    if (optionsFilter.getOption1() == 1) {
                        deleteTags = this.conTags(node);
                    }
                    if (optionsFilter.getOption2() == 1) {
                        deleteSinTags = this.sinTags(node);
                    }
                    if (optionsFilter.getOption3() == 1) {
                        deleteMasComunidades = this.conMasComunidades(node);
                    }
                    if (optionsFilter.getOption4() == 1) {
                        deleteMenosComunidades = this.conMenosComunidades(node);
                    }
                    if (deleteTags == true || deleteSinTags == true || deleteMasComunidades == true || deleteMenosComunidades == true) {
                        graph.removeNode(node);
                    }
                }
            }

            Edge[] edges = graph.getEdges().toArray();
            if (!aristasFilter.isEmpty()) {
            for (Edge edge : edges) {
                boolean deleteEdgeMoreWeight = false;
                boolean deleteEdgeLessWeight = false;
                if(optionsFilterEdge.getOption1() == 1){
                    deleteEdgeMoreWeight = conMasPeso(edge);
                }
                if(optionsFilterEdge.getOption2() == 1){
                    deleteEdgeLessWeight = conMenosPeso(edge);
                }
                if (deleteEdgeMoreWeight == true || deleteEdgeLessWeight == true) {
                    graph.removeEdge(edge);
                }
            }
        }
        }
        return graph;
    }
    
    //NODOS
    public boolean conTags(Node node) {
        boolean delete = true;
        
        if (nodesFilter.get(0).getId() == 0) {
            if (node.getAttribute("tags") != null) {
                String tagsCompare = node.getAttribute("tags").toString();
                String[] tagsArray = tagsCompare.split(" ");
                String[] findTag = nodesFilter.get(0).getValue().split(",");
                int numPalabras = findTag.length;
                int contador = 0;
                for (String tag : findTag) {
                    tag = "<" + tag.trim() + ">";
                    tag = tag.toLowerCase();
                        for (String tagArray : tagsArray) {
                        if (tagArray.equals(tag)) {
                            contador++;
                        }
                    }
                }
                if(contador == numPalabras){
                    delete = false;
                }
            }
        }
        return delete;
    }

    public boolean sinTags(Node node) {
        boolean delete = true;
        if (nodesFilter.get(1).getId() == 1) {
            if (node.getAttribute("tags") != null) {
                String tagsCompare = node.getAttribute("tags").toString();
                String[] tagsArray = tagsCompare.split(" ");
                String[] findTag = nodesFilter.get(1).getValue().split(",");

                for (String tag : findTag) {
                    tag = "<" + tag.trim() + ">";
                    tag = tag.toLowerCase();

                    for (String tagArray : tagsArray) {
                        delete = false;
                        if (tagArray.equals(tag)) {
                            delete = true;
                            return delete;
                        }
                    }
                }
            }
        }
        return delete;
    }

    public boolean conMasComunidades(Node node) {
        boolean delete = true;
        if (nodesFilter.get(2).getId() == 2) {
            if (node.getAttribute("belongsCommunities") != null) {
                int nodeMoreCommunitie = Integer.parseInt(node.getAttribute("belongsCommunities").toString());
                int filter = Integer.parseInt(nodesFilter.get(2).getValue());
                if (nodeMoreCommunitie >= filter) {
                    delete = false;
                }
            }
        }
        return delete;
    }

    public boolean conMenosComunidades(Node node) {
        boolean delete = true;
        if (nodesFilter.get(3).getId() == 3) {
            if (node.getAttribute("belongsCommunities") != null) {
                int moreCommunitie = Integer.parseInt(node.getAttribute("belongsCommunities").toString());
                int filter = Integer.parseInt(nodesFilter.get(3).getValue());
                if (moreCommunitie <= filter) {
                    delete = false;
                }
            }
        }
        return delete;
    }

    //Aristas
    public boolean conMasPeso(Edge edge){
        boolean delete = true;
        //aristas con peso mayor a N
        if (aristasFilter.get(0).getId() == 0) {
            double weightFilter = Double.parseDouble(aristasFilter.get(0).getValue());
            int compare = Double.compare(edge.getWeight(), weightFilter);
            if (compare >= 0) {
                delete = false;
            }
        }
        return delete;
    }
    
    public boolean conMenosPeso(Edge edge){
        boolean delete = true;
        //aristas con peso menor a N
        if (aristasFilter.get(1).getId() == 1) {
            double weightFilter = Double.parseDouble(aristasFilter.get(1).getValue());
            int compare = Double.compare(edge.getWeight(), weightFilter);
            if (compare <= 0) {
                delete = false;
            }
        }
        return delete;
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

    /**
     * @return the optionsFilter
     */
    public OptionsFilter getOptionsFilter() {
        return optionsFilter;
    }

    /**
     * @param optionsFilter the optionsFilter to set
     */
    public void setOptionsFilter(OptionsFilter optionsFilter) {
        this.optionsFilter = optionsFilter;
    }

    /**
     * @return the optionsFilterEdge
     */
    public OptionsFilter getOptionsFilterEdge() {
        return optionsFilterEdge;
    }

    /**
     * @param optionsFilterEdge the optionsFilterEdge to set
     */
    public void setOptionsFilterEdge(OptionsFilter optionsFilterEdge) {
        this.optionsFilterEdge = optionsFilterEdge;
    }
    
    
}
