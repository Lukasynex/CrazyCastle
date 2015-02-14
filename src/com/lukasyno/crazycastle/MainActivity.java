package com.lukasyno.crazycastle;

import java.util.ArrayList;
import java.util.Random;

import com.lukasyno.crazycastle.Character.GESTURE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * TODO: castle engine fix door moving around fix set proper floor/door values
 * fix walking through door, set Bugs in proper position to do so -> door
 * 
 * splash screen oraz wygodne menu
 * opakować blendziora w klasę
 * 
 * wygenerować klasę LevelManager, która dba o przebieg gry
 * @author lukasz
 * 
 */
public class MainActivity extends Activity {
	/**
	 * custom classes for game!!!1
	 */
	public ArrayList<Pair<Integer, Integer>> track = new ArrayList<Pair<Integer, Integer>>();
	public Pair<Integer, Integer> temporaryPair = new Pair<Integer, Integer>(0,
			0);
//	public int indexForTrack = 0;
	public CastleEngine CrazyCastle;
	public SceneProvider sceneProvider;
	public Character character;
	public EvilBugs evilBugs;
	public CarrotManager carrotManager;

	 private final int ROOMS_COUNT = 14;

	Random generator = new Random();
//	int BunnyStep = 20;
	int HEIGHT = 1000;
	boolean DirectionLeft = false;
	boolean DirectionLeftForEvil = false;
	int CollectedCarrots = 0;
	int CurrentStage = 0;
	int time = 0;
	int PreviousStage = 0;
	float X0, Y0;
	int FingerSize = 100;

	private Animation fade;

	@SuppressLint("UseValueOf")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sceneProvider = new SceneProvider(ROOMS_COUNT);

		CrazyCastle = (CastleEngine) findViewById(R.id.castle_view);
		CrazyCastle.setBackground(getResources().getDrawable(R.drawable.wall));
		CrazyCastle.setScene(sceneProvider.getCurrentScene());
		View myView = (View) findViewById(R.id.mainLayout);
		carrotManager = new CarrotManager(this);

		fade = AnimationUtils.loadAnimation(this, R.anim.fade_out);

		character = new Character(this);
		character.CurrentCharacterEntity.setX(character.CurrentCharacterEntity
				.getX() + 111);
		evilBugs = new EvilBugs(this, sceneProvider);

//		BugsWalks.start(this);
		Loop.start(this);
	}

	// thread for stopping bugs
//	public void Walk() {
//		character.walk(character.WalkStatus);
//	}

	public float AbsDiff(float x, float y) {
		return (x > y) ? (x - y) : (y - x);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		if (event.getActionMasked() == MotionEvent.ACTION_UP) {
			character.setWalkStatus(GESTURE.STOP);
		}
		if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
			time = 0;
			ImageView AnimationLeft = (ImageView) findViewById(R.id.Bunny_Mirror);
			ImageView AnimationRight = (ImageView) findViewById(R.id.Bunny);
			ImageView ref = (DirectionLeft) ? AnimationLeft : AnimationRight;
			ImageView refMirror = (!DirectionLeft) ? AnimationLeft
					: AnimationRight;

			// variables needed to detect door
			ArrayList<PointAndStage> list = sceneProvider
					.getCurrentDoorPosition();

			PointAndStage pt = list.get(CurrentStage);
			CrazyCastle.setDoorCoordinates(list);

			float FloorHeight = CastleEngine.Floor_Height;
			float Y = event.getY() - FingerSize;

			// if we want to go downstairs to stage 1:
			if (CurrentStage == 0 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 6 * FloorHeight < Y) {

				View lay = (View) findViewById(R.id.mainLayout);
				lay.startAnimation(fade);

				CurrentStage = generator.nextInt(3);
				sceneProvider.setCurrentScene(generator.nextInt(ROOMS_COUNT));

				CrazyCastle.setScene(sceneProvider.getCurrentScene());
				list = sceneProvider.getCurrentDoorPosition();
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY(Y_Position_Is_Essential(CurrentStage));
				character.setMirrorProperly(refMirror, ref);
				carrotManager
						.setProperCarrot(sceneProvider.getCurrentScene().ID);

			}
			// if we want to go downstairs to stage 2:
			else if (CurrentStage == 1 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 12 * FloorHeight < Y) {

				View lay = (View) findViewById(R.id.mainLayout);
				lay.startAnimation(fade);

				CurrentStage = generator.nextInt(3);
				sceneProvider.setCurrentScene(generator.nextInt(4));
				
				CrazyCastle.setScene(sceneProvider.getCurrentScene());
				list = sceneProvider.getCurrentDoorPosition();
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY(Y_Position_Is_Essential(CurrentStage));
				character.setMirrorProperly(refMirror, ref);
				carrotManager
						.setProperCarrot(sceneProvider.getCurrentScene().ID);
			}
			// if we want to go upstairs to stage 0:
			else if (CurrentStage == 1 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 6 * FloorHeight > Y) {

				View lay = (View) findViewById(R.id.mainLayout);
				lay.startAnimation(fade);

				CurrentStage = generator.nextInt(3);
				sceneProvider.setCurrentScene(generator.nextInt(ROOMS_COUNT));
				
				CrazyCastle.setScene(sceneProvider.getCurrentScene());
				list = sceneProvider.getCurrentDoorPosition();
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY(Y_Position_Is_Essential(CurrentStage));

				character.setMirrorProperly(refMirror, ref);
				carrotManager
						.setProperCarrot(sceneProvider.getCurrentScene().ID);

			}
			// if we want to go upstairs to stage 1:
			else if (CurrentStage == 2 && ref.getX() > pt.DoorStart
					&& ref.getX() < pt.DoorEnd && 12 * FloorHeight > Y) {

				View lay = (View) findViewById(R.id.mainLayout);
				lay.startAnimation(fade);

				CurrentStage = generator.nextInt(3);
				sceneProvider.setCurrentScene(generator.nextInt(ROOMS_COUNT));
				
				CrazyCastle.setScene(sceneProvider.getCurrentScene());

				list = sceneProvider.getCurrentDoorPosition();
				pt = list.get(CurrentStage);
				ref.setX(pt.DoorStart);
				ref.setY(Y_Position_Is_Essential(CurrentStage));
				character.setMirrorProperly(refMirror, ref);
				carrotManager
						.setProperCarrot(sceneProvider.getCurrentScene().ID);

			}
			// move character left/right
			if (character.CurrentCharacterEntity.getX() > event.getX()) {
				character.setAnimation(GESTURE.RIGHTWALK);
				character.DirectionLeft = true;
				character.setWalkStatus(GESTURE.LEFTWALK);
			} else if (character.CurrentCharacterEntity.getX() < event.getX()) {

				character.setAnimation(GESTURE.LEFTWALK);
				character.DirectionLeft = false;
				character.setWalkStatus(GESTURE.RIGHTWALK);
			}
		}
		return true;
	}

	private int Y_Position_Is_Essential(int stage) {
		return (5 + 6 * stage - 3) * CastleEngine.Floor_Height;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.restart) {
			RestartGame();
			return true;
		}
		if (id == R.id.action_settings) {
			
		}
		return super.onOptionsItemSelected(item);
	}
	public void RestartGame() {
		//character.onRestart();
		// ImageView bugsL = (ImageView) findViewById(R.id.Bunny);
		// ImageView bugsR = (ImageView) findViewById(R.id.Bunny_Mirror);
		// bugsL.setX(X0);
		// bugsL.setY(2 * CastleEngine.Floor_Height);
		// character.setMirrorProperly(bugsR, bugsL);
		// AnimationDrawable animR = (AnimationDrawable) bugsL.getDrawable();
		// animR.start();
		// AnimationDrawable animL = (AnimationDrawable) bugsR.getDrawable();
		// animL.start();
	}

	public void getX0(float x) {
		X0 = x;
	}

	public void LoopActions() {
		character.walk(character.WalkStatus);
		
		if (carrotManager.CollidesWith(character.CurrentCharacterEntity) && carrotManager.isVisible()) {
			carrotManager.DetachCarrot(sceneProvider.getCurrentScene().ID);
			carrotManager.update();
			
		}
		evilBugs.EvilWalk();
		
	}
}
