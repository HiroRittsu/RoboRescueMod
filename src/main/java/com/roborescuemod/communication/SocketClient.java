package com.roborescuemod.communication;

public class SocketClient {

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

					RescueInfo.registerData(originalSocket.subscribeMsgs());

					originalSocket.delaitinon(1);

				}

			}
		}).start();
	}

}
