package com.elezeta.roguecave.gui.resources;

public class Graphic {
    
	private org.newdawn.slick.opengl.Texture texture;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private float xStanding;
    private float yStanding;
    private float xCenter;
    private float yCenter;
    private float width;
    private float height;
    
    public Graphic(org.newdawn.slick.opengl.Texture texture,float x1,float y1,float x2,float y2, int xCenter, int yCenter,int width,int height) {
    	this.texture = texture;
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setXStanding(xCenter);
        setYStanding(yCenter);
        setXCenter(width/2f);
        setYCenter(height/2f);
        setWidth(width);
        setHeight(height);
    }
    
    public org.newdawn.slick.opengl.Texture getTexture() {
    	return texture;
    }
    
    public float getX1() {
        return x1;
    }

	private void setX1(float x1) {
		this.x1 = x1;
	}

    public float getY1() {
        return y1;
    }

	private void setY1(float y1) {
		this.y1 = y1;
	}
	
    public float getX2() {
        return x2;
    }

	private void setX2(float x2) {
		this.x2 = x2;
	}

    public float getY2() {
        return y2;
    }

	private void setY2(float y2) {
		this.y2 = y2;
	}

    public float getXStanding() {
        return xStanding;
    }

	private void setXStanding(float xStanding) {
		this.xStanding = xStanding;
	}

    public float getYStanding() {
        return yStanding;
    }

	private void setYStanding(float yStanding) {
		this.yStanding = yStanding;
	}

    public float getXCenter() {
		return xCenter;
	}

	private void setXCenter(float xCenter) {
		this.xCenter = xCenter;
	}

	public float getYCenter() {
		return yCenter;
	}

	private void setYCenter(float yCenter) {
		this.yCenter = yCenter;
	}

	public float getWidth() {
        return width;
    }

	private void setWidth(float width) {
		this.width = width;
	}

    public float getHeight() {
        return height;
    }

	private void setHeight(float height) {
		this.height = height;
	}

}
