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
			// 3����ȡ������������ȡ�ͻ�����Ϣ
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line =  br.readLine();
			socket.shutdownInput();
			Thread.sleep(5000);
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.println("��ã��ͻ��ˣ�");
			pw.println("���Ƿ��������ͻ���˵��" +  line);
			pw.flush();
			socket.shutdownOutput();
			// 4����ȡ���������Ӧ�ͻ��˵�����
		}catch(Exception e ){
			e.printStackTrace();
		}finally {
			// 5���ر���Դ
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
