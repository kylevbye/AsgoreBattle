/**
 * @author	byekv1
 */
package games.byekv1.asgorebattle.scenes;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author byekv1
 *
 */
public class SceneManager {
	
	///
	///	Scene Constants
	///
	
	public final class SceneConstants {
		
		public static final int TITLE = 0;
		public static final int BATTLE = 1;
		public static final int GAMEWIN = 2;
		public static final int GAMEOVER = 3;
		
	}
	
	///
	///	Fields
	///
	
	private static Scene currentScene;
	private static TitlescreenScene titlescreenScene;
	
	///
	///	Getters
	///
	
	public static Scene getCurrentScene() { return currentScene; }
	public static TitlescreenScene getTitlescreenScene() { return titlescreenScene; }
	
	///
	///	Setters
	///
	
	public static void setCurrentScene(Scene currentScene) { SceneManager.currentScene = currentScene; }
	public static void setTitlescreenScene(TitlescreenScene titlescreenScene) { SceneManager.titlescreenScene = titlescreenScene; }
	
	///
	///	Functions
	///
	
	public static void loadScenes() {
		
		setTitlescreenScene(new TitlescreenScene());
		
	}
	
	public static void setToTitle() { currentScene = titlescreenScene; }
	
	public static void processLogic() { currentScene.processLogic(); }
	
	public static void draw(Batch batch, float alpha) { currentScene.draw(batch, 1f); }
	
	///
	///	Constructors
	///

	private SceneManager() {}
	
	///
	///	Destructors
	///
	
	public static void dispose() {
		titlescreenScene.dispose();
	}

}
