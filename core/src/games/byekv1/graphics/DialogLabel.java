/**
 * @author	byekv1
 */
package games.byekv1.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import games.byekv1.output.VolumeManager;

/**
 * @author byekv
 *
 */
public class DialogLabel extends Label {
	
	public final static char[] letters = { 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'	
	};
	
	public static LabelStyle style = new LabelStyle(
			new BitmapFont(Gdx.files.internal("fonts/undertale.fnt")), Color.WHITE
			);
	
	public static Sound asgoreDialogSound = Gdx.audio.newSound(Gdx.files.internal("sounds/snd_txtasg.wav"));
	public static Sound genericDialogSound = Gdx.audio.newSound(Gdx.files.internal("sounds/SND_TXT1.wav"));
	
	///
	///	Fields
	///
	
	private int counter;
	private int letterDropDelay;
	private int letterCount;
	private int countLength;
	private String dialogLine;
	private Sound textSound;
	private Label fullLabel;
	
	///
	///	Getters
	///
	
	public Label getFullLabel() { return fullLabel; }
	public int getCounter() { return counter; }
	public int getLetterDropDelay() { return letterDropDelay; }
	public int getCountLength() { return countLength; }
	
	///
	///	Setters
	///
	
	public void setCounter(int counter) { this.counter = counter; }
	public void setLetterDropDelay(int letterDropDelay) { this.letterDropDelay = letterDropDelay; }
	
	///
	///	Functions
	///
	
	public boolean isDone() { return (letterCount == countLength) && counter > countLength*letterDropDelay + 10*letterDropDelay; }
	
	public void play() {
		
		if (counter % letterDropDelay == 0 && letterCount < countLength) {
			
			String newString;
			
			char c = dialogLine.charAt(letterCount);
			
			newString = getText().substring(0, letterCount+2) + c + getText().substring(letterCount+3);
			
			if (textSound != null && isLetter(c)) textSound.play(VolumeManager.volume);
			setText(newString); 
			++letterCount;
			
		}
		
		++counter;
		
	}
	
	///
	///	Helpers
	///
	
	private boolean isLetter(char c) {
		
		for (char letter : letters) {
			if (c == letter) return true;
		}
		
		return false;
		
	}
	
	
	
	///
	///	Constructors
	///
	
	/**
	 * 
	 */
	public DialogLabel(String dialogLine, Sound textSound, int letterDropDelay) {
		
		super("* ", style);
		fullLabel = new Label("* " + dialogLine, style);
		this.dialogLine = dialogLine;
		countLength = dialogLine.length();
		letterCount = 0;
		this.textSound = textSound;
		
		setLetterDropDelay(letterDropDelay);
		
		String emptyDialogLine = "* ";
		for (int i = 0; i<dialogLine.length(); ++i) {
			if (!(dialogLine.charAt(i) == '\n'))emptyDialogLine += " ";
			else emptyDialogLine += '\n';
		}
		setText(emptyDialogLine);
		
		
	}

}
