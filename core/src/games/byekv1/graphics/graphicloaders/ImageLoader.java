/**
 * @author	byekv1
 */
package games.byekv1.graphics.graphicloaders;

import com.badlogic.gdx.graphics.Texture;

import games.byekv1.graphics.Image;

/**
 * @author byekv
 *
 */
public class ImageLoader {
	
	public static Image loadImage(String filePath) {
		
		Image loadedImage;
		
		Texture texture = new Texture("images/" + filePath);
		loadedImage = new Image(texture, 0f, 0f, 0f, 0f, 1f, 1f, 0f);
		
		return loadedImage;
		
	}
	
	
	private ImageLoader() {
		// TODO Auto-generated constructor stub
	}

}
