package com.elezeta.roguecave.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.inventory.Inventory;
import com.elezeta.roguecave.inventory.Item;
import com.elezeta.roguecave.inventory.ItemWithAmmo;
import com.elezeta.roguecave.world.Level;

public abstract class Being extends Entity {

	private boolean alive = true;

	private float handsHeight = 0f;

	private int level = 1;
	private float hp = 0f;
	private float mp = 0f;

	private Stats selfStats = new Stats();
	
	private Inventory inventory = new Inventory();

	//
	
	private float attackingDistance = 0f;
	private float attackingHeight = 0f;
	private float attackingLookTime = 0f;
	private boolean attacking = false;
	private boolean startedAttacking = false;
	private float attackAngle = 0f;
	private ItemWithAmmo attackBurstItem = null;
	private float attackBurstTimePassed = 0f;
	private float attackBurstScanningAct = 0f;
	private float attackBurstScanningInc = 0f;

	private float busyTime = 0f;
	private float taintedTime = 0f;
	private float slowFactor = 0f;

	private Map<Pickup,Float> pickupTimer = new HashMap<Pickup,Float>();
	
	public boolean isAlive() {
		return alive;
	}

	private void setAlive(boolean alive) {
		this.alive = alive;
	}

	public float getHandsHeight() {
		return handsHeight;
	}
	
	public void setHandsHeight(float handsHeight) {
		this.handsHeight = handsHeight;
	}

	public float getAttackingHeight() {
		return attackingHeight;
	}
	
	public void setAttackingHeight(float attackingHeight) {
		this.attackingHeight = attackingHeight;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public float getHp() {
		return hp;
	}

	public int getHpRound() {
		int hp = Math.round(getHp());
		if (getHp()>0 && hp==0)
			hp = 1;
		return hp;
	}
	
	public void setHp(float hp) {
		this.hp = hp;
	}

	public float getMp() {
		return mp;
	}

	public void setMp(float mp) {
		this.mp = mp;
	}

	public Stats getStats() {
		Stats s = new Stats();
		s.clear();
		s.add(getSelfStats());
		s.add(getInventory().getStats());
		return s;
	}

	public Stats getStatsWithReplace(Item item) {
		Stats s = new Stats();
		s.clear();
		s.add(getSelfStats());
		s.add(getInventory().getStatsWithReplace(item));
		return s;
	}

	public Stats getStatsWithReplace(Item item,Item item2) {
		Stats s = new Stats();
		s.clear();
		s.add(getSelfStats());
		s.add(getInventory().getStatsWithReplace(item,item2));
		return s;
	}

	public Stats getStatsWithRemove(int slot) {
		Stats s = new Stats();
		s.clear();
		s.add(getSelfStats());
		s.add(getInventory().getStatsWithRemove(slot));
		return s;
	}
	public Stats getSelfStats() {
		return selfStats;
	}

	public void setSelfStats(Stats selfStats) {
		this.selfStats = selfStats;
	}

	public Inventory getInventory() {
		return inventory;
	}

	private float getAttackingDistance() {
		return attackingDistance;
	}

	public void setAttackingDistance(float attackingDistance) {
		this.attackingDistance = attackingDistance;
	}

	public float getAttackingLookTime() {
		return attackingLookTime;
	}

	public void setAttackingLookTime(float attackingLookTime) {
		this.attackingLookTime = attackingLookTime;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean startedAttacking() {
		return startedAttacking;
	}

	public void setStartedAttacking(boolean startedAttacking) {
		this.startedAttacking = startedAttacking;
	}

	public float getAttackAngle() {
		return attackAngle;
	}

	public void setAttackAngle(float attackAngle) {
		this.attackAngle = attackAngle;
	}

	public ItemWithAmmo getAttackBurstItem() {
		return attackBurstItem;
	}

	public void setAttackBurstItem(ItemWithAmmo attackBurstItem) {
		this.attackBurstItem = attackBurstItem;
	}

	public float getAttackBurstTimePassed() {
		return attackBurstTimePassed;
	}

	public void setAttackBurstTimePassed(float attackBurstTimePassed) {
		this.attackBurstTimePassed = attackBurstTimePassed;
	}

	public float getAttackBurstScanningAct() {
		return attackBurstScanningAct;
	}

	public void setAttackBurstScanningAct(float attackBurstScanningAct) {
		this.attackBurstScanningAct = attackBurstScanningAct;
	}

	public float getAttackBurstScanningInc() {
		return attackBurstScanningInc;
	}

	public void setAttackBurstScanningInc(float attackBurstScanningInc) {
		this.attackBurstScanningInc = attackBurstScanningInc;
	}

	public float getBusyTime() {
		return busyTime;
	}

	public void setBusyTime(float busyTime) {
		this.busyTime = busyTime;
	}

	public float getTaintedTime() {
		return taintedTime;
	}

	public void setTaintedTime(float taintedTime) {
		this.taintedTime = taintedTime;
	}

	public float getSlowFactor() {
		return slowFactor;
	}

	public void setSlowFactor(float slowFactor) {
		this.slowFactor = slowFactor;
	}

	public Map<Pickup,Float> getPickupTimer() {
		return pickupTimer;
	}
	
	public void reset() {
		super.reset();
		setAttacking(false);
		setStartedAttacking(false);
		setSlowFactor(0f);
	}

	public void hit(Attack attack) {
		taint();
		float attack1 = attack.getDamage()+attack.getAttack()-getStats().getDefense();
		float attack2 = attack.getDamage();
		float attack3 = attack.getDamage()/10;
		float attackf = Math.max(Math.min(attack1,attack2),attack3);
		hurt(attackf);
	}

	public void heal(float hp) {
		if (isAlive()) {
			setHp(getHp() + hp);
			if (getHp() >= getStats().getMaxHp())
				setHp(getStats().getMaxHp());
		}
	}

	public void hurt(float hp) {
		taint();
		setHp(getHp() - hp);
		if (getHp() <= 0) {
			setHp(0);
			setAlive(false);
		}
	}

	public void recoverMp(float mp) {
		setMp(getMp() + mp);
		if (getMp() >= getStats().getMaxMp())
			setMp(getStats().getMaxMp());
	}

	public void spendMp(float mp) {
		taint();
		setMp(getMp() - mp);
	}

	@Override
	public void think(Level level) {
		if (!hates(getTarget()) && isCloseToTarget())
			if (getTarget() != null)
				if (getTarget().getTarget() == null)
					unsetTarget();
		super.think(level);
	}

	@Override
	public float getAngle() {
		if (getAttackBurstTimePassed()>0f) {
			return (getAttackBurstScanningAct()+3600f)%360f;
		}
		else if (getAttackingLookTime()<=0f || !getInventory().getSelectedItem().requiresTarget()) {
			return getLookAngle();
		}
		else {
			return getAttackAngle();
		}
	}

	@Override
	public float getMaxSpeed() {
		return getStats().getSpeed() * Constants.speedFactor;
	}

	@Override
	protected void pass(float delta) {
		super.pass(delta);
		
		float burst = getAttackBurstTimePassed();
		if (burst > 0)
			setAttacking(true);

		float busy = getBusyTime();
		if (busy > 0)
			busy = busy - delta;
		if (busy <= 0)
			busy = -delta;
		if (burst>0f)
			if (busy<burst)
				busy = burst+Constants.EPS;
		if (busy <= 0) {
			if (getInventory().getUseSelf() && !startedAttacking()) {
				getInventory().setUseSelf(false);
				setAttacking(false);
				setAttackBurstItem(null);
			}
		}
		setBusyTime(busy);
		

		float tainted = getTaintedTime();
		if (tainted > 0)
			tainted = tainted - delta;
		if (tainted < 0)
			tainted = 0;
		setTaintedTime(tainted);

		float untainted = 1f;
		if (tainted == 0)
			untainted = Constants.untaintedRecoverFactor;

		float attack = getAttackingLookTime();
		if (attack > 0)
			attack = attack - delta;
		if (attack < 0)
			attack = 0;
		setAttackingLookTime(attack);

		heal(getStats().getHpRegen() * delta * Constants.healFactor * untainted);
		recoverMp(getStats().getMpRegen() * delta * Constants.recoverMpFactor * untainted);
		
		dropInventory();
		
		for (Iterator<Entry<Pickup,Float>> ite = getPickupTimer().entrySet().iterator();ite.hasNext();) {
			Entry<Pickup,Float> e = ite.next();
			float time = e.getValue();
			time -= delta;
			if (time<=0)
				ite.remove();
			else
				e.setValue(time);
		}
	}

	private void dropInventory() {
		for (int i = 0;i < getInventory().getDrop().size();i++) {
			drop(getInventory().getDrop().get(i));
		}
		getInventory().getDrop().clear();
	}
	
	public void drop(Item item) {
		Pickup p = new Pickup();
		p.setItem(item);
		p.setStartX(getX());
		p.setStartY(getY());
		p.setStartZ(0);
		float angle = (float)Math.toDegrees(Math.random()*360f);
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);
		float push = 0.2f+(float)Math.random()*0.4f;
		p.setSpeedX(cos*push*p.getAccelerationFactor());
		p.setSpeedY(sin*push*p.getAccelerationFactor());
		p.setSpeedZ(Constants.dropVerticalPush);
		getPickupTimer().put(p,Constants.dropDoNotPickup);
		getProducts().add(p);
	}
	
	@Override
	protected void acel(float delta) {
		float inhibition = 0f;
		float power = getStats().getPower();
		if (getSlowFactor() >= power)
			inhibition = 1f;
		if (getSlowFactor() < power && power > 0f && getSlowFactor() > 0f)
			inhibition = getSlowFactor() / power;
		setSpeedX(getSpeedX() + getForceX() * delta
				* getAccelerationFactor() * getStats().getSpeed()/20f * (1f - inhibition));
		setSpeedY(getSpeedY() + getForceY() * delta
				* getAccelerationFactor() * getStats().getSpeed()/20f * (1f - inhibition));
		setSpeedZ(getSpeedZ() + getForceZ() * delta
				* getAccelerationFactor() * getStats().getSpeed()/20f);
	}

	@Override
	public void collideWithEntity(Entity e) {
		if (Attack.class.isInstance(e)) {
			hit(((Attack) e));
			if (!isAlive()) {
				getInventory().destroy();
				dropInventory();
				getPickupTimer().clear();
				destroy();
			}
		}
	}

	public void taint() {
		setTaintedTime(Constants.taintedTime);
	}

	private float calculateAngleFromDistance(float height, float speed,float gravity, float distance,float epsilon,float min,float max) {
		float lo = min;
        float hi = max;
        float mid;
        float val;
        float dif;
        int i = 0;
        do {
        	i++;
        	mid = (lo+hi)/2f;
            val = calculateParabolicReach(height,speed,-gravity,mid);
            if (distance>val)
            	lo=mid;
            else
            	hi=mid;
            dif = Math.abs(val-distance);
        } while (dif>epsilon && i < Constants.maxAngleIterations);
        return mid;
	}

	private float calculateParabolicReach(float h,float v,float g,float a) {
		g*=getAccelerationFactor();
		if (a == 0f) {
			float t = (float)Math.sqrt(2*h/g);
			return t*v;
		}
		if (a < 0f) {
			float vv = -(float)(v*Math.sin(Math.toRadians(a)));
			float vh = (float)(v*Math.cos(Math.toRadians(a)));
			float tc = (float)((Math.sqrt(2f*g*h+Math.pow(vv,2))-vv)/g);
			float tf=tc;
			return tf*vh;
		}
		else {
			float vv = (float)(v*Math.sin(Math.toRadians(a)));
			float vh = (float)(v*Math.cos(Math.toRadians(a)));
			float ts=(vv/g);
			float tc = (float)((Math.sqrt(2f*g*h+Math.pow(vv,2))-vv)/g);
			float tf=ts*2+tc;
			return tf*vh;
		}
	}
	
	public void attack(float delta) {
	ItemWithAmmo item;
	if (getAttackBurstItem() != null)
		item = getAttackBurstItem();
	else
		item = getInventory().getSelectedItem();
    	if ((startedAttacking()) || (item.hasAutoFire() && isAttacking())) {
			if (item.isUseable()) {
	    		if (Constants.attackingLookTime>getAttackingLookTime())
	    			setAttackingLookTime(Constants.attackingLookTime);
				taint();
				float passed = 0f;
				float recovery = item.getRecoveryTime();
				float burst = item.getAttackBurstTime();
				boolean canShoot = true;
				
				float left = delta;

				while (getBusyTime() <= 0 && getMp() >= item.getManaCost() && canShoot) {
					canShoot = false;
					if (item.getRequiresAmmoID() != null) {
						if (item.getRequiresAmmoID() == item.getProvidesAmmoID()) {
							if (item.isStackable() && item.getAmmo().getQuantity() > 0 && getMp()>=item.getManaCost())
								canShoot = true;
						}
					}
					else if (getMp()>=item.getManaCost())
						canShoot = true;
						
					
					if (canShoot) {
						boolean consume = false;
						if (item.getAttackBurst()) {
							setAttackBurstScanningAct((getAngle()+item.getAttackBurstScanningMin()+360f)%360f);
							setAttackBurstTimePassed(item.getAttackBurstTime());
							setAttackBurstItem(item);
							setAttackBurstScanningInc((item.getAttackBurstScanningMax()-item.getAttackBurstScanningMin())/item.getAttackBurstTime());
							setAttackingLookTime(burst+Constants.attackingLookTime);
							consume = true;
						}
						else {
							shoot(item,passed,false);
							consume = true;
						}
						if (consume) {
							spendMp(item.getManaCost());
							if (item.getRequiresAmmoID() != null) {
								if (item.getAmmo() != null)
									if (item.getAmmo().isStackable())
										getInventory().consume(item.getAmmo());
							} else if (item.isStackable()) {
								getInventory().consume(item.getAmmo());
							}
						}
						setBusyTime(getBusyTime()+recovery);
						passed += recovery;
					}
				}
	
				if (getAttackBurstItem()!=null) {
					float interval = getAttackBurstItem().getAttackBurstInterval();
					if (getAttackBurstTimePassed()>0f && left>0f) {
						taint();
						passed = 0f;
						while (getAttackBurstTimePassed() >= interval && left>=interval) {
							shoot(getAttackBurstItem(),delta-passed,true);
							setAttackBurstTimePassed(getAttackBurstTimePassed()-interval);
							setAttackBurstScanningAct(getAttackBurstScanningAct()+getAttackBurstScanningInc()*interval);
							passed += interval;
							left -= interval;
						}
						setAttackBurstTimePassed(getAttackBurstTimePassed()-left);
					}
					if (getAttackBurstTimePassed()<=0f) {
						setAttackBurstTimePassed(0f);
						setAttacking(false);
						setAttackBurstItem(null);
					}
				}
			}
    	}
	}
	
	private void shoot(ItemWithAmmo item,float passed,boolean burst) {

		float dist = Math.max(getRadius(),item.getAttackRadius());
		if (dist < 0)
			dist = 0;

		float startX;
		float startY;
		if (item.heals()) {
			heal(item.getHeal());
		}
		if (item.attacks()) {
			float angle = (float)Math.random()*360; 
			float vector = -1f+((float)Math.random()*2f); 
			
			float sin = (float) Math.sin(Math.toRadians(angle));
			float cos = (float) Math.cos(Math.toRadians(angle));
			
			float hAngle = item.getAttackHorizontalAngleVariation()*cos*vector;
			float vAngle = item.getAttackVerticalAngleVariation()*sin*vector;

			float speedX;
			float speedY;
			float speedZ;
			float speed = item.getAttackSpeed();
			if (item.getAttackGravity() != 0f) {
				float distt = (float)Math.min(getAttackingDistance(),item.getStats().getRange()*10f);
				float max = calculateParabolicReach(getHandsHeight(),speed,-item.getAttackGravity(),37);
				while (max<distt) {
					speed*= distt/max;
					max = calculateParabolicReach(getHandsHeight()-getAttackingHeight(),speed,-item.getAttackGravity(),37);
				}
				vAngle += calculateAngleFromDistance(getHandsHeight()-getAttackingHeight(),speed,item.getAttackGravity(),distt,10,item.getAttackMinVerticalAngle(),item.getAttackMaxVerticalAngle());
			}
			
			float hFinalAngle;
			if (burst)
				hFinalAngle = (getAttackBurstScanningAct()+hAngle+3600f) % 360f;
			else
				hFinalAngle = (getAngle()+hAngle+3600f) % 360f;
			float lookX = (float) Math.cos(Math.toRadians(hFinalAngle));
			float lookY = (float) Math.sin(Math.toRadians(hFinalAngle));

			startX = getX() + dist * lookX;
			startY = getY() + dist * lookY;
			
			float vFinalAngle = (vAngle+3600f)%360f;

			speedZ = (float) Math.sin(Math.toRadians(vFinalAngle)) * speed;
			float speedH = (float) Math.cos(Math.toRadians(vFinalAngle)) * speed;
			speedX = (float) Math.cos(Math.toRadians(hFinalAngle)) * speedH;
			speedY = (float) Math.sin(Math.toRadians(hFinalAngle)) * speedH;
			float speedm = (float) Math.sqrt(speedX * speedX + speedY * speedY);
			float speedrand = (float) (Math.random() * 2f * item.getAttackSpeedVariation()) - item.getAttackSpeedVariation();
			float speednew = speedm + speedrand;
			if (speedm != 0) {
				speedX *= speednew / speedm;
				speedY *= speednew / speedm;
				speedZ *= speednew / speedm;
			}
			Attack a = new Attack();
			a.setDamage(item.getAttackDamage());
			a.setDuration(item.getAttackDuration() * Constants.attackDurationFactor);
			a.setLauncher(this);
			a.setLookAngle(hFinalAngle);
			a.setOriginX(getX());
			a.setOriginY(getY());
			a.setStartX(startX);
			a.setStartY(startY);
			a.setStartZ(getHandsHeight()-item.getAttackHeight()/2);
			a.setHeight(item.getAttackHeight());
			a.setRadius(item.getAttackRadius());
			a.setSpeedX(speedX);
			a.setSpeedY(speedY);
			a.setSprite(item.getAttackSprite());
			a.setShadow(item.getAttackShadow());
			a.setPreTime(passed);
			a.setSpeedZ(speedZ);
			a.setGravity(item.getAttackGravity());
			a.setAttack(item.getStats().getAttack());
			a.setHitCeiling(item.getAttackHitCeiling());
			getProducts().add(a);
			
		}		
	}

	public boolean hates(Being b) {
		return false;
	}
	
}
