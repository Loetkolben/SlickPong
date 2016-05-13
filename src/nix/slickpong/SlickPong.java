package nix.slickpong;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SlickPong extends BasicGame{
	private int windowWidth, windowHeight;
	
	private Slider sliderLeft, sliderRight;
	private Ball ball;
	private GameState state;

	public SlickPong(int windowWidth, int windowHeight) {
		super("SlickPong");
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		sliderLeft = new Slider(10,
				windowHeight / 2 - Slider.SLIDER_HEIGHT / 2,
				windowHeight - Slider.SLIDER_HEIGHT);
		sliderRight = new Slider(windowWidth - 10 - Slider.SLIDER_WIDTH,
				windowHeight / 2 - Slider.SLIDER_HEIGHT / 2,
				windowHeight - Slider.SLIDER_HEIGHT);
		ball = new Ball(windowWidth / 2, windowHeight / 2);
		
		state = GameState.NOT_RUNNING;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// "World Geometry" (draw ball only if RUNNING, otherwise it's hard to read the text...)
		g.fill(sliderLeft, new OneColorFill(Color.white));
		g.fill(sliderRight, new OneColorFill(Color.white));
		if(state == GameState.RUNNING) g.fill(ball, new OneColorFill(Color.white));
		
		// Score
		String scoreString = sliderLeft.getScore() + "       " + sliderRight.getScore();
		g.getFont().drawString((windowWidth - g.getFont().getWidth(scoreString)) / 2,
				10, scoreString, Color.white);
		
		// "Press Enter" msgs
		if(state == GameState.NOT_RUNNING){
			String msg = "[ PRESS ENTER TO START ]";
			g.getFont().drawString((windowWidth - g.getFont().getWidth(msg)) / 2,
					(windowHeight - g.getFont().getHeight(msg)) / 2 , msg, Color.white);
		}
		
		if(state == GameState.LEFT_WON){
			String msg = "[ LEFT WON - ENTER FOR NEW ROUND ]";
			g.getFont().drawString((windowWidth - g.getFont().getWidth(msg)) / 2,
					(windowHeight - g.getFont().getHeight(msg)) / 2 , msg, Color.white);
		}
		
		if(state == GameState.RIGHT_WON){
			String msg = "[ RIGHT WON - ENTER FOR NEW ROUND ]";
			g.getFont().drawString((windowWidth - g.getFont().getWidth(msg)) / 2,
					(windowHeight - g.getFont().getHeight(msg)) / 2 , msg, Color.white);
		}
	}
	
	// FIXME: Limit the "update method runs"/second to have a consistent experience (VSync off -> pretty fast game)
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(state == GameState.NOT_RUNNING || state == GameState.LEFT_WON || state == GameState.RIGHT_WON){
			if(input.isKeyDown(Input.KEY_ENTER)){
				ball.reset();
				sliderLeft.reset();
				sliderRight.reset();
				
				state = GameState.RUNNING;
			}
		}
		
		if(state == GameState.RUNNING) {
			// Left Player Controls
			if(input.isKeyDown(Input.KEY_W)) sliderLeft.moveUp();
			if(input.isKeyDown(Input.KEY_S)) sliderLeft.moveDown();
			
			// Right Player Controls
			if(input.isKeyDown(Input.KEY_UP)) sliderRight.moveUp();
			if(input.isKeyDown(Input.KEY_DOWN)) sliderRight.moveDown();
			
			// Ball Movement: Collision
			// FIXME: Only collide once with one "border" (to prevent the ball from clipping in the Sliders)
			/*
			if(ball.getX() + ball.getDeltaX() < 0){
				// Collision Left
				ball.setDeltaX(-ball.getDeltaX());
			}
			if(ball.getX() + ball.RADIUS * 2 + ball.getDeltaX() > windowWidth){
				// Collision Right
				ball.setDeltaX(-ball.getDeltaX());
			}
			*/
			if(ball.getY() + ball.getDeltaY() < 0){
				// Collision Top
				ball.setDeltaY(-ball.getDeltaY());
			}
			if(ball.getY() + Ball.RADIUS *2 + ball.getDeltaY() > windowHeight){
				// Collision Bottom
				ball.setDeltaY(-ball.getDeltaY());
			}
			
			// Ball Movement: Actual Movement
			ball.setX((float) (ball.getX() + ball.getDeltaX()));
			ball.setY((float) (ball.getY() + ball.getDeltaY()));
			
			// Ball Movement: Slider Collision
			if(ball.intersects(sliderLeft) || ball.intersects(sliderRight)){
				ball.setDeltaX(-ball.getDeltaX());
			}
			
			// Ball Movement: Scoring
			if(ball.getX() + ball.getDeltaX() < 0){
				// Collision Left -> Score for Right
				sliderRight.increaseScore();
				state = GameState.RIGHT_WON;
			}
			if(ball.getX() + Ball.RADIUS * 2 + ball.getDeltaX() > windowWidth){
				// Collision Right -> Score for Left
				sliderLeft.increaseScore();
				state = GameState.LEFT_WON;
			}
		}
	}
}
