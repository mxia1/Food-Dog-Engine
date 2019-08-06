package game.entities.nonstatics;

import game.Handler;
import game.entities.Entity;
import game.tile.Tile;

public abstract class NonstaticEntity extends Entity {
	
	public static final float DEFAULT_SPEED = 15.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed;
	protected float xMove, yMove;
	
	protected abstract void checkCollisionDamage();

	public NonstaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
		if(!checkEntityCollisions(xMove, 0f))
			moveX();
		else {
			while (checkEntityCollisions(xMove, 0f) && xMove != 0) {
				if (xMove <= .5 && xMove >= -.5) 
					xMove = 0;
				else if (xMove > 0)
					xMove -= .5;
				else if (xMove < 0)
					xMove += .5;
			}
			moveX();
		}
		
		if(!checkEntityCollisions(0f, yMove))
			moveY();
		else {
			while (checkEntityCollisions(0f, yMove) && yMove != 0) {
				if (yMove <= .5 && yMove >= -.5)
					yMove = 0;
				else if (yMove > 0)
					yMove -= .5;
				else if (yMove < 0)
					yMove += .5;
			}
			moveY();
		}
	}
	
	public void moveX(){
		if (xMove > 0) {
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && 
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
		}
		else if (xMove < 0) {
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && 
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}
	
	public void moveY(){
		if (yMove > 0) {
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile ((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
		else if (yMove < 0) {
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile ((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//GETTERS SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}

