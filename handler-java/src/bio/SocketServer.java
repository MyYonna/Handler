package bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

	public SocketServer() {
		// TODO Auto-generated constructor stub
	}

	public static void createServer() {
		// 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket = null;
		Socket socket = null;
		ExecutorService pool = Executors.newSingleThreadExecutor();
			try {
				serverSocket = new ServerSocket(10086);
				while(true){
				socket = serverSocket.accept();
				SocketHanderThread sht = new SocketHanderThread(socket);
				Thread t = new Thread(sht);
				pool.execute(t);
				//t.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 

	}

	public static void main(String[] args) {
		SocketServer.createServer();
	}
	

}
