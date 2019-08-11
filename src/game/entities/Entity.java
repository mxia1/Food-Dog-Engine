package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import game.Handler;

public abstract class Entity {

	protected Handler handler;
	protected float x, y;
	protected int width, height, health;
	protected Rectangle bounds;
	protected boolean active = true;
	protected boolean invincible = false;
	protected boolean isDead = false;
	protected boolean isSolid;
	protected int hitTimer, maxHitTimer;
	
	public static final int DEFAULT_HEALTH = 20,
							DEFAULT_MAX_HIT_TIMER = 50;
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		health = DEFAULT_HEALTH;
		maxHitTimer = DEFAULT_MAX_HIT_TIMER;
		hitTimer = 0;
		isSolid = true;
		
		bounds = new Rectangle(0, 0, width, height);
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	protected abstract void setBounds();
	
	protected abstract void castShadow(Graphics g);
	
	public void hurt(int amt) {
		if (!invincible) {
			health -= amt;
			if (health <= 0) {
				setActive(false);
				die();
			} else hitTimer = maxHitTimer;
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(entityCheck(e))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	public Entity getCollidedEntity(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(entityCheck(e))
				continue; 
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return e;
		}
		return null;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public boolean entityCheck(Entity e) {
		return (e.equals(this) || e.isDead());
	}
	
	public void drawBounds(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x), 
				(int) (y + bounds.y), 
				bounds.width, bounds.height);
	}
	
	public void kill() {
		isDead = true;
	}
	
	// getters and setters
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Rectangle getBound() {
		return bounds;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
