package com.roborescue.socket;

public class SocketClient {

	public OriginalSocket originalSocket;
	public SocketReader socketReader;
	public int port;
	public String ip;

	public SocketClient(int port, String ip) {
		this.port = port;
		this.ip = ip;
		this.originalSocket = new OriginalSocket();
		this.socketReader = new SocketReader();
		threadSocket();
	}

	private void threadSocket() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				originalSocket.joinClient(port, ip);

				while (true) {

					String msg = originalSocket.subscribeMsgs();

					switch (msg.split(",")[0]) {
					case "command":
						socketReader.readCommand(msg);
						break;
						
					case "node":
						socketReader.readNode(msg);
						break;

					case "edge":
						socketReader.readEdge(msg);
						break;

					case "road":
						socketReader.readRoad(msg);
						break;

					case "building":
						socketReader.readBuilding(msg);
						break;

					case "civilian":
						socketReader.readCivilian(msg);
						break;

					case "AT":
						socketReader.readAT(msg);
						break;

					case "FB":
						socketReader.readFB(msg);
						break;

					case "PF":
						socketReader.readPF(msg);
						break;

					default:
						System.out.println("sokcet例外受信");
						break;
					}

					originalSocket.delaitinon(1);

				}

			}
		}).start();
	}

}
