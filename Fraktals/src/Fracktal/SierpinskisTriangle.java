package Fracktal;
import java.awt.Graphics;

public class SierpinskisTriangle {
    private int depth = 10; //Recursion depth
    
    public void draw(Graphics g, int width, int height, double zoom) {
        //Define the three points of the main triangle
        int x1 = width / 2;
        int y1 = (int) (50 / zoom);
        
        int x2 = (int) ((width / 2 - 300) / zoom + (width / 2) * (1 - 1/zoom));
        int y2 = (int) ((height - 50) / zoom + 50 * (1 - 1/zoom));
        
        int x3 = (int) ((width / 2 + 300) / zoom + (width / 2) * (1 - 1/zoom));
        int y3 = (int) ((height - 50) / zoom + 50 * (1 - 1/zoom));
        
        //Start recursive drawing
        drawTriangle(g, x1, y1, x2, y2, x3, y3, depth);
    }
    
    private void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int depth) {
        if (depth == 0) {
            // Draw the triangle
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x2, y2, x3, y3);
            g.drawLine(x3, y3, x1, y1);
        } else {
            //Calculate midpoints
            int x12 = (x1 + x2) / 2;
            int y12 = (y1 + y2) / 2;
            
            int x23 = (x2 + x3) / 2;
            int y23 = (y2 + y3) / 2;
            
            int x31 = (x3 + x1) / 2;
            int y31 = (y3 + y1) / 2;
            
            // Recursively draw three smaller triangles
            drawTriangle(g, x1, y1, x12, y12, x31, y31, depth - 1);
            drawTriangle(g, x12, y12, x2, y2, x23, y23, depth - 1);
            drawTriangle(g, x31, y31, x23, y23, x3, y3, depth - 1);
        }
    }
}