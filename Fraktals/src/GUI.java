import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

import Fracktal.SierpinskisTriangle;
import GUI.DrawPanel;

public class GUI extends JFrame {
	
	  private int currentFractalType = 0; // 0 = none, 1 = Sierpinski, etc.
	  private double zoomLevel = 1.0;

	GUI() {
		System.out.println("Initializing GUI...");
		
		 setTitle("Fractal Viewer");
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	     // Use custom JPanel for drawing
	     drawPanel = new DrawPanel();
	     drawPanel.setPreferredSize(new Dimension(800, 600));
	     add(drawPanel);
	        
	     pack();
	     setVisible(true);
	        
	        // Add event listeners
	     setupKeyListener();
	     setupMouseListener();
				
	}
	
	public void drawPanel() {
		   super.paintComponent(g);
           
           if (fractalType == 1) {
               SierpinskisTriangle triangle = new SierpinskisTriangle();
               triangle.draw(g, getWidth(), getHeight(), zoom);
           } else if (fractalType == 2) {
               // Add other fractal types here
               g.drawString("Fractal Type 2 - Not implemented yet", 50, 50);
           } else if (fractalType == 3) {
               g.drawString("Fractal Type 3 - Not implemented yet", 50, 50);
           } else {
               g.drawString("Press 1-3 to select a fractal, R for random", 50, 50);
               g.drawString("Left click to zoom in, right click to zoom out", 50, 70);
           }
       }
	}
		

	public void keys() {
		//Idea Make a way to to radomely generate a fracktal with button
	    addMouseListener(new KeyAdapter() {
	    	public void mousePressed(MouseEvent e) {
	    		if (//number pressed 1 draw number one) {
	    			//draw serpinskis triangel
	    				
	    		} else if (//nr 2 draw nr 2) {
	    			
	    		} else if (// and so on) {
	    			
	    		}
	    			
	                
	        });
	        
	    //Idea make a zoom button that will zoom in on the fracktal indefenetly
	    addMouseListener(new MouseAdapter() {
	         public void mousePressed(MouseEvent e) {

	                 }
	         });
	         
	}
	
	
}
