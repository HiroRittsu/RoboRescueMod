package com.roborescuemod.communication;

public class SocketClient {

	String ip = "localhost";

	public SocketAPI connectMapData(int port) {

		SocketAPI socketAPI_map = new SocketAPI();
		socketAPI_map.joinClient(port, ip);

		return socketAPI_map;
	}

}
