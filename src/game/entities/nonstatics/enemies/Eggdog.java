package game.entities.nonstatics.enemies;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.Handler;
import game.entities.Entity;
import game.entities.nonstatics.NonstaticEntity;
import game.entities.nonstatics.Player;
import game.entities.nonstatics.projectiles.EnemyBullet;
import game.gfx.Animation;
import game.gfx.Assets;

public class Eggdog extends Enemy {

	private Animation animEggdog, animEggdogHurt;
	private long lastAttackTimer, attackCooldown = 20, attackTimer = attackCooldown;
	
	public Eggdog(Handler handler, float x, float y) {
		super(handler, x, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH, NonstaticEntity.DEFAULT_CREATURE_HEIGHT);
	
		health = 20;
		speed = 2;
		
		animEggdog = new Animation(25, Assets.eggdog);
		animEggdogHurt = new Animation(25, Assets.eggdogHurt);
		
		setBounds();
	}
	
	@Override
	protected void setBounds() {
		bounds.x = 17;
		bounds.y = 19;
		bounds.width = 30;
		bounds.height = 40;
	}

	@Override
	public void tick() {
		animEggdog.tick();
		animEggdogHurt.tick();
		
		checkSpeed();
		move();
		checkCollisionDamage();
		//checkAttacks();
	}
	
	private void checkSpeed() { 
		Player player = handler.getWorld().getEntityManager().getPlayer();
		double distance = Math.sqrt((player.getY() - y) * (player.getY() - y) + 
									(player.getX() - x) * (player.getX() - x));
		float interval = (float) (distance / speed);
		xMove = (player.getX() - x) / interval;
		yMove = (player.getY() - y) / interval;
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if (attackTimer < attackCooldown) return;
		
		ArrayList <Entity> list = handler.getWorld().getEntityManager().getEntities();
		
		// check shooting
		list.add(new EnemyBullet(handler, x, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH, NonstaticEntity.DEFAULT_CREATURE_HEIGHT, this));
		attackTimer = 0;
	}

	@Override
	public void render(Graphics g) {	
		castShadow(g);
		
		if (hitTimer > 0) {
			g.drawImage(animEggdogHurt.getCurrentFrame(), (int) x, (int) y, null);
			hitTimer -= 10;
		}
		else g.drawImage(animEggdog.getCurrentFrame(), (int) x, (int) y, null);
	}

	@Override
	public void die() {

	}

	@Override
	protected void castShadow(Graphics g) {
		float opacity = 0.2f;
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(Assets.shadow, (int) x, (int) y + 28, width, height, null);
		
		opacity = 1.0f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
	}
}
