package com.elezeta.roguecave;

public abstract class Constants {

	private Constants() { }
	
	/* GRAPHICS */
	public final static float animationSpeedFactor = 20f;
	public final static float worldAnimationSpeedFactor = 700f;

    /* PHYSICS */
	public final static float speedFactor = 8f;
	public final static float minimumSpeed = 2f;
	public final static float attackDurationFactor = 1f;
	public final static float forceKeyboardFactor = 1f;
	public final static float forceTargetFactor = 1f;
	public final static float forcePushFactor = 50f;
	public final static float forcePushMax = 0.2f;
	public final static float forcePushInhibition = 0.1f;
	public final static float forcePushRandom = 50f;
	public final static float maxForce = 1f;

	/* ATTACKS */
	public final static float minimumRecovery = 0.00001f;
	public final static float minimumBurstInterval = 0.00001f;
	public final static float minimumBurst = 0.00001f;
	public final static float minimumAttackRadius = 0.1f;
	public final static float minimumAttackSpeed = 10f;
	public final static float attackingLookTime = 0.35f;
	public final static int maxAngleIterations = 15;

	/* VALUES */
	public final static float coordinatesEPS = 4f;
	public final static float EPS = 0.0001f;
	public final static float INF = 9999999f;

	/* TARGET */
	public final static float targetNoAdvanceTimeLimit = 0.05f;
	public final static float targetNoAdvanceDistance = 2f;

	/* WATCHDOG */
	public final static int planeRouteWatchDog = 100;
		
	/* ENTITIES */
	public final static float healFactor = 0.2f;
	public final static float recoverMpFactor = 0.2f;
	public final static float untaintedRecoverFactor = 50f;
	public final static float taintedTime = 3f;
	
	/* INVENTORY */
	public final static int inventorySize = 32;
	public final static float dropDoNotPickup = 3f;
	public final static float pickupMagnetDistance = 60f;
	public final static float dropVerticalPush = 100f;
}
