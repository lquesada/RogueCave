package com.elezeta.roguecave.network.client;

import java.io.IOException;

import com.elezeta.roguecave.network.Network;
import com.elezeta.roguecave.network.Network.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;

public class GameClient {
    Client client;
    String name;

    public boolean connectToServer() {
            client = new Client();
            client.start();

            // For consistency, the classes to be sent over the network are
            // registered by the same method for both the client and server.
            Network.register(client);

            client.addListener(new Listener() {
                    public void connected (Connection connection) {
                            Message message = new Message();
                            message.text = "HELLO";
                            client.sendTCP(message);
                    }

                    public void received (Connection connection, Object object) {
                            if (object instanceof Message) {
                                    Message message = (Message)object;
                                    System.out.println("CLIENT RECEIVED MESSAGE "+message.text);
                                    return;
                            }
                    }

            });

            try {
				client.connect(5000,"localhost", Network.port);
			} catch (IOException e) {
				return false;
			}
            return true;
    }
}


