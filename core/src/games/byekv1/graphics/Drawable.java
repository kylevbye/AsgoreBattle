package games.byekv1.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * This interface describes an object that is able to
 * draw itself.
 * 
 * @author	Kyle V Bye
 * @see	com.badlogic.gdx.graphics.g2d.Batch
 */
public interface Drawable {
	
	/**
	 * Draw onto the provided batch with a provided parentAlpha.
	 * 
	 * @param	batchIn	batch to draw the object on
	 * @param	parentAlpha	alpha to draw at
	 */
	public void draw(Batch batchIn, float parentAlpha);
	
}
