package socket;

import information.Worldinfo;

public class SocketClient {

	public static OriginalSocket originalSocket;
	public SocketReader socketReader;
	public int port;
	public String ip;

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
				new Worldinfo();
				originalSocket.joinClient(port, ip);

				while (true) {
					String msg = originalSocket.subscribeMsgs();

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
