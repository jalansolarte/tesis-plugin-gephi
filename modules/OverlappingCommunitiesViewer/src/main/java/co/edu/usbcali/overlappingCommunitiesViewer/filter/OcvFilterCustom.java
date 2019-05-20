/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import org.gephi.filters.spi.ComplexFilter;
import org.gephi.filters.spi.FilterProperty;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
import org.gephi.io.importer.api.NodeDraft;

/**
 *
 * @author zinko
 */
public class OcvFilterCustom implements ComplexFilter{

    boolean useRegex;
    private List<String> comunidades = new ArrayList<>();
    private List<String> nodos = new ArrayList<>();
    private List<String> aristas = new ArrayList<>();
    
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
        
        
        if(comunidades.isEmpty() && nodos.isEmpty() && aristas.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay filtros por aplicar", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            Graph graph1 = null;
            //comunidades
            if(!comunidades.isEmpty()){
               /* 
                Node[] nodes = graph.getNodes().toArray();
                    for(int i = 0; i < nodes.length;i++){
                        if(nodes[i].getAttribute("isCommunity").equals("isCommunity")){
                            graph1.addNode(nodes[i]);
                        }
                    }
                */
            }
            //nodos
            if(!nodos.isEmpty()){
            
            }
            //aristas
            if(!aristas.isEmpty()){
            
            }
            //Codigo que quita la mitad de relaciones
            int edgeCount = graph.getEdgeCount();
            edgeCount /= 2;
        
            Edge[] edges = graph.getEdges().toArray();
            Arrays.sort(edges, new Comparator<Edge>() {
                public int compare(Edge o1, Edge o2){
                    return Double.compare(o1.getWeight(), o2.getWeight());
                }
            });
 
            for (int i = 0; i < edgeCount; i++) {
                Edge edge = edges[i];
                graph.removeEdge(edge);
            }
        }
        return graph;
    }

    /**
     * @return the comunidades
     */
    public List<String> getComunidades() {
        return comunidades;
    }

    /**
     * @param comunidades the comunidades to set
     */
    public void setComunidades(List<String> comunidades) {
        this.comunidades = comunidades;
    }

    /**
     * @return the nodos
     */
    public List<String> getNodos() {
        return nodos;
    }

    /**
     * @param nodos the nodos to set
     */
    public void setNodos(List<String> nodos) {
        this.nodos = nodos;
    }

    /**
     * @return the aristas
     */
    public List<String> getAristas() {
        return aristas;
    }

    /**
     * @param aristas the relaciones to set
     */
    public void setAristas(List<String> aristas) {
        this.aristas = aristas;
    }
}
