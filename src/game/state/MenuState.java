package game.state;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.ui.ClickListener;
import game.ui.UIManager;
import game.ui.UIStartButton;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIStartButton(handler.getWidth() / 2 - 100, handler.getHeight() / 2 - 50, 200, 100, Assets.startbutton, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
		
		//dev purpose skips menu screen
		handler.getMouseManager().setUIManager(null);
		State.setState(handler.getGame().gameState);

	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
}
