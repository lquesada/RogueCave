package com.elezeta.roguecave.upgrades;

import java.util.List;

import com.elezeta.roguecave.data.UpgradeCategoryID;
import com.elezeta.roguecave.data.UpgradeID;
import com.elezeta.roguecave.gui.GraphicID;

public class UpgradeCategory {

	private UpgradeCategoryID upgradeCategoryID;
	private boolean show;
	private List<UpgradeID> contents;
	private GraphicID graphic;
	
	public UpgradeCategoryID getUpgradeCategoryID() {
		return upgradeCategoryID;
	}
	
	public void setUpgradeCategoryID(UpgradeCategoryID upgradeCategoryID) {
		this.upgradeCategoryID = upgradeCategoryID;
	}
	
	public boolean isShow() {
		return show;
	}
	
	public void setShow(boolean show) {
		this.show = show;
	}
	
	public List<UpgradeID> getContents() {
		return contents;
		
	}
	
	public void setContents(List<UpgradeID> contents) {
		this.contents = contents;
	}

	public GraphicID getGraphic() {
		return graphic;
	}

	public void setGraphic(GraphicID graphic) {
		this.graphic = graphic;
	}
	
}
