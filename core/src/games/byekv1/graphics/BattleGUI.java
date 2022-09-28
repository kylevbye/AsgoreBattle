/**
 * @author	byekv1
 */
package games.byekv1.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import games.byekv1.graphics.graphicloaders.ImageLoader;

/**
 * @author byekv
 *
 */
public class BattleGUI extends Actor {
	
	///
	///	Fields
	///
	
	private boolean isScaling;
	private int selection;
	private TwoSidedImage fightOption;
	private TwoSidedImage actOption;
	private TwoSidedImage itemOption;
	private TwoSidedImage mercyOption;
	
	///
	///	Getters
	///
	
	public boolean isScaling() { return isScaling; }
	public int getSelection() { return selection; }
	public TwoSidedImage getFightOption() { return fightOption; }
	public TwoSidedImage getActOption() { return actOption; }
	public TwoSidedImage getItemOption() { return itemOption; }
	public TwoSidedImage getMercyOption() { return mercyOption; }
	
	///
	///	Setters
	///
	
	public void setScaling(boolean isScaling) { this.isScaling = isScaling; }
	public void setSelection(int selection) { this.selection = selection; }
	public void setFightOption(TwoSidedImage fightOption) { this.fightOption = fightOption; }
	public void setActOption(TwoSidedImage actOption) { this.actOption = actOption; }
	public void setItemOption(TwoSidedImage itemOption) { this.itemOption = itemOption; }
	public void setMercyOption(TwoSidedImage mercyOption) { this.mercyOption = mercyOption; }
	
	///
	///	Functions
	///
	
	public void activate() { setSelection(findLeftMostOption()); }
	
	public void deactivate() { setSelection(BattleGUIConstants.NONE); }
	
	public void left() { processSelection(BattleGUIConstants.LEFT); }
	
	public void right() { processSelection(BattleGUIConstants.RIGHT); }
	
	public void processSelection(int direction) {
		
		int activeOptionCount = 0;
		
		if (selection == BattleGUIConstants.NONE) return;
		
		if (fightOption.isVisible()) ++activeOptionCount;
		if (actOption.isVisible()) ++activeOptionCount;
		if (itemOption.isVisible()) ++activeOptionCount;
		if (mercyOption.isVisible()) ++activeOptionCount;
		
		int[] activeOptions = new int[activeOptionCount];
		int aoCount = 0;
		
		if (fightOption.isVisible()) {
			activeOptions[aoCount] = BattleGUIConstants.FIGHT;
			++aoCount;
		}
		if (actOption.isVisible()) {
			activeOptions[aoCount] = BattleGUIConstants.ACT;
			++aoCount;
		}
		if (itemOption.isVisible()) {
			activeOptions[aoCount] = BattleGUIConstants.ITEM;
			++aoCount;
		}
		if (mercyOption.isVisible()) {
			activeOptions[aoCount] = BattleGUIConstants.MERCY;
			++aoCount;
		}
		
		int selectionIndex = -1;
		
		for (int i = 0; i<activeOptions.length; ++i) {
			if (selection == activeOptions[i]) selectionIndex = i;
		}
		
		if (direction == BattleGUIConstants.LEFT) {
			
			selectionIndex += BattleGUIConstants.LEFT;
			
		}
		else if (direction == BattleGUIConstants.RIGHT) {
			
			selectionIndex += BattleGUIConstants.RIGHT;
			
		}

		if (selectionIndex < 0 || selectionIndex >= activeOptions.length) return;
		selection = activeOptions[selectionIndex];
		
	}
	
	///
	///	Actor Functions
	///
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		placeComponents();
		
		batch.enableBlending();
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		fightOption.setFlipped(false);
		actOption.setFlipped(false);
		itemOption.setFlipped(false);
		mercyOption.setFlipped(false);
		if (selection == BattleGUIConstants.FIGHT) fightOption.setFlipped(true);
		if (selection == BattleGUIConstants.ACT) actOption.setFlipped(true);
		if (selection == BattleGUIConstants.ITEM) itemOption.setFlipped(true);
		if (selection == BattleGUIConstants.MERCY) mercyOption.setFlipped(true);
		
		if (fightOption.isVisible()) fightOption.draw(batch, color.a);
		if (actOption.isVisible()) actOption.draw(batch, color.a);
		if (itemOption.isVisible()) itemOption.draw(batch, color.a);
		if (mercyOption.isVisible()) mercyOption.draw(batch, color.a);
		
	}
	
	///
	///	BattleGUI Selection Constants
	///
	
	public class BattleGUIConstants {
		
		//	Selection
		public final static int NONE = 0;
		public final static int FIGHT = 1;
		public final static int ACT = 2; 
		public final static int ITEM = 3;
		public final static int MERCY = 4;
		
		//	Direction
		public final static int LEFT = -1;
		public final static int RIGHT = 1;
		
	}
	
	///
	///	Helpers
	///
	
	private int findLeftMostOption() {
		
		if (fightOption.isVisible()) return BattleGUIConstants.FIGHT;
		if (actOption.isVisible()) return BattleGUIConstants.ACT;
		if (itemOption.isVisible()) return BattleGUIConstants.ITEM;
		if (mercyOption.isVisible()) return BattleGUIConstants.MERCY;
		
		return BattleGUIConstants.NONE;
		
	}
	
	private int findRightMostOption() {
		
		if (mercyOption.isVisible()) return BattleGUIConstants.MERCY;
		if (itemOption.isVisible()) return BattleGUIConstants.ITEM;
		if (actOption.isVisible()) return BattleGUIConstants.ACT;
		if (fightOption.isVisible()) return BattleGUIConstants.FIGHT;
		
		return BattleGUIConstants.NONE;
		
	}
	
	private void placeComponents() {
		
		float compW;
		
		float x, y, width, height, gap;
		x = getX(); y = getY();
		width = getWidth(); height = getHeight();
		
		//	Calc Gap
		int activeComponents = 0;
		if (fightOption.isVisible()) ++activeComponents;
		if (actOption.isVisible()) ++activeComponents;
		if (itemOption.isVisible()) ++activeComponents;
		if (mercyOption.isVisible()) ++activeComponents;
		
		if (isScaling) {
			compW = (width-(width/(float)(activeComponents+1)))/(float)activeComponents;
			gap = (width - ((float)activeComponents)*compW)/(float)(activeComponents-1);
		}
		else {
			compW = (width-(width/(float)(4+1)))/(float)4;
			gap = (width - ((float)4)*compW)/(float)(4-1);
		}
		
		float[] xPositions = new float[activeComponents];
		
		for (int i = 0; i<xPositions.length; ++i) {
			xPositions[i] = x + i*compW + i*gap;
		}
		
		int placedX = activeComponents;
		
		fightOption.setWidth(compW);
		actOption.setWidth(compW);
		itemOption.setWidth(compW);
		mercyOption.setWidth(compW);
		
		//	Fight
		if (fightOption.isVisible()) {
			if (isScaling) fightOption.setPosition(xPositions[activeComponents-placedX], y);
			else fightOption.setPosition(x, y);
			--placedX;
		}
		
		//	Act
		if (actOption.isVisible()) {
			if (isScaling) actOption.setPosition(xPositions[activeComponents-placedX], y);
			else actOption.setPosition(fightOption.getX()+fightOption.getWidth()+gap, y);
			--placedX;
		}
		
		//	Item
		if (itemOption.isVisible()) {
			if (isScaling) itemOption.setPosition(xPositions[activeComponents-placedX], y);
			else itemOption.setPosition(actOption.getX()+actOption.getWidth()+gap, y);
			--placedX;
		}
		
		//	Mercy
		if (mercyOption.isVisible()) {
			if (isScaling) mercyOption.setPosition(xPositions[activeComponents-placedX], y);
			else mercyOption.setPosition(itemOption.getX()+mercyOption.getWidth()+gap, y);
			--placedX;
		}
		
	}
	
	private void loadOptions() {
		
		//	Fight
		Image fightButtonNH = ImageLoader.loadImage("battle/fight.png");
		Image fightButtonH = ImageLoader.loadImage("battle/fightH.png");
		TwoSidedImage fightButton = new TwoSidedImage(fightButtonNH, fightButtonH, true);
		setFightOption(fightButton);
		
		//	Act
		Image actButtonNH = ImageLoader.loadImage("battle/act.png");
		Image actButtonH = ImageLoader.loadImage("battle/actH.png");
		TwoSidedImage actButton = new TwoSidedImage(actButtonNH, actButtonH, true);
		setActOption(actButton);
		
		//	Item
		Image itemButtonNH = ImageLoader.loadImage("battle/item.png");
		Image itemButtonH = ImageLoader.loadImage("battle/itemH.png");
		TwoSidedImage itemButton = new TwoSidedImage(itemButtonNH, itemButtonH, true);
		setItemOption(itemButton);
		
		//	Mercy
		Image mercyButtonNH = ImageLoader.loadImage("battle/mercy.png");
		Image mercyButtonH = ImageLoader.loadImage("battle/mercyH.png");
		TwoSidedImage mercyButton = new TwoSidedImage(mercyButtonNH, mercyButtonH, true);
		setMercyOption(mercyButton);
		
	}
	
	///
	///	Constructors
	///

	/**
	 * 
	 */
	public BattleGUI() {
		
		setSelection(BattleGUIConstants.NONE);
		setScaling(true);
		loadOptions();
		
	}

}
