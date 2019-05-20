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
import javax.swing.JOptionPane;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.EdgeDraft;
import org.gephi.utils.progress.Progress;

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
    
    private int i = 0;

    @Override
    public void generate(ContainerLoader container) {
        if(fileTags == null || fileCommunities == null || fileRelations == null){
            return;
        }
        
        Progress.start(progressTicket);
        
        container.addNodeColumn("tags", String.class);
        container.addNodeColumn("isCommunity", Boolean.class);
        container.setEdgeDefault(EdgeDirectionDefault.UNDIRECTED);
        
        //Se crean los nodos
        try {
            Files.readAllLines(fileTags.toPath()).forEach((line)->{
                String[] tags = line.split(" ");
                
                String nodo = tags[0];
                String tagsStr = "";
                String tag;
                
                for (int i = 1; i < tags.length; i++) {
                    tag = tags[i].replaceAll("\n", "");
                    tagsStr += tag + " ";
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
            i = 0;
            Files.readAllLines(fileCommunities.toPath()).forEach((line)->{
                String[] nodosComunidad = line.split(", ");
                
                NodeDraft community = container.factory().newNodeDraft("Community-" + i);
                community.setValue("isCommunity", true);
                
                container.addNode(community);
                for (String nodo : nodosComunidad) {
                    nodo = nodo.replaceAll("\\[", "");
                    nodo = nodo.replaceAll("\\]", "");
                    nodo = nodo.replaceAll("\n", "");
                    
                    NodeDraft node = container.getNode(nodo);
                    
                    if(node != null){
                        EdgeDraft edge = container.factory().newEdgeDraft();
                        edge.setSource(community);
                        edge.setTarget(node);
                        edge.setWeight(0D);
                    
                        container.addEdge(edge);
                    }
                }
                i++;
            });
            
            Progress.finish(progressTicket);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Communities File Error", JOptionPane.ERROR_MESSAGE);
        }
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
}