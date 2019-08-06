package game.state;

import java.awt.Graphics;
import java.awt.Graphics2D;

import game.Handler;
import game.backgrounds.Background;
import game.gfx.Assets;
import game.worlds.World;

public class GameState extends State {

	private World world;
	private int scale;
	private Background bg;
	private static Handler handler;
	
	public GameState(Handler handler) {
		super(handler);
		
		GameState.handler = handler;
		world = new World(handler, "res/world/world2.txt");
		handler.setWorld(world);
		world.loadPlayerHealth();
		bg = new Background(0, 0, scale, .8785, .784);
		bg.setScale(bg.getXScale(), bg.getYScale());
	}
	
	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(Assets.background, bg.getTransform(), null);

		world.render(g);
	}
	
	public static Handler getHandler() {
		return handler;
	}

}
