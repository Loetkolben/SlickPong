package nix.slickpong;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainEntrypoint {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	public static void main(String[] args) throws SlickException{
		SlickPong pong = new SlickPong(WIDTH, HEIGHT);
		AppGameContainer appgc = new AppGameContainer(pong);
        appgc.setDisplayMode(WIDTH, HEIGHT, false);
        appgc.setVSync(true);
        appgc.setShowFPS(true);
        appgc.start();
	}

}
