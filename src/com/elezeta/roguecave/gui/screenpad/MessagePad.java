package com.elezeta.roguecave.gui.screenpad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.elezeta.roguecave.gui.RogueCaveGUI;

public class MessagePad {
	
	private List<ScreenMessage> messages;
	
	private ScreenMessage nextMessage;
	
	private LinkedList<ScreenMessage> messagePool;
	
	private float waitTime=0f;
	
	private boolean talkMode = false;
	private String talkString = "";
	private int talkStringIndex = 0;
	private Set<Integer> talkKeys;
	private LinkedList<String> talkHistory;
	private int talkHistoryIndex = -1;
	private String talkOriginal = "";
	
	public MessagePad() {
		messages = new ArrayList<ScreenMessage>();
		messagePool = new LinkedList<ScreenMessage>(); 
		talkKeys = new HashSet<Integer>();
		talkHistory = new LinkedList<String>();
	}

	private void pull() {
		if (nextMessage == null && !messagePool.isEmpty()) {
			nextMessage = messagePool.pollFirst();
			waitTime = RogueCaveGUI.config.messageAppearTime;
		}
	}
	
	public void pass(float delta) {
		pull();
		if (nextMessage != null) {
			waitTime-=delta*(((float)messagePool.size())*1.5f+1);
			if (waitTime<=0f) {
				messages.add(nextMessage);
				nextMessage = null;
				pull();
			}
		}
		for (int i = 0;i < messages.size();i++)
			messages.get(i).pass(delta);

		int size = messages.size();
		if (nextMessage != null)
			size++;
		int disable = size-RogueCaveGUI.config.softMessageLimit;
		for (int i = 0;i < disable;i++) {
			if (messages.get(i).getTime()>RogueCaveGUI.config.messageDisappearTime)
				messages.get(i).setTime(RogueCaveGUI.config.messageDisappearTime);
		}

		int disableFinal = size-RogueCaveGUI.config.mediumMessageLimit;
		for (int i = 0;i < disableFinal;i++) {
			messages.get(i).setTime(0f);
		}

		int sizeHard = messages.size();
		if (nextMessage != null)
			sizeHard++;
		while (sizeHard>RogueCaveGUI.config.hardMessageLimit) {
			messages.remove(0);
			sizeHard--;
		}

	}

	public float offset() {
		if (nextMessage != null)
			return 1f-(waitTime/RogueCaveGUI.config.messageAppearTime);
		else
			return 1f;
	}
	
	public void addMessage(ScreenMessage message) {
		this.messagePool.addLast(message);
		pull();
	}
	
	public List<ScreenMessage> getMessages() {
		return Collections.unmodifiableList(messages);
	}

	public ScreenMessage getNextMessage() {
		return nextMessage;
	}

	public List<ScreenMessage> getMessagePool() {
		return Collections.unmodifiableList(messagePool);
	}

	public boolean isTalkMode() {
		return talkMode;
	}

	public void setTalkMode(boolean talkMode) {
		this.talkMode = talkMode;
	}

	public String getTalkString() {
		return talkString;
	}

	public void setTalkString(String talkString) {
		this.talkString = talkString;
	}

	public Set<Integer> getTalkKeys() {
		return talkKeys;
	}

	public LinkedList<String> getTalkHistory() {
		return talkHistory;
	}

	public int getTalkHistoryIndex() {
		return talkHistoryIndex;
	}

	public void setTalkHistoryIndex(int talkHistoryIndex) {
		this.talkHistoryIndex = talkHistoryIndex;
	}

	public String getTalkOriginal() {
		return talkOriginal;
	}

	public void setTalkOriginal(String talkOriginal) {
		this.talkOriginal = talkOriginal;
	}

	public int getTalkStringIndex() {
		return talkStringIndex;
	}

	public void setTalkStringIndex(int talkStringIndex) {
		this.talkStringIndex = talkStringIndex;
	}
	
}
