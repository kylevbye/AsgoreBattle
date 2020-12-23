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
		public static final int BATTLESTART = 1;
		public static final int BATTLE = 2;
		public static final int GAMEWIN = 3;
		public static final int GAMEOVER = 4;
		
	}
	
	///
	///	Fields
	///
	
	private static Scene currentScene;
	private static TitlescreenScene titlescreenScene;
	private static BattleStartScene battleStartScene;
	
	///
	///	Getters
	///
	
	public static Scene getCurrentScene() { return currentScene; }
	public static TitlescreenScene getTitlescreenScene() { return titlescreenScene; }
	public static BattleStartScene getBattleStartScene() { return battleStartScene; }
	
	///
	///	Setters
	///
	
	public static void setCurrentScene(Scene currentScene) { SceneManager.currentScene = currentScene; }
	public static void setTitlescreenScene(TitlescreenScene titlescreenScene) { SceneManager.titlescreenScene = titlescreenScene; }
	public static void setBattleStartScene(BattleStartScene battleStartScene) { SceneManager.battleStartScene = battleStartScene; }
	
	///
	///	Functions
	///
	
	public static void loadScene(int sceneconstant) {
		
		switch (sceneconstant) {
		
		case SceneConstants.TITLE:
			titlescreenScene.setup();
			currentScene = titlescreenScene;
			break;
			
		case SceneConstants.BATTLESTART:
			battleStartScene.setup();
			currentScene = battleStartScene;
			break;
			
		}
		
		
	}
	
	public static void loadScenes() {
		
		setTitlescreenScene(new TitlescreenScene());
		setBattleStartScene(new BattleStartScene());
		
	}
	
	public static void setToTitle() { currentScene = titlescreenScene; }
	
	public static void processLogic() { currentScene.processLogic(); }
	
	public static void draw(Batch batch, float alpha) { currentScene.draw(batch, 1f); }
	
	public static void resize(int width, int height) {
		titlescreenScene.resize(width, height);
		battleStartScene.resize(width, height);
	}
	
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
