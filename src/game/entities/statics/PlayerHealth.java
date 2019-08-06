package game.entities.statics;

import java.awt.Graphics;

import game.Handler;
import game.entities.nonstatics.NonstaticEntity;
import game.gfx.Assets;

public class PlayerHealth extends StaticEntity{
	
	private Handler handler;
	private int playerMaxHealth, playerCurrHealth;

	public PlayerHealth(Handler handler, float x, float y) {
		super(handler, x, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH / 2, NonstaticEntity.DEFAULT_CREATURE_HEIGHT / 2);
		this.handler = handler;
		playerMaxHealth = handler.getWorld().getEntityManager().getPlayer().getHealth();
		playerCurrHealth = playerMaxHealth;   
		
		setBounds();
	}
	
	@Override
	protected void setBounds() {
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;
	}

	@Override
	public void tick() {
		playerCurrHealth = handler.getWorld().getEntityManager().getPlayer().getHealth();
	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < playerMaxHealth; i++) {
			if (i < playerCurrHealth) {
				g.drawImage(Assets.playerHealth[0], (int) x + (25 * i), (int) y, width, height, null);
			}
			else {
				g.drawImage(Assets.playerHealth[1], (int) x + (25 * i), (int) y, width, height, null);
			}
		}
		
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
