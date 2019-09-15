package com.elezeta.roguecave.gui.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<MenuComponent> components = new ArrayList<MenuComponent>();

	public List<MenuComponent> getComponents() {
		return components;
	}

	public MenuButton getButton(int number) {
		for (int i = 0;i < getComponents().size();i++) {
			MenuComponent mc = getComponents().get(i);
			if (MenuButton.class.isInstance(mc)) {
				MenuButton mb = (MenuButton)mc; 
				if (mb.getNumber()==number)
					return mb;
			}
			if (MenuScroll.class.isInstance(mc)) {
				MenuScroll ms = (MenuScroll)mc;
				for (int j = 0;j < ms.getComponents().size();j++) {
					MenuComponent mc2 = ms.getComponents().get(j);
					if (MenuButton.class.isInstance(mc2)) {
						MenuButton mb = (MenuButton)mc2; 
						if (mb.getNumber()==number)
							return mb;
					}
				}
			}
		}
		return null;
	}

	public int getButtonSize() {
		int n = 0;
		for (int i = 0;i < getComponents().size();i++) {
			MenuComponent mc = getComponents().get(i);
			if (MenuButton.class.isInstance(mc)) {
				n++;
			}
			if (MenuScroll.class.isInstance(mc)) {
				MenuScroll ms = (MenuScroll)mc;
				for (int j = 0;j < ms.getComponents().size();j++) {
					MenuComponent mc2 = ms.getComponents().get(j);
					if (MenuButton.class.isInstance(mc2))
						n++;
				}
			}
		}
		return n;
	}

	public void show(MenuButton button) {
		for (int i = 0;i < getComponents().size();i++) {
			MenuComponent mc = getComponents().get(i);
			if (MenuScroll.class.isInstance(mc)) {
				MenuScroll ms = (MenuScroll)mc;
				if (ms.getComponents().contains(button))
					ms.show(button);
			}
		}
	}
	
}
