/**
 * @author	byekv1
 */
package games.byekv1.asgorebattle.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import games.byekv1.graphics.BattleGUI;
import games.byekv1.graphics.DialogLabel;
import games.byekv1.graphics.Image;
import games.byekv1.graphics.PNGAnimatedMobileScreenObject;
import games.byekv1.graphics.TwoSidedImage;
import games.byekv1.graphics.graphicloaders.ImageLoader;
import games.byekv1.graphics.graphicloaders.PNGAnimatedMSOLoader;
import games.byekv1.input.PlayerInput;
import games.byekv1.output.VolumeManager;

/**
 * @author byekv
 *
 */
public class BattleIntroScene implements Scene {
	
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
	///	Assets
	///
	
	private Music introMusic;
	private Sound spearSummonSound;
	private Sound errySound;
	private Sound breakSound1, breakSound2;
	private Image idleAsgore;
	private Image asgoreSilhouette;
	private Image asgoreSpear;
	private BattleGUI battleGUI;
	private PNGAnimatedMobileScreenObject asgoreIntroAnimation;
	private PNGAnimatedMobileScreenObject actShatterAnimation;
	private Image asgoreReady;
	private DialogLabel dLabel1;
	
	///
	///	Getters
	///
	
	public Stage getStage() { return stage; }
	public Viewport getViewport() { return viewport; }
	public Music getIntroMusic() { return introMusic; }
	public Sound getSpearSummonSound() { return spearSummonSound; }
	public Sound getErrySound() { return errySound; }
	public Sound getBreakSound1() { return breakSound1; }
	public Sound getBreakSound2() { return breakSound2; }
	public Image getIdleAsgore() { return idleAsgore; }
	public Image getAsgoreSilhouette() { return asgoreSilhouette; }
	public Image getAsgoreSpear() { return asgoreSpear; }
	public BattleGUI getBattleGUI() { return battleGUI; }
	public PNGAnimatedMobileScreenObject getAsgoreIntroAnimation() { return asgoreIntroAnimation; }
	public PNGAnimatedMobileScreenObject getActShatterAnimation() { return actShatterAnimation; }
	public Image getAsgoreReady() { return asgoreReady; }
	public DialogLabel getDLabel1() { return dLabel1; }
	
	///
	///	Setters
	///
	
	public void setStage(Stage stage) { this.stage = stage; }
	public void setViewport(Viewport viewport) { this.viewport = viewport; }
	public void setIntroMusic(Music introMusic) { this.introMusic = introMusic; }
	public void setSpearSummonSound(Sound spearSummonSound) { this.spearSummonSound = spearSummonSound; }
	public void setErrySound(Sound errySound) { this.errySound = errySound; }
	public void setBreakSound1(Sound breakSound1) { this.breakSound1 = breakSound1; }
	public void setBreakSound2(Sound breakSound2) { this.breakSound2 = breakSound2; }
	public void setIdleAsgore(Image idleAsgore) { this.idleAsgore = idleAsgore; }
	public void setAsgoreSilhouette(Image asgoreSilhouette) { this.asgoreSilhouette = asgoreSilhouette; }
	public void setAsgoreSpear(Image asgoreSpear) { this.asgoreSpear = asgoreSpear; }
	public void setBattleGUI(BattleGUI battleGUI) { this.battleGUI = battleGUI; }
	public void setAsgoreIntroAnimation(PNGAnimatedMobileScreenObject asgoreIntroAnimation) {
		this.asgoreIntroAnimation = asgoreIntroAnimation;
	}
	public void setActShatterAnimation(PNGAnimatedMobileScreenObject actShatterAnimation) {
		this.actShatterAnimation = actShatterAnimation;
	}
	public void setAsgoreReady(Image asgoreReady) { this.asgoreReady = asgoreReady; }
	public void setDLabel1(DialogLabel dLabel1) { this.dLabel1 = dLabel1; }
	
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
		
		//
		//	Asset Loading
		//
		
		Music iMusic = Gdx.audio.newMusic(Gdx.files.internal("music/bol1_.wav"));
		iMusic.setLooping(false);
		setIntroMusic(iMusic);

		setSpearSummonSound(Gdx.audio.newSound(Gdx.files.internal("sounds/spearsummon.mp3")));
		setErrySound(Gdx.audio.newSound(Gdx.files.internal("sounds/snd_spearappear.wav")));
		setBreakSound1(Gdx.audio.newSound(Gdx.files.internal("sounds/mus_sfx_swipe.wav")));
		setBreakSound2(Gdx.audio.newSound(Gdx.files.internal("sounds/snd_break2.wav")));
		
		Image asgoreIntroIdle = ImageLoader.loadImage("asgore/asgoreLookDown.png");
		setIdleAsgore(asgoreIntroIdle);
		stage.addActor(asgoreIntroIdle);
		
		Image asgoreSilo = ImageLoader.loadImage("asgore/asgoreSilhouette.png");
		setAsgoreSilhouette(asgoreSilo);
		stage.addActor(asgoreSilo);
		
		Image spear = ImageLoader.loadImage("asgore/spear.png");
		setAsgoreSpear(spear);
		spear.setZIndex(10);
		spear.setColor(Color.RED);
		stage.addActor(spear);
		
		BattleGUI bGUI = new BattleGUI();
		setBattleGUI(bGUI);
		stage.addActor(bGUI);
		
		PNGAnimatedMobileScreenObject asgoreIntroAnim = PNGAnimatedMSOLoader.loadPNGAnimatedMobileScreenObject("asgoreIntro/", "asgoreIntro", 5);
		asgoreIntroAnim.setFrameDelay(10);
		setAsgoreIntroAnimation(asgoreIntroAnim);
		stage.addActor(asgoreIntroAnim);
		
		PNGAnimatedMobileScreenObject actShatterAnim = PNGAnimatedMSOLoader.loadPNGAnimatedMobileScreenObject("actShatter/", "act", 9);
		actShatterAnim.setFrameDelay(7);
		setActShatterAnimation(actShatterAnim);
		stage.addActor(actShatterAnim);
		
		DialogLabel lab1 = new DialogLabel("", null, 7);
		setDLabel1(lab1);
		stage.addActor(lab1);

		Image asgoreReady = ImageLoader.loadImage("asgore/asgoreReady.png");
		setAsgoreReady(asgoreReady);
		stage.addActor(asgoreReady);
		
		
		//	Final Stage Apply
		stage.getViewport().apply();
		
	}

	@Override
	public void setup() {
		
		//	Scale/Resize
		float asgoreScale = 1.85f;
		idleAsgore.setScale(asgoreScale);
		asgoreSilhouette.setScale(asgoreScale);
		asgoreSpear.setOrigin(asgoreSpear.getWidth()/2, asgoreSpear.getHeight()/2);
		asgoreSpear.setRotation(-90f);
		asgoreSpear.setScale(1.3f);
		asgoreIntroAnimation.setScale(asgoreScale);
		battleGUI.setSize(WORLD_WIDTH-WORLD_WIDTH/6, WORLD_HEIGHT/3);
		battleGUI.setScaling(false);
		battleGUI.getMercyOption().setVisible(false);
		actShatterAnimation.setScale(battleGUI.getActOption().getScaleX(), battleGUI.getActOption().getScaleY());
		asgoreReady.setScale(.9f);
		
		//	Hide all
		idleAsgore.setVisible(false);
		asgoreIntroAnimation.setVisible(false);
		dLabel1.setVisible(false);
		asgoreSilhouette.setVisible(false);
		asgoreSpear.setVisible(false);
		actShatterAnimation.setVisible(false);
		asgoreReady.setVisible(false);
		
		setCounter(0);
		
		//	Set Positions
		idleAsgore.setPosition(WORLD_WIDTH/2 - asgoreIntroAnimation.getWidth()*asgoreIntroAnimation.getScaleX()/2, WORLD_HEIGHT/2);
		asgoreSilhouette.setPosition(WORLD_WIDTH/2 - asgoreIntroAnimation.getWidth()*asgoreIntroAnimation.getScaleX()/2, WORLD_HEIGHT/2);
		battleGUI.setPosition(WORLD_WIDTH/12, WORLD_HEIGHT/32);
		asgoreSpear.setPosition(asgoreSilhouette.getX()+asgoreSpear.getHeight()/6.5f, asgoreSilhouette.findCenterY());
		asgoreIntroAnimation.setPosition(
				WORLD_WIDTH/2 - asgoreIntroAnimation.getWidth()*asgoreIntroAnimation.getScaleX()/2, WORLD_HEIGHT/2
				);
		dLabel1.setPosition(0, 0);
		asgoreReady.setPosition(
				WORLD_WIDTH/2-asgoreReady.getWidth()*asgoreReady.getScaleX()/2 + 52.5f*asgoreReady.getScaleX(), 
				WORLD_HEIGHT/2
			);
		
	}

	@Override
	public void processLogic() {
		
		actShatterAnimation.setVisible(false);
		idleAsgore.setVisible(false);
		asgoreSilhouette.setVisible(false);
		asgoreIntroAnimation.setVisible(false);
		dLabel1.setVisible(false);
		asgoreSpear.setVisible(false);
		asgoreReady.setVisible(false);
		
		introMusic.setVolume(VolumeManager.volume);
		
		if (counter == 0) { 
			actShatterAnimation.setPosition(
					battleGUI.getActOption().getX()-battleGUI.getActOption().getWidth()*battleGUI.getActOption().getScaleX()-4f, 
					battleGUI.getActOption().getY()-battleGUI.getActOption().getHeight()*battleGUI.getActOption().getScaleY()
					);
			introMusic.play(); 
		}
		
		//	Skips
		if (PlayerInput.zWasPressed) {
			if (counter < CounterConstants.Dialogue.FINAL-30) {
				setCounter(CounterConstants.Dialogue.FINAL-30);
				introMusic.stop();
			}
		}
		
		//	Asgore
		if (counter < CounterConstants.Dialogue.FINAL) idleAsgore.setVisible(true);
		
		if (counter == CounterConstants.Dialogue.FINAL-30) errySound.play(VolumeManager.volume);
		
		if (counter == CounterConstants.Dialogue.FINAL-10) {
			spearSummonSound.play(VolumeManager.volume);
		}
		
		if (counter >= CounterConstants.Dialogue.FINAL && 
				counter < CounterConstants.ASGORE_SILO) {
			
			if (!asgoreIntroAnimation.isOver()) asgoreIntroAnimation.play();
			
			asgoreIntroAnimation.setVisible(true);
			
		}
		
		if (counter >= CounterConstants.ASGORE_SILO && counter < CounterConstants.FADEIN) {
			asgoreSilhouette.setVisible(true);
			asgoreSpear.setVisible(true);
			
			if (counter == CounterConstants.ASGORE_SILO) errySound.play(VolumeManager.volume);
			
			if (counter < CounterConstants.ASGORE_SILO + 100) {
				asgoreSpear.moveBy(0, 2);
				//asgoreSpear.setX(asgoreSilhouette.getX()+asgoreSilhouette.getScaleX()//*asgoreSilhouette.getWidth()*.5f
				//);
			}
			else asgoreSpear.moveBy(0, -7);
			
			//	Break
			if (counter == 3242) {
				battleGUI.getActOption().setVisible(false);
				breakSound1.play(VolumeManager.volume*1.6f);
			}
			if (counter >= 3242 && !actShatterAnimation.isOver()) {
				actShatterAnimation.play();
				actShatterAnimation.setVisible(true);
			}
			if (counter == 3245) breakSound2.play(VolumeManager.volume*1.6f);
			
			//.328
			if ((int)asgoreSilhouette.findCenterX() != (int)(WORLD_WIDTH * .275f)) {
				//asgoreSpear.moveBy(-1f, 0);
				asgoreSilhouette.moveBy(-1f, 0);
				asgoreSpear.setX(asgoreSilhouette.findCenterX()-asgoreSpear.getWidth()*(.199f));
			}
			
		}
		
		if (counter == CounterConstants.FADEIN) {
			
			idleAsgore.setPosition(WORLD_WIDTH/2 - asgoreIntroAnimation.getWidth()*asgoreIntroAnimation.getScaleX()/2, WORLD_HEIGHT/2);
		}
		
		if (counter >= CounterConstants.FADEIN) {
			idleAsgore.setVisible(true);
		}
		
		if (counter == CounterConstants.STARTFIGHT) {
			SceneManager.loadScene(SceneManager.SceneConstants.BATTLE);
		}

		if (counter >= 3242 && actShatterAnimation.isOver()) {
			battleGUI.setScaling(true);
			asgoreReady.setVisible(true);
			idleAsgore.setVisible(false);
		}
		
		
		//	Dialogue
		switch (counter) {
		
		case CounterConstants.Dialogue.D1:
			setDLabel1(VoidDialogue.d1());
			stage.addActor(dLabel1);
			break;
			
		case CounterConstants.Dialogue.D2:
			setDLabel1(VoidDialogue.d2());
			stage.addActor(dLabel1);
			break;
			
		case CounterConstants.Dialogue.D3:
			setDLabel1(VoidDialogue.d3());
			stage.addActor(dLabel1);
			break;
			
		case CounterConstants.Dialogue.D4:
			setDLabel1(VoidDialogue.d4());
			stage.addActor(dLabel1);
			break;
			
		}
		
		dLabel1.setPosition(WORLD_WIDTH/2-dLabel1.getFullLabel().getWidth()/2, WORLD_HEIGHT/3);
		
		if (dLabel1.isDone()) dLabel1.addAction(Actions.removeActor());
		else if (counter > CounterConstants.Dialogue.START && counter <CounterConstants.Dialogue.FINAL) dLabel1.setVisible(true);;
		if (dLabel1.isVisible()) dLabel1.play();
		
		stage.act();
		
		
		incrementCounter();
		
	}

	@Override
	public void draw(Batch batch, float alpha) {
		
		if (!batch.isDrawing()) batch.begin();
		
		stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
		stage.getViewport().getCamera().update();
		stage.getViewport().apply();
		
		if (counter >= CounterConstants.ASGORE_SILO && counter < CounterConstants.FADEIN) {
			
			float transparency = (-CounterConstants.ASGORE_SILO+counter)/70f;
			if (transparency > 1f) transparency = 1f;
			Gdx.gl.glClearColor(transparency, transparency, transparency, transparency);
		    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		    
		}
		
		if (counter >= CounterConstants.FADEOUT && counter < CounterConstants.FADEIN) {
			
			float transparency = (-CounterConstants.FADEOUT+counter)/30f;
			
			for (Actor a : stage.getActors()) {
		    	Color aCol = a.getColor();
		    	a.setColor(aCol.r, aCol.g, aCol.b, 1f - transparency);
		    }
			
		}
		
		if (counter >= CounterConstants.FADEIN) {
			
			float transparency = (-CounterConstants.FADEIN+counter)/70f;
			
			Gdx.gl.glClearColor(1-transparency, 1-transparency, 1-transparency, 1-transparency);
		    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			for (Actor a : stage.getActors()) {
		    	Color aCol = a.getColor();
		    	a.setColor(aCol.r, aCol.g, aCol.b, transparency);
		    }
			
		}
		
		stage.draw();
		
	}

	@Override
	public void stop() {
		
		if (introMusic.isPlaying()) introMusic.stop();
		
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
	///	Dialogue Generator
	///
	
	public static class VoidDialogue {
		
		public static final int delay = 11;
		
		public static final DialogLabel d1() { 
			return new DialogLabel("(Dust and Darkness fill\n  the room.)", DialogLabel.genericDialogSound, delay); 
		}
		public static final DialogLabel d2() {
			return new DialogLabel("(Twilight shines through\n  the barrier.)", DialogLabel.genericDialogSound, delay); 
		}
		public static final DialogLabel d3() { 
			DialogLabel d3 = new DialogLabel("(It seems that your spree\n  may finally come to an end.)", DialogLabel.genericDialogSound, delay); 
			d3.setColor(Color.RED);
			return d3;
		}
		public static final DialogLabel d4() {
			DialogLabel d4 = new DialogLabel("DETERMINATION", DialogLabel.genericDialogSound, (int)(delay*2.15f)); 
			d4.setColor(Color.RED);
			return d4;
		}
		
	}
	
	///
	///	Counter Constants
	///
	
	public static class CounterConstants {
		
		public static final int ASGORE_SILO = 3100;
		public static final int FADEOUT = 3250;
		public static final int FADEIN = 3325;
		public static final int STARTFIGHT = 3400;
		
		
		public static class Dialogue {
			
			public static final int START = 100;
			public static final int FINAL = 3000;
			
			public static final int D1 = 100;
			public static final int D2 = 800;
			public static final int D3 = 1400;
			public static final int D4 = 2200;
			
		}
		
	}
	
	///
	///	Constructors
	///

	/**
	 * 
	 */
	public BattleIntroScene() {
		
		initalize();
		
	}
	
	///
	///	Destructors
	///
	
	@Override
	public void dispose() {
		
		//	Dispose
		introMusic.dispose();
		spearSummonSound.dispose();
		asgoreIntroAnimation.dispose();
		
		//	Nullify
		introMusic = null;
		spearSummonSound = null;
		asgoreIntroAnimation = null;
		
	}

}
