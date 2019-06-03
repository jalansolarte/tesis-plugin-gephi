/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.layout;

import java.util.ArrayList;
import java.util.List;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.layout.spi.Layout;
import org.gephi.layout.spi.LayoutBuilder;
import org.gephi.layout.spi.LayoutProperty;
import org.gephi.layout.plugin.AbstractLayout;
import org.gephi.layout.plugin.ForceVectorNodeLayoutData;
import org.gephi.layout.plugin.ForceVectorUtils;

/**
 *
 * @author CamiloDelgado
 */
public class OcvLayout extends AbstractLayout implements Layout {

    //Graph
    protected Graph graph;
    //Flag
    private boolean executing = false;
    //Properties
    private double inertia;
    private double repulsionStrength;
    private double attractionStrength;
    private double maxDisplacement;
    private boolean freezeBalance;
    private double freezeStrength;
    private double freezeInertia;
    private double gravity;
    private double speed;
    private double cooling;
    private boolean outboundAttractionDistribution;
    private boolean adjustSizes;
    
    public OcvLayout(LayoutBuilder builder) {
        super(builder);
    }

    @Override
    public void initAlgo() {
        executing = true;
        ensureSafeLayoutNodePositions(graphModel);
    }
    
    @Override
    public boolean canAlgo() {
        return executing;
    }

    @Override
    public void goAlgo() {
        this.graph = graphModel.getGraphVisible();
        graph.readLock();
        
        try {
            Node[] nodes = graph.getNodes().toArray();
            Edge[] edges = graph.getEdges().toArray();

            for (Node n : nodes) {
                if (n.getLayoutData() == null || !(n.getLayoutData() instanceof ForceVectorNodeLayoutData)) {
                    n.setLayoutData(new ForceVectorNodeLayoutData());
                }
            }

            for (Node n : nodes) {
                ForceVectorNodeLayoutData layoutData = n.getLayoutData();
                layoutData.old_dx = layoutData.dx;
                layoutData.old_dy = layoutData.dy;
                layoutData.dx *= inertia;
                layoutData.dy *= inertia;
            }
            // repulsion
            if (isAdjustSizes()) {
                for (Node n1 : nodes) {
                    for (Node n2 : nodes) {
                        if (n1 != n2) {
                            ForceVectorUtils.fcBiRepulsor_noCollide(n1, n2, getRepulsionStrength() * (1 + graph.getDegree(n1)) * (1 + graph.getDegree(n2)));
                        }
                    }
                }
            } else {
                for (Node n1 : nodes) {
                    for (Node n2 : nodes) {
                        if (n1 != n2) {
                            ForceVectorUtils.fcBiRepulsor(n1, n2, getRepulsionStrength() * (1 + graph.getDegree(n1)) * (1 + graph.getDegree(n2)));
                        }
                    }
                }
            }
            // attraction
            if (isAdjustSizes()) {
                if (isOutboundAttractionDistribution()) {
                    for (Edge e : edges) {
                        Node nf = e.getSource();
                        Node nt = e.getTarget();
                        double bonus = (nf.isFixed() || nt.isFixed()) ? (100) : (1);
                        bonus *= e.getWeight();
                        ForceVectorUtils.fcBiAttractor_noCollide(nf, nt, bonus * getAttractionStrength() / (1 + graph.getDegree(nf)));
                    }
                } else {
                    for (Edge e : edges) {
                        Node nf = e.getSource();
                        Node nt = e.getTarget();
                        double bonus = (nf.isFixed() || nt.isFixed()) ? (100) : (1);
                        bonus *= e.getWeight();
                        ForceVectorUtils.fcBiAttractor_noCollide(nf, nt, bonus * getAttractionStrength());
                    }
                }
            } else {
                if (isOutboundAttractionDistribution()) {
                    for (Edge e : edges) {
                        Node nf = e.getSource();
                        Node nt = e.getTarget();
                        double bonus = (nf.isFixed() || nt.isFixed()) ? (100) : (1);
                        bonus *= e.getWeight();
                        ForceVectorUtils.fcBiAttractor(nf, nt, bonus * getAttractionStrength() / (1 + graph.getDegree(nf)));
                    }
                } else {
                    for (Edge e : edges) {
                        Node nf = e.getSource();
                        Node nt = e.getTarget();
                        double bonus = (nf.isFixed() || nt.isFixed()) ? (100) : (1);
                        bonus *= e.getWeight();
                        ForceVectorUtils.fcBiAttractor(nf, nt, bonus * getAttractionStrength());
                    }
                }
            }
            // gravity
            for (Node n : nodes) {

                float nx = n.x();
                float ny = n.y();
                double d = 0.0001 + Math.sqrt(nx * nx + ny * ny);
                double gf = 0.0001 * getGravity() * d;
                ForceVectorNodeLayoutData layoutData = n.getLayoutData();
                layoutData.dx -= gf * nx / d;
                layoutData.dy -= gf * ny / d;
            }
            // speed
            if (isFreezeBalance()) {
                for (Node n : nodes) {
                    ForceVectorNodeLayoutData layoutData = n.getLayoutData();
                    layoutData.dx *= getSpeed() * 10f;
                    layoutData.dy *= getSpeed() * 10f;
                }
            } else {
                for (Node n : nodes) {
                    ForceVectorNodeLayoutData layoutData = n.getLayoutData();
                    layoutData.dx *= getSpeed();
                    layoutData.dy *= getSpeed();
                }
            }
            // apply forces
            for (Node n : nodes) {
                ForceVectorNodeLayoutData nLayout = n.getLayoutData();
                if (!n.isFixed()) {
                    double d = 0.0001 + Math.sqrt(nLayout.dx * nLayout.dx + nLayout.dy * nLayout.dy);
                    float ratio;
                    if (isFreezeBalance()) {
                        nLayout.freeze = (float) (getFreezeInertia() * nLayout.freeze + (1 - getFreezeInertia()) * 0.1 * getFreezeStrength() * (Math.sqrt(Math.sqrt((nLayout.old_dx - nLayout.dx) * (nLayout.old_dx - nLayout.dx) + (nLayout.old_dy - nLayout.dy) * (nLayout.old_dy - nLayout.dy)))));
                        ratio = (float) Math.min((d / (d * (1f + nLayout.freeze))), getMaxDisplacement() / d);
                    } else {
                        ratio = (float) Math.min(1, getMaxDisplacement() / d);
                    }
                    nLayout.dx *= ratio / getCooling();
                    nLayout.dy *= ratio / getCooling();
                    float x = n.x() + nLayout.dx;
                    float y = n.y() + nLayout.dy;

                    n.setX(x);
                    n.setY(y);
                }
            }
        } finally {
            graph.readUnlockAll();
        }
    }

    @Override
    public void endAlgo() {
        graph.readLock();
        try {
            for (Node n : graph.getNodes()) {
                n.setLayoutData(null);
            }
        } finally {
            graph.readUnlockAll();
        }
        executing = false;
    }

    @Override
    public LayoutProperty[] getProperties() {
        List<LayoutProperty> properties = new ArrayList<>();
        
        return properties.toArray(new LayoutProperty[0]);
    }

    @Override
    public void resetPropertiesValues() {
        setInertia(0.1);
        setRepulsionStrength(200d);
        setAttractionStrength(10d);
        setMaxDisplacement(10d);
        setFreezeBalance(true);
        setFreezeStrength(80d);
        setFreezeInertia(0.2);
        setGravity(30d);
        setOutboundAttractionDistribution(false);
        setAdjustSizes(false);
        setSpeed(1d);
        setCooling(1d);
    }

    /**
     * @return the inertia
     */
    public double getInertia() {
        return inertia;
    }

    /**
     * @param inertia the inertia to set
     */
    public void setInertia(double inertia) {
        this.inertia = inertia;
    }

    /**
     * @return the repulsionStrength
     */
    public double getRepulsionStrength() {
        return repulsionStrength;
    }

    /**
     * @param repulsionStrength the repulsionStrength to set
     */
    public void setRepulsionStrength(double repulsionStrength) {
        this.repulsionStrength = repulsionStrength;
    }

    /**
     * @return the attractionStrength
     */
    public double getAttractionStrength() {
        return attractionStrength;
    }

    /**
     * @param attractionStrength the attractionStrength to set
     */
    public void setAttractionStrength(double attractionStrength) {
        this.attractionStrength = attractionStrength;
    }

    /**
     * @return the maxDisplacement
     */
    public double getMaxDisplacement() {
        return maxDisplacement;
    }

    /**
     * @param maxDisplacement the maxDisplacement to set
     */
    public void setMaxDisplacement(double maxDisplacement) {
        this.maxDisplacement = maxDisplacement;
    }

    /**
     * @return the freezeBalance
     */
    public boolean isFreezeBalance() {
        return freezeBalance;
    }

    /**
     * @param freezeBalance the freezeBalance to set
     */
    public void setFreezeBalance(boolean freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    /**
     * @return the freezeStrength
     */
    public double getFreezeStrength() {
        return freezeStrength;
    }

    /**
     * @param freezeStrength the freezeStrength to set
     */
    public void setFreezeStrength(double freezeStrength) {
        this.freezeStrength = freezeStrength;
    }

    /**
     * @return the freezeInertia
     */
    public double getFreezeInertia() {
        return freezeInertia;
    }

    /**
     * @param freezeInertia the freezeInertia to set
     */
    public void setFreezeInertia(double freezeInertia) {
        this.freezeInertia = freezeInertia;
    }

    /**
     * @return the gravity
     */
    public double getGravity() {
        return gravity;
    }

    /**
     * @param gravity the gravity to set
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the cooling
     */
    public double getCooling() {
        return cooling;
    }

    /**
     * @param cooling the cooling to set
     */
    public void setCooling(double cooling) {
        this.cooling = cooling;
    }

    /**
     * @return the outboundAttractionDistribution
     */
    public boolean isOutboundAttractionDistribution() {
        return outboundAttractionDistribution;
    }

    /**
     * @param outboundAttractionDistribution the outboundAttractionDistribution to set
     */
    public void setOutboundAttractionDistribution(boolean outboundAttractionDistribution) {
        this.outboundAttractionDistribution = outboundAttractionDistribution;
    }

    /**
     * @return the adjustSizes
     */
    public boolean isAdjustSizes() {
        return adjustSizes;
    }

    /**
     * @param adjustSizes the adjustSizes to set
     */
    public void setAdjustSizes(boolean adjustSizes) {
        this.adjustSizes = adjustSizes;
    }
    
    
}
