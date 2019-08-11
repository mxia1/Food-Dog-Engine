package game.worlds;

import java.awt.Graphics;

import game.Handler;
import game.entities.EntityManager;
import game.entities.nonstatics.Moly;
import game.entities.nonstatics.Player;
import game.entities.nonstatics.enemies.Eggdog;
import game.entities.nonstatics.enemies.Peadog;
import game.entities.statics.InvisibleBounds;
import game.entities.statics.PlayerHealth;
import game.entities.statics.consumable.Heart;
import game.tile.Tile;
import game.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private EntityManager entityManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 600, 200));
		entityManager.addEntity(new Peadog(handler, 100, 100));
		entityManager.addEntity(new Peadog(handler, 1000, 100));
		entityManager.addEntity(new Moly(handler,200, 200));
		entityManager.addEntity(new Eggdog(handler, 100, 200));
		//entityManager.addEntity(new Eggdog(handler, 100, 300)); 
		
		entityManager.addEntity(new Heart(handler, 300, 300));
		entityManager.addEntity(new Heart(handler, 350, 300));
		entityManager.addEntity(new Heart(handler, 400, 300));
		
		for(int i = 0; i < handler.getWidth() / 40; i++) {
			entityManager.addEntity(new InvisibleBounds(handler, i * 40, 0));
		}
		
		for(int i = 0; i < handler.getWidth() / 40; i++) {
			entityManager.addEntity(new InvisibleBounds(handler, i * 40, handler.getHeight() - 45));
		}
		
		for(int i = 1; i < handler.getHeight() / 45; i++) {
			entityManager.addEntity(new InvisibleBounds(handler, 0, i * 45));
		}
		
		for(int i = 1; i < handler.getHeight() / 45; i++) {
			entityManager.addEntity(new InvisibleBounds(handler, handler.getWidth() - 40, i * 45));
		}
		
//		loadWorld(path);
	}
	
	public void loadPlayerHealth() {
		entityManager.addEntity(new PlayerHealth(handler, 0, 0));
	}
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH), 
							(int) (y * Tile.TILEHEIGHT)); 
			}
		}
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.emptyTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if (t == null) return Tile.emptyTile;
		
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
