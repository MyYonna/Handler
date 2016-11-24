package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class SocketChannelClient {

	public SocketChannelClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
while(true){
	
	try {
		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		Selector selector = Selector.open();
		sc.register(selector, SelectionKey.OP_CONNECT);
		SocketAddress sa = new InetSocketAddress("127.0.0.1", 10086);
		sc.connect(sa);
		while(selector.select()>0){
			Set<SelectionKey> keys = selector.selectedKeys();
			for(SelectionKey key:keys){
				if(key.isConnectable()){
					System.out.println("客户端：连接服务器成功。。。");
					SocketChannel socketChannel = (SocketChannel)key.channel();
					if(socketChannel.isConnectionPending()){
						socketChannel.finishConnect();
					}
					socketChannel.configureBlocking(false);
					System.out.println("客户端：给服务器发送消息。。。");
					socketChannel.write(ByteBuffer.wrap(new String("你好,服务端").getBytes()));
					socketChannel.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){
					System.out.println("客户端：接收到服务器的消息。。。");
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					SocketChannel socketChannel = (SocketChannel)key.channel();
					int readBytes = 0;
					try{
						int ret = 0;
						String content = "";
						while((ret = socketChannel.read(buffer))>0){
							readBytes += ret;
							content+=new String(buffer.array(),0,ret);
						}
						System.out.print("客户端：接收到服务器的消息："+content);
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
		}
		sc.finishConnect();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	}
	
	public static void readAndWrite(Selector selector,SelectionKey key){
		try{
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void read(SelectionKey key){
		//可读

	}

}
