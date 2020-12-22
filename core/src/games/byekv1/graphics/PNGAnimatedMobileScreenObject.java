/**
 * 
 */
package games.byekv1.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author byekv
 *
 */
public class PNGAnimatedMobileScreenObject extends MobileScreenObject {
	
	private int counter;
	private int frameCount;
	private int frameDelay;
	private ArrayList<Image> frames;
	
	///
	///	Getters
	///
	
	public int getCounter() { return counter; }
	public int getFrameCount() { return frameCount; }
	public ArrayList<Image> getFrames() { return frames; }
	
	///
	///	Setters
	///
	
	public void setCounter(int counterIn) { counter = counterIn; }
	public void setFrameCount(int frameCountIn) { frameCount = frameCountIn; }
	public void setFrameDelay(int frameDelayIn) { frameDelay = frameDelayIn; }
	public void setFrames(ArrayList<Image> framesIn) { frames = framesIn; }
	
	///
	///	Setters - Image - Actor
	///
	
	@Override 
	protected void positionChanged() {
		if (frames != null) {
			for (Image frame : frames) frame.setPosition(getX(), getY());
		}
	}
	
	@Override
	protected void sizeChanged() {
		if (frames != null) {
			for (Image frame : frames) frame.setSize(getWidth(), getHeight());
		}
	}
	
	@Override 
	protected void scaleChanged() {
		if (frames != null) {
			for (Image frame : frames) frame.setScale(getScaleX(), getScaleY());
		}
	}
	
	@Override
	protected void rotationChanged() {
		if (frames != null) {
			for (Image frame : frames) frame.setRotation(getRotation());
		}
	}
	
	
	///
	///	Functions
	///
	
	public void incrementCounter() { ++counter; }
	
	public void incrementFrameCount() { ++frameCount; }
	
	public void resetFrameCount() { 
		setCounter(0);
		setFrameCount(0); 
	}
	
	@Override
	public void draw(Batch batchIn, float parentAlphaIn) {
		
		frames.get(frameCount).draw(batchIn, parentAlphaIn);
		
	}
	
	public void play() {
		
		if (counter % frameDelay == 0) {
			
			if (frameCount == frames.size()-1) frameCount = 0;
			else incrementFrameCount();
			
		}
		
		incrementCounter();
	}
	
	public boolean isOver() {
		
		return frames.size()-1 == frameCount;
		
	}
	
	///
	///	Constructors
	///
	
	public PNGAnimatedMobileScreenObject(ArrayList<Image> framesIn) {
		this(0f, 0f, framesIn);
	}
	
	public PNGAnimatedMobileScreenObject(float xIn, float yIn, ArrayList<Image> framesIn) {
		super(framesIn.get(0));
		setFrames(framesIn);
		setFrameDelay(0);
		setPosition(xIn, yIn);
		setCounter(0);
		setFrameCount(0);
	}


}
