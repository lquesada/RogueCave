package com.elezeta.roguecave.world;

import com.elezeta.roguecave.Constants;

public final class RoutePlanner {

	public static int DO_REDIRECT = 0;
	public static int DO_NOT_REDIRECT = 1;
	
	public static int NO_HIT = 0;
	public static int HIT_LEFT = 1;
	public static int HIT_RIGHT = 2;
	public static int HIT_TOP = 4;
	public static int HIT_BOTTOM = 8;

	public static RoutePlan checkRoute(Level level,float i1,float j1,float i2,float j2,float r,int redirect) {
		if (!level.getTile((int)Math.floor(i1),(int)Math.floor(j1)).isWalkable())
			return new RoutePlan(i1,j1,0f,HIT_TOP|HIT_LEFT|HIT_RIGHT|HIT_BOTTOM);

		// Optimization.
		int imin = (int)Math.floor(Math.min(i1,i2)-2*r);
		int imax = (int)Math.floor(Math.max(i1,i2)+2*r);
		int jmin = (int)Math.floor(Math.min(j1,j2)-2*r);
		int jmax = (int)Math.floor(Math.max(j1,j2)+2*r);
		boolean anyBlock = false;
		for (int i = imin;i <= imax;i++)
			for (int j = jmin;j <= jmax;j++)
				if (!level.getTile(i, j).isWalkable())
					anyBlock = true;
		if (anyBlock==false)
			return new RoutePlan(i2,j2,(float)Math.sqrt((i2-i1)*(i2-i1)+(j2-j1)*(j2-j1)),NO_HIT);

		if (r == 0f && redirect == DO_NOT_REDIRECT)
			return checkRoute(level,i1,j1,i2,j2);

		float i1use = i1;
		float j1use = j1;

		float di = i2-i1use;
		float dj = j2-j1use;

		if (di == 0 && dj == 0)
			return new RoutePlan(i2,j2,0f,NO_HIT);

		float angle = ((float)Math.toDegrees(Math.atan2(dj,di))+360f)%360f;
		RoutePlan ra = squareRadiusRoute(level,i1,j1,i2,j2,angle,r);
		
		if (ra.isHit()) {
			if (redirect == DO_NOT_REDIRECT)
				return ra;
			if (ra.isDoubleHit())
				return ra;
			RoutePlan ro = redirectRadiusRoute(level,i1,j1,i2,j2,angle,r,ra.getHit());
			return new RoutePlan(ro.getI(),ro.getJ(),ro.getLength(),ra.getHit());
		}
		else {

			int hit = NO_HIT;
			float ang;
			
			boolean hitLeft = false;
			boolean hitRight = false;
			
			RoutePlan best = ra;
			float bestLength = ra.getLength();
			
			RoutePlan ro;
			ang = ((angle+45f)+360f)%360f;
			ro = squareRadiusRoute(level,i1,j1,i2,j2,ang,r);
			if (ro.isHit()) {
				hit = hit|ro.getHit();
				hitLeft = true;
				if (ro.getLength()<bestLength) {
					bestLength = ro.getLength();
					best = ro;
				}
			}
			if (!hitLeft) {
				ang = ((angle+90f)+360f)%360f;
				ro = squareRadiusRoute(level,i1,j1,i2,j2,ang,r);
				if (ro.isHit()) {
					hit = hit|ro.getHit();
					hitLeft = true;
					if (ro.getLength()<bestLength) {
						bestLength = ro.getLength();
						best = ro;
					}
				}
			}
			if (!hitLeft) {
				ang = ((angle+135f)+360f)%360f;
				ro = squareRadiusRoute(level,i1,j1,i2,j2,ang,r);
				if (ro.isHit()) {
					hit = hit|ro.getHit();
					hitLeft = true;
					if (ro.getLength()<bestLength) {
						bestLength = ro.getLength();
						best = ro;
					}
				}
			}
			ang = ((angle-45f)+360f)%360f;
			ro = squareRadiusRoute(level,i1,j1,i2,j2,ang,r);
			if (ro.isHit()) {
				hit = hit|ro.getHit();
				hitRight = true;
				if (ro.getLength()<bestLength) {
					bestLength = ro.getLength();
					best = ro;
				}
			}

			if (!hitRight) {
				ang = ((angle-90f)+360f)%360f;
				ro = squareRadiusRoute(level,i1,j1,i2,j2,ang,r);
				if (ro.isHit()) {
					hit = hit|ro.getHit();
					hitRight = true;
					if (ro.getLength()<bestLength) {
						bestLength = ro.getLength();
						best = ro;
					}
				}
			}
			if (!hitRight) {
				ang = ((angle-135f)+360f)%360f;
				ro = squareRadiusRoute(level,i1,j1,i2,j2,ang,r);
				if (ro.isHit()) {
					hit = hit|ro.getHit();
					hitRight = true;
					if (ro.getLength()<bestLength) {
						bestLength = ro.getLength();
						best = ro;
					}
				}
			}

			if (redirect == DO_NOT_REDIRECT || (hitLeft && hitRight))
				return best;

			else {
				if (hitLeft) {
					ang = ((angle-0.1f)+360f)%360f;
					ro = redirectRadiusRoute(level,i1,j1,i2,j2,ang,r,hit);
					return new RoutePlan(ro.getI(),ro.getJ(),ro.getLength(),hit);
				}
				if (hitRight) {
					ang = ((angle+0.1f)+360f)%360f;
					ro = redirectRadiusRoute(level,i1,j1,i2,j2,ang,r,hit);
					return new RoutePlan(ro.getI(),ro.getJ(),ro.getLength(),hit);
				}
				return best;
			}
			
		}
	}
	
	private static RoutePlan redirectRadiusRoute(Level level, float i1,float j1, float i2, float j2, float angle, float r,int lastHit) {

		float di = i2-i1;
		float dj = j2-j1;
		
		float len = (float)Math.sqrt(di*di+dj*dj);

		float angleMin = angle-Constants.EPS*10;
		float angleMax = angle+Constants.EPS*10;
		boolean c1 = angleMin>0 && angleMax<90;
		boolean c2 = angleMin>90 && angleMax<180;
	    boolean c3 = angleMin>180 && angleMax<270;
	    boolean c4 = angleMin>270 && angleMax<360;

		if (lastHit == HIT_RIGHT) {
			if (c2) {
				di = -len;
				dj = 0;
			}
			else if (c1) {
				di = len;
				dj = 0;
			}
			else if (c3 || c4) {
				di = 0;
				dj = -len;
			}
			else
				return new RoutePlan(i1,j1,0f,lastHit);
		}
		if (lastHit == HIT_BOTTOM) {
			if (c4) {
				di = 0;
				dj = -len;
			}
			else if (c1) {
				di = 0;
				dj = len;
			}
			else if (c2 || c3) {
				di = -len;
				dj = 0;
			}
			else
				return new RoutePlan(i1,j1,0f,lastHit);
		}

		if (lastHit == HIT_TOP) {
			if (c2) {
				di = 0;
				dj = len;
			}
			else if (c3) {
				di = 0;
				dj = -len;
			}
			else if (c1 || c4) {
				di = len;
				dj = 0;
			}
			else
				return new RoutePlan(i1,j1,0f,lastHit);
		}

		if (lastHit == HIT_LEFT) {
			if (c3) {
				di = -len;
				dj = 0;
			}
			else if (c4) {
				di = len;
				dj = 0;
			}
			else if (c1 || c2) {
				di = 0;
				dj = len;
			}
			else
				return new RoutePlan(i1,j1,0f,lastHit);
		}
		float ni = i1+di;
		float nj = j1+dj;
		angle = ((float)Math.toDegrees(Math.atan2(dj,di))+360f)%360f;
		
		float ang;
		
		RoutePlan best = squareRadiusRoute(level,i1,j1,ni,nj,angle,r);

		float bestLength = best.getLength();
		
		RoutePlan ro;
		ang = ((angle+30f)+360f)%360f;
		ro = squareRadiusRoute(level,i1,j1,ni,nj,ang,r);
		if (ro.isHit()) {
			if (ro.getLength()<bestLength) {
				bestLength = ro.getLength();
				best = ro;
			}
		}
		ang = ((angle-30f)+360f)%360f;
		ro = squareRadiusRoute(level,i1,j1,ni,nj,ang,r);
		if (ro.isHit()) {
			if (ro.getLength()<bestLength) {
				bestLength = ro.getLength();
				best = ro;
			}
		}
		return best;
	}

	private static RoutePlan squareRadiusRoute(Level level, float i1,float j1, float i2, float j2, float angle,float r) {
		angle = (float)Math.toRadians(angle);
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);
		if (sin<0)
			sin = -1;
		if (sin>0)
			sin = 1;
		if (cos<0)
			cos = -1;
		if (cos>0)
			cos = 1;
		return radiusRoute(level,i1,j1,i2,j2,r*cos,r*sin);
	}

	private static RoutePlan radiusRoute(Level level, float i1, float j1,float i2, float j2, float pointAI, float pointAJ) {
		RoutePlan res = checkRoute(level,i1,j1,i2+pointAI,j2+pointAJ);
		float len = (res.getLength()-(float)Math.sqrt(pointAI*pointAI+pointAJ*pointAJ));
		if (len < 0)
			return new RoutePlan(i1,j1,0f,res.getHit());
		else {
			RoutePlan ro = new RoutePlan(res.getI()-pointAI,res.getJ()-pointAJ,len,res.getHit());
			return ro;
		}
	}

	private static RoutePlan checkRoute(Level level,float i1,float j1,float i2,float j2) {

		int wDog = 0;
		float i1use = i1;
		float j1use = j1;

		if (Math.abs(i1use-i2)<Constants.EPS && Math.abs(j1use-j2)<Constants.EPS)
			return new RoutePlan(i2,j2,0f,NO_HIT);

		//First, iToNext and jToNext, the i and j movements needed to get to the tile border, are calculated.
		
		int hit = NO_HIT;
		boolean target = false;
		float itf = i1use,jtf = j1use;

		while (hit == NO_HIT && !target) {

			wDog++;
			if (wDog > Constants.planeRouteWatchDog) {
				System.err.println("--------------");
				System.err.println("CRITICAL ERROR");
				System.err.println("PlaneRoute Watchdog timer out!");
				System.err.println("i1 "+i1+" j1 "+j1+" i2 "+i2+" j2 "+j2+" tileFactor "+level.getTileFactor());
				System.err.println("--------------");
				return new RoutePlan(i1,j1,0f,HIT_BOTTOM|HIT_TOP|HIT_LEFT|HIT_RIGHT);
			}
			
			float di = i2-i1use;
			float dj = j2-j1use;
			float iToNextOrig = di;
			float jToNextOrig = dj;
			if (di>0)
				iToNextOrig = Math.min((float)(1-(i1use-Math.floor(i1use))), iToNextOrig);
			if (di<0)
				iToNextOrig = Math.max((float)(Math.floor(i1use)-i1use), iToNextOrig);
			if (dj>0)
				jToNextOrig = Math.min((float)(1-(j1use-Math.floor(j1use))),jToNextOrig);
			if (dj<0)
				jToNextOrig = Math.max((float)((Math.floor(j1use)-j1use)),jToNextOrig);
	
			//Then, they are proportionally adjusted so only the closest one is hit.
			
			float iFactor = iToNextOrig/di;
			float jFactor = jToNextOrig/dj;
			float maxFactor = Math.max(iFactor,jFactor);
			float iToNext = iToNextOrig;
			float jToNext = jToNextOrig;
			if (maxFactor != 0) {
				iFactor /= maxFactor;
				jFactor /= maxFactor;
				if (di!=0 && dj!=0) {
					iToNext *= jFactor; 
				    jToNext *= iFactor;
				}
			}
			
			//Now, the current source and target coordinates are calculated.
	
			float is = i1use;
			float js = j1use;
			float it = i1use+iToNext;
			float jt = j1use+jToNext;
			
			float itd = it-(float)Math.floor(is);
			float jtd = jt-(float)Math.floor(js);			
			
			//The movement is applied
	
			int iTile = (int)Math.floor(is);
			int jTile = (int)Math.floor(js);
	
			float total = (float)Math.sqrt(iToNext*iToNext+jToNext*jToNext);
			float limit = total;

			if (di > 0 && itd+Constants.EPS>=1 && !level.getTile(iTile+1,jTile).isWalkable()) {
				limit = Math.min(limit,total/(itd+Constants.EPS));
				hit = hit|HIT_BOTTOM;
			}
			
			if (di < 0 && itd-Constants.EPS<0 && !level.getTile(iTile-1,jTile).isWalkable()) {
				limit = Math.min(limit,-total/(itd-Constants.EPS));
				hit = hit|HIT_TOP;
			}
			
			if (dj > 0 && jtd+Constants.EPS>=1 && !level.getTile(iTile,jTile+1).isWalkable()) {
				limit = Math.min(limit,total/(jtd+Constants.EPS));
				hit = hit|HIT_RIGHT;
			}

			if (dj < 0 && jtd-Constants.EPS<0 && !level.getTile(iTile,jTile-1).isWalkable()) {
				limit = Math.min(limit,-total/(jtd-Constants.EPS));
				hit = hit|HIT_LEFT;
			}
			
			
			if (total==0)
				limit = limit/(total+Constants.EPS);
			else
				limit /= total;
			

			itf = i1use+iToNext*limit;
			jtf = j1use+jToNext*limit;

			if (di<0)
				itf -= Constants.EPS/2;
			if (di>0)
				itf += Constants.EPS/2;
			if (dj<0)
				jtf -= Constants.EPS/2;
			if (dj>0)
				jtf += Constants.EPS/2;

			boolean iAdvance = Math.abs(iToNext*limit-iToNextOrig)<Constants.EPS;
			boolean jAdvance = Math.abs(jToNext*limit-jToNextOrig)<Constants.EPS;

			if (hit == NO_HIT) {
				if (Math.abs(itf-i2)<Constants.EPS && Math.abs(jtf-j2)<Constants.EPS)
					target = true;
				else {
					if (iAdvance) {
						if (di > 0)
							i1use = itf;
						if (di < 0)
							i1use = itf;
					}
					if (jAdvance) {
						if (dj > 0)
							j1use = jtf;
						if (dj < 0)
							j1use = jtf;
					}
				}
			}
		}

		float length = (float)Math.sqrt((itf-i1)*(itf-i1)+(jtf-j1)*(jtf-j1));
		if (i2-i1<0)
			itf += Constants.EPS*2;
		if (i2-i1>0)
			itf -= Constants.EPS*2;
		if (j2-j1<0)
			jtf += Constants.EPS*2;
		if (j2-j1>0)
			jtf -= Constants.EPS*2;

		return new RoutePlan(itf,jtf,length,hit);
	}
	
}
