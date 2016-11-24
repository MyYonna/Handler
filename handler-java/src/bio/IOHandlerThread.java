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
			System.out.println("�����������յ��ͻ����͵����ݡ�����");
			SocketChannel sc = (SocketChannel)key.channel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int i = 0;
			String content = "";
			while((i=sc.read(byteBuffer))>0){
				content += new String(byteBuffer.array());
			}
			byteBuffer.clear();
			System.out.println("���������ͻ��˷��͵����ݣ�"+content);
			
			
			System.out.println("���������������˷��͵����ݡ�����");
			ByteBuffer byteBuffer1 = ByteBuffer.wrap(new String("��ã��ͻ���").getBytes());
			sc.write(byteBuffer1);
			byteBuffer1.flip();
			byteBuffer1.clear();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
