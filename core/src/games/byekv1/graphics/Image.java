package games.byekv1.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * This class possesses many attributes from <code>Actor</code>.
 * It is an object that holds a <code>TextureRegion</code> that
 * can be drawn.
 * 
 * @author	Kyle V Bye
 * @see	TextureRegion
 * @see	Actor
 * @see	Disposable
 * @see	Drawable
 *
 */
public class Image extends Actor implements Disposable, Drawable {
	
	///
	///	Fields
	///
	
	private TextureRegion textureRegion;
	
	///
	///	Getters
	///
	
	public TextureRegion getTextureRegion() { return textureRegion; }
	
	///
	///	Setters
	///
	
	public void setTextureRegion(TextureRegion textureRegionIn) { textureRegion = textureRegionIn; }
	
	///
	///	Functions
	///
	
	public float findCenterX() { return getX() + getWidth()/2f; }
	
	public float findCenterY() { return getY() + getHeight()/2f; }
	
	public void draw(Batch batchIn, float parentAlphaIn) {
		batchIn.enableBlending();
		Color oldColor = batchIn.getColor();
		Color color = getColor();
		batchIn.setColor(color.r, color.g, color.b, color.a * parentAlphaIn);
		batchIn.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		batchIn.setColor(oldColor);
	}
	
	///
	///	toString
	///
	
	@Override
	public String toString() {
		
		String textureString = "null";
		if (textureRegion != null) textureString = textureRegion.toString();
		
		return String.format("Image:[Texture:%s, X:%.2f, Y:%.2f, OriginX:%.2f, OriginY:%.2f, ScaleX:%.2f, ScaleY:%.2f, Width:%.2f, Height:%.2f, Rotation:%.2f", 
				textureString, getX(), getY(), getOriginX(), getOriginY(), getScaleX(), getScaleY(), getWidth(), getHeight(), getRotation()
				);
		
	}
	
	///
	///	Constructor
	///
	
	public Image() {
		this(null, 0, 0, 0, 0, 0, 0, 0);
	}
	
	public Image(Texture textureIn) {
		this(textureIn, 0, 0, 0, 0, 0, 0 , 0);
	}
	
	public Image(Texture textureIn, float xIn, float yIn, float originXIn, float originYIn, float scaleXIn, float scaleYIn, float rotationAngleIn) {
		super();
		if (textureIn != null) {
			setTextureRegion(new TextureRegion(textureIn));
			setWidth(textureIn.getWidth());
			setHeight(textureIn.getHeight());
		}
		else {
			textureRegion = null;
			setWidth(0f);
			setHeight(0f);
		}
		setOrigin(originXIn, originYIn);
		setX(xIn-originXIn); setY(yIn-originYIn);
		setScale(scaleXIn, scaleYIn);
		setRotation(rotationAngleIn);
	}
	
	///
	///	Destructor
	///
	
	@Override
	public void dispose() {
		
		//	Clean
		textureRegion.getTexture().dispose();
		
		//	Nullify
		textureRegion = null;
		
	}
	
	
}
