package game.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	// static
	
	public static Tile[] tiles = new Tile[256];
	public static Tile poopTile = new PoopTile(0);
	public static Tile chestTile = new ChestTile(1);
	public static Tile trapdoorTile = new TrapdoorTile(2);
	public static Tile emptyTile = new EmptyTile(4);
	
	//class
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}
}
