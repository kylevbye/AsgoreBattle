package games.byekv1.asgorebattle.scenes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import games.byekv1.graphics.Image;
import games.byekv1.graphics.MobileScreenObject;
import games.byekv1.graphics.graphicloaders.ImageLoader;
import games.byekv1.output.VolumeManager;

public class GameOverScene implements Scene {

    ///	Constants
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
    /// Assets
    ///
    private Music gameOverMusic;
    private Label gameOverText;
    private Image soul;
    private Image soulShattered;
    private Sound soulSplitSound;
    private Sound soulScatterSound;
    private ArrayList<MobileScreenObject> soulFragments;

    ///
    /// Getters
    ///

    public Stage getStage() { return stage; }
	public Viewport getViewport() { return viewport; }
    public Music getGameOverMusic() { return gameOverMusic; }
    public Label getGameOverText() { return gameOverText; }
    public Image getSoul() { return soul; }
    public Image getSoulShattered() { return soulShattered; }
    public Sound getSoulSplitSound() { return soulSplitSound; }
    public Sound getSoulScatterSound() { return soulScatterSound; }
    public ArrayList<MobileScreenObject> getSoulFragments() { return soulFragments; }

    ///
    /// Setters
    ///

    public void setStage(Stage stage) { this.stage = stage; }
	public void setViewport(Viewport viewport) { this.viewport = viewport; }
    public void setGameOverMusic(Music gameOverMusic) { this.gameOverMusic = gameOverMusic; }
    public void setGameOverText(Label gameOverText) { this.gameOverText = gameOverText; }
    public void setSoul(Image soul) { this.soul = soul;}
    public void setSoulShattered(Image soulShattered) { this.soulShattered = soulShattered;}
    public void setSoulSplitSound(Sound soulSplitSound) { this.soulSplitSound = soulSplitSound;}
    public void setSoulScatterSound(Sound soulScatterSound) { this.soulScatterSound = soulScatterSound;}
    public void setSoulFragments(ArrayList<MobileScreenObject> soulFragments) {
        this.soulFragments = soulFragments; 
    }

    ///
    /// Scene Functions
    ///

    @Override
	public void draw(Batch batch, float alpha) {

		if (!batch.isDrawing()) batch.begin();
		
		stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
		stage.getViewport().getCamera().update();
		stage.getViewport().apply();
		
		stage.draw();
		
	}

    @Override
    public void initalize() {

        //	Camera
		OrthographicCamera cam = new OrthographicCamera(WORLD_WIDTH,WORLD_HEIGHT);
		cam.translate(cam.viewportWidth/2, cam.viewportHeight/2, 0);
		setCamera(cam);
		
		//	Stage/Viewport
		setViewport(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
		setStage(new Stage(viewport));

        //  gameOverMusic
        setGameOverMusic(Gdx.audio.newMusic(Gdx.files.internal("music/gameover.ogg")));

        //  gameOverText
        setGameOverText(new Label("Game\nOver", new LabelStyle(
            new BitmapFont(
                Gdx.files.internal("fonts/8bit.fnt")
            ), Color.WHITE
        )));

        //  Soul
        setSoul(ImageLoader.loadImage("soul/soul.png"));

        //  Soul Shattered
        setSoulShattered(ImageLoader.loadImage("soul/soulShattered.png"));

        //  soul split sound
        setSoulSplitSound(Gdx.audio.newSound(Gdx.files.internal("sounds/snd_break1.wav")));

        //  soul Scatter Sound
        setSoulScatterSound(Gdx.audio.newSound(Gdx.files.internal("sounds/snd_break2.wav")));

        ArrayList<MobileScreenObject> soulFragments = new ArrayList<MobileScreenObject>();
        for (int i = 0; i<6; ++i) {
            MobileScreenObject soulFragment = new MobileScreenObject(ImageLoader.loadImage("soul/soulFragment.png")); 
            soulFragments.add(soulFragment);
            stage.addActor(soulFragment);
        }
        setSoulFragments(soulFragments);
        

        stage.addActor(gameOverText);
        stage.addActor(soul);
        stage.addActor(soulShattered);

        stage.setViewport(viewport);
        stage.getViewport().apply();

    }

    @Override
    public void setup() {

        setCounter(0);

        //  gameOverMusic
        gameOverMusic.setPosition(0.f);

        //  gameOverText
        gameOverText.setAlignment(Align.center);
        gameOverText.setPosition(WORLD_WIDTH/2f, WORLD_HEIGHT/2f);
        gameOverText.setFontScale(2.5f, 2.5f);
        gameOverText.moveBy(-gameOverText.getWidth()*.5f, 100);
        gameOverText.getColor().a = 0.f;

        //  soul/soulShattered
        soul.setPosition(WORLD_WIDTH*.5f, WORLD_HEIGHT*.5f);
        soul.moveBy(-soul.getWidth()*.5f, -soul.getHeight()*3.5f);
        soul.setColor(Color.RED);
        soulShattered.setPosition(soul.getX(), soul.getY());
        soulShattered.setColor(Color.RED);

        //  soulFragments
        for (int i = 0; i<soulFragments.size(); ++i) {
            
            MobileScreenObject soulFragment = soulFragments.get(i);
            soulFragment.setPosition(soul.getX(), soul.getY());
            soulFragment.move(soulFragment.getWidth()*.5f, soulFragment.getHeight()*.5f);
            soulFragment.setAcceleration(5f);
            soulFragment.setDeceleration(.05f);
            float angle = 0.f;

            switch(i) {

                case 0:
                    angle = 30.f;
                    break;

                case 1:
                    angle = 45.f;
                    break;

                case 2:
                    angle = 60.f;
                    break;
                    
                case 3:
                    angle = 120.f;
                    break;
                        
                case 4:
                    angle = 135.f;
                    break;
                        
                case 5:
                    angle = 150.f;
                    break;

            }

            soulFragment.accelerateAtAngle(angle);

        }

    }

    @Override
    public void processLogic() {

        soul.setVisible(false);
        soulShattered.setVisible(false);
        for (MobileScreenObject soulFragment : soulFragments) {
            soulFragment.setVisible(false);
        }

        //  Sound Queues
        switch (counter) {
            case START:
                break;
            case SPLIT:
                soulSplitSound.play(VolumeManager.volume);
                break;
            case SHATTER:
                soulScatterSound.play(VolumeManager.volume);
                break;
            case MUSIC_START:
                gameOverMusic.play();
        }

        if (counter < SPLIT) {
            soul.setVisible(true);
        }
        else if (counter < SHATTER) {
            soulShattered.setVisible(true);
        }
        else if (counter < OVER) {
            for (MobileScreenObject soulFragment : soulFragments) {
                soulFragment.setVisible(true);
                soulFragment.applyPhysics(1.f);
                soulFragment.moveDown(2.f);
            }
        }
        
        if (counter > SHOW_TITLE && gameOverText.getColor().a < 1.0f) {
            gameOverText.getColor().a += .05f*.125f*.5f;
        }
        
        gameOverText.setVisible(true);

        stage.act();
        incrementCounter();

    }

	@Override
	public void stop() {
		
        if (gameOverMusic.isPlaying()) gameOverMusic.stop();

	}

	@Override
	public void incrementCounter() { ++counter; }

	@Override
	public void resize(int width, int height) {
		
		stage.getViewport().update(width, height, true);
		
		Camera stageCam = stage.getCamera();
		stageCam.viewportWidth = WORLD_WIDTH;
		stageCam.viewportHeight = WORLD_HEIGHT;
		stageCam.position.set(stageCam.viewportWidth/2, stageCam.viewportHeight/2, 0f);
		stageCam.update();
		
	}
	
	///
	///	Constructor
	///
	
	public GameOverScene() {
		
		initalize();
		
	}
	
	///
	///	Destructor
	///

	@Override
	public void dispose() {
		
		//	Clean
		
		//	Nullify
		
	}

    ///
    /// Scene Constants
    ///
    public static final int START = 0;
    public static final int SPLIT = 30;
    public static final int SHATTER = 120;
    public static final int SHOW_TITLE = 200;
    public static final int MUSIC_START = 220;
    public static final int OVER = 500;
    
}
