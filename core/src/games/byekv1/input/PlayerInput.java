package games.byekv1.input;

import com.badlogic.gdx.math.Vector2;

public class PlayerInput {
	
	///
	///	Mouse Controls
	///
	
	public static float mouseX;
	public static float mouseY;
	
	public static Vector2 lastLeftClickLocation = new Vector2();
	public static Vector2 lastMiddleClickLocation = new Vector2();
	public static Vector2 lastRightClickLocation = new Vector2();
	
	public static boolean mLeft_down = false;
	public static boolean mright_down = false;
	public static boolean mMiddle_down = false;
	
	public static boolean mLeftWasClicked = false;
	public static boolean mMiddleWasClicked = false;
	public static boolean mRightWasClicked = false;	
	
	///
	///	Keyboard Controls
	///
	
	public static boolean shift_down = false;
	
	public static boolean w_down = false;
	public static boolean a_down = false;
	public static boolean s_down = false;
	public static boolean d_down = false;
	public static boolean up_down = false;
	public static boolean left_down = false;
	public static boolean down_down = false;
	public static boolean right_down = false;
	
	public static boolean z_down = false;
	public static boolean x_down = false;
	public static boolean esc_down = false;
	
	public static boolean f4_down = false;
	
	public static boolean shiftWasPressed = false;
	
	public static boolean wWasPressed = false;
	public static boolean aWasPressed = false;
	public static boolean sWasPressed = false;
	public static boolean dWasPressed = false;
	public static boolean upWasPressed = false;
	public static boolean leftWasPressed = false;
	public static boolean downWasPressed = false;
	public static boolean rightWasPressed = false;
	
	public static boolean zWasPressed = false;
	public static boolean xWasPressed = false;
	public static boolean escWasPressed = false;
	
	public static boolean f4WasPressed = false;
	
	///
	///	Functions
	///
	
	public static void resetTempControls() {
		
		//	Mouse
		mLeftWasClicked = false;
		mMiddleWasClicked = false;
		mRightWasClicked = false;
		
		//	Keyboard
		shiftWasPressed = false;
		wWasPressed = false;
		aWasPressed = false;
		sWasPressed = false;
		dWasPressed = false;
		upWasPressed = false;
		leftWasPressed = false;
		downWasPressed = false;
		rightWasPressed = false;
		
		//	Actions
		zWasPressed = false;
		xWasPressed = false;
		escWasPressed = false;
		
		//	Function Keys
		f4WasPressed = false;
		
	}
	
	///
	///	Constructors
	///
	
	private PlayerInput() {}
	
}
