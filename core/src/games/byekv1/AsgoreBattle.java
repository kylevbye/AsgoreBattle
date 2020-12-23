package games.byekv1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import games.byekv1.asgorebattle.scenes.Scene;
import games.byekv1.asgorebattle.scenes.SceneManager;
import games.byekv1.asgorebattle.scenes.TitlescreenScene;
import games.byekv1.input.InputHandler;
import games.byekv1.input.PlayerInput;

public class AsgoreBattle extends ApplicationAdapter {
	
	SpriteBatch batch;
	
	Label quitLabel;
	int quitCount;
	
	@Override
	public void create () {
		
		Gdx.graphics.setWindowedMode(640, 480);
		batch = new SpriteBatch();
		
		Gdx.input.setInputProcessor(new InputHandler());
		
		quitCount = 0;
		BitmapFont quitFont = new BitmapFont(Gdx.files.internal("fonts/undertaleTitle.fnt"));
		LabelStyle quitStyle = new LabelStyle(quitFont, Color.WHITE);
		quitLabel = new Label("QUITING...", quitStyle);
		
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
		
		if (PlayerInput.esc_down) {
			++quitCount;
			quitLabel.setPosition(0, Gdx.graphics.getHeight()-quitLabel.getHeight()/2);
			quitLabel.setFontScale(Gdx.graphics.getWidth()/640 * .7f, Gdx.graphics.getHeight()/480 * .7f);
			quitLabel.moveBy(0, -quitLabel.getHeight()*quitLabel.getFontScaleY()/2);
			quitLabel.draw(batch, 1f);
		}
		else quitCount = 0;
		
		if (quitCount == 120) Gdx.app.exit();
		
		SceneManager.processLogic();
		SceneManager.draw(batch, 1f);
		
		batch.end();
		
		PlayerInput.resetTempControls();
		
	}
	
	@Override
	public void resize(int width, int height) {
		SceneManager.resize(width, height);
	}
	
	@Override
	public void dispose () {
		
		batch.dispose();
		SceneManager.dispose();
		
	}
}
