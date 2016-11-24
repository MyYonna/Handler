package bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHanderThread implements Runnable{

	Socket socket;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try{
			// 3、获取输入流，并读取客户端信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line =  br.readLine();
			socket.shutdownInput();
			Thread.sleep(5000);
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.println("你好，客户端！");
			pw.println("我是服务器，客户端说：" +  line);
			pw.flush();
			socket.shutdownOutput();
			// 4、获取输出流，响应客户端的请求
		}catch(Exception e ){
			e.printStackTrace();
		}finally {
			// 5、关闭资源
			try {
				os.close();
				pw.close();
				br.close();
				isr.close();
				is.close();
				socket.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
	
	public SocketHanderThread(Socket socket) {
		super();
		this.socket = socket;
	}
	
}
