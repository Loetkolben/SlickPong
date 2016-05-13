package nix.slickpong;

import java.io.File;
import java.lang.reflect.Field;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainEntrypoint {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	public static void main(String[] args) throws SlickException {
		
		// Detect if we're running from a jar, and than add our lwjgl_natives path to java.library.path
		File path = new File(MainEntrypoint.class.getProtectionDomain().getCodeSource().getLocation().toString().replaceFirst("file:/", ""));
		if(path.toString().endsWith(".jar")){
			System.setProperty("java.library.path",
					path.getParent().toString() + System.getProperty("file.separator") + "lwjgl_natives" + System.getProperty("path.separator") + System.getProperty("java.library.path"));
			// And now, some dirty hacks to force the CL to reevaluate java.library.path
			// From http://blog.cedarsoft.com/2010/11/setting-java-library-path-programmatically/
			try {
				Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
				fieldSysPath.setAccessible(true);
				fieldSysPath.set(null, null);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			System.out.println("System property \'java.library.path\' modified to include LWJGL natives! (You did not delete the \'lwjgl_natives\' folder, did you?)");
		}
		
		// Finally, the actual game!
		SlickPong pong = new SlickPong(WIDTH, HEIGHT);
		AppGameContainer appgc = new AppGameContainer(pong);
		
        appgc.setDisplayMode(WIDTH, HEIGHT, false);
        appgc.setVSync(true);
        appgc.setShowFPS(true);
        
        appgc.start();
	}
	

}
