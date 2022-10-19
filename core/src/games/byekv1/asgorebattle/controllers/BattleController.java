package games.byekv1.asgorebattle.controllers;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import games.byekv1.asgorebattle.scenes.BattleScene;
import games.byekv1.graphics.AsgoreAttack;
import games.byekv1.graphics.AsgoreAttackFactory;
import games.byekv1.graphics.BattleGUI;
import games.byekv1.graphics.HealthBar;
import games.byekv1.graphics.HollowBox;
import games.byekv1.input.PlayerInput;
import games.byekv1.output.VolumeManager;

public class BattleController {

    ///
    /// Fields
    ///

    private int turn;
    private int selection;
    private BattleGUI battleGUI;
    private long internalTimeRecord;
    private Random random;
    public AsgoreAttack currentAsgoreAttack;

    ///
    /// Assets
    ///
    public Sound knifeSwing;
    public Sound asgoreDamage;
    public HealthBar asgoreHealthBar;
    public HealthBar playerHealthBar;
    private Label damageLabel;

    ///
    /// Getters
    ///

    public int getTurn() { return turn; }
    public int getSelection() { return selection; }
    public BattleGUI getBattleGUI() { return battleGUI; }

    ///
    /// Setters
    ///

    public void setTurn(int turn) { this.turn = turn; }
    public void setSelection(int selection) { this.selection = selection; }
    public void setBattleGUI(BattleGUI battleGUI) { this.battleGUI = battleGUI; }
    public void setInternalTimeRecord(long timeMS) { internalTimeRecord = timeMS; }
    public void setKnifeSwing(Sound knifeSwing) { this.knifeSwing = knifeSwing; }
    public void setAsgoreDamage(Sound asgoreDamage) { this.asgoreDamage = asgoreDamage; }
    public void setAsgoreHealthBar(HealthBar asgoreHealthBar) { this.asgoreHealthBar = asgoreHealthBar; }
    public void setPlayerHealthBar(HealthBar playerHealthBar) { this.playerHealthBar = playerHealthBar; }
    public void setDamageLabel(Label damageLabel) { this.damageLabel = damageLabel; }

    ///
    /// Functions
    ///

    public void processLogic() {

        switch (turn) {
            case BattleControllerConstants.PLAYER_TURN:
                if (PlayerInput.zWasPressed) {
                    if (selection == BattleControllerConstants.FIGHT) {
                        setTurn(BattleControllerConstants.PLAYER_TURN_FIGHT);
                        setSelection(BattleControllerConstants.NONE);
                        setInternalTimeRecord(0);
                    }
                    else {
                        setTurn(BattleControllerConstants.PLAYER_TURN_ITEM);
                        setSelection(BattleControllerConstants.NONE);
                    }
                }
                else if (PlayerInput.aWasPressed || PlayerInput.leftWasPressed) {
                    if (selection == BattleControllerConstants.ITEM) {
                        setSelection(BattleControllerConstants.FIGHT);
                    }
                }
                else if (PlayerInput.dWasPressed || PlayerInput.rightWasPressed) {
                    if (selection == BattleControllerConstants.FIGHT) {
                        setSelection(BattleControllerConstants.ITEM);
                    }
                }
                break;

            case BattleControllerConstants.PLAYER_TURN_FIGHT:
                if (internalTimeRecord == 0) {
                    knifeSwing.play(VolumeManager.volume);
                    setInternalTimeRecord(System.currentTimeMillis());
                }
                else if (System.currentTimeMillis()-internalTimeRecord>700) {
                    setTurn(BattleControllerConstants.PLAYER_TURN_FIGHT_1);
                }
                break;
            case BattleControllerConstants.PLAYER_TURN_FIGHT_1:
                asgoreDamage.play(VolumeManager.volume);
                setTurn(BattleControllerConstants.PLAYER_TURN_FIGHT_2);
                setInternalTimeRecord(0);
                break;
            case BattleControllerConstants.PLAYER_TURN_FIGHT_2:
                asgoreHealthBar.setVisible(true);
                damageLabel.setVisible(true);
                if (internalTimeRecord == 0) {
                    setInternalTimeRecord(System.currentTimeMillis());
                    asgoreHealthBar.setHealthPoints(asgoreHealthBar.getHealthPoints()-10); 
                }
                else if (System.currentTimeMillis()-internalTimeRecord>2000) {
                    setTurn(BattleControllerConstants.ASGORE_TURN_SELECT);
                }
                break;
            case BattleControllerConstants.ASGORE_TURN_SELECT:
                int attackID = random.nextInt(2)+1;
                currentAsgoreAttack = AsgoreAttackFactory.getAttack(attackID);
                setTurn(BattleControllerConstants.ASGORE_TURN);
                break;
            case BattleControllerConstants.ASGORE_TURN:
                if (!currentAsgoreAttack.isOver()) currentAsgoreAttack.update();
                else setTurn(BattleControllerConstants.ASGORE_TURN_OVER);
                break;
            case BattleControllerConstants.ASGORE_TURN_OVER:
                System.out.println("Attack Over");
                HollowBox hollowBox = AsgoreAttackFactory.hollowBox;

                boolean widthControlled = false;
                boolean heightControlled= false;

                //  Shrink back to normal
                if (currentAsgoreAttack.getBoxWidth() > BattleScene.D_DEFAULT_W) {
                    if (hollowBox.getWidth() >= BattleScene.D_DEFAULT_W+1) {
                        hollowBox.shrinkCentered(BattleScene.BOX_RATE, 0);
                    }
                    else widthControlled = true;
                }
                //  Grow back to normal
                else {
                    if (hollowBox.getWidth() <= BattleScene.D_DEFAULT_W) {
                        hollowBox.growCentered(BattleScene.BOX_RATE, 0);
                    }
                    else widthControlled = true;
                }

                //  Shrink back to normal
                if (currentAsgoreAttack.getBoxHeight() > BattleScene.D_DEFAULT_H) {
                    if (hollowBox.getHeight() >= BattleScene.D_DEFAULT_H+1) {
                        hollowBox.shrinkFromBottomY(BattleScene.BOX_RATE);
                    }
                    else heightControlled = true;
                }
                //  Grow back to normal
                else {
                    if (hollowBox.getHeight() <= BattleScene.D_DEFAULT_H) {
                        hollowBox.growFromBottomY(BattleScene.BOX_RATE);
                    }
                    else heightControlled = true;
                }

                /*
                if (hollowBox.getWidth() < BattleScene.D_DEFAULT_W) hollowBox.growCentered(3, 0);
                if (hollowBox.getHeight() < BattleScene.D_DEFAULT_H) hollowBox.growFromBottomY(3);
                */

                if (widthControlled && heightControlled) {
                    hollowBox.setBounds(battleGUI.getX(), battleGUI.getY()+battleGUI.getHeight()*.6f, BattleScene.D_DEFAULT_W, BattleScene.D_DEFAULT_H);
                    setTurn(BattleControllerConstants.FIGHT);
                    setSelection(BattleControllerConstants.FIGHT);
                }
                break;

        }
    }

    public void initialize() {
        setTurn(BattleControllerConstants.PLAYER_TURN);
        setSelection(BattleControllerConstants.FIGHT);
        setBattleGUI(null);
        setInternalTimeRecord(System.currentTimeMillis());
        setKnifeSwing(Gdx.audio.newSound(Gdx.files.internal("sounds/snd_laz.wav")));
        setAsgoreDamage(Gdx.audio.newSound(Gdx.files.internal("sounds/snd_hdmg.ogg")));
        random = new Random();
    }

    ///
    /// Constructor
    ///

    public BattleController() {
        initialize();
    }

    ///
    /// BattleController Constants
    ///

    public class BattleControllerConstants {

        //  Stages
        public static final int NON_TURN = 0;
        public static final int PLAYER_TURN = 1;
        public static final int PLAYER_TURN_FIGHT = 2;
        public static final int PLAYER_TURN_FIGHT_1 = 3;
        public static final int PLAYER_TURN_FIGHT_2 = 4;
        public static final int PLAYER_TURN_ITEM = 5;
        public static final int ASGORE_TURN_SELECT = 8;
        public static final int ASGORE_TURN = 10;
        public static final int ASGORE_TURN_OVER = 15;
        public static final int GAME_OVER = 50;

        //  Selection
        public final static int NONE = 0;
		public final static int FIGHT = 1;
		public final static int ACT = 2; 
		public final static int ITEM = 3;
		public final static int MERCY = 4;

    }
    
}
