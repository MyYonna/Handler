package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient2 {

	public SocketClient2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			while(true){
				
				
				
				Socket  socket = new Socket("127.0.0.1",10086);
				String message1 = "你好，服务器";
				PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
				pw.println(message1);
				pw.flush();
				socket.shutdownOutput();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String message = br.readLine();
				System.out.println(System.currentTimeMillis());
				System.out.println("我是客户端，服务器说："+message);
				System.out.println(br.readLine());
				socket.shutdownInput();
				socket.close();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
