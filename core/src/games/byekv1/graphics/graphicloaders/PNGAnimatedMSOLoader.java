/**
 * @author	byekv1
 */
package games.byekv1.graphics.graphicloaders;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

import games.byekv1.graphics.Image;
import games.byekv1.graphics.PNGAnimatedMobileScreenObject;

/**
 * @author byekv
 *
 */
public class PNGAnimatedMSOLoader {
	
	public static PNGAnimatedMobileScreenObject loadPNGAnimatedMobileScreenObject(
			String relativeFolderPath, String baseFileName, int frameCount) {
		
		String fullFilePath = "animations/" + relativeFolderPath;
		
		PNGAnimatedMobileScreenObject animation;
		
		ArrayList<Image> frames = new ArrayList<Image>();
		
		for (int i = 0; i<frameCount; i++) {
			
			Texture texture = new Texture(fullFilePath + baseFileName + Integer.toString(i+1) + ".png");
			Image loadedImage = new Image(texture, 0f, 0f, 0f, 0f, 1f, 1f, 0f);
			frames.add(loadedImage);
			
		}
		
		animation = new PNGAnimatedMobileScreenObject(frames);
		
		return animation;
		
	}
	private PNGAnimatedMSOLoader() {
		// TODO Auto-generated constructor stub
	}

}
