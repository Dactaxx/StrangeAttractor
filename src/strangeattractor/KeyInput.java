package strangeattractor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			StrangeAttractor.keyW = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			StrangeAttractor.keyA = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			StrangeAttractor.keyS = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			StrangeAttractor.keyD = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			StrangeAttractor.keySpace = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			StrangeAttractor.keyShift = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			StrangeAttractor.keyW = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			StrangeAttractor.keyA = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			StrangeAttractor.keyS = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			StrangeAttractor.keyD = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			StrangeAttractor.keySpace = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			StrangeAttractor.keyShift = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
