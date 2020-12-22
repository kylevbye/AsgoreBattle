/**
 * @author	byekv1
 */
package games.byekv1.asgorebattle.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

import games.byekv1.graphics.Image;
import games.byekv1.graphics.PNGAnimatedMobileScreenObject;
import games.byekv1.graphics.graphicloaders.ImageLoader;
import games.byekv1.graphics.graphicloaders.PNGAnimatedMSOLoader;
import games.byekv1.input.PlayerInput;
import games.byekv1.output.VolumeManager;

/**
 * This scene is the scene that plays when the game has
 * just started or after the game over scene. It has the
 * iconic Undertale start screen along with an unique main 
 * menu and instruction screen.
 * 
 * @author	byekv1
 * @see	Scene
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
	private Label titleInstructionLabel; 
	private Label titleAsgoreLabel;
	private Label titleAsgoreLabel2;
	private Label titleStartOptionLabel;
	private Label titleInstructionOptionLabel;
	private Label instructionLabel;
	private Label instructionConfirmLabel;
	private Label instructionCancelLabel;
	private Label instructionFullscreenLabel;
	private Label instructionQuitLabel;
	private Label instructionLabel2;
	private Label instructionDoneLabel;
	private int stage;
	private int selection;
	
	///
	///	Assets
	///

	private Sound titleDropSound;
	private Sound selectionSound;
	private Sound selectSound;
	private Sound knifeSlashSound;
	private Music titleMusic;
	private Image asgoreImage;
	private Image backgroundImage1;
	private Image backgroundImage2;
	private PNGAnimatedMobileScreenObject knifeAnimation;
	
	///
	///	Getters
	///
	
	public Label getTitleLabel() { return titleLabel; }
	public Label getTitleInstructionLabel() { return titleInstructionLabel; }
	public Label getTitleAsgoreLabel() { return titleAsgoreLabel; }
	public Label getTitleAsgoreLabel2() { return titleAsgoreLabel2; }
	public Label getTitleStartOptionLabel() { return titleStartOptionLabel; }
	public Label getTitleInstructionOptionLabel() { return titleInstructionOptionLabel; }
	public Label getInstructionLabel() { return instructionLabel; }
	public Label getInstructionConfirmLabel() { return instructionConfirmLabel; }
	public Label getInstructionCancelLabel() { return instructionCancelLabel; }
	public Label getInstructionFullscreenLabel() { return instructionFullscreenLabel; }
	public Label getInstructionQuitLabel() { return instructionQuitLabel; }
	public Label getInstructionLabel2() { return instructionLabel2; }
	public Label getInstructionDoneLabel() { return instructionDoneLabel; }
	public int getStage() { return stage; }
	public int getSelection() { return selection; }
	public Sound getTitleDropSound() { return titleDropSound; }
	public Sound getSelectionSound() { return selectionSound; }
	public Sound getSelectSound() { return selectSound; }
	public Sound getKnifeSlashSound() { return knifeSlashSound; }
	public Music getTitleMusic() { return titleMusic; }
	public Image getAsgoreImage() { return asgoreImage; }
	public Image getBackgroundImage1() { return backgroundImage1; }
	public Image getBackGroundImage2() { return backgroundImage2; }
	public PNGAnimatedMobileScreenObject getKnifeAnimation() { return knifeAnimation; }
	
	///
	///	Setters
	///
	
	public void setTitleLabel(Label titleLabel) { this.titleLabel = titleLabel; }
	public void setTitleInstructionLabel(Label titleInstructionLabel) { this.titleInstructionLabel = titleInstructionLabel; }
	public void setTitleAsgoreLabel(Label titleAsgoreLabel) { this.titleAsgoreLabel = titleAsgoreLabel; }
	public void setTitleAsgoreLabel2(Label titleAsgoreLabel2) { this.titleAsgoreLabel2 = titleAsgoreLabel2; }
	public void setTitleStartOptionLabel(Label titleStartOptionLabel) { this.titleStartOptionLabel = titleStartOptionLabel; }
	public void setTitleInstructionOptionLabel(Label titleInstructionOptionLabel) { this.titleInstructionOptionLabel = titleInstructionOptionLabel; }
	public void setInstructionLabel(Label instructionLabel) { this.instructionLabel = instructionLabel; }
	public void setInstructionConfirmLabel(Label instructionConfirmLabel) { this.instructionConfirmLabel = instructionConfirmLabel; }
	public void setInstructionCancelLabel(Label instructionCancelLabel) { this.instructionCancelLabel = instructionCancelLabel; }
	public void setInstructionFullscreenLabel(Label instructionFullscreenLabel) { this.instructionFullscreenLabel = instructionFullscreenLabel; }
	public void setInstructionQuitLabel(Label instructionQuitLabel) { this.instructionQuitLabel = instructionQuitLabel; }
	public void setInstructionLabel2(Label instructionLabel2) { this.instructionLabel2 = instructionLabel2; }
	public void setInstructionDoneLabel(Label instructionDoneLabel) { this.instructionDoneLabel = instructionDoneLabel; }
	public void setStage(int stage) { this.stage = stage; }
	public void setSelection(int selection) { this.selection = selection; }
	public void setTitleDropSound(Sound titleDropSound) { this.titleDropSound = titleDropSound; }
	public void setSelectionSound(Sound selectionSound) { this.selectionSound = selectionSound; }
	public void setSelectSound(Sound selectSound) { this.selectSound = selectSound; }
	public void setKnifeSlashSound(Sound knifeSlashSound) { this.knifeSlashSound = knifeSlashSound; }
	public void setTitleMusic(Music titleMusic) { this.titleMusic = titleMusic; }
	public void setAsgoreImage(Image asgoreImage) { this.asgoreImage = asgoreImage; }
	public void setBackgroundImage1(Image backgroundImage1) { this.backgroundImage1 = backgroundImage1; }
	public void setBackgroundImage2(Image backgroundImage2) { this.backgroundImage2 = backgroundImage2; }
	public void setKnifeAnimation(PNGAnimatedMobileScreenObject knifeAnimation) { this.knifeAnimation = knifeAnimation; }
	
	///
	///	Scene Functions
	///

	@Override
	public void initalize() {
		
		//	Camera
		float width, height;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		OrthographicCamera cam = new OrthographicCamera(width, height);
		cam.translate(width/2, height/2);
		setCamera(cam);
		
		//	Title Label
		BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/undertaleTitle.fnt"));
		LabelStyle titleStyle = new LabelStyle(titleFont, Color.WHITE);
		Label titleLab = new Label("Undertale AU", titleStyle);
		titleLab.setVisible(false);
		setTitleLabel(titleLab);
		
		//	Title Instruction Label
		BitmapFont titleInsFont = new BitmapFont(Gdx.files.internal("fonts/undertaleGameOver.fnt"));
		LabelStyle titleInsStyle = new LabelStyle(titleInsFont, Color.GRAY);
		Label titleInsLab = new Label("[ PRESS Z OR ENTER ]", titleInsStyle);
		titleInsLab.setVisible(false);
		setTitleInstructionLabel(titleInsLab);
		
		//	Title AsgoreLabel 1
		BitmapFont titleAsgoreFont = new BitmapFont(Gdx.files.internal("fonts/undertale.fnt"));
		LabelStyle titleAsgoreStyle = new LabelStyle(titleAsgoreFont, Color.WHITE);
		Label titleAsgLab = new Label("Asgore", titleAsgoreStyle);
		titleAsgLab.setVisible(false);
		setTitleAsgoreLabel(titleAsgLab);
		
		//	Title AsgoreLabel 2
		Label titleAsgLab2 = new Label("Genocide Battle", titleAsgoreStyle);
		titleAsgLab2.setVisible(false);
		setTitleAsgoreLabel2(titleAsgLab2);
		
		//	Title Drop Sound
		Sound titleDrop = Gdx.audio.newSound(Gdx.files.internal("sounds/titleDrop.mp3"));
		setTitleDropSound(titleDrop);
		
		//	Selection Sound
		Sound selSound = Gdx.audio.newSound(Gdx.files.internal("sounds/snd_squeak.wav"));
		setSelectionSound(selSound);
		
		//	Select Sound
		Sound sel2Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/snd_select.wav"));
		setSelectSound(sel2Sound);
		
		//	Knife Slash Sound
		Sound kSound = Gdx.audio.newSound(Gdx.files.internal("sounds/snd_laz.wav"));
		setKnifeSlashSound(kSound);
		
		//	Menu Asgore Image
		Image asgoreIm = ImageLoader.loadImage("asgore/asgoreLookDown.png");
		asgoreIm.setVisible(false);
		setAsgoreImage(asgoreIm);
		
		//	Background 1
		Image background1 = ImageLoader.loadImage("title/titleBck1.png");
		background1.setVisible(false);
		setBackgroundImage1(background1);
		
		//	Background 2
		Image background2 = ImageLoader.loadImage("title/titleBck2.png");
		background2.setVisible(false);
		setBackgroundImage2(background2);
		
		//	Title Music
		Music tMusic = Gdx.audio.newMusic(Gdx.files.internal("music/anotherMedium.mp3"));
		tMusic.setPosition(200000f);
		setTitleMusic(tMusic);
		
		//	Title Options
		LabelStyle optionStyle = new LabelStyle(titleAsgoreFont, Color.WHITE);
		setTitleStartOptionLabel(new Label("Start", optionStyle));
		setTitleInstructionOptionLabel(new Label("Instruction", optionStyle));
		
		//	Instruction Labels
		LabelStyle instructionStyle = new LabelStyle(titleAsgoreFont, Color.GRAY);
		setInstructionLabel(new Label("---Instruction---", instructionStyle));
		setInstructionConfirmLabel(new Label("[Z] - Confirm", instructionStyle));
		setInstructionCancelLabel(new Label("[X] - Cancel", instructionStyle));
		setInstructionFullscreenLabel(new Label("[F4] - Fullscreen", instructionStyle));
		setInstructionQuitLabel(new Label("[Hold ESC] - Quit", instructionStyle));
		setInstructionLabel2(new Label("When HP is 0, you lose.", instructionStyle));
		setInstructionDoneLabel(new Label("Done", optionStyle));
		
		//	Knife animation
		PNGAnimatedMobileScreenObject kAnim = PNGAnimatedMSOLoader.loadPNGAnimatedMobileScreenObject(
				"knifeslash/", "knife", 6);
		kAnim.setFrameDelay(7);
		setKnifeAnimation(kAnim);
		
	}

	@Override
	public void setup() {
		
		setCounter(0);
		setStage(CounterConstants.TITLE);
		setSelection(CounterConstants.Menu.NONE);
		
		//	Set all Assets to invisible
		titleLabel.setVisible(false);
		titleInstructionLabel.setVisible(false);
		asgoreImage.setVisible(false);
		titleAsgoreLabel.setVisible(false);
		titleAsgoreLabel2.setVisible(false);
		titleStartOptionLabel.setVisible(false);
		titleInstructionOptionLabel.setVisible(false);
		instructionLabel.setVisible(false);
		instructionConfirmLabel.setVisible(false);
		instructionCancelLabel.setVisible(false);
		instructionFullscreenLabel.setVisible(false);
		instructionQuitLabel.setVisible(false);
		instructionLabel2.setVisible(false);
		instructionDoneLabel.setVisible(true);
		knifeAnimation.setVisible(false);
		knifeAnimation.resetFrameCount();
		
	}

	@Override
	public void processLogic() {
		
		//	Reset
		titleLabel.setVisible(false);
		titleInstructionLabel.setVisible(false);
		asgoreImage.setVisible(false);
		titleAsgoreLabel.setVisible(false);
		titleAsgoreLabel2.setVisible(false);
		titleStartOptionLabel.setVisible(false);
		titleInstructionOptionLabel.setVisible(false);
		backgroundImage1.setVisible(false);
		backgroundImage2.setVisible(false);
		instructionLabel.setVisible(false);
		instructionConfirmLabel.setVisible(false);
		instructionCancelLabel.setVisible(false);
		instructionFullscreenLabel.setVisible(false);
		instructionQuitLabel.setVisible(false);
		instructionLabel2.setVisible(false);
		instructionDoneLabel.setVisible(false);
		knifeAnimation.setVisible(false);
		
		switch (stage) {
		
		case CounterConstants.TITLE:
			
			//	Make sure music wasn't already playing.
			if (titleMusic.isPlaying()) titleMusic.stop();
			
			//	Sounds
			if (counter == CounterConstants.Title.TITLEDROP-20) titleDropSound.play(VolumeManager.volume);
			
			//	Labels
			if (counter > CounterConstants.Title.TITLEDROP && !titleLabel.isVisible()) {
				titleLabel.setVisible(true);
			}
			
			if (counter > CounterConstants.Title.INSTRUCTIONSHOW && 
					!titleInstructionLabel.isVisible()) {
				titleInstructionLabel.setVisible(true);
			}
			
			//	Wait for Z
			if (counter > CounterConstants.Title.TITLEDROP && PlayerInput.zWasPressed) {
				selectSound.play(VolumeManager.volume);
				stageChanged();
				setStage(CounterConstants.MENU);
				counter = 0;
				if (titleLabel.isVisible()) titleLabel.setVisible(false);
				if (titleInstructionLabel.isVisible()) titleInstructionLabel.setVisible(false);
			}
			
			break;
			
		case CounterConstants.MENU:
			
			//	Play Menu Music
			titleMusic.setVolume(VolumeManager.volume*.5f);
			if (!titleMusic.isPlaying()) titleMusic.play();
			
			if (selection == CounterConstants.Menu.NONE) selection = CounterConstants.Menu.START;
			
			if ((PlayerInput.aWasPressed || PlayerInput.leftWasPressed) && selection == CounterConstants.Menu.INSTRUCTION) {
				selectionSound.play(VolumeManager.volume);
				selection = CounterConstants.Menu.START;
			}
			
			if (PlayerInput.dWasPressed || PlayerInput.rightWasPressed && selection == CounterConstants.Menu.START) {
				selectionSound.play(VolumeManager.volume);
				selection = CounterConstants.Menu.INSTRUCTION;
			}
			
			if (PlayerInput.zWasPressed) {
				
				selectSound.play(VolumeManager.volume);
				
				if (selection == CounterConstants.Menu.START) {
					stageChanged();
					stage = CounterConstants.GAMESTART;
				}
				
				if (selection == CounterConstants.Menu.INSTRUCTION) {
					stageChanged();
					stage = CounterConstants.INSTRUCTION;
				}
				
				selection = CounterConstants.Menu.NONE;
			}
			
			if (PlayerInput.xWasPressed) {
				
				selectSound.play(VolumeManager.volume);
				stageChanged();
				stage = CounterConstants.TITLE;
				
			}
			
			//	Asgore Image
			asgoreImage.setVisible(true);
			
			//	Backgrounds
			backgroundImage1.setVisible(true);
			backgroundImage2.setVisible(true);
			
			//	Asgore Title Labels
			titleAsgoreLabel.setVisible(true);
			titleAsgoreLabel2.setVisible(true);
			
			//	Analyze Selection
			titleStartOptionLabel.setColor(Color.WHITE);
			titleInstructionOptionLabel.setColor(Color.WHITE);
			
			if (selection == CounterConstants.Menu.START) titleStartOptionLabel.setColor(Color.YELLOW);
			else if (selection == CounterConstants.Menu.INSTRUCTION) titleInstructionOptionLabel.setColor(Color.YELLOW);
			
			titleStartOptionLabel.setVisible(true);
			titleInstructionOptionLabel.setVisible(true);
			
			break;
			
		case CounterConstants.INSTRUCTION:
			
			if (selection != CounterConstants.Menu.NONE) selection = CounterConstants.Menu.NONE;
			
			if (PlayerInput.zWasPressed || PlayerInput.xWasPressed) {
				selectSound.play(VolumeManager.volume);
				stageChanged();
				stage = CounterConstants.MENU;
			}
			
			instructionDoneLabel.setColor(Color.YELLOW);
			
			//	Labels
			if (!instructionLabel.isVisible()) instructionLabel.setVisible(true);
			if (!instructionConfirmLabel.isVisible()) instructionConfirmLabel.setVisible(true);
			if (!instructionCancelLabel.isVisible()) instructionCancelLabel.setVisible(true);
			if (!instructionFullscreenLabel.isVisible()) instructionFullscreenLabel.setVisible(true);
			if (!instructionQuitLabel.isVisible()) instructionQuitLabel.setVisible(true);
			if (!instructionLabel2.isVisible()) instructionLabel2.setVisible(true);
			
			if (!instructionDoneLabel.isVisible()) instructionDoneLabel.setVisible(true);
			
			break;
			
		case CounterConstants.GAMESTART:
			
			if (counter == CounterConstants.GameStart.SLASHSOUND) {
				knifeSlashSound.play(VolumeManager.volume);
			}
			
			if (!knifeAnimation.isOver()) {
				knifeAnimation.play();
				knifeAnimation.setVisible(true);
			}
			
			if (counter == CounterConstants.GameStart.ENDSCENE) {
				stageChanged();
				stage = CounterConstants.TITLE;
			}
			
			break;
			
		}
		
		incrementCounter();
		
	}

	@Override
	public void draw(Batch batch, float alpha) {
		
		float screenWidth, screenHeight;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		if (screenWidth != camera.viewportWidth ||
				screenHeight != camera.viewportHeight) {
			camera.viewportWidth = screenWidth;
			camera.viewportHeight = screenHeight;
			camera.position.x = 0f;
			camera.position.y = 0f;
			camera.translate(screenWidth/2, screenHeight/2, 0f);
		}
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		if (!batch.isDrawing()) batch.begin();
		
		//	Title Label
		if (titleLabel.isVisible()) {
			titleLabel.setPosition(screenWidth/2, screenHeight/2);
			titleLabel.setFontScale(screenWidth/640 * 1.5f, screenHeight/480 *1.5f);
			titleLabel.moveBy(-(titleLabel.getWidth()*titleLabel.getFontScaleX())/2, 0);
			titleLabel.draw(batch, alpha);
		}
		
		if (titleInstructionLabel.isVisible()) {
			titleInstructionLabel.setPosition(screenWidth/2, screenHeight/4);
			titleInstructionLabel.setFontScale(screenWidth/640 * .5f, screenHeight/480 * .5f);
			titleInstructionLabel.moveBy(-(titleInstructionLabel.getWidth()*titleInstructionLabel.getFontScaleX())/2, -(titleInstructionLabel.getHeight()*titleInstructionLabel.getFontScaleY())/2);
			titleInstructionLabel.draw(batch, alpha);
		}
		
		//	Backgrounds
		if (backgroundImage1.isVisible()) {
			backgroundImage1.setPosition(screenWidth/2, screenHeight/2 - screenHeight/3.8f);
			backgroundImage1.setScale(screenWidth/640 * 2f, screenHeight/480 * 2f);
			backgroundImage1.moveBy(-backgroundImage1.getWidth()*backgroundImage1.getScaleX()/2, -backgroundImage1.getHeight()*backgroundImage1.getScaleY()/2);
			backgroundImage1.draw(batch, alpha*.9f);
		}
		
		if (backgroundImage2.isVisible()) {
			backgroundImage2.setPosition(screenWidth/2, screenHeight*(.98f));
			backgroundImage2.setScale(screenWidth/640 * 2f, screenHeight/480 * 2f);
			backgroundImage2.moveBy(-backgroundImage2.getWidth()*backgroundImage2.getScaleX()/2, -backgroundImage2.getHeight()*backgroundImage2.getScaleY()/2);
			backgroundImage2.draw(batch, alpha*.6f);
		}
		
		//	Asgore Image
		if (asgoreImage.isVisible()) {
			asgoreImage.setPosition(screenWidth/2, screenHeight/2 - screenHeight/12);
			asgoreImage.setScale(screenWidth/640 * 1.5f, screenHeight/480 * 1.5f);
			asgoreImage.moveBy(-(asgoreImage.getWidth()*asgoreImage.getScaleX())/2, -(asgoreImage.getHeight()*asgoreImage.getScaleY())/2);
			asgoreImage.draw(batch, alpha);
		}
		
		//	Asgore Title 1
		if (titleAsgoreLabel.isVisible()) {
			titleAsgoreLabel.setPosition(screenWidth/2, screenHeight*(0.9f));
			titleAsgoreLabel.setFontScale(screenWidth/640 * 1.5f, screenHeight/480 * 1.5f);
			titleAsgoreLabel.moveBy(-(titleAsgoreLabel.getWidth()*titleAsgoreLabel.getFontScaleX())/2, -(titleAsgoreLabel.getHeight()*titleAsgoreLabel.getFontScaleY())/2);
			titleAsgoreLabel.draw(batch, alpha);
		}
		
		//	Asgore Title 2
		if (titleAsgoreLabel2.isVisible()) {
			titleAsgoreLabel2.setPosition(screenWidth/2, titleAsgoreLabel.getY()-(titleAsgoreLabel.getHeight()*titleAsgoreLabel.getFontScaleX())/2);
			titleAsgoreLabel2.setFontScale(screenWidth/640 * 1.5f, screenHeight/480 * 1.5f);
			titleAsgoreLabel2.moveBy(-(titleAsgoreLabel2.getWidth()*titleAsgoreLabel2.getFontScaleX())/2, -(titleAsgoreLabel2.getHeight()*titleAsgoreLabel2.getFontScaleY())/2);
			titleAsgoreLabel2.draw(batch, alpha);
		}
		
		float optFontX = screenWidth/640 * 1f;
		float optFontY = screenHeight/480 * 1f;
		
		//	Title Options
		if (titleStartOptionLabel.isVisible()) {
			titleStartOptionLabel.setPosition(screenWidth/4, screenHeight/8);
			titleStartOptionLabel.setFontScale(optFontX, optFontY);
			titleStartOptionLabel.moveBy(-titleStartOptionLabel.getWidth()*titleStartOptionLabel.getFontScaleX()/2, -titleStartOptionLabel.getHeight()*titleStartOptionLabel.getFontScaleY()/2);
			titleStartOptionLabel.draw(batch, alpha);
		}
		
		if (titleInstructionOptionLabel.isVisible()) {
			titleInstructionOptionLabel.setPosition((3f/4f)*screenWidth, screenHeight/8);
			titleInstructionOptionLabel.setFontScale(optFontX, optFontY);
			titleInstructionOptionLabel.moveBy(-titleInstructionOptionLabel.getWidth()*titleInstructionOptionLabel.getFontScaleX()/2, -titleInstructionOptionLabel.getHeight()*titleInstructionOptionLabel.getFontScaleY()/2);
			titleInstructionOptionLabel.draw(batch, alpha);
		}
		
		//	Instruction Label:
		
		float insFontX = screenWidth/640 * 1f;
		float insFontY = screenHeight/480 * 1f;
		float insGap = -instructionConfirmLabel.getHeight()*insFontY;
		
		if (instructionLabel.isVisible()) {
			instructionLabel.setPosition(screenWidth/2, (7f/8f)*screenHeight);
			instructionLabel.setFontScale(screenWidth/640 * 1f, screenHeight/480 * 1f);
			instructionLabel.moveBy(-instructionLabel.getWidth()*instructionLabel.getFontScaleX()/2, -instructionLabel.getHeight()*instructionLabel.getFontScaleY()/2);
			instructionLabel.draw(batch, alpha);
		}
		
		if (instructionConfirmLabel.isVisible()) {
			instructionConfirmLabel.setPosition(instructionLabel.getX(), instructionLabel.getY() + insGap);
			instructionConfirmLabel.setFontScale(insFontX, insFontY);
			instructionConfirmLabel.moveBy(-0, -instructionConfirmLabel.getHeight()*instructionConfirmLabel.getFontScaleY()/2);
			instructionConfirmLabel.draw(batch, alpha);
		}
		
		if (instructionCancelLabel.isVisible()) {
			instructionCancelLabel.setPosition(instructionLabel.getX(), instructionConfirmLabel.getY() + insGap);
			instructionCancelLabel.setFontScale(insFontX, insFontY);
			instructionCancelLabel.moveBy(0, -instructionCancelLabel.getHeight()*instructionCancelLabel.getFontScaleY()/2);
			instructionCancelLabel.draw(batch, alpha);
		}
		
		if (instructionFullscreenLabel.isVisible()) {
			instructionFullscreenLabel.setPosition(instructionLabel.getX(), instructionCancelLabel.getY() + insGap);
			instructionFullscreenLabel.setFontScale(insFontX, insFontY);
			instructionFullscreenLabel.moveBy(0, -instructionFullscreenLabel.getHeight()*instructionFullscreenLabel.getFontScaleY()/2);
			instructionFullscreenLabel.draw(batch, alpha);
		}
		
		if (instructionQuitLabel.isVisible()) {
			instructionQuitLabel.setPosition(instructionLabel.getX(), instructionFullscreenLabel.getY() + insGap);
			instructionQuitLabel.setFontScale(insFontX, insFontY);
			instructionQuitLabel.moveBy(0, -instructionQuitLabel.getHeight()*instructionQuitLabel.getFontScaleY()/2);
			instructionQuitLabel.draw(batch, alpha);
		}
		
		if (instructionLabel2.isVisible()) {
			instructionLabel2.setPosition(instructionLabel.getX(), instructionQuitLabel.getY() + insGap);
			instructionLabel2.setFontScale(insFontX, insFontY);
			instructionLabel2.moveBy(0, -instructionLabel2.getHeight()*instructionLabel2.getFontScaleY()/2);
			instructionLabel2.draw(batch, alpha);
		}
		
		if (instructionDoneLabel.isVisible()) {
			instructionDoneLabel.setPosition(screenWidth/2, screenHeight/8);
			instructionDoneLabel.setFontScale(screenWidth/640 * 1f, screenHeight/480 * 1f);
			instructionDoneLabel.moveBy(-instructionDoneLabel.getWidth()*instructionDoneLabel.getFontScaleX()/2, -instructionDoneLabel.getHeight()*instructionDoneLabel.getFontScaleY()/2);
			instructionDoneLabel.draw(batch, alpha);
		}
		
		if (knifeAnimation.isVisible()) {
			knifeAnimation.setPosition(screenWidth/2, screenHeight/2);
			knifeAnimation.setScale(screenWidth/640 * 5f, screenHeight/480 * 5f);
			knifeAnimation.moveBy(-knifeAnimation.getWidth()*knifeAnimation.getScaleX()/2, -knifeAnimation.getHeight()*knifeAnimation.getScaleX()/2);
			knifeAnimation.draw(batch, alpha);
		}
		
	}
	
	@Override
	public void stop() {
		
		if (titleMusic.isPlaying()) titleMusic.stop();
		
	}
	
	@Override
	public void incrementCounter() { ++counter; }
	
	///
	///	Title Scene Constants
	///
	
	public final class CounterConstants {
		
		//	Stages
		public static final int TITLE = 0;
		public static final int MENU = 1;
		public static final int INSTRUCTION = 2;
		public static final int GAMESTART = 3;
		
		public final class Title {
			
			public static final int TITLEDROP = 100;
			public static final int INSTRUCTIONSHOW = 300;
			
		}
		
		public final class Menu {
			
			public static final int NONE = 0;
			public static final int START = 1;
			public static final int INSTRUCTION = 2;
			
		}
		
		public final class Instruction {
			
		}
		
		public final class GameStart {
			
			public static final int SLASHSOUND = 1;
			public static final int ENDSCENE = 300;
			
		}
		
		
	}
	
	public void stageChanged() {
		
		counter = 0;
		knifeAnimation.resetFrameCount();
		//	Make sure music wasn't already playing.
		if (titleMusic.isPlaying()) titleMusic.stop();
		
	}
	
	
	///
	///	Constructors
	///
	
	/**
	 * 
	 */
	public TitlescreenScene() {
		
		initalize();
		
	}
	
	///
	///	Destructors
	///
	
	@Override
	public void dispose() {
		
		//	Dispose
		titleDropSound.dispose();
		selectionSound.dispose();
		selectSound.dispose();
		knifeSlashSound.dispose();
		titleMusic.dispose();
		asgoreImage.dispose();
		backgroundImage1.dispose();
		backgroundImage2.dispose();
		knifeAnimation.dispose();
		
		//	Nullify
		titleDropSound = null;
		selectionSound = null;
		selectSound = null;
		knifeSlashSound = null;
		titleMusic = null;
		asgoreImage = null;
		backgroundImage1 = null;
		backgroundImage2 = null;
		knifeAnimation = null;
		
	}
	
}