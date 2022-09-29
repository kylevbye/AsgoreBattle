package games.byekv1.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import games.byekv1.graphics.graphicloaders.ImageLoader;

public class HealthBar extends Actor {

    ///
    /// Fields
    ///

    private Image topRectangle;
    private Image bottomRectangle;
    private int healthPoints;
    private int maxHealthPoints;
    private int displayPoints;
    private int displayDelay;
    private int counter;

    ///
    /// Getters
    ///

    public Image gettopRectangle() { return topRectangle; }
	public Image getbottomRectangle() { return bottomRectangle; }
    public int getHealthPoints() { return healthPoints; }
    public int getMaxHealthPoints() { return maxHealthPoints; }
	public int getdisplayPoints() { return displayPoints; }
    public int getdisplayDelay() { return displayDelay; }

    ///
    /// Setters
    ///

    public void settopRectangle(Image topRectangle) { this.topRectangle = topRectangle; }
	public void setbottomRectangle(Image bottomRectangle) { 
        this.bottomRectangle = bottomRectangle; 
    }
    public void setHealthPoints(int healthPoints) { this.healthPoints = healthPoints; }
    public void setMaxHealthPoints(int maxHealthPoints) { this.maxHealthPoints = maxHealthPoints; }
    public void setdisplayPoints(int displayPoints) { this.displayPoints = displayPoints; }
    public void setdisplayDelay(int displayDelay) { this.displayDelay = displayDelay; }

    ///
    /// Functions
    ///

    public void processLogic() {

        if (counter%displayDelay == 0 && displayPoints>healthPoints && displayPoints > 0) {
            --displayPoints;
            healthDisplayChanged();
        }

        ++counter;
    }

    public void resetCounter() {
        counter = 0;
    }

    public void healthDisplayChanged() {

        float topWidth = getWidth()*((float)displayPoints/(float)maxHealthPoints);
        topRectangle.setWidth(topWidth);

    }
    
    ///
    /// Actors Functions
    ///

    @Override 
	protected void positionChanged() {
		
		topRectangle.setPosition(getX(), getY());
		bottomRectangle.setPosition(getX(), getY());
		
	}
	
	@Override
	protected void sizeChanged() {
		
		//topRectangle.setSize(getWidth(), getHeight());
		//bottomRectangle.setSize(getWidth(), getHeight());
		
	}
	
	@Override 
	protected void scaleChanged() {
		
		//topRectangle.setScale(getScaleX(), getScaleY());
		//bottomRectangle.setScale(getScaleX(), getScaleY());
		
	}
	
	@Override
	protected void rotationChanged() {
		
		topRectangle.setRotation(getRotation());
		bottomRectangle.setRotation(getRotation());
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		batch.enableBlending();
        Color old = batch.getColor();
		Color colorTop = topRectangle.getColor();
        Color colorBottom = bottomRectangle.getColor();
		batch.setColor(colorBottom);
        bottomRectangle.draw(batch, parentAlpha);
        batch.setColor(colorTop);
        topRectangle.draw(batch, parentAlpha);

        batch.setColor(old);
		
	}

    ///
    /// Constructor
    ///

    public HealthBar(float xIn, float yIn, float scaleXIn, float scaleYIn, float width, 
    float height, Color topColor, Color bottomColor) {
        super();

        

        /// Create Top
        Image topRectangle = ImageLoader.loadImage("rect.png");
        topRectangle.setBounds(xIn, yIn, width, height);
        topRectangle.setScale(scaleXIn, scaleYIn);
        topRectangle.setColor(topColor);
        settopRectangle(topRectangle);

        /// Create Bottom
        Image bottomRectangle = ImageLoader.loadImage("rect.png");
        bottomRectangle.setBounds(xIn, yIn, width, height);
        bottomRectangle.setScale(scaleXIn, scaleYIn);
        bottomRectangle.setColor(bottomColor);
        setbottomRectangle(bottomRectangle);

        setWidth(width); setHeight(height);
        setOrigin(0.f, 0.f);
		setX(xIn); setY(yIn);
		setScale(scaleXIn, scaleYIn);
		setRotation(0.f);

    }

}
