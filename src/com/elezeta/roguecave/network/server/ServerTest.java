package com.elezeta.roguecave.network.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

//TODO remove

public class ServerTest {
	public static void main(String args[]) {
		final GameServer gameServer = new GameServer();
		gameServer.openServer();

        JFrame frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
                public void windowClosed (WindowEvent evt) {
                        gameServer.closeServer();
                }
        });
        frame.getContentPane().add(new JLabel("SERVER"));
        frame.setSize(320, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

	}
	
}

