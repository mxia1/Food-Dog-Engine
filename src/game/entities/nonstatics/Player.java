package game.entities.nonstatics;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Handler;
import game.entities.Entity;
import game.entities.nonstatics.enemies.Enemy;
import game.entities.nonstatics.projectiles.EnemyBullet;
import game.entities.nonstatics.projectiles.PlayerBullet;
import game.entities.nonstatics.projectiles.Projectile;
import game.entities.statics.consumable.Consumable;
import game.gfx.Animation;
import game.gfx.Assets;

public class Player extends NonstaticEntity {
	
	//animation
	private Animation animDown, animLeft, animRight;
	private int lastMove; // 0 = down, 1 = left, 2 = right
	private int lastAttackDirection; // 0 = up, 1 = down, 2 = left, 3 = right
	private int tearSide = 0; // 0 = left, 1 = right
	private int maxHealth;
	             //attack timer
	private long lastAttackTimer, attackCooldown = 100, 
				 attackTimer = attackCooldown,
				 //invincibility timer
				 lastInvincibleTimer, invincibleCooldown = 100,
				 invincibleTimer = invincibleCooldown;
	private BufferedImage head;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH, NonstaticEntity.DEFAULT_CREATURE_HEIGHT);
		tearSide = 0;
		maxHealth = 10;
		health = maxHealth;
		speed = 5;
		
		//animations
		animDown = new Animation(75, Assets.bodyDown);
		animLeft = new Animation(75, Assets.bodyLeft);
		animRight = new Animation(75, Assets.bodyRight);
		
		head = Assets.headDown;
		
		lastMove = 0;
		
		setBounds();
	}
	
	@Override
	protected void setBounds() {
		bounds.x = 24;
		bounds.y = 0;
		bounds.width = 20;
		bounds.height = 50;
	}

	@Override
	public void tick() {
		//animations
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		
		//movements
		getInput();
		move();
		checkCollisionDamage();
		
		//camera
		//handler.getGameCamera().centerOnEntity(this);
		
		//attacks
		checkAttacks();
		checkHeadFrame();
		
	}
	
	@Override
	protected void checkCollisionDamage() {
		if (checkEntityCollisions(1, 0f) || checkEntityCollisions(0f, 1) ||
				checkEntityCollisions(-1, 0f) || checkEntityCollisions(0f, -1)) {
			if (checkEntityCollisions(1, 0f)) {
				Entity e = getCollidedEntity(1, 0f);
				if (e instanceof Enemy) 
					this.hurt(1);
				else if (e instanceof Consumable)
					((Consumable) e).consume();
				else if (e instanceof EnemyBullet) {
					this.hurt(((EnemyBullet) e).getDamage());
					e.kill();
				}
			}
			if (checkEntityCollisions(0f, 1)) {
				Entity e = getCollidedEntity(0f, 1);
				if (e instanceof Enemy) 
					this.hurt(1);
				else if (e instanceof Consumable)
					((Consumable) e).consume();
				else if (e instanceof EnemyBullet) {
					this.hurt(((EnemyBullet) e).getDamage());
					e.kill();
				}
			}
			if (checkEntityCollisions(-1, 0f)) {
				Entity e = getCollidedEntity(-1, 0f);
				if (e instanceof Enemy) 
					this.hurt(1);
				else if (e instanceof Consumable)
					((Consumable) e).consume();
				else if (e instanceof EnemyBullet) {
					this.hurt(((EnemyBullet) e).getDamage());
					e.kill();
				}
			}
			if (checkEntityCollisions(0f, -1)) {
				Entity e = getCollidedEntity(0f, -1);
				if (e instanceof Enemy) 
					this.hurt(1);
				else if (e instanceof Consumable)
					((Consumable) e).consume();
				else if (e instanceof EnemyBullet) {
					this.hurt(((EnemyBullet) e).getDamage());
					e.kill();
				}
			}
		}
	}
	
	private void checkHeadFrame() {
		if (attackTimer < attackCooldown) {
			
			if (lastAttackDirection == 0) head = Assets.headUp;
			if (lastAttackDirection == 1) head = Assets.headDown;
			if (lastAttackDirection == 2) head = Assets.headLeft;
			if (lastAttackDirection == 3) head = Assets.headRight;
			
			return;
		}
		
		else if (xMove == 0 && yMove == 0)
			head = Assets.headDown;
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if (attackTimer < attackCooldown) return;
		
		ArrayList <Entity> list = handler.getWorld().getEntityManager().getEntities();
		
		// check shooting
		if (handler.getKeyManager().upArrow) {
			lastAttackDirection = 0;
			head = Assets.headUpShoot;
			if (tearSide == 0)
				list.add(new PlayerBullet(handler, x - 10, y - 10, NonstaticEntity.DEFAULT_CREATURE_WIDTH, // shoot up
					NonstaticEntity.DEFAULT_CREATURE_HEIGHT, 0, this));
			else
				list.add(new PlayerBullet(handler, x + 15, y - 10, NonstaticEntity.DEFAULT_CREATURE_WIDTH,
						NonstaticEntity.DEFAULT_CREATURE_HEIGHT, 0, this));
		} else if (handler.getKeyManager().downArrow) {
			lastAttackDirection = 1;
			head = Assets.headDownShoot;
			if (tearSide == 0)
				list.add(new PlayerBullet(handler, x - 10, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH, // shoot down
					NonstaticEntity.DEFAULT_CREATURE_HEIGHT, 1, this));
			else
				list.add(new PlayerBullet(handler, x + 15, y, NonstaticEntity.DEFAULT_CREATURE_WIDTH,  
						NonstaticEntity.DEFAULT_CREATURE_HEIGHT, 1, this));
		} else	if (handler.getKeyManager().leftArrow) {
			lastAttackDirection = 2;
			head = Assets.headLeftShoot;
			if (tearSide == 0)
				list.add(new PlayerBullet(handler, x - 10, y - 15, NonstaticEntity.DEFAULT_CREATURE_WIDTH, // shoot left
					NonstaticEntity.DEFAULT_CREATURE_HEIGHT, 2, this));
			else
				list.add(new PlayerBullet(handler, x - 10, y - 20, NonstaticEntity.DEFAULT_CREATURE_WIDTH, 
						NonstaticEntity.DEFAULT_CREATURE_HEIGHT, 2, this));
		} else if (handler.getKeyManager().rightArrow) {
			lastAttackDirection = 3;
			head = Assets.headRightShoot;
			if (tearSide == 0)
				list.add(new PlayerBullet(handler, x + 10, y - 15, NonstaticEntity.DEFAULT_CREATURE_WIDTH, // shoot right
					NonstaticEntity.DEFAULT_CREATURE_HEIGHT, 3, this));
			else
				list.add(new PlayerBullet(handler, x + 10, y - 20, NonstaticEntity.DEFAULT_CREATURE_WIDTH,
						NonstaticEntity.DEFAULT_CREATURE_HEIGHT, 3, this));
		} else {
			return;
		} 
		
		tearSide++;
		tearSide %= 2;
		
		attackTimer = 0;
	}

	@Override
	public void die() {
		System.out.println("You lose!");
	}
	
	@Override
	public boolean entityCheck(Entity e) {
		return (e.equals(this) ||  !e.isSolid() ||
			   (e instanceof Projectile && ((Projectile) e).getOrigin().equals(this)) || 
				e.isDead());
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) {
			yMove = -speed;
			head = Assets.headUp;
		}
		if(handler.getKeyManager().down) {
			yMove = speed;
			head = Assets.headDown;
		}
		if(handler.getKeyManager().left) {
			xMove = -speed;
			head = Assets.headLeft;
		}
		if(handler.getKeyManager().right) {
			xMove = speed;
			head = Assets.headRight;
		}
	}
	
	@Override
	public void render(Graphics g) { 
		castShadow(g);
		
		if (hitTimer > 0) {
			invincible = true;
			renderInvincible(g);
			hitTimer--;
		}
		else {
			invincible = false;
			g.drawImage(getCurrentAnimationFrame(), (int) x, 
					(int) y, width, height, null);
			g.drawImage(head, (int) x, 
					(int) y - 20, width, height, null); 
		}
		
		//drawBounds(g);
	}
	
	@Override
	protected void castShadow(Graphics g) {
		float opacity = 0.2f;
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(Assets.shadow, (int) x + 3, (int) y + 17, width, height, null);
		
		opacity = 1.0f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}
	
	int i = 0;
	
	public void renderInvincible(Graphics g) {
		invincibleTimer += System.currentTimeMillis() - lastInvincibleTimer;
		lastInvincibleTimer = System.currentTimeMillis();

		if (invincibleTimer > invincibleCooldown) {
			i++;	
		} else {
			if (i % 2 == 0) {
				g.drawImage(getCurrentAnimationFrame(), (int) x, 
						(int) y, width, height, null);
				g.drawImage(head, (int) x, 
						(int) y - 20, width, height, null); 
			} else {
				float opacity = 0.1f;
				Graphics2D g2d = (Graphics2D) g;
				
				g.drawImage(getCurrentAnimationFrame(), (int) x, 
						(int) y, width, height, null);
				g.drawImage(head, (int) x, 
						(int) y - 20, width, height, null); 
				
				opacity = 1.0f;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			}
			return;
		}
		
		invincibleTimer = 0;
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) { 
			lastMove = 1;
			return animLeft.getCurrentFrame();
		}
		else if (xMove > 0) {
			lastMove = 2;
			return animRight.getCurrentFrame();
		}
		else if (yMove < 0 || yMove > 0) {
			lastMove = 0;
			return animDown.getCurrentFrame();
		}
		else {
			if (lastMove == 0) return Assets.bodyDown[0];
			else if (lastMove == 1) return Assets.bodyLeft[0];
			else return Assets.bodyRight[0];
		}
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
}
