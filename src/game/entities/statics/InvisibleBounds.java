package game.entities.statics;

import java.awt.Graphics;

import game.Handler;
import game.entities.nonstatics.NonstaticEntity;

public class InvisibleBounds extends StaticEntity{
	
	public InvisibleBounds(Handler handler, float x, float y) {
		super(handler, x, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH, NonstaticEntity.DEFAULT_CREATURE_HEIGHT);
		
		invincible = true;

		setBounds();
	}
	
	@Override
	protected void setBounds() {
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 40;
		bounds.height = 45;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		
//		drawBounds(g);
	}
	
	@Override
	public void die() {

	}

	@Override
	protected void castShadow(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
