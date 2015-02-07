package com.example.epilepsy;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CastleEngine extends View {
	//for future, maybe it helps:
	public enum COLOR{
        YELLOW,
        ORANGE;

        public int getColorValue() {
            switch (this) {
          case YELLOW:
              return 0xffffff00;
          case ORANGE:
              return 0xffffa500;    
          default://RED
              return 0xff0000;
          }
        }
	}
	private static final Rect clippingRect;
	public static final Paint FloorPaint; //usunąłem new!
	public static final Paint DoorPaint; //tu też
	static{
		clippingRect = new Rect();
		FloorPaint = new Paint();
		DoorPaint = new Paint();
	}
	public ArrayList<PointAndStage> DoorsCoordinates = new ArrayList<PointAndStage>();
	int ScreenHeight;
	int ScreenWidth;
	int Floor_Height;
	int DoorWidth = 70;
	int DoorHeight = 110;
	boolean started = false;
	float X0,Y0;
	Random generator = new Random();
	public CastleEngine(Context context) {
        super(context);
    }
	public CastleEngine(Context context, AttributeSet set) {
		super(context, set);
		ScreenHeight =640;// getHeight();
		ScreenWidth = 480;//getWidth();
		Floor_Height = ScreenHeight/18;
	}
	
	@Override
	protected void onDraw(Canvas c){
		super.onDraw(c);
		c.getClipBounds(clippingRect);
		FloorPaint.setColor(Color.RED);
//		FloorPaint.setColor(0x2e0854);
		drawStage(0, c);drawStage(1, c);drawStage(2, c);
		DoorPaint.setColor(Color.BLACK);
		drawDoorAtStage(0,c);
		drawDoorAtStage(1,c);
		drawDoorAtStage(2,c);
	}
	
	/** Draws Door(black rectangle) at stage 0, 1, 2*/
	public void drawDoorAtStage(int stage, Canvas c){
		int dx=ScreenWidth/2;
		dx += (generator.nextFloat()-0.5f)*ScreenWidth/2 +1;
		
		c.drawRect(dx, (4+6*stage)*Floor_Height-DoorHeight, dx+DoorWidth,
					   (5+6*stage)*Floor_Height, DoorPaint);
		
		PointAndStage newDoor = new PointAndStage(dx, dx+DoorWidth, stage);
		DoorsCoordinates.add(newDoor);
		
	}
	/** Draws Floor 0,1,2*/
	public void drawStage(int stage,Canvas c){
		c.drawRect(0, (5+6*stage)*Floor_Height, ScreenWidth, (6+6*stage)*Floor_Height, FloorPaint);
	}
	
	public void makeSth(){
		
	}
}
//TODO:isolate door and also create stairs
class PointAndStage{
	int DoorStart;
	int DoorEnd;
	int Stage;
	public PointAndStage(int dS,int dE,int S){
		DoorStart=dS;
		DoorEnd=dE;
		Stage=S;
	}
}
