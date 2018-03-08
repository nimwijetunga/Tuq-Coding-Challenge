package tic.main;

import java.awt.Color;
import java.awt.Rectangle;

public class Tile extends Rectangle{
	
	public static final int SIZE = 100;
	private Color col;
	public Color getCol() {
		return col;
	}


	private boolean contains;
	
	public boolean isContains() {
		return contains;
	}


	public void setContains(boolean contains) {
		this.contains = contains;
	}


	private Type type;
	
	
	public void setType(Type type) {
		this.type = type;
	}


	public Type getType() {
		return type;
	}


	public Tile(int x, int y, Color col) {
		this.x = x;
		this.y = y;
		this.col = col;
		this.type = null;
		contains = false;
		this.setBounds(x, y, Tile.SIZE, Tile.SIZE);
	}
	
	
	
	
	
	

}
