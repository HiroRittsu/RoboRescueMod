package com.roborescuemod.communication;

public class SocketClient {

	public SocketAPI connectMapData(int port) {
		
		SocketAPI socketAPI_map = new SocketAPI();
		socketAPI_map.joinClient(port);
		
		return socketAPI_map;
	}

}
