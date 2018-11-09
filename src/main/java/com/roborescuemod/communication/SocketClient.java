package com.roborescuemod.communication;

import java.util.ArrayList;

public class SocketClient {

	public static ArrayList<String> agent_data = new ArrayList<>();

	private OriginalSocket originalSocket = new OriginalSocket();

	public SocketClient() {
		threadSocket();
	}

	private void threadSocket() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				originalSocket.joinClient(12345, "localhost");

				while (true) {

					agent_data.add(originalSocket.subscribeMsgs());

					originalSocket.delaitinon(1);

				}

			}
		}).start();
	}

}
