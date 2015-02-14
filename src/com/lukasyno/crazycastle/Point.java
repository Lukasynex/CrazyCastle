package com.lukasyno.crazycastle;

class Point {
	public float x, y;

	public Point() {
	}

	public Point(float dx, float dy) {
		x = dx;
		y = dy;
	}

	public void set(float dx, float dy) {
		x = dx;
		y = dy;
	}

	@Override
	public String toString() {
		return x + ", " + y;
	}
}