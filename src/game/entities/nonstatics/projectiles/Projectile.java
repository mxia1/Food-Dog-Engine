package game.entities.nonstatics.projectiles;

import game.Handler;
import game.entities.Entity;
import game.entities.nonstatics.NonstaticEntity;

public abstract class Projectile extends NonstaticEntity{
	
	protected Entity origin;
	protected int damage;
	
	public abstract int getDamage();
	
	protected abstract void projectileSpeed();
	
	public Projectile(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		invincible = true;
	}
	
	public Entity getOrigin() {
		return origin;
	}
	
	@Override
	protected void checkCollisionDamage() {
		if (checkEntityCollisions(1, 0f) || checkEntityCollisions(0f, 1) ||
				checkEntityCollisions(-1, 0f) || checkEntityCollisions(0f, -1)) {
			if (checkEntityCollisions(1, 0f)) {
				getCollidedEntity(1, 0f).hurt(damage);
				isDead = true;
			}
			if (checkEntityCollisions(0f, 1)) {
				getCollidedEntity(0f, 1).hurt(damage);
				isDead = true;
			}
			if (checkEntityCollisions(-1, 0f)) {
				getCollidedEntity(-1, 0f).hurt(damage);
				isDead = true;
			}
			if (checkEntityCollisions(0f, -1)) {
				getCollidedEntity(0f, -1).hurt(damage);
				isDead = true;
			}
		}
	}
}
