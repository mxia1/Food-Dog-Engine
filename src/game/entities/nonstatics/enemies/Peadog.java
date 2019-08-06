package game.entities.nonstatics.enemies;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game.Handler;
import game.entities.nonstatics.NonstaticEntity;
import game.entities.nonstatics.projectiles.EnemyBullet;
import game.gfx.Animation;
import game.gfx.Assets;

public class Peadog extends Enemy {
	
	private Animation peadogAnim, peadogShoot, currentAnim;
	private Handler handler;
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
	private boolean isShooting, shot;

	public Peadog(Handler handler, float x, float y) {
		super(handler, x, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH, NonstaticEntity.DEFAULT_CREATURE_HEIGHT);
		this.handler = handler;
		health = 20;
		
		peadogAnim = new Animation(50, Assets.peadog);
		peadogShoot = new Animation(100, Assets.peadogShoot);
		
		isShooting = false;
		shot = false;
		
		setBounds();
	}
	
	@Override
	protected void setBounds() {
		bounds.x = 15;
		bounds.y = 32;
		bounds.width = 68;
		bounds.height = 25;
	}

	@Override
	public void tick() {
		checkCollisionDamage();
		
		if (isShooting) {
			currentAnim = peadogShoot;
			if (peadogShoot.getCurrentFrameIndex() == Assets.peadogShoot.length / 2 && !shot) {
				handler.getWorld().getEntityManager().getEntities().add
				(new EnemyBullet(handler, x + bounds.x + (bounds.width / 2), y, 100, 100, this));
				shot = true;
			}
			
			peadogShoot.tick();
			
			if (peadogShoot.getCurrentFrameIndex() == Assets.peadogShoot.length - 1) {
				isShooting = false;
				shot = false;
			}
		}
		else {
			currentAnim = peadogAnim;
			peadogAnim.tick();	
		}
		
		//shoot();
		
	}
	
	public void shoot() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if (attackTimer < attackCooldown) return;
		
		//if (manager.getPlayer().getX() > (x + bounds.x + bounds.width))
		isShooting = true;

		attackTimer = 0;
	}

	@Override
	public void render(Graphics g) { 
		//drawBounds(g);
		castShadow(g);
		g.drawImage(currentAnim.getCurrentFrame(), (int) x, (int) y, 100,
					100, null);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void castShadow(Graphics g) {
		float opacity = 0.2f;
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(Assets.shadow, (int) x - 15, (int) y + 38, width * 2, height, null);
		
		opacity = 1.0f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}
}
