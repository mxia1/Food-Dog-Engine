package game.entities.nonstatics.projectiles;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game.Handler;
import game.entities.Entity;
import game.entities.nonstatics.NonstaticEntity;
import game.entities.statics.consumable.Consumable;
import game.gfx.Animation;
import game.gfx.Assets;

public class PlayerBullet extends Projectile {
	
	private int direction, // 0 = up, 1 = down, 2 = left, 3 = right
				angle;
	private float speedModifier, maxRange, currRange;
	private static final float DEFAULT_SPEED_MODIFIER = 1.5f;
	private static final int DEFAULT_DAMAGE = 5,
							 DEFAULT_SPEED = 5,
							 DEFAULT_RANGE = 400,
							 DEFAULT_ANGLE = 8;
	private Animation tear;

	public PlayerBullet(Handler handler, float x, float y, int width, int height, int direction, Entity origin) {
		super(handler, x, y, width, height);
		this.direction = direction;
		this.origin = origin;
		
		speedModifier = DEFAULT_SPEED_MODIFIER;
		speed = DEFAULT_SPEED * speedModifier;
		maxRange = DEFAULT_RANGE;
		angle = DEFAULT_ANGLE;
		currRange = 0;
		
		setBounds();
	
		damage = DEFAULT_DAMAGE;
		
		tear = new Animation(30, Assets.tear);
		projectileSpeed();
	}
	
	@Override
	protected void setBounds() {
		bounds.x = 24;
		bounds.y = 24;
		bounds.height = 16;
		bounds.width = 16;
	}

	@Override
	public void tick() {
		if (currRange >= maxRange) isDead = true;
		
		if (!isDead) {
			move();
			checkCollisionDamage();
		} else {
			tear.tick();
			if (tear.getCurrentFrameIndex() == Assets.tear.length - 1)
				this.die();
		}
	}

	@Override
	protected void projectileSpeed() { 
		if(direction == 0) { // up
			yMove = -speed; 
			if (((NonstaticEntity) origin).getxMove() < 0) xMove = -speed / angle;
			else if (((NonstaticEntity) origin).getxMove() > 0) xMove = speed / angle;
		}
		if(direction == 1) { // down
			yMove = speed; 
			if (((NonstaticEntity) origin).getxMove() < 0) xMove = -speed / angle;
			else if (((NonstaticEntity) origin).getxMove() > 0) xMove = speed / angle;
		}
		if(direction == 2) { // left
			xMove = -speed; 
			if (((NonstaticEntity) origin).getyMove() < 0) yMove = -speed / angle;
			else if (((NonstaticEntity) origin).getyMove() > 0) yMove = speed / angle;
		}
		if(direction == 3) { // right
			xMove = speed; 
			if (((NonstaticEntity) origin).getyMove() < 0) yMove = -speed / angle;
			else if (((NonstaticEntity) origin).getyMove() > 0) yMove = speed / angle;
		}
	}

	int i = 0;
	
	@Override
	public void render(Graphics g) {
		double x0 = xMove;
		double y0 = yMove;
		currRange += Math.sqrt(x0 * x0 + y0 * y0);
		
		//drawBounds(g);
		
		if (!isDead)
			castShadow(g);
		
		g.drawImage(tear.getCurrentFrame(), (int) x, (int) y + i, width, height, null);
		
		
		if (currRange > maxRange - speed * 24 && !isDead) {
			i++;
			bounds.y++;
		}
	}

	@Override
	public void die() {
		this.setActive(false);
	}
	
	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public boolean entityCheck(Entity e) {
		return (e.equals(this) || e instanceof PlayerBullet || e.equals(origin) 
				|| e instanceof EnemyBullet || e.isDead() || e instanceof Consumable);
	}

	@Override
	public void castShadow(Graphics g) {
		float opacity = 0.2f;
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(Assets.shadow, (int) x + 16, (int) y + 45, width / 2, height / 2, null);
		
		opacity = 1.0f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}

}
