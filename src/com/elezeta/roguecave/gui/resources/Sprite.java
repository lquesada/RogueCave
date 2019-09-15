package com.elezeta.roguecave.gui.resources;

import com.elezeta.roguecave.Constants;
import com.elezeta.roguecave.entities.Entity;

public final class Sprite {

	private Graphic[] graphicsW;
	private Graphic[] graphicsE;
	private Graphic[] graphicsN;
	private Graphic[] graphicsS;
	private Graphic[] graphicsNW;
	private Graphic[] graphicsNE;
	private Graphic[] graphicsSW;
	private Graphic[] graphicsSE;
	
	private boolean random;
	private boolean rotate;
	private float tail;

	public Sprite(Graphic graphic) {
		this(new Graphic[] {graphic});
	}
	
	public Sprite(Graphic[] graphics) {
		this(graphics,graphics,graphics,graphics);
	}
	
	public Sprite(Graphic[] graphics,boolean random) {
		this(graphics,graphics,graphics,graphics,random);
	}

	public Sprite(Graphic[] graphics,boolean random,boolean rotate) {
		this(graphics,graphics,graphics,graphics,random);
		setRotate(rotate);
	}
	public Sprite(Graphic[] graphics,boolean random,boolean rotate,float tail) {
		this(graphics,graphics,graphics,graphics,random);
		setRotate(rotate);
		setTail(tail);
	}
		
	public Sprite(Graphic[] graphicsN,Graphic[] graphicsE,Graphic[] graphicsS,Graphic[] graphicsW) {
		this(graphicsN,graphicsE,graphicsS,graphicsW,false);
	}

	public Sprite(Graphic[] graphicsN,Graphic[] graphicsE,Graphic[] graphicsS,Graphic[] graphicsW,boolean random) {
		this(graphicsN,graphicsE,graphicsE,graphicsE,graphicsS,graphicsW,graphicsW,graphicsW,random);
	}
	
	
	public Sprite(Graphic[] graphicsN,Graphic[] graphicsNE,Graphic[] graphicsE,Graphic[] graphicsSE,Graphic[] graphicsS,Graphic[] graphicsSW,Graphic[] graphicsW,Graphic[] graphicsNW) {
		this(graphicsN,graphicsNE,graphicsE,graphicsSE,graphicsS,graphicsSW,graphicsW,graphicsNW,false);
	}
	
	public Sprite(Graphic[] graphicsN,Graphic[] graphicsNE,Graphic[] graphicsE,Graphic[] graphicsSE,Graphic[] graphicsS,Graphic[] graphicsSW,Graphic[] graphicsW,Graphic[] graphicsNW,boolean random) {
		setGraphicsN(graphicsN);
		setGraphicsE(graphicsE);
		setGraphicsS(graphicsS);
		setGraphicsW(graphicsW);
		setGraphicsNW(graphicsNW);
		setGraphicsNE(graphicsNE);
		setGraphicsSW(graphicsSW);
		setGraphicsSE(graphicsSE);
		setRandom(random);
		setRotate(false);
	}

	private Graphic[] getGraphicsW() {
		return graphicsW;
	}
	
	private void setGraphicsW(Graphic[] graphics) {
		this.graphicsW = graphics;
	}

	private Graphic[] getGraphicsE() {
		return graphicsE;
	}
	
	private void setGraphicsE(Graphic[] graphics) {
		this.graphicsE = graphics;
	}

	private Graphic[] getGraphicsN() {
		return graphicsN;
	}
	
	private void setGraphicsN(Graphic[] graphics) {
		this.graphicsN = graphics;
	}

	private Graphic[] getGraphicsS() {
		return graphicsS;
	}
	
	private void setGraphicsS(Graphic[] graphics) {
		this.graphicsS = graphics;
	}

	private Graphic[] getGraphicsNW() {
		return graphicsNW;
	}

	private void setGraphicsNW(Graphic[] graphicsNW) {
		this.graphicsNW = graphicsNW;
	}

	private Graphic[] getGraphicsNE() {
		return graphicsNE;
	}

	private void setGraphicsNE(Graphic[] graphicsNE) {
		this.graphicsNE = graphicsNE;
	}

	private Graphic[] getGraphicsSW() {
		return graphicsSW;
	}

	private void setGraphicsSW(Graphic[] graphicsSW) {
		this.graphicsSW = graphicsSW;
	}

	private Graphic[] getGraphicsSE() {
		return graphicsSE;
	}

	private void setGraphicsSE(Graphic[] graphicsSE) {
		this.graphicsSE = graphicsSE;
	}
    
	public Graphic getGraphic(Entity e) {
		return getGraphic(e.getAngle(),e.getMoveAnimationCounter());
	}

	public Graphic getGraphicFront() {
		return getGraphic(270f,0);
	}
	
	public Graphic getGraphicReverse(Entity e) {
		float cos = (float)Math.cos(Math.toRadians(e.getAngle()));
		if (Math.abs(cos) < Constants.EPS)
			return getGraphic(e);
		else
			return getGraphic((float)Math.toDegrees(Math.acos(-cos)),e.getMoveAnimationCounter());
	}
	
	public Graphic getGraphic(float moveAnimationCounter) {
		return getGraphic(0,moveAnimationCounter);
	}
	
	private Graphic getGraphic(float angle,float moveAnimationCounter) {
		Graphic[] set;
    	if ((angle>=337.5 && angle<360) || (angle>=0 && angle<22.5)) {
        	set = getGraphicsE();
    	}
    	else if (angle>=22.5 && angle<47.5) {
        	set = getGraphicsNE();
    	}
    	else if (angle>=47.5 && angle<132.5) { //+-20
        	set = getGraphicsN();
    	}
    	else if (angle>=132.5 && angle<157.5) {
        	set = getGraphicsNW();
    	}
    	else if (angle>=157.5 && angle<202.5) {
        	set = getGraphicsW();
    	}
    	else if (angle>=202.5 && angle<227.5) {
        	set = getGraphicsSW();
    	}
    	else if (angle>=227.5 && angle<312.5) { //+-20
        	set = getGraphicsS();
    	}
    	else { //if (angle>=312.5 && angle<337.5)
        	set = getGraphicsSE();
    	}
    	Graphic ret;
    	if (!isRandom())
    		ret = set[getAnimId((int)moveAnimationCounter,set.length)];
    	else {
    		float r = moveAnimationCounter+(float)Math.abs(this.hashCode()%1000);
    		ret = set[(int)r%set.length];
    	}
    	if (ret == null) {
			System.err.println("--------------");
			System.err.println("CRITICAL ERROR");
    		System.err.println("Invalid graphic in sprite.");
			System.err.println("animId "+getAnimId((int)moveAnimationCounter,set.length)+" max "+set.length+"  angle "+angle);
			System.err.println("--------------");
    		ret = Resources.getDefaultGraphic(); 
    	}    	
    	return ret;
	}    

    protected static int getAnimId(int i,int max) {
    	int ret = ((i/(1000/max)));
        if (ret >= max)
        	ret = max-1;
    	return ret;
    }

    private boolean isRandom() {
		return random;
	}

	private void setRandom(boolean random) {
		this.random = random;
	}

	public boolean isRotate() {
		return rotate;
	}

	public void setRotate(boolean rotate) {
		this.rotate = rotate;
	}

	public float getTail() {
		return tail;
	}

	public void setTail(float tail) {
		this.tail = tail;
	}

}
