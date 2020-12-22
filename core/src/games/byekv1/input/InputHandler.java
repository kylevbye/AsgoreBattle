package games.byekv1.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

public class InputHandler extends InputAdapter {
	
	///
	///	Functions
	///
	
	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		
		super.mouseMoved(screenX, screenY);
		
		PlayerInput.mouseX = screenX;
		PlayerInput.mouseY = screenY;
		
		return true;
		
	}
	
	@Override 
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		
		super.touchDown(screenX, screenY, pointer, button);
		
		switch(button) {
		
		case Buttons.LEFT:
			PlayerInput.mLeft_down = true;
			PlayerInput.mLeftWasClicked = true;
			PlayerInput.lastLeftClickLocation = new Vector2(screenX, screenY);
			break;
			
		case Buttons.MIDDLE:
			PlayerInput.mMiddle_down = true;
			PlayerInput.mMiddleWasClicked = true;
			PlayerInput.lastMiddleClickLocation = new Vector2(screenX, screenY);
			break;
			
		case Buttons.RIGHT:
			PlayerInput.right_down = true;
			PlayerInput.mRightWasClicked = true;
			PlayerInput.lastRightClickLocation = new Vector2(screenX, screenY);
			break;
			
		}
		
		return true;
		
	}
	
	@Override 
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		
		super.touchUp(screenX, screenY, pointer, button);
		
		switch(button) {
		
		case Buttons.LEFT:
			PlayerInput.mLeft_down = false;
			break;
			
		case Buttons.RIGHT:
			PlayerInput.mright_down = false;
			break;
			
		case Buttons.MIDDLE:
			PlayerInput.mMiddle_down = false;
			break;
			
		}
		
		return true;
		
	}
	
	
	
	@Override
	public boolean keyDown(int keyCode) {
		
		super.keyDown(keyCode);
			
		switch (keyCode) {
		
		//	Shift
		case Keys.SHIFT_LEFT:
			PlayerInput.shift_down = true;
			PlayerInput.shiftWasPressed = true;
			break;
			
		case Keys.SHIFT_RIGHT:
			PlayerInput.shift_down = true;
			PlayerInput.shiftWasPressed = true;
			break;
		
		//	WASD
		case Keys.W:
			PlayerInput.w_down = true;
			PlayerInput.wWasPressed = true;
			break;
		
		case Keys.A: 
			PlayerInput.a_down = true;
			PlayerInput.aWasPressed = true;
			break;
		
		case Keys.S:
			PlayerInput.s_down = true;
			PlayerInput.sWasPressed = true;
			break;
			
		case Keys.D:
			PlayerInput.d_down = true;
			PlayerInput.dWasPressed = true;
			break;
			
		//	Arrow
		case Keys.UP:
			PlayerInput.up_down = true;
			PlayerInput.upWasPressed = true;
			break;
		
		case Keys.LEFT:
			PlayerInput.left_down = true;
			PlayerInput.leftWasPressed = true;
			break;
			
		case Keys.DOWN:
			PlayerInput.down_down = true;
			PlayerInput.downWasPressed = true;
			break;
			
		case Keys.RIGHT:
			PlayerInput.right_down = true;
			PlayerInput.rightWasPressed = true;
			break;
			
		//	Actions
			
		case Keys.Z:
			PlayerInput.z_down = true;
			PlayerInput.zWasPressed = true;
			break;
			
		case Keys.X:
			PlayerInput.x_down = true;
			PlayerInput.xWasPressed = true;
			break;
			
		case Keys.ESCAPE:
			PlayerInput.esc_down = true;
			PlayerInput.escWasPressed = true;
			break;
			
		//	Function Keys
			
		case Keys.F4:
			PlayerInput.f4_down = true;
			PlayerInput.f4WasPressed = true;
			break;
			
		}
		
		
		return true;
			
	}

	@Override 
	public boolean keyUp(int keyCode) {
		
		super.keyUp(keyCode);
		
		switch (keyCode) {
		
		//	Shift
		case Keys.SHIFT_LEFT:
			PlayerInput.shift_down = false;
			break;
			
		case Keys.SHIFT_RIGHT:
			PlayerInput.shift_down = false;
			break;
		
		//	WASD
		case Keys.W:
			PlayerInput.w_down = false;
			break;
		
		case Keys.A: 
			PlayerInput.a_down = false;
			break;
		
		case Keys.S:
			PlayerInput.s_down = false;
			break;
			
		case Keys.D:
			PlayerInput.d_down = false;
			break;
			
		//	Arrow
		case Keys.UP:
			PlayerInput.up_down = false;
			break;
		
		case Keys.LEFT:
			PlayerInput.left_down = false;
			break;
			
		case Keys.DOWN:
			PlayerInput.down_down = false;
			break;
			
		case Keys.RIGHT:
			PlayerInput.right_down = false;
			break;
			
		//	Actions
		
		case Keys.Z:
			PlayerInput.z_down = false;
			break;
			
		case Keys.X:
			PlayerInput.x_down = false;
			break;
			
		case Keys.ESCAPE:
			PlayerInput.esc_down = false;
			break;
			
		//	Functions
		case Keys.F4:
			PlayerInput.f4_down = false;
			break;
		
		}
		
		return true;
		
	}
	
}
