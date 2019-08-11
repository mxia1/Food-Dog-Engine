package game.entities.nonstatics.enemies;

import game.Handler;
import game.entities.Entity;
import game.entities.nonstatics.NonstaticEntity;
import game.entities.nonstatics.Player;
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
		if (checkEntityCollisions(1, 0f)) {
			Entity entity = getCollidedEntity(1, 0f);
			playerBulletCollisionCheck(entity);
			playerCollisionCheck(entity);
		}
		if (checkEntityCollisions(0f, 1)) {
			Entity entity = getCollidedEntity(0f, 1);
			playerBulletCollisionCheck(entity);
			playerCollisionCheck(entity);
		}
		if (checkEntityCollisions(-1, 0f)) {
			Entity entity = getCollidedEntity(-1, 0f);
			playerBulletCollisionCheck(entity);
			playerCollisionCheck(entity);
		}
		if (checkEntityCollisions(0f, -1)) {
			Entity entity = getCollidedEntity(0f, -1);
			playerBulletCollisionCheck(entity);
			playerCollisionCheck(entity);
		}
	}


	private void playerBulletCollisionCheck(Entity entity) {
		if (entity instanceof PlayerBullet) {
			this.hurt(((PlayerBullet) entity).getDamage());
			entity.kill();
		}
	}

	private void playerCollisionCheck(Entity entity) {
		if (entity instanceof Player) {
			entity.hurt(1);
		}
	}

}
