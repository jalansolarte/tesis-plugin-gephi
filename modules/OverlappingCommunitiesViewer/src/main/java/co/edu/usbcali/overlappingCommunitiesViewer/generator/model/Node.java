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
    private List<NodeDraft> communitiesAsNodes;
    private List<Community> communitiesAsProperty;

    public Node(String id){
        this.id = id;
        communitiesAsNodes = new ArrayList<>();
        communitiesAsProperty = new ArrayList<>();
    }
    
    public void addCommunity(NodeDraft community){
        if(!communitiesAsNodes.contains(community)){
            communitiesAsNodes.add(community);
        }
    }
    
    public void addCommunity(Community community){
        if(!communitiesAsProperty.contains(community)){
            communitiesAsProperty.add(community);
        }
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<NodeDraft> getCommunitiesAsNodes() {
        return communitiesAsNodes;
    }
    
    public List<Community> getCommunitiesAsProperty() {
        return communitiesAsProperty;
    }
}
