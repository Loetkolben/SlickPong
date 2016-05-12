package nix.slickpong;

import org.newdawn.slick.geom.Rectangle;

@SuppressWarnings("serial")
public class Slider extends Rectangle {
	public static final int SLIDER_WIDTH = 10;
	public static final int SLIDER_HEIGHT = 75;
	private static final int SPEED = 5;
	
	private int  startX, startY;
	private int sliderYMax = -1, sliderYMin = -1;
	private int score = 0;

	public Slider(int startX, int startY, int sliderYMax, int sliderYMin) {
		super(startX, startY, SLIDER_WIDTH, SLIDER_HEIGHT);
		this.startX = startX;
		this.startY = startY;
		this.sliderYMax = sliderYMax;
		this.sliderYMin = sliderYMin;
	}
	
	public Slider(int startX, int startY, int sliderYMax) {
		this(startX, startY, sliderYMax, 0);
	}
	
	public void reset(){
		setX(startX);
		setY(startY);
	}
	
	public int getScore(){
		return score;
	}
	
	public void increaseScore(){
		score++;
	}

	
	public void moveUp(){
		if ((getY() - SPEED) > sliderYMin) setY(getY() - SPEED);
	}
	
	public void moveDown(){
		if ((getY() + SPEED) < sliderYMax) setY(getY() + SPEED);
	}

}
