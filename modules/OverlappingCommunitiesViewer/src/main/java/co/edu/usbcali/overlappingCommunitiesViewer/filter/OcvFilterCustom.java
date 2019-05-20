/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.filter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import org.gephi.filters.spi.FilterProperty;
import org.gephi.filters.spi.NodeFilter;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author zinko
 */
public class OcvFilterCustom implements NodeFilter{
    private Column column;
    private FilterProperty[] filterProperties;
    private Integer top = 1;
    //Flag
    private Set<Node> topSet;

    @Override
    public boolean init(Graph graph) {
        Node[] nodes = graph.getNodes().toArray();
        Arrays.sort(nodes, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                Comparable a1 = (Comparable) o1.getAttribute(column);
                Comparable a2 = (Comparable) o2.getAttribute(column);
                if (a1 == null && a2 != null) {
                    return 1;
                } else if (a1 != null && a2 == null) {
                    return -1;
                } else if (a1 == null && a2 == null) {
                    return 0;
                } else {
                    return a2.compareTo(a1);
                }
            }
        });
        topSet = new HashSet<Node>(top);
        for (int i = 0; i < top && i < nodes.length; i++) {
            topSet.add(nodes[i]);
        }
        return true;
    }

    @Override
    public boolean evaluate(Graph graph, Node node) {
        return topSet.contains(node);
    }

    @Override
    public void finish() {
        topSet = null;
    }

    @Override
    public String getName() {
        return "Keep the top N nodes for this column";
    }

    @Override
    public FilterProperty[] getProperties() {
        if (filterProperties == null) {
            filterProperties = new FilterProperty[0];
            try {
                filterProperties = new FilterProperty[]{
                    FilterProperty.createProperty(this, Column.class, "column"),
                    FilterProperty.createProperty(this, Integer.class, "top"),};
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return filterProperties;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Column getColumn() {
        return column;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
