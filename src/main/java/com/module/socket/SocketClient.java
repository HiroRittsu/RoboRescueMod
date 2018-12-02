package com.module.socket;

import com.module.information.Worldinfo;

public class SocketClient {

	public static OriginalSocket originalSocket;
	public SocketReader socketReader;
	public int port;
	public String ip;
	public Worldinfo worldinfo;

	public SocketClient(int port, String ip) {
		this.port = port;
		this.ip = ip;
		originalSocket = new OriginalSocket();
		this.socketReader = new SocketReader();
		threadSocket();
	}

	public static void publishCommand(String command) {
		originalSocket.publishMsgs("command," + command);
	}

	private void threadSocket() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				worldinfo = new Worldinfo();
				originalSocket.joinClient(port, ip);

				while (true) {
					String msg = originalSocket.subscribeMsgs();
					System.out.println(msg);

					switch (msg.split(",")[0]) {
					case "command":
						System.out.println("command");
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
					case "building_neighbour":
						System.out.println("neighbour");
						socketReader.readBuilding_neighbour(msg);
						break;
					case "scenario":
						System.out.println("scenario");
						socketReader.readScenario(msg);
						break;

					case "civilian_steta":
						System.out.println("civilian_state");
						socketReader.readCivilian_State(msg);
						break;

					case "ambulanceteam_steta":
						System.out.println("ambulanceteam_state");
						socketReader.readAT_State(msg);
						break;

					case "firebrigade_steta":
						System.out.println("firebrigade_state");
						socketReader.readFB_State(msg);
						break;

					case "policeforce_steta":
						System.out.println("policeforce_state");
						socketReader.readPF_State(msg);
						break;

					case "building_state":
						System.out.println("building_state");
						break;

					case "blockade_steta":
						System.out.println("blockade_steta");
						break;

					default:
						System.out.println("sokcet例外受信" + msg.split(",")[0]);
						break;
					}

					originalSocket.delaitinon(1);
				}
			}
		}).start();
	}
}
