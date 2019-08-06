package game.entities.nonstatics.enemies;

import game.Handler;
import game.entities.Entity;
import game.entities.nonstatics.NonstaticEntity;
import game.entities.nonstatics.projectiles.EnemyBullet;
import game.entities.nonstatics.projectiles.PlayerBullet;
import game.entities.statics.consumable.Consumable;

public abstract class Enemy extends NonstaticEntity{

	public Enemy(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	@Override
	public boolean entityCheck(Entity e) {
		return (e.equals(this) || e instanceof EnemyBullet || e.isDead() || e instanceof Consumable || !e.isSolid());
	}
	
	@Override
	protected void checkCollisionDamage() {
		if (checkEntityCollisions(1, 0f) || checkEntityCollisions(0f, 1) ||
				checkEntityCollisions(-1, 0f) || checkEntityCollisions(0f, -1)) {
			if (checkEntityCollisions(1, 0f)) {
				Entity e = getCollidedEntity(1, 0f);
				if (e instanceof PlayerBullet) {
					this.hurt(((PlayerBullet) e).getDamage());
					e.kill();
				}
			}
			if (checkEntityCollisions(0f, 1)) {
				Entity e = getCollidedEntity(0f, 1);
				if (e instanceof PlayerBullet) {
					this.hurt(((PlayerBullet) e).getDamage());
					e.kill();
				}
			}
			if (checkEntityCollisions(-1, 0f)) {
				Entity e = getCollidedEntity(-1, 0f);
				if (e instanceof PlayerBullet) {
					this.hurt(((PlayerBullet) e).getDamage());
					e.kill();
				}
			}
			if (checkEntityCollisions(0f, -1)) {
				Entity e = getCollidedEntity(0f, -1);
				if (e instanceof PlayerBullet) {
					this.hurt(((PlayerBullet) e).getDamage());
					e.kill();
				}
			}
		}
	}

}
