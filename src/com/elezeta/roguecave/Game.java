package com.elezeta.roguecave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.elezeta.roguecave.data.GameID;
import com.elezeta.roguecave.data.SpriteID;
import com.elezeta.roguecave.entities.Attack;
import com.elezeta.roguecave.entities.Being;
import com.elezeta.roguecave.entities.Entity;
import com.elezeta.roguecave.entities.Mob;
import com.elezeta.roguecave.entities.Pickup;
import com.elezeta.roguecave.entities.Player;
import com.elezeta.roguecave.inventory.Item;
import com.elezeta.roguecave.world.Level;
import com.elezeta.roguecave.world.RoutePlan;
import com.elezeta.roguecave.world.RoutePlanner;
import com.elezeta.roguecave.world.Tile;

public class Game {

	private Player player;

	private List<Entity> goodAttacks;

	private List<Entity> badAttacks;

	private List<Entity> goodBeings;

	private List<Entity> badBeings;

	private List<Entity> pickups;

	private Level level; //TODO world!
	
	private float time = 0f;
	
	private GameID gameId;
	
	private int number;
	
	public Game() {
		goodAttacks = new ArrayList<Entity>();
		badAttacks = new ArrayList<Entity>();
		badBeings = new ArrayList<Entity>();
		goodBeings = new ArrayList<Entity>();
		pickups = new ArrayList<Entity>();
		player = new Player();
		char[][] map = new char[4][4];
		map[0] = "WWWW".toCharArray();
		map[1] = "WWWW".toCharArray();
		map[2] = "WWWW".toCharArray();
		map[3] = "WWWW".toCharArray();
		Map<Character,Tile> tiles = new HashMap<Character,Tile>();
		tiles.put('·',new Tile(SpriteID.voidTile,false));
		tiles.put('W',new Tile(SpriteID.stoneTile,SpriteID.stoneWall,true));
		level = new Level(map,0,0,tiles,tiles.get('W'),64f,64f,60f);
	}
	
	public List<Entity> getGoodAttacks() {
		return goodAttacks;
	}

	public List<Entity> getBadAttacks() {
		return badAttacks;
	}

	public List<Entity> getGoodBeings() {
		return goodBeings;
	}

	public List<Entity> getBadBeings() {
		return badBeings;
	}

	public List<Entity> getPickups() {
		return pickups;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
		this.level = level;		
	}

	public float getTime() {
		return time;
	}

	private void setTime(float time) {
		this.time = time;
	}
	
	public GameID getGameID() {
		return gameId;
	}

	public int getNumber() {
		return number;
	}

	public void setGameID(GameID gId) {
		this.gameId = gId; 
	}

	public void setNumber(int number) {
		this.number = number; 
	}

	public void setPlayer(Player player) {
		this.player = player; 
	}
	
	public void tick(float delta) {

		setTime(getTime() + delta);
		
        magnetBetweenPickupsAndBeings(getPickups(),getGoodBeings());        

        collisionBetweenEntities(getBadBeings(),getBadBeings());        
        collisionBetweenEntities(getBadBeings(),getGoodBeings());        

    	slowBeings(getGoodBeings(),getBadBeings());

    	updateTargets(getBadBeings(),getGoodBeings());
    	
		think(getGoodBeings());
		think(getBadBeings());
		think(getPickups());
		think(getGoodAttacks());
		think(getBadAttacks());
		
    	moveAndCollide(getGoodBeings(),delta);
    	moveAndCollide(getGoodAttacks(),delta);
    	moveAndCollide(getBadAttacks(),delta);
    	moveAndCollide(getBadBeings(),delta);
    	moveAndCollide(getPickups(),delta);

        manageAttacks(getBadBeings(),delta);
        manageAttacks(getGoodBeings(),delta);

        addProducts(getBadBeings());
        addProducts(getGoodBeings());

        collisionBetweenAttacksAndEntities(getGoodAttacks(),getBadBeings());        
        collisionBetweenAttacksAndEntities(getBadAttacks(),getGoodBeings());        
        collisionBetweenEntities(getPickups(),getGoodBeings());        

    	updateTargetsDead(getBadBeings());

    	cleanEntities(getGoodBeings());
    	cleanEntities(getBadBeings());
    	cleanEntities(getGoodAttacks());
    	cleanEntities(getBadAttacks());
    	cleanEntities(getPickups());

    }

    public void reset() {
        resetEntities(getGoodBeings());
        resetEntities(getBadBeings());
        resetEntities(getGoodAttacks());
        resetEntities(getBadAttacks());
        resetEntities(getPickups());
    }

    private void resetEntities(List<Entity> entities) {
        for (int i = 0;i < entities.size();i++)
        	entities.get(i).reset();
  	}
    
	private void think(List<Entity> entities) {
        for (int i = 0;i < entities.size();i++)
        	entities.get(i).think(getLevel());
	}

	private void cleanEntities(List<Entity> entities) {
        for (Iterator<Entity> ite = entities.iterator();ite.hasNext();) {
        	Entity e = ite.next();
        	if (!e.isActive())
        		ite.remove();
        }
	}

	private void manageAttacks(List<Entity> entities,float delta) {
        for (int i = 0;i < entities.size();i++) {
        	Being a = (Being)entities.get(i);
        	if (a.isActive())
        		a.attack(delta);
        }
	}
	

	private void addProducts(List<Entity> entities) {
        for (int i = 0;i < entities.size();i++) {
        	Being e = (Being)entities.get(i);
        	for (int j = 0;j < e.getProducts().size();j++) {
        		Entity a = e.getProducts().get(j);
        		a.tick(0);
        		if (Attack.class.isInstance(a)) {
                	if (!RoutePlanner.checkRoute(getLevel(),e.getI(getLevel()),e.getJ(getLevel()),a.getI(getLevel()),a.getJ(getLevel()),a.getRadius()/getLevel().getTileFactor(),RoutePlanner.DO_NOT_REDIRECT).isHit()) {
                		if (Player.class.isInstance(e))
                			getGoodAttacks().add(a);
                		else
                			getBadAttacks().add(a);
                	}
        		}
        		if (Pickup.class.isInstance(a)) {
        			getPickups().add(a);
        		}

        	}
    		e.getProducts().clear();
        }
	}

	private void updateTargets(List<Entity> entities1,List<Entity> entities2) {
		Set<Being> pending = new HashSet<Being>();
		Set<Being> objectives = new HashSet<Being>();
		for (int i = 0;i < entities2.size();i++) {
			Being b = (Being)entities2.get(i);
			if (b.isAlive())
				objectives.add(b);
		}
		for (int i = 0;i < entities1.size();i++) {
			Being b = (Being)entities1.get(i);
			if (b.isAlive())
				pending.add(b);
		}

		updateTargets(pending,objectives,Being.MOVE_TO_RADIUS,1);
	}


	private void updateTargetsDead(List<Entity> entities) {
		Set<Being> pending = new HashSet<Being>();
		Set<Being> objectives = new HashSet<Being>();
		for (int i = 0;i < entities.size();i++) {
			Being b = (Being)entities.get(i);
			if (b.isAlive())
				pending.add(b);
			else
				objectives.add(b);
		}
		
		updateTargets(pending,objectives,Being.MOVE_TO_RADIUS,1);
	}

	private void updateTargets(Set<Being> pending,Set<Being> objectives,int mode,int depth) {
		Set<Being> newObjectives = new HashSet<Being>();
		Set<Being> newPending = new HashSet<Being>();
		for (Being objective : objectives) {
			for (Being current : pending) {
				boolean done = false;
				float dist = current.distanceTo(objective);
				float route = dist;
				if (!current.hates(objective))
					route += objective.getTargetRoute();
				if ((current.getStats().getVision()>=dist || (objective.getStats().getVision()>=dist && !current.hates(objective))) &&
						!RoutePlanner.checkRoute(getLevel(),current.getI(getLevel()),current.getJ(getLevel()),objective.getI(getLevel()),objective.getJ(getLevel()),0f,RoutePlanner.DO_NOT_REDIRECT).isHit()) {
					if (((route<current.getTargetRoute() || current.hates(objective)) && (depth<=current.getTargetDepth())) || current.getTarget()==null) {
						if (current.hates(objective) || current.getTargetTime()<objective.getTargetTime()) {
							current.setSeesTarget(true);
							current.setTarget(objective.getX(),objective.getY(),depth,objective,mode,route,dist,getTime());
							current.taint();
							objective.taint();
							done = true;
						}
					}
				}
				if (done || current.getTargetDepth()==1) {
					newObjectives.add(current);
				}
				else if (current.getTargetDepth()>depth || current.getTarget()==null) {
					newPending.add(current);
				}
			}
		}
		if (!newObjectives.isEmpty()) {
			updateTargets(newPending,newObjectives,Being.MOVE_TO_RADIUS, depth+1);
		}
	}
	
	private void slowBeings(List<Entity> entities1,List<Entity> entities2) {
        for (int i = 0;i < entities1.size();i++) {
        	Being a = (Being)entities1.get(i);
            for (int j = 0;j < entities2.size();j++) {
            	Being b = (Being)entities2.get(j);
            	if (a != b) {
            		boolean coll = a.isCollidingWith(b);
	        		if (coll && a.isApproximatingTo(b)) {
	        			a.setSlowFactor(a.getSlowFactor()+b.getStats().getPower());
	        		}
            	}
            }
        }
	}

	private void magnetBetweenPickupsAndBeings(List<Entity> entities1,List<Entity> entities2) {
        for (int i = 0;i < entities1.size();i++) {
        	Entity a = entities1.get(i);
        	if (a.isActive()) {
	            for (int j = 0;j < entities2.size();j++) {
	            	Entity b = entities2.get(j);
	            	if (b.isActive()) {
		            	if (a != b) {
		            		Item item = ((Pickup)a).getItem();
		            		Being being = (Being)b; 
		            		if (being.getInventory().wantToPickUp(item) && !being.getPickupTimer().containsKey(a)) {
			            		float dist = a.distanceTo(b);
			            		if (dist<=Constants.pickupMagnetDistance)
			            			if (!RoutePlanner.checkRoute(getLevel(),a.getI(getLevel()),a.getJ(getLevel()),b.getI(getLevel()),b.getJ(getLevel()),0f,RoutePlanner.DO_NOT_REDIRECT).isHit()) {
			            				a.moveTowards(b);
			            				a.setTarget(b.getX(),b.getY(),1,(Being)b,Entity.MOVE_EXACT,dist,dist,getTime());
				            	}
		            		}
		            	}
	            	}
	            }
        	}
        }
	}
	
	private void collisionBetweenEntities(List<Entity> entities1,List<Entity> entities2) {
        for (int i = 0;i < entities1.size();i++) {
        	Entity a = entities1.get(i);
        	if (a.isActive()) {
	            for (int j = 0;j < entities2.size();j++) {
	            	Entity b = entities2.get(j);
	            	if (b.isActive()) {
		            	if (a != b) {
			        		if (a.isCollidingWith(b)) {
			        			a.collideWithEntity(b);
			        			b.collideWithEntity(a);
			            	}
		            	}
	            	}
	            }
        	}
        }
	}
	
	private void collisionBetweenAttacksAndEntities(List<Entity> entities1,List<Entity> entities2) {
        for (int i = 0;i < entities1.size();i++) {
        	Entity a = entities1.get(i);
        	if (a.isActive()) {
	    		Entity bestHit = null;
	    		float bestHitDistance = 0;
	            for (int j = 0;j < entities2.size();j++) {
	            	Entity b = entities2.get(j);
	            	if (b.isActive()) {
		            	if (a != b) {
			        		if (a.isCollidingWith(b)) {
			        			float dist = a.distanceTo(b);
			        			if (bestHit == null || dist < bestHitDistance) {
			        				bestHit = b;
			        				bestHitDistance = dist; 
			        			}
			            	}
		            	}
	            	}
	            }
	            if (bestHit != null) {
	    			a.collideWithEntity(bestHit);
	    			bestHit.collideWithEntity(a);
    				Attack aa = (Attack)a;
        			if (aa.getLauncher() != null && Mob.class.isAssignableFrom(bestHit.getClass())) {
        				float dist = bestHit.distanceTo(aa.getLauncher());
        				bestHit.setTarget(aa.getOriginX(),aa.getOriginY(),1,aa.getLauncher(),Entity.MOVE_EXACT,dist,dist,getTime());
        			}
   			
	            }
        	}
        }
	}
	
	private void moveAndCollide(List<Entity> entities,float delta) {
        for (int i = 0;i < entities.size();i++) {
        	Entity e = entities.get(i);
        	if (e.isActive()) {
	        	e.tick(delta);
	
	        	int redirect;
	    		if (Player.class.isInstance(e) || Mob.class.isInstance(e))
	    			redirect = RoutePlanner.DO_REDIRECT;
	    		else
	    			redirect = RoutePlanner.DO_NOT_REDIRECT;
	    		
	        	RoutePlan pr = RoutePlanner.checkRoute(getLevel(), e.getOldI(getLevel()),e.getOldJ(getLevel()),e.getI(getLevel()),e.getJ(getLevel()), e.getRadius()/getLevel().getTileFactor(),redirect);
	       		e.setIJ(pr.getI(),pr.getJ(),getLevel());
	        	if (pr.getHit() != RoutePlanner.NO_HIT)
	        		e.collideWithWall();
	        	if (e.getZ()<0f)
	        		e.collideWithGround();
	        	if (e.getZ()+e.getHeight()>getLevel().getCeilingHeight())
	        		e.collideWithCeiling();
        	}
       	}
    }

	public int getEntityNumber() {
		return 1+getBadBeings().size()+getGoodAttacks().size()+getBadAttacks().size()+getPickups().size();
	}

}
