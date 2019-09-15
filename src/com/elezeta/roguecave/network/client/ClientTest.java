package com.elezeta.roguecave.network.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

//TODO remove

public class ClientTest {
	public static void main(String args[]) {
		final GameClient gameClient = new GameClient();
		gameClient.connectToServer();

      JFrame frame = new JFrame("Client");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter() {
              public void windowClosed (WindowEvent evt) {
                      gameClient.connectToServer();
              }
      });
      frame.getContentPane().add(new JLabel("CLIENT"));
      frame.setSize(320, 200);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);

	}
	
}