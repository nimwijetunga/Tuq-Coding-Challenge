package tic.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import tic.main.Main;

public class MouseHandler implements MouseListener, MouseMotionListener{
	
	private Main main;
	public static int x,y;
	
	public MouseHandler(Main main) {
				
		this.main = main;
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		main.setClicked(true);
			
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	

}
