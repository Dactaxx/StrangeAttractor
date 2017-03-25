package strangeattractor;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Robot;

import javax.swing.JPanel;

public class StrangeAttractor extends JPanel implements Runnable {
	public static StrangeAttractor sa = new StrangeAttractor();
	
	public static boolean running = false;
	
	public static Point3D c = sa.new Point3D(100, 0, -100);
	public static Point3D o = sa.new Point3D(0, 0, 0);
	public static Point3D e = sa.new Point3D(0, 0, 500);
	public static Point3D d = sa.new Point3D();
	
	public static boolean keyW = false;
	public static boolean keyA = false;
	public static boolean keyS = false;
	public static boolean keyD	= false;
	public static boolean keySpace = false;
	public static boolean keyShift = false;
	
	public static double mouseX, mouseY;

	public static void main(String[] args) {
		Window.createWindow();
		start();
		
	}
	
	public static synchronized void start() {
		new Thread(new StrangeAttractor()).start();
		running = true;
		
	}
	
	public static void tick() throws AWTException {
		mouseX = MouseInfo.getPointerInfo().getLocation().getX() - 800;
		mouseY = MouseInfo.getPointerInfo().getLocation().getY() - 450;
		
		o.setY(o.getY() + mouseX * .001);
		o.setX(o.getX() - mouseY * .001);
		
		if(o.getX() >= 2 * Math.PI) o.setX(0);
		if(o.getX() < 0) o.setX(2 * Math.PI + o.getX());
		if(o.getY() >= 2 * Math.PI) o.setY(0);
		if(o.getY() < 2 * 0) o.setY(2 * Math.PI + o.getY());
		Robot rbt = new Robot();
		rbt.mouseMove(800, 450);
		
		double sensitivity = 1;
		
		if(keyW) {
			c.setZ(c.getZ() + sensitivity * Math.cos(o.getY()));
			c.setX(c.getX() + sensitivity * Math.sin(o.getY()));
		}
		
		if(keyA) {
			c.setZ(c.getZ() + sensitivity * Math.sin(o.getY()));
			c.setX(c.getX() - sensitivity * Math.cos(o.getY()));
		}
		
		if(keyS) {
			c.setZ(c.getZ() - sensitivity * Math.cos(o.getY()));
			c.setX(c.getX() - sensitivity * Math.sin(o.getY()));
		}
		
		if(keyD) {
			c.setZ(c.getZ() - sensitivity * Math.sin(o.getY()));
			c.setX(c.getX() + sensitivity * Math.cos(o.getY()));
		}
		
		if(keySpace) {
			c.setY(c.getY() - sensitivity);
		}
		
		if(keyShift) {
			c.setY(c.getY() + sensitivity);
		}
	}
	
	public static void render() {
		Window.frame.repaint();

		
	}	

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		Point3D origin = new Point3D(0, 0, 0);
		Point3D xAxis = new Point3D(500, 0, 0);
		Point3D yAxis = new Point3D(0, -500, 0);
		Point3D zAxis = new Point3D(0, 0, 500);

		g2d.setColor(new Color(255, 0, 0));
//		g2d.drawLine((int)project(origin).getX() + 960, (int)project(origin).getY() + 540, (int)project(xAxis).getX() + 960, (int)project(xAxis).getY() + 540);
		
		g2d.setColor(new Color(0, 255, 0));
//		g2d.drawLine((int)project(origin).getX() + 960, (int)project(origin).getY() + 540, (int)project(yAxis).getX() + 960, (int)project(yAxis).getY() + 540);
		
		g2d.setColor(new Color(0, 0, 255));
//		g2d.drawLine((int)project(origin).getX() + 960, (int)project(origin).getY() + 540, (int)project(zAxis).getX() + 960, (int)project(zAxis).getY() + 540);
		

		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRect(0, 0, 1920, 1080);
		double o = 10;
		double p = 28;
		double b = 8/3;
		
		Point3D d = new Point3D(1, 2, 3);
		Point3D dOld = new Point3D(1, 2, 3);
		double h = .01;
		g2d.setStroke(new BasicStroke(2));
		for(double i = 0; i < 10000; i += 1) {
			d.setX(dOld.getX() + h * (o * (dOld.getY() - dOld.getX())));
			d.setY(dOld.getY() + h * (dOld.getX() * (p - dOld.getZ()) - dOld.getY()));
			d.setZ(dOld.getZ() + h*(dOld.getX() * dOld.getY() - b * dOld.getZ()));
			
			g2d.setColor(new Color((int)i % 200 + 55, (int)i %150 + 105, (int)i % 100 + 155));
			g2d.drawLine((int)project(dOld).getX() + 960, (int)project(dOld).getY() + 540, (int)project(d).getX() + 960, (int)project(d).getY() + 540);
			dOld.setX(d.getX());
			dOld.setY(d.getY());
			dOld.setZ(d.getZ());
		}

		
	}
	
	@Override
	public void run() {
		while(running) {
			try {
				tick();
			} catch (AWTException e) {
			}
			render();
			
			try {
				Thread.sleep(16);
			
			} catch (InterruptedException e) {

			}
			running = true;
		}
	}

	public static Point2D project(Point3D a) {
		Double x = a.getX() - c.getX();
		Double y = a.getY() - c.getY();
		Double z = a.getZ() - c.getZ();
		
		Point2D b = sa.new Point2D();
		
		d.setX(Math.cos(o.getY()) * (Math.sin(o.getZ()) * y) + Math.cos(o.getZ()) * x - Math.sin(o.getY()) * z);
		d.setY(Math.sin(o.getX()) * (Math.cos(o.getY()) * z + Math.sin(o.getY()) * (Math.sin(o.getZ()) * y + Math.cos(o.getZ()) * x)) + Math.cos(o.getX()) * (Math.cos(o.getZ()) * y - Math.sin(o.getZ()) * x));
		d.setZ(Math.cos(o.getX()) * (Math.cos(o.getY()) * z + Math.sin(o.getY()) * (Math.sin(o.getZ()) * y + Math.cos(o.getZ()) * x)) - Math.sin(o.getX()) * (Math.cos(o.getZ()) * y - Math.sin(o.getZ()) * x));
		
//		b.setX((e.getZ() / d.getZ()) * d.getX() - e.getX());
//		b.setY((e.getZ() / d.getZ()) * d.getY() - e.getY());
		
		b.setX(e.getZ() * d.getX() / d.getZ());
		b.setY(e.getZ() * d.getY() / d.getZ());
		
		return b;
		
	}
	
	public class Point3D {
		private double x, y, z;
		public Point3D() {
			
		}
		
		public Point3D(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
			
		}
		
		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}

		public double getZ() {
			return z;
		}

		public void setZ(double z) {
			this.z = z;
		}

	}
	
	public class Point2D {
		private double x, y;
		public Point2D() {
			
		}
		
		public Point2D(double x, double y) {
			this.x = x;
			this.y = y;
			
		}
		
		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}

	}
}
