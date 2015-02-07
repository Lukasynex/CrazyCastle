package com.lukasyno.crazycastle;

public class BasicScene {
	private final int ID;
	public int topKey;
	public int middleKey;
	public int bottomKey;
	private int RoomsCount;
	enum FLOORMASK{
		TOP, MID, BOT
	}
	public BasicScene(int id) {
		ID = id;
		topKey = middleKey = bottomKey = -1;
		// TODO Auto-generated constructor stub
	}
	public void setRoomsCount(int val){
		RoomsCount = val;
	}
	
	public void setDoorBuddy(FLOORMASK floor, int key){
		switch(floor){
			case TOP:{
				break;
			}
			case MID:{
				break;
			}
			case BOT:{
				break;
			}
		}
	}
	public boolean isPopulatingDone(){
		return (topKey==-1 || middleKey == -1 || bottomKey == -1);
	}
	public void setRandomBuddies(int top, int mid, int bot) {
		topKey = top;
		middleKey = mid;
		bottomKey = bot;
	}
}
