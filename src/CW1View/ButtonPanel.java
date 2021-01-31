/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW1View;

import CW1Controller.imageController;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author hassan
 */
public class ButtonPanel extends JPanel{
    
    private final JButton openButton = new JButton("Open");
    private final JButton saveButton = new JButton("Save");
    
    public ButtonPanel(mainFrameView view, imageController controller){
        
        this.setLayout(new GridLayout());
        
        this.openButton.addActionListener((ev) -> controller.chooseFolder());
        
        this.saveButton.addActionListener((ev) -> {
            try {
                view.saveImage();
            } catch (IOException ex) {
                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        add(this.openButton);
        add(this.saveButton);
        
        
    }
    
}
