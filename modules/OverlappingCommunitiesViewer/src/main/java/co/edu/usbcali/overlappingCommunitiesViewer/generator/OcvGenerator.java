/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.generator;

import java.io.File;
import java.nio.file.Files;
import org.gephi.io.generator.spi.Generator;
import org.gephi.io.generator.spi.GeneratorUI;
import org.gephi.io.importer.api.ContainerLoader;
import org.gephi.io.importer.api.NodeDraft;
import org.gephi.utils.progress.ProgressTicket;
import org.openide.util.lookup.ServiceProvider;
import java.io.IOException;
import java.util.Map;
import javax.swing.JOptionPane;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.EdgeDraft;
import org.gephi.utils.progress.Progress;

import co.edu.usbcali.overlappingCommunitiesViewer.generator.model.Node;
import java.util.HashMap;
import org.gephi.io.importer.api.EdgeWeightMergeStrategy;

/**
 *
 * @author CamiloDelgado
 */
@ServiceProvider(service = Generator.class)
public class OcvGenerator implements Generator {
    
    protected ProgressTicket progressTicket;
    protected boolean cancel = false;
    private File fileTags;
    private File fileCommunities;
    private File fileRelations;
    private Boolean communitiesGrouping;
    
    private Float i = 0F;
    private Map<String, Node> nodes;
    
    @Override
    public void generate(ContainerLoader container) {
        
        if(communitiesGrouping == null){
            return;
        }
            
        Progress.start(progressTicket);
        container.setEdgeDefault(EdgeDirectionDefault.UNDIRECTED);
        
        if(communitiesGrouping){
            if(fileCommunities == null || fileRelations == null){
                return;
            }
            
            nodes = new HashMap<>();
            
            //Se crean las comunidades
            try {
                i = 0F;
                Files.readAllLines(fileCommunities.toPath()).forEach((line)->{
                    String[] nodosComunidad = line.split(", ");

                    NodeDraft community = container.factory().newNodeDraft("" + i.intValue());
                    community.setSize(nodosComunidad.length);
                    container.addNode(community);
                    
                    for (String nodo : nodosComunidad) {
                        nodo = nodo.replaceAll("\\[", "");
                        nodo = nodo.replaceAll("\\]", "");
                        nodo = nodo.replaceAll("\n", "");
                        
                        Node node;
                        if(nodes.containsKey(nodo)){
                            node = nodes.get(nodo);
                        }else{
                            node = new Node(nodo);
                        }
                        
                        node.addCommunity(community);
                        nodes.put(nodo, node);
                    }
                    i++;
                });

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Communities File Error", JOptionPane.ERROR_MESSAGE);
            }

            //Se crean las relaciones
            try {
                Files.readAllLines(fileRelations.toPath()).forEach((line)->{
                    String[] componentes = line.split(" ");

                    String nodo1 = componentes[0];
                    Node node1 = null;
                    if(nodes.containsKey(nodo1)){
                        node1 = nodes.get(nodo1);
                    }
                    
                    String nodo2 = componentes[1];
                    Node node2 = null;
                    if(nodes.containsKey(nodo2)){
                        node2 = nodes.get(nodo2);
                    }
                    
                    String valorStr = componentes[2];
                    Double valor = 0D;
                    try {
                        valor = Double.valueOf(valorStr);
                    } catch (Exception e) {
                    }

                    if(node1 != null && node2 != null){
                        EdgeDraft edge;
                        for (NodeDraft community1 : node1.getCommunities()) {
                            for (NodeDraft community2 : node2.getCommunities()) {
                                edge = container.factory().newEdgeDraft();
                                edge.setSource(community1);
                                edge.setTarget(community2);
                                edge.setWeight(valor);

                                container.addEdge(edge);
                            }
                        }
                    }
                });
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Relations File Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            if(fileTags == null || fileCommunities == null || fileRelations == null){
                return;
            }

            container.addNodeColumn("tags", String.class);
            container.addNodeColumn("isCommunity", Boolean.class);
            
            //Se crean los nodos
            try {
            
                Files.readAllLines(fileTags.toPath()).forEach((line)->{
                    String[] tags = line.split(" ");

                    String nodo = tags[0];
                    String tagsStr = "";
                    String tag;

                    for (int i = 1; i < tags.length; i++) {
                        tag = tags[i].replaceAll("\n", "");
                        tagsStr += tag.toLowerCase() + " ";
                    }
                    tagsStr = tagsStr.trim();
                    NodeDraft node = container.factory().newNodeDraft(nodo);
                    node.setValue("tags", tagsStr);
                    node.setValue("isCommunity", false);

                    container.addNode(node);
                });
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Tags File Error", JOptionPane.ERROR_MESSAGE);
            }

            //Se crean las relaciones
            try {
                Files.readAllLines(fileRelations.toPath()).forEach((line)->{
                    String[] componentes = line.split(" "); 

                    String nodo1 = componentes[0];
                    NodeDraft node1 = container.getNode(nodo1);
                    String nodo2 = componentes[1];
                    NodeDraft node2 = container.getNode(nodo2);

                    String valorStr = componentes[2];
                    Double valor = 0D;
                    try {
                        valor = Double.valueOf(valorStr);
                    } catch (Exception e) {
                    }

                    EdgeDraft edge = container.factory().newEdgeDraft();
                    edge.setSource(node1);
                    edge.setTarget(node2);
                    edge.setWeight(valor);

                    container.addEdge(edge);
                });
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Relations File Error", JOptionPane.ERROR_MESSAGE);
            }

            //Se crean las comunidades
            try {
                i = 0F;
                Files.readAllLines(fileCommunities.toPath()).forEach((line)->{
                    String[] nodosComunidad = line.split(", ");

                    NodeDraft community = container.factory().newNodeDraft("Community-" + i);
                    community.setValue("isCommunity", true);
                    community.setSize(nodosComunidad.length);
                    container.addNode(community);
                    for (String nodo : nodosComunidad) {
                        nodo = nodo.replaceAll("\\[", "");
                        nodo = nodo.replaceAll("\\]", "");
                        nodo = nodo.replaceAll("\n", "");

                        if(container.nodeExists(nodo)){
                            EdgeDraft edge = container.factory().newEdgeDraft();
                            edge.setSource(community);
                            edge.setTarget(container.getNode(nodo));
                            edge.setWeight(0D);

                            container.addEdge(edge);
                        }
                    }
                    i++;
                });

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Communities File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        Progress.finish(progressTicket);
    }

    @Override
    public String getName() {
        return "OgvGeneration";
    }

    @Override
    public GeneratorUI getUI() {
        return new OcvGeneratorUI();
    }

    @Override
    public boolean cancel() {
        cancel = true;
        return true;
    }

    @Override
    public void setProgressTicket(ProgressTicket progressTicket) {
        this.progressTicket = progressTicket;
    }

    public void setFileTags(File fileTags) {
        this.fileTags = fileTags;
    }

    public void setFileCommunities(File fileCommunities) {
        this.fileCommunities = fileCommunities;
    }

    public void setFileRelations(File fileRelations) {
        this.fileRelations = fileRelations;
    }
    
    public void setCommunitiesGrouping(Boolean communitiesGrouping) {
        this.communitiesGrouping = communitiesGrouping;
    }
}