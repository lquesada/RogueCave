package com.elezeta.roguecave.world;

public class RoutePlan {

	private float i;
	private float j;
	private int hit;
	private float length;

	RoutePlan(float i,float j,float length,int hit) {
		setI(i);
		setJ(j);
		setHit(hit);
		setLength(length);
	}
	
	public float getI() {
		return i;
	}
	
	private void setI(float i) {
		this.i = i;
	}
	
	public float getJ() {
		return j;
	}
	
	private void setJ(float j) {
		this.j = j;
	}
	
	public int getHit() {
		return hit;
	}

	public boolean isHit() {
		return hit != RoutePlanner.NO_HIT;
	}

	public boolean isSingleHit() {
		return (hit == RoutePlanner.HIT_TOP || hit == RoutePlanner.HIT_BOTTOM || hit == RoutePlanner.HIT_LEFT || hit == RoutePlanner.HIT_RIGHT);
	}

	public boolean isDoubleHit() {
		return isHit()&&!isSingleHit();
	}

	private void setHit(int hit) {
		this.hit = hit;
	}

	public float getLength() {
		return length;
	}
	
	private void setLength(float length) {
		this.length = length;
	}
	
}
