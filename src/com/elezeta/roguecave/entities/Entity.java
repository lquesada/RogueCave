package com.elezeta.roguecave.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.world.Level;
import com.elezeta.roguecave.sprites.SpriteData;

public abstract class Entity {

	private float x = 0f;
	private float y = 0f;
	private float z = 0f;

	private float height = 0f;
	
	private float oldX = 0f;
	private float oldY = 0f;
	private float oldZ = 0f;
	
	private float speedX = 0f;
	private float speedY = 0f;
	private float speedZ = 0f;

	private float forceX = 0f;
	private float forceY = 0f;
	private float forceZ = 0f;

	private float radius = 0f;
			
	private float lookAngle = 0f;
	
	private float moveAnimationCounter = 0f;
	private float moveAnimationFactor = 1f;
	
	private boolean active = true;

	private SpriteData sprite = null;
	
	private float shadow = 0f;

	private float preTime = 0f;
	
	private List<Entity> products = new ArrayList<Entity>();

	private float runDistance = 0f;

	public static final int NO_TARGET = 0;
	public static final int MOVE_EXACT = 1;
	public static final int MOVE_TO_RADIUS = 2;

	private boolean seesTarget = false;
	private float targetX = 0f;
	private float targetY = 0f;
	private float targetDistanceCounter = 0f;
	private float targetLastDistance = 0f;
	private float targetTime = 0f;
	private float targetRoute = 0f;
	private int targetDepth = -1;
	private int targetMovement = NO_TARGET;
	private Being target = null;
	private Set<Being> targetChain = new HashSet<Being>();

	private float accelerationFactor = 3000f;
	private float decelerationFactor = 20f;
	private float gravity = -2f;

	public void setStartX(float x) {
		setX(x);
		setOldX(x);
	}
	
	public void setStartY(float y) {
		setY(y);
		setOldY(y);
	}

	public void setStartZ(float z) {
		setZ(z);
		setOldZ(z);
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getOldX() {
		return oldX;
	}
	
	public void setOldX(float oldX) {
		this.oldX = oldX;
	}
	
	public float getOldY() {
		return oldY;
	}

	public void setOldY(float oldY) {
		this.oldY = oldY;
	}

	public float getOldZ() {
		return oldZ;
	}

	public void setOldZ(float oldZ) {
		this.oldZ = oldZ;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
	
	public float getSpeedY() {
		return speedY;
	}
	
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	public float getSpeedZ() {
		return speedZ;
	}

	public void setSpeedZ(float speedZ) {
		this.speedZ = speedZ;
	}

	public float getForceX() {
		return forceX;
	}

	public void setForceX(float forceX) {
		this.forceX = forceX;
	}
	
	public float getForceY() {
		return forceY;
	}

	public void setForceY(float forceY) {
		this.forceY = forceY;
	}
	
	public float getForceZ() {
		return forceZ;
	}

	public void setForceZ(float forceZ) {
		this.forceZ = forceZ;
	}

	public float getRadius() {
		return radius;
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getLookAngle() {
		return lookAngle;
	}
	
	public void setLookAngle(float lookAngle) {
		this.lookAngle = lookAngle;
	}
	
	public float getMoveAnimationCounter() {
		if (Math.abs(getSpeedX())<Constants.EPS && Math.abs(getSpeedY())<Constants.EPS && Math.abs(getSpeedZ())<Constants.EPS)
			return 0;
		else
			return moveAnimationCounter;
	}
	
	public void setMoveAnimationCounter(float moveAnimationCounter) {
		this.moveAnimationCounter = moveAnimationCounter;
	}

	public float getMoveAnimationFactor() {
		return moveAnimationFactor;
	}

	public void setMoveAnimationFactor(float moveAnimationFactor) {
		this.moveAnimationFactor = moveAnimationFactor;
	}

	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public SpriteData getSprite() {
		return sprite;
	}

	public void setSprite(SpriteData sprite) {
		this.sprite = sprite;
	}

	public float getShadow() {
		return shadow;
	}

	public void setShadow(float shadow) {
		this.shadow = shadow;
	}

	public float getPreTime() {
		return preTime;
	}

	public void setPreTime(float preTime) {
		this.preTime = preTime;
	}

	public List<Entity> getProducts() {
		return products;
	}

	public float getRunDistance() {
		return runDistance;
	}

	public void setRunDistance(float runDistance) {
		this.runDistance = runDistance;
	}

	public boolean seesTarget() {
		return seesTarget;
	}

	public void setSeesTarget(boolean seesTarget) {
		this.seesTarget = seesTarget;
	}

	public float getTargetX() {
		return targetX;
	}

	private void setTargetX(float targetX) {
		this.targetX = targetX;
	}

	public float getTargetY() {
		return targetY;
	}

	private void setTargetY(float targetY) {
		this.targetY = targetY;
	}

	private float getTargetDistanceCounter() {
		return targetDistanceCounter;
	}

	private void setTargetDistanceCounter(float targetDistanceCounter) {
		this.targetDistanceCounter = targetDistanceCounter;
	}

	public float getTargetLastDistance() {
		return targetLastDistance;
	}

	public void setTargetLastDistance(float targetLastDistance) {
		this.targetLastDistance = targetLastDistance;
	}

	public float getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(float targetTime) {
		this.targetTime = targetTime;
	}

	public float getTargetRoute() {
		return targetRoute;
	}

	private void setTargetRoute(float targetRoute) {
		this.targetRoute = targetRoute;
	}

	public int getTargetDepth() {
		return targetDepth;
	}

	private void setTargetDepth(int targetDepth) {
		this.targetDepth = targetDepth;
	}

	public int getTargetMovement() {
		return targetMovement;
	}

	private void setTargetMovement(int targetMovement) {
		this.targetMovement = targetMovement;
	}

	public Being getTarget() {
		return target;
	}

	private void setTarget(Being target) {
		this.target = target;
	}

	public Set<Being> getTargetChain() {
		return targetChain;
	}

	public float getAccelerationFactor() {
		return accelerationFactor;
	}

	public void setAccelerationFactor(float accelerationFactor) {
		this.accelerationFactor = accelerationFactor;
	}

	public float getDecelerationFactor() {
		return decelerationFactor;
	}

	public void setDecelerationFactor(float decelerationFactor) {
		this.decelerationFactor = decelerationFactor;
	}
	
	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	//
	
	public void tick(float delta) {
		if (getPreTime()>0f) {
			delta += getPreTime();
			setPreTime(0f);
		}
		pass(delta);
		decel(delta);
		gravity();
		acel(delta);
		move(delta);
		updateAnimationCounter(delta);
	}

	public float getAngle() {
		return getLookAngle();
	}

	public float getVerticalAngle() {
		float x = getSpeedXY();
		float y = getSpeedZ();
		return (float)Math.toDegrees(Math.atan2(y,x));
	}

	public float getSpeedXY() {
		return (float)Math.sqrt(getSpeedX()*getSpeedX()+getSpeedY()*getSpeedY());
	}

	public float distanceTo(float x,float y) {
		return Entity.distance(getX(),getY(),x,y);
	}

	public static float distance(float x1,float y1,float x2,float y2) {
		return (float)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));  
	}

	public float distanceTo(Entity other) {
		return distanceTo(other.getX(),other.getY());  
	}

	public float deltaMovement() {
		return (float)Math.sqrt((getX()-getOldX())*(getX()-getOldX())+(getY()-getOldY())*(getY()-getOldY()));  
	}
	
	public float shortestMovingDistanceTo(Entity other) {

		float ux = getX()-getOldX()+Constants.EPS*Constants.EPS;
		float uy = getY()-getOldY()+Constants.EPS*Constants.EPS;
		
		float vx = other.getX()-other.getOldX()+Constants.EPS*Constants.EPS;
		float vy = other.getY()-other.getOldY()+Constants.EPS*Constants.EPS;
		
		float wx = getOldX()-other.getOldX()+Constants.EPS*Constants.EPS;
		float wy = getOldY()-other.getOldY()+Constants.EPS*Constants.EPS;
		
		float a = ux*ux+uy*uy;
		float b = ux*vx+uy*vy;
		float c = vx*vx+vy*vy;
		float d = ux*wx+uy*wy;
		float e = vx*wx+vy*wy;

		float D = a*c - b*b;
		float sc, sN, sD = D;
		float tc, tN, tD = D;
	
	    if (Math.abs(D) < Constants.EPS) {
	        sN = 0.0f;
	        sD = 1.0f;
	        tN = e;
	        tD = c;
	    }
	    else {
	        sN = (b*e - c*d);
	        tN = (a*e - b*d);
	        if (sN < 0.0) {
	            sN = 0.0f;
	            tN = e;
	            tD = c;
	        }
	        else if (sN > sD) {
	            sN = sD;
	            tN = e + b;
	            tD = c;
	        }
	    }
	
	    if (tN < 0.0) {
	        tN = 0.0f;
	        if (-d < 0.0)
	            sN = 0.0f;
	        else if (-d > a)
	            sN = sD;
	        else {
	            sN = -d;
	            sD = a;
	        }
	    }
	    else if (tN > tD) {
	        tN = tD;
	        if ((-d + b) < 0.0)
	            sN = 0;
	        else if ((-d + b) > a)
	            sN = sD;
	        else {
	            sN = (-d + b);
	            sD = a;
	        }
	    }

	    sc = (float) (Math.abs(sN) < Constants.EPS ? 0.0 : sN / sD);
	    tc = (float) (Math.abs(tN) < Constants.EPS ? 0.0 : tN / tD);
	    
	    float dpx = wx+ux*sc-vx*tc;
	    float dpy = wy+uy*sc-vy*tc;
	    
	    return (float)Math.sqrt(dpx*dpx+dpy*dpy);	    
	}
	
	public boolean isCollidingWith(Entity other) {
		float minZ = Math.min(getZ(),getOldZ());
		float maxZ = Math.max(getZ(),getOldZ());
		float ominZ = Math.min(other.getZ(),other.getOldZ());
		float omaxZ = Math.max(other.getZ(),other.getOldZ());
		if (minZ>omaxZ+other.getHeight() || ominZ>maxZ+getHeight())
			return false;
		return shortestMovingDistanceTo(other)-getRadius()-other.getRadius()<=Constants.EPS;
	}	

	protected void move(float delta) {
		if (getSpeedXY()>getMaxSpeed()) {
			float factor = getMaxSpeed()/getSpeedXY();
			setSpeedX(getSpeedX()*factor);
			setSpeedY(getSpeedY()*factor);
		}
		if (getSpeedXY()<Constants.minimumSpeed) {
			setSpeedX(0);
			setSpeedY(0);
		}
		if (Math.abs(getSpeedZ())<Constants.minimumSpeed) 
			setSpeedZ(0);

		setOldX(getX());
		setOldY(getY());
		setOldZ(getZ());
		setX(getX()+getSpeedX()*delta);
		setY(getY()+getSpeedY()*delta);
		setZ(getZ()+getSpeedZ()*delta);
		
		setRunDistance(getRunDistance()+distance(getOldX(),getOldY(),getX(),getY()));
	}
		
	public void destroy() {
		setActive(false);
	}

	private void updateAnimationCounter(float delta) {
		setMoveAnimationCounter(((getMoveAnimationCounter()+Math.max(Math.abs(getSpeedX()),Math.abs(getSpeedY()))*delta*getMoveAnimationFactor()*Constants.animationSpeedFactor)+1000)%1000);
	}
	
	public void collideWithEntity(Entity e) { };
	
	public void collideWithWall() { };

	public void collideWithGround() { setZ(0); setSpeedZ(0); };

	public void collideWithCeiling() { };

	public void think(Level level) {
		forgetTarget();
		walkTowardTarget();
	}

	public float getMaxSpeed() { return 0f; }

	protected void pass(float delta) {
		if (getTargetMovement() != NO_TARGET) {
			setTargetDistanceCounter(getTargetDistanceCounter() + delta);
		}
	};

	protected void decel(float delta) {
		float reductionX = (getSpeedX() * getDecelerationFactor() * delta);
		if (Math.abs(reductionX) < Math.abs(getSpeedX()))
			setSpeedX(getSpeedX() - reductionX);
		else
			setSpeedX(0);
		float reductionY = (getSpeedY() * getDecelerationFactor() * delta);
		if (Math.abs(reductionY) < Math.abs(getSpeedY()))
			setSpeedY(getSpeedY() - reductionY);
		else
			setSpeedY(0);
		
	}
	
	protected void gravity() {
		if (getZ()>0)
			setForceZ(getGravity());
	}
	
	protected void acel(float delta) {
		if (Math.abs(getForceX())>Constants.maxForce)
			setForceX(getForceX()*Math.abs(Constants.maxForce/getForceX()));
		if (Math.abs(getForceY())>Constants.maxForce)
			setForceX(getForceY()*Math.abs(Constants.maxForce/getForceY()));
		setSpeedX(getSpeedX() + getForceX() * delta
				* getAccelerationFactor());
		setSpeedY(getSpeedY() + getForceY() * delta
				* getAccelerationFactor());
		setSpeedZ(getSpeedZ() + getForceZ() * delta
				* getAccelerationFactor());
	};

	public void reset() {
		setForceX(0f);
		setForceY(0f);
		setForceZ(0f);
		setSeesTarget(false);
	}

	public float getI(Level level) {
		return coordinatesToI(getX(),getY(),level);
	}

	public float getJ(Level level) {
		return coordinatesToJ(getX(),getY(),level);
	}

	public float getOldI(Level level) {
		return coordinatesToI(getOldX(),getOldY(),level);
	}

	public float getOldJ(Level level) {
		return coordinatesToJ(getOldX(),getOldY(),level);
	}

	public void setIJ(float i, float j, Level level) {
		setX(coordinatesToX(i,j,level));
		setY(coordinatesToY(i,j,level));
	}	
	
	protected float coordinatesToI(float x,float y,Level level) {
		return ((x-y)/(level.getTileFactor()*2f))+0.5f;
	}

	protected float coordinatesToJ(float x,float y,Level level) {
		return ((x+y)/(level.getTileFactor()*2f))+0.5f;
	}

	protected float coordinatesToX(float i,float j,Level level) {
		return level.getTileFactor()*(i+j-1);
	}

	protected float coordinatesToY(float i,float j,Level level) {
		return level.getTileFactor()*(j-i);
	}

	public boolean isApproximatingTo(Being b) {
		float dist1 = Entity.distance(getX(),getY(),b.getX(),b.getY());
		float dist2 = Entity.distance(getX()+getForceX(),getY()+getForceY(),b.getX(),b.getY());
        return dist2<dist1;
	}

	public void forgetTarget() {
		if (!seesTarget()) {
			if (getTargetDistanceCounter() > Constants.targetNoAdvanceTimeLimit) {
				float dist = distanceTo(getTargetX(), getTargetY());
				if (dist + Constants.targetNoAdvanceDistance > getTargetLastDistance()) {
					unsetTarget();
				}
				setTargetDistanceCounter(0f);
				setTargetLastDistance(Math.min(getTargetLastDistance(), dist));
			}
		}
	}

	public boolean isCloseToTarget() {
		float dx = getTargetX() - getX();
		float dy = getTargetY() - getY();
		return ((getTargetMovement() == MOVE_EXACT
				&& Math.abs(dx) < Constants.coordinatesEPS && Math.abs(dy) < Constants.coordinatesEPS) || (getTargetMovement() == MOVE_TO_RADIUS && Math
				.sqrt(dx * dx + dy * dy)
				- getRadius()
				- getTarget().getRadius() < Constants.coordinatesEPS));
	}

	public void walkTowardTarget() {
		if (getTargetMovement() != NO_TARGET) {
			float fx = 0;
			float fy = 0;
			float dx = getTargetX() - getX();
			float dy = getTargetY() - getY();
			float angle = (float) Math.atan2(dy, dx);
			if (Math.abs(dx) > Constants.coordinatesEPS)
				fx = (float) Math.cos(angle);
			if (Math.abs(dy) > Constants.coordinatesEPS)
				fy = (float) Math.sin(angle);
			if (fx == 0) {
				if (fy < 0)
					fy = -1;
				else if (fy > 0)
					fy = 1;
			}
			if (fy == 0) {
				if (fx < 0)
					fx = -1;
				else if (fx > 0)
					fx = 1;
			}
			if (fx != 0 && fy != 0) {
				float max = Math.max(Math.abs(fx), Math.abs(fy));
				fx /= max;
				fy /= max;
			}
			fx *= Constants.forceTargetFactor;
			fy *= Constants.forceTargetFactor;
			float ang = (float) ((360f + Math.toDegrees(Math.atan2(fy, fx))) % 360);
			setLookAngle(ang);
			if (!isCloseToTarget()) {
				setForceX(getForceX() + fx);
				setForceY(getForceY() + fy);
			}
		}

	}

	public void unsetTarget() {
		setTarget(0f, 0f, 0, null, NO_TARGET, 0f,0f,getTargetTime());
	}

	public void setTarget(float x, float y, int depth, Being target,int movement, float route,float distance,float time) {
		setTargetX(x);
		setTargetY(y);
		setTargetMovement(movement);
		setTargetTime(time);
		setTarget(target);
		setTargetDistanceCounter(0f);
		setTargetLastDistance(distance);
		if (movement != NO_TARGET) {
			setTargetDepth(depth);
			setTargetRoute(route);
		} else {
			setTargetRoute(0f);
			setTargetDepth(-1);
		}
		getTargetChain().clear();
	}

	public void moveTowards(Entity other) {
		float speed = getSpeedXY();
		float dx = other.getX() - getX();
		float dy = other.getY() - getY();
		float angle = (float) Math.atan2(dy, dx);
		setSpeedX(speed*(float)Math.cos(angle));
		setSpeedY(speed*(float)Math.sin(angle));
	}

}
