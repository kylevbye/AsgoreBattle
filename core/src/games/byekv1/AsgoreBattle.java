package games.byekv1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import games.byekv1.asgorebattle.scenes.Scene;
import games.byekv1.asgorebattle.scenes.SceneManager;
import games.byekv1.asgorebattle.scenes.TitlescreenScene;
import games.byekv1.input.InputHandler;
import games.byekv1.input.PlayerInput;

public class AsgoreBattle extends ApplicationAdapter {
	
	SpriteBatch batch;
	Stage stage;
	Camera cam;
	Label quitLabel;
	int quitCount;
	
	@Override
	public void create () {
		
		Gdx.graphics.setWindowedMode(640, 480);
		batch = new SpriteBatch();

		cam = new OrthographicCamera(640, 480);
		cam.translate(cam.viewportWidth*.5f, cam.viewportHeight*.5f, 0);
		stage = new Stage(new StretchViewport(640, 480, cam));
		
		Gdx.input.setInputProcessor(new InputHandler());
		
		quitCount = 0;
		BitmapFont quitFont = new BitmapFont(Gdx.files.internal("fonts/undertaleTitle.fnt"));
		LabelStyle quitStyle = new LabelStyle(quitFont, Color.WHITE);
		quitLabel = new Label("QUITING...", quitStyle);
		quitLabel.setPosition(0, Gdx.graphics.getHeight()-quitLabel.getHeight()/2);
		quitLabel.setFontScale(Gdx.graphics.getWidth()/640 * .7f, Gdx.graphics.getHeight()/480 * .7f);
		quitLabel.moveBy(0, -quitLabel.getHeight()*quitLabel.getFontScaleY()/2);

		stage.addActor(quitLabel);
		
		stage.getViewport().apply();

		SceneManager.loadScenes();
		SceneManager.setToTitle();
		
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (PlayerInput.f4WasPressed) {
			if (!Gdx.graphics.isFullscreen()) {
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			}
			else {
				Gdx.graphics.setWindowedMode(640, 480);
			}
		}
		
		batch.begin();

		quitLabel.setVisible(false);
		
		if (PlayerInput.esc_down) {
			++quitCount;
			quitLabel.setVisible(true);
		}
		else quitCount = 0;
		
		if (quitCount == 120) Gdx.app.exit();

		stage.act();
		stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
		stage.getViewport().getCamera().update();
		stage.getViewport().apply();

		stage.draw();
		
		SceneManager.processLogic();
		SceneManager.draw(batch, 1f);
		
		batch.end();
		
		PlayerInput.resetTempControls();
		
	}
	
	@Override
	public void resize(int width, int height) {

		stage.getViewport().update(width, height, true);
		
		Camera stageCam = stage.getCamera();
		stageCam.viewportWidth = 640;
		stageCam.viewportHeight = 480;
		stageCam.position.set(stageCam.viewportWidth/2, stageCam.viewportHeight/2, 0f);
		stageCam.update();

		SceneManager.resize(width, height);
		
	}
	
	@Override
	public void dispose () {
		
		batch.dispose();
		SceneManager.dispose();
		
	}
}
