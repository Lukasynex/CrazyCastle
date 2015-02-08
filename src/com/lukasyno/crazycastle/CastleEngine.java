package com.lukasyno.crazycastle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CastleEngine extends View {
	public enum WALL {
		REDBRICK, PRISON, NIGHT, LAB, WIZARD
	}
	Random generator = new Random();
//	public int x1  = (int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1),
//				x2  = (int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1),
//				x3  = (int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1);
	public Canvas mainCanvas = new Canvas();
	private static final Rect clippingRect;
	public static final Paint FloorPaint;
	public static final Paint DoorPaint;
	static {
		clippingRect = new Rect();
		FloorPaint = new Paint();
		DoorPaint = new Paint();
	}
	public ArrayList<PointAndStage> DoorsCoordinates = new ArrayList<PointAndStage>();
	public static int ScreenHeight;
	public static int ScreenWidth;
	public static int Floor_Height;
	public static final int DoorWidth = 70;
	public static final int DoorHeight = 110;
	boolean started = false;
	float X0, Y0;
	private static final int ROOMS_COUNT = 4;
	
	// since 23:037.02.2015
	private List<Integer> PadlockList = new ArrayList<Integer>();
	private BasicScene[] AllRooms = new BasicScene[ROOMS_COUNT];
	private BasicScene currentScene = null;

	public int getCurrentScene() {
		return currentScene.ID;
	}

	public CastleEngine(Context context) {
		super(context);
		ScreenHeight = 640;// getHeight();
		ScreenWidth = 480;// getWidth();
		Floor_Height = ScreenHeight / 18;
//		setBackgroundWall(WALL.REDBRICK);
//		 fillPadlockSet();
//		 onPopulateCastle();
//		 setCurrentRoom(1);
	}

	public CastleEngine(Context context, AttributeSet set) {
		super(context, set);

		ScreenHeight = 640;// getHeight();
		ScreenWidth = 480;// getWidth();
		Floor_Height = ScreenHeight / 18;

//		setBackgroundWall(WALL.REDBRICK);
		// fillPadlockSet();
		// onPopulateCastle();
		// setCurrentRoom(1);
	}

	private void setBackground(int i) {
		switch (i) {
		case 0: {
			this.setBackground(getResources().getDrawable(R.drawable.wall));
			break;
		}
		case 1: {
			this.setBackground(getResources()
					.getDrawable(R.drawable.prisonwall));
			break;
		}
		case 2: {
			this.setBackground(getResources().getDrawable(R.drawable.nightwall));
			break;
		}
		case 3: {
			this.setBackground(getResources()
					.getDrawable(R.drawable.prisonwall));
			break;
		}
		case 4: {
			this.setBackground(getResources().getDrawable(R.drawable.labwall));
			break;
		}

		}
	}

//	public void reDrawScene(int current_scene_index) {
//		mainCanvas.drawColor(Color.BLACK);
//
//		mainCanvas.getClipBounds(clippingRect);
//		FloorPaint.setColor(currentScene.FloorColor);
//		RedrawStage(0, mainCanvas);
//		RedrawStage(1, mainCanvas);
//		RedrawStage(2, mainCanvas);
//		DoorPaint.setColor(currentScene.DoorColor);
//		RedrawDoorAtStage(0, mainCanvas);
//		RedrawDoorAtStage(1, mainCanvas);
//		RedrawDoorAtStage(2, mainCanvas);
//
//		invalidate();
//	}
//	public void Start(){
//		 fillPadlockSet();
//		 onPopulateCastle();
//		 setCurrentRoom(1);
//	}
	public void setScene(BasicScene scene) {
		
		currentScene = scene;
		setBackground(scene.BackgroundType);
		invalidate();
//	Canvas c = mainCanvas;
//	FloorPaint.setColor(currentScene.FloorColor);
//	DoorPaint.setColor(currentScene.DoorColor);
//	
//	c.getClipBounds(clippingRect);
//	drawStage(0, c);
//	drawStage(1, c);
//	drawStage(2, c);
//	drawDoorAtStage(0, c);
//	drawDoorAtStage(1, c);
//	drawDoorAtStage(2, c);
	}


	public void EnterTheGates() {
		// TODO:
		// make Bugs ready to switch/teleport(?) between many doors
	}

	@Override
	protected void onDraw(Canvas c) {
		super.onDraw(c);
		if(currentScene==null){
			FloorPaint.setColor(Color.RED);
			DoorPaint.setColor(Color.BLACK);
		}
		else{
			FloorPaint.setColor(currentScene.FloorColor);
			DoorPaint.setColor(currentScene.DoorColor);
		}
		
		c.getClipBounds(clippingRect);
		drawStage(0, c);
		drawStage(1, c);
		drawStage(2, c);
		drawDoorAtStage(0, c);
		drawDoorAtStage(1, c);
		drawDoorAtStage(2, c);
		mainCanvas = c;
		
	}

	/** Draws Door(black rectangle) at stage 0, 1, 2 */
	public void drawDoorAtStage(int stage, Canvas c) {
		int dx = 0;
		if(currentScene != null){
			if (stage == 0)
				dx = currentScene.topDoorPosition;
			else if (stage == 1)
				dx = currentScene.middleDoorPosition;
			else
				dx = currentScene.bottomDoorPosition;
		}else{
//			if(stage==0)
//				dx=x1;
//			else if(stage==1)
//				dx=x2;
//			else
//				dx=x3;
			dx = (int) (ScreenWidth/2 + ((generator.nextFloat()-0.5f)*ScreenWidth/2) + 1);
		}
		
		
		c.drawRect(dx, (4 + 6 * stage) * Floor_Height - DoorHeight, dx
				+ DoorWidth, (5 + 6 * stage) * Floor_Height, DoorPaint);
		PointAndStage newDoor = new PointAndStage(dx, dx + DoorWidth, stage);
		DoorsCoordinates.add(newDoor);
		
	}


	/** Draws Floor 0,1,2 */
	public void drawStage(int stage, Canvas c) {
		c.drawRect(0, (5 + 6 * stage) * Floor_Height, ScreenWidth,
				(6 + 6 * stage) * Floor_Height, FloorPaint);
	}

	public void setBackgroundWall(WALL type) {
		switch (type) {
		case LAB: {
			this.setBackground(getResources().getDrawable(R.drawable.labwall));
			break;
		}
		case REDBRICK: {
			this.setBackground(getResources().getDrawable(R.drawable.wall));
			break;
		}
		case WIZARD: {
			this.setBackground(getResources()
					.getDrawable(R.drawable.wizardwall));
			break;
		}
		case NIGHT: {
			this.setBackground(getResources().getDrawable(R.drawable.nightwall));
			break;
		}
		case PRISON: {
			this.setBackground(getResources()
					.getDrawable(R.drawable.prisonwall));
			break;
		}
		}
		invalidate();
	}


}

// TODO:isolate door and also create stairs
class PointAndStage {
	int DoorStart;
	int DoorEnd;
	int Stage;

	public PointAndStage(int dS, int dE, int S) {
		DoorStart = dS;
		DoorEnd = dE;
		Stage = S;
	}
}
