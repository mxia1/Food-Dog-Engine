package game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static BufferedImage headDown, headRight, headUp, headLeft, poop, trapdoor, chest, 
								background, empty, emptyCollision, scolex, headDownShoot, headRightShoot, 
								headUpShoot, headLeftShoot, shadow, heart; 
	public static BufferedImage[] moly, bodyDown, bodyLeft, bodyRight, eggdog, eggdogHurt, startbutton, peaTear, 
								  tear, peadog, peadogShoot, playerHealth;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/boiS.png"));
		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/util.png"));
		SpriteSheet eggdogSheet = new SpriteSheet(ImageLoader.loadImage("/textures/eggdog.png"));
		SpriteSheet eggdogHurtSheet = new SpriteSheet(ImageLoader.loadImage("/textures/eggdog_hurt.png"));
		SpriteSheet startButtonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/startbuttons.png"));
		SpriteSheet tearSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_ammo.png"));
		SpriteSheet peadogSheet = new SpriteSheet(ImageLoader.loadImage("/textures/peadog.png"));
		SpriteSheet peadogShootSheet = new SpriteSheet(ImageLoader.loadImage("/textures/peadog_shoot.png"));
		SpriteSheet peaTearSheet = new SpriteSheet(ImageLoader.loadImage("/textures/pea_ammo.png"));
		SpriteSheet playerHealthSheet = new SpriteSheet(ImageLoader.loadImage("/textures/heart.png"));
		SpriteSheet molySheet = new SpriteSheet(ImageLoader.loadImage("/textures/moly_idle.png"));
		
		shadow = ImageLoader.loadImage("/textures/ammo_shadow.png");
		
		headDown = sheet.crop(width, 0, width, height);
		headRight = sheet.crop(width * 3, 0, width, height);
		headUp = sheet.crop(width * 5, 0, width, height);
		headLeft = sheet.crop(width * 4 + 8, height * 3 + 3, width, height);
		
		headDownShoot = sheet.crop(width * 2, 0, width, height);
		headRightShoot = sheet.crop(width * 4, 0, width, height);
		headUpShoot = sheet.crop(width * 6, 0, width, height);
		headLeftShoot = sheet.crop(width * 3 + 8, height * 3 + 3, width, height);
		
		moly = new BufferedImage[4];
		for (int i = 0; i < moly.length; i++) {
			moly[i] = molySheet.crop(i * 96, 0, 96, 96);
		}
		
		playerHealth = new BufferedImage[2];
		for (int i = 0; i < playerHealth.length; i++) {
			playerHealth[i] = playerHealthSheet.crop(width * i, 0, width, height);
		}
		
		peadog = new BufferedImage[8];
		for (int i = 0; i < peadog.length; i++) {
			peadog[i] = peadogSheet.crop(width * i, 0, width, height);
		}
		
		peadogShoot = new BufferedImage[4];
		for (int i = 0; i < peadogShoot.length; i++) {
			peadogShoot[i] = peadogShootSheet.crop(width * i, 0, width, height);
		}
		
		peaTear = new BufferedImage[6];
		for (int i = 0; i < peaTear.length; i++) {
			peaTear[i] = peaTearSheet.crop(width * i, 0, width, height);
		}
		
		bodyDown = new BufferedImage[10];
		bodyDown[8] = sheet.crop(width * 7, 0, width, height);
		bodyDown[9] = sheet.crop(width * 8, 0, width, height);
		bodyDown[0] = sheet.crop(width, height, width, height);
		bodyDown[1] = sheet.crop(width * 2, height, width, height);
		bodyDown[2] = sheet.crop(width * 3, height, width, height);
		bodyDown[3] = sheet.crop(width * 4, height, width, height);
		bodyDown[4] = sheet.crop(width * 5, height, width, height);
		bodyDown[5] = sheet.crop(width * 6, height, width, height);
		bodyDown[6] = sheet.crop(width * 7, height, width, height);
		bodyDown[7] = sheet.crop(width * 8, height, width, height);
		
		bodyRight = new BufferedImage[10];
		bodyRight[0] = sheet.crop(width, height * 2, width, height);
		bodyRight[1] = sheet.crop(width * 2, height * 2, width, height);
		bodyRight[2] = sheet.crop(width * 3, height * 2, width, height);
		bodyRight[3] = sheet.crop(width * 4, height * 2, width, height);
		bodyRight[4] = sheet.crop(width * 5, height * 2, width, height);
		bodyRight[5] = sheet.crop(width * 6, height * 2, width, height);
		bodyRight[6] = sheet.crop(width * 7, height * 2, width, height);
		bodyRight[7] = sheet.crop(width * 8, height * 2, width, height);
		bodyRight[8] = sheet.crop(width, height * 3, width, height);
		bodyRight[9] = sheet.crop(width * 2, height * 3, width, height);

		bodyLeft = new BufferedImage[10];
		bodyLeft[7] = sheet.crop(width, height * 4 - 3, width, height);
		bodyLeft[6] = sheet.crop(width * 2, height * 4 - 3, width, height);
		bodyLeft[5] = sheet.crop(width * 3, height * 4 - 3, width, height);
		bodyLeft[4] = sheet.crop(width * 4, height * 4 - 3, width, height);
		bodyLeft[3] = sheet.crop(width * 5, height * 4 - 3, width, height);
		bodyLeft[2] = sheet.crop(width * 6, height * 4 - 3, width, height);
		bodyLeft[1] = sheet.crop(width * 7, height * 4 - 3, width, height);
		bodyLeft[0] = sheet.crop(width * 8, height * 4 - 3, width, height);
		bodyLeft[9] = sheet.crop(width * 7, height * 5 - 3, width, height);
		bodyLeft[8] = sheet.crop(width * 8, height * 5 - 3, width, height);
		
		eggdog = new BufferedImage[10];
		eggdog[0] = eggdogSheet.crop(0, 0, width, height);
		eggdog[1] = eggdogSheet.crop(width, 0, width, height);
		eggdog[2] = eggdogSheet.crop(width * 2, 0, width, height);
		eggdog[3] = eggdogSheet.crop(0, height, width, height);
		eggdog[4] = eggdogSheet.crop(width, height, width, height);
		eggdog[5] = eggdogSheet.crop(width * 2, height, width, height);
		eggdog[6] = eggdogSheet.crop(0, height * 2, width, height);
		eggdog[7] = eggdogSheet.crop(width, height * 2, width, height);
		eggdog[8] = eggdogSheet.crop(width * 2, height * 2, width, height);
		eggdog[9] = eggdogSheet.crop(0, height * 3, width, height);
		
		eggdogHurt = new BufferedImage[10];
		eggdogHurt[0] = eggdogHurtSheet.crop(0, 0, width, height);
		eggdogHurt[1] = eggdogHurtSheet.crop(width, 0, width, height);
		eggdogHurt[2] = eggdogHurtSheet.crop(width * 2, 0, width, height);
		eggdogHurt[3] = eggdogHurtSheet.crop(0, height, width, height);
		eggdogHurt[4] = eggdogHurtSheet.crop(width, height, width, height);
		eggdogHurt[5] = eggdogHurtSheet.crop(width * 2, height, width, height);
		eggdogHurt[6] = eggdogHurtSheet.crop(0, height * 2, width, height);
		eggdogHurt[7] = eggdogHurtSheet.crop(width, height * 2, width, height);
		eggdogHurt[8] = eggdogHurtSheet.crop(width * 2, height * 2, width, height);
		eggdogHurt[9] = eggdogHurtSheet.crop(0, height * 3, width, height);
		
		tear = new BufferedImage[6];
		for (int i = 0; i < tear.length; i++) {
			tear[i] = tearSheet.crop(width * i, 0, width, height);
		}
			
		poop = sheet2.crop(874, 398, 35, 40);
		trapdoor = sheet2.crop(966, 356, 40, 40);
		chest = sheet2.crop(1010, 400, 36, 36);
		empty = sheet.crop(0, 0, 1, 1);
		emptyCollision = sheet2.crop(12, 64, 1, 1);
		scolex = sheet2.crop(366, 293, 50, 50);
		heart = sheet2.crop(377, 95, 24, 18);

		startbutton = new BufferedImage[2];
		startbutton[0] = startButtonSheet.crop(0, 0, 30, 9);
		startbutton[1] = startButtonSheet.crop(30, 0, 30, 9);
		
		background = ImageLoader.loadImage("/textures/empt.png");
	}
}
