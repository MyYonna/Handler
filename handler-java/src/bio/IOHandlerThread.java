package bio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class IOHandlerThread implements Runnable {

	private SelectionKey key;
	public IOHandlerThread(SelectionKey key) {
		// TODO Auto-generated constructor stub
		this.key = key;
	}

	@Override
	public void run() {
		try{
			// TODO Auto-generated method stub
			System.out.println("服务器：接收到客户发送的数据。。。");
			SocketChannel sc = (SocketChannel)key.channel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int i = 0;
			String content = "";
			while((i=sc.read(byteBuffer))>0){
				content += new String(byteBuffer.array());
			}
			byteBuffer.clear();
			System.out.println("服务器：客户端发送的数据："+content);
			
			
			System.out.println("服务器：服务器端发送的数据。。。");
			ByteBuffer byteBuffer1 = ByteBuffer.wrap(new String("你好，客户端").getBytes());
			sc.write(byteBuffer1);
			byteBuffer1.flip();
			byteBuffer1.clear();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
