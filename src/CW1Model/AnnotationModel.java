/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW1Model;

import java.awt.Shape;

/**
 *
 * @author hassan
 */
public class AnnotationModel {
    
    private int xLocation;
    private int yLocation;
    private String annotationText;
    private Shape cicle;
    
    public AnnotationModel(int x, int y, String text, Shape circle){
        this.xLocation = x;
        this.yLocation = y;
        this.annotationText = text;
        this.cicle = circle;
    }
    
    public int getXLocation(){
        return this.xLocation;
    }
    
    public int getYLocation(){
        return this.yLocation;
    }
    
    public String getAnnotationText(){
        return this.annotationText;
    }
    
    public Shape getShape(){
        return this.cicle;
    }
    
}
