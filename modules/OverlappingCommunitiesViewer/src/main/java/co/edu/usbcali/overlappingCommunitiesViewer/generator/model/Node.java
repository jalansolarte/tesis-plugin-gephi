/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.generator.model;

import java.util.ArrayList;
import java.util.List;
import org.gephi.io.importer.api.NodeDraft;

/**
 *
 * @author CamiloDelgado
 */
public class Node {
    private String id;
    private List<NodeDraft> communities;

    public Node(String id){
        this.id = id;
        communities = new ArrayList<NodeDraft>();
    }
    
    public void addCommunity(NodeDraft community){
        if(!communities.contains(community)){
            communities.add(community);
        }
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<NodeDraft> getCommunities() {
        return communities;
    }

    public void setCommunities(List<NodeDraft> communities) {
        this.communities = communities;
    }
    
    
}
