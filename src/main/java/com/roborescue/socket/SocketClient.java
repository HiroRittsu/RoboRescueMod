package com.roborescue.socket;

import com.roborescue.information.Worldinfo;
import com.roborescue.information.Agentinfo;

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
				new Worldinfo();
				new Agentinfo();
				originalSocket.joinClient(port, ip);

				while (true) {

					String msg = originalSocket.subscribeMsgs();

					switch (msg.split(",")[0]) {
					case "command":
						socketReader.readCommand(msg);
						break;

					case "node":
						System.out.println("node");
						socketReader.readNode(msg);
						break;

					case "edge":
						System.out.println("edge");
						socketReader.readEdge(msg);
						break;

					case "road":
						System.out.println("road");
						socketReader.readRoad(msg);
						break;

					case "building":
						System.out.println("building");
						socketReader.readBuilding(msg);
						break;
					case "scenario":
						System.out.println("scenario");
						socketReader.readScenario(msg);
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
