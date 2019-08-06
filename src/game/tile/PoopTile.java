package game.tile;

import game.gfx.Assets;

public class PoopTile extends Tile {

	public PoopTile(int id) {
		super(Assets.poop, id);
	} 
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
