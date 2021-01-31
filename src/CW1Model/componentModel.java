/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW1Model;

import CW1View.mainFrameView;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author hassan
 */
public class componentModel {
    private mainFrameView view;
    private Collection<AnnotationModel> Annotations;
    
    public componentModel(mainFrameView view){
        this.view = view;
        this.Annotations = new ArrayList<AnnotationModel>();
    }
    
    public Collection<AnnotationModel> getAnnotationsList(){
        return this.Annotations;
    }
    
    public void removeAllAnnotations(){
        if(this.Annotations!=null){
            this.Annotations.removeAll(Annotations);
        }    
    }
    
    
}
