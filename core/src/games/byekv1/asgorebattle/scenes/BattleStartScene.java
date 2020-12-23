/**
 * @author	byekv1
 */
package games.byekv1.asgorebattle.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import games.byekv1.graphics.MobileScreenObject;
import games.byekv1.graphics.graphicloaders.ImageLoader;
import games.byekv1.output.VolumeManager;

/**
 * @author byekv1
 *
 */
public class BattleStartScene implements Scene {
	
	public static int WORLD_WIDTH = 640;
	public static int WORLD_HEIGHT = 480;
	
	///
	///	Scene Fields
	///
	
	private Camera camera;
	private int counter;
	
	///
	///	Scene Getters
	///
	
	@Override
	public Camera getCamera() { return camera; }
	
	@Override
	public int getCounter() { return counter; }
	
	///
	///	Scene Setters
	///
	
	@Override
	public void setCamera(Camera camera) { this.camera = camera; }
	
	@Override
	public void setCounter(int counter) { this.counter = counter; }
	
	///
	///	Fields
	///
	
	private Stage stage;
	private Viewport viewport;
	
	///
	///	Local Assets
	///
	
	private MobileScreenObject soul;
	private Sound battleStartSound;
	
	///
	///	Getters
	///
	
	public Stage getStage() { return stage; }
	public Viewport getViewport() { return viewport; }
	public MobileScreenObject getSoul() { return soul; }
	public Sound getBattleStartSound() { return battleStartSound; }
	
	///
	///	Setters
	///
	
	public void setStage(Stage stage) { this.stage = stage; }
	public void setViewport(Viewport viewport) { this.viewport = viewport; }
	public void setSoul(MobileScreenObject soul) { this.soul = soul; }
	public void setBattleStartSound(Sound battleStartSound) { this.battleStartSound = battleStartSound; }
	
	///
	///	Scene Functions
	///
	
	@Override
	public void initalize() {
		
		//	Camera
		OrthographicCamera cam = new OrthographicCamera(WORLD_WIDTH,WORLD_HEIGHT);
		cam.translate(cam.viewportWidth/2, cam.viewportHeight/2, 0);
		setCamera(cam);
		
		//	Stage/Viewport
		setViewport(new StretchViewport(640, 480, camera));
		setStage(new Stage(viewport));
		
		//	Soul
		MobileScreenObject soulSprite = new MobileScreenObject(ImageLoader.loadImage("soul/soul.png"));
		soulSprite.setColor(Color.RED);
		soulSprite.centerOrigin();
		setSoul(soulSprite);
		stage.addActor(soulSprite);
		
		//	Battke Start Sound
		Sound bSound = Gdx.audio.newSound(Gdx.files.internal("sounds/battleStart.mp3"));
		setBattleStartSound(bSound);
		
		stage.getViewport().apply();
		
	}
	
	@Override
	public void setup() {
		
		float width, height;
		width = viewport.getWorldWidth();
		height = viewport.getWorldHeight();
		
		setCounter(0);
		
		soul.setPosition(width/2-soul.getWidth()/2, height/2-((10f/16f)*height)-soul.getHeight()/2);
		
	}
	
	@Override
	public void processLogic() {
		
		soul.setVisible(false);
		
		if (counter == CounterConstants.BLINKING) battleStartSound.play(VolumeManager.volume);
		
		if (counter < CounterConstants.MOVEUP && counter % 10 == 0) {
			
			soul.setVisible(true);
			
		}
		
		if (counter >= CounterConstants.MOVEUP && counter < CounterConstants.STANDSTILL) {
			
			soul.moveUp(13f);
			soul.setVisible(true);
			
		}
		
		if (counter >= CounterConstants.STANDSTILL && counter < CounterConstants.END) {
	
			soul.setVisible(true);
			
		}
		
		if (counter == CounterConstants.END) SceneManager.loadScene(SceneManager.SceneConstants.TITLE);
		
		stage.act(Gdx.graphics.getDeltaTime());
		
		incrementCounter();
		
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		
		if (!batch.isDrawing()) batch.begin();
		
		stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
		stage.getViewport().getCamera().update();
		stage.getViewport().apply();
		stage.draw();
		
	}
	
	@Override
	public void stop() {}
	
	@Override
	public void incrementCounter() { ++counter; }
	
	@Override 
	public void resize(int width, int height) {
		
		stage.getViewport().update(width, height, true);
		
		Camera stageCam = stage.getCamera();
		stageCam.viewportWidth = WORLD_WIDTH;
		stageCam.viewportHeight = WORLD_HEIGHT * height / width;
		stageCam.position.set(stageCam.viewportWidth/2, stageCam.viewportHeight/2, 0f);
		stageCam.update();
		
	}
	
	///
	///	Scene Constants
	///
	
	public final class CounterConstants {
		
		public static final int BLINKING = 0;
		public static final int MOVEUP = 30;
		public static final int STANDSTILL = 40;
		public static final int END = 90;
		
	}
	
	///
	///	Constructors
	///
	
	/**
	 * 
	 */
	public BattleStartScene() {
		
		initalize();
		
	}
	
	///
	///	Destructors
	///
	
	@Override
	public void dispose() {
		
		soul.dispose();
		battleStartSound.dispose();
		
		soul = null;
		battleStartSound.dispose();
		
	}

}
