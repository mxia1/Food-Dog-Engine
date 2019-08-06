package game.entities.statics.consumable;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game.Handler;
import game.entities.nonstatics.Player;
import game.gfx.Assets;

public class Heart extends Consumable {

	public Heart(Handler handler, float x, float y) {
		super(handler, x, y, Consumable.DEFAULT_CONSUMABLE_WIDTH, Consumable.DEFAULT_CONSUMABLE_HEIGHT);
		setBounds();
	}
	
	@Override
	protected void setBounds() {
		bounds.x = 5;
		bounds.y = 5;
		bounds.width = 15;
		bounds.height = 15;
	}

	@Override
	public void tick() {
		Player player = handler.getWorld().getEntityManager().getPlayer();
		
		if (isDead) this.die();
		
		if (player.getHealth() == player.getMaxHealth()) setSolid(false);
		else setSolid(true);

	}

	@Override
	public void render(Graphics g) {
		castShadow(g);
		g.drawImage(Assets.heart, (int) x, (int) y, width, height, null);
		//drawBounds(g);
	}

	@Override
	public void die() {
		this.setActive(false);
	}

	@Override
	public void consume() {
		Player player = handler.getWorld().getEntityManager().getPlayer();
		
		if (player.getHealth() == player.getMaxHealth()) {
			return;
		}
		
		player.setHealth(player.getHealth() + 1);
		this.kill();
	}

	@Override
	protected void castShadow(Graphics g) {
		float opacity = 0.2f;
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(Assets.shadow, (int) x - 12, (int) y - 2, width * 2, height * 2, null);
		
		opacity = 1.0f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}
}
