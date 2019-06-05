/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbcali.overlappingCommunitiesViewer.generator.model;

import java.awt.Color;

/**
 *
 * @author CamiloDelgado
 */
public class Community {
    private Integer id;
    private Color color;
    
    public Community(Integer id, Color color){
        this.id = id;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
    
    
}
