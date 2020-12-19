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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * @author byekv
 *
 */
public class TitlescreenScene implements Scene {
	
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
	
	private Label titleLabel;
	
	///
	///	Assets
	///
	
	private Sound titleDropSound;
	
	///
	///	Getters
	///
	
	public Label getTitleLabel() { return titleLabel; }
	public Sound getTitleDropSound() { return titleDropSound; }
	
	///
	///	Setters
	///
	
	public void setTitleLabel(Label titleLabel) { this.titleLabel = titleLabel; }
	public void setTitleDropSound(Sound titleDropSound) { this.titleDropSound = titleDropSound; }
	
	///
	///	Scene Functions
	///

	@Override
	public void initalize() {
		
		//	Title Label
		BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/undertale.fnt"));
		LabelStyle titleStyle = new LabelStyle(titleFont, Color.WHITE);
		Label titleLab = new Label("Undertale AU", titleStyle);
		titleLab.setVisible(false);
		setTitleLabel(titleLab);
		
		//	Title Drop Sound
		Sound titleDrop = Gdx.audio.newSound(Gdx.files.internal("sounds/titleDrop.mp3"));
		setTitleDropSound(titleDrop);
		
	}

	@Override
	public void setup() {
		
		setCounter(0);
		
		
	}

	@Override
	public void processLogic() {
		
		//	Sounds
		if (counter == CounterConstants.TITLEDROP-20) titleDropSound.play();
		
		//	Labels
		if (counter > CounterConstants.TITLEDROP) {
			titleLabel.setVisible(true);
		}
		
		incrementCounter();
	}

	@Override
	public void draw(Batch batch, float alpha) {
		
		float screenWidth, screenHeight;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		if (!batch.isDrawing()) batch.begin();
		
		//	Title Label
		if (titleLabel.isVisible()) {
			titleLabel.setPosition(screenWidth/2, 3*screenHeight/4);
			titleLabel.setFontScale(2f);
			titleLabel.moveBy(-(titleLabel.getWidth()*titleLabel.getFontScaleX())/2, -(titleLabel.getHeight()*titleLabel.getFontScaleY())/2);
			titleLabel.draw(batch, alpha);
		}
		
	}
	
	@Override
	public void incrementCounter() { ++counter; }
	
	///
	///	Title Scene Constants
	///
	
	public class CounterConstants {
		
		public static final int TITLEDROP = 100;
		
	}
	
	
	///
	///	Constructors
	///
	
	/**
	 * 
	 */
	public TitlescreenScene() {
		
		OrthographicCamera cam = new OrthographicCamera();
		cam.translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		setCamera(cam);
		initalize();
		
	}
	
	///
	///	Destructors
	///
	
	@Override
	public void dispose() {
		
		//	Dispose
		titleDropSound.dispose();
		
		//	Nullify
		titleDropSound = null;
		
	}
	

}
