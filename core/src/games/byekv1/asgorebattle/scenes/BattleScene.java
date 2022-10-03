/**
 * @author	byekv1
 */
package games.byekv1.asgorebattle.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import games.byekv1.asgorebattle.controllers.BattleController;
import games.byekv1.graphics.BattleGUI;
import games.byekv1.graphics.HollowBox;
import games.byekv1.graphics.HealthBar;
import games.byekv1.graphics.PNGAnimatedMobileScreenObject;
import games.byekv1.graphics.graphicloaders.PNGAnimatedMSOLoader;
import games.byekv1.output.VolumeManager;

/**
 * @author byekv
 *
 */
public class BattleScene implements Scene {
	
	///	Constants
	public static int WORLD_WIDTH = 640;
	public static int WORLD_HEIGHT = 480;
	public static int D_DEFAULT_W = 534; 
	public static int D_DEFAULT_H = 128;


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
	
	private BattleGUI battleGUI;
	private BattleController battleController;
	private PNGAnimatedMobileScreenObject asgoreIdleAnimation;
	private PNGAnimatedMobileScreenObject knifeSlashAnimation;
	private Label damageLabel;
	private Label nameLabel;
	private Label hpLabel;
	private Label hpNumberLabel;
	private HealthBar asgoreHealthBar;
	private HealthBar playerHealthBar;
	private Music phase1Music;
	private HollowBox hollowBox;

	///
	///	Getters
	///
	
	public Stage getStage() { return stage; }
	public Viewport getViewport() { return viewport; }
	public BattleGUI getBattleGUI() { return battleGUI; }
	public BattleController getBattleController() { return battleController; }
	public PNGAnimatedMobileScreenObject getAsgoreIdleAnimation() { return asgoreIdleAnimation; }
	
	///
	///	Setters
	///
		
	public void setStage(Stage stage) { this.stage = stage; }
	public void setViewport(Viewport viewport) { this.viewport = viewport; }
	public void setBattleGUI(BattleGUI battleGUI) { this.battleGUI = battleGUI; }
	public void setBattleController(BattleController battleController) {
		this.battleController = battleController; 
	}
	private void setAsgoreIdleAnimation(PNGAnimatedMobileScreenObject asgoreIdleAnimation) {
		this.asgoreIdleAnimation = asgoreIdleAnimation;
	}
	private void setKnifeSlashAnimation(PNGAnimatedMobileScreenObject knifeSlashAnimation) {
		this.knifeSlashAnimation = knifeSlashAnimation;
	}
	public void setDamageLabel(Label damageLabel) { this.damageLabel = damageLabel; }
	public void setNameLabel(Label nameLabel) { this.nameLabel = nameLabel; }
	public void setHPLabel(Label hpLabel) { this.hpLabel = hpLabel; }
	public void setHPNumberLabel(Label hpNumberLabel) { this.hpNumberLabel = hpNumberLabel; }
	private void setAsgoreHealthBar(HealthBar asgoreHealthBar) { this.asgoreHealthBar = asgoreHealthBar; }
	private void setPlayerHealthBar(HealthBar playerHealthBar) { this.playerHealthBar = playerHealthBar; }
	public void setPhase1Music(Music phase1Music) { this.phase1Music = phase1Music; }
	public void setHollowBox(HollowBox hollowBox) { this.hollowBox = hollowBox; }
	
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
		
		//	BattleController
		setBattleController(new BattleController());

		//	Battle GUI
		setBattleGUI(new BattleGUI());
		battleController.setBattleGUI(battleGUI);
		battleGUI.getActOption().setVisible(false);
		battleGUI.getMercyOption().setVisible(false);
		battleGUI.setScaling(true);
		
		//	Asgore Idle Animation
		setAsgoreIdleAnimation(
				PNGAnimatedMSOLoader.loadPNGAnimatedMobileScreenObject("asgoreIdle1/", "", 60)
				);

		//	knife Slash Animation
		setKnifeSlashAnimation(
			PNGAnimatedMSOLoader.loadPNGAnimatedMobileScreenObject(
				"knifeslash/", "knife", 6
			)
		);

		//	damageLabel
		setDamageLabel(new Label("9999999", new LabelStyle(
			new BitmapFont(
				Gdx.files.internal("fonts/undertaleDamage.fnt")
				), Color.RED)
			));
		battleController.setDamageLabel(damageLabel);

		//	nameLabel
		setNameLabel(new Label("Chara  Lv 20", new LabelStyle(
			new BitmapFont(
				Gdx.files.internal("fonts/names.fnt")
				), Color.WHITE)
			));
		
		nameLabel.setFontScale(.64f, .96f);

		//	hpLabel
		setHPLabel(new Label("HP", new LabelStyle(
			new BitmapFont(
				Gdx.files.internal("fonts/8bit.fnt")
				), Color.WHITE)
			));
		hpLabel.setFontScale(.3f, .3f);

		//	hpNumberLabel
		setHPNumberLabel(new Label("XX/99", new LabelStyle(
			new BitmapFont(
				Gdx.files.internal("fonts/names.fnt")
				), Color.WHITE)
			));
		hpNumberLabel.setFontScale(.64f, .96f);
		
		//	asgoreHealthBar
		setAsgoreHealthBar(
			new HealthBar(0,0, 1, 1, asgoreIdleAnimation.getWidth()*.7f, WORLD_HEIGHT/30*.8f, Color.GREEN, Color.GRAY)
		);
		battleController.setAsgoreHealthBar(asgoreHealthBar);

		//	playerHealthBar
		setPlayerHealthBar(
			new HealthBar(0, 0, 1, 1, WORLD_WIDTH*.22f, nameLabel.getHeight(), Color.YELLOW, Color.RED)
		);
		battleController.setPlayerHealthBar(playerHealthBar);

		//	hollowBox
		setHollowBox(new HollowBox(0, 0, battleGUI.getWidth(), battleGUI.getHeight(), Color.WHITE, 5));

		//	Music
		setPhase1Music(Gdx.audio.newMusic(Gdx.files.internal("music/asgoreBattle.ogg")));
		phase1Music.setLooping(true);
		
		//	Stage Additions
		stage.addActor(battleGUI);
		stage.addActor(asgoreIdleAnimation);
		stage.addActor(knifeSlashAnimation);
		stage.addActor(asgoreHealthBar);
		stage.addActor(playerHealthBar);
		stage.addActor(damageLabel);
		stage.addActor(nameLabel);
		stage.addActor(hpLabel);
		stage.addActor(hpNumberLabel);
		stage.addActor(hollowBox);

		//	Final Stage Apply
		stage.getViewport().apply();
		
	}

	@Override
	public void setup() {
		
		//	Setup
		asgoreIdleAnimation.setFrameDelay(3);
		knifeSlashAnimation.setFrameDelay(7);
		knifeSlashAnimation.resetFrameCount();
		asgoreIdleAnimation.resetFrameCount();
		
		//	Scaling
		battleGUI.setSize(WORLD_WIDTH-WORLD_WIDTH/6, WORLD_HEIGHT/3);
		battleGUI.setScaling(false);
		asgoreIdleAnimation.setScale(.9f);
		knifeSlashAnimation.setScale(2f);

		//	Colors
		knifeSlashAnimation.setColor(new Color(255, 0, 0, 1.f));
		
		//	Postions
		battleGUI.setPosition(WORLD_WIDTH/12, WORLD_HEIGHT/32);
		asgoreIdleAnimation.setPosition(
				WORLD_WIDTH/2-asgoreIdleAnimation.getWidth()*asgoreIdleAnimation.getScaleX()/2 + 52.5f*asgoreIdleAnimation.getScaleX(), 
				WORLD_HEIGHT/2
			);
		knifeSlashAnimation.setPosition(WORLD_WIDTH/2-knifeSlashAnimation.getWidth()*knifeSlashAnimation.getScaleX()/2, (WORLD_HEIGHT/2)+knifeSlashAnimation.getHeight()*knifeSlashAnimation.getScaleY()*7);
		hollowBox.setBounds(battleGUI.getX(), battleGUI.getY()+battleGUI.getHeight()*.6f, D_DEFAULT_W, D_DEFAULT_H);

		//	asgoreHealthBar
		asgoreHealthBar.setPosition(WORLD_WIDTH/2-asgoreHealthBar.getWidth()/2, (10.f/16.f)*(WORLD_HEIGHT));
		asgoreHealthBar.setMaxHealthPoints(200);
		asgoreHealthBar.setHealthPoints(200);
		asgoreHealthBar.setdisplayDelay(1);
		asgoreHealthBar.setdisplayPoints(200);
		asgoreHealthBar.setVisible(false);

		//	damagelabel
		damageLabel.setVisible(false);
		damageLabel.setPosition((asgoreHealthBar.getX()+asgoreHealthBar.getWidth()/2)-damageLabel.getWidth()/2, asgoreHealthBar.getY()+(asgoreHealthBar.getHeight()*9.3f));

		//	nameLabel
		nameLabel.setVisible(false);
		nameLabel.setPosition(battleGUI.getX(), battleGUI.getY()+battleGUI.getHeight()*.38f);

		//	playerHealthBar
		playerHealthBar.setPosition((WORLD_WIDTH-playerHealthBar.getWidth())*.5f, nameLabel.getY());
		playerHealthBar.setMaxHealthPoints(99);
		playerHealthBar.setHealthPoints(99);
		playerHealthBar.setdisplayDelay(0);
		playerHealthBar.setdisplayPoints(99);
		playerHealthBar.setVisible(false);
		System.out.println(playerHealthBar.getWidth());

		//	hpLabel
		hpLabel.setVisible(false);
		hpLabel.setPosition(playerHealthBar.getX()-hpLabel.getWidth()*.39f, playerHealthBar.getY()-3f);

		//	hpNumberLabel
		hpNumberLabel.setVisible(false);
		hpNumberLabel.setPosition(playerHealthBar.getX()+playerHealthBar.getWidth()*1.23f, playerHealthBar.getY());

		battleGUI.setScaling(true);

		//Mus
		phase1Music.setVolume(VolumeManager.volume*.85f);

		setCounter(0);
		
	}

	@Override
	public void processLogic() {
		
		battleGUI.setVisible(false);
		asgoreIdleAnimation.setVisible(false);
		knifeSlashAnimation.setVisible(false);
		asgoreHealthBar.setVisible(false);
		damageLabel.setVisible(false);

		nameLabel.setVisible(true);
		hpLabel.setVisible(true);
		playerHealthBar.setVisible(true);
		hpNumberLabel.setVisible(true);
		
		if (counter == 0) {
			phase1Music.play();
			battleGUI.getActOption().setVisible(false);
			battleGUI.getMercyOption().setVisible(false);
		}
		if (counter >= 1) {
			battleGUI.setVisible(true);
			asgoreIdleAnimation.setVisible(true);
			battleController.processLogic();
			battleGUI.setSelection(battleController.getSelection());
		}

		if (battleController.getTurn() == BattleController.BattleControllerConstants.PLAYER_TURN_FIGHT) {
			if (!knifeSlashAnimation.isOver()) {
				knifeSlashAnimation.play();
				knifeSlashAnimation.setVisible(true);
			}
		}
		else knifeSlashAnimation.resetFrameCount();
		asgoreHealthBar.processLogic();
		if(counter%10==0)playerHealthBar.setHealthPoints(playerHealthBar.getHealthPoints()-1);
		playerHealthBar.processLogic();

		hpNumberLabel.setText(String.format("%02d / 99", playerHealthBar.getdisplayPoints()));

		if (playerHealthBar.getHealthPoints() == 0) {
			System.exit(0);
		}
		
		stage.act();
		
		asgoreIdleAnimation.setVisible(true);
		asgoreIdleAnimation.play();
		
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
	public void stop() {
		
		phase1Music.stop();
		
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
	
	public BattleScene() {
		
		initalize();
		
	}
	
	///
	///	Destructor
	///

	@Override
	public void dispose() {
		
		//	Clean
		asgoreIdleAnimation.dispose();
		phase1Music.dispose();
		
		//	Nullify
		asgoreIdleAnimation = null;
		phase1Music = null;
		
	}

}
