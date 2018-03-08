package tic.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import tic.handler.MouseHandler;
import tic.handler.Sprite;


public class Board extends JPanel{
	
	Tile [][] tiles;
	private int width,height;
	private Main main;
	private boolean win = false;
	private double wc[];
	
	public Board(int width,int height, Main main) {
		tiles = new Tile[3][3];
		wc = new double[4];
		this.width = width;
		this.height = height;
		this.main = main;
		this.createTiles();
	}
	
	public void createTiles(){
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				Color c;
				if((i + 1) % 2 != 0){
					c = Color.BLACK;
					if((j + 1) % 2 == 0)
						c = Color.WHITE;
				}else{
					c = Color.WHITE;
					if((j + 1) % 2 == 0)
						c = Color.BLACK;
				}
				tiles[i][j] = new Tile(j * Tile.SIZE,i * Tile.SIZE,c);
			}
		}
	}
	
	public void update() {
		
	}
	
	public boolean set_p(Type type) {
		int tx = MouseHandler.x;
		int ty = MouseHandler.y;
		
		for(Tile [] i : tiles) {
			for(Tile t : i) {
				if(t.contains(tx, ty) && t.getType() == null) {
					t.setType(type);
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public boolean horiz_win(Type type) {
		
		int count = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(tiles[i][j].getType() == type) {
					count++;
				}
				if(count == 3) {//x1,y1,x2,y2
					win = true;
					wc[0] = tiles[i][j- 2].x;
					wc[1] = tiles[i][j].y + Tile.SIZE/2;
					wc[2] = tiles[i][j].x + Tile.SIZE;
					wc[3] = wc[1];
					return true;
				}
			}
			count = 0;
		}
		return false;
	}
	
	public boolean verti_win(Type type) {
		
		int count = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(tiles[j][i].getType() == type) {
					count++;
				}
				if(count == 3) {//x1,y1,x2,y2
					win = true;
					wc[0] = tiles[j][i].x + Tile.SIZE/2;
					wc[1] = tiles[j - 2][i].y;
					wc[2] = wc[0];
					wc[3] = tiles[j][i].y + Tile.SIZE;
					return true;
				}
			}
			count = 0;
		}
		return false;
	}
	
	public boolean diag_win(Type type) {
		if(tiles[0][0].getType() == type && tiles[1][1].getType() == type && tiles[2][2].getType() == type) {
			win = true;
			wc[0] = tiles[0][0].x;
			wc[1] = tiles[0][0].y;
			wc[2]= tiles[2][2].x + Tile.SIZE;
			wc[3] = tiles[2][2].y + Tile.SIZE;
			return true;
		}else if(tiles[0][2].getType() == type && tiles[1][1].getType() == type && tiles[2][0].getType() == type) {
			win = true;
			wc[0] = tiles[0][2].x + Tile.SIZE;
			wc[1] = tiles[0][2].y;
			wc[2]= tiles[2][0].x;
			wc[3] = tiles[2][0].y + Tile.SIZE;
			return true;
		}
		
		return false;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		int width = (int) main.getSize().getWidth(), height = (int) main.getSize().getHeight();
		g.fillRect(0, 0, width, height);
		
		
		
		Sprite x = new Sprite("res/X.png", Tile.SIZE / 2, Tile.SIZE / 2);
		Sprite o = new Sprite("res/O.png", Tile.SIZE / 2, Tile.SIZE / 2);
		
		for(Tile [] i : tiles){
			for(Tile t : i){
				g.setColor(Color.BLACK);
				g.drawRect((int) (t.getX()), (int)(t.getY()), Tile.SIZE, Tile.SIZE);
				if(t.getType() == Type.X) {
					x.paintComponent(g, (int) (t.getX() + Tile.SIZE / 2 - 20), (int) (t.getY() + Tile.SIZE / 2 - 20));
				}else if (t.getType() == Type.O) {
					o.paintComponent(g, (int) (t.getX() + Tile.SIZE / 2 - 20), (int) (t.getY() + Tile.SIZE / 2 - 20));
				}
			}
		}
		
		

		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Cooper Black", Font.BOLD, 24));
		if(main.isTurn_one()) {
			g.drawString("Player One", Tile.SIZE, Tile.SIZE * 4);
			x.paintComponent(g, (Tile.SIZE * 2) - 50, Tile.SIZE * 4 + 10);
		}else {
			g.drawString("Player Two", Tile.SIZE, Tile.SIZE * 4);
			o.paintComponent(g, (Tile.SIZE * 2) - 50, Tile.SIZE * 4 + 10);
		}
		
		if(win) {
			System.out.println(wc[0] + " " + wc[2]);
			g.setColor(Color.YELLOW);
			g.drawLine((int)wc[0], (int)wc[1], (int)wc[2], (int)wc[3]);
			g.setFont(new Font("Cooper Black", Font.BOLD, 45));
			g.drawString("Winner!", main.getWidth() / 2 - 120, main.getHeight() / 2 - 100);
		}
		

	}

}
