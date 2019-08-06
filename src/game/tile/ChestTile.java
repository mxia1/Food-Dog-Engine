package game.tile;

import game.gfx.Assets;

public class ChestTile extends Tile{

	public ChestTile(int id) {
		super(Assets.chest, id);
	} 
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
