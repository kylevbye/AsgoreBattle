/**
 * @author	byekv1
 */
package games.byekv1.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author byekv
 *
 */
public class TwoSidedImage extends Actor {
	
	///
	///	Fields
	///
	
	private boolean isFlipped;
	private Image frontImage;
	private Image backImage;
	
	///
	///	Getters
	///
	
	public boolean isFlipped() { return isFlipped; }
	public Image getFrontImage() { return frontImage; }
	public Image getBackImage() { return backImage; }
	
	///
	///	Setters
	///
	
	public void setFlipped(boolean isFlipped) { this.isFlipped = isFlipped; }
	public void setFrontImage(Image frontImage) { this.frontImage = frontImage; }
	public void setBackImage(Image backImage) { this.backImage = backImage; }
	
	///
	///	Functions
	///
	
	public void flip() { isFlipped = !isFlipped; }
	
	///
	///	Actor Functions
	///
	
	@Override 
	protected void positionChanged() {
		
		frontImage.setPosition(getX(), getY());
		backImage.setPosition(getX(), getY());
		
	}
	
	@Override
	protected void sizeChanged() {
		
		frontImage.setSize(getWidth(), getHeight());
		backImage.setSize(getWidth(), getHeight());
		
	}
	
	@Override 
	protected void scaleChanged() {
		
		frontImage.setScale(getScaleX(), getScaleY());
		backImage.setScale(getScaleX(), getScaleY());
		
	}
	
	@Override
	protected void rotationChanged() {
		
		frontImage.setRotation(getRotation());
		backImage.setRotation(getRotation());
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		Image selectedImage;
		
		if (!isFlipped) selectedImage = frontImage;
		else selectedImage = backImage;
		
		batch.enableBlending();
		Color color = selectedImage.getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		selectedImage.draw(batch, parentAlpha);
		
	}
	
	///
	///	Constructor
	///

	/**
	 * Initializes this instance's two sided Image with the
	 * provided Images. This instance's attributes (x,y,scaleX, etc.)
	 * will be based on <code>frontSide</code>'s attributes if
	 * <code>takeFrontAttributes<c/ode> is true. Otherwise, 
	 * <code>backSide</code>'s attributes will be used.
	 */
	public TwoSidedImage(Image frontSide, Image backSide, boolean takeFrontAttributes) {
	
		super();
		
		setFlipped(false);
		
		setFrontImage(frontSide);
		setBackImage(backSide);
		
		Image primarySide;
		if (takeFrontAttributes) primarySide = frontSide;
		else primarySide = backSide;
		
		setPosition(primarySide.getX(), primarySide.getY());
		setSize(primarySide.getWidth(), primarySide.getHeight());
		setScale(primarySide.getScaleX(), primarySide.getScaleY());
		setRotation(primarySide.getRotation());
		
	}

}
