import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Fracktal.SierpinskisTriangle;

public class GUI extends JFrame {
	  private DrawPanel drawPanel;
	  private int currentFractalType = 0; // 0 = none, 1 = Sierpinski, etc.
	  private double zoomLevel = 1.0;

	GUI() {
		System.out.println("Initializing GUI...");
		
		 setTitle("Fractal Viewer");
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	     //Use custom JPanel for drawing
	     drawPanel = new DrawPanel();
	     drawPanel.setPreferredSize(new Dimension(1200, 1000));
	     add(drawPanel);
	        
	     pack();
	     setVisible(true);
	        
	        // Add event listeners
	     setupKeyListener();
	     setupMouseListener();
				
	}
	
	  private class DrawPanel extends JPanel {
	        private int fractalType = 0;
	        private double zoom = 1.0;
	        
	        public void setFractalType(int type) {
	            this.fractalType = type;
	        }
	        
	        public void setZoomLevel(double zoom) {
	            this.zoom = zoom;
	        }
	        
	        @Override
	        protected void paintComponent(Graphics g) {
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
	  
	private void setupKeyListener() {
	    addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            int key = e.getKeyCode();
	            
	            if (key == KeyEvent.VK_1) {
	                currentFractalType = 1;
	                System.out.println("Drawing Sierpinski's Triangle");
	                drawPanel.setFractalType(1);
	                drawPanel.repaint();
	            } else if (key == KeyEvent.VK_2) {
	                currentFractalType = 2;
	                System.out.println("Drawing Fractal Type 2");
	                drawPanel.setFractalType(2);
	                drawPanel.repaint();
	            } else if (key == KeyEvent.VK_3) {
	                currentFractalType = 3;
	                System.out.println("Drawing Fractal Type 3");
	                drawPanel.setFractalType(3);
	                drawPanel.repaint();
	            } else if (key == KeyEvent.VK_R) {
	                // Random fractal
	                currentFractalType = (int) (Math.random() * 3) + 1;
	                drawPanel.setFractalType(currentFractalType);
	                drawPanel.repaint();
	            }
	        }
	    });
	}
	
	private void setupMouseListener() {
	    drawPanel.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if (e.getButton() == MouseEvent.BUTTON1) { // Left click to zoom in
	                zoomLevel *= 1.5;
	                drawPanel.setZoomLevel(zoomLevel);
	                drawPanel.repaint();
	            } else if (e.getButton() == MouseEvent.BUTTON3) { // Right click to zoom out
	                zoomLevel /= 1.5;
	                drawPanel.setZoomLevel(zoomLevel);
	                drawPanel.repaint();
	            }
	        }
	    });
	}
	
}
