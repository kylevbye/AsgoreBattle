package games.byekv1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import games.byekv1.asgorebattle.scenes.Scene;
import games.byekv1.asgorebattle.scenes.TitlescreenScene;

public class AsgoreBattle extends ApplicationAdapter {
	
	OrthographicCamera cam;
	Scene titleScene;
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		cam = new OrthographicCamera();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		titleScene = new TitlescreenScene();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		titleScene.processLogic();
		
		batch.begin();
		batch.draw(img, 0, 0);
		titleScene.draw(batch, 1f);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
