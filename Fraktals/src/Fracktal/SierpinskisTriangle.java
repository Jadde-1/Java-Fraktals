package Fracktal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SierpinskisTriangle {

    private static final int MAX_DEPTH = 11; // Recursion depth
    
    public void draw(Graphics g, int width, int height, double zoom) {
        draw(g, width, height, zoom, 0.5, 0.5);
    }
    
	public void draw(Graphics g, int width, int height, double zoom, double cx, double cy) {
		Graphics2D g2d = (Graphics2D) g;
		
		// Clear background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		
		// Calculate triangle dimensions with zoom
		int size = (int) (Math.min(width, height) * 0.8 * zoom);
		
		// Calculate center point based on zoom target
		int centerX = (int) (width * cx);
		int centerY = (int) (height * cy);
		
		// Define the three corners of the main triangle
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		
		// Top point
		xPoints[0] = centerX;
		yPoints[0] = centerY - size / 2;
		
		// Bottom left
		xPoints[1] = centerX - size / 2;
		yPoints[1] = centerY + size / 2;
		
		// Bottom right
		xPoints[2] = centerX + size / 2;
		yPoints[2] = centerY + size / 2;
		
		// Draw recursively
		g2d.setColor(Color.WHITE);
		drawSierpinski(g2d, xPoints, yPoints, 0);
	}
	
	private void drawSierpinski(Graphics2D g, int[] x, int[] y, int depth) {
	    if (depth >= MAX_DEPTH) {
	        // Draw filled triangle at max depth
	        g.fillPolygon(x, y, 3);
	        return;
	    }
	    
	    // Calculate midpoints
	    int[] mx = new int[3];
	    int[] my = new int[3];
	    
	    mx[0] = (x[0] + x[1]) / 2; // Midpoint between top and left
	    my[0] = (y[0] + y[1]) / 2;
	    
	    mx[1] = (x[1] + x[2]) / 2; // Midpoint between left and right
	    my[1] = (y[1] + y[2]) / 2;
	    
	    mx[2] = (x[2] + x[0]) / 2; // Midpoint between right and top
	    my[2] = (y[2] + y[0]) / 2;
	    
	    // Recursively draw three smaller triangles
	    // Top triangle
	    drawSierpinski(g, new int[]{x[0], mx[0], mx[2]}, new int[]{y[0], my[0], my[2]}, depth + 1);
	    
	    // Bottom left triangle
	    drawSierpinski(g, new int[]{mx[0], x[1], mx[1]}, new int[]{my[0], y[1], my[1]}, depth + 1);
	    
	    // Bottom right triangle
	    drawSierpinski(g, new int[]{mx[2], mx[1], x[2]}, new int[]{my[2], my[1], y[2]}, depth + 1);
	}
}