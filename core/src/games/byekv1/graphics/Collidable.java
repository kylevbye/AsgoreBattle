package games.byekv1.graphics;

import com.badlogic.gdx.math.Polygon;

/**
 * Describes and object that can collide with another
 * 
 * @author	Kyle V Bye
 */
public interface Collidable {
	
	/**
	 * Returns the "hitbox" of an object.
	 * @return
	 */
	public abstract Polygon getBoundingPolygon();
	
}
