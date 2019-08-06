package game.entities.nonstatics.projectiles;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.Handler;
import game.entities.Entity;
import game.entities.nonstatics.Player;
import game.entities.nonstatics.enemies.Enemy;
import game.entities.statics.consumable.Consumable;
import game.gfx.Animation;
import game.gfx.Assets;

public class EnemyBullet extends Projectile {
	
	private float maxRange, currRange;
	private static final int DEFAULT_DAMAGE = 1,
							 DEFAULT_SPEED = 10,
							 DEFAULT_MAX_RANGE = 1000;
	private Animation peaTear;
	private boolean isHoming;

	public EnemyBullet(Handler handler, float x, float y, int width, int height, Entity origin) {
		super(handler, x, y, width, height);
		this.origin = origin;
		
		speed = DEFAULT_SPEED;
		maxRange = DEFAULT_MAX_RANGE;
		damage = DEFAULT_DAMAGE;
		
		peaTear = new Animation(30, Assets.peaTear);
		
		currRange = 0;
		isHoming = true;

		projectileSpeed();
		setBounds();
	}
	
	@Override
	protected void setBounds() {
		bounds.x = width / 2 - 10;
		bounds.y = height / 2 - 10;
		bounds.height = 20;
		bounds.width = 20;
	}

	@Override
	public void tick() {
		if (currRange >= maxRange) isDead = true;
		
		if (isHoming) projectileSpeed();
		
		if (!isDead) {
			move();
			checkCollisionDamage();
		} else {
			peaTear.tick();
			if (peaTear.getCurrentFrameIndex() == Assets.peaTear.length - 1)
				this.die();
		}
	}

	@Override
	protected void projectileSpeed() { 
		Player player = handler.getWorld().getEntityManager().getPlayer();
		Rectangle bounds = player.getBound();
		
		float y0 = player.getY() + bounds.y + (bounds.height / 2);
		float x0 = player.getX() + bounds.x + (bounds.width / 2);
		float y1 = y + this.bounds.y + (this.bounds.height / 2);
		float x1 = x + this.bounds.x + (this.bounds.width / 2);
		
		double distance = Math.sqrt((y0 - y1) * (y0 - y1) + 
									(x0 - x1) * (x0 - x1));
		float interval = (float) (distance / speed);
		
		xMove = (x0 - x1) / interval;
		yMove = (y0 - y1) / interval;
	}
	
	@Override
	public boolean entityCheck(Entity e) {
		return (e.equals(this) || e instanceof PlayerBullet || e.equals(origin) 
				|| e instanceof EnemyBullet || e.isDead() || e instanceof Consumable || e instanceof Enemy);
	}

	int i = 0;
	
	@Override
	public void render(Graphics g) {
		currRange += speed;

		if (!isDead)
			castShadow(g);
		
		g.drawImage(peaTear.getCurrentFrame(), (int) x, (int) y + i, width, height, null);
		
		if (currRange > maxRange - speed * 16 && !isDead) {
			i++;
			bounds.y++;
		}
		
//		g.setColor(Color.green);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), 
//				bounds.width, bounds.height);
	}

	@Override
	public void die() {
		this.setActive(false);
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}

	@Override
	public void castShadow(Graphics g) {
		float opacity = 0.2f;
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(Assets.shadow, (int) x + 34, (int) y + 60, width / 3, height / 3, null);
		
		opacity = 1.0f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}

}
