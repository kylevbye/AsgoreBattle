/**
 * 
 */
package games.byekv1.asgorebattle.scenes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;

/**
 * This interface is for objects that are supposed
 * to act like scenes that can be shown to the screen.
 * 
 * @author	byekv1
 */
public interface Scene extends Disposable {
	
	///
	///	Force Fields
	///
	
	//	Getters
	public Camera getCamera();
	public int getCounter();
	
	//	Setters
	public void setCamera(Camera cam);
	public void setCounter(int counter);
	
	///
	///	Functions
	///
	
	/**
	 * This method should absolutely be called in the constructor
	 * only. Loading of assets should take place here. This method
	 * should load all assets, then it should call <code>setup()</code>
	 * to set up the game logic.
	 */
	public void initalize();
	
	/**
	 * Sets up the game logic to get this scene to work.
	 */
	public void setup();
	
	/**
	 * Process game logic. Follow with <code>draw()</code> in
	 * the main classes's render method after processing.
	 */
	public void processLogic();
	
	/**
	 * Renders the scene to the screen. Separate game logic
	 * from this call.
	 * 
	 * @param	batch	batch to draw entities and sprites on
	 * @param	alpha	alpha (opacity) of the scene.	
	 */
	public void draw(Batch batch, float alpha);
	
	/**
	 * Stops all music and all continuous actions from the scene from playing.
	 */
	public void stop();
	
	/**
	 * Increments the scene frame counter. Call this in the end of
	 * <code>processLogic()</code>.
	 */
	public void incrementCounter();
	
	///
	///	Force Destructors
	///
	
	/**
	 * Cleans up scene and free up memory.
	 */
	@Override
	public void dispose();

}
