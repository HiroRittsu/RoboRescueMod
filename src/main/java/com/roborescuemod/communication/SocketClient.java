package com.roborescuemod.communication;

import java.io.InputStream;

public class SocketClient {

	private OriginalSocket originalSocket = new OriginalSocket();

	public SocketClient(int port, String ip) {
		originalSocket.joinClient(port, ip);
	}

	public InputStream subscribeGML() {
		return originalSocket.subscribeFile();
	}

	public String subscribeText() {
		return originalSocket.subscribeMsgs();
	}

}
