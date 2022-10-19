package games.byekv1.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import games.byekv1.asgorebattle.scenes.BattleScene;
import games.byekv1.input.PlayerInput;

public abstract class AsgoreAttack {

    ///
    /// CONSTANTS
    ///

    public static final int DETERMINATION = 0;

    ///
    /// Fields
    ///

    private int counter;
    private int duration;
    private int boxWidth;
    private int boxHeight;
    private boolean boxShrinked;
    private ArrayList<MobileScreenObject> attackParts;
    private int type;

    ///
    /// Getters
    ///

    public int getCounter() { return counter; }
    public int getDuration() { return duration; }
    public int getBoxWidth() { return boxWidth; }
    public int getBoxHeight() { return boxHeight; }
    public boolean boxShrinked() { return boxShrinked; }
    public ArrayList<MobileScreenObject> getAttackParts() { return attackParts; }
    public int getType() { return type; }

    ///
    /// Setters
    ///

    public void setCounter(int counter) { this.counter = counter; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setBoxWidth(int boxWidth) { this.boxWidth = boxWidth; }
    public void setBoxHeight(int boxHeight) { this.boxHeight = boxHeight; }
    public void setBoxShrinked(boolean boxShrinkedFlag) { this.boxShrinked = boxShrinkedFlag; }
    public void setAttackParts(ArrayList<MobileScreenObject> attackParts) { 
        this.attackParts = attackParts; 
    }
    public void setType(int type) { this.type = type; }

    ///
    /// Functions
    ///

    public void prepare() {
        setCounter(0);
        setBoxShrinked(false);
        AsgoreAttackFactory.soul.setPosition(((float)BattleScene.WORLD_WIDTH*.5f)+AsgoreAttackFactory.soul.getWidth()*.5f,AsgoreAttackFactory.hollowBox.getY()+BattleScene.D_DEFAULT_H*.5f-AsgoreAttackFactory.soul.getHeight()*.5f, 500);
        AsgoreAttackFactory.soul.setVisible(false);
        AsgoreAttackFactory.playerImmune = false;
        AsgoreAttackFactory.playerImmuneCount = 0;
    }

    public boolean isOver() { return counter >= duration; }

    public void update() {

        HollowBox hollowBox = AsgoreAttackFactory.hollowBox;
        boolean widthChanged, heightChanged;
        widthChanged = false;
        heightChanged = false;

        //  Grow Width
        if (getBoxWidth() > BattleScene.D_DEFAULT_W) {
            if (hollowBox.getWidth() < getBoxWidth()) {
                hollowBox.growCentered(BattleScene.BOX_RATE, 0);
            }
            else widthChanged = true;
        }
        //  Shrink Width
        else {
            if (hollowBox.getWidth() > getBoxWidth()) {
                hollowBox.shrinkCentered(BattleScene.BOX_RATE, 0);
            }
            else widthChanged = true;
        }

        //  Grow Height
        if (getBoxHeight() > BattleScene.D_DEFAULT_H) {
            if (hollowBox.getHeight() < getBoxHeight()) {
                hollowBox.growFromBottomY(BattleScene.BOX_RATE);
            }
            else heightChanged = true;
        }
        //  Shrink Height
        else {
            if (hollowBox.getHeight() > getBoxHeight()) {
                hollowBox.shrinkFromBottomY(BattleScene.BOX_RATE);
            }
            else heightChanged = true;
        }

        if (widthChanged && heightChanged) {
            boxShrinked = true;
            AsgoreAttackFactory.soul.setVisible(true);
        }

        //  Controls
        MobileScreenObject soul = AsgoreAttackFactory.soul;
        switch (type) {
            case DETERMINATION:
                float dx = 0; float dy = 0;
                float moveRate = 3.f;
                if (PlayerInput.shift_down) moveRate = 1.f;
                if (PlayerInput.w_down || PlayerInput.up_down) dy += moveRate;
                if (PlayerInput.a_down || PlayerInput.left_down) dx -= moveRate;
                if (PlayerInput.s_down || PlayerInput.down_down) dy -= moveRate;
                if (PlayerInput.d_down || PlayerInput.right_down) dx += moveRate;
                soul.moveBy(dx, dy);
                break;
        }

        //  Soul Containment
        //+hollowBox.getBorderLength()
        float offset = hollowBox.getBorderLength();//soul.getWidth()*.1f;
        if (soul.getX() < hollowBox.getX()+offset) {
            soul.setX(hollowBox.getX()+offset);
        }
        else if (soul.getX() > hollowBox.getX()+hollowBox.getWidth()-soul.getWidth()-offset) {
            soul.setX(hollowBox.getX()+hollowBox.getWidth()-soul.getWidth()-offset);
        }
        if (soul.getY() < hollowBox.getY()+offset) {
            soul.setY(hollowBox.getY()+offset);
        }
        else if (soul.getY() > hollowBox.getY()+hollowBox.getHeight()-soul.getHeight()-offset) {
            soul.setY(hollowBox.getY()+hollowBox.getHeight()-soul.getHeight()-offset);
        }

        if (AsgoreAttackFactory.playerImmune) {
            --AsgoreAttackFactory.playerImmuneCount;
            if (AsgoreAttackFactory.playerImmuneCount == 0) { 
                AsgoreAttackFactory.playerImmune = false;
            }
            if (getCounter() % AsgoreAttackFactory.HURT_BLICK_CONSTANT == 0 || getCounter() % AsgoreAttackFactory.HURT_BLICK_CONSTANT == 1 || getCounter() % AsgoreAttackFactory.HURT_BLICK_CONSTANT == 2) {
                soul.getColor().a = .2f;
            }
            else soul.getColor().a = 1.f;
        }
        else soul.getColor().a = 1.f;

        if (counter < duration && boxShrinked) ++counter;

    }
    public void draw(Stage stage) {
        Batch batch = stage.getBatch();
        boolean drawStart = true;
        if (!batch.isDrawing()) {
            batch.begin();
            drawStart = false;
        }
        for (MobileScreenObject so : attackParts) {
            if (so.isVisible()) so.draw(batch, 1.0f);
        }
        if (AsgoreAttackFactory.soul.isVisible()) AsgoreAttackFactory.soul.draw(batch, 1.0f);
        if (!drawStart) batch.end();
    }

    protected abstract void init();

    ///
    /// Consturctors
    ///

    public AsgoreAttack(int counter, int duration, int boxWidth, int boxHeight, int type) {
        setCounter(counter);
        setDuration(duration);
        setBoxWidth(boxWidth);
        setBoxHeight(boxHeight);
        setType(type);
        init();
    }
    
}
