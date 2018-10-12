package com.example.examplemod;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MobileSockets {

	private ServerSocket server = null;
	private Socket socket = null;
	private OutputStreamWriter output;
	private InputStreamReader input;

	private void buffer() { // buffer変数作成
		try {
			// socket送信
			output = new OutputStreamWriter(socket.getOutputStream());
			// socket受け取り
			input = new InputStreamReader(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Error CreateBuffer");
		}
	}

	public void server(int port) {

		try {
			// serverの設定
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", port));

			System.out.println("接続待ち");
			// 応答待機、応答後接続
			socket = server.accept();

			buffer();

		} catch (IOException e) {
			System.out.println("un-connection");
		}

		System.out.println("connected");

	}

	public void client(int port) {

		try {
			socket = new Socket("localhost", port);
			buffer();
		} catch (IOException e) {
			System.out.println(".");
		}

		System.out.println("connected");
	}

	public void pub_msgs(String s) {
		try {
			// 送信
			(new BufferedWriter(output)).write(s);
			System.out.println("send");
		} catch (IOException e) {
			System.out.println("Error Publisher");
		}
	}

	public String get_msgs() {

		try {
			// 受信
			return (new BufferedReader(input)).readLine();
		} catch (IOException e) {
			System.out.println("Error Subsclibe");
		}

		return null;
	}

	public void pub_file(String Path) {

		byte[] buffer   = new byte[512];      // ファイル送信時のバッファ

		try {
			InputStream  inputStream  = new FileInputStream(Path);
			OutputStream outputStream = socket.getOutputStream();

			// ファイルをストリームで送信
			int fileLength;
			while ((fileLength = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, fileLength);
			}

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}

	public InputStream get_file() {

		byte[] buffer = new byte[512]; // ファイル受信時のバッファ

		try {
			InputStream  inputStream  = socket.getInputStream();
			OutputStream outputStream = new FileOutputStream("test");

			// ファイルをストリームで受信
			int fileLength;
			while ((fileLength = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, fileLength);
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return null;
	}

}
