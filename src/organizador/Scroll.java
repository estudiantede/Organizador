/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package organizador;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JLabel;

/**
 *
 * @author Avaya
 */
public class Scroll extends JLabel implements MouseWheelListener{

    public  Scroll()
    {
        addMouseWheelListener(this);
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("scroll");
        MainFrame.setTiempo(e.getWheelRotation()*-1);
        
    }
    
}
