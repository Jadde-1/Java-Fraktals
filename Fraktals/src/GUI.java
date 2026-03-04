import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Fracktal.SierpinskisTriangle;

public class GUI extends JFrame {
	  private DrawPanel drawPanel;
	  private int currentFractalType = 0; // 0 = none, 1 = Sierpinski, etc.
	  private double zoomLevel = 5.0;
	  private double centerX = 0.5; // Zoom center (normalized 0-1)
	  private double centerY = 0.5;
	  private Timer autoZoomTimer;
	  private boolean autoZoomEnabled = false;
	  
	  // Auto-zoom settings
	  private static final double ZOOM_SPEED = 1.02; // Multiplicative zoom per frame
	  private static final double MAX_ZOOM = 100.0; // Reset when reaching this zoom
	  private static final int ANIMATION_DELAY = 30; // Milliseconds between frames

	GUI() {
		System.out.println("Initializing GUI...");
		
		 setTitle("Fractal Viewer");
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	     //Use custom JPanel for drawing
	     drawPanel = new DrawPanel();
	     drawPanel.setPreferredSize(new Dimension(800, 600));
	     add(drawPanel);
	        
	     pack();
	     setVisible(true);
	        
	     // Add event listeners
	     setupKeyListener();
	     setupMouseListener();
	     setupAutoZoom();
	}
	
	private void setupAutoZoom() {
	    autoZoomTimer = new Timer(ANIMATION_DELAY, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (autoZoomEnabled && currentFractalType != 0) {
	                zoomLevel *= ZOOM_SPEED;
	                
	                // Reset zoom to create loop effect
	                if (zoomLevel >= MAX_ZOOM) {
	                    zoomLevel = 5.0;
	                    // Optional: Change zoom target for variety
	                    // centerX = Math.random();
	                    // centerY = Math.random();
	                }
	                
	                drawPanel.setZoomLevel(zoomLevel);
	                drawPanel.setCenterPoint(centerX, centerY);
	                drawPanel.repaint();
	            }
	        }
	    });
	}
	
	private void toggleAutoZoom() {
	    autoZoomEnabled = !autoZoomEnabled;
	    if (autoZoomEnabled) {
	        System.out.println("Auto-zoom enabled");
	        autoZoomTimer.start();
	    } else {
	        System.out.println("Auto-zoom disabled");
	        autoZoomTimer.stop();
	    }
	}
	
	  private class DrawPanel extends JPanel {
	        private int fractalType = 0;
	        private double zoom = 5.0;
	        private double cx = 0.5;
	        private double cy = 0.5;
	        
	        public void setFractalType(int type) {
	            this.fractalType = type;
	        }
	        
	        public void setZoomLevel(double zoom) {
	            this.zoom = zoom;
	        }
	        
	        public void setCenterPoint(double cx, double cy) {
	            this.cx = cx;
	            this.cy = cy;
	        }
	        
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            
	            if (fractalType == 1) {
	                SierpinskisTriangle triangle = new SierpinskisTriangle();
	                triangle.draw(g, getWidth(), getHeight(), zoom, cx, cy);
	            } else if (fractalType == 2) {
	                // Add other fractal types here
	                g.drawString("Fractal Type 2 - Not implemented yet", 50, 50);
	            } else if (fractalType == 3) {
	                g.drawString("Fractal Type 3 - Not implemented yet", 50, 50);
	            } else {
	                g.drawString("Press 1-3 to select a fractal, R for random", 50, 50);
	                g.drawString("Left click to zoom in, right click to zoom out", 50, 70);
	                g.drawString("Press A to toggle auto-zoom", 50, 90);
	            }
	            
	            // Show zoom level
	            if (autoZoomEnabled) {
	                g.drawString(String.format("Zoom: %.2fx (Auto)", zoom), 10, 20);
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
	            } else if (key == KeyEvent.VK_A) {
	                // Toggle auto-zoom
	                toggleAutoZoom();
	            } else if (key == KeyEvent.VK_SPACE) {
	                // Reset zoom
	                zoomLevel = 3.0;
	                drawPanel.setZoomLevel(zoomLevel);
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
	                if (zoomLevel < 1.0) zoomLevel = 1.0;
	                drawPanel.setZoomLevel(zoomLevel);
	                drawPanel.repaint();
	            }
	        }
	    });
	}
}