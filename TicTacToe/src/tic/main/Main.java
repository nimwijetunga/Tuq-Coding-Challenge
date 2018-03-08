package tic.main;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Scanner;

import javax.swing.JFrame;

import tic.handler.MouseHandler;


public class Main extends Applet implements Runnable{
	
	Board board;

	private static JFrame display;
	private static Main main;
	private Dimension size;

	private boolean runGame;
	private Image displayImg;
	private static int n = 3;
	private boolean turn_one = true, clicked = false;
	

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public boolean isTurn_one() {
		return turn_one;
	}
	
	public static void main (String [] args){
		//Allowing game to start once JFrame is created
		main = new Main(); 

		//Creating JFrame
		display = new JFrame();
		display.add(main);
		display.pack();
		display.setTitle("Tic Tac Toe - Vichara Wijetunga");
		display.setResizable(false);
		display.setLocationRelativeTo(null);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setVisible(true);
		main.start();
	}
	
	public Main(){
		size = new Dimension(n * 100 + 60, n * 100 + 60 * 3);
		setPreferredSize(size); 
		addMouseListener (new MouseHandler(this)); 
		addMouseMotionListener (new MouseHandler(this)); 
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void start(){
		board = new Board(n, n, this);
		new Thread (this).start();
		requestFocus();
		runGame = true;
	}
	
	public void stop(){
		runGame = false;
	}
	
	public void update(double delta){
		display.pack();
	}
	
	public void run() {

		displayImg = createVolatileImage(size.width, size.height);      

		long lastLoopTime = System.nanoTime(); 

		while (runGame){
			
			
			Type t;
			
			if(turn_one) {
				t = Type.X;
			}else {
				t = Type.O;
			}
			
			if(clicked && board.set_p(t)) {
				//System.out.println(t);
				clicked = false;
				if(turn_one) turn_one = false;
				else turn_one = true;
			}
			
			render();
			
			//Wins
			if(board.diag_win(t) || board.verti_win(t) || board.horiz_win(t)) {
				render();
				System.out.println(t + " Wins");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
			
			
		}
	}
	
	public void setTurn_one(boolean turn_one) {
		this.turn_one = turn_one;
	}
	
	public void updateBoard() {
		board.update();
	}
	
	public void render(){
		Graphics g;
		g = displayImg.getGraphics();
		//Code goes here
		board.paintComponent(g);
		
		g = this.getGraphics();

		g.drawImage(displayImg, 0, 0, size.width, size.height, 0, 0, size.width, size.height, null);
		

		g.dispose();
	}
	
	public boolean isRunGame() {
		return runGame;
	}

	public void setRunGame(boolean runGame) {
		
		this.runGame = runGame;
	}
	
	public Dimension getSize() {
		return size;
	}

}
