/**
 * 
 */
package games.byekv1.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class is literally just a rectangle.
 * @author Kyle V Bye
 */
public class Rectangle extends MobileScreenObject {
	
	///
	///	Functions
	///
	@Override
	public void draw(Batch batchIn, float parentAlphaIn) {
		Color rectColor = getColor();
		batchIn.setColor(rectColor.r, rectColor.g, rectColor.b, rectColor.a);
		batchIn.draw(getTextureRegion(), getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation()
				);
	}
	
	///
	///	Constructor
	///
	/**
	 * @param imageIn
	 */
	public Rectangle(float xIn, float yIn, float widthIn, float heightIn, Color colorIn) {
		
		super(null, xIn, yIn, 0f, 0f, 1f, 1f, 0f, false, false);
		
		Texture texture;
		Pixmap pixmap = new Pixmap((int)widthIn, (int)heightIn, Pixmap.Format.RGBA8888);
		pixmap.setColor(colorIn);
		pixmap.fillRectangle(0, 0, (int)widthIn, (int)heightIn);
		texture = new Texture(pixmap);
		
		setTextureRegion(new TextureRegion(texture));
		setWidth(widthIn);
		setHeight(heightIn);
		
	}

}
