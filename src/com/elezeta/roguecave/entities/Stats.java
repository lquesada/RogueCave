package com.elezeta.roguecave.entities;

public class Stats {

	private float maxHp = 0f;
	private float maxMp = 0f;

	private float hpRegen = 0f;
	private float hpSteal = 0f;

	private float mpRegen = 0f;
	private float mpSteal = 0f;
	
	private float speed = 0f;
	private float recovery = 0f;
	private float accuracy = 0f;

	private float defense = 0f;
	private float attack = 0f;
	private float range = 0f;
	
	private float vision = 0f;

	public float getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(float maxHp) {
		this.maxHp = maxHp;
	}

	public float getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(float maxMp) {
		this.maxMp = maxMp;
	}
	
	public float getHpRegen() {
		return hpRegen;
	}

	public void setHpRegen(float hpRegen) {
		this.hpRegen = hpRegen;
	}
	public float getHpSteal() {
		return hpSteal;
	}

	public void setHpSteal(float hpSteal) {
		this.hpSteal = hpSteal;
	}

	public float getMpRegen() {
		return mpRegen;
	}

	public void setMpRegen(float mpRegen) {
		this.mpRegen = mpRegen;
	}
	
	public float getMpSteal() {
		return mpSteal;
	}

	public void setMpSteal(float mpSteal) {
		this.mpSteal = mpSteal;
	}

	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getRecovery() {
		return recovery;
	}
	
	public void setRecovery(float recovery) {
		this.recovery = recovery;
	}
	
	public float getAccuracy() {
		return accuracy;
	}
	
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public float getDefense() {
		return defense;
	}

	public void setDefense(float defense) {
		this.defense = defense;
	}

	public float getAttack() {
		return attack;
	}

	public void setAttack(float attack) {
		this.attack = attack;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getVision() {
		return vision;
	}

	public void setVision(float vision) {
		this.vision = vision;
	}
	
	public void add(Stats other) {
		setMaxHp(getMaxHp()+other.getMaxHp());
		setMaxMp(getMaxMp()+other.getMaxMp());
		setHpRegen(getHpRegen()+other.getHpRegen());
		setHpSteal(getHpSteal()+other.getHpSteal());
		setMpRegen(getMpRegen()+other.getMpRegen());
		setMpSteal(getMpSteal()+other.getMpSteal());
		setSpeed(getSpeed()+other.getSpeed());
		setRecovery(getRecovery()+other.getRecovery());
		setAccuracy(getAccuracy()+other.getAccuracy());
		setDefense(getDefense()+other.getDefense());
		setAttack(getAttack()+other.getAttack());
		setRange(getRange()+other.getRange());
		setVision(getVision()+other.getVision());
	}

	public void remove(Stats other) {
		setMaxHp(getMaxHp()-other.getMaxHp());
		setMaxMp(getMaxMp()-other.getMaxMp());
		setHpRegen(getHpRegen()-other.getHpRegen());
		setHpSteal(getHpSteal()-other.getHpSteal());
		setMpRegen(getMpRegen()-other.getMpRegen());
		setMpSteal(getMpSteal()-other.getMpSteal());
		setSpeed(getSpeed()-other.getSpeed());
		setRecovery(getRecovery()-other.getRecovery());
		setAccuracy(getAccuracy()-other.getAccuracy());
		setDefense(getDefense()-other.getDefense());
		setAttack(getAttack()-other.getAttack());
		setRange(getRange()-other.getRange());
		setVision(getVision()-other.getVision());
	}
	
	public void clear() {
		setMaxHp(0);
		setMaxMp(0);
		setHpRegen(0);
		setHpSteal(0);
		setMpRegen(0);
		setMpSteal(0);
		setSpeed(0);
		setRecovery(0);
		setAccuracy(0);
		setDefense(0);
		setAttack(0);
		setRange(0);
		setVision(0);
	}
	
	public float getPower() {
		float res = 0;
		res += getMaxHp();
		res += getMaxMp();
		res += getSpeed() * 5;
		res += getRecovery() * 5;
		res += getAccuracy() * 5;
		return res;
	}

	public boolean allBetterOrEqualThan(Stats other) {
		return getMaxHp()>=other.getMaxHp() &&
		getMaxMp()>=other.getMaxMp() &&
		getHpRegen()>=other.getHpRegen() &&
		getHpSteal()>=other.getHpSteal() &&
		getMpRegen()>=other.getMpRegen() &&
		getMpSteal()>=other.getMpSteal() &&
		getSpeed()>=other.getSpeed() &&
		getRecovery()>=other.getRecovery() &&
		getAccuracy()>=other.getAccuracy() &&
		getDefense()>=other.getDefense() &&
		getAttack()>=other.getAttack() &&
		getRange()>=other.getRange() &&
		getVision()>=other.getVision();
	}
}
