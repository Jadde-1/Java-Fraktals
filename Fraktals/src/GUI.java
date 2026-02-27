import javax.swing.JFrame;

public class GUI {

	GUI() {
		System.out.println("printer 1");
		
		//kreation af the frame
				JFrame mainFrame = new JFrame("A* finding");
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				PathPanel pathPanel = new PathPanel();
				mainFrame.add(pathPanel);
				mainFrame.pack();
				mainFrame.setVisible(true);
	}
}
