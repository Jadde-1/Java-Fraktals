import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class GUI extends JFrame {

	GUI() {
		System.out.println("printer 1");
		
		//kreation af the frame
				JFrame mainFrame = new JFrame("A* finding");
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Panel Panel = new Panel();
				mainFrame.add(Panel);
				mainFrame.pack();
				mainFrame.setVisible(true);
				setPreferredSize(800, 600);
				
				
	}
	

	public void keys() {
		//Idea Make a way to to radomely generate a fracktal with button
	    addMouseListener(new MouseAdapter() {
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
