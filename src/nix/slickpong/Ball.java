package nix.slickpong;

import java.util.Random;

import org.newdawn.slick.geom.Circle;

@SuppressWarnings("serial")
public class Ball extends Circle {
	public static int RADIUS = 8;
	private static float DEFAULT_DELTA = 1;
	
	private int centerPointX, centerPointY;
	
	private double deltaX = DEFAULT_DELTA, deltaY = DEFAULT_DELTA;
	
	public Ball(int centerPointX, int centerPointY) {
		super(centerPointX, centerPointY, RADIUS);
		this.centerPointX = centerPointX;
		this.centerPointY = centerPointY;
		recalcStartingDeltas();
	}
	
	public void reset(){
		setX(centerPointX);
		setY(centerPointY);
		recalcStartingDeltas();
	}
	
	private void recalcStartingDeltas(){
		Random r = new Random();
		double direction = (180.0 / Math.PI) * r.nextInt();
		deltaX = Math.cos(direction);
		deltaY = Math.sin(direction);
	}
	
	public double getDeltaX(){
		return deltaX;
	}
	
	public void setDeltaX(double newDeltaX){
		deltaX = newDeltaX;
	}
	
	public double getDeltaY(){
		return deltaY;
	}
	
	public void setDeltaY(double newDeltaY){
		deltaY = newDeltaY;
	}

}
