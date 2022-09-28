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
		public static final int BATTLEINTRO = 2;
		public static final int BATTLE = 3;
		public static final int GAMEWIN = 4;
		public static final int GAMEOVER = 5;
		
	}
	
	///
	///	Fields
	///
	
	private static Scene currentScene;
	private static TitlescreenScene titlescreenScene;
	private static BattleStartScene battleStartScene;
	private static BattleIntroScene battleIntroScene;
	private static BattleScene battleScene;
	
	///
	///	Getters
	///
	
	public static Scene getCurrentScene() { return currentScene; }
	public static TitlescreenScene getTitlescreenScene() { return titlescreenScene; }
	public static BattleStartScene getBattleStartScene() { return battleStartScene; }
	public static BattleIntroScene getBattleIntroScene() { return battleIntroScene; }
	public static BattleScene getBattleScene() { return battleScene; }
	
	///
	///	Setters
	///
	
	public static void setCurrentScene(Scene currentScene) { SceneManager.currentScene = currentScene; }
	public static void setTitlescreenScene(TitlescreenScene titlescreenScene) { SceneManager.titlescreenScene = titlescreenScene; }
	public static void setBattleStartScene(BattleStartScene battleStartScene) { SceneManager.battleStartScene = battleStartScene; }
	public static void setBattleIntroScene(BattleIntroScene battleIntroScene) { SceneManager.battleIntroScene = battleIntroScene; }
	public static void setBattleScene(BattleScene battleScene) { SceneManager.battleScene = battleScene; }
	
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
			
		case SceneConstants.BATTLEINTRO:
			battleIntroScene.setup();
			currentScene = battleIntroScene;
			break;
			
		case SceneConstants.BATTLE:
			battleScene.setup();
			currentScene = battleScene;
			break;
			
		}
		
		
	}
	
	public static void loadScenes() {
		
		setTitlescreenScene(new TitlescreenScene());
		setBattleStartScene(new BattleStartScene());
		setBattleIntroScene(new BattleIntroScene());
		setBattleScene(new BattleScene());
		
	}
	
	public static void setToTitle() { currentScene = titlescreenScene; }
	
	public static void processLogic() { currentScene.processLogic(); }
	
	public static void draw(Batch batch, float alpha) { currentScene.draw(batch, 1f); }
	
	public static void resize(int width, int height) {
		titlescreenScene.resize(width, height);
		battleStartScene.resize(width, height);
		battleIntroScene.resize(width, height);
		battleScene.resize(width, height);
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
		battleStartScene.dispose();
		battleIntroScene.dispose();
	}

}
