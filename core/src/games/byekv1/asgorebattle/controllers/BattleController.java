package games.byekv1.asgorebattle.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import games.byekv1.graphics.BattleGUI;
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

    ///
    /// Assets
    ///
    public Sound knifeSwing;
    public Sound asgoreDamage;

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
                if (internalTimeRecord == 0) setInternalTimeRecord(System.currentTimeMillis());
                else if (System.currentTimeMillis()-internalTimeRecord>2000) {
                    setTurn(BattleControllerConstants.PLAYER_TURN);
                    setSelection(BattleControllerConstants.FIGHT);
                }
                break;
            case BattleControllerConstants.ASGORE_TURN:
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
        public static final int ASGORE_TURN = 10;
        public static final int GAME_OVER = 50;

        //  Selection
        public final static int NONE = 0;
		public final static int FIGHT = 1;
		public final static int ACT = 2; 
		public final static int ITEM = 3;
		public final static int MERCY = 4;

    }
    
}
