package com.elezeta.roguecave.network.server;

import java.io.IOException;

import com.elezeta.roguecave.network.Network;
import com.elezeta.roguecave.network.Network.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class GameServer {

	public Server server;
	
	static class GameConnection extends Connection {
		
	}
	
	public boolean openServer() {
		server = new Server(){
            protected Connection newConnection () {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new GameConnection();
            }
		};
		Network.register(server);

        server.addListener(new Listener() {
                public void received (Connection c, Object object) {
                        if (object instanceof Message) {
                                Message message = (Message)object;
                                String messageText = message.text;
                                if (messageText == null) return;
                                messageText = messageText.trim();
                                if (messageText.length() == 0) return;
                                server.sendToAllTCP(message);
                                return;
                        }
                }

                public void disconnected (Connection c) {
                }
        });
        try {
			server.bind(Network.port);
		} catch (IOException e) {
			return false;
		}
        server.start();
        
		return true;
	}
	
	public void closeServer() {
		server.stop();
	}
	
}