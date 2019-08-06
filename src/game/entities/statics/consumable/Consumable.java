package game.entities.statics.consumable;

import game.Handler;
import game.entities.statics.StaticEntity;

public abstract class Consumable extends StaticEntity {
	
	public static final int DEFAULT_CONSUMABLE_WIDTH = 25,
							DEFAULT_CONSUMABLE_HEIGHT = 25;

	public Consumable(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		invincible = true;
	}
	
	public abstract void consume(); 

}
