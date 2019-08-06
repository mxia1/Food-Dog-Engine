package game.backgrounds;

import java.awt.geom.AffineTransform;

public class Background {
	
	protected AffineTransform at;
	protected float x, y;
	protected double scale, xScale, yScale;
	protected int width, height;
	
	public Background(float x, float y, double scale, double xScale, double yScale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.xScale = xScale;
		this.yScale = yScale;
		
		at = AffineTransform.getTranslateInstance(x, y);

	}
	
	public double getXScale() {
		return xScale;
	}
	
	public double getYScale() {
		return yScale;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		at = AffineTransform.getTranslateInstance(x, y);
		setScale(xScale, yScale);
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		at = AffineTransform.getTranslateInstance(x, y);
		setScale(xScale, yScale);
	}
	
	public void setScale(double multiplier) {
		this.scale = multiplier;
		at.scale(multiplier, multiplier);
	}
	
	public void setScale(double multiplier, double multiplier2) {
		at.scale(multiplier, multiplier2);
	}

	public AffineTransform getTransform() {
		return at;
	}
}
