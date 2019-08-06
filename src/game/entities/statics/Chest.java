package game.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.entities.nonstatics.NonstaticEntity;
import game.gfx.Assets;

public class Chest extends StaticEntity{

	public Chest(Handler handler, float x, float y) {
		super(handler, x, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH, NonstaticEntity.DEFAULT_CREATURE_WIDTH);
		invincible = true;
	}
	
	@Override
	protected void setBounds() {
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.red);
//		g.fillRect((int)x, (int)y, bounds.width, bounds.height);
		g.drawImage(Assets.chest, (int) x, (int) y, bounds.width, bounds.height, null);

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void castShadow(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
