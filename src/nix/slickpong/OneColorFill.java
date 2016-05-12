package nix.slickpong;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class OneColorFill implements ShapeFill{
	private Color color;
	
	public OneColorFill(Color color){
		this.color = color;
	}

	@Override
	public Color colorAt(Shape shape, float x, float y) {
		return color;
	}

	@Override
	public Vector2f getOffsetAt(Shape shape, float x, float y) {
		return new Vector2f(0,0);
	}

}
