package test.com.elezeta.roguecave;

import com.elezeta.roguecave.entities.Entity;

class Line extends Entity {

	public Line(float x,float y) {
		setX(x);
		setY(y);
	}

	public Line(float  x1,float y1,float x2,float y2) {
		setOldX(x1);
		setOldY(y1);
		setX(x2);
		setY(y2);
	}

	public Line(float  x1,float y1,float x2,float y2,float radius) {
		setOldX(x1);
		setOldY(y1);
		setX(x2);
		setY(y2);
		setRadius(radius);
	}
	
	@Override
	protected void pass(float delta) {
	}

	@Override
	protected void decel(float delta) {
	}

	@Override
	protected void acel(float delta) {
	}

	@Override
	public float getMaxSpeed() {
		return 0;
	}

	@Override
	public void collideWithEntity(Entity e) {
	}

	@Override
	public void collideWithWall() {
	}

}