package game.entities.nonstatics;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;

public class Moly extends NonstaticEntity {
	
	Animation molyAnim;

	public Moly(Handler handler, float x, float y) {
		super(handler, x, y, 96, 96);
		
		molyAnim = new Animation(100, Assets.moly);
		
		setBounds();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkCollisionDamage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		molyAnim.tick();
		
	}

	@Override
	public void render(Graphics g) {
		drawBounds(g);
		g.drawImage(molyAnim.getCurrentFrame(), (int) x, (int) y, null);
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setBounds() {
		bounds.x = 20;
		bounds.y = 20;
		bounds.width = 30;
		bounds.height = 90;
		
	}

	@Override
	protected void castShadow(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
