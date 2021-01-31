/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW1View;

import CW1Controller.imageController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author hassan
 */
public class mainFrameView extends JFrame{
    
    private final imageController controller;
    private final ButtonPanel panelButton;
    private final JFileChooser chooseFile = new JFileChooser();
    JPanel mainPanel = new JPanel(new BorderLayout());
    
    private customComponentCW1 imageComponent;
    
    private File targetImage = null;
    
    public mainFrameView(imageController controller){
        this.controller = controller;
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Image Annotator");
        
        
        this.panelButton = new ButtonPanel(this, controller);
        this.mainPanel.add(this.panelButton, BorderLayout.SOUTH);
        
        this.imageComponent = new customComponentCW1(this.targetImage, controller);
        this.mainPanel.add(this.imageComponent, BorderLayout.CENTER);
        
        add(this.mainPanel);
        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    public File getFileChooser() {
        if (this.chooseFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return this.chooseFile.getSelectedFile();
        } else {
            return null;
        }
    }
    
    public void addImage(File myFile){
        this.mainPanel.remove(this.imageComponent);
        this.imageComponent = new customComponentCW1(myFile, this.controller);
        this.mainPanel.add(this.imageComponent, BorderLayout.CENTER);
        add(this.mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    
    public void saveImage() throws IOException{
        this.imageComponent.saveNewImage();
    }
    
   
    
    
}
