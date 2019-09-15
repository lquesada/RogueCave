package com.elezeta.roguecave.gui.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuScroll extends MenuComponent {

	private int w = 0;
	
	private int h = 0;

	private int padding = 0;
	
	private int componentHeight = 0;
	
	private int componentSeparation = 0;
	
	private List<MenuComponent> components = new ArrayList<MenuComponent>();

	private float currentPosition = 0;	

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}
	public int getComponentHeight() {
		return componentHeight;
	}

	public void setComponentHeight(int componentHeight) {
		this.componentHeight = componentHeight;
	}

	public int getComponentSeparation() {
		return componentSeparation;
	}

	public void setComponentSeparation(int componentSeparation) {
		this.componentSeparation = componentSeparation;
	}

	public List<MenuComponent> getComponents() {
		return components;
	}

	public void setComponents(List<MenuComponent> components) {
		this.components = components;
	}

	public int getCurrentPosition() {
		return (int)currentPosition;
	}

	public void setCurrentPosition(float currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getMaxPosition() {
		return getComponents().size()*(getComponentHeight()+getComponentSeparation());
	}
	
	public void scroll(float movement) {
		setCurrentPosition(Math.max(Math.min(getCurrentPosition()+movement,getMaxPosition()-getH()),0));
	}

	public void show(MenuButton button) {
		for (int i = 0;i < getComponents().size();i++) {
			MenuComponent mc = getComponents().get(i);
			if (MenuButton.class.isInstance(mc)) {
				MenuButton mb = (MenuButton)mc;
				if (mb==button) {
					if (getCurrentPosition()+getH()<mb.getY()+mb.getH())
						scroll((mb.getY()+mb.getH())-(getCurrentPosition()+getH()));
					else if (getCurrentPosition()>mb.getY())
						scroll(mb.getY()-getCurrentPosition());
				}
			}
		}
	}
}
