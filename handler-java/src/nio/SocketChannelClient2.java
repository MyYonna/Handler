package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class SocketChannelClient2 {

	public SocketChannelClient2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SocketChannel sc;
		try {
			sc = SocketChannel.open();
			sc.configureBlocking(false);
			Selector selector = Selector.open();
			sc.register(selector, SelectionKey.OP_CONNECT);
			SocketAddress sa = new InetSocketAddress("127.0.0.1", 10086);
			sc.connect(sa);
			while(selector.select()>0){
				Set<SelectionKey> keys = selector.selectedKeys();
				for(SelectionKey key:keys){
					if(key.isConnectable()){
						readAndWrite(selector,key);
					}else if(key.isReadable()){
						read(key);
					}
				}
			}
			sc.finishConnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readAndWrite(Selector selector,SelectionKey key){
		try{
			System.out.println("�ͻ��ˣ����ӷ������ɹ�������");
			SocketChannel socketChannel = (SocketChannel)key.channel();
			if(socketChannel.isConnectionPending()){
				socketChannel.finishConnect();
			}
			socketChannel.configureBlocking(false);
			System.out.println("�ͻ��ˣ���������������Ϣ������");
			socketChannel.write(ByteBuffer.wrap(new String("���,�����").getBytes()));
			socketChannel.register(selector, SelectionKey.OP_READ);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void read(SelectionKey key){
		//�ɶ�
		System.out.println("�ͻ��ˣ����յ�����������Ϣ������");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		SocketChannel socketChannel = (SocketChannel)key.channel();
		if(socketChannel.isConnectionPending()){
			try {
				socketChannel.finishConnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int readBytes = 0;
		try{
			int ret = 0;
			String content = "";
			while((ret = socketChannel.read(buffer))>0){
				readBytes += ret;
				content+=new String(buffer.array(),0,ret);
			}
			System.out.print("�ͻ��ˣ����յ�����������Ϣ��"+content);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(buffer != null){
				buffer.flip();
				buffer.clear();
			}
		}
	}

}
