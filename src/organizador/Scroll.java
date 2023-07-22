/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package organizador;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

/**
 *
 * @author Avaya
 */
public class Scroll extends JPanel implements MouseWheelListener{

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("scroll");
    }
    
}
