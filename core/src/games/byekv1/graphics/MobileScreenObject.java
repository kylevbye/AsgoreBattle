package games.byekv1.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

/**
 * This ScreenObject is movable using realistic simulated physics.
 * 
 * @author	Kyle V Bye
 * @see	ScreenObject
 * @see	Disposable
 * @see	Drawable
 */
public class MobileScreenObject extends ScreenObject {
	
	///
	///	Fields
	///
	protected Vector2 velocityVector;
	protected Vector2 accelerationVector;
	protected float acceleration;
	protected float maxSpeed;
	protected float deceleration;
	
	///
	///	Getters pointing to velocityVector and accelerationVector
	///
	
	public float getSpeed() { return velocityVector.len(); }
	public float getMotionAngle() { return velocityVector.angle(); }
	
	
	///
	///	Setters
	///
	
	public void setVelocityVector(Vector2 velocityVectorIn) { velocityVector = velocityVectorIn; }
	public void setAccelerationVector(Vector2 accelerationVectorIn) { accelerationVector = accelerationVectorIn; }
	public void setAcceleration(float accelerationIn) { acceleration = accelerationIn; }
	public void setMaxSpeed(float maxSpeedIn) { maxSpeed = maxSpeedIn; }
	public void setDeceleration(float decelerationIn) { deceleration = decelerationIn; }
	
	///
	///	Setters pointing to velocityVector and accelerationVector
	///
	
	public void setSpeed(float speedIn) {
		
        if (velocityVector.len() == 0.f) velocityVector.set(speedIn, 0.f);
        else velocityVector.setLength(speedIn);
        
    }
	
	public void setMotionAngle(float angleIn) { velocityVector.setAngle(angleIn); }
	
	///
	///	Functions
	///
	
	public boolean isMoving() { return getSpeed() > 0; }
	
	public void moveX(float amountIn) { setX(getX()+amountIn); }
	public void moveY(float amountIn) { setY(getY()+amountIn); }
	public void move(float dx, float dy) { moveX(dx); moveY(dy); }
	
	public void moveUp(float amountIn) { moveY(amountIn); }
	public void moveDown(float amountIn) { moveY(-amountIn); }
	public void moveLeft(float amountIn) { moveX(-amountIn); }
	public void moveRight(float amountIn) { moveX(amountIn); }
	
	public void rotateCW(float degreesIn) { rotate(-degreesIn); }
	public void rotateCCW(float degreesIn) { rotate(degreesIn); }
	public void rotate(float angle) { setRotation(getRotation() + angle); }
	
	public void initMovement() {
		
		this.initMovement(0.f, 1000.f, 0.f);
	}
	
	public void initMovement(float accelerationIn, float maxSpeedIn, float decelerationIn) {
		
		setVelocityVector(new Vector2(0.f, 0.f));
		setAccelerationVector(new Vector2(0.f, 0.f));
		setAcceleration(0.f);
		setMaxSpeed(1000.f);
		setDeceleration(0.f);
		
	}
	
	/**
	 * Backs both objects away from each other in case of a collision
	 * @param	otherIn	other object that is collidable
	 * @return	vector that is the rebound direction
	 */
	public Vector2 preventOverlap(Collidable otherIn) {
		
		MinimumTranslationVector mtv = new MinimumTranslationVector();
		
		Polygon thisPoly = this.boundingPolygon;
		Polygon otherPoly = otherIn.getBoundingPolygon();
		
		if (!Intersector.overlapConvexPolygons(thisPoly, otherPoly, mtv)) return null;
		
		move(mtv.normal.x*mtv.depth, mtv.normal.y*mtv.depth);
		
		return mtv.normal;
		
	}
	
	/**
	 * Simulate rebounding force.
	 * 
	 * @param	angleIn	direction to rebound
	 * @param	elasticityIn	collision imperfection value
	 */
	public void rebound(float angleIn, float elasticityIn) {
		
		accelerationVector.add(
	            new Vector2((int)(getSpeed()*elasticityIn*acceleration),0).setAngle(angleIn)
	            );
		
	}
	
	/**
	 * Translate vector and acceleration values into motion.
	 * 
	 * @param	dt	time since last physics application
	 */
	public void applyPhysics(float dt) {
		
		float speed;
		
		//	Acceleration
		velocityVector.add(accelerationVector.x*dt, accelerationVector.y*dt);
		speed = getSpeed();
		
		//	Decel if neccessary
		if (accelerationVector.len() == 0.f) speed -= deceleration * dt; 
		
		//	Bound Speed
		speed = MathUtils.clamp(speed, 0.f, maxSpeed);
		setSpeed(speed);
		
		//	Apply motion
		move(velocityVector.x*dt, velocityVector.y*dt);
		
		//	Reset Acceleration
		accelerationVector.set(0.f, 0.f);
		
		
	}
	
	public void accelerateAtAngle(float angleIn) {
		
        accelerationVector.add(new 
            Vector2(acceleration, 0).setAngle(angleIn)
            );
        
    }
	
	///
	///	Constructors
	///
	
	public MobileScreenObject() {
		
		this(null, 0, 0, 0, 0, 1, 1, 0, false, false);
	}
	
	public MobileScreenObject(Image imageIn) {
		
		this(imageIn.getTextureRegion().getTexture(), imageIn.getX(), imageIn.getY(), imageIn.getOriginX(), 
				imageIn.getOriginY(), imageIn.getScaleX(), imageIn.getScaleY(), imageIn.getRotation(), false, false);
		
	}
	
	public MobileScreenObject(Texture textureIn) {
		
		this(textureIn, 0, 0, 0, 0, 1, 1, 0, false, false);
		
	}
	
	public MobileScreenObject(Texture textureIn, float xIn, float yIn, boolean centerOrigin) {
		
		this(textureIn, xIn, yIn, 0, 0, 1, 1, 0, false, false);
		centerOrigin();
		
	}
	
	public MobileScreenObject(Texture textureIn, float xIn, float yIn, float originXIn, float originYIn, float scaleXIn, 
			float scaleYIn, float rotationAngleIn, boolean flippedX, boolean flippedY) {
		
		super(textureIn, xIn, yIn, originXIn, originYIn, scaleXIn, scaleYIn, rotationAngleIn, flippedX, flippedY);
		initMovement();
		
	}
	
	
	
}
