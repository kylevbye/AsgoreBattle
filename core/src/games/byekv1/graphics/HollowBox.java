package games.byekv1.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HollowBox extends Actor {

    ///
    /// Fields
    ///
    private int borderLength;
    private boolean isHollowed;

    ///
    /// Getters
    ///
    public int getBorderLength() { return borderLength; }

    ///
    /// Setters
    ///

    public void setBorderLength(int borderLength) { this.borderLength = borderLength; }
    public void setHollow(boolean isHollowed) { this.isHollowed = isHollowed; }

    ///
    /// Actors Functions
    ///

    @Override
    public void draw(Batch batch, float parentAlpha) {

        //
        batch.end();

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setColor(getColor());

        for (int i = 0; i<borderLength; ++i) {
            shapeRenderer.rect(getX()+i, getY()+i, getOriginX(), getOriginY(), getWidth()-2*i, getHeight()-2*i, getScaleX(), getScaleY(), getRotation());

            if (i == borderLength-1 && !isHollowed) {
                shapeRenderer.set(ShapeType.Filled);
                shapeRenderer.setColor(Color.BLACK);
                shapeRenderer.getColor().a = 1.0f;
                shapeRenderer.rect(getX()+i, getY()+i, getOriginX(), getOriginY(), getWidth()-2*i, getHeight()-2*i, getScaleX(), getScaleY(), getRotation());
            }

        }
        shapeRenderer.end();

        batch.begin();
    }

    ///
    /// Provided Functions
    ///

    public void growFromBottomY(int rate) {
        //shrinkFromBottomY(-rate);
        //moveBy(0, rate);
        setHeight(getHeight()+rate);
    }

    public void shrinkFromBottomY(int rate) {
        //moveBy(0, -rate*2);
        //setHeight(getHeight()-rate*2);
        growFromBottomY(-rate);
    } 

    public void shrinkCentered(int rate) {
        shrinkCentered(rate, rate);
    }

    public void shrinkCentered(int rateX, int rateY) {
        moveBy(rateX, rateY);
        setWidth(getWidth()-rateX*2);
        setHeight(getHeight()-rateY*2);
    }

    public void growCentered(int rate) {
        shrinkCentered(-rate);
    }

    public void growCentered(int rateX, int rateY) {
        shrinkCentered(-rateX, -rateY);
    }

    ///
    /// Constructors
    ///

    public HollowBox (float x, float y, float width, float height, Color color, int borderLength) {

        super();
        setBounds(x, y, width, height);
        setColor(color);
        setBorderLength(borderLength);
        setHollow(true);

    }
    
}
