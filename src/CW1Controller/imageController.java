/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW1Controller;

import CW1Model.AnnotationModel;
import CW1Model.componentModel;
import CW1View.mainFrameView;
import java.io.File;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author hassan
 */
public class imageController {
    
    private mainFrameView view;
    private componentModel model;
    
    private File targetImage = null;
    
    public imageController(){
        this.view = new mainFrameView(this);
        this.model = new componentModel(this.view);
    }
    
    public void chooseFolder(){
        this.targetImage = this.view.getFileChooser();
        if (this.targetImage != null) {
            this.view.addImage(this.targetImage);
        }
    }
    
    public Collection<AnnotationModel> getAnnotationsFromModel(){
        return this.model.getAnnotationsList();
    }
    
    public void removeAnnotation(){  
        try{
            model.removeAllAnnotations();
        }catch(NullPointerException x){}  
               
    }
    
    
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new imageController());
        
    }
}
