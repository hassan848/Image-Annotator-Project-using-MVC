/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW1View;

import CW1Controller.imageController;
import CW1Model.AnnotationModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author hassan
 */
public class customComponentCW1 extends JComponent implements MouseListener{
    
    private BufferedImage image;
    private Collection<AnnotationModel> Annotations;
    private Shape circle;
    boolean checkCircle = false;
    
    private final imageController controller;
    
    public customComponentCW1(File imageFile, imageController controller){
        this.controller = controller;
          
        this.controller.removeAnnotation();
        
        //setPreferredSize(new Dimension(400,400));
        if(imageFile!=null){
            try {        
                this.image = ImageIO.read(imageFile);
                
                try{
                    setPreferredSize(new Dimension(this.image.getWidth(),this.image.getHeight()));
                    
                    this.addMouseListener(this);
                    this.Annotations = controller.getAnnotationsFromModel();
                    
                }catch (NullPointerException x){
                    JOptionPane.showMessageDialog(null, "Please Select An Image File", "Not an Image File", 0);
                }
                
            } catch (IOException x) {
                x.printStackTrace();
                JOptionPane.showMessageDialog(null, "Please Select An Image File", "Not an Image File", 0);
            }
            
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        if(image!=null){
            super.paintComponent(g);
            g = g.create();

            g.drawImage(this.image, 0, 0, this);
        
            paintInputToComponent(g);
        }
    }

    public void paintInputToComponent(Graphics g){
        super.paintComponent(g);
        g = g.create();
        g.setColor(Color.yellow);
        
        Graphics2D gDraw2d = (Graphics2D) g;
        
        for (AnnotationModel A : this.Annotations){
            g.drawString(A.getAnnotationText(), A.getXLocation(), A.getYLocation());
            gDraw2d.draw(A.getShape());
        }
    }
    
    public BufferedImage paintBufferedImage(BufferedImage imageToSave){
        
        Graphics g = imageToSave.getGraphics();
        
        g = g.create();
        g.setColor(Color.yellow);
        
        for (AnnotationModel A : this.Annotations){
            g.drawString(A.getAnnotationText(), A.getXLocation(), A.getYLocation());
     
        }
        return imageToSave;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        this.checkCircle = false;
        AnnotationModel selectedCircle = checkCircleLocation(me);
        
        if(!checkCircle){
            try{
                String getAnnotationTextD = JOptionPane.showInputDialog("Enter Your Annotation");

                if((getAnnotationTextD!=null)){
                    if(getAnnotationTextD.equals("")){ 
                    }else{
                        //Initialises the circle to have correct X and Y co-ordinates
                        this.circle = new Ellipse2D.Double(me.getX(), me.getY(), 25, 25);
                        //Add To Collection Objects of AnnotationModel
                        this.Annotations.add(new AnnotationModel(me.getX(), me.getY(), getAnnotationTextD, this.circle));

                        //draw annotations and circles to component
                        paintInputToComponent(this.getGraphics());
                    }    
                }
            }catch(HeadlessException | java.lang.NullPointerException ex){

            }
        }else{
            removeAnnotation(selectedCircle);
        }    
    }
    
    public void saveNewImage() throws IOException{
        
        if(image!=null){
            BufferedImage imageToSave = paintBufferedImage(this.image);
            JFileChooser fileSave = new JFileChooser();
            int endFile = fileSave.showSaveDialog(null);

            if (endFile == JFileChooser.APPROVE_OPTION) {
                File file = fileSave.getSelectedFile();

                if (!file.getName().toLowerCase().endsWith(".png")) {
                    file = new File(file.getParentFile(), file.getName() + ".png");
                }
                
                ImageIO.write(imageToSave , "png", file);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Please Open an Image First", "No Image Selected", 0);
        }
    }
    
    public AnnotationModel checkCircleLocation(MouseEvent me){
        for(AnnotationModel A: this.Annotations){
            //check if mouse was clicked inside the circle
            if(A.getShape().contains(me.getPoint())){
                this.checkCircle = true;
                return A;
            }
        }
        return null;
    }
    
    public void removeAnnotation(AnnotationModel annotation){
        int decision = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Annotation?","Delete Annotation", JOptionPane.YES_NO_OPTION);
        if (decision == 0){
            this.Annotations.remove(annotation);
            repaint();
        }    
    }
    
    

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
      
    }
    
    
}
