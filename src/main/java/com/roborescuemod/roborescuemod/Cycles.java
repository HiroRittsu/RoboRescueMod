package com.roborescuemod.roborescuemod;

import com.roborescuemod.communication.SocketClient;

public class Cycles {

	SocketClient socketClient = new SocketClient();

	public Cycles() {
		// socket
		new Thread(new Runnable() {
			@Override
			public void run() {
				socketClient.connectMapData(6591);
			}
		}).start();
	}

	public void calcCycles() {
		
	}

}
