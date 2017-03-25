package strangeattractor;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {
	public static JFrame frame = new JFrame("Physics");
	public static int width, height;
	
	public static void createWindow() {
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		
		frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		try {
			gd.setFullScreenWindow(frame);
			
		}	finally {
			
			}
		
		StrangeAttractor sa = new StrangeAttractor();
		frame.addKeyListener(new KeyInput());
		frame.add(sa);
		
		frame.setVisible(true);
		
	}
	
	
}
